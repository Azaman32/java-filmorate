package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FilmController {
    private int key = 1;
    private final Map<Integer, Film> films = new HashMap<>();

    @GetMapping("/films")
    public ArrayList<Film> findAll() {
        return new ArrayList<>(films.values());
    }


}
