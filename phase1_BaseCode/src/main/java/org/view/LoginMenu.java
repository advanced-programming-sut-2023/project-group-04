package org.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginMenu extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.stage = stage;
        AnchorPane anchorPane = FXMLLoader.load(LoginMenu.class.getResource("/FXML/loginMenu.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }



}
