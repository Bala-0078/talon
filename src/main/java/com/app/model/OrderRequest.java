package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO representing a request to place a new order.
 * Contains the user's ID and the list of items in their cart.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    /**
     * The integration ID of the user placing the order.
     */
    @NotNull(message = "User ID cannot be null")
    private String userId;

    /**
     * The list of items to be included in the order.
     */
    @NotEmpty(message = "Items list cannot be empty")
    private List<ItemDTO> items;
}