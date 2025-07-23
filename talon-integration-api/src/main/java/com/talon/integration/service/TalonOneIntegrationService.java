package com.talon.integration.service;

import com.talon.integration.config.TalonOneProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TalonOneIntegrationService {
    @Autowired
    private TalonOneProperties talonOneProperties;
    @Autowired
    private RestTemplate talonRestTemplate;

    /**
     * Custom function to sync a customer session with Talon.One
     * @param customerSessionId The session integration ID
     * @param sessionPayload The session payload (request body)
     * @param dryRun Whether to persist changes
     * @return Talon.One API response
     */
    public ResponseEntity<String> syncCustomerSession(String customerSessionId, Map<String, Object> sessionPayload, boolean dryRun) {
        String url = talonOneProperties.getBaseUrl() + "/v2/customer_sessions/" + customerSessionId + "?dry=" + dryRun;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(sessionPayload, headers);
        try {
            return talonRestTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    // Additional methods for other endpoints can be added here
}
