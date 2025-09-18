package com.zchat.service;

import com.zchat.dto.CreateUserDto;
import com.zchat.dto.UpdateUserDto;
import com.zchat.entity.User;
import org.springframework.stereotype.Service;
import com.zchat.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserDto createUserDto) {

        var entity = new User(
                null,
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null,
                null);

        return userRepository.save(entity);
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(UUID userId, UpdateUserDto updateUserDto) {

        Optional<User> userEntityOptional = userRepository.findById(userId);

        userEntityOptional.ifPresent(user -> {
            if (updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }
            if (updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }
            if (updateUserDto.profilePictureUrl() != null) {
                user.setProfilePictureUrl(updateUserDto.profilePictureUrl());
            }
            userRepository.save(user);
        });
    }

    public void deleteById(UUID userId) {
        userRepository.deleteById(userId);
    }
}