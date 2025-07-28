package com.app.repository;

import com.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for {@link User} entities.
 * <p>
 * Extends JpaRepository for standard CRUD operations. The primary key for the User entity is of type Long.
 * It provides a custom query method to find users by their business-facing integration ID, which is essential
 * for the application's service layer to function correctly.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their unique integration ID.
     * This ID is the business key used for interactions with external systems like Talon.One
     * and for API requests, distinguishing it from the internal database primary key.
     *
     * @param integrationId The unique integration identifier of the user.
     * @return an {@link Optional} containing the found user or {@link Optional#empty()} if no user is found.
     */
    Optional<User> findByIntegrationId(String integrationId);
}