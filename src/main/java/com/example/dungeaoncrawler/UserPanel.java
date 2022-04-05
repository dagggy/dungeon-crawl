package com.example.dungeaoncrawler;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserPanel extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-panel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @FXML
    private TextField idUserName;

    public static String userName;

    @FXML
    void startGame(ActionEvent event) {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.loadNewGame();
    }

    @FXML
    void loadPreviousGame(ActionEvent event) {

    }

    public String getIdUserName() {
        return userName = idUserName.getText();
    }
}
