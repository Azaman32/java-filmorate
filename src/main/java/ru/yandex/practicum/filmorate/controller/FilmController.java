package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.List;

@RestController
@Slf4j
public class FilmController {
    private final FilmService filmService;
    private final FilmValidator validationFilms = new FilmValidator();

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public List<Film> findAll() {
        log.info("Выполнен запрос Get");
        return filmService.getAllFilm();
    }

    @GetMapping("/films/{id}")
    public Film getFilm(@PathVariable int id) {
        if (id < 0) {
            throw new UserNotFoundException("Invalid id");
        }
        return filmService.getFilm(id);
    }

    @GetMapping("/films/popular")
    public List<Film> topFilms(@RequestParam(required = false, defaultValue = "10") int count) {
        return filmService.getTopFilms(count);
    }

    @PostMapping("/films")
    public Film create(@RequestBody Film film) {
        validationFilms.validation(film);
        filmService.saveFilm(film);
        log.info("POST /films: создан фильм с id {}", film.getId());
        return film;
    }

    @PutMapping("/films")
    public Film update(@RequestBody Film film) {
        validationFilms.validation(film);
        filmService.updateFilm(film);
        log.info("Put /films: обновлен фильм с id {}", film.getId());
        return film;
    }

    @PutMapping("/films/{id}/like/{userId}")
    public void putLike(@PathVariable int id, @PathVariable int userId) {
        filmService.addLike(id, userId);
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    public void removeLike(@PathVariable int id, @PathVariable int userId) {
        filmService.deleteLike(id, userId);
    }

}
