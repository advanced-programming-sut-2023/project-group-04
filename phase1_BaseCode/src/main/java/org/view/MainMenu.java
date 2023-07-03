package org.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.model.*;

import javafx.scene.control.Button;
import org.model.map.Map;
import org.view.CommandsEnum.GameMessages;

public class MainMenu extends Application {

    public static Stage stage;

    public Button startGame;
    public Button environment;
    public Button profile;
    public Button logout;
    public Button exit;
    public GridPane PlayersGrid;
    public ImageView back;
    public ImageView forward;
    public Slider playerNumbers;
    public Text text1;
    public Text text2;
    public Text text3;
    public Text text4;
    public Text text5;
    public Text text6;
    public Text text7;
    public TextField username1;
    public TextField username2;
    public TextField username3;
    public TextField username4;
    public TextField username5;
    public TextField username6;
    public TextField username7;
    public TextField mapName;
    private String[] players;
    public Button submit;

    public MainMenu() {
    }

    @FXML
    public void initialize() {
        playerNumbers.valueProperty().addListener((observableValue, number, t1) -> {
            if (Double.parseDouble(t1.toString()) >= 2) {
                text2.setVisible(true);
                username2.setVisible(true);
            } else {
                text2.setVisible(false);
                username2.setVisible(false);
            }
            if (Double.parseDouble(t1.toString()) >= 3) {
                text3.setVisible(true);
                username3.setVisible(true);
            } else {
                text3.setVisible(false);
                username3.setVisible(false);
            }
            if (Double.parseDouble(t1.toString()) >= 4) {
                text4.setVisible(true);
                username4.setVisible(true);
            } else {
                text4.setVisible(false);
                username4.setVisible(false);
            }
            if (Double.parseDouble(t1.toString()) >= 5) {
                text5.setVisible(true);
                username5.setVisible(true);
            } else {
                text5.setVisible(false);
                username5.setVisible(false);
            }
            if (Double.parseDouble(t1.toString()) >= 6) {
                text6.setVisible(true);
                username6.setVisible(true);
            } else {
                text6.setVisible(false);
                username6.setVisible(false);
            }
            if (Double.parseDouble(t1.toString()) >= 7) {
                text7.setVisible(true);
                username7.setVisible(true);
            } else {
                text7.setVisible(false);
                username7.setVisible(false);
            }
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainMenu.stage = stage;
        AnchorPane anchorPane = FXMLLoader.load(MainMenu.class.getResource("/fxml/mainMenu.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        Menu.getSignupController().clearStayLogin();
        Player.setCurrentPlayer(null);
        new LoginMenu().start(LoginMenu.stage);
    }

    public void runProfileMenu(MouseEvent mouseEvent) throws Exception {
        new ProfileMenu().start(LoginMenu.stage);
    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void showPlayersList(MouseEvent mouseEvent) {
        startGame.setVisible(false);
        profile.setVisible(false);
        environment.setVisible(false);
        logout.setVisible(false);
        exit.setVisible(false);
        PlayersGrid.setVisible(true);
        back.setVisible(true);
        forward.setVisible(true);
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        start(MainMenu.stage);
    }

    public void showMapTextBox(MouseEvent mouseEvent) {
        String[] allUsers = getUsers();
        if (allUsers.length == 0)
            showErrors();
        else {
            forward.setVisible(false);
            PlayersGrid.setVisible(false);
            mapName.setVisible(true);
            submit.setVisible(true);
            this.players = allUsers;
        }
    }

    private void showErrors() {
        username1.setStyle("-fx-border-color: red");
        username2.setStyle("-fx-border-color: red");
        username3.setStyle("-fx-border-color: red");
        username4.setStyle("-fx-border-color: red");
        username5.setStyle("-fx-border-color: red");
        username6.setStyle("-fx-border-color: red");
        username7.setStyle("-fx-border-color: red");
        Menu.getSignupController()
                .showAlert(Alert.AlertType.ERROR, "user not found!", "user doesn't exist!");
    }

    private String[] getUsers() {
        int counter = 0;
        if (username1.isVisible())
            counter++;
        if (username2.isVisible())
            counter++;
        if (username3.isVisible())
            counter++;
        if (username4.isVisible())
            counter++;
        if (username5.isVisible())
            counter++;
        if (username6.isVisible())
            counter++;
        if (username7.isVisible())
            counter++;
        String[] allUsers;
        if (!checkValidUsername()) {
            return new String[0];
        } else {
            allUsers = getUsernames(counter);
        }
        return allUsers;
    }

    private String[] getUsernames(int counter) {
        String[] users = new String[counter];
        if (username1.isVisible())
            users[0] = username1.getText();
        if (username2.isVisible())
            users[1] = username2.getText();
        if (username3.isVisible())
            users[2] = username3.getText();
        if (username4.isVisible())
            users[3] = username4.getText();
        if (username5.isVisible())
            users[4] = username5.getText();
        if (username6.isVisible())
            users[5] = username6.getText();
        if (username7.isVisible())
            users[6] = username7.getText();
        return users;
    }

    private boolean checkValidUsername() {
        if (username1.isVisible())
            if (Player.getPlayerByUsername(username1.getText()) == null)
                return false;
        if (username2.isVisible())
            if (Player.getPlayerByUsername(username2.getText()) == null)
                return false;
        if (username3.isVisible())
            if (Player.getPlayerByUsername(username3.getText()) == null)
                return false;
        if (username4.isVisible())
            if (Player.getPlayerByUsername(username4.getText()) == null)
                return false;
        if (username5.isVisible())
            if (Player.getPlayerByUsername(username5.getText()) == null)
                return false;
        if (username6.isVisible())
            if (Player.getPlayerByUsername(username6.getText()) == null)
                return false;
        if (username7.isVisible())
            if (Player.getPlayerByUsername(username7.getText()) == null)
                return false;
        return true;
    }

    public void startGame(MouseEvent mouseEvent) {
        if (Map.getMapByName(mapName.getText()) == null)
            Menu.getSignupController().showAlert(Alert.AlertType.ERROR,
                    "map not exist", GameMessages.MAP_NOT_EXIST.getMessage());
        else {
            GameMessages gameMessages = Menu.getGameController().startGame(this.players, mapName.getText());
            if (gameMessages.equals(GameMessages.GAME_STARTED)) {
                // TODO: 7/2/2023 Usef Please Start Game!
            } else {
                Menu.getSignupController().showAlert(Alert.AlertType.ERROR,
                        "can not start", gameMessages.getMessage());
            }
        }
    }

}