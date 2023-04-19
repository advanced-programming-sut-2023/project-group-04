package org.model.person;

import model.Empire;
import model.Player;

public class Soldier extends Person {

    private SoldiersDictionary soldiersDictionary;

    public Soldier(SoldiersDictionary soldiersDictionary, Empire personOwner, int hp) {
        super(personOwner, hp);
    }

    public int getOffensivePower() {
        return soldiersDictionary.getOffensivePower();
    }

    public int getDefensivePower() {
        return soldiersDictionary.getDefensivePower();
    }

    public int getSpeed() {
        return soldiersDictionary.getSpeed();
    }

    public int getFireRange() {
        return soldiersDictionary.getFireRange();
    }
}
