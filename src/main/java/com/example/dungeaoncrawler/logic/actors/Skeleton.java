package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Cell;

import java.util.concurrent.ThreadLocalRandom;

public class Skeleton extends Actor {
    public Skeleton(int health, int resistance, int armor) {
        super(health, resistance, armor);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
    public int magicAttack(){
        return ThreadLocalRandom.current().nextInt(2,5);
    }
    public int damageAttack(){
        return ThreadLocalRandom.current().nextInt(4,8);
    }
    public int poisonAttack(){
        return ThreadLocalRandom.current().nextInt(1,3);
    }
}
