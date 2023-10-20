package com.glez.authorization_service.controllers;

import com.glez.authorization_service.entities.UserEntity;
import com.glez.authorization_service.services.IUsersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountsRestAPI {
    private final IUsersService usersService;

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserEntity> getUserByUsername(@PathVariable String username) {
        return usersService.getUserByUsername(username);
    }

    @PostMapping("/")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        return usersService.createUser(user);
    }

    @PutMapping("/")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user) {
        return usersService.updateUser(user);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable String username) {
        return usersService.deleteUser(username);
    }
}
