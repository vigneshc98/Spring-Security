package guru.sfg.brewery.security.customannotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('beer.delete') OR hasAuthority('customer.order.delete')")
public @interface BeerDeletePermission {
}
