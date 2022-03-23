package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Cell;

public class Enemy extends Actor {
    public Enemy (Cell cell, int health, int armor, int powerLvl, String name, int xpOnKill) {
        super(cell, health, armor, powerLvl, name);

    }

    @Override
    public String getTileName() {
        return this.getActorName();
    }
}
