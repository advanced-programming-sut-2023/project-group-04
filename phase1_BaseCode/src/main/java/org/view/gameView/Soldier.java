package org.view.gameView;

import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;

public class Soldier extends ImageView {
    private static ArrayList<Soldier> soldiers = new ArrayList<>();
    private int x;
    private int y;
    private final String name;
    private final Tile tile;
    private Building building;

    public Soldier(String name) throws IOException {
        this.name = name;
        this.setImage(ControlBar.soldierImages.get(name));
        this.building = ControlBar.clickedBuilding;
        this.x = building.getXCoordinate();
        this.y = building.getYCoordinate() + 1;
        this.tile = Tile.getAllTiles()[x][y];
        soldiers.add(this);
    }
}
