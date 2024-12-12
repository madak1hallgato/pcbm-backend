package org.ppke.itk.pcbm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ppke.itk.pcbm.domain.User;
import org.ppke.itk.pcbm.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(name = "User")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @Operation(summary = "Retrieve all user", description = "Fetches a list of all users in the system.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of users.")
    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = this.userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Retrieve a specific user", description = "Fetches the details of a user by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the user."),
            @ApiResponse(responseCode = "404", description = "User not found."),
    })
    @GetMapping("/id/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable Integer userId) {
        Optional<User> user = Optional.ofNullable(this.userRepository.findByUserId(userId));
        return user
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
