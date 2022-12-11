//package guru.sfg.brewery.web.controllers.api;
//
//﻿
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import guru.sfg.brewery.bootstrap.DefaultBreweryLoader;
//import guru.sfg.brewery.domain.Beer;
//import guru.sfg.brewery.domain.Customer;
//import guru.sfg.brewery.repositories.BeerOrderRepository;
//import guru.sfg.brewery.repositories.BeerRepository;
//import guru.sfg.brewery.repositories.CustomerRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; import static org.springframework.test.web.servlet.result MockMvcResultMatchers.status;
//@SpringBootTest
//class BeerOrderControllerTest extends BaseIT {
//    public static final String API_ROOT = "/api/v1/customers/";
//    @Autowired
//    CustomerRepository customerRepository;
//    @Autowired
//    BeerOrderRepository beerOrderRepository;
//    @Autowired
//    BeerRepository beerRepository;
//    @Autowired
//    ObjectMapper objectMapper;
//
//    Customer stPeteCustomer;
//    Customer dunedinCustomer;
//    Customer keyWestCustomer;
//    List<Beer> loadedBeers;
//
//    @BeforeEach
//    void setUp() {
//        stPeteCustomer = customerRepository.findAllByCustomerName(DefaultBreweryLoader.ST_PETE_DISTRIBUTING).orElseThrow();
//        dunedinCustomer = customerRepository.findAllByCustomerName (DefaultBreweryLoader.DUNEDIN_DISTRIBUTING).orElseThrow();
//        keyWestCustomer = customerRepository.findAllByCustomerName (DefaultBreweryLoader.KEY_WEST_DISTRIBUTORS).orElseThrow();
//        loadedBeers = beerRepository.findAll();
//    }
//    }
//
//
//
//// @DisplayName("Create Test")
//// @Nested
////class createOrderTests {
