package org.controller;

import org.model.Game;
import org.model.Map;
import org.view.CommandsEnum.MapMenuMessages;

import java.util.regex.Matcher;

public class EnvironmentController {
    public MapMenuMessages createMap(Matcher matcher) {
        int id = Integer.parseInt(matcher.group("id"));
        int size = Integer.parseInt(matcher.group("size"));
        if (size != 200 && size != 400) return MapMenuMessages.INVALID_SIZE;
        new Map(id, size);
        return MapMenuMessages.MAP_CREATION_SUCCESSFUL;
    }

    public MapMenuMessages chooseExistingMap(Matcher matcher) {
        int id = Integer.parseInt(matcher.group("id"));
        Map map = Map.getMapById(id);
        if (map == null) return MapMenuMessages.MAP_NOT_EXIST;
        Map.setCurrentMap(map);
        return MapMenuMessages.MAP_SELECT_SUCCESSFUL;
    }
    public MapMenuMessages changeBlockTexture(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        return null;
    }

    public MapMenuMessages changeAreaTexture(Matcher matcher) {
        return null;
    }

    public MapMenuMessages clearBlock(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        return null;
    }

    public MapMenuMessages setRock(Matcher matcher) {
        return null;
    }

    public MapMenuMessages setTree(Matcher matcher) {
        return null;
    }

    private boolean correctCoordinate(int x, int y) {
        int size = Map.getCurrentMap().getMap().length
        return (x <= size || y <= size);

    }

}
