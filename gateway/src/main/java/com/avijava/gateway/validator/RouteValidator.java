package com.avijava.gateway.validator;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
@Component
public class RouteValidator {
    public static final List<String> openApiEndpoints = List.of(
            "/eureka/**",
            "/auth/**",
            "/employee/v3/api-docs",
            "/employee/v3/api-docs/**",
            "/employee/swagger-ui",
            "/swagger-resources",
            "/webjars/**",
            "employee/swagger-ui.html",
            "/api/v1/department/v3/api-docs",
            "/api/v1/department/v3/api-docs/**",
            "/api/v1/department/swagger-ui",
            "/api/v1/department/swagger-ui.html",
            "/api/v1/department/webjars/**",
            "/actuator/**"
    );
    public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints
            .stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
}