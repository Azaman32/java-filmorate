package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        log.info("Выполнен запрос Get");
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }


    @GetMapping("/users/{id}/friends")
    public List<User> listFriends(@PathVariable int id) {
        return userService.getFriends(id);
    }

    @GetMapping("/users/{id}/friends/common/{otherId}")
    public List<User> listCommonFriends(@PathVariable("id") int id, @PathVariable("otherId") int otherId) {
        return userService.commonFriends(id, otherId);
    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        userValidator.validation(user);
        userService.saveUser(user);
        log.info("Выполнен запрос Post");
        return user;
    }

    @PutMapping("/users")
    public User update(@RequestBody User user) {
        userValidator.validation(user);
        userService.updateUser(user);
        log.info("Выполнен запрос Put");
        return user;
    }

    @PutMapping("/users/{id}/friends/{friendId}")
    public void addFriend(@PathVariable("id") int id, @PathVariable("friendId") int friendId) {
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/users/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable("id") int currentUserId, @PathVariable("friendId") int friendId) {
        userService.deleteFriend(currentUserId, friendId);
    }
}
