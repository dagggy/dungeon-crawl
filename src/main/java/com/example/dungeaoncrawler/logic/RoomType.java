package com.example.dungeaoncrawler.logic;

public enum RoomType {
    SPAWN("p"),
    NORMAL("■"),
    SHOP("■"),
    SPECIAL("l"),
    LAST("s"),
    FINAL("■");

    private final String symbol;

    RoomType (String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
