package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell, int health, int resistance, int armor) {
        super(cell, health, resistance, armor);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
