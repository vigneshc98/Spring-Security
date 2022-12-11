package guru.sfg.brewery.web.controllers.api;

import guru.sfg.brewery.domain.security.User;
import guru.sfg.brewery.security.customannotation.BeerReadPermission;
import guru.sfg.brewery.services.BeerOrderService;
import guru.sfg.brewery.web.model.BeerOrderDto;
import guru.sfg.brewery.web.model.BeerOrderPagedList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Slf4j
@RequestMapping("/api/v2/orders/")
@RestController
public class BeerOrderControllerV2 {
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerOrderService beerOrderService;

    public BeerOrderControllerV2(BeerOrderService beerOrderService) {
        this.beerOrderService = beerOrderService;
    }

    @BeerReadPermission
    @GetMapping
    public BeerOrderPagedList listOrders(@AuthenticationPrincipal User user,
                                         @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                         @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        //If customer exist for the user fetch based on customerId or fetch All beerOrders.
        // (only Admin(user1),Customer(user2),User(user3) don't have customer)
        if(user.getCustomer()!=null){
            return beerOrderService.listOrders(user.getCustomer().getId(), PageRequest.of(pageNumber, pageSize));
        }else{
            return beerOrderService.listOrders(PageRequest.of(pageNumber, pageSize));
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerOrderDto placeOrder(@PathVariable("customerId") UUID customerId, @RequestBody BeerOrderDto beerOrderDto) {
        return beerOrderService.placeOrder(customerId, beerOrderDto);
    }

    @BeerReadPermission
    @GetMapping("{orderId}")
    public BeerOrderDto getOrder (@PathVariable("orderId") UUID orderId) {
        BeerOrderDto beerOrderDto = beerOrderService.getOrderById(orderId);

        if(beerOrderDto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        log.debug("Found Order: "+beerOrderDto);

        return beerOrderDto;
    }

    @PutMapping("{orderId}/pickup") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pickupOrder (@PathVariable("customerId") UUID customerId,
                             @PathVariable("orderId") UUID orderId) {
        beerOrderService.pickupOrder (customerId, orderId);
    }
}