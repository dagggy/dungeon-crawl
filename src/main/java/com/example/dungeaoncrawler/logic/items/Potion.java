package com.example.dungeaoncrawler.logic.items;

import com.example.dungeaoncrawler.logic.Position;
import com.example.dungeaoncrawler.logic.actors.Actor;
import com.example.dungeaoncrawler.logic.actors.Player;

public class Potion extends Items{
    private int gainHealth;
    public Potion(String img, String name, Position position) {
        super(img, name, position);
    }

    @Override
    public void use() {

    }

}

