package com.example.dungeaoncrawler.logic.items;

import com.example.dungeaoncrawler.logic.Position;
import com.example.dungeaoncrawler.logic.actors.Actor;
import com.example.dungeaoncrawler.logic.actors.Player;

import java.util.List;

public abstract class Items {
    protected String img;
    protected String name;
    protected Position position;

    public Items(String img, String name, Position position) {
        this.img = img;
        this.name = name;
        this.position = position;
    }

    public abstract void use();
}