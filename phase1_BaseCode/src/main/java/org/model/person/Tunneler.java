package org.model.person;

import org.model.Empire;
import org.model.MapCell;

public class Tunneler extends Person {

    private static final int requiredGold = 0;
    private static final int speed = 0;
    private MapCell aimTunnel;

    public Tunneler(Empire personOwner, MapCell mapCell) {
        super(personOwner, 0, mapCell, speed);
    }

    public static int getRequiredGold(){
        return requiredGold;
    }

    public MapCell getAimTunnel() {
        return aimTunnel;
    }

    public void setAimTunnel(MapCell aimTunnel) {
        this.aimTunnel = aimTunnel;
    }
}
