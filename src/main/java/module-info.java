module com.example.dungeaoncrawler {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dungeaoncrawler to javafx.fxml;
    exports com.example.dungeaoncrawler;
    exports com.example.dungeaoncrawler.logic;
    opens com.example.dungeaoncrawler.logic to javafx.fxml;
}