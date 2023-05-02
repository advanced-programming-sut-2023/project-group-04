package org.controller;

import org.model.Game;
import org.model.MapCell;

import java.util.regex.Matcher;

public class MapController {
    public String showMap(Matcher matcher) {
        MapCell[][] map = Game.getCurrentGame().getMap();
        int xAsis = Integer.parseInt(matcher.group("xAsis"));
        int yAsis = Integer.parseInt(matcher.group("yAsis"));
        int x = xAsis;
        int y = yAsis;
        String mapData = "";
        String ANSI_RESET = "\033[0m", ANSI_COLOR_BACKGROUND;
        for (int i = 0; i < 29; i++) { // 7*3 + 8
            for (int j = 0; j < 29; j++) {
                if (j % 4 == 0 && j != 0)
                    y++;
                if (i % 4 == 0) {
                    mapData += "-";
                }
                else if (j % 4 == 0) {
                    mapData += "|";
                }
                else {
                    ANSI_COLOR_BACKGROUND = getColor(map[x][y]);
                    mapData += ANSI_COLOR_BACKGROUND;
                    mapData += getObjectChar(map[x][y]);
                    mapData += ANSI_RESET;
                }
                if (j == 28)
                    y = yAsis;
            }
            mapData += "\n";
            if (i % 4 == 0 && i != 0) {
                xAsis++;
                x = xAsis;
            }
        }
        return mapData;
    }

    private String getObjectChar(MapCell mapCell) {
        if (mapCell.getSoldiers().size() != 0)
            return "S";
        else if (mapCell.getBuilding() != null)
            return "B";
        else if (mapCell.getTree() != null)
            return "T";
        return "#";
    }

    private String getColor(MapCell mapCell) {
        String groundTexture = mapCell.getGroundTexture();
        if (groundTexture.equals("GROUND") || groundTexture.equals("SANDY_GROUND") || groundTexture.equals("BEACH"))
            return "\033[43m"; // yellow
        else if (groundTexture.equals("STONE_GROUND") || groundTexture.equals("OIL"))
            return "\033[40m"; // black
        else if (groundTexture.equals("IRON_GROUND"))
            return "\033[41m"; // red
        else if (groundTexture.equals("GRASSLAND") || groundTexture.equals("LOW_MEADOW")
                || groundTexture.equals("HIGH_MEADOW"))
            return "\033[42m"; // green
        else if (groundTexture.equals("LOW_WATER") || groundTexture.equals("RIVER")
                || groundTexture.equals("SMALL_POND") || groundTexture.equals("LARGE_POND")
                || groundTexture.equals("SEA"))
            return "\033[44m"; // blue
        else if (groundTexture.equals("PLAIN"))
            return "\033[45m"; // purple
        return null;
    }

    public String changeMapView(Matcher matcher) {
        return null;
    }

    public String showMapDetail(Matcher matcher) {
        return null;
    }
}
