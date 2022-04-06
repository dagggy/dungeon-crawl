package com.example.dungeaoncrawler;

import com.example.dungeaoncrawler.logic.actors.Actor;
import com.example.dungeaoncrawler.logic.actors.Player;
import com.example.dungeaoncrawler.logic.actors.Skeleton;
import com.example.dungeaoncrawler.logic.actors.WarriorClass;
import com.example.dungeaoncrawler.logic.items.CardRarity;
import com.example.dungeaoncrawler.logic.items.Cards;
import com.example.dungeaoncrawler.logic.items.CardsCreator;
import com.example.dungeaoncrawler.logic.items.CardsType;
import com.example.dungeaoncrawler.logic.status.CharacterAttributes;
import com.example.dungeaoncrawler.logic.status.LifeChanger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FightController {
    Player player;
    Skeleton opponent;
    boolean wasRolled = false;
    private int sumDiceRoll;
    private ArrayList<Cards> hand = new ArrayList<>();
    private ArrayList<Cards> winningCards;
    private boolean drawCard = false;
    private boolean playerTurn = true;
    private int handSize;
    private int handSizeModification =0;


    public void initialize(){
        player = new WarriorClass(150,0,5,4, null);
        opponent = new Skeleton(100, 4, 1, 30,2, null);
        displayActorInfo(player);
        displayActorInfo(opponent);
        handSize = player.getCards();
    }

    private void displayActorInfo(Actor actor){
        if (actor instanceof Player) {
            displayPlayerInfo((Player) actor);
        } else {displayOpponentInfo(actor);}
    }

    private void displayPlayerInfo(Player player){
        PlayerAttributesDisplayContainer.getItems().clear();
        ObservableList<CharacterAttributes> playerAttributes = createCharacterAttributesList(player);
        PlayerAttributesDisplayContainer.setItems(playerAttributes);
        PlayerAttributesType.setCellValueFactory(cellData-> cellData.getValue().getAttributeName());
        PlayerAttributesValue.setCellValueFactory(cellData-> cellData.getValue().getAttributeValue().asObject());
    }

    /**
     * Fill table with player attributes and its value and display it.
     * @param opponent
     */
    private void displayOpponentInfo(Actor opponent){
        OpponentAttributesDisplayContainer1.getItems().clear();
        ObservableList<CharacterAttributes> characterAttributes = createCharacterAttributesList(opponent);
        OpponentAttributesDisplayContainer1.setItems(characterAttributes);
        OpponentAttributesType1.setCellValueFactory(cellData-> cellData.getValue().getAttributeName());
        OpponentsAttributesValue1.setCellValueFactory(cellData-> cellData.getValue().getAttributeValue().asObject());
    }

    /**
    Create List of object from characters attributes with its name and value.
     */
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
        matchHistory.getItems().add(0,message);
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
            if (hand.get(cardIndex).getCardsType() != CardsType.DISPEL || hand.get(cardIndex).getCardsType() != CardsType.DISPEL && hand.size()>1) {
                String message = resolveCardEffect(hand.get(cardIndex));
                setFightMessage(message);
                source.setOpacity(0.2);
                source.setDisable(true);
                sumDiceRoll -= hand.get(cardIndex).getCardCost();
                refreshCharacterAttributes(hand.get(cardIndex));
                displayPlayerCondition();
                displayOpponentCondition();
                rollDice.setText(sumDiceRoll > 0 ? sumDiceRoll + " points remains" : "No points left.");
                checkForWin();
            }
        } else {
            String message = "You don't have points to play this card\n";
            setFightMessage(message);
        }
    }


    @FXML
    void pickReward(MouseEvent event) {
        AnchorPane source = (AnchorPane) event.getSource();
        int cardIndex = Integer.parseInt(source.toString().replaceAll("[^0-9.]", ""));
        player.addCardToDeck(winningCards.get(cardIndex));
        winningBoard.setDisable(true);
        player.endFight();
    }


    private void displayPlayerCondition() {
        if (player.isHeal()) {playerHealIcon.setVisible(true);
        }else playerHealIcon.setVisible(false);

        if (player.isPoison()) {playerPoisonIcon.setVisible(true);
        } else {playerPoisonIcon.setVisible(false);}

        if (player.isStuned()) {playerStunIcon.setVisible(true);
        } else {playerStunIcon.setVisible(false);}
    }

    private void displayOpponentCondition(){
        if (opponent.isHeal()) {opponentHealIcon.setVisible(true);
        } else opponentHealIcon.setVisible(false);

        if (opponent.isPoison()) {opponentPoisonIcon.setVisible(true);
        } else {opponentPoisonIcon.setVisible(false);}

        if (opponent.isStuned()) {opponentStunIcon.setVisible(true);
        } else {opponentStunIcon.setVisible(false);}
    }

    private void checkForWin() {
        if (opponent.getHealth() <= 0){
            playerIsWon();
        } else if (player.getHealth() <= 0)
            opponentIsWon();
    }

    private void opponentIsWon() {
        String message = "You lose general Kenobi\n Hahaha";
        setFightMessage(message);
        endTurn.setDisable(true);
        GameBoard.setVisible(false);
        endFightButton.setVisible(true);
    }

    private void playerIsWon() {
        String message = "You have won! This time .....";
        setFightMessage(message);
        player.setExp(opponent.getExp());
        displayWinningScreen();
    }

    private void displayWinningScreen() {
        GameBoard.setVisible(false);
        endFightButton.setVisible(true);
        ArrayList<Cards> winningCards = generateWinningCards(3);
        ArrayList<AnchorPane> winCardContainer = createWinningCardContainer();
        displayCards(winningCards, winCardContainer);
        winningBoard.setVisible(true);
    }

    private ArrayList<AnchorPane> createWinningCardContainer() {
        ArrayList<AnchorPane> cardContainer = new ArrayList<>();
        Collections.addAll(cardContainer, winningCard0, winningCard1, winningCard2);
        return cardContainer;
    }

    private ArrayList<Cards> generateWinningCards(int cardsToGen) {
        ArrayList<Cards> listWinningCards = new ArrayList<>();
        for (int i = 0; i < cardsToGen; i++) {
            CardsType cardType = CardsType.getRandomeType();
            String imgCard = CardsCreator.imageCardCreator(cardType);
            Cards card = new Cards(imgCard, "Winning Card", null, cardType, CardRarity.genWinRandomCardRarity());
            listWinningCards.add(card);
        }
        this.winningCards = listWinningCards;
        return listWinningCards;
    }

    private void refreshCharacterAttributes(Cards card) {
        switch(card.getCardsType()){
            case DECREASE_ARMOR, POISON, ATTACK, SPELL, STUN, DISCARD -> displayActorInfo(opponent);
            default -> displayActorInfo(player);
        }
    }

    @FXML
    void endTurn(ActionEvent event) {
        hideCardsAfterEndTurn();
//        cardsField.setVisible(false);
        String message = "Now its next turn";
        setFightMessage(message);
        roundBeginning(opponent);
        opponentMove();
    }

    private void hideCardsAfterEndTurn() {
        for (int i = 0; i < cardsField.getChildren().size(); i++) {
            cardsField.getChildren().get(i).setVisible(false);
        }
    }

    private void opponentMove() {
        if (opponent.getStun() <= 0) {
            int attackRound = opponent.getAttackRound();
            for (int i = 0; i < attackRound; i++) {
                opponentAttackPhase();
                checkForWin();
                if (i == attackRound - 1) {
                    playerNewTurnToPlay();
                }
            }
        }else {
            opponent.setStun(-1);
            playerNewTurnToPlay();
        }
    }

    //TODO poprawić zagranie kart przy starcie rundy
    private void roundBeginning(Actor character) {
        checkCharacterStatus(character);
        displayActorInfo(character);
        rollDice.setText("*****  " + character.getName() + " Turn");
        checkForWin();
        displayOpponentCondition();
    }

    private void playerNewTurnToPlay() {
        roundBeginning(player);
        displayPlayerCondition();
        rollDice.setText("Roll Dices");
        wasRolled = false;
        drawCard = false;
        endTurn.setVisible(false);
        playerTurn = false;
    }

    private void opponentAttackPhase() {
        String attack = opponent.opponentChoseAttack();
        int value = opponent.opponentAttack(attack);
        String message = resolveOpponentAttack(attack, value);
        displayOpponentCondition();
        displayPlayerCondition();
        setFightMessage(message);
        displayActorInfo(player);
    }

    private String resolveOpponentAttack(String attack, int value) {
        switch (attack) {
            case "magic" -> {return player.takeMagicDamage(value);}
            case "poison" -> {return player.setPoison(new LifeChanger(opponent.getPower(), -value));}
            case "damage" -> {return player.takeDamage(value);}
        }
        return "";
    }

    private void checkCharacterStatus(Actor character) {
        character.resolveLifeChanger();
    }

    public Label getDiceSum() {
        return rollDice;
    }

    @FXML
    void cardBringFront(MouseEvent event) {
        AnchorPane source = (AnchorPane) event.getSource();
        source.toFront();
        source.setEffect(null);
    }

    @FXML
    void setCardOpacity(MouseEvent event) {
        AnchorPane source = (AnchorPane) event.getSource();
        source.toFront();
        ColorAdjust  colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.8);
        source.setEffect(colorAdjust);
    }

    /**
     * After clicking draw card button player draw cards and display it on card field
     * @param event - click on drawCard button
     */
    @FXML
    void drawCards(MouseEvent event) {
        if (!drawCard) {
            ArrayList<Cards> newHand = drawRandomCards();
            this.hand = newHand;
            ArrayList<AnchorPane> cardContainerList = createCardContainerList();
            displayCards(newHand, cardContainerList);
            cardsField.setVisible(true);
            drawCard = true;
            endTurn.setVisible(true);
        }
    }

    /**
     * put cards property (image, cost, description etc.) into cards container.
     * @param cardsToDisplay list of cards object, that player draw during draw stage
     */
    private void displayCards(ArrayList<Cards> cardsToDisplay, ArrayList<AnchorPane> gameCardsContainer ){
        for (int i = 0; i < cardsToDisplay.size(); i++) {
            AnchorPane container = gameCardsContainer.get(i);
            container.setVisible(true);
            container.setOpacity(1);
            container.setDisable(false);
            // set card image
            ImageView cardImage = (ImageView) container.getChildren().get(0);
            cardImage.setImage(new Image(cardsToDisplay.get(i).getImg()));

            //set card description
            Label cardDescription = (Label) container.getChildren().get(2);
            cardDescription.setText(cardsToDisplay.get(i).getDescription());

            // set card cost
            Label cardCost = (Label) container.getChildren().get(3);
            cardCost.setText(String.valueOf(cardsToDisplay.get(i).getCardCost()));
        }
    }

    /**
     *
     * @return create list card containers
     */
    private ArrayList<AnchorPane> createCardContainerList(){
        ArrayList<AnchorPane> cardContainer = new ArrayList<>();
        Collections.addAll(cardContainer, card0, card1, card2, card3, card4, card5);
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
     * @param card picked card
     * @return return message to display about card effect
     */
    private String resolveCardEffect(Cards card){
        switch (card.getCardsType()){
            case DECREASE_ARMOR -> {return opponent.setArmor(Math.max(opponent.getArmor() - card.getValue(), 0));}
            case RESISTANCE -> {return player.setResistance(card.getValue());}
            case DISPEL -> {return player.setDispel(card.getValue());}
            case POISON -> {return opponent.setPoison(new LifeChanger(player.getPower(), -card.getValue()));}
            case ATTACK -> {return opponent.takeDamage(card.getValue());}
            case DISCARD -> { handSizeModification -=1;
                return opponent.takeDamage(card.getValue());}
            case SPELL -> {return opponent.takeMagicDamage(card.getValue());}
            case ARMOR -> {return player.setArmor(card.getValue());}
            case HEAL -> {return player.setHeal(new LifeChanger(player.getPower(), card.getValue()));}
            case STUN -> {return opponent.setStun(player.getPower());}
        } return "";
    }

    private boolean canPlayCard(int roll, ArrayList<Cards> hand, int cardIndex){
        return roll >= hand.get(cardIndex).getCardCost();
    }

    /**
     * draw card mechanism
     * @return all cards that are in hand
     */
    private ArrayList<Cards> drawRandomCards(){
        Random random = new Random();
        int cardsOnHand = handSize + handSizeModification;
        handSizeModification = 0;
        ArrayList<Cards> hand = new ArrayList<>();
        ArrayList<Cards> deck = player.getPlayingDeck();
        for (int i = 0; i < cardsOnHand; i++) {
            if (deck.size() > cardsOnHand){
                int index =random.nextInt(deck.size());
                hand.add(deck.get(index));
                deck.remove(index);
            }
            else {
                player.setPlayingDeck();
                deck = player.getPlayingDeck();
                int index = random.nextInt(deck.size());
                hand.add(deck.get(index));
                deck.remove(index);
            }
        }
        return hand;
    }

    @FXML
    void endFight(ActionEvent event) {
        player.endFight();
        Platform.exit();
    }

    /**
     * simulate throwing cards
     * @param dices count of dice that player has
     * @return sum of all dice rolls
     */
    private int rollDice(int dices){
        Random random = new Random();
        int diceSum = 0;
        String message = "";
        for (int i = 0; i < dices; i++) {
            int score = random.nextInt(6)+1;
            diceSum += score;
            message = (i+1) + ". Dice roll = " + score + "\n";
            setFightMessage(message);
        }
        message = "You rolled " + diceSum + "\n";
        setFightMessage(message);
        return diceSum;
    }

    public void setDiceSum(String text){
        rollDice.setText(text);
    }


    @FXML
    private ImageView opponentHealIcon;

    @FXML
    private ImageView opponentPoisonIcon;

    @FXML
    private ImageView opponentStunIcon;

    @FXML
    private ImageView playerHealIcon;

    @FXML
    private ImageView playerPoisonIcon;

    @FXML
    private ImageView playerStunIcon;

    @FXML
    private ListView<String> matchHistory;

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
    private AnchorPane cardsField;

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

    @FXML
    private AnchorPane GameBoard;

    @FXML
    private AnchorPane winningBoard;

    @FXML
    private AnchorPane winningCard0;

    @FXML
    private AnchorPane winningCard1;

    @FXML
    private AnchorPane winningCard2;

    @FXML
    private Label winningCardCost0;

    @FXML
    private Label winningCardCost1;

    @FXML
    private Label winningCardCost2;

    @FXML
    private Label winningCardDescription0;

    @FXML
    private Label winningCardDescription01;

    @FXML
    private Label winningCardDescription02;

    @FXML
    private ImageView winningCardImage0;

    @FXML
    private ImageView winningCardImage1;

    @FXML
    private ImageView winningCardImage2;

    @FXML
    private ImageView winningCardbackground0;

    @FXML
    private ImageView winningCardbackground1;

    @FXML
    private ImageView winningCardbackground2;


    @FXML
    private Button endFightButton;

    @FXML
    private ImageView cardImage4;

    @FXML
    private ImageView cardImage5;

    @FXML
    private Label cardDescription4;

    @FXML
    private Label cardDescription5;

    @FXML
    private Label cardCost4;

    @FXML
    private Label cardCost5;

    @FXML
    private AnchorPane card4;

    @FXML
    private AnchorPane card5;

    @FXML
    private ImageView card1background4;

    @FXML
    private ImageView card1background5;

    @FXML
    void showCharacterNextRoundDmg(MouseEvent event) {
        ImageView image = (ImageView) event.getSource();
        switch (image.getId()){
            case "playerImage1"-> {
                if (opponent.getHealPts() > 0) {
                    healthInfoOpponent.setText(String.valueOf(opponent.getHealPts()));
                    healthInfoOpponent.setVisible(true);
                }
                if (opponent.getPoisonDmg() > 0) {
                    poisonInfoOpponent.setText(String.valueOf(opponent.getPoisonDmg()));
                    poisonInfoOpponent.setVisible(true);
                }
                if (opponent.getStun() > 0) {
                    stunInfoOpponent.setText(String.valueOf(opponent.getStun()));
                    stunInfoOpponent.setVisible(true);
                }
            }
            case "playerImage"-> {
                if (player.getStun() > 0) {
                    stunInfoPlayer.setText(String.valueOf(player.getStun()));
                    stunInfoPlayer.setVisible(true);
                }
                if (player.getPoisonDmg() > 0) {
                    poisonInfoPlayer.setText(String.valueOf(player.getPoisonDmg()));
                    poisonInfoPlayer.setVisible(true);
                }
                if (player.getHealPts() >0) {
                    healthInfoPlayer.setText(String.valueOf(player.getHealPts()));
                    healthInfoPlayer.setVisible(true);
                }
            }
        }
    }


    @FXML
    void hideCharacterNextRoundDmg(MouseEvent event) {
        ImageView image = (ImageView) event.getSource();
        switch (image.getId()){
            case "playerImage1"-> {
                healthInfoOpponent.setVisible(false);
                poisonInfoOpponent.setVisible(false);
                stunInfoOpponent.setVisible(false);
            }
            case "playerImage"-> {
                stunInfoPlayer.setVisible(false);
                poisonInfoPlayer.setVisible(false);
                healthInfoPlayer.setVisible(false);
            }
        }
    }
    @FXML
    private Label stunInfoOpponent;

    @FXML
    private Label stunInfoPlayer;

    @FXML
    private Label poisonInfoOpponent;

    @FXML
    private Label poisonInfoPlayer;

    @FXML
    private Label healthInfoOpponent;

    @FXML
    private Label healthInfoPlayer;

}
