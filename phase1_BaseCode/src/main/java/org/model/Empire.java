package org.model;

import org.model.buildings.Building;
import org.model.buildings.StorageBuilding;
import org.model.buildings.StorageBuildingsDictionary;
import org.model.person.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class Empire {
    private final Player owner;
    private int foodRate;
    private int taxRate;
    private int fearRate;
    private final ArrayList<Person> population = new ArrayList<>();
    private final HashMap<String, Integer> popularity;

    private final ArrayList<StorageBuilding> allStockPiles;
    private final HashMap<String, Integer> resources;

    private final ArrayList<StorageBuilding> allGranaries;
    private final HashMap<String, Integer> food;

    private final ArrayList<StorageBuilding> allArmouries;
    private final HashMap<String, Integer> weaponAndArmour;

    private ArrayList<Trade> allTrades;
    private Building headquarter;

    public Empire(Player owner) {
        this.owner = owner;
        this.fearRate = 0;
        this.taxRate = -100;
        this.foodRate = 0;
        this.popularity = new HashMap<>();
        initializePopularity();
        this.resources = new HashMap<>();
        initializeResource();
        this.weaponAndArmour = new HashMap<>();
        initializeWeaponAndArmor();
        this.food = new HashMap<>();
        initializeFood();
        allStockPiles = new ArrayList<>();
        allGranaries = new ArrayList<>();
        allArmouries = new ArrayList<>();
        allTrades = new ArrayList<>();
    }

    private void initializeWeaponAndArmor() {
        this.weaponAndArmour.put("bow", 0);
        this.weaponAndArmour.put("crossbow", 0);
        this.weaponAndArmour.put("spear", 0);
        this.weaponAndArmour.put("pike", 0);
        this.weaponAndArmour.put("mace", 0);
        this.weaponAndArmour.put("sword", 0);
        this.weaponAndArmour.put("leather armour", 0);
        this.weaponAndArmour.put("metal armour", 0);
    }

    private void initializePopularity() {
        this.popularity.put("food", 0);
        this.popularity.put("religion", 0);
        this.popularity.put("tax", 0);
        this.popularity.put("fear", 0);
        this.popularity.put("ale", 0);
    }

    private void initializeResource() {
        String[] resources = {"wheat", "flour", "hops", "ale", "stone", "iron", "wood", "pitch", "gold"};
        for (String resource : resources) this.resources.put(resource, 0);
    }

    private void initializeFood() {
        this.food.put("meet", 0);
        this.food.put("apples", 0);
        this.food.put("cheese", 0);
        this.food.put("bread", 0);
    }

    public Player getOwner() {
        return owner;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public HashMap<String, Integer> getPopularity() {
        return popularity;
    }

    public HashMap<String, Integer> getResources() {
        return resources;
    }

    public HashMap<String, Integer> getFood() {
        return food;
    }

    public int getFoodAmount(String foodName) {
        return this.food.get(foodName);
    }

    public void changeResourceAmount(String resource, int amount) {
        ArrayList<StorageBuilding> storages = new ArrayList<>();
        if (StorageBuildingsDictionary.STOCKPILE.getObjects().contains(resource)) {
            resources.computeIfPresent(resource, (key, val) -> val + amount);
            storages = allStockPiles;
        } else if (StorageBuildingsDictionary.ARMOURY.getObjects().contains(resource)) {
            weaponAndArmour.computeIfPresent(resource, (key, val) -> val + amount);
            storages = allArmouries;
        } else if (StorageBuildingsDictionary.GRANARY.getObjects().contains(resource)) {
            food.computeIfPresent(resource, (key, val) -> val + amount);
            storages = allGranaries;
        }
        if (amount > 0) {
            increaseResourceFromStorageBuilding(storages, resource, amount);
        }else {
            decreaseResourceFromStorageBuilding(storages, resource, -1*amount);
        }
    }

    private void increaseResourceFromStorageBuilding(ArrayList<StorageBuilding> storageBuildings,
                                                     String itemsName, int itemsAmount) {
        for (StorageBuilding storageBuilding : storageBuildings) {
            int freeSpace = storageBuilding.getFreeSpace();
            if (freeSpace >= itemsAmount) {
                storageBuilding.changeResourcesAmount(itemsName, itemsAmount);
                break;
            } else if (freeSpace > 1) {
                storageBuilding.changeResourcesAmount(itemsName, freeSpace);
                itemsAmount -= freeSpace;
            }
        }
    }

    private void decreaseResourceFromStorageBuilding(ArrayList<StorageBuilding> storageBuildings,
                                                     String itemsName, int itemsAmount) {
        for (StorageBuilding storageBuilding : storageBuildings) {
            int availableAmount = storageBuilding.getResourceAmount(itemsName);
            if (availableAmount >= itemsAmount) {
                storageBuilding.changeResourcesAmount(itemsName, -1 * itemsAmount);
                break;
            } else if (availableAmount > 0) {
                storageBuilding.changeResourcesAmount(itemsName, availableAmount);
                itemsAmount -= availableAmount;
            }
        }
    }
    public void activateTaxRate() {
        this.taxRate = 0;
    }

    public int getResourceAmount(String resource) {
        return this.resources.get(resource);
    }

    public ArrayList<StorageBuilding> getAllStockPiles() {
        return allStockPiles;
    }

    public void addStockPile(StorageBuilding stockPile) {
        this.allStockPiles.add(stockPile);
    }

    public ArrayList<StorageBuilding> getAllGranaries() {
        return allGranaries;
    }

    public void addGranary(StorageBuilding granary) {
        this.allGranaries.add(granary);
    }

    public ArrayList<StorageBuilding> getAllArmouries() {
        return allArmouries;
    }

    public ArrayList<Trade> getAllTrades() {
        return allTrades;
    }

    public void addArmoury(StorageBuilding armoury) {
        this.allArmouries.add(armoury);
    }

    public void addPerson(Person person) {
        this.population.add(person);
    }

    public void removePerson(Person person) {
        this.population.remove(person);
    }

    public void removePerson(int index) {
        this.population.remove(index);
    }

    public ArrayList<Person> getPopulation() {
        return population;
    }

    public int getWeaponAndArmourAmount(String weaponOrArmour) {
        return weaponAndArmour.get(weaponOrArmour);
    }

    public void changePopularity(String type, int number) {
        popularity.put(type, popularity.get(type) + number);
    }

    public void setHeadquarter(Building headquarter) {
        this.headquarter = headquarter;
    }

    public Building getHeadquarter() {
        return headquarter;
    }

}
