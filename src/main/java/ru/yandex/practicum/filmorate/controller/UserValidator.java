package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.ValidationException;

import java.time.LocalDate;

@Slf4j
public class UserValidator {
    private LocalDate todayDate;

    public void validation(User user) {
        todayDate = LocalDate.now();
        if (user.getEmail() == null || user.getEmail().trim().equals("") || !user.getEmail().contains("@")) {
            log.error("Invalid email: {}", user.getEmail());
            throw new ValidationException("Invalid email: {}");
        }
        if (user.getLogin() == null || user.getLogin().equals("") || user.getLogin().contains(" ")) {
            log.error("Invalid login: {}", user.getLogin());
            throw new ValidationException("Invalid login");
        }
        if (todayDate.isBefore(user.getBirthday())) {
            log.error("Invalid birthday: {}", user.getBirthday());
            throw new ValidationException("Invalid birthday");
        }
        if (user.getName() == null || user.getName().equals("")) {
            user.setName(user.getLogin());
        }
    }
}
