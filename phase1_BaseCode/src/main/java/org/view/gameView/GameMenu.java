package org.view.gameView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;


public class GameMenu extends Application {

    public VBox controlBar;
    public VBox game;


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
        controlBarSetup();
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
        bar.showGoldAmount();
    }

    private void controlBarSetup() throws IOException {
        Pane controlBar = new Pane();
        Image image = new javafx.scene.image.Image(GameMenu.class.getResource("/img/controlBar/menu3.png").openStream());
        BackgroundSize backgroundSize = new BackgroundSize(1540, 250, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        controlBar.setBackground(background);
        controlBar.setPrefSize(1540,250);
        controlBar.setTranslateY(620);
        pane.getChildren().add(controlBar);
    }

}
