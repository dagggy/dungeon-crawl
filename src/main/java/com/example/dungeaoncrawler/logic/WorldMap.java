package com.example.dungeaoncrawler.logic;

import com.example.dungeaoncrawler.logic.actors.Player;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.dungeaoncrawler.HelloApplication.player;

/**A war crime*/
public class WorldMap {
    private final int floor;
    private int currMapX;
    private int currMapY;
    private final int worldWidth = 11;
    private final int worldHeight = 11;
    private final GameMap[][] gameMaps = new GameMap[worldWidth][worldHeight];
    private final ArrayList<GameMap> gameMapStorage = new ArrayList<>();



    /** WorldMap constructor
     * @param floor Might be used in the future, numerical representation on the current floor (in case we go deeper) */
    public WorldMap (int floor) {
        this.floor = floor;
        placeSpawn();
        this.currMapX = gameMapStorage.get(0).getWorldPos().get(0);
        this.currMapY = gameMapStorage.get(0).getWorldPos().get(1);

        Random random = new Random();

        int normalRoomCount = random.nextInt(8, 15);
        for (int i = 0; i < normalRoomCount; i++) {
            placeMap(RoomType.NORMAL);
        }

        int specialRoomCount = random.nextInt(3, 6);
        for (int i = 0; i < specialRoomCount; i++) {
            placeMap(RoomType.SPECIAL);
        }

        placeMap(RoomType.LAST);
    }

    /** Grab GameMap from WorldMap on specified coordinates
     * @param x X location of the GameMap on the WorldMap
     * @param y Y location of the GameMap on the WorldMap*/
    public GameMap getGameMap (int x, int y) {
        return gameMaps[x][y];
    }

    public int[] getCurrentPos () {
        return new int[]{currMapX, currMapY};
    }


    /** Places the spawn GameMap in the center of the WorldMap*/
    private void placeSpawn () {

        GameMap map = MapLoader.loadMap(RoomType.SPAWN, worldWidth/2, worldHeight/2, this);
        map.getCell(map.getWidth()/2, map.getHeight()/2).setActor(player);
        player.setCell(map.getCell(map.getWidth()/2, map.getHeight()/2));
        gameMaps[worldWidth/2][worldHeight/2] = map;
        gameMapStorage.add(map);

    }


    /** Places GameMap on the WorldMap
     * @param roomType Used to specify what type of the room we're placing*/
    private void placeMap (RoomType roomType) {
        ArrayList<ArrayList<ArrayList<Integer>>> possibleRoomPositions = getPossibleRoomPositions();
        ArrayList<ArrayList<Integer>> selectedCoordinates = possibleRoomPositions.get(ThreadLocalRandom.current().nextInt(0, possibleRoomPositions.size()));

        GameMap map = MapLoader.loadMap(roomType, selectedCoordinates.get(0).get(0), selectedCoordinates.get(0).get(1), this);

        if (Objects.equals(map.getWorldPos().get(0), selectedCoordinates.get(1).get(0)) && Objects.equals(map.getWorldPos().get(1)-1, selectedCoordinates.get(1).get(1))) {
            gameMaps[selectedCoordinates.get(1).get(0)][selectedCoordinates.get(1).get(1)].addDoor('r');
            map.addDoor('l');
        } else if (Objects.equals(map.getWorldPos().get(0), selectedCoordinates.get(1).get(0)) && Objects.equals(map.getWorldPos().get(1)+1, selectedCoordinates.get(1).get(1))) {
            gameMaps[selectedCoordinates.get(1).get(0)][selectedCoordinates.get(1).get(1)].addDoor('l');
            map.addDoor('r');
        } else if (Objects.equals(map.getWorldPos().get(0)-1, selectedCoordinates.get(1).get(0)) && Objects.equals(map.getWorldPos().get(1), selectedCoordinates.get(1).get(1))) {
            gameMaps[selectedCoordinates.get(1).get(0)][selectedCoordinates.get(1).get(1)].addDoor('d');
            map.addDoor('u');
        } else {
            gameMaps[selectedCoordinates.get(1).get(0)][selectedCoordinates.get(1).get(1)].addDoor('u');
            map.addDoor('d');
        }

        gameMaps[map.getWorldPos().get(0)][map.getWorldPos().get(1)] = map;
        gameMapStorage.add(map);
    }


