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

    public String showMapDetail(int x, int y) {
        Game currentGame = Game.getCurrentGame();
        MapCell[][] map = currentGame.getMap();
        MapCell mapCell = map[x][y];
        HashMap<String, Integer> mySoldiers = getMySoldiers(currentGame.getCurrentEmpire().getOwner(), mapCell);
        HashMap<String, Integer> enemiesSoldiers = getEnemiesSoldiers(currentGame.getCurrentEmpire().getOwner(), mapCell);
        Building building = mapCell.getBuilding();
        String mapDetails = "";
        mapDetails += "Ground texture :  \"" + mapCell.getGroundTexture().toLowerCase() + "\"\n";
        mapDetails += "Your Soldiers :  \n";
        for (String key : mySoldiers.keySet())
            if (mySoldiers.get(key) != 0)
                mapDetails += key + ": {" + mySoldiers.get(key) + "}\n";
        mapDetails += "Enemies Soldiers :  \n";
        for (String key : enemiesSoldiers.keySet())
            if (enemiesSoldiers.get(key) != 0)
                mapDetails += key + ": {" + enemiesSoldiers.get(key) + "}\n";
        if (building != null)
            mapDetails += "Building :  " + building.getName() + ", owner :  "
                    + building.getBuildingOwner().getOwner().getNickname()
                    + ", building hp :  " + building.getCurrentHp();
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
            if (soldier.getPersonOwner().getOwner().equals(owner))
                soldiersType.put(soldier.getSoldierName(), soldiersType.get(soldier.getSoldierName()) + 1);
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
