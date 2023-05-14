package org.model.person;

import org.model.Empire;
import org.model.MapCell;

public class Soldier extends Person {

    private SoldiersDictionary soldiersDictionary;
    private MapCell aim;

    public Soldier(Empire personOwner, SoldiersDictionary soldiersDictionary, MapCell mapCell) {
        super(personOwner, soldiersDictionary.getHp(), mapCell, soldiersDictionary.getSpeed());
        this.soldiersDictionary = soldiersDictionary;
    }

    public SoldiersDictionary getSoldiersDictionary() {
        return soldiersDictionary;
    }

    public String getSoldierType() {
        return soldiersDictionary.getName();
    }

    public int getOffensivePower() {
        return soldiersDictionary.getOffensivePower();
    }

    public int getDefensivePower() {
        return soldiersDictionary.getDefensivePower();
    }

    public int getFireRange() {
        return soldiersDictionary.getFireRange();
    }

    public String getSoldierName() {
        return soldiersDictionary.getSoldierName();
    }

    public MapCell getAim() {
        return aim;
    }

    public void setAim(MapCell aim) {
        this.aim = aim;
    }
}
