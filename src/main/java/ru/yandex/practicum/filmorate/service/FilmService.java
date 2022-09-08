package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.controller.FilmIdGenerator;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FilmService {
    private final FilmStorage filmStorage;
    private final UserService userService;
    private final FilmIdGenerator filmIdGenerator;

    @Autowired
    public FilmService(FilmStorage filmStorage, UserService userService, FilmIdGenerator filmIdGenerator) {
        this.filmStorage = filmStorage;
        this.userService = userService;
        this.filmIdGenerator = filmIdGenerator;
    }

    public List<Film> getAllFilm() {
        return filmStorage.getAll();
    }

    public Film getFilm(int filmId) {
        return filmStorage.get(filmId);
    }

    public Film saveFilm(Film film) {
        film.setId(filmIdGenerator.generate());
        filmStorage.save(film);
        return film;
    }

    public void addLike(int filmId, int userId) {
        filmStorage.get(filmId).setLike(userId);
    }

    public void deleteLike(int filmId, int userId) {
        userService.userExist(userId);
        filmStorage.get(filmId).getLike().remove(userId);
    }

    public void updateFilm(Film film) {
        filmStorage.save(film);
    }

    public List<Film> getTopFilms(int count) {
        List<Film> films = filmStorage.getAll();
        List<Film> top = new ArrayList<>();
        Comparator<Film> comparator = (o1, o2) -> o2.getNumberLikes().compareTo(o1.getNumberLikes());
        films.sort(comparator);
        if (films.size() < count) {
            count = films.size();
        }
        for (int i = 0; i < count; i++) {
            top.add(films.get(i));
        }
        return top;
    }
}
