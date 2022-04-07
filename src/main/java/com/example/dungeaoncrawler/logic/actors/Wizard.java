package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Cell;

public class Wizard extends Enemy {

    public Wizard(int health, int resistance, int armor, int exp, int attackRound, Cell cell) {
        super(health, resistance, armor, exp, attackRound, cell, "wizard", ActorType.WIZARD, new String[]{"magic", "damage"},
                1, 10, 1, 3, 0, 0);
    }
}
