package org.controller;

import org.model.buildings.BuildingsDictionary;
import org.model.map.Map;
import org.model.Player;
import org.model.map.MapTile;
import org.model.person.SoldiersDictionary;
import org.view.CommandsEnum.MapMenuMessages;

import java.util.Random;
import java.util.regex.Matcher;

public class EnvironmentController {
    public MapMenuMessages createMap(Matcher matcher) {
        String mapName = matcher.group("mapName");
        int size = Integer.parseInt(matcher.group("size"));
        if (size != 200 && size != 400) return MapMenuMessages.INVALID_SIZE;
        Map map = new Map(mapName, size);
        Player.getCurrentPlayer().addMap(map);
        return MapMenuMessages.MAP_CREATION_SUCCESSFUL;
    }

    public MapMenuMessages customExistingMap(Matcher matcher) {
         String mapName = matcher.group("mapName");
        Map map = Player.getCurrentPlayer().getMapByName(mapName);
        if (map == null) return MapMenuMessages.MAP_NOT_EXIST;
        Map.setCurrentMap(map);
        return MapMenuMessages.MAP_SELECT_SUCCESSFUL;
    }

    public MapMenuMessages changeBlockTexture(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        if (!checkCoordinate(x,y)) return MapMenuMessages.INCORRECT_COORDINATES;
        if (!Map.validGroundTexture(type)) return MapMenuMessages.INVALID_GROUND_TEXTURE;
        MapTile mapTile = Map.getCurrentMap().getMapTile(x,y);
        if (mapTile.getTree() != null) return MapMenuMessages.TREE_EXIST;
        mapTile.setTexture(type);
        return MapMenuMessages.SET_TEXTURE_SUCCESSFUL;
    }

