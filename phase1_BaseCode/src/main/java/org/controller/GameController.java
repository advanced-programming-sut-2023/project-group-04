package org.controller;

import org.model.Empire;
import org.model.Game;
import org.model.Map;
import org.model.MapCell;
import org.model.buildings.*;
import org.view.CommandsEnum.GameMessages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class GameController {
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

    public GameMessages changeFoodRate(Matcher matcher) {
        //TODO : ADD LOGIC TO CHECK FOOD RATE ; SET AUTO -2;
        Game.getCurrentGame().getCurrentEmpire().setFoodRate(Integer.parseInt(matcher.group("foodRate")));
        return GameMessages.CHANGE_FOOD_RATE;
    }

    public String showFoodRate() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        return "your food rate is : <<" + empire.getFoodRate() + ">>\n";
    }

    public GameMessages changeTaxRate(Matcher matcher) {
        //TODO : ADD LOGIC TO CHECK TAX RATE ; SET AUTO 0;
        Game.getCurrentGame().getCurrentEmpire().setTaxRate(Integer.parseInt(matcher.group("taxRate")));
        return GameMessages.CHANGE_TAX_RATE;
    }

    public String showTaxRate() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        return "your tax rate is : <<" + empire.getTaxRate() + ">>\n";
    }

    public GameMessages changeFearRate(Matcher matcher) {
        //TODO : ADD LOGIC TO CHECK FEAR RATE ; SET AUTO -2;
        Game.getCurrentGame().getCurrentEmpire().setFearRate(Integer.parseInt(matcher.group("fearRate")));
        return GameMessages.CHANGE_FEAR_RATE;
    }

    public String showFearRate() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        return "your fear rate is : <<" + empire.getFearRate() + ">>\n";
    }

    public GameMessages dropBuilding(Matcher matcher) {
        //TODO: STRUCTURAL BUILDINGS MUST HAVE A DIRECTION FIELD....DIRECTION MUST BE OPTION FIELD IN DROPBUILDING
        //TODO: CHANGE RETURN TYPE AND RETURNS -----> **ABOLFAZL**
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String buildingName = matcher.group("type");
        //todo: handle direction
        if (!checkCoordinates(x, y)) return GameMessages.INVALID_POSITION;
        if (Game.getCurrentGame().getMapCellByAddress(x, y).getBuilding() != null)
            return GameMessages.EXISTENCE_BUILDING;
        if (BuildingsDictionary.getDictionaryByName(buildingName) == null)
            return GameMessages.INCORRECT_BUILDING_TYPE;
        //TODO: CHECK GROUND TEXTURE
        return createBuilding(empire, x, y, "", buildingName);//todo: handle direction
        //TODO: DRAW BRIDGE ONLY CAN BE PLACED IN FRONT OF GATEHOUSES
    }

    private GameMessages createBuilding(Empire empire, int x, int y, String direction, String buildingName) {
        ProductiveBuildingsDictionary productiveBuildingDictionary;
        StorageBuildingsDictionary storageBuildingDictionary;
        StructuralBuildingsDictionary structuralBuildingDictionary;
        TowerBuildingsDictionary towerBuildingDictionary;
        TrainerBuildingsDictionary trainerBuildingDictionary;
        TrapBuildingsDictionary trapBuildingDictionary;
        Building building = null;
        HashMap<String, Integer> prices = null;
        if ((productiveBuildingDictionary = ProductiveBuildingsDictionary.getDictionaryByName(buildingName)) != null) {
            prices = productiveBuildingDictionary.getBuildingDictionary().getPrices();
            building = new ProductiveBuilding(empire, productiveBuildingDictionary);
        } else if ((storageBuildingDictionary = StorageBuildingsDictionary.getDictionaryByName(buildingName)) != null) {
            prices = storageBuildingDictionary.getBuildingDictionary().getPrices();
            building = new StorageBuilding(empire, storageBuildingDictionary);
        } else if ((structuralBuildingDictionary = StructuralBuildingsDictionary.getDictionaryByName(buildingName)) != null) {
            prices = structuralBuildingDictionary.getBuildingDictionary().getPrices();
            building = new StructuralBuilding(empire, structuralBuildingDictionary, direction);
        } else if ((towerBuildingDictionary = TowerBuildingsDictionary.getDictionaryByName(buildingName)) != null) {
            prices = towerBuildingDictionary.getBuildingDictionary().getPrices();
            building = new TowerBuilding(empire, towerBuildingDictionary);
        } else if ((trainerBuildingDictionary = TrainerBuildingsDictionary.getDictionaryByName(buildingName)) != null) {
            prices = trainerBuildingDictionary.getBuildingDictionary().getPrices();
            building = new TrainerBuilding(empire, trainerBuildingDictionary);
        } else if ((trapBuildingDictionary = TrapBuildingsDictionary.getDictionaryByName(buildingName)) != null) {
            prices = trapBuildingDictionary.getBuildingDictionary().getPrices();
            building = new TrapBuilding(empire, trapBuildingDictionary);
        } else {
            BuildingsDictionary buildingDictionary = BuildingsDictionary.getDictionaryByName(buildingName);
            prices = buildingDictionary.getPrices();
            building = new Building(empire, buildingDictionary);
        }
        if (!buyBuilding(empire, prices)) return GameMessages.NOT_ENOUGH_RESOURCE;
        Game.getCurrentGame().getMapCellByAddress(x, y).setBuilding(building);
        initBuildings(building);
        return GameMessages.SUCCESSFUL_DROP;
    }

    private void initBuildings(Building building) {
        if (building.getBuildingDictionary().equals(BuildingsDictionary.SMALL_STONE_GATEHOUSE) |
                building.getBuildingDictionary().equals(BuildingsDictionary.LARGE_STONE_GATEHOUSE))
            Game.getCurrentGame().getCurrentEmpire().activateTaxRate();
    }

    private boolean buyBuilding(Empire empire, HashMap<String, Integer> prices) {
        for (String resource : prices.keySet())
            if (empire.getResourceAmount(resource) < prices.get(resource)) return false;
        for (String resource : prices.keySet()) {
            empire.ChangeResourceAmount(resource, -(prices.get(resource)));
            for (StorageBuilding storageBuilding : empire.getAllStockPiles()) {
                int resourceAmountInStorage = storageBuilding.getResourceAmount(resource);
                if (resourceAmountInStorage <= prices.get(resource)) {
                    prices.put(resource, prices.get(resource) - resourceAmountInStorage);
                    storageBuilding.changeResourcesAmount(resource, -resourceAmountInStorage);
                } else {
                    storageBuilding.changeResourcesAmount(resource, -prices.get(resource));
                    break;
                }
            }
        }
        return true;
    }

    public GameMessages selectBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!checkCoordinates(x, y)) return GameMessages.INVALID_POSITION;
        Building building = Game.getCurrentGame().getMapCellByAddress(x, y).getBuilding();
        if (building == null)
            return GameMessages.BUILDING_NOT_EXIST;
        if (!building.getBuildingOwner().equals(Game.getCurrentGame().getCurrentEmpire().getOwner()))
            return GameMessages.NOT_OWNING_THE_BUILDING;
        Game.getCurrentGame().setSelectedBuilding(building);
        return GameMessages.SET_SUCCESSFUL;
    }

    public GameMessages repair(Matcher matcher) {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        Building selectedBuilding = Game.getCurrentGame().getSelectedBuilding();
        if (!selectedBuilding.getType().equals(BuildingType.CASTLE))
            return GameMessages.CAN_NOT_REPAIR;
        HashMap<String, Integer> requirementMaterial = new HashMap<>();
        HashMap<String, Integer> prices = selectedBuilding.getPrices();
        int currentHp = selectedBuilding.getCurrentHp();
        int basicHp = selectedBuilding.getBasicHp();
        float requirementPercentage = (float) (currentHp / basicHp);
        for (String key : prices.keySet())
            requirementMaterial.put(key, (int) (prices.get(key) * requirementPercentage));
        if (!buyBuilding(empire, requirementMaterial)) return GameMessages.NOT_ENOUGH_RESOURCE;
        selectedBuilding.repair();
        return GameMessages.SUCCESSFUL_REPAIR;
    }

    public String createUnit(Matcher matcher) {
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

    private boolean checkCoordinates(int x, int y) {
        int mapSize = Map.getCurrentMap().getMapSize();
        return x > 0 && x <= mapSize && y > 0 && y <= mapSize;
    }

}