package com.example.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple in-memory, fixed-window rate limiter GatewayFilterFactory.
 *
 * Referenced in application.yml as "SimpleRateLimiter" (Spring Cloud Gateway maps the
 * "SimpleRateLimiter" name to this class by convention: <Name>GatewayFilterFactory).
 *
 * This avoids requiring a Redis instance just to try out rate limiting, unlike the
 * built-in RequestRateLimiter filter which needs spring-boot-starter-data-redis-reactive.
 * For production use, prefer the built-in RequestRateLimiter + Redis for a solution that
 * works correctly across multiple gateway instances.
 */
@Component
public class SimpleRateLimiterGatewayFilterFactory
        extends AbstractGatewayFilterFactory<SimpleRateLimiterGatewayFilterFactory.Config> {

    // key = client IP + route path, value = window state
    private final ConcurrentHashMap<String, Window> windows = new ConcurrentHashMap<>();

    public SimpleRateLimiterGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String clientIp = exchange.getRequest().getRemoteAddress() != null
                    ? exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
                    : "unknown";
            String key = clientIp + ":" + exchange.getRequest().getPath();

            Window window = windows.computeIfAbsent(key, k -> new Window(Instant.now()));

            synchronized (window) {
                long elapsedSeconds = Instant.now().getEpochSecond() - window.windowStart.getEpochSecond();
                if (elapsedSeconds >= config.getWindowSeconds()) {
                    // Window expired, reset it
                    window.windowStart = Instant.now();
                    window.count.set(0);
                }

                int currentCount = window.count.incrementAndGet();
                if (currentCount > config.getMaxRequestsPerWindow()) {
                    exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                    exchange.getResponse().getHeaders().add("X-RateLimit-Limit",
                            String.valueOf(config.getMaxRequestsPerWindow()));
                    exchange.getResponse().getHeaders().add("X-RateLimit-Remaining", "0");
                    return exchange.getResponse().setComplete();
                }

                exchange.getResponse().getHeaders().add("X-RateLimit-Limit",
                        String.valueOf(config.getMaxRequestsPerWindow()));
                exchange.getResponse().getHeaders().add("X-RateLimit-Remaining",
                        String.valueOf(Math.max(0, config.getMaxRequestsPerWindow() - currentCount)));
            }

            return chain.filter(exchange);
        };
    }

    @Override
    public String[] shortcutFieldOrder() {
        return new String[]{"maxRequestsPerWindow", "windowSeconds"};
    }

    private static class Window {
        volatile Instant windowStart;
        final AtomicInteger count = new AtomicInteger(0);

        Window(Instant windowStart) {
            this.windowStart = windowStart;
        }
    }

    public static class Config {
        private int maxRequestsPerWindow = 10;
        private int windowSeconds = 60;

        public int getMaxRequestsPerWindow() { return maxRequestsPerWindow; }
        public void setMaxRequestsPerWindow(int maxRequestsPerWindow) { this.maxRequestsPerWindow = maxRequestsPerWindow; }
        public int getWindowSeconds() { return windowSeconds; }
        public void setWindowSeconds(int windowSeconds) { this.windowSeconds = windowSeconds; }
    }
}
