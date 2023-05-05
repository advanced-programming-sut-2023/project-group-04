package org.model.person;

import org.model.Empire;
import org.model.Player;

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

    public String getSoldierName() {
        return soldiersDictionary.getSoldierName();
    }
}
