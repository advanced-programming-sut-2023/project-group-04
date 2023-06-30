package org.view.gameView;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class GameMenu extends Application {

    private GameMapView mapView;

    private String pressedKey;

    public static void main(String[] args) {
        launch(args);
    }

    Pane pane;

    @Override
    public void start(Stage stage) throws Exception {
        pane = new Pane();
        Scene scene = new Scene(pane);
        ControlBar bar = new ControlBar(pane, scene);
        bar.reporterClick();
        mapView = new GameMapView();
        pane.getChildren().add(mapView.getMapBox());
        controlBarSetup();
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
        pane.requestFocus();
        bar.showGoldAmount();
        keyPressEvent();
    }


    private void keyPressEvent() {
        pane.setOnKeyPressed((new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                pressedKey = keyEvent.getText();
                switch (pressedKey) {
                    case "+" -> mapView.zoom(+1);
                    case "-" -> mapView.zoom(-1);
                    //todo : add shortcuts
                }
            }
        }));

        pane.setOnKeyReleased((new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                pressedKey = null;
            }
        }));
    }

    private void controlBarSetup() throws IOException {
        ImageView controlBar = new ImageView();
        Image image = new Image(GameMenu.class.getResource("/img/controlBar/menu3.png").openStream());
        controlBar.setImage(image);
        controlBar.setFitWidth(1540);
        controlBar.setFitHeight(250);
        controlBar.setTranslateY(620);
        pane.getChildren().add(controlBar);
    }

    public String getPressedKey() {
        return pressedKey;
    }
}
