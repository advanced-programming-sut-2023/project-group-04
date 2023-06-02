package org.view.gameView;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;


public class GameMenu extends Application {

    public VBox controlBar;
    public VBox game;

    public static void main(String[] args) {
        launch(args);
    }

    Pane pane;

    @Override
    public void start(Stage stage) throws Exception {
        pane = FXMLLoader.load(new URL(GameMenu.class.getResource("/FXML/GameMenu.fxml").toExternalForm()));
        Scene scene = new Scene(pane);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }


}
