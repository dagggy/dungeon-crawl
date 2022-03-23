package com.example.dungeaoncrawler.logic.items;

import com.example.dungeaoncrawler.logic.Position;
import com.example.dungeaoncrawler.logic.actors.Actor;

public class Cards extends Items{
    private final CardsType cardsType;
    private int cardCost;
    private CardRarity rarity;
    private int points;
    private String description;


    public Cards(String img, String name, Position position, CardsType cardsType, int cardCost, CardRarity cardRarity, String description) {
        super(img, name, position);
        this.cardsType = cardsType;
        this.cardCost = cardCost;
        this.rarity  = cardRarity;
        this.description = description;
    }

    public void use() {

    }

    public int getCardCost() {
        return cardCost;
    }

    public void setCardCost(int cardCost) {
        this.cardCost = cardCost;
    }

    public CardRarity getRarity() {
        return rarity;
    }

    public void setRarity(CardRarity rarity) {
        this.rarity = rarity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
