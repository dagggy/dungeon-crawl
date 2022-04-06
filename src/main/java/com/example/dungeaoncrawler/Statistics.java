package com.example.dungeaoncrawler;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Statistics {

    private String StatisticsName;
    private int StatisticsPoints;

    public Statistics (String statisticsName, int statisticsPoints) {
        StatisticsName = statisticsName;
        StatisticsPoints = statisticsPoints;
    }

    public StringProperty getStatisticsName() {
        return new SimpleStringProperty(StatisticsName);
    }

    public void setStatisticsName(String statisticsName) {
        StatisticsName = statisticsName;
    }

    public IntegerProperty getStatisticsPoints() {
        return new SimpleIntegerProperty(StatisticsPoints);
    }

    public void setStatisticsPoints(int statisticsPoints) {
        StatisticsPoints = statisticsPoints;
    }
}
