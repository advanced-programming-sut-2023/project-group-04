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
    private final GroundTextures groundTexture;
    private Building building;
    private ArrayList<Person> people;
    private Machine machine;
    private boolean tunnel;

    public MapCell(int x, int y, GroundTextures groundTexture) {
        this.xAxis = x;
        this.yAxis = y;
        this.groundTexture = groundTexture;
        this.people = new ArrayList<>();
        this.tunnel = false;
    }

    public int getX() {
        return xAxis;
    }

    public int getY() {
        return yAxis;
    }

    public GroundTextures getGroundTexture() {
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
