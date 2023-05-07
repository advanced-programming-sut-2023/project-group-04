package org.controller;

import org.model.Empire;
import org.model.Game;
import org.model.Map;
import org.model.MapCell;
import org.model.buildings.*;
import org.model.person.*;

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
        //TODO: STRUCTURAL BUILDINGS MUST HAVE A DIRECTION FIELD....DIRECTION MUST BE OPTION FIELD IN DROPBUILDING
        //TODO: CHANGE RETURN TYPE AND RETURNS -----> **ABOLFAZL**
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String buildingName = matcher.group("type");
        //todo: handle direction
        if (!checkCoordinates(x, y)) return "incorrect coordinates!";
        if (Game.getCurrentGame().getMapCellByAddress(x, y).getBuilding() != null)
            return "a building already exist in this place!";
        if (BuildingsDictionary.getDictionaryByName(type) == null)
            return "Incorrect building type!";
        //TODO: CHECK GROUND TEXTURE VALIDATION
        return createBuilding(empire, x, y, "");//todo: handle direction
        //TODO: DRAW BRIDGE ONLY CAN BE PLACED IN FRONT OF GATEHOUSES
    }

    private String createBuilding(Empire empire, int x, int y, String direction) {
        ProductiveBuildingsDictionary productiveBuildingDictionary;
        StorageBuildingsDictionary storageBuildingDictionary;
        StructuralBuildingsDictionary structuralBuildingDictionary;
        TowerBuildingsDictionary towerBuildingDictionary;
        TrainerBuildingsDictionary trainerBuildingDictionary;
        TrapBuildingsDictionary trapBuildingDictionary;
        Building building = null;
        HashMap<String, Integer> prices = null;
        MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
        if ((productiveBuildingDictionary = ProductiveBuildingsDictionary.getDictionaryByName(type)) != null) {
            prices = productiveBuildingDictionary.getBuildingDictionary().getPrices();
            building = new ProductiveBuilding(empire, productiveBuildingDictionary, mapCell);
        } else if ((storageBuildingDictionary = StorageBuildingsDictionary.getDictionaryByName(type)) != null) {
            prices = storageBuildingDictionary.getBuildingDictionary().getPrices();
            building = new StorageBuilding(empire, storageBuildingDictionary, mapCell);
        } else if ((structuralBuildingDictionary = StructuralBuildingsDictionary.getDictionaryByName(type)) != null) {
            prices = structuralBuildingDictionary.getBuildingDictionary().getPrices();
            building = new StructuralBuilding(empire, structuralBuildingDictionary, direction, mapCell);
        } else if ((towerBuildingDictionary = TowerBuildingsDictionary.getDictionaryByName(type)) != null) {
            prices = towerBuildingDictionary.getBuildingDictionary().getPrices();
            building = new TowerBuilding(empire, towerBuildingDictionary, mapCell);
        } else if ((trainerBuildingDictionary = TrainerBuildingsDictionary.getDictionaryByName(type)) != null) {
            prices = trainerBuildingDictionary.getBuildingDictionary().getPrices();
            building = new TrainerBuilding(empire, trainerBuildingDictionary, mapCell);
        } else if ((trapBuildingDictionary = TrapBuildingsDictionary.getDictionaryByName(type)) != null) {
            prices = trapBuildingDictionary.getBuildingDictionary().getPrices();
            building = new TrapBuilding(empire, trapBuildingDictionary, mapCell);
        } else {
            BuildingsDictionary buildingDictionary = BuildingsDictionary.getDictionaryByName(type);
            prices = buildingDictionary.getPrices();
            building = new Building(empire, buildingDictionary, mapCell);
        }
        if (!buyBuilding(empire, prices)) return "not enough resource to build!";
        mapCell.setBuilding(building);
        initBuildings(building);
        return "building dropped successfully";
    }

    private void initBuildings(Building building) {
        if (building.getBuildingDictionary().equals(BuildingsDictionary.SMALL_STONE_GATEHOUSE) ||
                building.getBuildingDictionary().equals(BuildingsDictionary.LARGE_STONE_GATEHOUSE))
            Game.getCurrentGame().getCurrentEmpire().activateTaxRate();
    }

    private boolean buyBuilding(Empire empire, HashMap<String, Integer> prices) {
        for (String resource : prices.keySet())
            if (empire.getResourceAmount(resource) < prices.get(resource)) return false;
        for (String resource : prices.keySet()) {
            empire.changeResourceAmount(resource, -(prices.get(resource)));
            if (!resource.equals("gold")) {
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
        }
        return true;
    }

    public String selectBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!checkCoordinates(x, y)) return "incorrect coordinate";
        Building building = Game.getCurrentGame().getMapCellByAddress(x, y).getBuilding();
        if (building == null)
            return null;
        if (!building.getBuildingOwner().equals(Game.getCurrentGame().getCurrentEmpire()))
            return null;
        Game.getCurrentGame().setSelectedBuilding(building);
        return null;
    }

    public String repair(Matcher matcher) {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        Building selectedBuilding = Game.getCurrentGame().getSelectedBuilding();
        if (!selectedBuilding.getType().equals(BuildingType.CASTLE))
            return null;
        HashMap<String, Integer> requirementMaterial = new HashMap<>();
        HashMap<String, Integer> prices = selectedBuilding.getPrices();
        int currentHp = selectedBuilding.getCurrentHp();
        int basicHp = selectedBuilding.getBasicHp();
        float requirementPercentage = (float) (currentHp / basicHp);
        for (String key : prices.keySet())
            requirementMaterial.put(key, (int) (prices.get(key) * requirementPercentage));
        if (!buyBuilding(empire, requirementMaterial)) return "not enough resource to repair!";
        selectedBuilding.repair();
        return "success!";
    }

    public String createUnit(Matcher matcher) {
        //todo: edit return types and return values
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        String soldierType = matcher.group("type");
        int count = Integer.parseInt(matcher.group("count"));
        Building currentBuilding = Game.getCurrentGame().getSelectedBuilding();
        int buildingPosX = currentBuilding.getMapCell().getX();
        int buildingPosY = currentBuilding.getMapCell().getY();
        MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(buildingPosX, buildingPosY);
        if (!(currentBuilding instanceof TrainerBuilding)) return "invalid building type for creating unit!!";
        if (!((TrainerBuilding) currentBuilding).getAvailableSoldiers().contains(soldierType))
            return "invalid unit type!";
        if (count <= 0) return "invalid troop count!";
        if (soldierType.equals("engineer")) {
            if (count * Engineer.getRequiredGold() < empire.getResourceAmount("gold")) return "not enough gold";
            mapCell.addPeople(new Engineer(empire, mapCell));
            return "create unit successful";
        } else if (soldierType.equals("tunneler")) {
            if (count * Tunneler.getRequiredGold() < empire.getResourceAmount("gold")) return "not enough gold";
            mapCell.addPeople(new Tunneler(empire, mapCell));
            return "create unit successful";
        }
        return addSoldier(empire, soldierType, count, buildingPosX, buildingPosY);
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
        for (Person person : removeAblePeople)
            empire.removePerson(person);
        return true;
    }

    private String addSoldier(Empire empire, String soldierType, int count, int x, int y) {
        SoldiersDictionary soldierDictionary = SoldiersDictionary.getSoldierDictionaryByName(soldierType);
        String weapon = soldierDictionary.getWeapon();
        String armour = soldierDictionary.getShield();
        int gold = soldierDictionary.getGold();
        if (count * gold < empire.getResourceAmount("gold")) return "not enough gold";
        if ((!weapon.isEmpty() && count < empire.getWeaponAndArmourAmount(weapon)) ||
                (!armour.isEmpty() && count < empire.getWeaponAndArmourAmount(armour)))
            return "not enough weapon and armour!";
        if (!removeFreePeople(empire, count)) return "not enough population for creating unit!!";
        buySoldierRequirement(empire, gold, weapon, armour, count);
        for (int i = 0; i < count; i++) {
            MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
            mapCell.addPeople(new Soldier(empire, soldierDictionary, mapCell));
        }
        return "create unit successful";
    }

    private void buySoldierRequirement(Empire empire, int gold, String weapon, String armour, int count) {
        empire.changeResourceAmount("gold", count * gold);
        empire.changeWeaponAndArmourAmount(weapon, count);
        empire.changeWeaponAndArmourAmount(armour, count);
        int weaponNum = count;
        int armourNum = count;
        for (StorageBuilding armoury : empire.getAllArmouries()) {
            int existWeaponNum = armoury.getResourceAmount(weapon);
            int existArmourNum = armoury.getResourceAmount(armour);
            if (weaponNum > 0) {
                if (existWeaponNum <= count) {
                    armoury.changeResourcesAmount(weapon, -existWeaponNum);
                    weaponNum -= existWeaponNum;
                } else {
                    armoury.changeResourcesAmount(weapon, -weaponNum);
                    weaponNum = 0;
                }
            }
            if (armourNum > 0) {
                if (existArmourNum <= count) {
                    armoury.changeResourcesAmount(armour, -existArmourNum);
                    armourNum -= existArmourNum;
                } else {
                    armoury.changeResourcesAmount(armour, -armourNum);
                    armourNum = 0;
                }
            }
            if (armourNum == 0 && weaponNum == 0) return;
        }

    }

    public String selectUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        if (!checkCoordinates(x, y)) return "incorrect coordinate";
        if (type != null && !type.equals("engineer") && !type.equals("tunneler") &&
                !SoldiersDictionary.getAllSoldierTypes().contains(type))
            return "invalid type!";
        ArrayList<Person> selectedUnit = new ArrayList<>();
        for (Person person : Game.getCurrentGame().getMapCellByAddress(x, y).getPeople()) {
            if (type == null || type.equals(""))
                selectedUnit.add(person);
            else if (type.equals("engineer") && person instanceof Engineer)
                selectedUnit.add(person);
            else if (type.equals("tunneler") && person instanceof Tunneler)
                selectedUnit.add(person);
            else if (person instanceof Soldier)
                if (((Soldier) person).getSoldierType().equals(type))
                    selectedUnit.add(person);
        }
        if (selectedUnit.size() == 0) return "no troop of input type!";
        Game.getCurrentGame().selectUnit(selectedUnit);
        return "unit selected successfully!";
    }

    public String moveUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!checkCoordinates(x, y)) return "incorrect coordinate";
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return "no unit or empty unit selected!";
        for (Person person : unit)
            person.setCurrentDestination(Game.getCurrentGame().getMapCellByAddress(x, y));
        return "move troop successful";
    }

    public String patrolUnit(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x1"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        if (!checkCoordinates(x1, y1) || !checkCoordinates(x2, y2)) return "incorrect coordinate";
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return "no unit or empty unit selected!";
        for (Person person : unit) {
            person.setCurrentDestination(Game.getCurrentGame().getMapCellByAddress(x1, y1));
            person.setNextDestination(Game.getCurrentGame().getMapCellByAddress(x2, y2));
        }
        return "patrol unit successful";
    }

    public String setUnitMode(Matcher matcher) {
        String mode = matcher.group("mode");
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return "no unit or empty unit selected!";
        for (Person person : unit)
            if (person instanceof Tunneler)
                return "selected unit is not soldier or engineer!";
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
                return "invalid mode name";
        }
        for (Person person : unit)
            ((Soldier) person).setMode(modeNum);
        return "mode set successful!";
    }

    public String tileAttack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!checkCoordinates(x, y)) return "incorrect coordinate";
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return "no unit or empty unit selected!";
        for (Person person : unit)
            if (person instanceof Engineer || person instanceof Tunneler)
                return "selected unit is not soldier!";
        ArrayList<SoldiersDictionary> longRangeSoldiers = new ArrayList<>() {
            {
                add(SoldiersDictionary.ARCHER);
                add(SoldiersDictionary.CROSSBOWMAN);
                add(SoldiersDictionary.ARABIANBOWS);
                add(SoldiersDictionary.HORSE_ARCHER);
            }
        };
        for (Person person : unit)
            if (!longRangeSoldiers.contains(((Soldier) unit.get(0)).getSoldiersDictionary()))
                return "selected soldiers not long range!";
        for (Person person : unit)
            ((Soldier) person).setAim(Game.getCurrentGame().getMapCellByAddress(x, y));
        return "attack set successful!";
    }

    public String Attack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!checkCoordinates(x, y)) return "incorrect coordinate";
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return "no unit or empty unit selected!";
        for (Person person : unit)
            if (person instanceof Engineer || person instanceof Tunneler)
                return "selected unit is not soldier!";
        MapCell aimMapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
        for (Person person : aimMapCell.getPeople())
            if (!person.getPersonOwner().equals(Game.getCurrentGame().getCurrentEmpire()))
                for (Person person1 : unit)
                    ((Soldier) person).setAim(aimMapCell);
        return "attack set successful!";
    }

    public String pourOil(Matcher matcher) {
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return "no unit or empty unit selected!";
        for (Person person : unit)
            if (!(person instanceof Engineer)) return "selected unit is not engineer!";
        int x = unit.get(0).getMapCell().getX();
        int y = unit.get(0).getMapCell().getY();
        String direction = matcher.group("direction");
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
                return "invalid direction!";
        }
        MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(x,y);
        for (Person person : unit) {
            Engineer engineer = (Engineer) person;
            if (engineer.hasOil()) {
                engineer.setOil(false);
                mapCell.setOilCondition(true);
                return "pour oil successful!";
            }
        }
        return "selected engineers don't have oil!";
    }

    public String digTunnel(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!checkCoordinates(x, y)) return "incorrect coordinate";
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return "no unit or empty unit selected!";
        for (Person person : unit)
            if (!(person instanceof Tunneler)) return "selected unit is not tunneler!";
        MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(x,y);
        ((Tunneler)unit.get(0)).setAimTunnel(mapCell);
        return "tunnel set for tunneler successful!";
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

    private ArrayList<MapCell> routing(MapCell origin, MapCell destination) {
        return null;
    }

    private boolean checkCoordinates(int x, int y) {
        int mapSize = Map.getCurrentMap().getMapSize();
        return x > 0 && x <= mapSize && y > 0 && y <= mapSize;
    }

}
