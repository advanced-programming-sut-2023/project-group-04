package org.controller;

import org.model.Empire;
import org.model.Game;
import org.model.MapCell;
import org.model.buildings.*;
import org.model.map.Map;
import org.model.person.*;
import org.view.CommandsEnum.GameMessages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
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
        int taxRate = Integer.parseInt(matcher.group("taxRate"));
        if (taxRate < -3 || taxRate > 8)
            return GameMessages.INVALID_TAX_RATE;
        Game.getCurrentGame().getCurrentEmpire().setTaxRate(taxRate);
        return GameMessages.CHANGE_TAX_RATE;
    }

    public String showTaxRate() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        return "your tax rate is : <<" + empire.getTaxRate() + ">>\n";
    }

    public GameMessages changeFearRate(Matcher matcher) {
        int fearRate = Integer.parseInt(matcher.group("fearRate"));
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
        int x = Integer.parseInt(matcher.group("x")) - 1;
        int y = Integer.parseInt(matcher.group("y")) - 1;
        String buildingName = matcher.group("type");
        boolean upDirection = matcher.group("direction").equals("up");
        if (!checkCoordinates(x, y)) return GameMessages.INVALID_POSITION;
        MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
        if (mapCell.getBuilding() != null) return GameMessages.EXISTENCE_BUILDING;
        if (BuildingsDictionary.getDictionaryByName(buildingName) == null) return GameMessages.INCORRECT_BUILDING_TYPE;
        if (checkGroundTexture(mapCell, buildingName)) return GameMessages.INVALID_TEXTURE_TREE;
        if (buildingName.equals("draw bridge") && !checkDrawBridge(x, y, upDirection))
            return GameMessages.INVALID_DRAWBRIDGE_POSITION;
        return createBuilding(empire, x, y, buildingName, upDirection);
    }

    private boolean checkGroundTexture(MapCell mapCell, String buildingName) {
        String texture = mapCell.getGroundTexture();
        String[] invalidTextures = {"OIL", "PLAIN", "LOW_WATER", "RIVER", "SMALL_POND", "LARGE_POND", "SEA"};
        for (String invalidTexture : invalidTextures)
            if (texture.equals(invalidTexture)) return false;
        if (texture.contains("ROCK") || !mapCell.getTree().equals("LITTLE_CHERRY")) return false;
        ProductiveBuildingsDictionary dictionary = ProductiveBuildingsDictionary.getDictionaryByName(buildingName);
        return dictionary == null || dictionary.getGroundTexture().contains(texture);
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

    public GameMessages selectBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x")) - 1;
        int y = Integer.parseInt(matcher.group("y")) - 1;
        if (!checkCoordinates(x, y)) return GameMessages.INVALID_POSITION;
        Building building = Game.getCurrentGame().getMapCellByAddress(x, y).getBuilding();
        if (building == null) return GameMessages.BUILDING_NOT_EXIST;
        if (!building.getBuildingOwner().equals(Game.getCurrentGame().getCurrentEmpire()))
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
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        String unitType = matcher.group("type");
        int count = Integer.parseInt(matcher.group("count"));
        Building currentBuilding = Game.getCurrentGame().getSelectedBuilding();
        MapCell mapCell = currentBuilding.getMapCell();
        int buildingPosX = mapCell.getX();
        int buildingPosY = mapCell.getY();
        if (!(currentBuilding instanceof TrainerBuilding)) return "invalid building type for creating unit!!";
        if (!((TrainerBuilding) currentBuilding).getAvailableSoldiers().contains(unitType)) return "invalid unit type!";
        if (count <= 0) return "invalid troop count!";
        if (unitType.equals("engineer")) {
            if (count * Engineer.getRequiredGold() < empire.getResourceAmount("gold")) return "not enough gold";
            if (!removeFreePeople(empire, count)) return "not enough population for creating unit!!";
            mapCell.addPeople(new Engineer(empire, mapCell));
            return "create unit successful";
        } else if (unitType.equals("tunneler")) {
            if (count * Tunneler.getRequiredGold() < empire.getResourceAmount("gold")) return "not enough gold";
            if (!removeFreePeople(empire, count)) return "not enough population for creating unit!!";
            mapCell.addPeople(new Tunneler(empire, mapCell));
            return "create unit successful";
        }
        return addSoldier(empire, unitType, count, buildingPosX, buildingPosY);
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
            if (soldierDictionary.equals(SoldiersDictionary.ASSASSIN))
                mapCell.addPeople(new Assassins(empire, mapCell));
            else mapCell.addPeople(new Soldier(empire, soldierDictionary, mapCell));
        }
        return "create unit successful";
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
                if (existWeaponNum <= weaponNum) {
                    armoury.changeResourcesAmount(weapon, -existWeaponNum);
                    weaponNum -= existWeaponNum;
                } else {
                    armoury.changeResourcesAmount(weapon, -weaponNum);
                    weaponNum = 0;
                }
            }
            if (armourNum > 0) {
                if (existArmourNum <= armourNum) {
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
        int x = Integer.parseInt(matcher.group("x")) - 1;
        int y = Integer.parseInt(matcher.group("y")) - 1;
        String type = matcher.group("type");
        if (!checkCoordinates(x, y)) return "incorrect coordinate";
        if (type != null && !type.equals("engineer") && !type.equals("tunneler") &&
                !SoldiersDictionary.getAllSoldierTypes().contains(type)) return "invalid type!";
        ArrayList<Person> selectedUnit = new ArrayList<>();
        for (Person person : Game.getCurrentGame().getMapCellByAddress(x, y).getPeople()) {
            if ((type == null || type.equals("engineer")) && person instanceof Engineer) selectedUnit.add(person);
            else if ((type == null || type.equals("tunneler")) && person instanceof Tunneler) selectedUnit.add(person);
            else if (person instanceof Soldier)
                if (type == null || ((Soldier) person).getSoldierType().equals(type)) selectedUnit.add(person);
        }
        if (selectedUnit.size() == 0) return "no troop selected!";
        Game.getCurrentGame().selectUnit(selectedUnit);
        return "unit selected successfully!";
    }

    public String moveUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x")) - 1;
        int y = Integer.parseInt(matcher.group("y")) - 1;
        if (!checkCoordinates(x, y)) return "incorrect coordinate";
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return "no unit or empty unit selected!";
        MapCell destination = Game.getCurrentGame().getMapCellByAddress(x, y);
        if (routing(unit.get(0).getMapCell(), destination) == null) return "No way to target!";
        String[] invalidTextures = {"OIL", "PLAIN", "LOW_WATER", "RIVER", "SMALL_POND", "LARGE_POND", "SEA"};
        for (String invalidTexture : invalidTextures)
            if (destination.getGroundTexture().contains("ROCK") || destination.getGroundTexture().equals(invalidTexture))
                return "selected destination is on water or rock!";
        for (Person person : unit) {
            if (person instanceof Soldier) ((Soldier) person).setAim(null);
            person.setCurrentDestination(Game.getCurrentGame().getMapCellByAddress(x, y));
            person.setNextDestination(null);
            Game.getCurrentGame().addMoveAblePerson(person);
        }
        return "move troop successful";
    }

    public String patrolUnit(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1")) - 1;
        int y1 = Integer.parseInt(matcher.group("y1")) - 1;
        int x2 = Integer.parseInt(matcher.group("x2")) - 1;
        int y2 = Integer.parseInt(matcher.group("y2")) - 1;
        if (!checkCoordinates(x1, y1) || !checkCoordinates(x2, y2)) return "incorrect coordinate";
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return "no unit or empty unit selected!";
        MapCell destination1 = Game.getCurrentGame().getMapCellByAddress(x1, y1);
        MapCell destination2 = Game.getCurrentGame().getMapCellByAddress(x2, y2);
        if (routing(unit.get(0).getMapCell(), destination1) == null || routing(destination1, destination2) == null)
            return "No way to Selected target!";
        for (Person person : unit) {
            if (person instanceof Soldier) ((Soldier) person).setAim(null);
            person.setCurrentDestination(Game.getCurrentGame().getMapCellByAddress(x1, y1));
            person.setNextDestination(Game.getCurrentGame().getMapCellByAddress(x2, y2));
            Game.getCurrentGame().addMoveAblePerson(person);
        }
        return "patrol unit successful";
    }

    public String setUnitMode(Matcher matcher) {
        String mode = matcher.group("mode");
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return "no unit or empty unit selected!";
        for (Person person : unit)
            if (person instanceof Tunneler) return "selected unit is not soldier or engineer!";
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
        for (Person person : unit) person.setMode(modeNum);
        return "mode set successful!";
    }

    public String airAttack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x")) - 1;
        int y = Integer.parseInt(matcher.group("y")) - 1;
        if (!checkCoordinates(x, y)) return "incorrect coordinate";
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return "no unit or empty unit selected!";
        for (Person person : unit)
            if (person instanceof Engineer || person instanceof Tunneler) return "selected unit is not soldier!";
        for (Person person : unit) {
            SoldiersDictionary dictionary = ((Soldier) unit.get(0)).getSoldiersDictionary();
            if (!dictionary.equals(SoldiersDictionary.ARCHER) && !dictionary.equals(SoldiersDictionary.CROSSBOWMAN) &&
                    !dictionary.equals(SoldiersDictionary.ARABIANBOWS) && !dictionary.equals(SoldiersDictionary.HORSE_ARCHER))
                return "selected soldiers are not long range!";
        }
        boolean inRange = false;
        for (Person person : unit) {
            Soldier soldier = (Soldier) person;
            int distance = (int) Math.sqrt(Math.pow(x - soldier.getMapCell().getX(), 2) +
                    Math.pow(y - soldier.getMapCell().getY(), 2));
            if (soldier.getSoldiersDictionary().getFireRange() > distance){
                inRange = true;
                soldier.setAim(Game.getCurrentGame().getMapCellByAddress(x, y));
            }
        }
        if (!inRange) return "aim out of range!";
        return "attack set successful!";
    }

    public String Attack(Matcher matcher) {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        int x = Integer.parseInt(matcher.group("x")) - 1;
        int y = Integer.parseInt(matcher.group("y")) - 1;
        if (!checkCoordinates(x, y)) return "incorrect coordinate";
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return "no unit or empty unit selected!";
        for (Person person : unit)
            if (!(person instanceof Soldier)) return "selected unit is not soldier!";
        MapCell aimMapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
        Building aimBuilding = aimMapCell.getBuilding();
        boolean enemy = false;
        for (Person person : aimMapCell.getPeople()) {
            if (!person.getPersonOwner().equals(empire)) {
                enemy = true;
                break;
            }
        }
        if ((aimBuilding == null || aimBuilding.getBuildingOwner().equals(empire)) && !enemy)
            return "no enemy or building in target tile!";
        for (Person person : unit) {
            person.setCurrentDestination(null);
            ((Soldier) person).setAim(aimMapCell);
        }
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
        MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
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
        int x = Integer.parseInt(matcher.group("x")) - 1;
        int y = Integer.parseInt(matcher.group("y")) - 1;
        if (!checkCoordinates(x, y)) return "incorrect coordinate";
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return "no unit or empty unit selected!";
        for (Person person : unit)
            if (!(person instanceof Tunneler)) return "selected unit is not tunneler!";
        MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
        ((Tunneler) unit.get(0)).setAimTunnel(mapCell);
        return "tunnel set for tunneler successful!";
    }

    public String engineerBuild(Matcher matcher) {
        return null;
    }

    public String disbandUnit() {
        ArrayList<Person> unit = Game.getCurrentGame().getSelectedUnit();
        if (unit == null || unit.size() == 0) return "no unit or empty unit selected!";
        MapCell mapCell = unit.get(0).getMapCell();
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        for (Person person : unit) {
            empire.removePerson(person);
            mapCell.removePeople(person);
            Person newPerson = new Person(empire, mapCell);
            mapCell.addPeople(newPerson);
            empire.addPerson(newPerson);
        }
        return "unit disbanded!";
    }

    public void nextTurn() {
        moveAndPatrolTroops();
        fights();
        updateRates();
        updateThingsWithRate();
        buildingOperations();
        checkEndGame();
    }

    private void moveAndPatrolTroops() {
        for (Person person : Game.getCurrentGame().getToMovePeople()) {
            if (person.getCurrentDestination() != null) {
                ArrayList<MapCell> path = routing(person.getMapCell(), person.getCurrentDestination());
                if (path == null) continue;
                int moveTileNumber = Math.min(person.getSpeed(), path.size() - 1);
                MapCell goal = path.get(moveTileNumber);
                for (int i = 0; i < moveTileNumber; i++) {
                    //todo : handle the traps
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

    private void fights() {
        for (int i = 0; i < Game.getCurrentGame().getMapSize(); i++) {
            for (MapCell mapCell : Game.getCurrentGame().getMap()[i]) {
                if (mapCell.getPeople() != null) {
                    for (Empire empire : Game.getCurrentGame().getAllEmpires()) {
                        ArrayList<Soldier> thisEmpire = new ArrayList<>();
                        ArrayList<Soldier> otherEmpires = new ArrayList<>();
                        for (Person person : mapCell.getPeople()) {
                            if (person instanceof Soldier) {
                                Soldier soldier = (Soldier) person;
                                int index = new Random().nextInt(otherEmpires.size());
                                if (person.getPersonOwner().equals(empire)) thisEmpire.add(soldier);
                                else otherEmpires.add(index, soldier);
                            }
                        }
                        for (int j = 0; j < thisEmpire.size(); j++) {
                            int damage = thisEmpire.get(j).getDefensivePower() - otherEmpires.get(j).getDefensivePower();
                            if (damage > 0) otherEmpires.get(j).damagePerson(damage);
                        }
                    }
                }
            }
        }
    }

    private void updateRates() {
        for (Empire empire : Game.getCurrentGame().getAllEmpires()) {
            //food rate
            int availableFood = 0;
            for (String food : empire.getFood().keySet()) availableFood += empire.getFoodAmount(food);
            int requiredFood = (int) ((empire.getFoodRate() + 2) * empire.getPopulation().size() / 2);
            if (availableFood < requiredFood) empire.setFoodRate(-2);
            //tax rate
            if (empire.getTaxRate() < 0) {
                int totalTax = (int) ((empire.getTaxRate() * (-0.2) + 0.4) * empire.getPopulation().size());
                if (empire.getResourceAmount("gold") < totalTax) empire.setTaxRate(0);
            }
        }
    }

    private void updateThingsWithRate() {
        for (Empire empire : Game.getCurrentGame().getAllEmpires()) {
            //food
            int foodTypes = 0;
            for (String food : empire.getFood().keySet())
                if (empire.getFood().get(food) != 0) foodTypes++;
            empire.changePopularity("food", foodTypes - 1);
            empire.changePopularity("food", 4 * empire.getFoodRate());
            int requiredFood = (int) ((empire.getFoodRate() + 2) * empire.getPopulation().size() / 2);
            while (requiredFood > 0) {
                for (String food : empire.getFood().keySet()) {
                    if (empire.getFoodAmount(food) > 0) {
                        empire.changeFoodAmount(food, -1);
                        requiredFood--;
                    }
                }
            }
            //tax
            int taxRate = empire.getTaxRate();
            int totalTax = (int) ((Math.abs(taxRate) * 0.2 + 0.4) * empire.getPopulation().size());
            if (taxRate < 0) totalTax *= (-1);
            empire.changeResourceAmount("gold", totalTax);
            int totalPopularity = (-2) * taxRate + (totalTax <= 0 ? 1 : 0);
            empire.changePopularity("tax", totalPopularity);
            //fear
            empire.changePopularity("fear", -empire.getFearRate());
            // religion
            //todo : update popularity by buildings that add popularity
        }
    }

    private void buildingOperations() {

    }

    private void checkEndGame() {

    }

    private boolean BFS(MapCell origin, MapCell destination, HashMap<MapCell, MapCell> predecessor) {
        LinkedList<MapCell> queue = new LinkedList<MapCell>();
        ArrayList<MapCell> visited = new ArrayList<>();
        visited.add(origin);
        queue.add(origin);
        while (!queue.isEmpty()) {
            MapCell currentNode = queue.remove();
            int x = currentNode.getX();
            int y = currentNode.getY();
            MapCell[] neighbors = new MapCell[]{
                    Game.getCurrentGame().getMapCellByAddress(x + 1, y),
                    Game.getCurrentGame().getMapCellByAddress(x - 1, y),
                    Game.getCurrentGame().getMapCellByAddress(x, y + 1),
                    Game.getCurrentGame().getMapCellByAddress(x, y - 1)
            };
            for (MapCell nextNode : neighbors) {
                if (!visited.contains(nextNode) && checkNode(x, y, nextNode)) {
                    visited.add(nextNode);
                    predecessor.put(nextNode, currentNode);
                    queue.add(nextNode);
                    if (nextNode.equals(destination)) return true;
                }
            }
        }
        return false;
    }

    private boolean checkNode(int x, int y, MapCell nextNode) {
        Building nextBuilding = nextNode.getBuilding();
        if (nextNode.getBuilding() != null) {
            BuildingsDictionary nextBuildingDictionary = nextBuilding.getBuildingDictionary();
            if ((nextBuildingDictionary != BuildingsDictionary.SMALL_STONE_GATEHOUSE &&
                    nextBuildingDictionary != BuildingsDictionary.LARGE_STONE_GATEHOUSE) ||
                    (((StructuralBuilding) nextNode.getBuilding()).isUpside() && (nextNode.getX() != x)) ||
                    (!((StructuralBuilding) nextNode.getBuilding()).isUpside() && (nextNode.getX() != y)))
                return false;
        } else if (nextNode.getGroundTexture().contains("ROCK") || nextNode.getGroundTexture().equals("SEA"))
            return false;
        return true;
    }

    private ArrayList<MapCell> routing(MapCell origin, MapCell destination) {
        HashMap<MapCell, MapCell> predecessor = new HashMap<>();
        if (!BFS(origin, destination, predecessor)) return null;
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
        int mapSize = Map.getCurrentMap().getMapSize();
        return x >= 0 && x < mapSize && y >= 0 && y < mapSize;
    }

}