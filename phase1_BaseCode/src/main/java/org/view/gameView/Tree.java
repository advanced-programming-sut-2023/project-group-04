package org.view.gameView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.HashMap;
import java.util.SimpleTimeZone;

public class Tree extends ImageView {
    private static HashMap<String, Image> trees;

    int x, y;

    public Tree(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.setImage(trees.get(type));
        double width = (double) Tile.tileWidth / 4 * 3;
        double height = this.getImage().getHeight() / this.getImage().getWidth() * width;
        this.setFitWidth(width);
        this.setFitHeight(height);
        double xPos = Tile.getAllTiles()[x][y].getTranslateX() + (double) Tile.tileWidth / 8;
        double yPos = Tile.getAllTiles()[x][y].getTranslateY() + (double) Tile.tileHeight / 2 - this.getFitHeight();
        this.setTranslateX(xPos);
        this.setTranslateY(yPos);
        GameMapView.getCurrentGameMapView().getMap().getChildren().add(this);
    }

    public static void initTrees() throws IOException {
        String[] types = {"LITTLE_CHERRY", "LARGE_CHERRY", "OLIVE", "COCONUT", "DATE"};
        trees = new HashMap<>() {
            {
                for (String type : types)
                    put(type, new Image(GameMenu.class.getResource(
                            "/img/trees/" + type + ".png").openStream()));
            }
        };
    }
}
