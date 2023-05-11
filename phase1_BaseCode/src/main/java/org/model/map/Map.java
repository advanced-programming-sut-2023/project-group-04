package org.model.map;

import org.model.person.Soldier;

import java.util.ArrayList;

public class Map {
    private static final String[] allGroundTextures = {"GROUND", "SANDY_GROUND", "STONE_GROUND", "IRON_GROUND", "GRASSLAND",
            "LOW_MEADOW", "HIGH_MEADOW", "OIL", "PLAIN", "LOW_WATER", "RIVER", "SMALL_POND", "LARGE_POND", "BEACH", "SEA"};
    private static final String[] allTrees = {"LITTLE_CHERRY", "LARGE_CHERRY", "OLIVE", "COCONUT", "DATE"};
    private static final String[] availableColors = {"red", "green", "blue", "yellow", "purple","orange", "white","black"};
    private ArrayList<String> allColors = new ArrayList<>();
    private static Map currentMap;
    private final String mapName;
    private final MapTile[][] map;

    public Map(String mapName, int mapSize) {
        currentMap = this;
        this.mapName = mapName;
        this.map = new MapTile[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++)
            for (int j = 0; j < mapSize; j++)
                map[i][j] = new MapTile();
    }

    public static Map getCurrentMap() {
        return currentMap;
    }

    public static void setCurrentMap(Map currentMap) {
        Map.currentMap = currentMap;
    }

    public String getMapName() {
        return mapName;
    }

    public int getMapSize() {
        return map.length;
    }

    public MapTile getMapTile(int i, int j) {
        return map[i][j];
    }

    public ArrayList<String> getAllColors() {
        return allColors;
    }

    public void addUsedColor(String color) {
        this.allColors.add(color);
    }

    public static boolean validGroundTexture(String groundTexture) {
        for (String ground : allGroundTextures)
            if (groundTexture.equals(ground))
                return true;
        return false;
    }

    public static boolean validTreeType(String treeType) {
        for (String tree : allTrees)
            if (treeType.equals(tree))
                return true;
        return false;
    }

    public static boolean isValidColor(String color) {
        for (String colorName : availableColors)
            if (colorName.equals(color))
                return true;
        return false;
    }

}
