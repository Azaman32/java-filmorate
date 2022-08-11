package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.ValidationException;

import java.time.LocalDate;

public class ValidationUsers {
    private LocalDate todayDate = LocalDate.now();
    private static final Logger log = LoggerFactory.getLogger(ValidationUsers.class);

    public void validation(User user) {
        if(user.getEmail()==null || user.getEmail().trim().equals("") || !user.getEmail().contains("@")) {
            log.info("Ошибка при вводе Email");
            throw new ValidationException();
        }else if(user.getLogin()==null || user.getLogin().equals("") || user.getLogin().contains(" ")) {
            log.info("Ошибка при вводе Login");
            throw new ValidationException();
        }else if(todayDate.isBefore(user.getBirthday())) {
            log.info("Ошибка при вводе Birthday");
            throw new ValidationException();
        }
        if (user.getName() == null || user.getName().equals("")) {
        user.setName(user.getLogin());
        }
    }
}
