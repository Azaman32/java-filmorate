package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.ValidationException;

import java.time.LocalDate;

public class ValidationFilms {
    private static final Logger log = LoggerFactory.getLogger(ValidationFilms.class);
    private final LocalDate RELEASE_DATE = LocalDate.of(1895,12,28);

    public void validation(Film film) {
        if(film.getName()==null || film.getName().trim().equals("")) {
            log.info("Ошибка при вводе Name");
            throw new ValidationException();
        } else if (film.getDescription().length() > 200) {
            log.info("Ошибка при вводе Description");
            throw new ValidationException();
        }else if(RELEASE_DATE.isAfter(film.getReleaseDate())) {
            log.info("Ошибка при вводе ReleaseDate");
            throw new ValidationException();
        } else if (film.getDuration()<= 0) {
            log.info("Ошибка при вводе Duration");
            throw new ValidationException();
        }
    }
}
