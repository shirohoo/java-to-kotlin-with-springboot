package io.github.shirohoo.library.application.controller.user;

import io.github.shirohoo.library.application.dto.user.UserCreateRequest;
import io.github.shirohoo.library.application.dto.user.UserResponse;
import io.github.shirohoo.library.application.dto.user.UserUpdateRequest;
import io.github.shirohoo.library.application.service.UserService;
import io.github.shirohoo.library.domain.user.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers()
                .stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
                .toList();
    }

    @PostMapping
    public void saveUser(@RequestBody UserCreateRequest request) {
        String username = request.getName();
        Integer age = request.getAge();
        userService.saveUser(new User(username, age, new ArrayList<>(), null));
    }

    @PutMapping
    public void updateUserName(@RequestBody UserUpdateRequest request) {
        long userId = request.getId();
        String username = request.getName();
        userService.updateUsername(userId, username);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }
}
