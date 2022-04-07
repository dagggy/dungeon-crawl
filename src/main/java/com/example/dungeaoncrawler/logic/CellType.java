package com.example.dungeaoncrawler.logic;

public enum CellType {
    EMPTY(1, 1, "empty"),
    WALL(7, 12, "wall"),
    CLOSED_DOOR(8, 11, "closedDoor"),
    OPEN_DOOR(9, 11, "openDoor"),
    KEY(17, 24, "key"),
    HEALTH(27, 23, "health"),
    POWER(27, 24, "power"),
    ARMOR(2, 24, "armor"),
    CARD(21, 17, "card"),
    TRAPDOOR(22, 1, "trapdoor"),
    GRAVE(24, 1, "grave"),
    P1(17,14, "p1"),
    P2(18, 14, "p2"),
    P3(19, 14, "p3"),
    P4(19, 15, "p4"),
    P5(19, 16, "p5"),
    KRZYSIEK(32, 32, "krzysiek"),
    BARTEK(31, 32, "bartek"),
    KUBA(30, 32, "kuba");

    private final int x;
    private final int y;
    private final String tileName;

    CellType(int x, int y, String tileName) {
        this.x = x;
        this.y = y;
        this.tileName = tileName;
    }

    public int[] getCellImageCoords() {
        return new int[]{x, y};
    }
    public String getTileName() {
        return tileName;
    }
}
