package org.view.gameView;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.view.Menu;

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
        bar.addDetailBox();
        keyPressEvent();
    }


    private void keyPressEvent() {
        pane.setOnKeyPressed((new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                pressedKey = String.valueOf(keyEvent.getCode());
                switch (pressedKey) {
                    case "ADD" -> mapView.zoom(+1);
                    case "SUBTRACT" -> mapView.zoom(-1);
                    case "SPACE" -> Menu.getGameController().nextTurn();
                    case "TAB" -> System.out.println("trade menu");  //todo : enter trade menu
                    case "C" -> Building.buildingCopy();
                    case "P" -> {
                        try {
                            Building.buildingPaste();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case "delete" -> System.out.println("delete"); //todo : delete
                    case "Q" -> {
                        GameMapView.cancel();
                        Tile.unSelectTile();
                    }
                    case "ESCAPE" -> System.out.println("exit"); //todo :  back to main menu
                    default -> System.out.println(pressedKey);
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
