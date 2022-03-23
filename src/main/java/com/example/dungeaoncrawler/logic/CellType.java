package com.example.dungeaoncrawler.logic;

public enum CellType {
    EMPTY(0, 0),
    FLOOR(2, 0),
    WALL(6, 11);

    private final int x;
    private final int y;

    CellType(int x, int y) {
        this.x = x;
        this.y = y;
    }

//    public String getTileName() {
//        return tileName;
//    }
}
