package com.example.dungeaoncrawler.fight;

import com.example.dungeaoncrawler.logic.actors.Player;
import com.example.dungeaoncrawler.logic.items.Cards;

import java.util.ArrayList;

public class Fight {
    public static void main(String[] args) {
    Player player = new Player(10,2,2,4);
        ArrayList<Cards> deck = player.getDeck();
    for (int i = 0; i < deck.size(); i++) {
            System.out.println(deck.get(i).getDescription());
        }
    }


}
