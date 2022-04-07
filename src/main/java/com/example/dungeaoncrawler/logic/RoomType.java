package com.example.dungeaoncrawler.logic;

public enum RoomType {

    SPAWN("S"),
    NORMAL("■"),
    SHOP("■"),
    SPECIAL("I"),
    LAST("L"),
    FINAL("F"),
    ENDING("E");

    private final String symbol;

    RoomType (String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
