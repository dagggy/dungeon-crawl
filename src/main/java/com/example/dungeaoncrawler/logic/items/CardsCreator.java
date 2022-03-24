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

    public static int setCardValue(int[] cardsRare) {
        return ThreadLocalRandom.current().nextInt(cardsRare[0], cardsRare[1]);
    }

    public static String setDescription(CardsType cardsType, int value){
        switch (cardsType){
            case ARMOR -> {return "Block next " + value + " physical damage";}
            case STUN -> {return "Stun opponent for " + value + " round";}
            case HEAL -> {return "Heal yourself at " + value + " points for turns equals your power";}
            case SPELL -> {return "Deal opponent " + value + " magical damage";}
            case ATTACK -> {return "Deal opponent " + value + " physical damage";}
            case POISON -> {return "Poison opponent at " + value + " points for turns equals your power";}
            case DISPELL -> {return "Block next " + value + " opponent spell(s)";}
            case RESISTANCE -> {return "Block next " + value + " magical damage";}
            case DECREASE_ARMOR -> {return "Decrease " + value + " opponent armor";}
        }
        return "do nothing";
    }
}
