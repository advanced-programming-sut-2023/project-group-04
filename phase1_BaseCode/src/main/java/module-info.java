module phase1.BaseCode {
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;
    requires org.json;
    requires json.simple;


    opens org.view to javafx.fxml;
    exports org.view;
}