package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Cell;
import com.example.dungeaoncrawler.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    private int armor;
    private int powerLvl;
    private String name;

    public Actor(Cell cell, int health, int armor, int powerLvl, String name) {
        this.cell = cell;
        this.health = health;
        this.armor = armor;
        this.powerLvl = powerLvl;
        this.name = name;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public int getHealth() {
        return health;
    }

    public String getActorName() {
        return name;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
