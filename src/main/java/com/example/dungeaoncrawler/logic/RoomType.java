package com.example.dungeaoncrawler.logic;

public enum RoomType {
    SPAWN('p'),
    NORMAL('n'),
    SHOP('c'),
    SPECIAL('s'),
    LAST('l'),
    FINAL('f');

    private final char symbol;

    RoomType (char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
