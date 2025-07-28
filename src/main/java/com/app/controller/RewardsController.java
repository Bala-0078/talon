package com.app.controller;

import com.app.model.CartRequest;
import com.app.model.RewardsResponse;
import com.app.service.RewardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * RewardsController handles requests related to evaluating customer rewards.
 * It integrates with the RewardsService, which communicates with the Talon.One API.
 */
@RestController
@RequestMapping("/rewards")
@RequiredArgsConstructor
public class RewardsController {

    private final RewardsService rewardsService;

    /**
     * Evaluates the rewards for a given shopping cart.
     * This endpoint is typically called before placing an order to show the user
     * applicable discounts and promotions.
     *
     * @param cartRequest The request body containing the cart details and user profile.
     * @return A ResponseEntity containing the RewardsResponse from Talon.One.
     */
    @PostMapping("/evaluate")
    public ResponseEntity<RewardsResponse> evaluateRewards(@RequestBody @Valid CartRequest cartRequest) {
        RewardsResponse rewardsResponse = rewardsService.evaluateRewards(cartRequest);
        return ResponseEntity.ok(rewardsResponse);
    }
}