    /**Gets possible room positions for the current world map.
     * It will not allow for a room to be adjacent to a room it's not connected to.
     * I sincerely hope no one ever reads this code.
     * @return List of lists of pairs for room options and where they connect to*/
    private ArrayList<ArrayList<ArrayList<Integer>>> getPossibleRoomPositions () {
        ArrayList<ArrayList<ArrayList<Integer>>> possibleRoomPositions = new ArrayList<>();
        for (GameMap gameMap:gameMapStorage) {
            ArrayList<Integer> mapPos = gameMap.getWorldPos();
            for (int i = 0; i < 2; i++) {
                for (int j = -1; j < 2; j=j+2) {
                    int x = mapPos.get(0);
                    int y = mapPos.get(1);
                    if (gameMaps[x+j][y] == null) {
                        if (x + j >= 0 && x + j < worldWidth) {
                            if (y-1 >=0 && y+1 < worldHeight && x+j-1 >= 0 && x+j+1 < worldWidth) {
                                if ((gameMaps[x + j][y - 1] == null || gameMaps[x + j][y - 1] == gameMap) &&
                                        (gameMaps[x + j][y + 1] == null || gameMaps[x + j][y + 1] == gameMap) &&
                                        (gameMaps[x + j - 1][y] == null || gameMaps[x + j - 1][y] == gameMap) &&
                                        (gameMaps[x + j + 1][y] == null || gameMaps[x + j + 1][y] == gameMap)
                                ) {
                                    ArrayList<Integer> coordsToCheck = new ArrayList<>();
                                    coordsToCheck.add(x + j);
                                    coordsToCheck.add(y);
                                    ArrayList<ArrayList<Integer>> totalCoords = new ArrayList<>();
                                    totalCoords.add(coordsToCheck);
                                    totalCoords.add(gameMap.getWorldPos());
                                    possibleRoomPositions.add(totalCoords);
                                }
                            }
                        }
                    }
                    if (gameMaps[x][y+j] == null) {
                        if (y+j >= 0 && y+j < worldHeight) {
                            if (y+j-1 >=0 && y+j+1 < worldHeight && x-1 >= 0 && x+1 < worldWidth) {
                                if ((gameMaps[x - 1][y + j] == null || gameMaps[x - 1][y + j] == gameMap) &&
                                        (gameMaps[x + 1][y + j] == null || gameMaps[x + 1][y + j] == gameMap) &&
                                        (gameMaps[x][y + j - 1] == null || gameMaps[x][y + j - 1] == gameMap) &&
                                        (gameMaps[x][y + j + 1] == null || gameMaps[x][y + j + 1] == gameMap)
                                ) {
                                    ArrayList<Integer> coordsToCheck = new ArrayList<>();
                                    coordsToCheck.add(x);
                                    coordsToCheck.add(y + j);
                                    ArrayList<ArrayList<Integer>> totalCoords = new ArrayList<>();
                                    totalCoords.add(coordsToCheck);
                                    totalCoords.add(gameMap.getWorldPos());
                                    possibleRoomPositions.add(totalCoords);
                                }
                            }
                        }
                    }
                }
            }
        }
        return possibleRoomPositions;
    }

    public int getCurrMapX() {
        return currMapX;
    }

    public int getCurrMapY() {
        return currMapY;
    }

    /**Move to a different map in the map storage, place player on given position*/
    public Cell mapMove (int x, int y, int pPosX, int pPosY, Player player) {
        this.currMapY += y;
        this.currMapX += x;
        Cell nextCell = getGameMap(currMapX, currMapY).getCell(pPosX, pPosY);
        nextCell.setActor(player);
        return nextCell;
    }

    /** neat little toString() override :)*/
    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (int x=0; x < worldWidth; x++) {
            for (int y=0; y < worldHeight; y++) {
                GameMap room = gameMaps[x][y];
                if (room != null) {
                    returnString.append(room.getRoomType().getSymbol()).append("  ");
                } else {
                    returnString.append("   ");
                }
            }
            returnString.append("\n");
        }
        return returnString.toString();
    }
}