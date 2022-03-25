package com.example.dungeaoncrawler;

import com.example.dungeaoncrawler.logic.actors.Actor;
import com.example.dungeaoncrawler.logic.actors.Player;
import com.example.dungeaoncrawler.logic.actors.Skeleton;
import com.example.dungeaoncrawler.logic.items.Cards;
import com.example.dungeaoncrawler.logic.status.Heal;
import com.example.dungeaoncrawler.logic.status.Poisone;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.*;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class FightControler {
    Player player;
    Skeleton opponent;
    boolean roll = false;
    private int sumDiceRoll;
    private ArrayList<Cards> hand = new ArrayList<>();

    public void initialize(){
        player = new Player(20,0,0,4);
        opponent = new Skeleton(10, 2, 5);
    }


    public void showCardsField(){
        cardsField.setVisible(true);
    }

    @FXML
    void printSumDice(MouseEvent event) {
    if (!roll) {
        int sumRolled = rollDice(3);
        setDiceSum("You rolled "+ sumRolled);
        roll = true;
    }
}
    public void setFightMassage(String massage){
        FightMassage.setText(massage);
    }


    @FXML
    void playCard(MouseEvent event) {
        AnchorPane source = (AnchorPane) event.getSource();
        source.setOpacity(0.2);
        int cardIndex = Integer.parseInt(source.toString().replaceAll("[^0-9.]", ""));
    }

    public Label getDiceSum() {
        return rollDice;
    }


    private void playermove(Actor player, Actor opponent){
        ArrayList<Cards> hand = drawRandomCards((Player) player);
        int roll = rollDice(((Player) player).getDice());
        displayMassage(roll);
        printHand(hand);
        if (canPlayCard(roll, hand)) {
            int cardNumber = getUserInput();
            playCard(player, opponent, cardNumber, hand, roll);
        }
    }

    /**
     * After clicking draw card button player draw cards and display it on card field
     * @param event - click on drawCard button
     */
    @FXML
    void drawCards(MouseEvent event) {
        ArrayList<Cards> hand = drawRandomCards(player);
        displayCards(hand);
        cardsField.setVisible(true);
    }

    /**
     * put cards property (image, cost, description etc.) into cards container.
     * @param hand list of cards object, that player draw during draw stage
     */
    private void displayCards(ArrayList<Cards> hand){
        ArrayList<AnchorPane> cardsContainer = createCardContainerList();
        for (int i = 0; i < cardsContainer.size(); i++) {
            AnchorPane container = cardsContainer.get(i);
            // set card image
//            ImageView cardImage = (ImageView) container.getChildren().get(0);
//            Image image = new Image("cards/swordAttack.gif");
//            cardImage.setImage(image);

            //set card description
            Label cardDescription = (Label) container.getChildren().get(2);
            cardDescription.setText(hand.get(i).getDescription());

            // set card cost
            Label cardCost = (Label) container.getChildren().get(3);
            cardCost.setText(String.valueOf(hand.get(i).getCardCost()));
        }
    }

    private ArrayList<AnchorPane> createCardContainerList(){
        ArrayList<AnchorPane> cardContainer = new ArrayList<>();
        Collections.addAll(cardContainer, card0, card1, card2, card3);
        return cardContainer;
    }

    /**
     * Display information about throw dice sum
     * @param throwDiceSum results throw dice sum
     */
    private void displayMassage(int throwDiceSum){
        rollDice.setText("You have rolled " + throwDiceSum);
    }

    private void playCard(Actor player, Actor opponent, int cardIndex, ArrayList<Cards> hand, int roll){
        if (hand.get(cardIndex).getCardCost()>=roll){
            resolveCardEffect(player, opponent, hand.get(cardIndex));
        } else {
            System.out.println("You dont have point action to play this card");
        }
    }

    private void resolveCardEffect(Actor player, Actor opponent, Cards card){
        switch (card.getCardsType()){
            case DECREASE_ARMOR -> opponent.setArmor(Math.max(opponent.getArmor() - card.getValue(), 0));
            case RESISTANCE -> player.setResistance(card.getValue());
            case DISPELL -> player.setDispell(card.getValue());
            case POISON -> opponent.setPoisone(new Poisone(player.getPower(), card.getValue()));
            case ATTACK -> opponent.takeDamage(card.getValue());
            case SPELL -> opponent.takeMagicDamage(card.getValue());
            case ARMOR -> player.setArmor(card.getValue());
            case HEAL -> player.setHeal(new Heal(player.getPower(), card.getValue()));
            case STUN -> opponent.setStun(player.getPower());
        }
    }

    private boolean canPlayCard(int roll, ArrayList<Cards> hand){
        if (hand.size()<=0) return false;
        else return roll >= getLowestCost(hand);
    }


    private int getLowestCost(ArrayList<Cards> hand){
        int lowestCost = 100;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getCardCost()< lowestCost) lowestCost = hand.get(i).getCardCost();
        }
        return lowestCost;
    }

    private ArrayList<Cards> drawRandomCards(Player player){
        Random random = new Random();
        int cardsOnHand = player.getCards();
        ArrayList<Cards> hand = new ArrayList<>();
        ArrayList<Cards> deck = player.getPlayingDeck();
        for (int i = 0; i < cardsOnHand; i++) {
            if (deck.size() > player.getCards()-hand.size()){
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

    private int rollDice(int dices){
        Random random = new Random();
        int diceSum = 0;
        String message = "";
        for (int i = 0; i < dices; i++) {
            int score = random.nextInt(6)+1;
            diceSum += score;
            message += (i+1) + ". Dice roll = " + score + "\n";
        }
        message += "You rolled " + diceSum;
        setFightMassage(message);
        return diceSum;
    }

    public void setDiceSum(String text){
        rollDice.setText(text);
    }
    private void printHand(ArrayList<Cards> hand){
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

    private int getUserInput(){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        return number;
    }







    @FXML
    private Label FightMassage;

    @FXML
    private ListView<?> PlayerInventory;

    @FXML
    private ListView<?> PlayerInventory1;

    @FXML
    private ListView<?> PlayerStatus;

    @FXML
    private ListView<?> PlayerStatus1;

    @FXML
    private AnchorPane card0;

    @FXML
    private AnchorPane card1;

    @FXML
    private ImageView card1background;

    @FXML
    private ImageView card1background2;

    @FXML
    private ImageView card1background3;

    @FXML
    private AnchorPane card2;

    @FXML
    private AnchorPane card3;

    @FXML
    private Label cardCost0;

    @FXML
    private Label cardCost1;

    @FXML
    private Label cardCost2;

    @FXML
    private Label cardCost3;

    @FXML
    private Label cardDescription0;

    @FXML
    private Label cardDescription1;

    @FXML
    private Label cardDescription2;

    @FXML
    private Label cardDescription3;

    @FXML
    private ImageView cardImage0;

    @FXML
    private ImageView cardImage1;

    @FXML
    private ImageView cardImage2;

    @FXML
    private ImageView cardImage3;

    @FXML
    private ImageView cardbackground1;

    @FXML
    private HBox cardsField;

    @FXML
    private Button drawCards;

    @FXML
    private Button endTurn;

    @FXML
    private ImageView playerImage;

    @FXML
    private ImageView playerImage1;

    @FXML
    private Label rollDice;

    @FXML
    private ImageView windowBackground;


}
