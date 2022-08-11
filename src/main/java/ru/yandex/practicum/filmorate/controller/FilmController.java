package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.ValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FilmController {
    ValidationFilms validationFilms = new ValidationFilms();
    private static final Logger log = LoggerFactory.getLogger(FilmController.class);
    private int key = 1;
    private final Map<Integer, Film> films = new HashMap<>();

    @GetMapping("/films")
    public ArrayList<Film> findAll() {
        log.info("Выполнен запрос Get");
        return new ArrayList<>(films.values());
    }

    @PostMapping("/films")
    public Film create(@RequestBody Film film) {
       validationFilms.validation(film);
       film.setId(key);
       films.put(key, film);
       key = key + 1;
       log.info("Выполнен запрос Post");
       return film;
    }

    @PutMapping("/films")
    public Film update(@RequestBody Film film) {
        validationFilms.validation(film);
        if(film.getId() <= 0) {
            throw new ValidationException();
        }
        for (Film film1 : films.values()) {
            if (film1.getId() !=film.getId()) {
                film.setId(key);
                films.put(key, film);
                key += 1;
            }
            if(film1.getId() == film.getId()) {
                film1.setName(film.getName());
                film1.setDescription(film.getDescription());
                film1.setDuration(film.getDuration());
                film1.setReleaseDate(film.getReleaseDate());
                return film1;
            }
        }
        log.info("Выполнен запрос Put");
        return film;
    }
}
