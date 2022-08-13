package ru.yandex.practicum.filmorate.controller;

import lombok.Getter;

@Getter
public class Identifier {
    private int id = 1;

    public int generate() {
        return id++;
    }
}
