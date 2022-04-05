package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Cell;

import java.util.concurrent.ThreadLocalRandom;

public class Knight extends Actor {
    public Knight(int health, int resistance, int armor, int exp, int attackRound, Cell cell) {
        super(health, resistance, armor, exp, "Knight", attackRound, ActorType.KNIGHT, cell);
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
