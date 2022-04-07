package com.example.dungeaoncrawler.logic;

public enum CellType {
    EMPTY(1, 1, "empty", false),
    WALL(7, 12, "wall", false),
    CLOSED_DOOR(8, 11, "closedDoor", true),
    OPEN_DOOR(9, 11, "openDoor", false),
    KEY(17, 24, "key", true),
    HEALTH(27, 23, "health", true),
    POWER(27, 24, "power", true),
    ARMOR(2, 24, "armor", true),
    CARD(21, 17, "card", true),
    TRAPDOOR(22, 1, "trapdoor", true),
    GRAVE(24, 1, "grave", false),
    P1(17,14, "p1", false),
    P2(18, 14, "p2", false),
    P3(19, 14, "p3", false),
    P4(19, 15, "p4", false),
    P5(19, 16, "p5", false),
    KRZYSIEK(32, 32, "dev", true),
    BARTEK(31, 32, "dev", true),
    KUBA(30, 32, "dev", true);

    private final int x;
    private final int y;
    private final String tileName;
    private final boolean isInteractable;

    CellType(int x, int y, String tileName, boolean isInteractable) {
        this.x = x;
        this.y = y;
        this.tileName = tileName;
        this.isInteractable = isInteractable;
    }

    public int[] getCellImageCoords() {
        return new int[]{x, y};
    }
    public String getTileName() {
        return tileName;
    }
    public boolean getInteractableStatus () {
        return isInteractable;
    }
}
