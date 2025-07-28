package com.app.service;

import com.app.model.CartRequest;
import com.app.model.RewardsResponse;
import com.app.model.SessionDTO;
import com.app.talonone.TalonOneClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * RewardsService acts as a bridge to the Talon.One API.
 * It encapsulates the logic for updating customer profiles and evaluating sessions for rewards.
 */
@Service
@RequiredArgsConstructor
public class RewardsService {

    private final TalonOneClient talonOneClient;

    /**
     * Evaluates a customer's cart for applicable rewards and discounts from Talon.One.
     * It first updates the customer's profile with the latest data and then evaluates their session.
     *
     * @param req The cart request containing user ID, items, and current profile attributes.
     * @return A RewardsResponse containing the effects from Talon.One (e.g., discounts).
     */
    public RewardsResponse evaluateCart(CartRequest req) {
        // 1. Update the customer profile in Talon.One with the latest stats
        talonOneClient.updateProfile(req.getUserId(), req.getProfile());

        // 2. Evaluate the customer session with the current cart items
        SessionDTO session = new SessionDTO(req.getItems());
        return talonOneClient.evaluateSession(req.getUserId(), session);
    }

    /**
     * Confirms a transaction with Talon.One after an order is successfully placed.
     * This finalizes the session, applying any pending effects like earning or redeeming loyalty points.
     *
     * @param userId The integration ID of the user.
     * @param total  The final total of the order.
     */
    public void confirmLoyalty(String userId, double total) {
        // This call informs Talon.One that the session should be closed and the transaction is complete.
        talonOneClient.confirmLoyalty(userId, total);
    }
}