package com.example.testeditions.Controllers;


import com.example.testeditions.DTO.LoginDTO;
import com.example.testeditions.DTO.UserDTO;
import com.example.testeditions.Entites.Preferences;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Payload.Response.LoginMesage;
import com.example.testeditions.Repositories.UserRepository;
import com.example.testeditions.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }




    @PostMapping("add-preferences/{userId}")
    public User addPreferencesToUser(@PathVariable Long userId, @RequestBody List<String> selectedTypes) {
        return userService.addPreferencesToUser(userId, selectedTypes);

    }


    @PostMapping(path = "/save")
    public User saveEmployee(@RequestBody UserDTO userDTO) {
        // Save the user
        User savedUser = userService.save(userDTO);

        // Send verification code to the user's email

        // Return the saved user
        return savedUser;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUserrr(@RequestBody LoginDTO loginDTO) {
        UserDTO userDetailsDTO = userService.loginUser(loginDTO);
        if (userDetailsDTO != null) {
            return ResponseEntity.ok(userDetailsDTO);
        } else {
            // Check if the user is banned
            User user = userRepository.findByEmail(loginDTO.getEmail());
            if (user != null && user.isBanned()) {
                // Return 403 Forbidden status if the user is banned
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is banned");
            } else {
                // Return Unauthorized status for other login failures
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
            }
        }
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User userDTO) {
        User updatedUser = userService.update(userId, userDTO);

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/listUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> findConnectedUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/ban")
    public ResponseEntity<?> banUserByEmail(@RequestParam("email") String email) {
        // Ban the user using the service method
        userService.banUserByEmail(email);

        // Return a success response
        return ResponseEntity.ok("User with email " + email + " has been banned.");
    }

    @GetMapping("/banned/count")
    public long countBannedUsers() {
        return userService.countBannedUsers();
    }



    @GetMapping("/all")
    public long getAllUsers() {
        return userRepository.count();
    }



}
