package com.example.dungeaoncrawler.logic.items;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public enum CardsType {

    SPELL("img.png"),
    ATTACK("cardHeal.png"),
    POISON("poison2.png"),
    ARMOR("img.png"),
    RESISTANCE("cardHeal.png"),
    DISPEL("poison2.png"),
    DECREASE_ARMOR("img.png"),
    HEAL("cardHeal.png"),
    STUN("poison2.png"),
    DISCARD("img.png");


    private final String pngImg;

    CardsType(String pngImg) {
        this.pngImg = pngImg;
    }

    public String getPngImg() {
        return pngImg;
    }

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
