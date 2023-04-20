package org.model.person;

import org.model.Empire;


public class Assassins extends Soldier{
    private boolean isHidden;

    public Assassins(SoldiersDictionary soldiersDictionary, Empire personOwner, int hp) {
        super(soldiersDictionary, personOwner, hp);
        this.isHidden = false;
    }
}
