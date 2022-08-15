package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.ValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    UserValidator validationUsers = new UserValidator();
    private UserIdGenerator userIdGenerator = new UserIdGenerator();
    private final Map<Integer, User> users = new HashMap<>();

    @GetMapping("/users")
    public List<User> findAll() {
        log.info("Выполнен запрос Get");
        return new ArrayList<>(users.values());
    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        validationUsers.validation(user);
        user.setId(userIdGenerator.generate());
        users.put(user.getId(), user);
        log.info("Выполнен запрос Post");
        return user;
    }

    @PutMapping("/users")
    public User update(@RequestBody User user) {
        if (user.getId() <= 0) {
            throw new ValidationException("Invalid id");
        }
        validationUsers.validation(user);
        for (User user1 : users.values()) {
            if (user1.getId() != user.getId()) {
                user.setId(userIdGenerator.generate());
                users.put(user.getId(), user);
            }
            if (user1.getId() == user.getId()) {
                user1.setName(user.getName());
                user1.setEmail(user.getEmail());
                user1.setBirthday(user.getBirthday());
                user1.setLogin(user.getLogin());
                return user1;
            }
        }
        log.info("Выполнен запрос Put");
        return user;
    }
}
