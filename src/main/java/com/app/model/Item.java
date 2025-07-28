package com.app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Represents a single item within an order.
 * This entity is mapped to the 'items' table.
 */
@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class Item {

    /**
     * The primary key for the Item entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The order to which this item belongs.
     * This is a many-to-one relationship, as an order can have multiple items.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Order order;

    /**
     * The Stock Keeping Unit (SKU) of the product.
     */
    @Column(nullable = false)
    private String sku;

    /**
     * The name of the product.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The price of a single unit of the item.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * The quantity of this item in the order.
     */
    @Column(nullable = false)
    private int quantity;

    /**
     * Constructor to create an Item from an ItemDTO.
     * This is useful for converting request data into a persistable entity.
     * @param itemDTO The DTO containing item details.
     */
    public Item(ItemDTO itemDTO) {
        this.sku = itemDTO.getSku();
        this.name = itemDTO.getName();
        this.price = itemDTO.getPrice();
        this.quantity = itemDTO.getQuantity();
    }
}