package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Cell;

import java.util.concurrent.ThreadLocalRandom;

public class Wizard extends Enemy {
    //(int health, int resistance, int armor, int exp, int attackRound, Cell cell, String name, ActorType actorType, String[] attack,
    //                 int magicMin, int magicMax, int physMin, int physMax, int poisonMin, int poisonMax)
    public Wizard(int health, int resistance, int armor, int exp, int attackRound, Cell cell) {
        super(health, resistance, armor, exp, attackRound, cell, "skeleton" , ActorType.SKELETON, new String[]{"magic", "damage"},
                1, 10, 1, 3, 0, 0);
    }
}
