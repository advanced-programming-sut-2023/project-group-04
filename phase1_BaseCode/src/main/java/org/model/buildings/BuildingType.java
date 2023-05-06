package org.model.buildings;

public enum BuildingType {
    CASTLE("Castle Buildings"),
    FARM("Farm Buildings"),
    FOOD("Food Processing Buildings"),
    INDUSTRY("Industry"),
    KEEPS("Keeps and Strongholds"),
    TOWER("Towers and Gatehouses"),
    TOWN("Town Buildings"),
    WEAPON("Weapon Buildings");

    private final String buildingType;

    BuildingType(String name) {
        this.buildingType = name;
    }

    public String getBuildingType() {
        return buildingType;
    }
}
