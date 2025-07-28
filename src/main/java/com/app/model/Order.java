package com.app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a customer order.
 * This entity is mapped to the 'orders' table.
 */
@Entity
@Table(name = "orders")
@Data
public class Order {

    /**
     * The primary key for the Order entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who placed the order.
     * This is a many-to-one relationship, as a user can have multiple orders.
     * It is non-nullable because an order must belong to a user.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    /**
     * The set of items included in this order.
     * The relationship is managed via the 'order' field in the Item entity.
     * CascadeType.ALL ensures items are persisted with the order.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Item> items = new HashSet<>();

    /**
     * The total cost of the order before any discounts are applied.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal originalTotal;

    /**
     * The total discount amount applied to the order, calculated by Talon.One.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal discountAmount;

    /**
     * The final cost of the order after discounts have been applied.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal finalTotal;

    /**
     * The timestamp when the order was created.
     * Automatically populated by Hibernate.
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
}