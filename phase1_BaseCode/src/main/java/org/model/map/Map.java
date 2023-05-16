package org.model.map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.model.Player;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static ArrayList<Map> allMaps = new ArrayList<>();

    public Map(String mapName, int mapSize) {
        allMaps.add(this);
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

    public static Map getMapByName(String name) {
        for (Map map : allMaps)
            if (map.getMapName().equals(name))
                return map;
        return null;
    }

    public static ArrayList<Map> getAllMaps() {
        return allMaps;
    }

    public static void setAllMaps(ArrayList<Map> allMaps) {
        Map.allMaps = allMaps;
    }

    public static void recoveryMaps() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Path.of("MAPS.json"));
            ArrayList<Map> allMaps = new ArrayList<>();
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
            if (jsonArray != null) {
                for (JsonElement jsonElement : jsonArray) {
                    allMaps.add(gson.fromJson(jsonElement, Map.class));
                }
                Map.allMaps = allMaps;
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void saveMaps() {
        Gson gson = new Gson();
        String data = gson.toJson(Map.getAllMaps());
        try {
            FileWriter output = new FileWriter(Path.of("MAPS.json").toFile());
            output.write(data);
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
