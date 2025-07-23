package com.talonone.integrationapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class CustomerSessionRequest {
    @NotBlank
    private String profileId;
    @NotBlank
    private String state;
    @NotNull
    private List<CartItem> cartItems;
    private Map<String, Object> attributes;

    public String getProfileId() { return profileId; }
    public void setProfileId(String profileId) { this.profileId = profileId; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public List<CartItem> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItem> cartItems) { this.cartItems = cartItems; }
    public Map<String, Object> getAttributes() { return attributes; }
    public void setAttributes(Map<String, Object> attributes) { this.attributes = attributes; }
}
