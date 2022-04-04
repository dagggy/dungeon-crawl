package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Drawable;
import com.example.dungeaoncrawler.logic.status.Heal;
import com.example.dungeaoncrawler.logic.status.Poison;

import java.util.ArrayList;

public abstract class Actor implements Drawable {
    //    private Cell cell;
    private int health = 10;
    private ArrayList <Heal> heal;
    private ArrayList <Poison> poison;
    private int stun;
    private int resistance;
    private int armor;
    private int dispel;
    private int power;


    public Actor(int health, int resistance, int armor) {
//        this.cell = cell;
        this.health = health;
        this.resistance = resistance;
        this.armor = armor;
        this.heal = new ArrayList<>();
        this.poison = new ArrayList<>();
        this.stun = 0;
        power = 1;
        dispel =0;
    }

    public ArrayList <Poison> getPoison() {
        return poison;
    }

    public String setPoisone(Poison poison) {
        if (dispel == 0){
        this.poison.add(poison);
        return "Player successfully poison opponent\n";}
        else {
            dispel -= 1;
            return "Opponent successfully block spell\n";
        }

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

    public int getDispel() {
        return dispel;
    }

    public void setDispel(int dispel) {
        this.dispel = dispel;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public ArrayList <Heal> getHeal() {
        return heal;
    }

    public String setHeal(Heal heal) {
        this.heal.add(heal);
        return "Player successfully cast healing\n";
    }

    public int getStun() {
        return stun;
    }

    public String setStun(int stun) {
        if (dispel <= 0) {
            this.stun += stun;
            return "Opponent is stuned\n";
        } else {
            dispel -= 1;
            return "Opponent successfully cancel player spell \n";
        }
    }

    public int getResistance() {
        return resistance;
    }

    public String setResistance(int resistance) {
        this.resistance += resistance;
        return "Player increase resistance by " + resistance + "\n";
    }

    public int getArmor() {
        return armor;
    }

    public String setArmor(int armor) {
        this.armor = armor;
        return "Armor is set on " + armor + " points\n";
    }

    public String takeDamage(int damage) {
        if (damage>armor) {
            int dealtDamage = damage - armor;
            health -= dealtDamage;
            armor = 0;
            return "Player deal " + dealtDamage + " damage\n";
        } else armor -= damage;
        return "Player decrease armor by " + damage+"\n";
    }

    public String takeMagicDamage(int damage){
        if (dispel >0) {
            dispel -= 1;
            return "Opponent block your spell\n";
        }
        else if (damage > resistance) {
            int dealtMagicDamage = damage - resistance;
            health -= dealtMagicDamage;
            resistance = 0;
            return "Player deal " + dealtMagicDamage + " magic damage\n";
        } else resistance -= damage;
        return "Player decrease opponent resistance by " + damage + " points\n";
    }

    public String setDispell(int dispell) {
        this.dispel += dispell;
        return "Player can now block next " + this.dispel + " spell(s)\n";
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
