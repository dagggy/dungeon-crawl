package com.example.dungeaoncrawler.logic.items;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class CardsCreator {
    public static int[] commonDamage = {1,5};
    public static int[] rareDamage = {4,8};
    public static int[] mythicDamage = {10,25};
    public static int[] commonPoison = {1,3};
    public static int[] rarePoison = {2,4};
    public static int[] mythicPoison = {5,8};

    public static int setCardDamage(int[] cardsRare) {
        return ThreadLocalRandom.current().nextInt(cardsRare[0], cardsRare[1]);
    }

}
