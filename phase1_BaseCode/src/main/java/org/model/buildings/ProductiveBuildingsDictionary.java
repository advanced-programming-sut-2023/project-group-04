package org.model.buildings;

import org.model.ResourcesDictionary;

import java.util.ArrayList;

public enum ProductiveBuildingsDictionary {
    MILL(BuildingsDictionary.MILL, 1, ResourcesDictionary.WHEAT.getName(), new ArrayList<>() {
        {
            add(ResourcesDictionary.FLOUR.getName());
        }
    }, new ArrayList<>() {
        {
            add("GROUND");
            add("SANDY_GROUND");
            add("GRASSLAND");
            add("LOW_MEADOW");
            add("HIGH_MEADOW");
            add("PLAIN");
        }
    }),
    IRON_MINE(BuildingsDictionary.IRON_MINE, 4, null, new ArrayList<>() {
        {
            add(ResourcesDictionary.IRON.getName());
        }
    }, new ArrayList<>() {
        {
            add("IRON_GROUND");
        }
    }),
    OX_TETHER(BuildingsDictionary.OX_TETHER, 0, null, new ArrayList<>() {
    }, new ArrayList<>() {
        {
            add("GROUND");
            add("SANDY_GROUND");
            add("GRASSLAND");
            add("LOW_MEADOW");
            add("HIGH_MEADOW");
            add("PLAIN");
        }
    }),
    PITCH_RIG(BuildingsDictionary.PITCH_RIG, 2, null, new ArrayList<>() {
        {
            add(ResourcesDictionary.PITCH.getName());
        }
    }, new ArrayList<>() {
        {
            add("OIL");
        }
    }),
    QUARRY(BuildingsDictionary.QUARRY, 10, null, new ArrayList<>() {
        {
            add(ResourcesDictionary.STONE.getName());
        }
    }, new ArrayList<>() {
        {
            add("STONE_GROUND");
        }
    }),
    WOODCUTTER(BuildingsDictionary.WOODCUTTER, 12, null, new ArrayList<>() {
        {
            add(ResourcesDictionary.WOOD.getName());
        }
    }, new ArrayList<>() {
        {
            add("GROUND");
            add("SANDY_GROUND");
            add("GRASSLAND");
            add("LOW_MEADOW");
            add("HIGH_MEADOW");
            add("PLAIN");
        }
    }),
    ARMOURER(BuildingsDictionary.ARMOURER, 1, "iron", new ArrayList<>() {
        {
            add(ResourcesDictionary.METAL_ARMOUR.getName());
        }
    }, new ArrayList<>() {
        {
            add("GROUND");
            add("SANDY_GROUND");
            add("GRASSLAND");
            add("LOW_MEADOW");
            add("HIGH_MEADOW");
            add("PLAIN");
        }
    }),
    BLACKSMITH(BuildingsDictionary.BLACKSMITH, 1, "iron", new ArrayList<>() {
        {
            add(ResourcesDictionary.SWORD.getName());
            add(ResourcesDictionary.MACE.getName());
        }
    }, new ArrayList<>() {
        {
            add("GROUND");
            add("SANDY_GROUND");
            add("GRASSLAND");
            add("LOW_MEADOW");
            add("HIGH_MEADOW");
            add("PLAIN");
        }
    }),
    FLETCHER(BuildingsDictionary.FLETCHER, 1, "", new ArrayList<>() {
        {
            add(ResourcesDictionary.BOW.getName());
            add(ResourcesDictionary.CROSSBOW.getName());
        }
    }, new ArrayList<>() {
        {
            add("GROUND");
            add("SANDY_GROUND");
            add("GRASSLAND");
            add("LOW_MEADOW");
            add("HIGH_MEADOW");
            add("PLAIN");
        }
    }),
    POLETURNER(BuildingsDictionary.POLETURNER, 1, "", new ArrayList<>() {
        {
            add(ResourcesDictionary.SPEAR.getName());
            add(ResourcesDictionary.PIKE.getName());
        }
    }, new ArrayList<>() {
        {
            add("GROUND");
            add("SANDY_GROUND");
            add("GRASSLAND");
            add("LOW_MEADOW");
            add("HIGH_MEADOW");
            add("PLAIN");
        }
    }),
    STABLE(BuildingsDictionary.STABLE, 4, "", new ArrayList<>() {
        {
            add("horse");
        }
    }, new ArrayList<>() {
        {
            add("GROUND");
            add("SANDY_GROUND");
            add("GRASSLAND");
            add("LOW_MEADOW");
            add("HIGH_MEADOW");
            add("PLAIN");
        }
    }),
    APPLE_ORCHARD(BuildingsDictionary.APPLE_ORCHARD, 5, null, new ArrayList<>() {
        {
            add(ResourcesDictionary.APPLE.getName());
        }
    }, new ArrayList<>() {
        {
            add("GRASSLAND");
            add("HIGH_MEADOW");
        }
    }),
    DAIRY_FARMER(BuildingsDictionary.DIARY_FARMER, 5, null, new ArrayList<>() {
        {
            add(ResourcesDictionary.CHEESE.getName());
        }
    }, new ArrayList<>() {
        {
            add("GRASSLAND");
            add("HIGH_MEADOW");
        }
    }),
    HOPS_FARMER(BuildingsDictionary.HOPS_FARMER, 5, null, new ArrayList<>() {
        {
            add(ResourcesDictionary.HOP.getName());
        }
    }, new ArrayList<>() {
        {
            add("GRASSLAND");
            add("HIGH_MEADOW");
        }
    }),
    WHEAT_FARMER(BuildingsDictionary.WHEAT_FARMER, 5, null, new ArrayList<>() {
        {
            add(ResourcesDictionary.WHEAT.getName());
        }
    }, new ArrayList<>() {
        {
            add("GRASSLAND");
            add("HIGH_MEADOW");
        }
    }),
    HUNTER_POST(BuildingsDictionary.HUNTER_POST, 5, null, new ArrayList<>() {
        {
            add(ResourcesDictionary.MEAT.getName());
        }
    }, new ArrayList<>() {
        {
            add("GROUND");
            add("SANDY_GROUND");
            add("GRASSLAND");
            add("LOW_MEADOW");
            add("HIGH_MEADOW");
            add("PLAIN");
        }
    }),
    BAKERY(BuildingsDictionary.BAKERY, 4, "wheat", new ArrayList<>() {
        {
            add(ResourcesDictionary.BREAD.getName());
        }
    }, new ArrayList<>() {
        {
            add("GROUND");
            add("SANDY_GROUND");
            add("GRASSLAND");
            add("LOW_MEADOW");
            add("HIGH_MEADOW");
            add("PLAIN");
        }
    }),
    BREWER(BuildingsDictionary.BREWER, 1, "hop", new ArrayList<>() {
        {
            add(ResourcesDictionary.ALE.getName());
        }
    }, new ArrayList<>() {
        {
            add("GROUND");
            add("SANDY_GROUND");
            add("GRASSLAND");
            add("LOW_MEADOW");
            add("HIGH_MEADOW");
            add("PLAIN");
        }
    });

    private BuildingsDictionary buildingDictionary;
    private int rate;
    private String inputResource;
    private ArrayList<String> outputResource;
    private ArrayList<String> acceptableTexture;

    private ProductiveBuildingsDictionary(BuildingsDictionary buildingsDictionary, int rate, String inputResource,
                                          ArrayList<String> outputResource, ArrayList<String> acceptableTexture) {
        this.rate = rate;
        this.inputResource = inputResource;
        this.outputResource = outputResource;
        this.acceptableTexture = acceptableTexture;
    }

    public int getRate() {
        return rate;
    }

    public BuildingsDictionary getBuildingDictionary() {
        return buildingDictionary;
    }

    public String getInputResource() {
        return inputResource;
    }

    public ArrayList<String> getOutputResource() {
        return outputResource;
    }

    public ArrayList<String> getAcceptableTexture() {
        return acceptableTexture;
    }

    public static ProductiveBuildingsDictionary getDictionaryByName(String buildingName) {
        for (ProductiveBuildingsDictionary productiveBuildingsDictionary : ProductiveBuildingsDictionary.values()) {
            if (productiveBuildingsDictionary.getBuildingDictionary().getName().equals(buildingName))
                return productiveBuildingsDictionary;
        }
        return null;
    }
}
