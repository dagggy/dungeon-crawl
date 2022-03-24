package com.example.dungeaoncrawler;

import com.example.dungeaoncrawler.logic.items.Items;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class Test extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("test.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Fight!");
        stage.setScene(scene);
        stage.show();

    }







    @FXML
    private Label FightMassage;

    @FXML
    private ListView<?> PlayerInventory;

    @FXML
    private ListView<?> PlayerInventory1;

    @FXML
    private ListView<?> PlayerStatus;

    @FXML
    private ListView<?> PlayerStatus1;

    @FXML
    private AnchorPane card1;

    @FXML
    private AnchorPane card11;

    @FXML
    private AnchorPane card12;

    @FXML
    private AnchorPane card13;

    @FXML
    private Label card1Cost;

    @FXML
    private Label card1Cost1;

    @FXML
    private Label card1Cost2;

    @FXML
    private Label card1Cost3;

    @FXML
    private ImageView card1background;

    @FXML
    private ImageView card1background1;

    @FXML
    private ImageView card1background2;

    @FXML
    private ImageView card1background3;

    @FXML
    private Label cardDescription1;

    @FXML
    private Label cardDescription11;

    @FXML
    private Label cardDescription12;

    @FXML
    private Label cardDescription13;

    @FXML
    private ImageView cardImage1;

    @FXML
    private ImageView cardImage2;

    @FXML
    private ImageView cardImage3;

    @FXML
    private ImageView cardImage4;

    @FXML
    private ImageView playerImage;

    @FXML
    private ImageView playerImage1;

    @FXML
    private ImageView windowBackground;


}
