package com.mylearning.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping("/order")
    public ResponseEntity<String> orderFallback(){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("We are facing a problem in order-service ! Please contact to help desk");
    }

    @PostMapping("/order")
    public ResponseEntity<String> orderFallbackPost(){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("We are facing a problem in order-service ! Please contact to help desk");
    }

    @GetMapping("/payment")
    public ResponseEntity<String> paymentFallback(){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("We are facing a problem in payment-service ! Please contact to help desk");
    }

    @PostMapping("/payment")
    public ResponseEntity<String> paymentFallbackPost(){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("We are facing a problem in payment-service ! Please contact to help desk");
    }

    @GetMapping("/user")
    public ResponseEntity<String> userFallback(){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("We are facing a problem in user-service ! Please contact to help desk");
    }

    @PostMapping("/user")
    public ResponseEntity<String> userFallbackPost(){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("We are facing a problem in user-service ! Please contact to help desk");
    }

    @GetMapping("/auth")
    public ResponseEntity<String> authFallback(){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("We are facing a problem in auth-service ! Please contact to help desk");
    }

    @PostMapping("/auth")
    public ResponseEntity<String> authFallbackPost(){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("We are facing a problem in auth-service ! Please contact to help desk");
    }
}