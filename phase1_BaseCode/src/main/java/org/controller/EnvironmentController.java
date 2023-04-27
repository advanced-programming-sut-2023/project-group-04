package org.controller;

import org.model.Map;
import org.model.Player;
import org.view.CommandsEnum.MapMenuMessages;

import java.util.Random;
import java.util.regex.Matcher;

public class EnvironmentController {
    public MapMenuMessages createMap(Matcher matcher) {
        int id = Integer.parseInt(matcher.group("mapId"));
        int size = Integer.parseInt(matcher.group("size"));
        if (size != 200 && size != 400) return MapMenuMessages.INVALID_SIZE;
        Map map = new Map(id, size);
        Player.getCurrentPlayer().addMap(map);
        return MapMenuMessages.MAP_CREATION_SUCCESSFUL;
    }

    public MapMenuMessages chooseExistingMap(Matcher matcher) {
        int id = Integer.parseInt(matcher.group("id"));
        Map map = Player.getCurrentPlayer().getMapById(id);
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
        Map.getCurrentMap().setBlockTexture(x,y, type);
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
        for (int i = x1; i < x2; i++)
            for (int j = y1; j < y2; j++)
                Map.getCurrentMap().setBlockTexture(i, j, type);
        return MapMenuMessages.SET_TEXTURE_SUCCESSFUL;
    }

    public MapMenuMessages clearBlock(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!checkCoordinate(x,y)) return MapMenuMessages.INCORRECT_COORDINATES;
        Map.getCurrentMap().setBlockTexture(x,y, "GROUND");
        return MapMenuMessages.TILE_CLEAR_SUCCESSFUL;
    }

    public MapMenuMessages setRock(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String direction = matcher.group("direction");
        if (!checkCoordinate(x,y)) return MapMenuMessages.INCORRECT_COORDINATES;
        if ((direction = getDirection(direction)) == null) return MapMenuMessages.INVALID_DIRECTION;
        if (!Map.getCurrentMap().getBlockTexture(x,y).equals("GROUND")) return MapMenuMessages.ROCK_GROUND_TEXTURE_ERROR;
        Map.getCurrentMap().setBlockTexture(x, y, "ROCK-"+direction);
        return MapMenuMessages.ROCK_SET_SUCCESSFUL;
    }

    public MapMenuMessages setTree(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        if (!checkCoordinate(x,y)) return MapMenuMessages.INCORRECT_COORDINATES;
        if (!Map.validTreeType(type)) return MapMenuMessages.INVALID_TREE_TYPE;
        if (!checkValidTreeTexture(x,y)) return MapMenuMessages.TREE_GROUND_TEXTURE_ERROR;
        Map.getCurrentMap().setTree(x, y, type);
        return MapMenuMessages.SET_TREE_SUCCESSFUL;
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
        String texture = Map.getCurrentMap().getBlockTexture(x,y);
        return texture.equals("GROUND") ||
               texture.equals("GRASSLAND") ||
               texture.equals("LOW_MEADOW") ||
               texture.equals("HIGH_MEADOW");
    }


    public void save() {
        Player.savePlayers();
    }
}
