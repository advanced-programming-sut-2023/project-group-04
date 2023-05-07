package org.model;

import org.model.Machine.Machine;
import org.model.buildings.Building;
import org.model.person.Person;
import org.model.person.Soldier;
import org.model.buildings.Building;

import java.util.ArrayList;

public class MapCell {
    private final int xAxis;
    private final int yAxis;
    private final String groundTexture;
    private Building building;
    private final ArrayList<Person> people;
    private Machine machine;
    private boolean tunnel;
    private boolean oil;

    public MapCell(int x, int y, String groundTexture) {
        this.xAxis = x;
        this.yAxis = y;
        this.groundTexture = groundTexture;
        this.people = new ArrayList<>();
        this.tunnel = false;
        this.oil = false;
    }
    public int getX() {
        return xAxis;
    }

    public int getY() {
        return yAxis;
    }

    public String getGroundTexture() {
        return groundTexture;
    }

    public Building getBuilding() {
        return building;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public boolean hasTunnel() {
        return tunnel;
    }

    public boolean isOily() {
        return oil;
    }

    public void setOilCondition(boolean oil) {
        this.oil = oil;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void addPeople(Person person) {
        this.people.add(person);
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public void setTunnel(boolean tunnel) {
        this.tunnel = tunnel;
    }
}
