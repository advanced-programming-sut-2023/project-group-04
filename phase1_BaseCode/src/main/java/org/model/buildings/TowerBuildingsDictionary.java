package org.model.buildings;

public enum TowerBuildingsDictionary {
    LOOKOUT_TOWER(BuildingsDictionary.LOOKOUT_TOWER, 3, 0, 8),
    PERIMETER_TOWER(BuildingsDictionary.PERIMETER_TOWER, 3, 0, 8),
    DEFENCE_TURRET(BuildingsDictionary.DEFENCE_TURRET, 5, 0, 10),
    SQUARE_TOWER(BuildingsDictionary.SQUARE_TOWER, 5, 0, 12),
    ROUND_TOWER(BuildingsDictionary.ROUND_TOWER, 5, 0, 15);

    private BuildingsDictionary buildingDictionary;
    private int fireRange;
    private int defendRange;
    private int soldierCapacity;

    TowerBuildingsDictionary(BuildingsDictionary buildingsDictionary, int fireRange, int defendRange, int soldierCapacity) {
        this.buildingDictionary = buildingsDictionary;
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.soldierCapacity = soldierCapacity;
    }

    public BuildingsDictionary getBuildingDictionary() {
        return buildingDictionary;
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
            if (towerBuildingsDictionary.getBuildingDictionary().getName().equals(buildingName))
                return towerBuildingsDictionary;
        }
        return null;
    }
}
