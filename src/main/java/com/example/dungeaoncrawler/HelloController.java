package com.example.dungeaoncrawler;

import com.example.dungeaoncrawler.logic.Cell;
import com.example.dungeaoncrawler.logic.CellType;
import com.example.dungeaoncrawler.logic.GameMap;
import com.example.dungeaoncrawler.logic.MapLoader;
import com.example.dungeaoncrawler.logic.actors.Player;
import com.example.dungeaoncrawler.logic.actors.Skeleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.InputStream;
import java.time.Instant;
import java.util.Scanner;

public class HelloController {
    ImageView imageView = new ImageView("img.png");
    ImageView imageView1 = new ImageView("img.png");




    @FXML
    private GridPane gridMap;

    @FXML
    private Button printButton;

    @FXML
    private GridPane actorMap;

    @FXML
    void printMap(ActionEvent event) {
//        Image tileset = new Image("tiles.png");
        ImageHandler imageHandler = new ImageHandler();
        Image dog = new Image("mapObjects.png", 543 * 2, 543 * 2, true, false);
        ImageView whatever = imageHandler.getTile(dog, 4, 2);

        gridMap.setHgap(0);
        gridMap.setVgap(0);
        for (int i = 0; i < gridMap.getColumnCount(); i++) {
            for (int j = 0; j < gridMap.getRowCount(); j++) {
                ImageView imageView = imageHandler.getTile(dog, 4, 2);
                imageView.setFitWidth(32);
                imageView.setFitHeight(32);
                gridMap.add(imageView,i,j);
            }
        }
        Rectangle b = new Rectangle(32,16,Color.BLACK);
        actorMap.add(whatever, 0, 0);
        System.out.println(actorMap.getChildren());
    }
}
