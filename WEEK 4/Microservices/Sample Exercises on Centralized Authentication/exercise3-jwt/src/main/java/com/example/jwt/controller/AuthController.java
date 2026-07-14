package com.example.jwt.controller;

import com.example.jwt.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * The original exercise only covered validating incoming JWTs, but a
 * runnable demo also needs a way to issue one. This is a minimal,
 * unauthenticated "login" endpoint for local testing only - in a real
 * system you would authenticate the user's credentials first.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        String username = body.getOrDefault("username", "anonymous");
        String token = jwtTokenProvider.createToken(username);
        return Map.of("token", token);
    }
}
