package com.example.dungeaoncrawler.logic.items;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public enum CardsType {
    SPELL,
    ATTACK,
    POISON,
    ARMOR,
    RESISTANCE,
    DISPELL,
    DECREASE_ARMOR,
    HEAL,
    STUN;

    public static CardsType getRandomOffensive() {
        Random random = new Random();
        return values()[random.nextInt(3)];
    }
    public static CardsType getRandomDefence() {
        Random random = new Random();
        return values()[ThreadLocalRandom.current().nextInt(3, 6)];
    }
    public static CardsType getRandomeType(){
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
