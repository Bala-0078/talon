package com.app.repository;

import com.app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Order} entities.
 * <p>
 * Extends JpaRepository for standard CRUD operations. The primary key for the Order entity is of type Long.
 * This provides all necessary methods for creating, reading, updating, and deleting orders without
 * requiring additional method definitions for the application's current scope.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // The default methods provided by JpaRepository (e.g., save, findById, findAll, delete)
    // are sufficient for the initial requirements of managing orders.
    // Custom query methods, such as finding orders by user or status, can be added here as needed in the future.
}