package com.app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user in the e-commerce application.
 * This entity is mapped to the 'users' table in the database.
 */
@Entity
@Table(name = "users")
@Data
public class User {

    /**
     * The primary key for the User entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * A unique identifier for integrating with external systems like Talon.One.
     * This field should be indexed for efficient lookups.
     */
    @Column(unique = true, nullable = false)
    private String integrationId;

    /**
     * The total number of orders placed by the user.
     * Used for loyalty calculations in Talon.One.
     */
    @Column(nullable = false)
    private int totalOrders = 0;

    /**
     * The total amount of money spent by the user across all orders.
     * Used for loyalty calculations in Talon.One.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalSpent = BigDecimal.ZERO;

    /**
     * A set of orders placed by this user.
     * The relationship is mapped by the 'user' field in the Order entity.
     * CascadeType.ALL ensures that orders are managed along with the user.
     * FetchType.LAZY is used for performance optimization.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Order> orders = new HashSet<>();
}