package guru.sfg.brewery.config;

//import guru.sfg.brewery.filters.RestHeaderAuthFilter;
import guru.sfg.brewery.security.google.Google2faFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)  //securedEnabled = Determines if Spring Security's Secured annotations should be enabled
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PersistentTokenRepository persistentTokenRepository;
    private final Google2faFilter google2faFilter;

//    public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager){
//        RestHeaderAuthFilter filter = new RestHeaderAuthFilter(new AntPathRequestMatcher("/api/**"));
//        filter.setAuthenticationManager(authenticationManager);
//        return filter;
//    }

    // needed for use Spring Security with Spring Data JPA SPel
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension(){
        return new SecurityEvaluationContextExtension();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(restHeaderAuthFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

        // apply this filter before user session works begins (create, store ...)
        http.addFilterBefore(google2faFilter, SessionManagementFilter.class);

        http
                .authorizeRequests(authorize->{
                    authorize
                            .antMatchers(("/h2-console/**")).permitAll()  //not recomemded in production
                            .antMatchers("/","/webjars/**","/login","/resources/**").permitAll();
//                            .antMatchers("/beers/find","/beers").permitAll()
//                            .antMatchers(HttpMethod.GET, "/api/v1/beer/**").permitAll()
//                            .mvcMatchers(HttpMethod.DELETE, "/api/v1/beer/**").hasRole("ADMIN")
//                            .mvcMatchers(HttpMethod.GET, "/api/v1/beerUpc/{upc}").permitAll()
//                            .mvcMatchers("/brewery/breweries").hasAnyRole("ADMIN","CUSTOMER")
//                            .mvcMatchers(HttpMethod.GET, "brewery/api/v1/breweries").hasAnyRole("ADMIN","CUSTOMER");
                })
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**","/api/**")
                //hash based token
                .and().rememberMe().key("sfg-key").userDetailsService(userDetailsService);
                //persistent token
//                .and().rememberMe()
//                        .tokenRepository(persistentTokenRepository)
//                        .userDetailsService(userDetailsService);

        //h2 console config
        http.headers().frameOptions().disable();
        http.cors();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//    }


}
