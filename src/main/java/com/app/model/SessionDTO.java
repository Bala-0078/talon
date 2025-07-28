package com.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO representing the session payload for Talon.One's evaluation endpoint.
 * It primarily contains the list of items in the customer's current cart.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO {

    /**
     * The list of items in the cart, which Talon.One will evaluate.
     * The JSON property is named to match Talon.One's expected field name.
     */
    @JsonProperty("cartItems")
    private List<ItemDTO> cartItems;
}