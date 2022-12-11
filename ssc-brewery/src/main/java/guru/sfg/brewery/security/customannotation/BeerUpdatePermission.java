package guru.sfg.brewery.security.customannotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('beer.update') OR hasAuthority('customer.order.update')")
public @interface BeerUpdatePermission {
}
