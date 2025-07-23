package com.talon.integration.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class TalonIntegrationService {
    @Value("${talon.api.base-url}")
    private String baseUrl;
    @Value("${talon.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public ResponseEntity<String> forwardPut(String path, String body) {
        HttpEntity<String> entity = new HttpEntity<>(body, buildHeaders());
        try {
            return restTemplate.exchange(baseUrl + path, HttpMethod.PUT, entity, String.class);
        } catch (HttpClientErrorException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
        }
    }

    public ResponseEntity<String> forwardPost(String path, String body) {
        HttpEntity<String> entity = new HttpEntity<>(body, buildHeaders());
        try {
            return restTemplate.exchange(baseUrl + path, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
        }
    }

    public ResponseEntity<String> forwardGet(String path) {
        HttpEntity<Void> entity = new HttpEntity<>(buildHeaders());
        try {
            return restTemplate.exchange(baseUrl + path, HttpMethod.GET, entity, String.class);
        } catch (HttpClientErrorException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
        }
    }
}
