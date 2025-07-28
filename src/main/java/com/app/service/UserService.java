package com.app.service;

import com.app.model.User;
import com.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * UserService handles business logic related to user management.
 * It interacts with the UserRepository to fetch and update user data.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Fetches a user by their unique integration ID.
     * This ID is used to identify the user in external systems like Talon.One.
     *
     * @param id The integration ID of the user to fetch.
     * @return The User object if found.
     * @throws EntityNotFoundException if no user is found with the given ID.
     */
    public User getUser(String id) {
        // Assuming the repository has a method to find a user by their integration ID.
        return userRepository.findByIntegrationId(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    /**
     * Saves or updates a user's entity. This method is used to persist changes,
     * such as updating total orders and total spent after a new order is placed.
     *
     * @param user The User object with updated data to save.
     */
    public void save(User user) {
        userRepository.save(user);
    }
}