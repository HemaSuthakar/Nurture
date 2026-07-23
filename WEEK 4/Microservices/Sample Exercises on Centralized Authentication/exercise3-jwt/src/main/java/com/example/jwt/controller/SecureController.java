package com.example.jwt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/secure")
    public String secure(Authentication authentication) {
        return "Hello " + authentication.getName() + ", this is a JWT-secured endpoint";
    }
}
