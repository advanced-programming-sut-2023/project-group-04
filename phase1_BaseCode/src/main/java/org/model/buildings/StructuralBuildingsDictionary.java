package org.model.buildings;

public enum StructuralBuildingsDictionary {
    SMALL_STONE_GATEHOUSE(BuildingsDictionary.SMALL_STONE_GATEHOUSE, 8),
    LARGE_STONE_GATEHOUSE(BuildingsDictionary.LARGE_STONE_GATEHOUSE, 10),
    DRAW_BRIDGE(BuildingsDictionary.DRAW_BRIDGE, 0),
    HOVEL(BuildingsDictionary.HOVEL, 8);

    private final BuildingsDictionary buildingDictionary;
    private final int capacity;

    private StructuralBuildingsDictionary(BuildingsDictionary buildingDictionary, int capacity) {
        this.capacity = capacity;
        this.buildingDictionary = buildingDictionary;
    }

    public BuildingsDictionary getBuildingDictionary() {
        return buildingDictionary;
    }

    public int getCapacity() {
        return capacity;
    }

    public static StructuralBuildingsDictionary getDictionaryByName(String buildingName) {
        for (StructuralBuildingsDictionary structuralBuildingsDictionary : StructuralBuildingsDictionary.values()) {
            if (structuralBuildingsDictionary.getBuildingDictionary().getName().equals(buildingName))
                return structuralBuildingsDictionary;
        }
        return null;
    }
}
