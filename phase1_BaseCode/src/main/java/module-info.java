module phase1.BaseCode {
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;
    requires org.json;
    requires json.simple;
    requires com.google.gson;
    requires passay;
    requires java.datatransfer;
    requires java.desktop;


    opens org.view.gameView to javafx.fxml;
    exports org.view.gameView;

    opens org.view to javafx.fxml;
    exports org.view;
    opens org.controller to javafx.fxml;
    exports org.controller;
    opens org.model to com.google.gson;
    exports org.model;
}