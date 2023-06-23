package org.view.gameView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;


public class GameMenu extends Application {

    public VBox controlBar;
    public VBox game;
    public Text goldAmount;
    public Group popularity = new Group();
    public Group report = new Group();
    public Group food = new Group();
    public Group tax = new Group();
    public Group weapon = new Group();
    public Group fear = new Group();
    public Group stores = new Group();
    private boolean menuFlag = false;


    public static void main(String[] args) {
        launch(args);
    }

    Pane pane;

    @Override
    public void start(Stage stage) throws Exception {
        pane = FXMLLoader.load(new URL(GameMenu.class.getResource("/FXML/GameMenu.fxml").toExternalForm()));
        Scene scene = new Scene(pane);
        ControlBar bar = new ControlBar(pane, scene);
        bar.showGoldAmount();
        bar.reporterClick();
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

}
