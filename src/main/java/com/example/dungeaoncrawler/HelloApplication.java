package com.example.dungeaoncrawler;

import com.example.dungeaoncrawler.logic.WorldMap;
import com.example.dungeaoncrawler.logic.actors.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication {
    public static Player player;
    static WorldMap worldMap;

    public void loadNewGame() {
        try {
            player = new Player(100, 0, 0,4, null);
            worldMap = new WorldMap(1);
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(UserPanel.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            HelloController helloController = fxmlLoader.getController();
            stage.setTitle("Dungeon Crawl");
            stage.setScene(scene);
            stage.show();
            scene.setOnKeyPressed(helloController::onKeyPressed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}