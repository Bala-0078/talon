package com.talonone.integrationapi.model;

import java.util.List;
import java.util.Map;

public class CustomerSessionResponse {
    private String customerSessionId;
    private String profileId;
    private String state;
    private List<CartItem> cartItems;
    private Map<String, Object> attributes;

    public String getCustomerSessionId() { return customerSessionId; }
    public void setCustomerSessionId(String customerSessionId) { this.customerSessionId = customerSessionId; }
    public String getProfileId() { return profileId; }
    public void setProfileId(String profileId) { this.profileId = profileId; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public List<CartItem> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItem> cartItems) { this.cartItems = cartItems; }
    public Map<String, Object> getAttributes() { return attributes; }
    public void setAttributes(Map<String, Object> attributes) { this.attributes = attributes; }
}
