package com.gameple.core.domain;

import com.gameple.core.entity.User;
import com.gameple.core.enums.UserType;
import org.springframework.stereotype.Service;
import com.gameple.core.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, UserType userType) {
        User user = new User(username, userType);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
