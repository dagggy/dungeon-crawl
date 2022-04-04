package com.example.dungeaoncrawler;

import com.example.dungeaoncrawler.logic.*;
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

import static com.example.dungeaoncrawler.HelloApplication.worldMap;
import static com.example.dungeaoncrawler.HelloApplication.player;

public class HelloController {
    ImageView imageView = new ImageView("img.png");
    ImageView imageView1 = new ImageView("img.png");



    public void initialize() {
        printMap(worldMap);
        player.move(3,5);
        printMap(worldMap);
    }

    @FXML
    private GridPane gridMap;

//    @FXML
//    private Button printButton;

    @FXML
    private GridPane actorMap;

    private final Image tileset = new Image("mapObjects.png", 577 * 2, 577 * 2, true, false);

    private void printMap(WorldMap worldMap) {
        gridMap.getChildren().clear();

        GameMap map = worldMap.getGameMap(worldMap.getCurrentPos()[0],worldMap.getCurrentPos()[1]);
        gridMap.setHgap(0);
        gridMap.setVgap(0);

        for (int i = 0; i < gridMap.getColumnCount(); i++) {
            for (int j = 0; j < gridMap.getRowCount(); j++) {

                int[] currCell = map.getCell(i, j).getCellImageCoords();

                ImageView imageView = ImageHandler.getTile(tileset, currCell[0], currCell[1]);
                imageView.setFitWidth(32);
                imageView.setFitHeight(32);
                gridMap.add(imageView,i,j);
            }
        }

        System.out.println(gridMap.getChildren().size());
        System.out.println(worldMap);
    }
}
