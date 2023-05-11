package org.model.person;

import org.model.Empire;
import org.model.MapCell;


public class Assassins extends Soldier{
    private boolean isHidden;

    public Assassins(Empire personOwner, MapCell mapCell) {
        super(personOwner, SoldiersDictionary.ASSASSIN, mapCell);
        this.isHidden = false;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }
}
