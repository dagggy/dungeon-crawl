package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Cell;
import com.example.dungeaoncrawler.logic.Drawable;
import com.example.dungeaoncrawler.logic.status.Heal;
import com.example.dungeaoncrawler.logic.status.Poisone;
import com.example.dungeaoncrawler.logic.status.Stun;

public abstract class Actor implements Drawable {
    //    private Cell cell;
    private int health = 10;
    private Heal heal;
    private Poisone poisone;
    private Stun stun;
    private int resistance;
    private int armor;
    private int power;


    public Actor(int health, int resistance, int armor) {
//        this.cell = cell;
        this.health = health;
        this.resistance = resistance;
        this.armor = armor;
        this.heal = null;
        this.poisone = null;
        this.stun = null;
        power = 1;
    }

    public Poisone getPoisone() {
        return poisone;
    }

    public void setPoisone(Poisone poisone) {
        this.poisone = poisone;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Heal getHeal() {
        return heal;
    }

    public void setHeal(Heal heal) {
        this.heal = heal;
    }

    public Poisone getPoison() {
        return poisone;
    }

    public void setPoison(Poisone poisone) {
        this.poisone = poisone;
    }

    public Stun getStun() {
        return stun;
    }

    public void setStun(Stun stun) {
        this.stun = stun;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void endFight() {
    }
}

//    public void move(int dx, int dy) {
//        Cell nextCell = cell.getNeighbor(dx, dy);
////        cell.setActor(null);
//        nextCell.setActor(this);
////        cell = nextCell;
//    }

//    public int getHealth() {
//        return health;
////    }

//    public Cell getCell() {
//        return cell;
//    }
//
//    public int getX() {
//        return cell.getX();
//    }
//
//    public int getY() {
//        return cell.getY();
//    }
//}
