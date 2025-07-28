package com.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Configures the primary RestTemplate bean for the application, specifically tailored for
 * communication with the Talon.One API. This configuration ensures that all outgoing
 * requests made through the autowired RestTemplate include the necessary authentication
 * headers and provides concise logging for traceability.
 */
@Configuration
public class RestTemplateConfig {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateConfig.class);

    /**
     * Injects the Talon.One API key from application properties (e.g., application.yml)
     * for secure handling of credentials, avoiding hardcoded values.
     */
    @Value("${talonone.api-key}")
    private String talonOneApiKey;

    /**
     * Creates and configures the primary singleton, thread-safe RestTemplate instance.
     * <p>
     * By marking this bean with {@code @Primary}, it becomes the default candidate for
     * autowiring wherever a {@code RestTemplate} is required, including the
     * {@code TalonOneClient}. This avoids the need to modify client classes with {@code @Qualifier}.
     * The RestTemplate is equipped with an interceptor that automatically handles
     * authentication and logging for all Talon.One API calls.
     *
     * @return A configured {@link RestTemplate} instance ready for use with Talon.One.
     */
    @Bean
    @Primary
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(talonOneInterceptor()));
        return restTemplate;
    }

    /**
     * Defines the ClientHttpRequestInterceptor for Talon.One API calls.
     * <p>
     * This interceptor is responsible for two main tasks:
     * 1. Logging essential request details (method and URI) for monitoring and debugging.
     * 2. Injecting the correct API key into the request headers for authentication. It uses
     *    the 'ApiKey-v1' scheme as specified by Talon.One's documentation.
     *
     * @return A {@link ClientHttpRequestInterceptor} instance.
     */
    private ClientHttpRequestInterceptor talonOneInterceptor() {
        return (request, body, execution) -> {
            // Log essential, non-sensitive request details for monitoring.
            log.info("Sending request to Talon.One API: {} {}", request.getMethod(), request.getURI());

            // Set the correct Authorization header. Per Talon.One docs, this should be 'ApiKey-v1'.
            // Using .set() overwrites any 'Authorization' header that might have been
            // added in the client code, ensuring this configuration is the single source of truth.
            request.getHeaders().set("Authorization", "ApiKey-v1 " + talonOneApiKey);

            // Proceed with the request execution.
            return execution.execute(request, body);
        };
    }
}