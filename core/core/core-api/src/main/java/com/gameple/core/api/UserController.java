package com.gameple.core.api;

import com.gameple.core.domain.UserService;
import com.gameple.core.entity.User;
import com.gameple.core.enums.UserType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestParam String username,
                           @RequestParam UserType userType) {
        return userService.createUser(username, userType);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
