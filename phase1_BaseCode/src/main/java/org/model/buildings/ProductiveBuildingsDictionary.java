package org.model.buildings;

public enum ProductiveBuildingsDictionary {
    MILL(BuildingsDictionary.MILL, 0, "wheat", "floor", ""),
    IRON_MINE(BuildingsDictionary.ARMOURY, 0, "", "", "iron"),
    OX_TETHER(BuildingsDictionary.OX_TETHER, 0, "", "", ""),
    PITCH_RIG(BuildingsDictionary.PITCH_RIG, 0, "", "", ""),
    QUARRY(BuildingsDictionary.QUARRY, 0, "", "", ""),
    WOODCUTTER(BuildingsDictionary.WOODCUTTER, 0, "", "", ""),
    ARMOURER(BuildingsDictionary.ARMOURER, 0, "", "", ""),
    BLACKSMITH(BuildingsDictionary.BLACKSMITH, 0, "", "", ""),
    FLETCHER(BuildingsDictionary.FLETCHER, 0, "", "", ""),
    POLETURNER(BuildingsDictionary.POLETURNER, 0, "", "", ""),
    STABLE(BuildingsDictionary.STABLE, 0, "", "", ""),
    APPLE_ORCHARD(BuildingsDictionary.APPLE_ORCHARD, 0, "", "", ""),
    DAIRY_FARMER(BuildingsDictionary.DIARY_FARMER, 0, "", "", ""),
    HOPS_FARMER(BuildingsDictionary.HOPS_FARMER, 0, "", "", ""),
    WHEAT_FARMER(BuildingsDictionary.WHEAT_FARMER, 0, "", "", ""),
    HUNTER_POST(BuildingsDictionary.HUNTER_POST, 0, "", "", ""),
    BAKERY(BuildingsDictionary.BAKERY, 0, "", "", ""),
    BREWER(BuildingsDictionary.BREWER, 0, "", "", "");

    private BuildingsDictionary buildingsDictionary;
    private int rate;
    private String inputResource;
    private String outputResource;
    private String groundTexture;

    private ProductiveBuildingsDictionary(BuildingsDictionary buildingsDictionary, int rate, String inputResource, String outputResource, String groundTexture) {
        this.rate = rate;
        this.inputResource = inputResource;
        this.outputResource = outputResource;
        this.groundTexture = groundTexture;
    }

    public int getRate() {
        return rate;
    }

    public BuildingsDictionary getBuildingsDictionary() {
        return buildingsDictionary;
    }

    public String getInputResource() {
        return inputResource;
    }

    public String getOutputResource() {
        return outputResource;
    }

    public String getGroundTexture() {
        return groundTexture;
    }

    public static ProductiveBuildingsDictionary getDictionaryByName(String buildingName) {
        for (ProductiveBuildingsDictionary productiveBuildingsDictionary : ProductiveBuildingsDictionary.values()) {
            if (productiveBuildingsDictionary.buildingsDictionary.getName().equals(buildingName))
                return productiveBuildingsDictionary;
        }
        return null;
    }
}
