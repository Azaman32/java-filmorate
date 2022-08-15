package ru.yandex.practicum.filmorate.controller;

public class FilmIdGenerator {
    private int id = 1;

    public int generate() {
        return id++;
    }
}