    public MapMenuMessages changeAreaTexture(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        String type = matcher.group("type");
        if (!checkCoordinate(x1,y1) || !checkCoordinate(x2,y2) || x2 < x1 || y2 < y1)
            return MapMenuMessages.INCORRECT_COORDINATES;
        if (!Map.validGroundTexture(type)) return MapMenuMessages.INVALID_GROUND_TEXTURE;
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                MapTile mapTile = Map.getCurrentMap().getMapTile(i,j);
                if (mapTile.getTree() != null) return MapMenuMessages.TREE_EXIST;
                mapTile.setTexture(type);
            }
        }
        return MapMenuMessages.SET_TEXTURE_SUCCESSFUL;
    }

    public MapMenuMessages clearBlock(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!checkCoordinate(x,y)) return MapMenuMessages.INCORRECT_COORDINATES;
        MapTile mapTile = Map.getCurrentMap().getMapTile(x,y);
        mapTile.setTexture("GROUND");
        mapTile.setTree(null);
        return MapMenuMessages.TILE_CLEAR_SUCCESSFUL;
    }

    public MapMenuMessages setRock(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String direction = matcher.group("direction");
        if (!checkCoordinate(x,y)) return MapMenuMessages.INCORRECT_COORDINATES;
        if ((direction = getDirection(direction)) == null) return MapMenuMessages.INVALID_DIRECTION;
        MapTile mapTile = Map.getCurrentMap().getMapTile(x,y);
        if (!mapTile.getTexture().equals("GROUND"))
            return MapMenuMessages.ROCK_GROUND_TEXTURE_ERROR;
        if (mapTile.getTree() != null) return MapMenuMessages.TREE_EXIST;
        mapTile.setTexture("ROCK-"+direction);
        return MapMenuMessages.ROCK_SET_SUCCESSFUL;
    }

    public MapMenuMessages setTree(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        if (!checkCoordinate(x,y)) return MapMenuMessages.INCORRECT_COORDINATES;
        if (!Map.validTreeType(type)) return MapMenuMessages.INVALID_TREE_TYPE;
        if (!checkValidTreeTexture(x,y)) return MapMenuMessages.TREE_GROUND_TEXTURE_ERROR;
        MapTile mapTile = Map.getCurrentMap().getMapTile(x,y);
        if (mapTile.getTree() != null) return MapMenuMessages.TREE_EXIST;
        mapTile.setTree(type);
        return MapMenuMessages.SET_TREE_SUCCESSFUL;
    }

    public MapMenuMessages setHeadQuarter(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String color = matcher.group("color");
        if (!checkCoordinate(x,y)) return MapMenuMessages.INCORRECT_COORDINATES;
        if (!Map.isValidColor(color)) return MapMenuMessages.INVALID_COLOR;
        if (Map.getCurrentMap().getAllColors().contains(color)) return MapMenuMessages.USED_COLOR;
        MapTile mapTile = Map.getCurrentMap().getMapTile(x,y);
        if (mapTile.isHeadQuarter() || mapTile.getBuilding() != null || mapTile.getSoldier() != null)
            return MapMenuMessages.USED_TILE;
        if (!mapTile.getTexture().equals("GROUND")) return MapMenuMessages.INVALID_GROUND_TEXTURE;
        if (mapTile.getTree() != null) return MapMenuMessages.TREE_EXIST;
        mapTile.setOwnerColor(color);
        mapTile.setHeadQuarter();
        Map.getCurrentMap().addUsedColor(color);
        return MapMenuMessages.HEADQUARTER_SET;
    }

    public MapMenuMessages dropUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String color = matcher.group("color");
        String soldierType = matcher.group("type");
        int count = Integer.parseInt(matcher.group("count"));
        if (!checkCoordinate(x,y)) return MapMenuMessages.INCORRECT_COORDINATES;
        if (!Map.isValidColor(color)) return MapMenuMessages.INVALID_COLOR;
        MapTile mapTile = Map.getCurrentMap().getMapTile(x,y);
        if (mapTile.isHeadQuarter() || mapTile.getBuilding() != null || mapTile.getSoldier() != null)
            return MapMenuMessages.USED_TILE;
        if (!mapTile.getTexture().equals("GROUND")) return MapMenuMessages.INVALID_GROUND_TEXTURE;
        if (count <= 0) return MapMenuMessages.INVALID_SOLDIER_NUMBER;
        if (SoldiersDictionary.getSoldierDictionaryByName(soldierType) == null)
            return MapMenuMessages.INVALID_SOLDIER_TYPE;
        mapTile.setOwnerColor(color);
        mapTile.setSoldier(soldierType, count);
        return MapMenuMessages.DROP_UNIT_SUCCESS;
    }

    public MapMenuMessages dropBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String color = matcher.group("color");
        String buildingType = matcher.group("type");
        if (!checkCoordinate(x,y)) return MapMenuMessages.INCORRECT_COORDINATES;
        if (!Map.isValidColor(color)) return MapMenuMessages.INVALID_COLOR;
        MapTile mapTile = Map.getCurrentMap().getMapTile(x,y);
        if (mapTile.isHeadQuarter() || mapTile.getBuilding() != null || mapTile.getSoldier() != null)
            return MapMenuMessages.USED_TILE;
        if (!mapTile.getTexture().equals("GROUND")) return MapMenuMessages.INVALID_GROUND_TEXTURE;
        if (mapTile.getTree() != null) return MapMenuMessages.TREE_EXIST;
        if (BuildingsDictionary.getDictionaryByName(buildingType) == null)
            return MapMenuMessages.INVALID_BUILDING_TYPE;
        mapTile.setOwnerColor(color);
        mapTile.setBuilding(buildingType);
        return MapMenuMessages.DROP_BUILDING_SUCCESS;
    }

    private boolean checkCoordinate(int x, int y) {
        int size = Map.getCurrentMap().getMapSize();
        return (x <= size && x >= 1 && y <= size && y>= 1);
    }

    private String getDirection(String direction) {
        String[] directions = {"UP","DOWN","RIGHT","LEFT"};
        switch (direction) {
            case "n":
                return "UP";
            case "e":
                return "RIGHT";
            case "w":
                return "DOWN";
            case "s":
                return "LEFT";
            case "r":
                Random random = new Random();
                return directions[random.nextInt(4)];
            default:
                return null;
        }
    }

    private boolean checkValidTreeTexture(int x, int y) {
        String texture = Map.getCurrentMap().getMapTile(x,y).getTexture();
        return texture.equals("GROUND") ||
               texture.equals("GRASSLAND") ||
               texture.equals("LOW_MEADOW") ||
               texture.equals("HIGH_MEADOW");
    }

    public void save() {
        Player.savePlayers();
    }
}
