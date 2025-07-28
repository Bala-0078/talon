package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO representing the response from Talon.One after evaluating a session.
 * It contains the total discount and a list of all effects triggered by the rules.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore fields from Talon.One we don't need
public class RewardsResponse {

    private BigDecimal totalDiscount;
    private List<Effect> effects;

    /**
     * Convenience method to get the discount amount, defaulting to zero if null.
     * @return The total discount amount.
     */
    public BigDecimal getDiscountAmount() {
        return totalDiscount == null ? BigDecimal.ZERO : totalDiscount;
    }
}