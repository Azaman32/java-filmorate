package ru.yandex.practicum.filmorate.controller;

import org.springframework.stereotype.Component;

@Component
public class FilmIdGenerator {
    private int id = 1;

    public int generate() {
        return id++;
    }
}
