package org.view.gameView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Building extends ImageView {

    private static HashMap<String, Image> buildings;
    private int x, y;

    public Building(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.setImage(buildings.get(type));
    }

    public static void initBuildings() throws IOException {
        ArrayList<String> buildingsName = new ArrayList<>();
        buildings = new HashMap<>() {
            {
                for (String buildingName : buildingsName)
                    put(buildingName, new Image(Building.class.getResource(
                            "/img/buildings/" + buildingName + ".png").openStream()));

            }
        };

    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getXCoordinate() {
        return x;
    }

    public int getYCoordinate() {
        return y;
    }

}
