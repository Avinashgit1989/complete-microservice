package com.avijava.gateway.filter;

import com.avijava.gateway.util.JwtUtils;
import com.avijava.gateway.validator.RouteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private JwtUtils jwtUtils;

   /* @Autowired
    private RestTemplate restTemplate;*/


    public  AuthenticationFilter(){
    super(Config.class);
    }



    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())){
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw  new RuntimeException("Missing Authorization Header");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                    if (!authHeader.isEmpty() && authHeader.startsWith("Bearer ")) {
                        authHeader = authHeader.substring(7);
                    }else {
                        throw new RuntimeException("Invalid Authorization Header");
                    }
                // Here you can add logic to validate the token, e.g., decode it, check its validity, etc.
                try{
                        //restTemplate.getForObject("http://IDENTITY-SERVICE/auth/validateToken?token=" + authHeader, String.class);
                       //ResponseEntity<String> isvalid = identityClient.validateToken(authHeader);
                       //We can implement using rest client or feign client but for now i am using Util class.
                        jwtUtils.validateToken(authHeader);
                }
                catch (Exception e) {
                    throw new RuntimeException("Un Authorized access to application: " + e.getMessage());
                }
            }
            return chain.filter(exchange);
        });
    }

    public static  class Config {
        // Configuration properties can be added here if needed
    }
}
