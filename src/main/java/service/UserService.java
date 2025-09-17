package service;

import dto.CreateUserDto;
import dto.UpdateUserDto;
import entity.User;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {

        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.password(),
                Instant.now(),
                null,
                null);
        var userSaved = userRepository.save(entity);

        return userSaved.getId();
    }

    public Optional<User> getUserById (String userId){

        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public void updateUserById(String userId, UpdateUserDto updateUserDto){

        var id = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

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
        }
    }

    public void deletedById(String userId) {
        var id = UUID.fromString(userId);

        var userExist = userRepository.existsById(id);

        if (userExist) {
            userRepository.deleteById(id);

        }
    }
}