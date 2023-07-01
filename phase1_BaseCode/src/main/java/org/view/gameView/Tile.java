package org.view.gameView;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.view.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Tile extends ImageView {

    public static final int tileWidth = 80;
    public static final int tileHeight = 40;
    private static HashMap<String, Image> tileTextures = null;
    private static Tile hoveredTile = null;
    private static Tile[][] allTiles = null;
    private final int x;
    private final int y;
    private String texture;
    private ImageView tree;
    private ArrayList<ImageView> people;
    private ImageView machine;


    public Tile(int x, int y, String texture) {
        tree = null;
        people = new ArrayList<>();
        machine = null;
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.setImage(tileTextures.get(texture));
        this.setTranslateX(tileWidth * x + (double) ((y % 2) * tileWidth) / 2);
        this.setTranslateY((double) tileHeight / 2 * y);
        this.setFitWidth(tileWidth);
        this.setFitHeight(tileHeight);
        allTiles[x][y] = this;
        events();
    }

    private void events() {
        Tile thisTile = this;
        this.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hoveredTile = thisTile;
//                ControlBar.setDetailText(Menu.getMapController().showMapDetail(hoveredTile.x, hoveredTile.y));
            }
        });
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {

                }
            }
        });

    }

    public static void loadTiles() throws IOException {
        String[] textures = {"GROUND", "SANDY_GROUND", "STONE_GROUND", "IRON_GROUND", "GRASSLAND", "LOW_MEADOW",
                "HIGH_MEADOW", "OIL", "PLAIN", "LOW_WATER", "RIVER", "SMALL_POND", "LARGE_POND", "BEACH", "SEA"};
        tileTextures = new HashMap<>() {
            {
                for (String texture : textures)
                    put(texture, new Image(Tile.class.getResource(
                            "/img/groundTiles/" + texture + ".png").openStream()));
            }
        };
    }

    public static void initializeAllTiles(int mapSize) {
        allTiles = new Tile[mapSize][mapSize];
    }

    public static Tile getHoveredTile() {
        return hoveredTile;
    }

    public static Tile[][] getAllTiles() {
        return allTiles;
    }

    public int getXCoordinate() {
        return x;
    }

    public int getYCoordinate() {
        return y;
    }

    public String getTexture() {
        return texture;
    }

    public void ChangeTexture(String texture) {
        this.texture = texture;
        this.setImage(tileTextures.get(texture));
    }

}
