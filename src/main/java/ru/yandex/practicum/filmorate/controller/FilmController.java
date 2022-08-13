package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.ValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class FilmController {
    private FilmValidator validationFilms = new FilmValidator();
    Identifier identifier = new Identifier();
    private final Map<Integer, Film> films = new HashMap<>();

    @GetMapping("/films")
    public List<Film> findAll() {
        log.info("Выполнен запрос Get");
        return new ArrayList<>(films.values());
    }

    @PostMapping("/films")
    public Film create(@RequestBody Film film) {
        validationFilms.validation(film);
        film.setId(identifier.getId());
        films.put(film.getId(), film);
        log.info("POST /films: создан фильм с id {}", film.getId());
        return film;
    }

    @PutMapping("/films")
    public Film update(@RequestBody Film film) {
        validationFilms.validation(film);
        if (film.getId() <= 0) {
            throw new ValidationException("Invalid id");
        }
        for (Film variableFilm : films.values()) {
            if (variableFilm.getId() != film.getId()) {
                film.setId(identifier.getId());
                films.put(film.getId(), film);
                log.info("Put /films: создан фильм с id {}", film.getId());
            }
            if (variableFilm.getId() == film.getId()) {
                variableFilm.setName(film.getName());
                variableFilm.setDescription(film.getDescription());
                variableFilm.setDuration(film.getDuration());
                variableFilm.setReleaseDate(film.getReleaseDate());
                log.info("Put /films: обновлен фильм с id {}", film.getId());
                return variableFilm;
            }
        }
        return film;
    }
}
