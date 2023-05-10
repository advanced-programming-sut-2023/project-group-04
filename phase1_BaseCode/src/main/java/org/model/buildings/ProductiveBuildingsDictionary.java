package org.model.buildings;

import java.util.ArrayList;

public enum ProductiveBuildingsDictionary {
    MILL(BuildingsDictionary.MILL, 0, "wheat", new ArrayList<>() {
        {
            add("floor");
        }
    }, ""),
    IRON_MINE(BuildingsDictionary.IRON_MINE, 0, null, new ArrayList<>() {
        {
            add("iron");
        }
    }, "iron"),
    OX_TETHER(BuildingsDictionary.OX_TETHER, 0, null, new ArrayList<>() {
    }, ""),
    PITCH_RIG(BuildingsDictionary.PITCH_RIG, 0, "", new ArrayList<>() {
        {
            add("pitch");
        }
    }, ""),
    QUARRY(BuildingsDictionary.QUARRY, 0, "", new ArrayList<>() {
        {
            add("stone");
        }
    }, ""),
    WOODCUTTER(BuildingsDictionary.WOODCUTTER, 0, "", new ArrayList<>() {
        {
            add("wood");
        }
    }, ""),
    ARMOURER(BuildingsDictionary.ARMOURER, 0, "iron", new ArrayList<>() {
        {
            add("metal armour");
        }
    }, ""),
    BLACKSMITH(BuildingsDictionary.BLACKSMITH, 0, "iron", new ArrayList<>(){
        {add("metal armour");}
    }, ""),
    FLETCHER(BuildingsDictionary.FLETCHER, 0, "", new ArrayList<>() {
        {
            add("pitch");
        }
    }, ""),
    POLETURNER(BuildingsDictionary.POLETURNER, 0, "", new ArrayList<>() {
        {
            add("");
        }
    }, ""),
    STABLE(BuildingsDictionary.STABLE, 0, "", new ArrayList<>() {
        {
            add("horse");
        }
    }, ""),
    APPLE_ORCHARD(BuildingsDictionary.APPLE_ORCHARD, 0, "", new ArrayList<>() {
        {
            add("apple");
        }
    }, ""),
    DAIRY_FARMER(BuildingsDictionary.DIARY_FARMER, 0, "", new ArrayList<>() {
        {
            add("cheese");
        }
    }, ""),
    HOPS_FARMER(BuildingsDictionary.HOPS_FARMER, 0, "", new ArrayList<>() {
        {
            add("hop");
        }
    }, ""),
    WHEAT_FARMER(BuildingsDictionary.WHEAT_FARMER, 0, "", new ArrayList<>() {
        {
            add("wheat");
        }
    }, ""),
    HUNTER_POST(BuildingsDictionary.HUNTER_POST, 0, "", new ArrayList<>() {
        {
            add("meat");
        }
    }, ""),
    BAKERY(BuildingsDictionary.BAKERY, 0, "", new ArrayList<>() {
        {
            add("bread");
        }
    }, ""),
    BREWER(BuildingsDictionary.BREWER, 0, "", new ArrayList<>() {
        {
            add("");
        }
    }, "");

    private BuildingsDictionary buildingDictionary;
    private int rate;
    private String inputResource;
    private ArrayList<String> outputResource;
    private String groundTexture;

    private ProductiveBuildingsDictionary(BuildingsDictionary buildingsDictionary, int rate, String inputResource,
                                          ArrayList<String> outputResource, String groundTexture) {
        this.rate = rate;
        this.inputResource = inputResource;
        this.outputResource = outputResource;
        this.groundTexture = groundTexture;
    }

    public int getRate() {
        return rate;
    }

    public BuildingsDictionary getBuildingDictionary() {
        return buildingDictionary;
    }

    public String getInputResource() {
        return inputResource;
    }

    public ArrayList<String> getOutputResource() {
        return outputResource;
    }

    public String getGroundTexture() {
        return groundTexture;
    }

    public static ProductiveBuildingsDictionary getDictionaryByName(String buildingName) {
        for (ProductiveBuildingsDictionary productiveBuildingsDictionary : ProductiveBuildingsDictionary.values()) {
            if (productiveBuildingsDictionary.getBuildingDictionary().getName().equals(buildingName))
                return productiveBuildingsDictionary;
        }
        return null;
    }
}
