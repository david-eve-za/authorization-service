package com.glez.authorization_service.services;

import com.glez.authorization_service.entities.UserEntity;
import com.glez.authorization_service.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements IUsersService {
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<UserEntity> getUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<UserEntity> createUser(UserEntity user) {
        UserEntity newUser = userRepository.save(user);
        return ResponseEntity.ok(newUser);
    }

    @Override
    public ResponseEntity<UserEntity> updateUser(UserEntity user) {
        UserEntity updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @Override
    public ResponseEntity<UserEntity> deleteUser(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
}
