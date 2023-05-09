package org.model.Machine;

public enum MachinesDictionary {
    PORTABLE_SHIELDS("portable_shields", 4, 0,1000,0, 1, 5),
    BATTERING_RAMS("battering_rams", 1, 400, 1500,1, 4, 150),
    SIEGE_TOWER("siege_tower", 1, 0, 1500,1, 4, 150),
    CATAPULTS("catapults", 2, 200, 950,30, 2, 150),
    TREBUCHETS("trebuchets", 0, 250, 1000,50, 3, 150),
    FIRE_BALLISTA("fire_ballista", 2, 250, 1000,30, 2, 150);


    private String machineName;
    private int speed;
    private int offensivePower;
    private int hp;
    private int fireRange;
    private int numberOfEngineer;
    private int gold;


    MachinesDictionary(String machineName, int speed, int offensivePower, int hp, int fireRange, int numberOfEngineer, int gold) {
        this.machineName = machineName;
        this.speed = speed;
        this.offensivePower = offensivePower;
        this.hp = hp;
        this.fireRange = fireRange;
        this.numberOfEngineer = numberOfEngineer;
        this.gold = gold;
    }

    public int getSpeed() {
        return speed;
    }

    public int getOffensivePower() {
        return offensivePower;
    }

    public int getHp() {
        return hp;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getNumberOfEngineer() {
        return numberOfEngineer;
    }
}
