package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.ValidationFilms;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.ValidationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

public class ValidationFilmsTest {
    ValidationFilms validationFilms = new ValidationFilms();


    @Test()
    public void emptyName() {
        Film film = new Film(1, "", "description", LocalDate.of(2000, 1, 1), 100);
        assertThrows(ValidationException.class,() ->validationFilms.validation(film));
   }

   @Test
    public void nameIsBlankSpace() {
       Film film = new Film(1, "  ", "description", LocalDate.of(2000, 1, 1), 100);
       assertThrows(ValidationException.class,() ->validationFilms.validation(film));
   }
    @Test
    public void maxDescription() {
        Film film = new Film(1, "name", "descript                                                                                                                                                                                                                    " +
                "", LocalDate.of(2000, 1, 1), 20);
        assertThrows(ValidationException.class,() ->validationFilms.validation(film));
    }

    @Test
    public void incorrectReleaseDate() {
        Film film = new Film(1, "name", "description", LocalDate.of(1895, 12, 26), 20);
        assertThrows(ValidationException.class,() ->validationFilms.validation(film));
    }

    @Test
    public void positiveDuration() {
        Film film = new Film(1, "name", "description", LocalDate.of(1895, 12, 26), -200);
        assertThrows(ValidationException.class,() ->validationFilms.validation(film));
    }
}
