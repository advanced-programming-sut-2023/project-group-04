package org.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.model.Player;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.*;

import java.util.ArrayList;

public class Scoreboard extends Application {
    private Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        BorderPane root = new BorderPane();
        AnchorPane anchorPane = new AnchorPane();
        root.setCenter(anchorPane);
        GridPane scoreboard = createScoreboard();
        setScoreBoardDetails(scoreboard);
        ScrollPane scrollPane = createScrollPane(scoreboard);
        anchorPane.getChildren().add(scrollPane);
        setBackButton(anchorPane);
        setBackground(anchorPane);
        Scene scene = new Scene(root, 1560, 920);
        scene.getStylesheets().add(Scoreboard.class.getResource("/css/scoreboard.css").toString());
        stage.setScene(scene);
        stage.setTitle("scoreboard");
        stage.show();
    }

    private static ScrollPane createScrollPane(GridPane scoreboard) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(scoreboard);
        scrollPane.setLayoutX(125);
        scrollPane.setLayoutY(45);
        scrollPane.setPrefWidth(800);
        scrollPane.setPrefHeight(600);

        scrollPane.setStyle("-fx-border-color: rgb(145,13,145);");
        return scrollPane;
    }

    private static void setScoreBoardDetails(GridPane scoreboard) {
        scoreboard.setPrefWidth(800);
        scoreboard.setPrefHeight(600);
        scoreboard.setStyle("-fx-background-color: rgb(145,13,145);");
    }

    private GridPane createScoreboard() {
        ArrayList<Player> players = Player.getSortedPlayers();
        GridPane scoreboardGrid = new GridPane();
        scoreboardGrid.setAlignment(Pos.CENTER);
        scoreboardGrid.setHgap(12);
        scoreboardGrid.setVgap(12);
        scoreboardGrid.add(new Text("Rank"), 0, 0);
        scoreboardGrid.add(new Text("Username"), 1, 0);
        scoreboardGrid.add(new Text("Score"), 2, 0);
        int i = 1;
        for (Player player : players) {
            Label playerRankLabel = new Label("" + i);
            scoreboardGrid.add(playerRankLabel, 0, i);
            Label playerNameLabel = new Label(player.getUsername());
            scoreboardGrid.add(playerNameLabel, 1, i);
            Label scoreLabel = new Label(player.getScore() + "");
            scoreboardGrid.add(scoreLabel, 2, i);
            i++;
        }
        return scoreboardGrid;
    }

    private void setBackButton(AnchorPane anchorPane) {
        Rectangle backButton = new Rectangle();
        backButton.setLayoutX(50);
        backButton.setLayoutY(770);
        backButton.setWidth(104);
        backButton.setHeight(75);
        backButton.setFill(new ImagePattern(new Image(Scoreboard.class.getResource("/images/icons/back.png").toExternalForm())));
        backButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                back();
            }
        });
        anchorPane.getChildren().add(backButton);
    }

    private void back() {
        try {
            new ProfileMenu().start(LoginMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void setBackground(AnchorPane anchorPane) {
        Image image = new Image(Scoreboard.class.getResource
                ("/images/scoreboard.jpg").toString(),
                1560, 920, false, false);
        Background background = new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT));
        anchorPane.setBackground(background);
    }
}
