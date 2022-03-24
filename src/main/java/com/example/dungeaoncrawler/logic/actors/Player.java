package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Cell;
import com.example.dungeaoncrawler.logic.Position;
import com.example.dungeaoncrawler.logic.items.CardRarity;
import com.example.dungeaoncrawler.logic.items.Cards;
import com.example.dungeaoncrawler.logic.items.CardsCreator;
import com.example.dungeaoncrawler.logic.items.CardsType;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Player extends Actor {
    private int getCards;
    private int dice;
    private int lvl;
    private int exp;
    ArrayList<Cards> deck = new ArrayList<>();

    public Player(int health, int resistance, int armor, int getCards) {
        super(health, resistance, armor);
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
        int attackCards = 4;
        int defendsCards = 4;
        int otherCards = 2;
        addOffensiveCardsToStartingDeck(attackCards);
        addDefensiveCardsToStartingDeck(defendsCards);
        addRandomCardsToStartingDeck(otherCards);
    }

    private void addOffensiveCardsToStartingDeck(int cardsNumber){
        for (int i = 0; i < cardsNumber; i++) {
            CardRarity rarity = drawRarity();
            CardsType cardsType = CardsType.getRandomOffensive();
            deck.add(new Cards("img.png", "attack", null, cardsType, rarity));
        }
    }

    private void addDefensiveCardsToStartingDeck(int cardsNumber){
        for (int i = 0; i < cardsNumber; i++) {
            CardRarity rarity = drawRarity();
            CardsType cardsType = CardsType.getRandomDefence();
            deck.add(new Cards("img.png", "attack", null, cardsType, rarity));
        }
    }
    private void addRandomCardsToStartingDeck(int cardsNumber){
        for (int i = 0; i < cardsNumber; i++) {
            CardRarity rarity = drawRarity();
            CardsType cardsType = CardsType.getRandomeType();
            deck.add(new Cards("img.png", "attack", null, cardsType, rarity));
        }
    }

    private CardRarity drawRarity(){
        int faith = ThreadLocalRandom.current().nextInt(0,11);
        if (faith <=6 ) return CardRarity.COMMON;
        else if (faith <=9) return CardRarity.RARE;
        else return CardRarity.MYTHIC;
    }

    public int getGetCards() {
        return getCards;
    }

    public void setGetCards(int getCards) {
        this.getCards = getCards;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public ArrayList<Cards> getDeck() {
        return deck;
    }

    public void addCardToDeck(Cards card) {
        deck.add(card);
    }
}
//    Cards(String img, String name, Position position, CardsType cardsType, CardRarity cardRarity)