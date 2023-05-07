package org.model.person;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

public enum SoldiersDictionary {
    ARCHER("",0,"","",0, 0, 0, 0,0),
    CROSSBOWMAN("",0,"","",0, 0, 0, 0,0),
    SPEARMAN("",0,"","",0, 0, 0, 0,0),
    PIKEMAN("",0,"","",0, 0, 0, 0,0),
    MACEMAN("",0,"","",0, 0, 0, 0,0),
    SWORDMAN("",0,"","",0, 0, 0, 0,0),
    KNIGHT("",0,"","",0, 0, 0, 0,0),
    TUNNELER("",0,"","",0, 0, 0, 0,0),
    LADDERMAN("",0,"","",0, 0, 0, 0,0),
    ENGINEER("",0,"","",0, 0, 0, 0,0),
    BLACKMONKS("",0,"","",0, 0, 0, 0,0),
    ARABIANBOWS("",0,"","",0, 0, 0, 0,0),
    SLAVE("",0,"","",0, 0, 0, 0,0),
    SLINGER("",0,"","",0, 0, 0, 0,0),
    ASSASSIN("",0,"","",0, 0, 0, 0,0),
    HORSE_ARCHER("",0,"","",0, 0, 0, 0,0),
    ARABIAN_SWORDSMAN("",0,"","",0, 0, 0, 0,0),
    FIRE_THROWER("",0,"","", 0, 0, 0, 0,0);

    private final String soldierName;
    private final int gold;
    private final String weapon;
    private final String shield;
    private final int hp;
    private final int offensivePower;
    private final int defensivePower;
    private final int speed;
    private final int fireRange;

     private SoldiersDictionary(String soldierName, int gold, String weapon, String shield, int hp, int offensivePower, int defensivePower, int speed, int fireRange) {
        this.soldierName = soldierName;
        this.gold = gold;
        this.weapon = weapon;
        this.shield = shield;
        this.hp = hp;
        this.offensivePower = offensivePower;
        this.defensivePower = defensivePower;
        this.speed = speed;
        this.fireRange = fireRange;
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
