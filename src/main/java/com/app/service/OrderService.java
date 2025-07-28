package com.app.service;

import com.app.model.*;
import com.app.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 * OrderService manages the business logic for creating and processing orders.
 * It orchestrates interactions between user management, rewards evaluation, and order persistence.
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserService userService;
    private final RewardsService rewardsService;
    private final OrderRepository orderRepository;

    /**
     * Places a new order. This method orchestrates the entire process:
     * 1. Retrieves the user.
     * 2. Evaluates the cart for discounts via RewardsService.
     * 3. Creates and saves the order with the applied discount.
     * 4. Updates the user's lifetime statistics (total orders, total spent).
     * 5. Confirms the transaction with the Talon.One API to apply loyalty effects.
     *
     * @param req The order request containing the user ID and cart items.
     * @return The persisted Order object with all details.
     */
    @Transactional
    public Order placeOrder(OrderRequest req) {
        // 1. Retrieve the user
        User user = userService.getUser(req.getUserId());

        // 2. Evaluate discounts by calling RewardsService
        // We build a CartRequest containing the necessary data for Talon.One
        CartRequest cartRequest = new CartRequest(
                req.getUserId(),
                req.getItems(),
                new ProfileDTO(user.getTotalOrders(), user.getTotalSpent())
        );
        RewardsResponse rewardsResponse = rewardsService.evaluateCart(cartRequest);

        // Calculate original total and apply discount
        BigDecimal originalTotal = req.getItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal discountAmount = rewardsResponse.getDiscountAmount();
        BigDecimal finalTotal = originalTotal.subtract(discountAmount);

        // 3. Create and save the order
        Order order = new Order();
        order.setUser(user);
        // Assuming Item can be created from the request's item data
        order.setItems(req.getItems().stream().map(Item::new).collect(Collectors.toList()));
        order.setOriginalTotal(originalTotal);
        order.setDiscountAmount(discountAmount);
        order.setFinalTotal(finalTotal);
        Order savedOrder = orderRepository.save(order);

        // 4. Update user statistics
        user.setTotalOrders(user.getTotalOrders() + 1);
        user.setTotalSpent(user.getTotalSpent().add(finalTotal));
        userService.save(user);

        // 5. Confirm loyalty point usage and close the Talon.One session
        rewardsService.confirmLoyalty(req.getUserId(), finalTotal.doubleValue());

        return savedOrder;
    }
}