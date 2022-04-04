package com.example.dungeaoncrawler;

import com.example.dungeaoncrawler.logic.actors.Actor;
import com.example.dungeaoncrawler.logic.actors.Player;
import com.example.dungeaoncrawler.logic.actors.Skeleton;
import com.example.dungeaoncrawler.logic.items.Cards;
import com.example.dungeaoncrawler.logic.status.CharacterAttributes;
import com.example.dungeaoncrawler.logic.status.Heal;
import com.example.dungeaoncrawler.logic.status.Poison;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FightControler {
    Player player;
    Skeleton opponent;
    boolean wasRolled = false;
    private int sumDiceRoll;
    private ArrayList<Cards> hand = new ArrayList<>();
    private boolean drawCard = false;

    public void initialize(){
        player = new Player(20,0,0,4);
        opponent = new Skeleton(10, 2, 5);
        displayCharacterInfo(player);
        displayCharacterInfo(opponent);
    }



    private void displayCharacterInfo(Player player){
        ObservableList<CharacterAttributes> playerAttributes = createCharacterAttributesList(player);
        PlayerAttributesDisplayContainer.setItems(playerAttributes);
        PlayerAttributesType.setCellValueFactory(cellData-> cellData.getValue().getAttributeName());
        PlayerAttributesValue.setCellValueFactory(cellData-> cellData.getValue().getAttributeValue().asObject());
    }

    private void displayCharacterInfo(Actor opponent){
        ObservableList<CharacterAttributes> characterAttributes = createCharacterAttributesList(opponent);
        OpponentAttributesDisplayContainer1.setItems(characterAttributes);
        OpponentAttributesType1.setCellValueFactory(cellData-> cellData.getValue().getAttributeName());
        OpponentsAttributesValue1.setCellValueFactory(cellData-> cellData.getValue().getAttributeValue().asObject());
    }
    //TODO dokończyć wyświetlanie statusów
//    @FXML
//    private TableView<CharacterAttributes> OpponentAttributesDisplayContainer1;
//
//    @FXML
//    private TableColumn<CharacterAttributes, String> OpponentAttributesType1;
//
//    @FXML
//    private TableColumn<CharacterAttributes, Integer> OpponentsAttributesValue1;

    private ObservableList<CharacterAttributes> createCharacterAttributesList(Actor character){
        ObservableList<CharacterAttributes> characterAttributes = FXCollections.observableArrayList();
        characterAttributes.add(new CharacterAttributes("Health", character.getHealth()));
        characterAttributes.add (new CharacterAttributes("Armor",character.getArmor()));
        characterAttributes.add (new CharacterAttributes("Resistance", character.getResistance()));
        characterAttributes.add (new CharacterAttributes("Power",character.getPower()));
        characterAttributes.add (new CharacterAttributes("Dispel",character.getDispel()));
        return characterAttributes;
    }


    /**
     * put into array every information about actors like health, armor, power etc
     * @param character player or opponent
     * @return array with information to display.
     */

    /**
     * after clicking roll dice simulate throw dice
     * @param event mouse click
     */

    @FXML
    void printSumDice(MouseEvent event) {
    if (!wasRolled) {
        int sumRolled = rollDice(3);
        this.sumDiceRoll = sumRolled;
        setDiceSum("You rolled "+ sumRolled);
        wasRolled = true;
    }
}

    /**
     * display message during fight - information about dealt dmg, healing etc.
     * @param message text message that we want to display
     */
    public void setFightMessage(String message){
        FightMassage.setText(message);
    }

    /**
     * game logic after picking card to play
     * @param event click on card container
     */
    @FXML
    void playCard(MouseEvent event) {
        AnchorPane source = (AnchorPane) event.getSource();
        int cardIndex = Integer.parseInt(source.toString().replaceAll("[^0-9.]", ""));
        if (canPlayCard(sumDiceRoll, hand, cardIndex)) {
            String message = resolveCardEffect(player, opponent, hand.get(cardIndex));
            setFightMessage(message);
            source.setOpacity(0.2);
            source.setDisable(true);
            sumDiceRoll -= hand.get(cardIndex).getCardCost();
        } else {
            String message = "You don't have points to play this card\n";
            setFightMessage(message);
        }
    }
//TODO zmienić label na listView lub tabelview
    public Label getDiceSum() {
        return rollDice;
    }


    /**
     * After clicking draw card button player draw cards and display it on card field
     * @param event - click on drawCard button
     */
    @FXML
    void drawCards(MouseEvent event) {
        if (!drawCard) {
            ArrayList<Cards> hand = drawRandomCards(player);
            this.hand = hand;
            displayCards(hand);
            cardsField.setVisible(true);
            drawCard= true;
            endTurn.setVisible(true);
        }
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
            ImageView cardImage = (ImageView) container.getChildren().get(0);
            cardImage.setImage(new Image("swordAttack.gif"));

            //set card description
            Label cardDescription = (Label) container.getChildren().get(2);
            cardDescription.setText(hand.get(i).getDescription());

            // set card cost
            Label cardCost = (Label) container.getChildren().get(3);
            cardCost.setText(String.valueOf(hand.get(i).getCardCost()));
        }
    }

    /**
     *
     * @return create list card containers
     */
    private ArrayList<AnchorPane> createCardContainerList(){
        ArrayList<AnchorPane> cardContainer = new ArrayList<>();
        Collections.addAll(cardContainer, card0, card1, card2, card3);
        return cardContainer;
    }
    //TODO reset values after end of round - enable clickers change opacity

    /**
     * Display information about throw dice sum
     * @param throwDiceSum results throw dice sum
     */
    private void displayMassage(int throwDiceSum){
        String message = "You have rolled " + throwDiceSum+"\n";
        rollDice.setText(message);
    }


    /**
     * after picking card to play
     * @param player player character
     * @param opponent opponent character
     * @param card picked card
     * @return return message to display about card effect
     */
    private String resolveCardEffect(Actor player, Actor opponent, Cards card){
        switch (card.getCardsType()){
            case DECREASE_ARMOR -> {return opponent.setArmor(Math.max(opponent.getArmor() - card.getValue(), 0));}
            case RESISTANCE -> {return player.setResistance(card.getValue());}
            case DISPELL -> {return player.setDispell(card.getValue());}
            case POISON -> {return opponent.setPoisone(new Poison(player.getPower(), card.getValue()));}
            case ATTACK -> {return opponent.takeDamage(card.getValue());}
            case SPELL -> {return opponent.takeMagicDamage(card.getValue());}
            case ARMOR -> {return player.setArmor(card.getValue());}
            case HEAL -> {return player.setHeal(new Heal(player.getPower(), card.getValue()));}
            case STUN -> {return opponent.setStun(player.getPower());}
        } return "";
    }

    private boolean canPlayCard(int roll, ArrayList<Cards> hand, int cardIndex){
        return roll >= hand.get(cardIndex).getCardCost();
    }

    /**
     * draw card mechanism
     * @param player player
     * @return all cards that are in hand
     */
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

    /**
     * simulate throwing cards
     * @param dices coun of dice that player have
     * @return sum of all dice thraws
     */
    private int rollDice(int dices){
        Random random = new Random();
        int diceSum = 0;
        String message = "";
        for (int i = 0; i < dices; i++) {
            int score = random.nextInt(6)+1;
            diceSum += score;
            message += (i+1) + ". Dice roll = " + score + "\n";
        }
        message += "You rolled " + diceSum + "\n";
        setFightMessage(message);
        return diceSum;
    }

    public void setDiceSum(String text){
        rollDice.setText(text);
    }


    @FXML
    private TableView<CharacterAttributes> OpponentAttributesDisplayContainer1;

    @FXML
    private TableColumn<CharacterAttributes, String> OpponentAttributesType1;

    @FXML
    private VBox OpponentStatus;

    @FXML
    private TableColumn<CharacterAttributes, Integer> OpponentsAttributesValue1;

    @FXML
    private TableView<CharacterAttributes> PlayerAttributesDisplayContainer;

    @FXML
    private TableColumn<CharacterAttributes, String> PlayerAttributesType;

    @FXML
    private TableColumn<CharacterAttributes, Integer> PlayerAttributesValue;

    @FXML
    private Label FightMassage;

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
