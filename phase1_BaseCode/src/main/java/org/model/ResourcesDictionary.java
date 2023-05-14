package org.model;

public enum ResourcesDictionary {
    MEAT("meat", 40),
    CHEESE("cheese",40),
    APPLE("apple",40),
    WOOD("wood",20),
    STONE("stone",70),
    IRON("iron",220),
    HOP("hops",70),
    ALE("ale",100),
    WHEAT("wheat",110),
    FLOUR("flour",160),
    PITCH("pitch",100),
    BREAD("bread",40),
    BOW("bow",150),
    CROSSBOW("crossbow",290),
    SPEAR("spear",100),
    PIKE("pike",180),
    MACE("mace",290),
    SWORD("sword",290),
    LEATHER_ARMOUR("leather armour",120),
    METAL_ARMOUR("metal armour",290);


    private final int price;
    private final String name;

    ResourcesDictionary(String name, int price) {
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public int getPrice() {
        return price;
    }

    public static ResourcesDictionary getDictionaryByName(String buildingName) {
        for (ResourcesDictionary resourcesDictionary : ResourcesDictionary.values()) {
            if (resourcesDictionary.getName().equals(buildingName))
                return resourcesDictionary;
        }
        return null;
    }
}
