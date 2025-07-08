package com.avijava.gateway.swagger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/swagger")
public class SwaggerRedirectController {

    @GetMapping("/employee")
    public ResponseEntity<Void> redirectToEmployeeSwagger() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("http://localhost:8081/swagger-ui.html"))
                .build();
    }
}
