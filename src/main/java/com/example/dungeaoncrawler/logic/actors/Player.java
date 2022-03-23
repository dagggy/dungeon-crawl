package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Cell;
import com.example.dungeaoncrawler.logic.items.Cards;

import java.util.ArrayList;

public class Player extends Actor {
    private int getCards;
    private int dice;
    private int lvl;
    private int exp;
    ArrayList<Cards> deck = new ArrayList<>();

    public Player(Cell cell, int health, int resistance, int armor, int getCards) {
        super(cell, health, resistance, armor);
        this.getCards = getCards;
        this.lvl = 1;
        exp = 0;
        dice = 3;
        setStartingDeck();
    }

    public String getTileName() {
        return "player";
    }
    public void setStartingDeck(){
    }
}
