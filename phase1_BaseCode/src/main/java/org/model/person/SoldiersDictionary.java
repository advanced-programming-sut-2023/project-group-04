package org.model.person;

import java.util.ArrayList;

public enum SoldiersDictionary {
    ARCHER("archer", 12, "bow", "", 250, 125, 80, 16, 5),
    CROSSBOWMAN("crossbow man", 20, "crossbow", "leather armour", 270, 125, 100, 8, 3),
    SPEARMAN("spearman", 8, "spear", "", 220, 150, 60, 12, 0),
    PIKEMAN("pike man", 20, "pike", "metal armour", 290, 150, 120, 8, 0),
    MACEMAN("mace man", 20, "mace", "leather armour", 300, 175, 100, 12, 0),
    SWORDSMAN("swordsman", 40, "sword", "metal armour", 350, 200, 60, 4, 0),
    KNIGHT("knight", 40, "sword", "metal armour", 400, 200, 120, 20, 0),
    TUNNELER("tunneler", 25, "", "", 270, 150, 60, 16, 0),
    LADDERMAN("ladder man", 5, "", "", 210, 0, 60, 16, 0),
    ENGINEER("engineer", 30, "", "", 220, 0, 60, 12, 0),
    BLACKMONKS("blackmonks", 10, "staff", "", 270, 150, 100, 8, 0),
    ARABIANBOWS("arabian bow", 75, "", "", 270, 125, 80, 16, 5),
    SLAVE("slave", 5, "", "", 200, 100, 20, 16, 0),
    SLINGER("slinger", 12, "", "", 250, 125, 60, 16, 2),
    ASSASSIN("assassin", 60, "", "", 300, 150, 100, 12, 0),
    HORSE_ARCHER("horse archer", 80, "", "", 340, 125, 100, 20, 6),
    ARABIAN_SWORDSMAN("arabian swordsman", 80, "", "", 360, 175, 120, 20, 0),
    FIRE_THROWER("fire thrower", 100, "", "", 400, 175, 80, 20, 8);

    int hp;
    private int offensivePower;
    private int defensivePower;
    private int speed;
    private int fireRange;
    private int gold;
    private String soldierName;
    private String weapon;
    private String shield;

    SoldiersDictionary(String soldierName, int gold, String weapon, String shield, int hp, int offensivePower, int defensivePower, int speed, int fireRange) {
        this.gold = gold;
        this.weapon = weapon;
        this.shield = shield;
        this.hp = hp;
        this.offensivePower = offensivePower;
        this.defensivePower = defensivePower;
        this.speed = speed;
        this.fireRange = fireRange;
        this.soldierName = soldierName;
    }

    public String getName() {
        return soldierName;
    }

    public int getOffensivePower() {
        return offensivePower;
    }

    public int getDefensivePower() {
        return defensivePower;
    }

    public int getSpeed() {
        return speed;
    }

    public int getFireRange() {
        return fireRange;
    }

    public String getSoldierName() {
        return soldierName;
    }

    public int getGold() {
        return gold;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getShield() {
        return shield;
    }

    public int getHp() {
        return hp;
    }

    public static SoldiersDictionary getSoldierDictionaryByName(String name) {
        for (SoldiersDictionary soldierDictionary : SoldiersDictionary.values())
            if (soldierDictionary.getName().equals(name))
                return soldierDictionary;
        return null;
    }


    public static ArrayList<String> getAllSoldierTypes() {
        ArrayList<String> allSoldiersType = new ArrayList<>();
        for (SoldiersDictionary soldierDictionary : SoldiersDictionary.values())
            allSoldiersType.add(soldierDictionary.getName());
        return allSoldiersType;
    }
}
