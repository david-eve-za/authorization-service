package com.glez.authorization_service.services;

import com.glez.authorization_service.entities.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUsersService {
    ResponseEntity<List<UserEntity>> getAllUsers();
    ResponseEntity<UserEntity> getUserByUsername(String username);
    ResponseEntity<UserEntity> createUser(UserEntity user);
    ResponseEntity<UserEntity> updateUser(UserEntity user);
    ResponseEntity<UserEntity> deleteUser(String username);

}
