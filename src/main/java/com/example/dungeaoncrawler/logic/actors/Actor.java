package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Drawable;
import com.example.dungeaoncrawler.logic.status.Heal;
import com.example.dungeaoncrawler.logic.status.Poisone;

import java.util.ArrayList;

public abstract class Actor implements Drawable {
    //    private Cell cell;
    private int health = 10;
    private ArrayList <Heal> heal;
    private ArrayList <Poisone> poisone;
    private int stun;
    private int resistance;
    private int armor;
    private int dispell;
    private int power;


    public Actor(int health, int resistance, int armor) {
//        this.cell = cell;
        this.health = health;
        this.resistance = resistance;
        this.armor = armor;
        this.heal = new ArrayList<>();
        this.poisone = new ArrayList<>();
        this.stun = 0;
        power = 1;
        dispell=0;
    }

    public ArrayList <Poisone> getPoisone() {
        return poisone;
    }

    public void setPoisone(Poisone poisone) {
        this.poisone.add(poisone);
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

    public ArrayList <Heal> getHeal() {
        return heal;
    }

    public void setHeal(Heal heal) {
        this.heal.add(heal);
    }

    public int getStun() {
        return stun;
    }

    public void setStun(int stun) {
        this.stun += stun;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance += resistance;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void takeDamage(int damage) {
        if (damage>armor) {
            health -= damage - armor;
            armor = 0;
        } else armor -= damage;
    }

    public void takeMagicDamage(int damage){
        if (dispell>0) dispell -=1;
        else if (damage>resistance) {
            health -= damage - resistance;
            resistance = 0;
        } else resistance -= damage;
    }

    public void setDispell(int dispell) {
        this.dispell += dispell;
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
