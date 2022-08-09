package ru.yandex.practicum.filmorate.controller;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.ValidationException;

import java.time.LocalDate;

public class ValidationFilms {
    private final LocalDate RELEASE_DATE = LocalDate.of(1895,12,28);

    public void validation(Film film) {
        if(film.getName()==null || film.getName().trim().equals("")) {
            throw new ValidationException();
        } else if (film.getDescription().length() > 200) {
            throw new ValidationException();
        }else if(RELEASE_DATE.isAfter(film.getReleaseDate())) {
            throw new ValidationException();
        }
    }
}
