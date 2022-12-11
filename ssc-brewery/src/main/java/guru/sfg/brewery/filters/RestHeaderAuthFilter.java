//package guru.sfg.brewery.filters;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//public class RestHeaderAuthFilter extends AbstractAuthenticationProcessingFilter {
//
//    public RestHeaderAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
//        super(requiresAuthenticationRequestMatcher);
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//            throws IOException, ServletException {
//
//        log.debug("--------------INSIDE doFilter()");
//
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//
////        if (!requiresAuthentication(request, response)) {
////            chain.doFilter(request, response);
////            return;
////        }
//
//        if (logger.isDebugEnabled()) {
//            logger.debug("Request is to process authentication");
//        }
//
//        try{
//            //call attemptAuthenticate() method
//            Authentication authResult = attemptAuthentication(request, response);
//
//            if(authResult!=null) {
//                log.debug("--------------INSIDE doFilter()--attempAuth success--inside if");
//                successfulAuthentication(request, response, chain, authResult);
//                chain.doFilter(request, response);
//            }
//            else {
//                log.debug("--------------INSIDE doFilter()--attempAuth failed--inside else");
//                //right now even if user is not authenticated we are not doing anything, just continuing the filter.
//                chain.doFilter(request, response);
//            }
//        }catch (AuthenticationException e){
//            unsuccessfulAuthentication(request, response, e);
//        }
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//       String username = getUserName(request);
//       String password = getPassword(request);
//
//       if(username==null){
//           username="";
//       }
//       if(password == null){
//           password ="";
//       }
//       log.debug("Authentication User:"+username);
//
//        //Note: depending on the Authentication technique, it will compare the username and Password (http basic, custom userDetailService...)
//        //So if we configured Custom userDetailsService from db, it will take username and password in db.
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//
//        if(!StringUtils.isEmpty(username)) {
//            return this.getAuthenticationManager().authenticate(token);
//        }
//        else {
//            return null;
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
//            throws IOException, ServletException {
//
//        if (logger.isDebugEnabled()) {
//            logger.debug("Authentication success. Updating SecurityContextHolder to contain: "
//                    + authResult);
//        }
//
//        SecurityContextHolder.getContext().setAuthentication(authResult);
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request,
//                                              HttpServletResponse response, AuthenticationException failed)
//            throws IOException, ServletException {
//        SecurityContextHolder.clearContext();
//
//        if (logger.isDebugEnabled()) {
//            logger.debug("Authentication request failed: " + failed.toString(), failed);
//            logger.debug("Updated SecurityContextHolder to contain null Authentication");
//        }
//
//        response.sendError(HttpStatus.UNAUTHORIZED.value(),
//                HttpStatus.UNAUTHORIZED.getReasonPhrase());
//    }
//
//    private String getPassword(HttpServletRequest request) {
//        return request.getHeader("username");
//    }
//
//    private String getUserName(HttpServletRequest request) {
//        return request.getHeader("password");
//    }
//}
