package org.model.buildings;

public enum TrapBuildingsDictionary {

    KILLING_PIT(BuildingsDictionary.KILLING_PIT,0),
    PITCH_DITCH(BuildingsDictionary.KILLING_PIT,0);

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
}
