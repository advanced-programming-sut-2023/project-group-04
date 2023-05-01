package org.controller;

import org.model.Empire;
import org.model.Game;
import org.model.Map;
import org.model.MapCell;
import org.model.buildings.Building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class GameController {
    //TODO : CREATE GAME MESSAGES AND REPLACE RETURN TYPE OF SOME FUNCTIONS;
    public String showPopularityFactors() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        HashMap<String, Integer> popularityFactors = empire.getPopularity();
        String popularity = "<<popularity factors>>\n";
        for (String key : popularityFactors.keySet()) {
            popularity += key + " :" + popularityFactors.get(key) + "\n";
        }
        return popularity;
    }

    public String showPopularity() {
        int popularitySum = 0;
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        HashMap<String, Integer> popularityFactors = empire.getPopularity();
        for (String key : popularityFactors.keySet()) {
            popularitySum += popularityFactors.get(key);
        }
        return "your popularity is: <<" + popularitySum + ">>";
    }

    public String showFoodList() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        HashMap<String, Integer> foodList = empire.getFood();
        String foods = "<<food list>> :\n";
        for (String key : foodList.keySet()) {
            foods += key + ": " + foodList.get(key) + "\n";
        }
        return foods;
    }

    public String changeFoodRate(Matcher matcher) {
        //TODO : ADD LOGIC TO CHECK FOOD RATE ; SET AUTO -2;
        Game.getCurrentGame().getCurrentEmpire().setFoodRate(Integer.parseInt(matcher.group("foodRate")));
        return "Food rate changed successfully";
    }

    public String showFoodRate() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        return "your food rate is : <<" + empire.getFoodRate() + ">>\n";
    }

    public String changeTaxRate(Matcher matcher) {
        //TODO : ADD LOGIC TO CHECK TAX RATE ; SET AUTO 0;
        Game.getCurrentGame().getCurrentEmpire().setTaxRate(Integer.parseInt(matcher.group("taxRate")));
        return "Tax rate changed successfully";
    }

    public String showTaxRate() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        return "your tax rate is : <<" + empire.getTaxRate() + ">>\n";
    }

    public String changeFearRate(Matcher matcher) {
        //TODO : ADD LOGIC TO CHECK FEAR RATE ; SET AUTO -2;
        Game.getCurrentGame().getCurrentEmpire().setFearRate(Integer.parseInt(matcher.group("fearRate")));
        return "Fear rate changed successfully";
    }

    public String showFearRate() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        return "your fear rate is : <<" + empire.getFearRate() + ">>\n";
    }

    public String dropBuilding(Matcher matcher) {
        return null;
    }

    public String selectBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        int mapSize = Map.getCurrentMap().getMapSize();
        if (x <= 0 || x > mapSize || y <= 0 || y > mapSize)
            return null;
        Building building = Game.getCurrentGame().getMapCellByAddress(x, y).getBuilding();
        if (building == null)
            return null;

        if (building.getBuildingOwner().equals(Game.getCurrentGame().getCurrentEmpire()))
            return null;

        Game.getCurrentGame().setSelectedBuilding(building);
        return null;
    }

    public String createUnit(Matcher matcher) {
        return null;
    }

    public String repair(Matcher matcher) {
        Building selectedBuilding = Game.getCurrentGame().getSelectedBuilding();
        if (!selectedBuilding.getType().equals("Castle Buildings"))
            return null;

        HashMap<String, Integer> requirementMaterial = new HashMap<>();
        HashMap<String, Integer> prices = selectedBuilding.getPrices();
        int currentHp = selectedBuilding.getCurrentHp();
        int basicHp = selectedBuilding.getBasicHp();
        Float requirementPercentage = (float) (currentHp / basicHp);
        for (String key : prices.keySet()) {
            Integer cost = prices.get(key);
            //requirementMaterial.put(key,(Integer)(cost * requirementPercentage));
        }
        return null;
//        if ()
    }

    public String selectUnit(Matcher matcher) {
        return null;
    }

    public String moveUnit(Matcher matcher) {
        return null;
    }

    public String patrolUnit(Matcher matcher) {
        return null;
    }

    public String setUnitMode(Matcher matcher) {
        return null;
    }

    public String groundAttack(Matcher matcher) {
        return null;
    }

    public String airAttack(Matcher matcher) {
        return null;
    }

    public String pourOil(Matcher matcher) {
        return null;
    }

    public String digTunnel(Matcher matcher) {
        return null;
    }

    public String engineerBuild(Matcher matcher) {
        return null;
    }

    public String disbandUnit(Matcher matcher) {
        return null;
    }

    public String nextTurn() {
        return null;
    }

    private ArrayList<MapCell> routing(int originX, int originY, int destinationX, int destinationY) {
        return null;
    }


}