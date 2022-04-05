package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Cell;

import java.util.concurrent.ThreadLocalRandom;

public class Knight extends Actor {
    public Knight(int health, int resistance, int armor, Cell cell) {
        super(health, resistance, armor, ActorType.KNIGHT, cell);
    }

    @Override
    public String getTileName() {
        return "knight";
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
