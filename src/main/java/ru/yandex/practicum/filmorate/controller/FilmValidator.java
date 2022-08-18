package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.ValidationException;

import java.time.LocalDate;

@Slf4j
public class FilmValidator {
    static final LocalDate RELEASE_DATE = LocalDate.of(1895, 12, 28);

    public void validation(Film film) {
        if (film.getName() == null || film.getName().trim().equals("")) {
            log.error("Invalid user: {}", film.getName());
            throw new ValidationException("Invalid user");
        }
        if (film.getDescription().length() > 200) {
            log.error("Invalid description: {}", film.getDescription());
            throw new ValidationException("Invalid description");
        }
        if (RELEASE_DATE.isAfter(film.getReleaseDate())) {
            log.error("Invalid releaseDate: {}", film.getReleaseDate());
            throw new ValidationException("Invalid releaseDate");
        }
        if (film.getDuration() <= 0) {
            log.error("Invalid duration: {}", film.getDuration());
            throw new ValidationException("Invalid duration");
        }
    }
}
