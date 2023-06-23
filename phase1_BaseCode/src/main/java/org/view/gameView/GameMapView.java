package org.view.gameView;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class GameMapView {

    private final ScrollPane mapBox;
    private final GridPane map;

    public GameMapView() {
        map = new GridPane();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Button button = new Button();
                button.setText("salam");
                button.setMinWidth(10);
                map.add(button, i, j, 1, 1);
            }
        }
        mapBox = new ScrollPane();
        mapBox.setContent(map);
        mapBox.setMaxSize(1540,715);
        mapBox.setPannable(true);
        mapBox.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mapBox.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public ScrollPane getMapBox() {
        return this.mapBox;
    }

    public GridPane getMap() {
        return this.map;
    }
}
