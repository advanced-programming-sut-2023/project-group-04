package org.model.person;

import org.model.Empire;
import org.model.Map;
import org.model.MapCell;
import org.model.Player;

import java.util.ArrayList;

public class Soldier extends Person {

    private SoldiersDictionary soldiersDictionary;
    private int mode;

    private MapCell aim;

    public Soldier(Empire personOwner, SoldiersDictionary soldiersDictionary, MapCell mapCell) {
        super(personOwner, soldiersDictionary.getHp(), mapCell);
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

    public int getSpeed() {
        return soldiersDictionary.getSpeed();
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
