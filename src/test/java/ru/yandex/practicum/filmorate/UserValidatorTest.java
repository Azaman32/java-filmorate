package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.UserValidator;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserValidatorTest {
    private final UserValidator validationUsers = new UserValidator();

    @Test()
    public void incorrectEmail() {
        User user = new User(1, "email", "login", "name", LocalDate.of(2000, 1, 1), new HashSet<>());
        assertThrows(ValidationException.class, () -> validationUsers.validation(user));
    }

    @Test()
    public void emptyEmail() {
        User user = new User(1, " ", "login", "name", LocalDate.of(2000, 1, 1), new HashSet<>());
        assertThrows(ValidationException.class, () -> validationUsers.validation(user));
    }

    @Test()
    public void emptyLogin() {
        User user = new User(1, "email@.ru", "", "name",
                LocalDate.of(2000, 1, 1), new HashSet<>());
        assertThrows(ValidationException.class, () -> validationUsers.validation(user));
    }

    @Test()
    public void loginCannotContainSpaces() {
        User user = new User(1, "email@.ru", " login ", "name",
                LocalDate.of(2000, 1, 1), new HashSet<>());
        assertThrows(ValidationException.class, () -> validationUsers.validation(user));
    }

    @Test()
    public void incorrectBirthday() {
        User user = new User(1, "email@.ru", "login", "name",
                LocalDate.of(2446, 8, 20), new HashSet<>());
        assertThrows(ValidationException.class, () -> validationUsers.validation(user));
    }

    @Test()
    public void emptyName() {
        User user = new User(1, "email@.ru", "login", "",
                LocalDate.of(2000, 1, 1), new HashSet<>());
        validationUsers.validation(user);
        User user1 = new User(1, "email@.ru", "login", "login",
                LocalDate.of(2000, 1, 1), new HashSet<>());
        assertEquals(user, user1);
    }
}
