package org.view.gameView;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class GameMapView {

    private final ScrollPane mapBox;
    private GridPane map;

    public GameMapView() throws IOException {
        mapBox = new ScrollPane();
        readyMap();
        mapBox.setContent(map);
        mapBox.setMaxSize(1540,710);
        mapBox.setPannable(true);
        mapBox.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mapBox.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    private void readyMap() throws IOException {
        map = new GridPane();
        Image image = new Image(GameMenu.class.getResource("/img/gameAssets/tiles/tile/desert_tile.jpg").openStream());
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Pane pane = new Pane();
                BackgroundSize backgroundSize = new BackgroundSize(50, 50, false, false, false, false);
                BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
                Background background = new Background(backgroundImage);
                pane.setBackground(background);
                pane.setPrefSize(50, 50);
                map.add(pane, i, j, 1, 1);
            }
        }
    }

    public ScrollPane getMapBox() {
        return this.mapBox;
    }

    public GridPane getMap() {
        return this.map;
    }
}
