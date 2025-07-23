package com.talon.integrationapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String integrationId;

    @NotBlank
    private String state;

    private String profileId;

    private LocalDateTime updated;

    @ElementCollection
    private List<String> couponCodes;

    private String referralCode;

    private Double total;

    // Add more fields as per OpenAPI spec (cartItems, attributes, etc.)
}
