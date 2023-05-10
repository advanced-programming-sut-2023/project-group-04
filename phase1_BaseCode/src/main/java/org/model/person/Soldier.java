package org.model.person;

import org.model.Empire;
import org.model.MapCell;

public class Soldier extends Person {

    private SoldiersDictionary soldiersDictionary;
    private int mode;
    private MapCell aim;

    public Soldier(Empire personOwner, SoldiersDictionary soldiersDictionary, MapCell mapCell) {
        super(personOwner, soldiersDictionary.getHp(), mapCell, soldiersDictionary.getSpeed());
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

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public MapCell getAim() {
        return aim;
    }

    public void setAim(MapCell aim) {
        this.aim = aim;
    }
}
