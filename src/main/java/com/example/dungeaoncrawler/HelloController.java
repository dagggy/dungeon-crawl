package com.example.dungeaoncrawler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HelloController {
    ImageView imageView = new ImageView("img.png");
    ImageView imageView1 = new ImageView("img.png");

    ImageView whatever = ImageHandler.getTile(3, 2);

    @FXML
    private GridPane gridMap;

    @FXML
    private Button printButton;

    @FXML
    private GridPane actorMap;

    @FXML
    void printMap(ActionEvent event) {
        Image dog = new Image("img.png");
        gridMap.setHgap(0);
        gridMap.setVgap(0);
        for (int i = 0; i < gridMap.getColumnCount(); i++) {
            for (int j = 0; j < gridMap.getRowCount(); j++) {
                ImageView imageView = new ImageView(dog);
                imageView.setFitWidth(32);
                imageView.setFitHeight(32);
                gridMap.add(imageView, i, j);
            }
        }
        actorMap.add(whatever, 0, 0);
    }
}
