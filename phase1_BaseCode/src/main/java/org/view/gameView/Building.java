package org.view.gameView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Building extends ImageView {

    private static HashMap<String, Image> buildings;
    private int x;
    private int y;
    private boolean added;
    private boolean fixed;

    public Building(String type) throws IOException {
        this.setImage(ControlBar.buildingImages.get(type));
        double width = Tile.tileWidth - 10;
        double height = this.getImage().getHeight() / this.getImage().getWidth() * width;
        this.setFitWidth(width);
        this.setFitHeight(height);
        this.setMouseTransparent(true);
        added = false;
    }

    public void movingBuilding(Tile hoveredTile) {
        this.x = hoveredTile.getXCoordinate();
        this.y = hoveredTile.getYCoordinate();
        double xPos = hoveredTile.getTranslateX() + 5;
        double yPos = hoveredTile.getTranslateY() + (double) Tile.tileHeight - this.getFitHeight() - 2;
        this.setTranslateX(xPos);
        this.setTranslateY(yPos);
        if (!added) {
            added = true;
            GameMapView.getCurrentGameMapView().getMap().getChildren().add(this);
        }
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
