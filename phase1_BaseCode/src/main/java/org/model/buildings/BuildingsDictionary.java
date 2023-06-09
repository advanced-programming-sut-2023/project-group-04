package org.model.buildings;

import java.util.HashMap;

public enum BuildingsDictionary {
    LOW_WALL("low wall", BuildingType.CASTLE, 2500, new HashMap<>() {
        {
            put("stone", 2);
        }
    }, 1, 0, 0),
    STONE_WALL("stone wall", BuildingType.CASTLE, 3000, new HashMap<>() {
        {
            put("stone", 4);
        }
    }, 1, 0, 0),
    CRENULATED_WALL("crenulated wall", BuildingType.CASTLE, 3500, new HashMap<>() {
        {
            put("stone", 6);
        }
    }, 1, 0, 0),
    STAIRS("stairs", BuildingType.CASTLE, 2000, new HashMap<>() {
        {
            put("stone", 4);
        }
    }, 1, 0, 0),
    HEAD_QUARTER("headquarter", BuildingType.CASTLE, 6000, new HashMap<>() {
    }, 1, 0, 0),
    SMALL_STONE_GATEHOUSE("small stone gatehouse", BuildingType.CASTLE, 2600, new HashMap<>() {
    }, 1, 0, 0),
    LARGE_STONE_GATEHOUSE("large stone gatehouse", BuildingType.CASTLE, 3200, new HashMap<>() {
        {
            put("stone", 20);
        }
    }, 1, 0, 0),
    DRAW_BRIDGE("draw bridge", BuildingType.CASTLE, 2200, new HashMap<>() {
        {
            put("wood", 10);
        }
    }, 1, 0, 0),
    LOOKOUT_TOWER("lookout tower", BuildingType.CASTLE, 2400, new HashMap<>() {
        {
            put("stone", 10);
        }
    }, 1, 0, 0),
    PERIMETER_TOWER("perimeter", BuildingType.CASTLE, 2400, new HashMap<>() {
        {
            put("stone", 10);
        }
    }, 1, 0, 0),
    DEFENCE_TURRET("defence turret", BuildingType.CASTLE, 2600, new HashMap<>() {
        {
            put("stone", 15);
        }
    }, 1, 0, 0),
    SQUARE_TOWER("square tower", BuildingType.CASTLE, 2800, new HashMap<>() {
        {
            put("stone", 35);
        }
    }, 1, 0, 0),
    ROUND_TOWER("round tower", BuildingType.CASTLE, 3000, new HashMap<>() {
        {
            put("stone", 40);
        }
    }, 1, 0, 0),
    ARMOURY("armoury", BuildingType.CASTLE, 1500, new HashMap<>() {
        {
            put("wood", 5);
        }
    }, 1, 0, 0),
    BARRACKS("barracks", BuildingType.CASTLE, 1500, new HashMap<>() {
        {
            put("stone", 15);
        }
    }, 1, 0, 0),
    MERCENARY_POST("mercenary post", BuildingType.CASTLE, 1500, new HashMap<>() {
        {
            put("wood", 15);
        }
    }, 1, 0, 0),
    ENGINEER_GUILD("engineer guild", BuildingType.CASTLE, 1500, new HashMap<>() {
        {
            put("wood", 10);
            put("gold", 100);
        }
    }, 1, 0, 0),
    KILLING_PIT("killing pit", BuildingType.CASTLE, -1, new HashMap<>() {
        {
            put("wood", 6);
        }
    }, 1, 0, 0),
    OIL_SMELTER("oil smelter", BuildingType.CASTLE, 1500, new HashMap<>() {
        {
            put("iron", 10);
            put("gold", 100);
        }
    }, 1, 1, 0),
    PITCH_DITCH("pitch ditch", BuildingType.CASTLE, -1, new HashMap<>() {
        {
            put("pitch", 1);
        }
    }, 1, 0, 0),
    CAGED_WAR_DOGS("caged war dogs", BuildingType.CASTLE, -1, new HashMap<>() {
        {
            put("wood", 10);
            put("gold", 100);
        }
    }, 1, 0, 0),
    SIEGE_TENT("siege tent", BuildingType.CASTLE, 500, new HashMap<>() {
    }, 1, 1, 0),
    STABLE("stable", BuildingType.CASTLE, 2500, new HashMap<>() {
        {
            put("wood", 20);
            put("gold", 400);
        }
    }, 1, 0, 0),
    TUNNELER_GUILD("tunneler guild", BuildingType.CASTLE, 2000, new HashMap<>() {
    }, 0, 0, 0),
    APPLE_ORCHARD("apple orchard", BuildingType.FARM, 1800, new HashMap<>() {
        {
            put("wood", 5);
        }
    }, 1, 0, 1),
    DIARY_FARMER("diary farmer", BuildingType.FARM, 1800, new HashMap<>() {
        {
            put("wood", 10);
        }
    }, 1, 0, 1),
    HOPS_FARMER("hops farmer", BuildingType.FARM, 1800, new HashMap<>() {
        {
            put("wood", 15);
        }
    }, 1, 0, 1),
    HUNTER_POST("hunter post", BuildingType.FARM, 1800, new HashMap<>() {
        {
            put("wood", 5);
        }
    }, 1, 0, 1),
    WHEAT_FARMER("wheat", BuildingType.FARM, 1800, new HashMap<>() {
        {
            put("wood", 15);
        }
    }, 1, 0, 1),
    BAKERY("bakery", BuildingType.FOOD, 2000, new HashMap<>() {
        {
            put("wood", 10);
        }
    }, 1, 0, 1),
    BREWER("brewer", BuildingType.FOOD, 2000, new HashMap<>() {
        {
            put("wood", 10);
        }
    }, 1, 0, 1),
    GRANARY("granary", BuildingType.FOOD, 1600, new HashMap<>() {
        {
            put("wood", 5);
        }
    }, 1, 0, 0),
    INN("inn", BuildingType.FOOD, 2600, new HashMap<>() {
        {
            put("wood", 20);
            put("gold", 100);
        }
    }, 1, 0, 1),
    MILL("mill", BuildingType.FOOD, 2200, new HashMap<>() {
        {
            put("wood", 20);
        }
    }, 1, 0, 3),
    IRON_MINE("iron mine", BuildingType.INDUSTRY, 2000, new HashMap<>() {
        {
            put("wood", 20);
        }
    }, 1, 0, 2),
    MARKET("market", BuildingType.INDUSTRY, 1800, new HashMap<>() {
        {
            put("wood", 5);
        }
    }, 1, 0, 1),
    OX_TETHER("ox tether", BuildingType.INDUSTRY, 1400, new HashMap<>() {
        {
            put("wood", 5);
        }
    }, 1, 0, 1),
    PITCH_RIG("pitch rig", BuildingType.INDUSTRY, 2000, new HashMap<>() {
        {
            put("wood", 20);
        }
    }, 1, 0, 1),
    QUARRY("quarry", BuildingType.INDUSTRY, 2000, new HashMap<>() {
        {
            put("wood", 20);
        }
    }, 1, 0, 3),
    STOCKPILE("stockpile", BuildingType.INDUSTRY, 1600, new HashMap<>() {
    }, 1, 0, 0),
    WOODCUTTER("woodcutter", BuildingType.INDUSTRY, 1600, new HashMap<>() {
        {
            put("wood", 3);
        }
    }, 1, 0, 1),
    HOVEL("hovel", BuildingType.TOWN, 1600, new HashMap<>() {
        {
            put("wood", 6);
        }
    }, 1, 0, 0),
    CHURCH("church", BuildingType.TOWN, 2600, new HashMap<>() {
        {
            put("gold", 250);
        }
    }, 1, 0, 0),
    CATHEDRAL("cathedral", BuildingType.TOWN, 3200, new HashMap<>() {
        {
            put("gold", 1000);
        }
    }, 1, 0, 0),
    ARMOURER("armourer", BuildingType.WEAPON, 1600, new HashMap<>() {
        {
            put("wood", 20);
            put("gold", 100);
        }
    }, 1, 0, 1),
    BLACKSMITH("blacksmith", BuildingType.WEAPON, 1600, new HashMap<>() {
        {
            put("wood", 20);
            put("gold", 100);
        }
    }, 1, 0, 1),
    FLETCHER("fletcher", BuildingType.WEAPON, 1600, new HashMap<>() {
        {
            put("wood", 20);
            put("gold", 100);
        }
    }, 1, 0, 1),
    POLETURNER("poleturner", BuildingType.WEAPON, 1600, new HashMap<>() {
        {
            put("wood", 10);
            put("gold", 100);
        }
    }, 1, 0, 1);

    private final String name;
    private final BuildingType type;
    private final int hp;
    private final HashMap<String, Integer> prices;
    private final int size;
    private final int engineerNumber;
    private final int workerNumber;

    BuildingsDictionary(String name, BuildingType type, int hp,
                        HashMap<String, Integer> prices, int size, int engineerNumber, int workerNumber) {
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.prices = prices;
        this.size = size;
        this.engineerNumber = engineerNumber;
        this.workerNumber = workerNumber;
    }

    public String getName() {
        return name;
    }

    public BuildingType getType() {
        return type;
    }

    public int getHp() {
        return hp;
    }

    public HashMap<String, Integer> getPrices() {
        return prices;
    }

    public int getSize() {
        return size;
    }

    public int getEngineerNumber() {
        return engineerNumber;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }

    public static BuildingsDictionary getDictionaryByName(String buildingName) {
        for (BuildingsDictionary buildingsDictionary : BuildingsDictionary.values()) {
            if (buildingsDictionary.getName().equals(buildingName) && !buildingName.equals("headquarter"))
                return buildingsDictionary;
        }
        return null;
    }

}

