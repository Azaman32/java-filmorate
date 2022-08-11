package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.ValidationUsers;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.ValidationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationUserTest {
    ValidationUsers validationUsers = new ValidationUsers();

    @Test()
    public void incorrectEmail() {
        User user = new User(1,"email","login","name", LocalDate.of(2000, 1, 1));
        assertThrows(ValidationException.class,() ->validationUsers.validation(user));
    }

    @Test()
    public void emptyEmail() {
        User user = new User(1," ","login","name", LocalDate.of(2000, 1, 1));
        assertThrows(ValidationException.class,() ->validationUsers.validation(user));
    }

    @Test()
    public void emptyLogin() {
        User user = new User(1,"email@.ru","","name",
                LocalDate.of(2000, 1, 1));
        assertThrows(ValidationException.class,() ->validationUsers.validation(user));
    }

    @Test()
    public void loginCannotContainSpaces() {
        User user = new User(1,"email@.ru"," login ","name",
                LocalDate.of(2000, 1, 1));
        assertThrows(ValidationException.class,() ->validationUsers.validation(user));
    }

    @Test()
    public void incorrectBirthday() {
        User user = new User(1,"email@.ru","login","name",
                LocalDate.of(2100, 1, 1));
        assertThrows(ValidationException.class,() ->validationUsers.validation(user));
    }

    @Test()
    public void emptyName() {
        User user = new User(1,"email@.ru","login","",
                LocalDate.of(2000, 1, 1));
        validationUsers.validation(user);
        User user1 = new User(1,"email@.ru","login","login",
                LocalDate.of(2000, 1, 1));
        assertEquals(user,user1);
    }
}
