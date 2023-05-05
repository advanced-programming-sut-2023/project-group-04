package org.model.buildings;

public enum TowerBuildingsDictionary {
    LOOKOUT_TOWER(BuildingsDictionary.LOOKOUT_TOWER, 0, 0, 0),
    PERIMETER_TOWER(BuildingsDictionary.PERIMETER_TOWER, 0, 0, 0),
    DEFENCE_TURRET(BuildingsDictionary.DEFENCE_TURRET, 0, 0, 0),
    SQUARE_TOWER(BuildingsDictionary.SQUARE_TOWER, 0, 0, 0),
    ROUND_TOWER(BuildingsDictionary.ROUND_TOWER, 0, 0, 0);

    private BuildingsDictionary buildingsDictionary;
    private int fireRange;
    private int defendRange;
    private int soldierCapacity;

    TowerBuildingsDictionary(BuildingsDictionary buildingsDictionary, int fireRange, int defendRange, int soldierCapacity) {
        this.buildingsDictionary = buildingsDictionary;
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.soldierCapacity = soldierCapacity;
    }

    public BuildingsDictionary getBuildingsDictionary() {
        return buildingsDictionary;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }

    public int getSoldierCapacity() {
        return soldierCapacity;
    }

    public static TowerBuildingsDictionary getDictionaryByName(String buildingName) {
        for (TowerBuildingsDictionary towerBuildingsDictionary : TowerBuildingsDictionary.values()) {
            if (towerBuildingsDictionary.buildingsDictionary.getName().equals(buildingName))
                return towerBuildingsDictionary;
        }
        return null;
    }
}
