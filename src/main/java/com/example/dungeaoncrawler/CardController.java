package com.example.dungeaoncrawler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CardController {

    @FXML
    private Label description;

    @FXML
    private Label cardCost;

    @FXML
    private ImageView cardBg;

    @FXML
    private ImageView cardImage;

    CardController(String description, int cardCost, ImageView cardBg, ImageView cardImage) {
        this.description.setText(description);
        this.cardCost.setText(String.valueOf(cardCost));
        this.cardBg = cardBg;
        this.cardImage = cardImage;
    }
}
