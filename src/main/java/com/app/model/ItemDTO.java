package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO for representing an item in API requests (e.g., OrderRequest, CartRequest).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    @NotBlank(message = "SKU cannot be blank")
    private String sku;

    @NotBlank(message = "Item name cannot be blank")
    private String name;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be non-negative")
    private BigDecimal price;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
}