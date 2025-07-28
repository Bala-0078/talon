package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a single effect (e.g., a discount, a coupon, or a loyalty point change)
 * returned by the Talon.One API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Effect {

    private String effectType;
    private String name;
    private Object props; // A generic object to hold properties of the effect
}