package com.app.controller;

import com.app.model.Order;
import com.app.model.OrderRequest;
import com.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * OrderController manages the order placement process.
 * It orchestrates the interaction between the rewards evaluation, order creation,
 * and user statistics update.
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Places a new order.
     * This endpoint accepts an order request, processes it through the OrderService,
     * which handles reward evaluation, order persistence, and user profile updates.
     *
     * @param orderRequest The request body containing order details.
     * @return A ResponseEntity containing the created Order and an HTTP status of 201 Created.
     */
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody @Valid OrderRequest orderRequest) {
        // The orderService.placeOrder method is responsible for the entire process:
        // 1. Evaluating rewards with RewardsService.
        // 2. Saving the order with OrderRepository.
        // 3. Updating user stats with UserService.
        Order createdOrder = orderService.placeOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
}