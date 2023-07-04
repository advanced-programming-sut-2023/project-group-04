package org.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Chat extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = FXMLLoader.load(org.view.Chat.class.getResource("/fxml/chatView.fxml"));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}