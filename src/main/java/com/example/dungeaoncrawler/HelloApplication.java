package com.example.dungeaoncrawler;

import com.example.dungeaoncrawler.logic.WorldMap;
import com.example.dungeaoncrawler.logic.actors.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication {
    public static Player player;
    static WorldMap worldMap;
    static HelloController helloController;
    Stage stage;

    public HelloApplication(Player player) {
        HelloApplication.player = player;
    }

    public void loadNewGame() {
        try {
            worldMap = new WorldMap(1);
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(UserPanel.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            HelloController helloController = fxmlLoader.getController();
            HelloApplication.helloController = helloController;
            stage.setTitle("Dungeon Crawl");
            stage.setOnCloseRequest(e -> helloController.closeWindow());
            stage.setScene(scene);
            stage.show();

            scene.setOnKeyPressed(helloController::onKeyPressed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}