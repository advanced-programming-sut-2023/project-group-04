package org.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProfileMenu extends Application {

    public static AnchorPane anchorPane;
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(ProfileMenu.class.getResource("/fxml/profileMenu.fxml"));
        ProfileMenu.anchorPane = anchorPane;
        ProfileMenu.stage = stage;
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

}
