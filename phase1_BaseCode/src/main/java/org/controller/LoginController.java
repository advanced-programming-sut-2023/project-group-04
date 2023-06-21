package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.model.Player;
import org.view.CommandsEnum.SignUpMessages;
import org.view.LoginMenu;
import org.view.Menu;
import org.view.SignUpMenu;

import javafx.scene.image.ImageView;

import java.util.regex.Matcher;

import static org.view.CommandsEnum.SignUpMessages.*;
import static org.view.CommandsEnum.SignUpMessages.LOGIN_SUCCESSFUL;

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
    }

//    public String signIn(Matcher matcher) throws Exception {
//        String username = matcher.group("username").replaceAll("\"", "");
//        String password = matcher.group("password");
//        String status = matcher.group("status");
//        if (Player.getPlayerByUsername(username) == null) return USER_DOES_NOT_EXIST.getMessage();
//        if (!Player.getPlayerByUsername(username).isPasswordCorrect(Player.getSHA256Hash(password))) {
//            Player.getPlayerByUsername(username).increaseNumberOfAttempts();
//            return INCORRECT_PASSWORD.getMessage() + "\nPlease try again after <<"
//                    + Player.getNumberOfAttempts() * 5 + ">> seconds!";
//        }
//        // TODO: 4/19/2023  //Stay logged in not handled!!
//        if (generateCaptcha().equals(CAPTCHA_CORRECT)) Player.resetNumberOfAttempts();
//        Player player = Player.getPlayerByUsername(username);
//        Player.setCurrentPlayer(player);
//        if (status != null) player.setStayLogin();
//        return LOGIN_SUCCESSFUL.getMessage();
//    }


    public ImageView getCaptcha() {
        return captcha;
    }

    public void fillCaptcha() {
        int fileNumber = Menu.getSignupController().getFileNumber();
        captcha.setImage(new Image(LoginMenu.class.getResource("/images/captcha/" + fileNumber + ".png").toExternalForm()));
    }

    public void signIn(MouseEvent mouseEvent) {
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
        }
        // TODO: 6/21/2023 Stay loggedIn must be handled whit Usef
    }
}
