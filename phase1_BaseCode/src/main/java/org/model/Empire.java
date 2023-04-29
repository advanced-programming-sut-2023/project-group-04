package org.model;

import java.util.HashMap;

public class Empire {
    private final Player owner;
    private int foodRate;
    private int taxRate;
    private int fearRate;
    private final HashMap<String, Integer> popularity;
    private final HashMap<String, Integer> resources;

    private final HashMap<String, Integer> food;

    public Empire(Player owner) {
        this.owner = owner;
        this.fearRate = 0;
        this.taxRate = 0;
        this.foodRate = 0;
        this.popularity = new HashMap<>();
        initializePopularity();
        this.resources = new HashMap<>();
        initializeResource();
        this.food = new HashMap<>();
        initializeFood();
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
}
