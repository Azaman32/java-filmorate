package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private int key = 1;
    private final Map<Integer, User> users = new HashMap<>();

    @GetMapping("/users")
    public ArrayList<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @PostMapping
    public User create(@RequestBody User user) {
        users.put(key, user);
        key = key + 1;
        return user;
    }

    @PutMapping
    public User update(@RequestBody User user) {
        for (User user1 : users.values()) {
            if (!user1.equals(user)) {
                users.put(key, user);
                key += 1;
            }

        }
        return user;
    }
}
