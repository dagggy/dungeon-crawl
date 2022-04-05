package com.example.dungeaoncrawler.logic.actors;

import java.util.concurrent.ThreadLocalRandom;

public class Skeleton extends Actor {
    public Skeleton(int health, int resistance, int armor, int exp) {
        super(health, resistance, armor, exp);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    public String opponentChoseAttack(){
        String[] attack = new String[] {"magic", "damage", "poison"};
        return attack[ThreadLocalRandom.current().nextInt(0,attack.length)];
    }

    public int opponentAttack(String attack){
        switch (attack){
            case "magic" -> { return magicAttack();}
            case "damage" -> { return damageAttack();}
            case "poison" -> { return poisonAttack();}
            default -> {return 0;}
        }
    }


    private int magicAttack(){
        return ThreadLocalRandom.current().nextInt(2,5);
    }
    private int damageAttack(){
        return ThreadLocalRandom.current().nextInt(4,8);
    }
    private int poisonAttack(){
        return ThreadLocalRandom.current().nextInt(1,3);
    }
}
