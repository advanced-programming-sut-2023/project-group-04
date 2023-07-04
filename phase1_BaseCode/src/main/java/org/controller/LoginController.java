package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.model.*;
import org.view.CommandsEnum.SignUpMessages;
import org.view.LoginMenu;
import org.view.MainMenu;
import org.view.Menu;
import org.view.SignUpMenu;

import javafx.scene.image.ImageView;


public class LoginController {

    public ImageView captcha;
    public TextField username;
    public Text usernameError;
    public Text passwordError;
    public TextField password;
    public TextField captchaAnswer;
    public Button login;


    public void runSignupMenu(MouseEvent mouseEvent) throws Exception {
        new SignUpMenu().start(LoginMenu.stage);
    }

    @FXML
    public void initialize() {
        fillCaptcha();
        Menu.getSignupController().runProgram();
    }


    public ImageView getCaptcha() {
        return captcha;
    }

    public void fillCaptcha() {
        int fileNumber = Menu.getSignupController().getFileNumber();
        captcha.setImage(new Image(LoginMenu.class.getResource("/images/captcha/" + fileNumber + ".png").toExternalForm()));
    }

    public void signIn(MouseEvent mouseEvent) throws Exception {
        String username = this.username.getText();
        String password = this.password.getText();
        Player player = Player.getPlayerByUsername(username);
        String url = captcha.getImage().getUrl();
        String correctAnswer = url.substring(url.length() - 8, url.length() - 4);
        String answer = captchaAnswer.getText();
        if (username.isEmpty() || password.isEmpty()) {
            this.username.setStyle("-fx-border-color: red");
            this.password.setStyle("-fx-border-color: red");
            usernameError.setText("");
            passwordError.setText("");
        } else if (player == null) {
            usernameError.setText(SignUpMessages.USER_DOES_NOT_EXIST.getMessage());
            this.username.setStyle("-fx-border-color: red");
            fillCaptcha();
        } else if (!player.isPasswordCorrect(password)) {
            passwordError.setText(SignUpMessages.INCORRECT_PASSWORD.getMessage());
            this.password.setStyle("-fx-border-color: red");
            fillCaptcha();
        } else if (!answer.equals(correctAnswer)) {
            captchaAnswer.setStyle("-fx-border-color: red");
            fillCaptcha();
        } else {
            Player.setCurrentPlayer(player);
            Menu.getSignupController().showAlert(Alert.AlertType.INFORMATION
                    , "success", SignUpMessages.LOGIN_SUCCESSFUL.getMessage());
            login.setStyle("-fx-background-color: green");
            login.setStyle("-fx-text-fill: black");
            new MainMenu().start(LoginMenu.stage);
        }
        // TODO: 6/21/2023 Stay loggedIn must be handled whit Usef
    }
}
