package com.app.talonone;

import com.app.model.ProfileDTO;
import com.app.model.RewardsResponse;
import com.app.model.SessionDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * TalonOneClient acts as a reusable and centralized client for interacting with Talon.One's Integration API.
 * It handles HTTP communication, authentication, and provides methods for managing customer profiles,
 * evaluating sessions for rewards, and confirming loyalty points.
 * This client is designed to be a thread-safe Spring component.
 */
@Component
public class TalonOneClient {

    @Value("${talonone.base-url}")
    private String baseUrl;

    @Value("${talonone.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    /**
     * Constructs a new TalonOneClient with a RestTemplate for HTTP communication.
     *
     * @param restTemplate The RestTemplate instance to be used for making API calls.
     */
    public TalonOneClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Updates a customer profile in Talon.One with the provided data.
     * This is typically used to keep customer statistics, like total spending, up-to-date.
     *
     * @param userId The unique integration ID of the customer profile to update.
     * @param dto    A {@link ProfileDTO} containing the attributes to update.
     */
    public void updateProfile(String userId, ProfileDTO dto) {
        String url = String.format("%s/v1/profiles/%s", baseUrl, userId);
        HttpEntity<ProfileDTO> requestEntity = new HttpEntity<>(dto, createHeaders());
        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class);
    }

    /**
     * Evaluates a customer session to determine applicable rewards and discounts.
     * The session data, including cart items and customer ID, should be contained within the DTO.
     *
     * @param userId The unique integration ID of the customer.
     * @param dto A {@link SessionDTO} containing the session details to be evaluated.
     * @return A {@link RewardsResponse} object containing the effects and discounts calculated by Talon.One.
     */
    public RewardsResponse evaluateSession(String userId, SessionDTO dto) {
        String url = String.format("%s/v1/integration/customers/%s", baseUrl, userId);
        HttpEntity<SessionDTO> requestEntity = new HttpEntity<>(dto, createHeaders());
        return restTemplate.postForObject(url, requestEntity, RewardsResponse.class);
    }

    /**
     * Confirms a loyalty transaction for a given user after an order is completed.
     * This finalizes the session in Talon.One, applying any pending loyalty effects like earning or redeeming points.
     *
     * @param userId      The unique integration ID of the customer.
     * @param totalAmount The final total amount of the transaction to be confirmed.
     */
    public void confirmLoyalty(String userId, double totalAmount) {
        String url = String.format("%s/v1/loyalty/%s/confirm", baseUrl, userId);
        HttpEntity<Double> requestEntity = new HttpEntity<>(totalAmount, createHeaders());
        restTemplate.postForObject(url, requestEntity, Void.class);
    }

    /**
     * Creates the HttpHeaders required for authenticating with the Talon.One API.
     *
     * @return An {@link HttpHeaders} object with the 'Authorization' and 'Content-Type' headers set.
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "ApiKey-v1 " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}