package org.model.Machine;

public enum MachinesDictionary {
    PORTABLE_SHIELDS(0, 0,0,0, 0),
    BATTERING_RAMS(0, 0, 0,0, 0),
    SIEGE_TOWER(0, 0, 0,0, 0),
    CATAPULTS(0, 0, 0,0, 0),
    TREBUCHETS(0, 0, 0,0, 0),
    FIRE_BALLISTA(0, 0, 0,0, 0);


    private int speed;
    private int offensivePower;
    private int defensivePower;
    private int fireRange;
    private int numberOfEngineer;


    MachinesDictionary(int speed, int offensivePower, int defensivePower, int fireRange, int numberOfEngineer) {
        this.speed = speed;
        this.offensivePower = offensivePower;
        this.defensivePower = defensivePower;
        this.fireRange = fireRange;
        this.numberOfEngineer = numberOfEngineer;
    }

    public int getSpeed() {
        return speed;
    }

    public int getOffensivePower() {
        return offensivePower;
    }

    public int getDefensivePower() {
        return defensivePower;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getNumberOfEngineer() {
        return numberOfEngineer;
    }
}
