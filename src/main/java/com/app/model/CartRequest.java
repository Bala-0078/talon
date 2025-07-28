package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO for sending a cart evaluation request to the RewardsService.
 * This object encapsulates all data needed by Talon.One to calculate discounts.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

    @NotNull(message = "User ID cannot be null")
    private String userId;

    @NotEmpty(message = "Items list cannot be empty")
    @Valid
    private List<ItemDTO> items;

    @NotNull(message = "Profile DTO cannot be null")
    @Valid
    private ProfileDTO profile;
}