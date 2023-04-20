package org.model.buildings;

import org.model.buildings.BuildingType;

import java.util.HashMap;

public enum BuildingsDictionary {

    SMALL_STONE_GATEHOUSE("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    LARGE_STONE_GATEHOUSE("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    DRAW_BRIDGE("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    LOOKOUT_TOWER("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    PERIMETER_TOWER("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    DEFENCE_TURRET("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    SQUARE_TOWER("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    ROUND_TOWER("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    ARMORY("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    BARRACKS("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    MERCENARY_POST("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    ENGINEER_GUILD("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    KILLING_PIT("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    OIL_SMELTER("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    PITCH_DITCH("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    CAGED_WAR_DOGS("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    SIEGE_TENT("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    STABLE("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    TUNNELER_GUILD("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    APPLE_ORCHARD("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    DIARY_FARMER("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    HOPS_FARMER("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    HUNTER_POST("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    WHEAT_FARMER("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    BAKERY("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    BREWER("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    GRANARY("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    INN("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    MILL("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    IRON_MINE("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    MARKET("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    OX_TETHER("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    PITCH_RIG("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    QUARRY("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    STOCKPILE("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    WOODCUTTER("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    HOVEL("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    CHURCH("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    CATHEDRAL("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    ARMOURER("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    BLACKSMITH("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    FLETCHER("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0),
    POLETURNER("", "", 0, new HashMap<String, Integer>() {
    }, 0, 0, 0);

    private String name;
    private String type;
    private int hp;
    private HashMap<String, Integer> prices;
    private int size;
    private int engineerNumber;
    private int workerNumber;

    BuildingsDictionary(String name, String type, int hp,
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

    public String getType() {
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

