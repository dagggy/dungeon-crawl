package com.example.dungeaoncrawler.fight;

import com.example.dungeaoncrawler.logic.actors.Actor;
import com.example.dungeaoncrawler.logic.actors.Player;
import com.example.dungeaoncrawler.logic.actors.Skeleton;
import com.example.dungeaoncrawler.logic.items.Cards;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Fight {
    public static void main(String[] args) {
        Player player = new Player(10,0,0,4);
        Skeleton skeleton = new Skeleton(20, 3,2);
        player.endFight();
//        while (skeleton.getHealth() > 0){
            playermove(player, skeleton);
//        }
    }
    private static void playermove(Actor player, Actor skeleton){
        ArrayList<Cards> hand = choseRandomCard((Player) player);
        rollDice(((Player) player).getDice());
        printHand(hand);
//        int index = getUserInput();


    }


    private static ArrayList<Cards> choseRandomCard(Player player){
        Random random = new Random();
        int cardsOnHand = player.getCards();
        ArrayList<Cards> hand = new ArrayList<>();
        ArrayList<Cards> deck = player.getPlayingDeck();
        for (int i = 0; i < cardsOnHand; i++) {
            if (deck.size() > player.getCards()){
                int index =random.nextInt(deck.size());
                hand.add(deck.get(index));
                deck.remove(index);
            }
            else {
                deck = new ArrayList<>(player.getDeck());
                int index = random.nextInt(deck.size());
                hand.add(deck.get(index));
                deck.remove(index);
            }
        }
        return hand;
    }

    private static int rollDice(int dices){
        Random random = new Random();
        int diceSum = 0;
        for (int i = 0; i < dices; i++) {
            int score = random.nextInt(6)+1;
            diceSum += score;
            System.out.println((i+1) + ". Dice roll = " + score);
        }
        System.out.println("--------------------");
        System.out.println("Throws value: " + diceSum);
        System.out.println();
        return diceSum;
    }

    private static void printHand(ArrayList<Cards> hand){
        System.out.println();
        System.out.println("PLAYER HAND");
        System.out.println();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println("CARD NUMBER" + (i+1));
//            System.out.println("Card name: " + hand.get(i).getName());
            System.out.println("Card Cost: " + hand.get(i).getCardCost());
            System.out.println("Rarity: " + hand.get(i).getRarity());
            System.out.println("Description: " + hand.get(i).getDescription());
            System.out.println();
        }
    }

    private static int getUserInput(){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        return number;
    }
}
