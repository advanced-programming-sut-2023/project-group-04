package org.model.person;

import java.util.HashMap;

public enum SoldiersDictionary {
    ARCHER("archer", 12,"bow","",0, 0, 0, 16,5),
    CROSSBOWMAN("crossbowman", 20,"crossBow","",0, 0, 0, 8,3),
    SPEARMAN("spearman", 8,"spear","",0, 0, 0, 12,0),
    PIKEMAN("pikeman", 20,"pike","",0, 0, 0, 8,0),
    MACEMAN("maceman", 20,"mace","",0, 0, 0, 12,0),
    SWORDSMAN("swordsman", 40,"sword","",0, 0, 0, 4,0),
    KNIGHT("knight", 40,"sword","",0, 0, 0, 20,0),
    TUNNELER("tunneler", 25,"pickAxe","",0, 0, 0, 16,0),
    LADDERMAN("ladderman", 5,"","",0, 0, 0, 16,0),
    ENGINEER("engineer", 30,"","",0, 0, 0, 12,0),
    BLACKMONKS("blackmonks", 10,"staff","",0, 0, 0, 8,0),
    ARABIANBOWS("arabianbows", 75,"bow","",0, 0, 0, 16,5),
    SLAVE("slave", 5,"torch","",0, 0, 0, 16,0),
    SLINGER("slinger", 12,"sling","",0, 0, 0, 16,2),
    ASSASSIN("assassin", 60,"scimitar","",0, 0, 0, 12,0),
    HORSE_ARCHER("horse_archer", 80,"bow","",0, 0, 0, 20,6),
    ARABIAN_SWORDSMAN("arabian_swordsman", 80,"scimitar","",0, 0, 0, 20,0),
    FIRE_THROWER("fire_thrower", 100,"greekFire","", 0, 0, 0, 20,8);

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
}
