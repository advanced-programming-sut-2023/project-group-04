package org.controller;

import org.model.Empire;
import org.model.Game;
import org.model.Machine.Machine;
import org.model.Machine.MachinesDictionary;
import org.model.MapCell;
import org.model.Player;
import org.model.buildings.*;
import org.model.person.*;
import org.view.CommandsEnum.GameMessages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Matcher;

public class GameController {
    public String showResources() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        String output = "";
        for (String resource : empire.getResources().keySet()) {
            output += resource + " : " + empire.getAvailableResource(resource) + "\n";
        }
        for (String resource : empire.getFood().keySet()) {
            output += resource + " : " + empire.getAvailableResource(resource) + "\n";
        }
        for (String resource : empire.getWeaponAndArmour().keySet()) {
            output += resource + " : " + empire.getAvailableResource(resource) + "\n";
        }
        return output;
    }

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

    public GameMessages changeFoodRate(Matcher matcher) {
        int foodRate = Integer.parseInt(matcher.group("foodRate"));
        if (foodRate < -2 || foodRate > 2)
            return GameMessages.INVALID_FOOD_RATE;
        Game.getCurrentGame().getCurrentEmpire().setFoodRate(foodRate);
        return GameMessages.CHANGE_FOOD_RATE;
    }

    public String showFoodRate() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        return "your food rate is : <<" + empire.getFoodRate() + ">>\n";
    }

    public GameMessages changeTaxRate(Matcher matcher) {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        int taxRate = Integer.parseInt(removeQuotation(matcher.group("taxRate")));
        if (empire.getTaxRate() == -100) return GameMessages.TAX_NOT_ACTIVE;
        if (taxRate < -3 || taxRate > 8)
            return GameMessages.INVALID_TAX_RATE;
        empire.setTaxRate(taxRate);
        return GameMessages.CHANGE_TAX_RATE;
    }

    public String showTaxRate() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        return "your tax rate is : <<" + empire.getTaxRate() + ">>\n";
    }

    public GameMessages changeFearRate(Matcher matcher) {
        int fearRate = Integer.parseInt(removeQuotation(matcher.group("fearRate")));
        if (fearRate < -5 || fearRate > 5)
            return GameMessages.INVALID_FEAR_RATE;
        Game.getCurrentGame().getCurrentEmpire().setFearRate(fearRate);
        return GameMessages.CHANGE_FEAR_RATE;
    }

    public String showFearRate() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        return "your fear rate is : <<" + empire.getFearRate() + ">>\n";
    }

    public GameMessages dropBuilding(Matcher matcher) {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        if (matcher.group("x") == null || matcher.group("y") == null || matcher.group("type") == null)
            return GameMessages.EMPTY_FIELD;
        int x = Integer.parseInt(removeQuotation(matcher.group("x"))) - 1;
        int y = Integer.parseInt(removeQuotation(matcher.group("y"))) - 1;
        String buildingName = removeQuotation(matcher.group("type"));
        boolean upDirection = false;
        if (matcher.group("direction") != null)
            upDirection = removeQuotation(matcher.group("direction")).equals("up");
        if (!checkCoordinates(x, y)) return GameMessages.INVALID_POSITION;
        MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
        if (mapCell.getBuilding() != null) return GameMessages.EXISTENCE_BUILDING;
        if (BuildingsDictionary.getDictionaryByName(buildingName) == null) return GameMessages.INCORRECT_BUILDING_TYPE;
        if (!checkGroundTexture(mapCell, buildingName)) return GameMessages.INVALID_TEXTURE_TREE;
        if (buildingName.equals("draw bridge") && !checkDrawBridge(x, y, upDirection))
            return GameMessages.INVALID_DRAWBRIDGE_POSITION;
        return createBuilding(empire, x, y, buildingName, upDirection);
    }

    private boolean checkGroundTexture(MapCell mapCell, String buildingName) {
        String texture = removeQuotation(mapCell.getGroundTexture());
        String[] invalidTextures = {"OIL", "PLAIN", "LOW_WATER", "RIVER", "SMALL_POND", "LARGE_POND", "SEA"};
        for (String invalidTexture : invalidTextures)
            if (texture.equals(invalidTexture)) return false;
        if (texture.contains("ROCK") || (mapCell.getTree() != null && !mapCell.getTree().equals("LITTLE_CHERRY")))
            return false;
        ProductiveBuildingsDictionary dictionary = ProductiveBuildingsDictionary.getDictionaryByName(buildingName);
        if (dictionary == null) return true;
        else if (dictionary.getAcceptableTexture() == null) return true;
        else if (dictionary.getAcceptableTexture().contains(texture)) return true;
        else return false;
    }

    private boolean checkDrawBridge(int x, int y, boolean upDirection) {
        Building[] nearBuildings = new Building[]{
                Game.getCurrentGame().getMapCellByAddress(x, y - 1).getBuilding(),
                Game.getCurrentGame().getMapCellByAddress(x, y + 1).getBuilding(),
                Game.getCurrentGame().getMapCellByAddress(x + 1, y).getBuilding(),
                Game.getCurrentGame().getMapCellByAddress(x - 1, y).getBuilding(),
        };
        for (int i = 0; i < nearBuildings.length; i++)
            if (nearBuildings[i].getBuildingDictionary().equals(BuildingsDictionary.SMALL_STONE_GATEHOUSE) ||
                    nearBuildings[i].getBuildingDictionary().equals(BuildingsDictionary.LARGE_STONE_GATEHOUSE))
                if (((i == 0 || i == 1) && upDirection && ((StructuralBuilding) nearBuildings[i]).isUpside()) ||
                        ((i == 2 || i == 3) && !upDirection && !((StructuralBuilding) nearBuildings[i]).isUpside()))
                    return true;
        return false;
    }

    private GameMessages createBuilding(Empire empire, int x, int y, String buildingName, boolean upDirection) {
        ProductiveBuildingsDictionary productiveBuildingDictionary;
        StorageBuildingsDictionary storageBuildingDictionary;
        StructuralBuildingsDictionary structuralBuildingDictionary;
        TowerBuildingsDictionary towerBuildingDictionary;
        TrainerBuildingsDictionary trainerBuildingDictionary;
        TrapBuildingsDictionary trapBuildingDictionary;
        Building building = null;
        HashMap<String, Integer> prices = null;
        MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
        if ((productiveBuildingDictionary = ProductiveBuildingsDictionary.getDictionaryByName(buildingName)) != null) {
            prices = productiveBuildingDictionary.getBuildingDictionary().getPrices();
            building = new ProductiveBuilding(empire, productiveBuildingDictionary, mapCell);
        } else if ((storageBuildingDictionary = StorageBuildingsDictionary.getDictionaryByName(buildingName)) != null) {
            prices = storageBuildingDictionary.getBuildingDictionary().getPrices();
            building = new StorageBuilding(empire, storageBuildingDictionary, mapCell);
        } else if ((structuralBuildingDictionary = StructuralBuildingsDictionary.getDictionaryByName(buildingName)) != null) {
            prices = structuralBuildingDictionary.getBuildingDictionary().getPrices();
            building = new StructuralBuilding(empire, structuralBuildingDictionary, upDirection, mapCell);
        } else if ((towerBuildingDictionary = TowerBuildingsDictionary.getDictionaryByName(buildingName)) != null) {
            prices = towerBuildingDictionary.getBuildingDictionary().getPrices();
            building = new TowerBuilding(empire, towerBuildingDictionary, mapCell);
        } else if ((trainerBuildingDictionary = TrainerBuildingsDictionary.getDictionaryByName(buildingName)) != null) {
            prices = trainerBuildingDictionary.getBuildingDictionary().getPrices();
            building = new TrainerBuilding(empire, trainerBuildingDictionary, mapCell);
        } else if ((trapBuildingDictionary = TrapBuildingsDictionary.getDictionaryByName(buildingName)) != null) {
            prices = trapBuildingDictionary.getBuildingDictionary().getPrices();
            building = new TrapBuilding(empire, trapBuildingDictionary, mapCell);
        } else {
            BuildingsDictionary buildingDictionary = BuildingsDictionary.getDictionaryByName(buildingName);
            prices = buildingDictionary.getPrices();
            building = new Building(empire, buildingDictionary, mapCell);
        }
        if (!buyBuilding(empire, prices)) return GameMessages.NOT_ENOUGH_RESOURCE;
        Game.getCurrentGame().getMapCellByAddress(x, y).setBuilding(building);

        initBuildings(building);
        return GameMessages.SUCCESSFUL_DROP;
    }

    private void initBuildings(Building building) {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        if (building.getBuildingDictionary().equals(BuildingsDictionary.SMALL_STONE_GATEHOUSE) ||
                building.getBuildingDictionary().equals(BuildingsDictionary.LARGE_STONE_GATEHOUSE))
            empire.activateTaxRate();
        if (building.getBuildingDictionary().equals(BuildingsDictionary.STOCKPILE)) {
            empire.addStockPile((StorageBuilding) building);
            if (empire.getAllStockPiles().size() == 1) {
                empire.changeResourceAmount("wood", 60);
                empire.changeResourceAmount("stone", 50);
            }
        }
        if (building.getBuildingDictionary().equals(BuildingsDictionary.GRANARY)) {
            empire.addGranary((StorageBuilding) building);
            if (empire.getAllGranaries().size() == 1)
                empire.changeResourceAmount("bread", 50);
        }
        if (building.getBuildingDictionary().equals(BuildingsDictionary.ARMOURY)) {
            empire.addArmoury((StorageBuilding) building);
        }
    }

    private boolean buyBuilding(Empire empire, HashMap<String, Integer> prices) {
        for (String resource : prices.keySet())
            if (empire.getAvailableResource(resource) < prices.get(resource)) return false;
        for (String resource : prices.keySet())
            empire.changeResourceAmount(resource, -(prices.get(resource)));
        return true;
    }

    public GameMessages selectBuilding(Matcher matcher) {
        int x = Integer.parseInt(removeQuotation(matcher.group("x"))) - 1;
        int y = Integer.parseInt(removeQuotation(matcher.group("y"))) - 1;
        if (!checkCoordinates(x, y)) return GameMessages.INVALID_POSITION;
        Building building = Game.getCurrentGame().getMapCellByAddress(x, y).getBuilding();
        if (building == null) return GameMessages.BUILDING_NOT_EXIST;
        if (!building.getBuildingOwner().equals(Game.getCurrentGame().getCurrentEmpire()))
            return GameMessages.NOT_OWNING_THE_BUILDING;
        if (building.getBuildingDictionary().equals(BuildingsDictionary.MARKET))
            return GameMessages.ENTER_SHOP_MENU;
        Game.getCurrentGame().setSelectedBuilding(building);
        return GameMessages.SET_SUCCESSFUL;
    }

    public GameMessages repair() {
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
        Game.getCurrentGame().setSelectedBuilding(null);
        return GameMessages.SUCCESSFUL_REPAIR;
    }

    public GameMessages createUnit(Matcher matcher) {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        String unitType = removeQuotation(matcher.group("type"));
        int count = Integer.parseInt(removeQuotation(matcher.group("count")));
        Building currentBuilding = Game.getCurrentGame().getSelectedBuilding();
        if (currentBuilding == null) return GameMessages.NO_SELECTED_BUILDING;
        MapCell mapCell = currentBuilding.getMapCell();
        int buildingPosX = mapCell.getX();
        int buildingPosY = mapCell.getY();
        if (!(currentBuilding instanceof TrainerBuilding)) return GameMessages.INVALID_BUILDING_FOR_CREATE;
        if (!((TrainerBuilding) currentBuilding).getAvailableSoldiers().contains(unitType))
            return GameMessages.INVALID_SOLDIER_TYPE;
        if (count <= 0) return GameMessages.INVALID_TROOP_COUNT;
        if (unitType.equals("engineer")) {
            if (count * Engineer.getRequiredGold() > empire.getAvailableResource("gold"))
                return GameMessages.NOT_ENOUGH_GOLD;
            if (!removeFreePeople(empire, count)) return GameMessages.NOT_ENOUGH_POPULATION;
            mapCell.addPeople(new Engineer(empire, mapCell));
            empire.changeEmpireResource("gold", -1 * SoldiersDictionary.ENGINEER.getGold());
            Game.getCurrentGame().setSelectedBuilding(null);
            return GameMessages.SUCCESSFUL_CREATE_UNIT;
        } else if (unitType.equals("tunneler")) {
            if (count * Tunneler.getRequiredGold() > empire.getAvailableResource("gold"))
                return GameMessages.NOT_ENOUGH_GOLD;
            if (!removeFreePeople(empire, count)) return GameMessages.NOT_ENOUGH_POPULATION;
            mapCell.addPeople(new Tunneler(empire, mapCell));
            empire.changeEmpireResource("gold", -1 * SoldiersDictionary.TUNNELER.getGold());
            Game.getCurrentGame().setSelectedBuilding(null);
            return GameMessages.SUCCESSFUL_CREATE_UNIT;
        }
        return addSoldier(empire, unitType, count, buildingPosX, buildingPosY);
    }

    private boolean removeFreePeople(Empire empire, int count) {
        ArrayList<Person> removeAblePeople = new ArrayList<>();
        for (Person person : empire.getPopulation()) {
            if (!(person instanceof Soldier) && !(person instanceof Worker) &&
                    !(person instanceof Engineer) && !(person instanceof Tunneler))
                removeAblePeople.add(person);
            if (removeAblePeople.size() == count) break;
        }
        if (removeAblePeople.size() < count) return false;
        for (Person person : removeAblePeople) {
            person.getMapCell().removePeople(person);
            empire.removePerson(person);
        }
        return true;
    }

    private GameMessages addSoldier(Empire empire, String soldierType, int count, int x, int y) {
        SoldiersDictionary soldierDictionary = SoldiersDictionary.getSoldierDictionaryByName(soldierType);
        String weapon = soldierDictionary.getWeapon();
        String armour = soldierDictionary.getShield();
        int gold = soldierDictionary.getGold();
        if (count * gold > empire.getResourceAmount("gold")) return GameMessages.NOT_ENOUGH_GOLD;
        if ((!weapon.isEmpty() && count > empire.getWeaponAndArmourAmount(weapon)) ||
                (!armour.isEmpty() && count > empire.getWeaponAndArmourAmount(armour)))
            return GameMessages.NOT_ENOUGH_WEAPON;
        if (!removeFreePeople(empire, count)) return GameMessages.NOT_ENOUGH_POPULATION;
        buySoldierRequirement(empire, gold, weapon, armour, count);
        for (int i = 0; i < count; i++) {
            MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
            if (soldierDictionary.equals(SoldiersDictionary.ASSASSIN))
                mapCell.addPeople(new Assassins(empire, mapCell));
            else mapCell.addPeople(new Soldier(empire, soldierDictionary, mapCell));
        }
        Game.getCurrentGame().setSelectedBuilding(null);
        return GameMessages.SUCCESSFUL_CREATE_UNIT;
    }

    private void buySoldierRequirement(Empire empire, int gold, String weapon, String armour, int count) {
        empire.changeResourceAmount("gold", -1 * count * gold);
        empire.changeResourceAmount(weapon, -1 * count);
        empire.changeResourceAmount(armour, -1 * count);
    }

    public GameMessages selectUnit(Matcher matcher) {
        int x = Integer.parseInt(removeQuotation(matcher.group("x"))) - 1;
        int y = Integer.parseInt(removeQuotation(matcher.group("y"))) - 1;
        String type = matcher.group("type");
        if (type != null) type = removeQuotation(type);
        if (!checkCoordinates(x, y)) return GameMessages.INVALID_POSITION;
        if (type != null && !type.equals("engineer") && !type.equals("tunneler") &&
                !SoldiersDictionary.getAllSoldierTypes().contains(type)) return GameMessages.INVALID_UNIT_TYPE;
        ArrayList<Person> selectedUnit = new ArrayList<>();
        for (Person person : Game.getCurrentGame().getMapCellByAddress(x, y).getPeople()) {
            if ((type == null || type.equals("engineer")) && person instanceof Engineer) selectedUnit.add(person);
            else if ((type == null || type.equals("tunneler")) && person instanceof Tunneler) selectedUnit.add(person);
            else if (person instanceof Soldier)
                if (type == null || ((Soldier) person).getSoldierType().equals(type)) selectedUnit.add(person);
        }
        if (selectedUnit.size() == 0) return GameMessages.TROOP_NOT_EXIST;
        Game.getCurrentGame().selectUnit(selectedUnit);
        return GameMessages.SUCCESSFUL_SELECT_UNIT;
    }

    public GameMessages moveUnit(Matcher matcher) {
        int x = Integer.parseInt(removeQuotation(matcher.group("x"))) - 1;
        int y = Integer.parseInt(removeQuotation(matcher.group("y"))) - 1;
        if (!checkCoordinates(x, y)) return GameMessages.INVALID_POSITION;
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return GameMessages.EMPTY_UNIT_SELECT;
        MapCell destination = Game.getCurrentGame().getMapCellByAddress(x, y);
        MapCell origin = Game.getCurrentGame().getSelectedUnit().get(0).getMapCell();
        if (routing(origin, destination, false) == null) return GameMessages.NO_WAY;
        String[] invalidTextures = {"OIL", "PLAIN", "LOW_WATER", "RIVER", "SMALL_POND", "LARGE_POND", "SEA"};
        for (String invalidTexture : invalidTextures)
            if (destination.getGroundTexture().contains("ROCK") || destination.getGroundTexture().equals(invalidTexture))
                return GameMessages.INVALID_MOVE;
        for (Person person : unit) {
            if (person instanceof Soldier) ((Soldier) person).setAim(null);
            person.setCurrentDestination(Game.getCurrentGame().getMapCellByAddress(x, y));
            person.setNextDestination(null);
            Game.getCurrentGame().addMoveAblePerson(person);
        }
        Game.getCurrentGame().selectUnit(null);
        return GameMessages.SUCCESSFUL_TROOP_MOVE;
    }

    public GameMessages patrolUnit(Matcher matcher) {
        int x1 = Integer.parseInt(removeQuotation(matcher.group("x1"))) - 1;
        int y1 = Integer.parseInt(removeQuotation(matcher.group("y1"))) - 1;
        int x2 = Integer.parseInt(removeQuotation(matcher.group("x1"))) - 1;
        int y2 = Integer.parseInt(removeQuotation(matcher.group("y2"))) - 1;
        if (!checkCoordinates(x1, y1) || !checkCoordinates(x2, y2)) return GameMessages.INVALID_POSITION;
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return GameMessages.EMPTY_UNIT_SELECT;
        MapCell destination1 = Game.getCurrentGame().getMapCellByAddress(x1, y1);
        MapCell origin = Game.getCurrentGame().getSelectedUnit().get(0).getMapCell();
        if (routing(origin, destination1, false) == null) return GameMessages.NO_WAY;
        MapCell destination2 = Game.getCurrentGame().getMapCellByAddress(x2, y2);
        for (Person person : unit) {
            if (person instanceof Soldier) ((Soldier) person).setAim(null);
            person.setCurrentDestination(Game.getCurrentGame().getMapCellByAddress(x1, y1));
            person.setNextDestination(Game.getCurrentGame().getMapCellByAddress(x2, y2));
            Game.getCurrentGame().addMoveAblePerson(person);
        }
        Game.getCurrentGame().selectUnit(null);
        return GameMessages.SUCCESSFUL_PATROL_UNIT;
    }

    public GameMessages setUnitMode(Matcher matcher) {
        String mode = removeQuotation(matcher.group("state"));
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return GameMessages.EMPTY_UNIT_SELECT;
        for (Person person : unit)
            if (person instanceof Tunneler)
                return GameMessages.INVALID_UNIT_TYPE;
        int modeNum;
        switch (mode) {
            case "standing":
                modeNum = 0;
                break;
            case "defensive":
                modeNum = 1;
                break;
            case "offensive":
                modeNum = 2;
                break;
            default:
                return GameMessages.INVALID_MOVE_NAME;
        }
        for (Person person : unit) person.setMode(modeNum);
        Game.getCurrentGame().selectUnit(null);
        return GameMessages.SUCCESSFUL_SET_MODE;
    }

    public GameMessages airAttack(Matcher matcher) {
        int x = Integer.parseInt(removeQuotation(matcher.group("x"))) - 1;
        int y = Integer.parseInt(removeQuotation(matcher.group("y"))) - 1;
        if (!checkCoordinates(x, y)) return GameMessages.INVALID_POSITION;
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return GameMessages.EMPTY_UNIT_SELECT;
        for (Person person : unit)
            if (person instanceof Engineer || person instanceof Tunneler)
                return GameMessages.NOT_SOLDIER;
        for (Person person : unit) {
            SoldiersDictionary dictionary = ((Soldier) unit.get(0)).getSoldiersDictionary();
            if (!dictionary.equals(SoldiersDictionary.ARCHER) && !dictionary.equals(SoldiersDictionary.CROSSBOWMAN) &&
                    !dictionary.equals(SoldiersDictionary.ARABIANBOWS) && !dictionary.equals(SoldiersDictionary.HORSE_ARCHER))
                return GameMessages.FEW_RANGE;
        }
        boolean inRange = false;
        for (Person person : unit) {
            Soldier soldier = (Soldier) person;
            int distance = (int) Math.sqrt(Math.pow(x - soldier.getMapCell().getX(), 2) +
                    Math.pow(y - soldier.getMapCell().getY(), 2));
            if (soldier.getSoldiersDictionary().getFireRange() > distance) {
                inRange = true;
                soldier.setAim(Game.getCurrentGame().getMapCellByAddress(x, y));
                Game.getCurrentGame().addAttackingSoldier(soldier);
            }
        }
        if (!inRange) return GameMessages.AIM_OUT_OF_RANGE;
        Game.getCurrentGame().selectUnit(null);
        return GameMessages.SUCCESSFUL_ATTACK;
    }

    public GameMessages Attack(Matcher matcher) {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        int x = Integer.parseInt(removeQuotation(matcher.group("x"))) - 1;
        int y = Integer.parseInt(removeQuotation(matcher.group("y"))) - 1;
        if (!checkCoordinates(x, y)) return GameMessages.INVALID_POSITION;
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return GameMessages.EMPTY_UNIT_SELECT;
        for (Person person : unit)
            if (!(person instanceof Soldier)) return GameMessages.NOT_SOLDIER;
        MapCell aimMapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
        MapCell origin = Game.getCurrentGame().getSelectedUnit().get(0).getMapCell();
        if (routing(origin, aimMapCell, false) == null) return GameMessages.NO_WAY;
        Building aimBuilding = aimMapCell.getBuilding();
        boolean enemy = false;
        for (Person person : aimMapCell.getPeople()) {
            if (!person.getPersonOwner().equals(empire)) {
                enemy = true;
                break;
            }
        }
        if ((aimBuilding == null || aimBuilding.getBuildingOwner().equals(empire)) && !enemy)
            return GameMessages.NO_ENEMY;
        for (Person person : unit) {
            person.setCurrentDestination(aimMapCell);
            ((Soldier) person).setAim(aimMapCell);
            Game.getCurrentGame().addAttackingSoldier((Soldier) person);
        }
        Game.getCurrentGame().selectUnit(null);
        return GameMessages.SUCCESSFUL_ATTACK;
    }

    public GameMessages pourOil(Matcher matcher) {
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return GameMessages.EMPTY_UNIT_SELECT;
        for (Person person : unit)
            if (!(person instanceof Engineer)) return GameMessages.NOT_ENGINEER;
        int x = unit.get(0).getMapCell().getX();
        int y = unit.get(0).getMapCell().getY();
        String direction = removeQuotation(matcher.group("direction"));
        switch (direction) {
            case "u":
                y--;
                break;
            case "d":
                y++;
                break;
            case "r":
                x++;
                break;
            case "l":
                x--;
                break;
            default:
                return GameMessages.INVALID_DIRECTION;
        }
        MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
        for (Person person : unit) {
            Engineer engineer = (Engineer) person;
            if (engineer.hasOil()) {
                engineer.setOil(false);
                mapCell.setOilCondition(true);
                Game.getCurrentGame().selectUnit(null);
                return GameMessages.SUCCESSFUL_POUR;
            }
        }
        return GameMessages.DOES_NOT_HAVE_OIL;
    }

    public GameMessages digTunnel(Matcher matcher) {
        int x = Integer.parseInt(removeQuotation(matcher.group("x"))) - 1;
        int y = Integer.parseInt(removeQuotation(matcher.group("y"))) - 1;
        if (!checkCoordinates(x, y)) return GameMessages.INVALID_POSITION;
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return GameMessages.EMPTY_UNIT_SELECT;
        for (Person person : unit)
            if (!(person instanceof Tunneler)) return GameMessages.NOT_TUNNELER;
        MapCell destination = Game.getCurrentGame().getMapCellByAddress(x, y);
        MapCell origin = ((Tunneler) unit.get(0)).getMapCell();
        if (routing(origin, destination, true) == null) return GameMessages.NO_WAY;
        ((Tunneler) unit.get(0)).setAimTunnel(destination);
        Game.getCurrentGame().selectUnit(null);
        return GameMessages.SET_TUNNEL_SUCCESSFUL;
    }

    public GameMessages engineerBuild(Matcher matcher) {

        return null;
    }

    public GameMessages disbandUnit() {
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return GameMessages.EMPTY_UNIT_SELECT;
        MapCell mapCell = unit.get(0).getMapCell();
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        for (Person person : unit) {
            empire.removePerson(person);
            mapCell.removePeople(person);
            Person newPerson = new Person(empire, mapCell);
            mapCell.addPeople(newPerson);
            empire.addPerson(newPerson);
        }
        Game.getCurrentGame().selectUnit(null);
        return GameMessages.SUCCESSFUL_DISBAND;
    }

    public GameMessages setOutput(Matcher matcher) {
        if (matcher.group("output") == null)
            return GameMessages.EMPTY_FIELD;
        String output = removeQuotation(matcher.group("output"));
        Building building = Game.getCurrentGame().getSelectedBuilding();
        if (!(building instanceof ProductiveBuilding))
            return GameMessages.INCORRECT_BUILDING_TYPE;

        ProductiveBuilding productiveBuilding = (ProductiveBuilding) building;
        if (!productiveBuilding.getDictionary().getOutputResource().contains(output))
            return GameMessages.INCORRECT_OUTPUT;
        productiveBuilding.setOutputResource(output);
        Game.getCurrentGame().setSelectedBuilding(null);
        return GameMessages.SET_OUTPUT_SUCCESSFUL;
    }

    public GameMessages buildEquipment(Matcher matcher) {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        int x = Integer.parseInt(removeQuotation(matcher.group("x"))) - 1;
        int y = Integer.parseInt(removeQuotation(matcher.group("y"))) - 1;
        if (!checkCoordinates(x, y)) return GameMessages.INVALID_POSITION;
        MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
        String equipmentName = removeQuotation(matcher.group("equipmentName"));
        if (Game.getCurrentGame().getSelectedUnit() == null) return GameMessages.NO_SELECTED_UNIT;
        for (Person person : Game.getCurrentGame().getSelectedUnit())
            if (!(person instanceof Engineer)) return GameMessages.NOT_ENGINEER;
        MachinesDictionary machinesDictionary = MachinesDictionary.getDictionaryByName(equipmentName);
        if (machinesDictionary == null) return GameMessages.INVALID_EQUIPMENT_NAME;
        if (machinesDictionary.getGold() > empire.getAvailableResource("gold")) return GameMessages.NOT_ENOUGH_GOLD;
        if (mapCell.getMachine() != null) return GameMessages.MACHINE_EXIST;
        mapCell.setMachine(new Machine(machinesDictionary, empire, mapCell));
        empire.changeEmpireResource("gold", -1 * machinesDictionary.getGold());
        Game.getCurrentGame().selectUnit(null);
        return GameMessages.SUCCESSFUL_CREATE_EQUIPMENT;
    }



    public void nextTurn() {
        moveAndPatrolTroops();
        fights();
        longRangeFights();
        attackBuildings();
        tunnel();
        updateRates();
        updateThingsWithRate();
        buildingOperations();
        fillEngineerOil();
        checkEndGame();
    }

    private void tunnel() {
        for (Empire empire : Game.getCurrentGame().getAllEmpires())
            for (Person person : empire.getPopulation())
                if (person instanceof Tunneler) {
                    Tunneler tunneler = (Tunneler) person;
                    MapCell destination = tunneler.getAimTunnel();
                    if (destination != null) {
                        ArrayList<MapCell> path = routing(tunneler.getMapCell(), destination, true);
                        for (int i = 0; i < path.size(); i++) {
                            if (i == 15) {
                                kill(tunneler);
                                break;
                            }
                            Building building = path.get(i).getBuilding();
                            if (building != null) {
                                if (!building.getBuildingOwner().equals(empire)) {
                                    destroyBuilding(building);
                                    kill(tunneler);
                                }
                            }
                        }
                    }
                }
    }

    private void destroyBuilding(Building building) {
        if (building instanceof StorageBuilding) {
            StorageBuilding storageBuilding = (StorageBuilding) building;
            for (String resource : storageBuilding.getContent().keySet())
                building.getBuildingOwner().changeEmpireResource(resource, storageBuilding.getResourceAmount(resource));
        }
        building.getMapCell().removeBuilding();
    }

    private void fillEngineerOil() {
        for (Empire empire : Game.getCurrentGame().getAllEmpires()) {
            for (Person person : empire.getPopulation()) {
                if (person instanceof Engineer) {
                    Engineer engineer = (Engineer) person;
                    if (!engineer.hasOil()) engineer.setOil(true);//todo : fill engineer
                }
            }
        }
    }

    private void longRangeFights() {
        for (int i = 0; i < Game.getCurrentGame().getMapSize(); i++) {
            for (MapCell mapCell : Game.getCurrentGame().getMap()[i]) {
                if (mapCell.getSoldiers() != null) {
                    for (Soldier soldier : mapCell.getSoldiers()) {
                        SoldiersDictionary dictionary = soldier.getSoldiersDictionary();
                        if (dictionary.equals(SoldiersDictionary.ARCHER) ||
                                dictionary.equals(SoldiersDictionary.CROSSBOWMAN) ||
                                dictionary.equals(SoldiersDictionary.ARABIANBOWS) ||
                                dictionary.equals(SoldiersDictionary.HORSE_ARCHER)) {
                            fightInRange(soldier);
                        }
                    }
                }
            }
        }

    }

    private void fightInRange(Soldier soldier) {
        Empire empire = soldier.getPersonOwner();
        SoldiersDictionary dictionary = soldier.getSoldiersDictionary();
        int range = dictionary.getFireRange();
        int x = soldier.getMapCell().getX();
        int y = soldier.getMapCell().getY();
        int size = Game.getCurrentGame().getMapSize();
        for (int i = Math.min(x - range, 0); i <= Math.max(x + range, size); i++) {
            for (int j = Math.min(y - range, 0); j < Math.max(y + range, size); j++) {
                int distance = (int) Math.sqrt(i * i + j * j);
                if (distance <= range) {
                    MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
                    if (mapCell.getPeople() != null) {
                        for (Person person : mapCell.getPeople()) {
                            if (!person.getPersonOwner().equals(empire)) {
                                int defensivePower = person instanceof Soldier ? ((Soldier) person).getDefensivePower() : 0;
                                person.damagePerson(soldier.getOffensivePower() - defensivePower);
                                kill(person);
                            }
                        }
                    }
                }
            }
        }
    }

    private void attackBuildings() {


    }

    private void moveAndPatrolTroops() {
        for (Person person : Game.getCurrentGame().getToMovePeople()) {
            if (person.getCurrentDestination() != null) {
                ArrayList<MapCell> path = routing(person.getMapCell(), person.getCurrentDestination(), false);
                if (path == null) continue;
                int moveTileNumber = Math.min(person.getSpeed(), path.size() - 1);
                MapCell goal = path.get(moveTileNumber);
                for (int i = 0; i < moveTileNumber; i++) {
                    if (path.get(i).getBuilding() instanceof TrapBuilding) {
                        TrapBuilding building = (TrapBuilding) path.get(i).getBuilding();
                        person.damagePerson(building.getDamage());
                        kill(person);
                    }
                }
                goal.addPeople(person);
                person.changePosition(goal);
                path.get(0).removePeople(person);
                if (person.getMapCell().equals(goal)) {
                    if (person.getNextDestination() != null) person.swapDestinations();
                    else {
                        person.setCurrentDestination(null);
                        Game.getCurrentGame().removeMovedPerson(person);
                    }
                }
            }
        }
    }

    private void kill(Person person) {
        if (person.getHp() < 0) {
            person.getMapCell().removePeople(person);
            Game.getCurrentGame().removeMovedPerson(person);
            if (person instanceof Soldier) Game.getCurrentGame().removeAttackingSoldier((Soldier) person);
            person.getPersonOwner().removePerson(person);
        }
    }

    private void fights() {
        for (int i = 0; i < Game.getCurrentGame().getMapSize(); i++) {
            for (MapCell mapCell : Game.getCurrentGame().getMap()[i]) {
                if (mapCell.getPeople() != null) {
                    for (Empire empire : Game.getCurrentGame().getAllEmpires()) {
                        ArrayList<Soldier> thisEmpireSoldiers = new ArrayList<>();
                        ArrayList<Soldier> otherEmpiresSoldiers = new ArrayList<>();
                        for (Person person : mapCell.getPeople()) {
                            if (person instanceof Soldier) {
                                Soldier soldier = (Soldier) person;
                                int index = new Random().nextInt(otherEmpiresSoldiers.size());
                                if (person.getPersonOwner().equals(empire)) thisEmpireSoldiers.add(soldier);
                                else otherEmpiresSoldiers.add(index, soldier);
                            }
                        }
                        for (int j = 0; j < thisEmpireSoldiers.size(); j++) {
                            int damage = thisEmpireSoldiers.get(j).getDefensivePower() -
                                    otherEmpiresSoldiers.get(j).getDefensivePower();
                            otherEmpiresSoldiers.get(j).damagePerson(damage);
                            kill(otherEmpiresSoldiers.get(j));
                        }
                    }
                }
            }
        }
    }

    private void updateRates() {
        for (Empire empire : Game.getCurrentGame().getAllEmpires()) {
            int availableFood = 0;
            for (String food : empire.getFood().keySet()) availableFood += empire.getFoodAmount(food);
            int requiredFood = (int) ((empire.getFoodRate() + 2) * empire.getPopulation().size() / 2);
            if (availableFood < requiredFood) empire.setFoodRate(-2);
            if (empire.getTaxRate() < 0 && empire.getTaxRate() != -100) {
                int totalTax = (int) ((empire.getTaxRate() * (-0.2) + 0.4) * empire.getPopulation().size());
                if (empire.getResourceAmount("gold") < totalTax) empire.setTaxRate(0);
            }
        }
    }

    private void updateThingsWithRate() {
        for (Empire empire : Game.getCurrentGame().getAllEmpires()) {
            updateFood(empire);
            updateTax(empire);
            empire.changePopularity("fear", -empire.getFearRate());
            updateReligion(empire);
        }
    }

    private void updateFood(Empire empire) {
        int foodTypes = 0;
        for (String food : empire.getFood().keySet())
            if (empire.getFood().get(food) != 0) foodTypes++;
        empire.changePopularity("food", foodTypes - 1);
        empire.changePopularity("food", 4 * empire.getFoodRate());
        int requiredFood = (int) ((empire.getFoodRate() + 2) * empire.getPopulation().size() / 2);
        while (requiredFood > 0) {
            for (String food : empire.getFood().keySet()) {
                if (empire.getFoodAmount(food) > 0) {
                    empire.changeResourceAmount(food, -1);
                    requiredFood--;
                }
            }
        }
    }

    private void updateTax(Empire empire) {
        int taxRate = empire.getTaxRate();
        int totalTax = (int) ((Math.abs(taxRate) * 0.2 + 0.4) * empire.getPopulation().size());
        if (taxRate < 0) totalTax *= (-1);
        empire.changeResourceAmount("gold", totalTax);
        int totalPopularity = (-2) * taxRate + (totalTax <= 0 ? 1 : 0);
        empire.changePopularity("tax", totalPopularity);
    }

    private void updateReligion(Empire empire) {
        for (int i = 0; i < Game.getCurrentGame().getMapSize(); i++) {
            for (MapCell mapCell : Game.getCurrentGame().getMap()[i]) {
                Building building = mapCell.getBuilding();
                if (building != null) {
                    BuildingsDictionary buildingsDictionary = building.getBuildingDictionary();
                    if (buildingsDictionary.equals(BuildingsDictionary.CHURCH))
                        empire.changePopularity("religion", 1);
                    if (buildingsDictionary.equals(BuildingsDictionary.CATHEDRAL))
                        empire.changePopularity("religion", 2);
                    if (buildingsDictionary.equals(BuildingsDictionary.INN))
                        empire.changePopularity("ale", 1);
                }
            }
        }
    }

    private void buildingOperations() {
        for (int i = 0; i < Game.getCurrentGame().getMapSize(); i++) {
            for (MapCell mapCell : Game.getCurrentGame().getMap()[i]) {
                Building building = mapCell.getBuilding();
                if (!(building instanceof ProductiveBuilding)) continue;
                ProductiveBuilding productiveBuilding = ((ProductiveBuilding) building);
                ProductiveBuildingsDictionary dictionary = productiveBuilding.getDictionary();
                String input = dictionary.getInputResource();
                String output = productiveBuilding.getOutputResource();
                int rate = dictionary.getRate();
                Empire ownerEmpire = productiveBuilding.getBuildingOwner();
                buildingProduction(input, output, rate, ownerEmpire);
            }
        }
    }

    private void buildingProduction(String input, String output, int rate, Empire owner) {
        ArrayList<StorageBuilding> storageBuildings;
        if (input != null) {
            if (owner.getAvailableResource(input) < 1) return;
            owner.changeResourceAmount(input, -1);
        }
        owner.changeResourceAmount(output, rate);
    }

    private void checkEndGame() {

    }

    private boolean BFS(MapCell origin, MapCell destination, HashMap<MapCell, MapCell> predecessor, boolean isTunneler) {
        LinkedList<MapCell> queue = new LinkedList<MapCell>();
        ArrayList<MapCell> visited = new ArrayList<>();
        visited.add(origin);
        queue.add(origin);
        while (!queue.isEmpty()) {
            MapCell currentNode = queue.remove();
            int x = currentNode.getX();
            int y = currentNode.getY();
            ArrayList<MapCell> neighbors = new ArrayList<MapCell>() {
                {
                    int lowerBound = 0;
                    int upperBound = Game.getCurrentGame().getMapSize();
                    if (x != upperBound) add(Game.getCurrentGame().getMapCellByAddress(x + 1, y));
                    if (x != lowerBound) add(Game.getCurrentGame().getMapCellByAddress(x - 1, y));
                    if (y != upperBound) add(Game.getCurrentGame().getMapCellByAddress(x, y + 1));
                    if (y != lowerBound) add(Game.getCurrentGame().getMapCellByAddress(x, y - 1));
                }
            };
            for (MapCell nextNode : neighbors) {
                if (!visited.contains(nextNode) && checkNode(x, y, nextNode, isTunneler)) {
                    visited.add(nextNode);
                    predecessor.put(nextNode, currentNode);
                    queue.add(nextNode);
                    if (nextNode.equals(destination)) return true;
                }
            }
        }
        return false;
    }

    private boolean checkNode(int x, int y, MapCell nextNode, boolean isTunneler) {
        Building nextBuilding = nextNode.getBuilding();
        if (nextNode.getBuilding() != null && !isTunneler) {
            BuildingsDictionary nextBuildingDictionary = nextBuilding.getBuildingDictionary();
            if ((nextBuildingDictionary != BuildingsDictionary.SMALL_STONE_GATEHOUSE &&
                    nextBuildingDictionary != BuildingsDictionary.LARGE_STONE_GATEHOUSE) ||
                    (((StructuralBuilding) nextNode.getBuilding()).isUpside() && (nextNode.getX() != x)) ||
                    (!((StructuralBuilding) nextNode.getBuilding()).isUpside() && (nextNode.getX() != y)))
                return false;
        }
        if (nextNode.getGroundTexture().contains("ROCK") || nextNode.getGroundTexture().equals("SEA"))
            return false;
        if (isTunneler) {
            return !nextNode.getBuilding().getBuildingDictionary().equals(BuildingsDictionary.LOOKOUT_TOWER) &&
                    !nextNode.getBuilding().getBuildingDictionary().equals(BuildingsDictionary.PERIMETER_TOWER) &&
                    !(nextNode.getBuilding().getBuildingDictionary().equals(BuildingsDictionary.PITCH_DITCH));
        }
        return true;
    }

    private ArrayList<MapCell> routing(MapCell origin, MapCell destination, boolean isTunneler) {
        HashMap<MapCell, MapCell> predecessor = new HashMap<>();
        if (!BFS(origin, destination, predecessor, isTunneler)) return null;
        ArrayList<MapCell> path = new ArrayList<>();
        MapCell crawl = destination;
        while (!crawl.equals(origin)) {
            path.add(0, crawl);
            crawl = predecessor.get(crawl);
        }
        path.add(0, origin);
        return path;
    }

    private boolean checkCoordinates(int x, int y) {
        int mapSize = Game.getCurrentGame().getMapSize();
        return x >= 0 && x < mapSize && y >= 0 && y < mapSize;
    }

    public GameMessages startGame(Matcher matcher) {
        String mapName = matcher.group("mapName").replaceAll("\"", "");
        if (Player.getCurrentPlayer().getMapByName(mapName) == null)
            return GameMessages.MAP_NOT_EXIST;
        String allUsers = removeQuotation(matcher.group("allUsers"));
        String[] players;
        if (allUsers.contains("/")) players = allUsers.split("/");
        else players = new String[]{allUsers};
        if (!validPlayers(players))
            return GameMessages.USER_NOT_EXIST;
        ArrayList<Empire> allEmpires = setEmpireForPlayers(players);
        int colorNumbers = Player.getCurrentPlayer().getMapByName(mapName).getAllColors().size();
        if (colorNumbers != allEmpires.size())
            return GameMessages.NOT_ENOUGH_HEAD_QUARTER;
        new Game(allEmpires, mapName);
        return GameMessages.GAME_STARTED;
    }

    private boolean validPlayers(String[] players) {
        for (String playerUsername : players)
            if (Player.getPlayerByUsername(playerUsername) == null)
                return false;
        return true;
    }

    private ArrayList<Empire> setEmpireForPlayers(String[] players) {
        ArrayList<Empire> empires = new ArrayList<>();
        empires.add(new Empire(Player.getCurrentPlayer()));
        for (String playerUsername : players) {
            empires.add(new Empire(Player.getPlayerByUsername(playerUsername)));
        }
        return empires;
    }

    private String removeQuotation(String buffer) {
        return buffer.replaceAll("\"", "");
    }

}