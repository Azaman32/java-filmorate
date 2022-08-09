package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FilmController {
    ValidationFilms validationFilms = new ValidationFilms();
    private int key = 1;
    private final Map<Integer, Film> films = new HashMap<>();

    @GetMapping("/films")
    public ArrayList<Film> findAll() {
        return new ArrayList<>(films.values());
    }

    @PostMapping("/films")
    public Film create(@RequestBody Film film) {
       validationFilms.validation(film);
       films.put(key, film);
       key = key + 1;
       return film;
    }

    @PutMapping("/films")
    public Film update(@RequestBody Film film) {
        validationFilms.validation(film);
        for (Film film1 : films.values()) {
            if (!film1.equals(film)) {
                films.put(key, film);
                key += 1;
            }
        }
        return film;
    }
}
