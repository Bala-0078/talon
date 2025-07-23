package com.example.talonintegrationapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer_sessions")
public class CustomerSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String sessionId;
    private String profileId;
    private String state;
    private LocalDateTime updated;
    private Double total;
    @ElementCollection
    private List<String> couponCodes;
    private String referralCode;
    @Lob
    private String cartItemsJson;
    @Lob
    private String attributesJson;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public String getProfileId() { return profileId; }
    public void setProfileId(String profileId) { this.profileId = profileId; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public LocalDateTime getUpdated() { return updated; }
    public void setUpdated(LocalDateTime updated) { this.updated = updated; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public List<String> getCouponCodes() { return couponCodes; }
    public void setCouponCodes(List<String> couponCodes) { this.couponCodes = couponCodes; }
    public String getReferralCode() { return referralCode; }
    public void setReferralCode(String referralCode) { this.referralCode = referralCode; }
    public String getCartItemsJson() { return cartItemsJson; }
    public void setCartItemsJson(String cartItemsJson) { this.cartItemsJson = cartItemsJson; }
    public String getAttributesJson() { return attributesJson; }
    public void setAttributesJson(String attributesJson) { this.attributesJson = attributesJson; }
}
