package org.controller;

import org.model.Game;
import org.model.MapCell;
import org.model.Player;
import org.model.buildings.Building;
import org.model.person.Soldier;
import org.view.CommandsEnum.GameMessages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class MapController {
    public String showMap(int xPos, int yPos) {
        Game currentGame = Game.getCurrentGame();
        MapCell[][] map = currentGame.getMap();
        if (xPos < 0 || yPos < 0 ||xPos > map.length - 8 || yPos > map.length - 8)
            return GameMessages.INVALID_POSITION.getMessage();
        currentGame.setMapFirstX(xPos);
        currentGame.setMapFirstY(yPos);
        int x = xPos;
        int y = yPos;
        String mapData = "";
        String ANSI_RESET = "\033[0m", ANSI_COLOR_BACKGROUND;
        for (int i = 0; i < 29; i++) { // 7*3 + 8
            for (int j = 0; j < 29; j++) {
                if (j % 4 == 0 && j != 0)
                    x++;
                if (i % 4 == 0) {
                    mapData += "-";
                } else if (j % 4 == 0) {
                    mapData += "|";
                } else {
                    ANSI_COLOR_BACKGROUND = getColor(map[x][y]);
                    mapData += ANSI_COLOR_BACKGROUND;
                    mapData += getObjectChar(map[x][y]);
                    mapData += ANSI_RESET;
                }
                if (j == 28)
                    x = xPos;
            }
            mapData += "\n";
            if (i % 4 == 0 && i != 0) {
                yPos++;
                y = yPos;
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
        return "\033[43m";
    }

    public String changeMapView(Matcher matcher) {
        int x = getXPos(matcher);
        int y = getYPos(matcher);
        return showMap(x, y);
    }

    private int getXPos(Matcher matcher) {
        int x = Game.getCurrentGame().getMapFirstX();
        if (matcher.group("right") != null) {
            if (matcher.group("rightNumber") != null)
                x += Integer.parseInt(matcher.group("rightNumber"));
            else x += 1;
        }
        if (matcher.group("left") != null) {
            if (matcher.group("leftNumber") != null)
                x -= Integer.parseInt(matcher.group("leftNumber"));
            else x -= 1;
        }
        return x;
    }

    private int getYPos(Matcher matcher) {
        int y = Game.getCurrentGame().getMapFirstY();
        if (matcher.group("down") != null) {
            if (matcher.group("downNumber") != null)
                y += Integer.parseInt(matcher.group("downNumber"));
            else y += 1;
        }
        if (matcher.group("up") != null) {
            if (matcher.group("upNumber") != null)
                y -= Integer.parseInt(matcher.group("upNumber"));
            else y -= 1;
        }
        return y;
    }

    public String showMapDetail(Matcher matcher) {
        int xAsis = Integer.parseInt(matcher.group("xAsis")) - 1;
        int yAsis = Integer.parseInt(matcher.group("yAsis")) - 1;
        Game currentGame = Game.getCurrentGame();
        MapCell[][] map = currentGame.getMap();
        if (xAsis >= map.length || yAsis >= map.length)
            return GameMessages.INVALID_POSITION.getMessage();
        MapCell mapCell = map[xAsis][yAsis];
        HashMap<String, Integer> mySoldiers = getMySoldiers(currentGame.getCurrentEmpire().getOwner(), mapCell);
        HashMap<String, Integer> enemiesSoldiers = getEnemiesSoldiers(currentGame.getCurrentEmpire().getOwner(), mapCell);
        Building building = mapCell.getBuilding();
        String mapDetails = "<<Details>>:\n";
        mapDetails += "Ground texture : \"" + mapCell.getGroundTexture() + "\"\n";
        mapDetails += "<<Your Soldiers>>:\n";
        for (String key : mySoldiers.keySet()) {
            if (mySoldiers.get(key) != 0) {
                mapDetails += key + ": {" + mySoldiers.get(key) + "}\n";
            }
        }
        mapDetails += "<<Enemies Soldiers>>:\n";
        for (String key : enemiesSoldiers.keySet()) {
            if (enemiesSoldiers.get(key) != 0) {
                mapDetails += key + ": {" + enemiesSoldiers.get(key) + "}\n";
            }
        }
        if (building != null)
            mapDetails += "<<Building>>:\n" + building.getName() + ": owner: " + building.getBuildingOwner().getOwner().getNickname()
                    + " ,building hp: {" + building.getCurrentHp() + "}";
        return mapDetails;
    }

    private HashMap<String, Integer> getEnemiesSoldiers(Player owner, MapCell mapCell) {
        HashMap<String, Integer> soldiersType = getAllSoldiersType();
        ArrayList<Soldier> soldiers = mapCell.getSoldiers();
        for (Soldier soldier : soldiers) {
            if (!soldier.getPersonOwner().getOwner().equals(owner)) {
                soldiersType.put(soldier.getSoldierName(), soldiersType.get(soldier.getSoldierName()) + 1);
            }
        }
        return soldiersType;
    }

    private HashMap<String, Integer> getMySoldiers(Player owner, MapCell mapCell) {
        HashMap<String, Integer> soldiersType = getAllSoldiersType();
        ArrayList<Soldier> soldiers = mapCell.getSoldiers();
        for (Soldier soldier : soldiers) {
            if (soldier.getPersonOwner().getOwner().equals(owner)) {
                soldiersType.put(soldier.getSoldierName(), soldiersType.get(soldier.getSoldierName()) + 1);
            }
        }
        return soldiersType;
    }

    private HashMap<String, Integer> getAllSoldiersType() {
        HashMap<String, Integer> soldiers = new HashMap<>();
        soldiers.put("archer", 0);
        soldiers.put("crossbow man", 0);
        soldiers.put("spearman", 0);
        soldiers.put("pike man", 0);
        soldiers.put("mace man", 0);
        soldiers.put("swordsman", 0);
        soldiers.put("knight", 0);
        soldiers.put("tunneler", 0);
        soldiers.put("ladder man", 0);
        soldiers.put("engineer", 0);
        soldiers.put("blackmonks", 0);
        soldiers.put("arabian bow", 0);
        soldiers.put("slave", 0);
        soldiers.put("slinger", 0);
        soldiers.put("assassin", 0);
        soldiers.put("horse archer", 0);
        soldiers.put("arabian swordsman", 0);
        soldiers.put("fire thrower", 0);
        return soldiers;
    }
}
