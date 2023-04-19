package org.model.person;

public enum Soldiers {
    ARCHER(0, 0, 0, 0),
    CROSSBOWMAN(0, 0, 0, 0),
    SPEARMAN(0, 0, 0, 0),
    PIKEMAN(0, 0, 0, 0),
    MACEMAN(0, 0, 0, 0),
    SWORDMAN(0, 0, 0, 0),
    KNIGHT(0, 0, 0, 0),
    TUNNELER(0, 0, 0, 0),
    LADDERMAN(0, 0, 0, 0),
    ENGINEER(0, 0, 0, 0),
    BLACKMONKS(0, 0, 0, 0),
    ARABIANBOWS(0, 0, 0, 0),
    SLAVE(0, 0, 0, 0),
    SLINGER(0, 0, 0, 0),
    ASSASSIN(0, 0, 0, 0),
    HORSE_ARCHER(0, 0, 0, 0),
    ARABIAN_SWORDSMAN(0, 0, 0, 0),
    FIRE_THROWER(0, 0, 0, 0);

    private int offensivePower;
    private int defensivePower;
    private int speed;
    private int fireRange;

    Soldiers(int offensivePower, int defensivePower, int speed, int fireRange) {
        this.offensivePower = offensivePower;
        this.defensivePower = defensivePower;
        this.speed = speed;
        this.fireRange = fireRange;
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
}
