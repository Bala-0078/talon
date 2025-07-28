package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * DTO representing customer profile attributes sent to Talon.One.
 * This keeps Talon.One's view of the customer in sync with the application's database.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

    @Min(value = 0)
    private int totalOrders;

    @Min(value = 0)
    private BigDecimal totalSpent;
}