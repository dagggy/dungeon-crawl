package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Cell;
import com.example.dungeaoncrawler.logic.items.Cards;
import com.example.dungeaoncrawler.logic.items.Items;
import com.example.dungeaoncrawler.logic.items.PassiveSkills;

import java.util.ArrayList;
import java.util.Collections;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell, 10, 0, 0, "player");
        this.exp = 0;
        Collections.addAll(this.diceOwned, 6, 6);
    }

    int exp;
    ArrayList<Integer> diceOwned;

    ArrayList<Items> inventory;
    ArrayList<Cards> deck;
    ArrayList<PassiveSkills> passiveSkills;

    public String getTileName() {
        return "player";
    }
}
