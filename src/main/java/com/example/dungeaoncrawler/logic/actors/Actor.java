package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Drawable;
import com.example.dungeaoncrawler.logic.status.LifeChanger;

import java.util.ArrayList;

public abstract class Actor implements Drawable {
//        private Cell cell;
    protected int health = 10;
    protected ArrayList <LifeChanger> heal;
    protected ArrayList <LifeChanger> poison;
    protected int stun;
    protected int resistance;
    protected int armor;
    protected int dispel;
    protected int power;
    protected int exp;



    public Actor(int health, int resistance, int armor, int exp) {
//        this.cell = cell;
        this.health = health;
        this.resistance = resistance;
        this.armor = armor;
        this.heal = new ArrayList<>();
        this.poison = new ArrayList<>();
        this.stun = 0;
        this.exp = exp;
        power = 1;
        dispel = 0;
    }

    public ArrayList <LifeChanger> getPoison() {
        return poison;
    }

    public String setPoison(LifeChanger poison) {
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

    public void resetDispel() {
        this.dispel = 0;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public ArrayList <LifeChanger> getHeal() {
        return heal;
    }

    public String setHeal(LifeChanger lifeChanger) {
        this.heal.add(lifeChanger);
        return "Player successfully cast healing\n";
    }

    public int getStun() {
        return stun;
    }
    public void resetStun() {
        stun = 0;
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

    public String setDispel(int dispel) {
        this.dispel += dispel;
        return "Player can now block next " + this.dispel + " spell(s)\n";
    }

    public int getExp() {
        return exp;
    }
    public void resolveLifeChanger() {
        checkPlayerStatus(poison);
        checkPlayerStatus(heal);
    }

    private void checkPlayerStatus(ArrayList<LifeChanger> list){
        if (list.size()>0) for (int i = 0; i < list.size(); i++) {
            LifeChanger lifeChanger = list.get(i);
            health += lifeChanger.getLifeChanger();
            if (lifeChanger.getRounds() > 1) {
                lifeChanger.setRounds(-1);
            } else {
                list.remove(i);
            }
        }
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
