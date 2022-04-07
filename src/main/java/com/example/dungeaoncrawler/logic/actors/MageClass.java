package com.example.dungeaoncrawler.logic.actors;

import com.example.dungeaoncrawler.logic.Cell;
import com.example.dungeaoncrawler.logic.items.CardRarity;
import com.example.dungeaoncrawler.logic.items.Cards;
import com.example.dungeaoncrawler.logic.items.CardsCreator;
import com.example.dungeaoncrawler.logic.items.CardsType;

public class MageClass extends Player{

    public MageClass(int health, int resistance, int armor, String idUserName, int getCards, Cell cell) {
        super(health, resistance, armor, idUserName, getCards, cell);

    }

    @Override
    public void setStartingDeck() {
        setSpellStartingCards(3, CardsType.SPELL, 3);
        setSpellStartingCards(2, CardsType.DISPEL, 1);
        setSpellStartingCards(2, CardsType.STUN, 1);
        setSpellStartingCards(2, CardsType.POISON, 2);
        setSpellStartingCards(1, CardsType.HEAL, 2);

    }

    private void setSpellStartingCards(int cardsCount, CardsType cardsType, int value) {
        for (int i = 0; i < cardsCount; i++) {
            String cardImg = CardsCreator.imageCardCreator(cardsType);
            Cards card = new Cards(cardImg, "Spell", null, cardsType, CardRarity.COMMON, value);
            deck.add(card);
        }
    }

}
