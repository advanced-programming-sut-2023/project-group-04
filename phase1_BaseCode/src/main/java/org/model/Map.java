package org.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private static String[] allGroundTextures = {"GROUND", "SANDY_GROUND", "STONE_GROUND", "IRON_GROUND", "GRASSLAND",
            "LOW_MEADOW", "HIGH_MEADOW", "OIL", "PLAIN", "LOW_WATER", "RIVER", "SMALL_POND", "LARGE_POND", "BEACH",
            "SEA"};
    private static String[] allTrees = {"LITTLE_CHERRY", "LARGE_CHERRY", "OLIVE", "COCONUT", "DATE"};
    private static ArrayList<Map> allMaps;
    private static Map currentMap;
    private int id;
    private String[][] mapTexture;
    private String[][] mapTrees;

    public Map(int id, int mapSize) {
        currentMap = this;
        allMaps.add(this);
        this.mapTexture = new String[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++)
            for (int j = 0; j < mapSize; j++)
                mapTexture[i][j] = "GROUND";
        this.mapTrees = new String[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++)
            for (int j = 0; j < mapSize; j++)
                mapTrees[i][j] = null;
    }

    public static Map getMapById(int id) {
        for (Map map1 : allMaps)
            if (map1.id == id)
                return map1;
        return null;
    }

    public static Map getCurrentMap() {
        return currentMap;
    }

    public String[][] getMap() {
        return mapTexture;
    }

    public String getBlockTexture(int x, int y) {
        return mapTexture[x][y];
    }

    public int getMapSize() {
        return this.mapTexture.length;
    }

    public void setBlockTexture(int x, int y, String groundTexture) {
        this.mapTexture[x-1][y-1] = groundTexture;
    }

    public static void setCurrentMap(Map currentMap) {
        Map.currentMap = currentMap;
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

    public void setTree(int x, int y, String treeType) {
        this.mapTrees[x-1][y-1] = treeType;
    }
}
