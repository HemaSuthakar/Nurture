package com.example.jwt.config;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.security.Key;
import java.nio.charset.StandardCharsets;

@Configuration
public class JwtConfig {

    @Value("${spring.security.jwt.secret}")
    private String secret;

    public String getSecret() {
        return secret;
    }

    /**
     * HS256 requires a key of at least 256 bits. Derive a safe signing key
     * from the configured secret via SHA-256 hashing so any plain-text
     * secret in application.yml still produces a valid key length.
     */
    public Key getSigningKey() {
        // HS256 needs a >= 256-bit key. If the configured secret is shorter
        // than that, derive a 256-bit key from it via SHA-256 instead of
        // failing at signing time.
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length < 32) {
            java.security.MessageDigest sha256;
            try {
                sha256 = java.security.MessageDigest.getInstance("SHA-256");
                keyBytes = sha256.digest(keyBytes);
            } catch (java.security.NoSuchAlgorithmException e) {
                throw new IllegalStateException(e);
            }
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
