package com.example.dungeaoncrawler;

import com.example.dungeaoncrawler.logic.*;
import com.example.dungeaoncrawler.logic.actors.Actor;
import com.example.dungeaoncrawler.logic.actors.Player;
import com.example.dungeaoncrawler.logic.actors.Skeleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;


import java.time.Instant;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static com.example.dungeaoncrawler.HelloApplication.worldMap;
import static com.example.dungeaoncrawler.HelloApplication.player;

public class HelloController {
    ImageView imageView = new ImageView("img.png");
    ImageView imageView1 = new ImageView("img.png");



    @FXML
    private GridPane gridMap;

    @FXML
    private GridPane actorMap;

    public void initialize() throws IOException {
        printMap();
        printMinimap();

    }



    private final Image tileset = new Image("mapObjects.png", 577 * 2, 577 * 2, true, false);

    private void printMap() {
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

    }

    public void printMinimap () {
        System.out.println(worldMap);
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP -> {
                player.move(0, -1);
                printMap();
            }
            case DOWN -> {
                player.move(0, 1);
                printMap();
            }
            case LEFT -> {
                player.move(-1, 0);
                printMap();
            }
            case RIGHT -> {
                player.move(1, 0);
                printMap();
            }
            case A -> {
                saveGame();
            }
            case S -> {
                loadGame();
                printMap();
                printMinimap();
            }
        }
    }


    private void loadGame () {
        try {
            FileInputStream loadStream = new FileInputStream("SAVE.sav");
            ObjectInputStream loadData = new ObjectInputStream(loadStream);

            worldMap = (WorldMap) loadData.readObject();
            player = (Player) loadData.readObject();

            loadData.close();
            loadStream.close();
        } catch (FileNotFoundException f) {
            f.printStackTrace();
            System.out.println(f + ": File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e + ": Object Error");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveGame () {
        try {
            File saveFile = new File("SAVE.sav");
            FileOutputStream saveStream = new FileOutputStream(saveFile);
            ObjectOutputStream saveData = new ObjectOutputStream(saveStream);

            saveData.writeObject(worldMap);
            saveData.writeObject(player);

            saveData.close();
            saveStream.close();
            System.out.println(saveFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(": Error while saving: file not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getEnemyMove(Actor enemy) {
        int[][] possibleMoves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int[] randomPair = possibleMoves[new Random().nextInt(possibleMoves.length)];
        enemy.move(randomPair[0], randomPair[1]);
    }
}
