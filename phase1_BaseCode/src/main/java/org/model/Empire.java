package org.model;

import org.model.buildings.StorageBuilding;

import java.util.ArrayList;
import java.util.HashMap;

public class Empire {
    private final Player owner;
    private int foodRate;
    private int taxRate;
    private int fearRate;
    private final HashMap<String, Integer> popularity;
    private final HashMap<String, Integer> resources;
    private final HashMap<String, Integer> food;
    private final ArrayList<StorageBuilding> allStockPiles;
    private final ArrayList<StorageBuilding> allGranaries;
    private final ArrayList<StorageBuilding> allArmouries;

    public Empire(Player owner) {
        this.owner = owner;
        this.fearRate = 0;
        this.taxRate = -100;
        this.foodRate = 0;
        this.popularity = new HashMap<>();
        initializePopularity();
        this.resources = new HashMap<>();
        initializeResource();
        this.food = new HashMap<>();
        initializeFood();
        allStockPiles = new ArrayList<>();
        allGranaries = new ArrayList<>();
        allArmouries = new ArrayList<>();
    }

    private void initializePopularity() {
        this.popularity.put("food", 0);
        this.popularity.put("religion", 0);
        this.popularity.put("tax", 0);
        this.popularity.put("fear", 0);
    }

    private void initializeResource() {
        String[] resources = {"wheat", "flour", "hops", "ale", "stone", "iron", "wood", "pitch"};
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

    public void ChangeResourceAmount(String resource, int amount) {
        this.resources.put(resource, resources.get(resource) + amount);
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

    public void addArmoury(StorageBuilding armoury) {
        this.allArmouries.add(armoury);
    }
}
