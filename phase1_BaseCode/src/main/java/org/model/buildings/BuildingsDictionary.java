package org.model.buildings;

import java.util.HashMap;

public enum BuildingsDictionary {

    SMALL_STONE_GATEHOUSE("small stone gatehouse", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    LARGE_STONE_GATEHOUSE("large stone gatehouse", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    DRAW_BRIDGE("draw bridge", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    LOOKOUT_TOWER("lookout tower", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    PERIMETER_TOWER("perimeter", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    DEFENCE_TURRET("defence turret", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    SQUARE_TOWER("square tower", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    ROUND_TOWER("round tower", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    ARMOURY("armoury", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    BARRACKS("barracks", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    MERCENARY_POST("mercenary post", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    ENGINEER_GUILD("engineer guild", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    KILLING_PIT("killing pit", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    OIL_SMELTER("oil smelter", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    PITCH_DITCH("pitch ditch", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    CAGED_WAR_DOGS("caged war dogs", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    SIEGE_TENT("siege tent", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    STABLE("stable", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    TUNNELER_GUILD("", BuildingType.CASTLE, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    APPLE_ORCHARD("apple orchard", BuildingType.FARM, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    DIARY_FARMER("diary farmer", BuildingType.FARM, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    HOPS_FARMER("hops farmer", BuildingType.FARM, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    HUNTER_POST("hunter post", BuildingType.FARM, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    WHEAT_FARMER("wheat", BuildingType.FARM, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    BAKERY("bakery", BuildingType.FOOD, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    BREWER("brewer", BuildingType.FOOD, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    GRANARY("granary", BuildingType.FOOD, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    INN("inn", BuildingType.FOOD, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    MILL("mill", BuildingType.FOOD, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    IRON_MINE("iron mine", BuildingType.INDUSTRY, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    MARKET("market", BuildingType.INDUSTRY, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    OX_TETHER("ox tether", BuildingType.INDUSTRY, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    PITCH_RIG("pitch rig", BuildingType.INDUSTRY, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    QUARRY("quarry", BuildingType.INDUSTRY, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    STOCKPILE("stockpile", BuildingType.INDUSTRY, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    WOODCUTTER("woodcutter", BuildingType.INDUSTRY, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    HOVEL("hovel", BuildingType.TOWN, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    CHURCH("church", BuildingType.TOWN, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    CATHEDRAL("cathedral", BuildingType.TOWN, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    ARMOURER("armourer", BuildingType.WEAPON, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    BLACKSMITH("blacksmith", BuildingType.WEAPON, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    FLETCHER("fletcher", BuildingType.WEAPON, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    POLETURNER("poleturner", BuildingType.WEAPON, 0, new HashMap<String, Integer>() {
    }, 0, 0, 0);

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

}

