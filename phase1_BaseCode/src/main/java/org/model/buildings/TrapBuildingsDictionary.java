package org.model.buildings;

public enum TrapBuildingsDictionary {

    KILLING_PIT(BuildingsDictionary.KILLING_PIT, 300),
    PITCH_DITCH(BuildingsDictionary.PITCH_DITCH, 300);

    private BuildingsDictionary buildingDictionary;
    private int damage;

    private TrapBuildingsDictionary(BuildingsDictionary buildingDictionary, int damage) {
        this.buildingDictionary = buildingDictionary;
        this.damage = damage;
    }

    public BuildingsDictionary getBuildingDictionary() {
        return buildingDictionary;
    }

    public int getDamage() {
        return damage;
    }

    public static TrapBuildingsDictionary getDictionaryByName(String buildingName) {
        for (TrapBuildingsDictionary trapBuildingsDictionary : TrapBuildingsDictionary.values()) {
            if (trapBuildingsDictionary.getBuildingDictionary().getName().equals(buildingName))
                return trapBuildingsDictionary;
        }
        return null;
    }
}
