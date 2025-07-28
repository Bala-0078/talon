package com.app.controller;

import com.app.model.ProfileDTO;
import com.app.model.User;
import com.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * UserController handles HTTP requests related to user management.
 * It provides endpoints to fetch user details and update user statistics.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Fetches a user by their unique ID.
     *
     * @param id The ID of the user to fetch.
     * @return A ResponseEntity containing the User object if found, or 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // Assuming userService.findById(id) returns an Optional<User>
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Updates a user's profile statistics, such as total orders and total spent.
     * The request body should contain the updated values.
     *
     * @param id         The ID of the user to update.
     * @param profileDTO The DTO containing the updated user statistics.
     * @return A ResponseEntity with no content (204 No Content) on successful update.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserProfile(@PathVariable Long id, @RequestBody @Valid ProfileDTO profileDTO) {
        userService.updateUserProfile(id, profileDTO);
        return ResponseEntity.noContent().build();
    }
}