package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Cell;
import com.example.dungeaoncrawler.logic.CellType;

public class SkeletonBoss extends Enemy {

    public SkeletonBoss(int health, int resistance, int armor, int exp, int attackRound, Cell cell) {
        super(health, resistance, armor, exp, attackRound, cell, "skeleton boss" , ActorType.ANGRY_SKELETON, new String[]{"magic", "damage"},
                5, 11, 8, 16, 0, 0);
    }

    @Override
    public void onKill () {
        this.cell.setType(CellType.KEY);
    }
}
