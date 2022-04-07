package com.example.dungeaoncrawler;

import com.example.dungeaoncrawler.logic.WorldMap;
import com.example.dungeaoncrawler.logic.actors.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class HelloApplication {

    public static Player player;
    static WorldMap worldMap;
    static HelloController helloController;
    Stage stage;

    public HelloApplication(Player player) {
        HelloApplication.player = player;
    }

    public void loadSavedGame() {
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
        loadGame();
    }

    public void loadNewGame() {
        worldMap = new WorldMap(1);
        stage = new Stage();
        loadGame();
    }

    public void loadGame() {
        try {
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