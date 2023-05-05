package org.model.buildings;

public enum TrapBuildingsDictionary {

    KILLING_PIT(BuildingsDictionary.KILLING_PIT, 0),
    PITCH_DITCH(BuildingsDictionary.KILLING_PIT, 0);

    private BuildingsDictionary buildingsDictionary;
    private int damage;

    private TrapBuildingsDictionary(BuildingsDictionary buildingsDictionary, int damage) {
        this.buildingsDictionary = buildingsDictionary;
        this.damage = damage;
    }

    public BuildingsDictionary getBuildingDictionary() {
        return buildingsDictionary;
    }

    public int getDamage() {
        return damage;
    }

    public static TrapBuildingsDictionary getDictionaryByName(String buildingName) {
        for (TrapBuildingsDictionary trapBuildingsDictionary : TrapBuildingsDictionary.values()) {
            if (trapBuildingsDictionary.buildingsDictionary.getName().equals(buildingName))
                return trapBuildingsDictionary;
        }
        return null;
    }
}
