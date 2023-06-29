package org.view.gameView;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import org.view.Menu;

import java.io.IOException;
import java.util.HashMap;

public class GameMapView {
    private HashMap<String, Image> trees;
    private ScrollPane mapBox;
    private Pane map;
    private Scale zoomScale;
    private double mouseCursorPositionX;
    private double mouseCursorPositionY;

    public GameMapView() throws IOException {
        Tile.loadTiles();
        loadTrees();
        int mapSize = 100;
        Tile.initializeAllTiles(mapSize);
        readyMap(mapSize);
        showTrees();
        showBuildings();
        readyMapBox();
        mouseCursorPositionX = 0;
        mouseCursorPositionY = 0;
        Building.initBuildings();
    }

    private void readyMapBox() {
        mapBox = new ScrollPane();
        mapBox.setContent(map);
        mapBox.setPrefSize(1540, 710);
        mapBox.setPannable(true);
        mapBox.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mapBox.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    private void readyMap(int size) throws IOException {
        map = new Pane();
        map.setPrefSize(Tile.tileWidth * size, (double) (Tile.tileHeight * size) / 2);
        map.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        loadBorder(size);
        for (int j = 0; j < size; j++) {
            int bound = size + (j % 2 == 0 ? 1 : 0);
            for (int i = 0; i < bound; i++) {
                int y = j - 1;
                int x = bound == size ? i : i - 1;
                if (x >= 0 && y >= 0 && (bound == size || x < size - 1) && y < size)
                    map.getChildren().add(new Tile(x, y, "GROUND"));
            }
        }
        handleMouseCursorMove();
        //TODO : okey this part and mouse move
        ImageView tree = new ImageView();
        tree.setImage(trees.get("COCONUT"));
        tree.setFitWidth(80);
        tree.setFitHeight(200);
        tree.setTranslateX(200);
        tree.setTranslateX(200);
        map.getChildren().add(tree);
        map.getTransforms().add(zoomScale = new Scale());
        ImageView building = new ImageView();
        Image image = new Image(GameMenu.class.getResource("/img/Barracks.png").openStream());
        building.setImage(image);
        building.setFitWidth(120);
        building.setFitHeight(120);
        double height = building.getFitHeight();
        building.setTranslateX(560);
        building.setTranslateY(400);
        building.setMouseTransparent(true);
        map.getChildren().add(building);
        map.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseCursorPositionX = mouseEvent.getX();
                mouseCursorPositionY = mouseEvent.getY();
                if (Tile.getHoveredTile() != null) {
                    double x = Tile.getHoveredTile().getTranslateX() + Tile.getHoveredTile().getFitWidth() / 2 - building.getFitWidth() / 2;
                    double y = Tile.getHoveredTile().getTranslateY() + 2 * Tile.getHoveredTile().getFitHeight() - 10 - building.getFitHeight();
                    building.setTranslateX(x);
                    building.setTranslateY(y);
                }

            }
        });
        map.getTransforms().add(zoomScale = new Scale());
    }

    private void handleMouseCursorMove() {
    }

    private void loadBorder(int size) throws IOException {
        Image texture = new Image(Tile.class.getResource("/img/groundTiles/STONE_GROUND.png").openStream());
        for (int rep = 0; rep < 2; rep++) {
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i <= size; i++) {
                    ImageView borderTile = new ImageView();
                    borderTile.setImage(texture);
                    borderTile.setFitWidth(Tile.tileWidth);
                    borderTile.setFitHeight(Tile.tileHeight);
                    double x;
                    double y ;
                    if (rep == 0) {
                        x = i * Tile.tileWidth - (double) Tile.tileWidth / 2;
                        y = (double) (j * size / 2 * Tile.tileHeight) - (double) Tile.tileHeight / 2;
                    } else {
                        y = i * Tile.tileHeight - (double) Tile.tileHeight / 2;
                        x = j * size * Tile.tileWidth - (double) Tile.tileWidth / 2;
                    }
                    borderTile.setTranslateX(x);
                    borderTile.setTranslateY(y);
                    map.getChildren().add(borderTile);
                    System.out.println(x + " : " + y);
                    if (i == size / 2 && rep == 1) break;
                }
            }
        }
    }

    private void showBuildings() {
    }

    private void showTrees() {
    }

    void zoom(int dir) {
        double scale = zoomScale.getX();
        if ((scale > 0.6 && dir < 0)) scale -= scale > 1 ? 0.2 : 0.1;
        if ((scale < 1.8 && dir > 0)) scale += scale >= 1 ? 0.2 : 0.1;
        zoomScale.setX(scale);
        zoomScale.setY(scale);
        map.setPrefSize(scale * 8000, scale * 2000);
    }


    private void loadTrees() throws IOException {
        String[] types = {"LITTLE_CHERRY", "LARGE_CHERRY", "OLIVE", "COCONUT", "DATE"};
        trees = new HashMap<>() {
            {
                for (String type : types)
                    put(type, new Image(GameMenu.class.getResource(
                            "/img/trees/" + type + ".png").openStream()));
            }
        };
    }

    public ScrollPane getMapBox() {
        return this.mapBox;
    }

    public Pane getMap() {
        return this.map;
    }
}