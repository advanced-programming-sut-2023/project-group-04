package org.view.gameView;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;


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
        mapView = new GameMapView();
        pane.getChildren().add(mapView.getMapBox());
        controlBarSetup();
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
        pane.requestFocus();
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
