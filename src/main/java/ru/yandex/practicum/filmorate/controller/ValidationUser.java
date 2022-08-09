package ru.yandex.practicum.filmorate.controller;

import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.ValidationException;

import java.time.LocalDate;

public class ValidationUser {
    private LocalDate todayDate = LocalDate.now();

    public void validation(User user) {
        if(user.getEmail()==null || user.getEmail().trim().equals("") || !user.getEmail().contains("@")) {
            throw new ValidationException();
        }else if(user.getLogin()==null || user.getLogin().equals("") || user.getLogin().contains(" ")) {
            throw new ValidationException();
        } else if (user.getName() == null || user.getName().equals("")) {
            user.setName(user.getLogin());
        }else if(todayDate.isBefore(user.getBirthday())) {
            throw new ValidationException();
        }
    }
}
