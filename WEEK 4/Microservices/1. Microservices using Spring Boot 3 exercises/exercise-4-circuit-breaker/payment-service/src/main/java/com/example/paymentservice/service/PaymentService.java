package com.example.paymentservice.service;

import com.example.paymentservice.client.ThirdPartyPaymentClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final ThirdPartyPaymentClient thirdPartyPaymentClient;

    public PaymentService(ThirdPartyPaymentClient thirdPartyPaymentClient) {
        this.thirdPartyPaymentClient = thirdPartyPaymentClient;
    }

    // CircuitBreaker + TimeLimiter both point at the "thirdPartyPayment" instance
    // configured in application.yml. TimeLimiter requires the method to return a
    // CompletableFuture (or similar async type).
    @CircuitBreaker(name = "thirdPartyPayment", fallbackMethod = "fallbackCharge")
    @TimeLimiter(name = "thirdPartyPayment")
    public CompletableFuture<String> chargeCard(String orderId, double amount) {
        return CompletableFuture.supplyAsync(
                () -> thirdPartyPaymentClient.charge(orderId, amount),
                Executors.newCachedThreadPool()
        );
    }

    // Fallback signature must match the original method's params + a Throwable at the end.
    public CompletableFuture<String> fallbackCharge(String orderId, double amount, Throwable throwable) {
        log.warn("FALLBACK triggered for order {} amount {} - reason: {}",
                orderId, amount, throwable.toString());
        return CompletableFuture.completedFuture(
                "FALLBACK: payment for order " + orderId +
                        " could not be processed right now. It has been queued for retry. (reason: "
                        + throwable.getClass().getSimpleName() + ")"
        );
    }
}
