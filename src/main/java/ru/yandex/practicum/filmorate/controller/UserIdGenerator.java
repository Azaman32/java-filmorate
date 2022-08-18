package ru.yandex.practicum.filmorate.controller;

public class UserIdGenerator {
    private int id = 1;

    public int generate() {
        return id++;
    }
}

