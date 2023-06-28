package org.controller;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.model.Player;
import org.view.*;
import org.view.CommandsEnum.ProfileMessages;

import javafx.scene.image.ImageView;
import org.view.CommandsEnum.SignUpMessages;

import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;

import static java.lang.Math.random;

public class ProfileController extends Application {
    public Text username;
    public Text email;
    public Text slogan;
    public Text nickname;
    public ImageView avatar;

    @FXML
    public void initialize() {
        Player player = Player.getCurrentPlayer();
        if (username != null)
            username.setText(player.getUsername());
        if (email != null)
            email.setText(player.getEmail());
        if (slogan != null) {
            if (player.getSlogan() == null || player.getSlogan().equals(""))
                slogan.setText("slogan is empty");
            else slogan.setText(player.getSlogan());
        }
        String avatarURL = player.getAvatarResource();
        if (avatar != null)
            if (avatarURL != null)
                avatar.setImage(new Image(avatarURL));
        if (nickname != null)
            nickname.setText(player.getNickname());
    }

//    public ProfileMessages changeUsername(Matcher matcher) {
//        ProfileMessages profileMessages = checkUsername(matcher);
//
//        if (profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY))
//            setUsername(removeQuotation(matcher.group("username")));
//        return profileMessages;
//    }

    public void setUsername(String username, AnchorPane anchorPane, VBox vBox, Text usernameError) {
        if (isUsernameFormatCorrect(username)) {
            Player.getCurrentPlayer().setUsername(username);
            Player.savePlayers();
            Menu.getSignupController().
                    showAlert(Alert.AlertType.INFORMATION, "Success", "username successfully changed!");
            anchorPane.getChildren().removeAll(vBox, usernameError);
            initialize();
        }
    }

    public void setPassword(String password, AnchorPane anchorPane, Text oldPasswordError, Text newPasswordError, VBox vBox) {
        Player.getCurrentPlayer().setPassword(password);
        Player.savePlayers();
        anchorPane.getChildren().removeAll(vBox, oldPasswordError, newPasswordError);
        Menu.getSignupController().
                showAlert(Alert.AlertType.INFORMATION, "Success", "Your password successfully changed!");
    }

    public ProfileMessages changeNickname(Matcher matcher) {
        if (matcher.group("nickname") == null)
            return ProfileMessages.EMPTY_FIELD;
        String nickname = removeQuotation(matcher.group("nickname"));
        Player.getCurrentPlayer().setNickname(nickname);
        Player.savePlayers();
        return ProfileMessages.CHANGE_SUCCESSFULLY;
    }

//    public ProfileMessages changePassword(Matcher matcher) {
//        if (matcher.group("newPassword") == null)
//            return ProfileMessages.EMPTY_FIELD;
//        String newPassword = removeQuotation(matcher.group("newPassword"));
//        ProfileMessages profileMessages = checkPassword(matcher);
//        if (profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY))
//            setPassword(newPassword, anchorPane, oldPasswordError, newPasswordError, vBox);
//        return profileMessages;
//    }

    public ProfileMessages setEmail(Matcher matcher) {
        if (matcher.group("email") == null)
            return ProfileMessages.EMPTY_FIELD;
        String email = removeQuotation(matcher.group("email"));
        ProfileMessages profileMessages = checkEmail(matcher);
        if (profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY)) {
            Player.getCurrentPlayer().setEmail(email);
            Player.savePlayers();
        }
        return profileMessages;
    }

//    public ProfileMessages changeSlogan(Matcher matcher) {
//        if (matcher.group("slogan") == null)
//            return ProfileMessages.EMPTY_FIELD;
//        ProfileMessages profileMessages = checkSlogan(matcher);
//        String slogan = removeQuotation(matcher.group("slogan"));
//        if (profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY))
//            setSlogan(slogan, anchorPane, vBox);
//        return profileMessages;
//    }

    public void setSlogan(String slogan, AnchorPane anchorPane, VBox vBox) {
        Player.getCurrentPlayer().setSlogan(slogan);
        Player.savePlayers();
        anchorPane.getChildren().remove(vBox);
        Menu.getSignupController().showAlert(Alert.AlertType.INFORMATION
                , "Success", "Your slogan successfully changed!");
        initialize();
    }

    public void removeSlogan(AnchorPane anchorPane, VBox vBox) {
        Player.getCurrentPlayer().setSlogan("");
        Player.savePlayers();
        anchorPane.getChildren().remove(vBox);
        Menu.getSignupController().showAlert(Alert.AlertType.INFORMATION
                , "Success", "Your slogan successfully removed!");
        initialize();
    }

    public int highScore() {
        return Player.getCurrentPlayer().getScore();
    }

    public String showRank() {
        return null;
    }

    public String showSlogan() {
        String slogan = Player.getCurrentPlayer().getSlogan();
        if (slogan.isEmpty()) {
            return "Slogan is empty!";
        }
        return slogan;
    }

    public String displayProfile() {
        Player player = Player.getCurrentPlayer();
        StringBuilder string = new StringBuilder();
        string.append("username : ").append(player.getUsername()).append("\nnickname : ").append(player.getNickname())
                .append("\nemail : ").append(player.getEmail()).append("\nslogan : ").append(player.getSlogan())
                .append("\nscore : ").append(player.getScore());

        return string.toString();
    }


    private ProfileMessages checkUsername(Matcher matcher) {
        if (matcher.group("username") == null)
            return ProfileMessages.EMPTY_FIELD;
        String username = removeQuotation(matcher.group("username"));

        if (!isUsernameFormatCorrect(username))
            return ProfileMessages.INCORRECT_USERNAME_FORMAT;

        else if (isUsernameDuplicated(username))
            return ProfileMessages.REPEATED_USERNAME;

        else return ProfileMessages.CHANGE_SUCCESSFULLY;
    }

    public String suggestNewUsername(String username) {
        username = removeQuotation(username);
        Random rand = new Random();
        while (true) {
            int randomNum = rand.nextInt(1000);
            if (Player.getPlayerByUsername(username + randomNum) == null)
                return username + randomNum;
        }
    }

    private boolean isUsernameFormatCorrect(String username) {
        return username.matches("[a-zA-Z_0-9]+");
    }

    private ProfileMessages checkPassword(Matcher password) {
        if (password.group("oldPassword") == null || password.group("newPassword") == null)
            return ProfileMessages.EMPTY_FIELD;
        String oldPassword = removeQuotation(password.group("oldPassword"));
        String newPassword = removeQuotation(password.group("newPassword"));
        if (oldPassword.isEmpty() || newPassword.isEmpty())
            return ProfileMessages.EMPTY_FIELD;
        else if (!Player.getCurrentPlayer().isPasswordCorrect(oldPassword))
            return ProfileMessages.INCORRECT_PASSWORD;
        else if (newPassword.contains(" "))
            return ProfileMessages.PASSWORD_HAVE_SPACE;
        else if (newPassword.length() < 6)
            return ProfileMessages.PASSWORD_LENGTH_WEAK;
        else if (!newPassword.matches(".*[A-Z].*"))
            return ProfileMessages.PASSWORD_UPPERCASE_WEAK;
        else if (!newPassword.matches(".*[a-z].*"))
            return ProfileMessages.PASSWORD_LOWERCASE_WEAK;
        else if (!newPassword.matches(".*[0-9].*"))
            return ProfileMessages.PASSWORD_NUMBER_WEAK;
        else if (!newPassword.matches(".*[`~!@#$%^&*()_+|}{“:?><\\[\\];’,./\\-=]+[^\n]*$"))
            return ProfileMessages.PASSWORD_SPECIFIC_CHAR_WEAK;
        else
            return ProfileMessages.CHANGE_SUCCESSFULLY;
    }

    public String generateRandomPassword() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" +
                "0123456789!@#$%^&*()_+-=[]{}<>,.?/:;|~";
        int PASSWORD_LENGTH = 16;
        SecureRandom random = new SecureRandom();
        StringBuilder randomPassword = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            randomPassword.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return randomPassword.toString();
    }

    private ProfileMessages checkEmail(Matcher matcher) {
        if (matcher.group("email") == null)
            return ProfileMessages.EMPTY_FIELD;
        String email = removeQuotation(matcher.group("email"));
        if (isEmailDuplicated(email))
            return ProfileMessages.EXISTENCE_EMAIL;
        else if (!email.matches("^[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\.]+\\.[a-zA-Z0-9\\.]+$") | email.contains(" "))
            return ProfileMessages.INCORRECT_EMAIL_FORMAT;
        else
            return ProfileMessages.CHANGE_SUCCESSFULLY;
    }

    private boolean isEmailDuplicated(String email) {
        for (Player player : Player.getAllPlayers()) {
            if (player.getEmail().equalsIgnoreCase(email))
                return true;
        }
        return false;
    }

    private boolean isUsernameDuplicated(String username) {
        for (Player player : Player.getAllPlayers()) {
            if (player.getUsername().equals(username))
                return true;
        }
        return false;
    }

    private ProfileMessages checkSlogan(Matcher matcher) {
        if (matcher.group("slogan") == null)
            return ProfileMessages.EMPTY_FIELD;
        String slogan = removeQuotation(matcher.group("slogan"));
        return ProfileMessages.CHANGE_SUCCESSFULLY;
    }

    public String giveRandomSlogan() {
        ArrayList<String> slogans = new ArrayList<>();
        slogans.add("I shall have my revenge, in this life or the next!");
        slogans.add("I will not lose I either win or learn!");
        slogans.add("You are too weak to play with me:)!");
        slogans.add("If I lose, I will lose in such a way that you doubt that you will win!");
        slogans.add("Defend your castle, conquer your foes.");
        int number = (int) (random() / (5));
        return slogans.get(number);
    }

    private String removeQuotation(String buffer) {
        return buffer.replaceAll("\"", "");
    }


    public ProfileMessages changeRandomSlogan(String randomSlogan) {
        Player.getCurrentPlayer().setSlogan(randomSlogan);
        return ProfileMessages.CHANGE_SUCCESSFULLY;
    }

    public void changeUsername(MouseEvent mouseEvent) {
        AnchorPane anchorPane = ProfileMenu.anchorPane;
        TextField username = new TextField();
        Text usernameError = new Text("");
        usernameError.setFill(Color.RED);
        usernameError.setX(750);
        usernameError.setY(535);
        username.setPromptText("new username");
        Button submit = new Button("Submit");
        submit.setStyle(ProfileController.class.getResource("/css/profileMenu.css").toString());
        VBox vBox = new VBox(username, submit);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(32);
        vBox.setPrefHeight(150);
        vBox.setPrefWidth(250);
        vBox.setLayoutX(700);
        vBox.setLayoutY(450);
        anchorPane.getChildren().addAll(vBox, usernameError);
        username.textProperty().addListener((observableValue, oldText, newText) -> {
            if (!isUsernameFormatCorrect(newText)) {
                username.setStyle("-fx-border-color: red");
                usernameError.setText("username format is incorrect!");
            } else {
                username.setStyle("-fx-border-color: white");
                usernameError.setText("");
            }
        });
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setUsername(username.getText(), anchorPane, vBox, usernameError);
            }
        });
    }

    public void changePassword(MouseEvent mouseEvent) {
        AnchorPane anchorPane = ProfileMenu.anchorPane;
        PasswordField oldPassword = new PasswordField();
        PasswordField newPassword = new PasswordField();
        Text newPasswordError = new Text("");
        newPasswordError.setFill(Color.RED);
        newPasswordError.setX(700);
        newPasswordError.setY(625);
        Text oldPasswordError = new Text("");
        oldPasswordError.setX(700);
        oldPasswordError.setY(575);
        oldPasswordError.setFill(Color.RED);
        oldPassword.setPromptText("old password");
        newPassword.setPromptText("new password");
        Button submit = new Button("Submit");
        submit.setStyle(ProfileController.class.getResource("/css/profileMenu.css").toString());
        VBox vBox = new VBox(oldPassword, newPassword, submit);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(32);
        vBox.setPrefHeight(300);
        vBox.setPrefWidth(350);
        vBox.setLayoutX(700);
        vBox.setLayoutY(450);
        anchorPane.getChildren().addAll(vBox, oldPasswordError, newPasswordError);
        newPassword.textProperty().addListener((observableValue, oldText, newText) -> {
            SignUpMessages signUpMessages = Menu.getSignupController().checkPassword(newText);
            if (!signUpMessages.equals(SignUpMessages.PASSWORD_STRONG)) {
                newPassword.setStyle("-fx-border-color: red");
                submit.setDisable(true);
                newPasswordError.setText(signUpMessages.getMessage());
            } else {
                newPassword.setStyle("-fx-border-color: white");
                submit.setDisable(false);
                newPasswordError.setText("");
            }
        });
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!isOldPasswordCorrect(oldPassword.getText())) {
                    oldPasswordError.setText("password is incorrect!");
                    oldPassword.setStyle("-fx-border-color: red");
                    submit.setDisable(true);
                } else {
                    submit.setDisable(false);
                    if (!newPassword.getText().isEmpty())
                        setPassword(newPassword.getText(), anchorPane, oldPasswordError, newPasswordError, vBox);
                }
            }
        });
    }

    private boolean isOldPasswordCorrect(String password) {
        return Player.getCurrentPlayer().isPasswordCorrect(password);
    }

    public void setEmail(MouseEvent mouseEvent) {
        AnchorPane anchorPane = ProfileMenu.anchorPane;
        TextField email = new TextField();
        Text emailError = new Text("");
        emailError.setFill(Color.RED);
        emailError.setX(750);
        emailError.setY(535);
        email.setPromptText("new Email");
        Button submit = new Button("Submit");
        submit.setStyle(ProfileController.class.getResource("/css/profileMenu.css").toString());
        VBox vBox = new VBox(email, submit);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(32);
        vBox.setPrefHeight(150);
        vBox.setPrefWidth(250);
        vBox.setLayoutX(700);
        vBox.setLayoutY(450);
        anchorPane.getChildren().addAll(vBox, emailError);
        email.textProperty().addListener((observableValue, oldText, newText) -> {
            SignUpMessages signUpMessages = Menu.getSignupController().checkEmailErrors(newText);
            if (!signUpMessages.equals(SignUpMessages.WITHOUT_ERROR)) {
                submit.setDisable(true);
                email.setStyle("-fx-border-color: red");
                emailError.setText(signUpMessages.getMessage());
            } else {
                email.setStyle("-fx-border-color: white");
                emailError.setText("");
                submit.setDisable(false);
            }
        });
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!email.getText().isEmpty())
                    setEmail(email.getText(), anchorPane, vBox, emailError);
            }
        });
    }

    private void setEmail(String email, AnchorPane anchorPane, VBox vBox, Text emailError) {
        Player.getCurrentPlayer().setEmail(email);
        Player.savePlayers();
        Menu.getSignupController().
                showAlert(Alert.AlertType.INFORMATION, "Success", "Email successfully changed!");
        anchorPane.getChildren().removeAll(vBox, emailError);
        initialize();
    }

    public void changeNickname(MouseEvent mouseEvent) {
        AnchorPane anchorPane = ProfileMenu.anchorPane;
        TextField nickname = new TextField();
        nickname.setPromptText("new nickname");
        Button submit = new Button("Submit");
        submit.setStyle(ProfileController.class.getResource("/css/profileMenu.css").toString());
        VBox vBox = new VBox(nickname, submit);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(32);
        vBox.setPrefHeight(150);
        vBox.setPrefWidth(250);
        vBox.setLayoutX(700);
        vBox.setLayoutY(450);
        anchorPane.getChildren().addAll(vBox);
        nickname.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.isEmpty())
                nickname.setStyle("-fx-border-color: white");
        });
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!nickname.getText().isEmpty())
                    setNickname(nickname.getText(), anchorPane, vBox);
                else {
                    nickname.setStyle("-fx-border-color: red");
                }
            }
        });
    }

    private void setNickname(String nickname, AnchorPane anchorPane, VBox vBox) {
        Player.getCurrentPlayer().setNickname(nickname);
        Player.savePlayers();
        Menu.getSignupController().
                showAlert(Alert.AlertType.INFORMATION, "Success", "nickname successfully changed!");
        anchorPane.getChildren().removeAll(vBox);
        initialize();
    }

    public void changeSlogan(MouseEvent mouseEvent) {
        AnchorPane anchorPane = ProfileMenu.anchorPane;
        TextField slogan = new TextField();
        slogan.setPromptText("new slogan");
        Button delete = new Button("Delete Slogan");
        delete.setStyle(ProfileController.class.getResource("/css/profileMenu.css").toString());
        Button submit = new Button("Submit");
        submit.setStyle(ProfileController.class.getResource("/css/profileMenu.css").toString());
        VBox vBox = new VBox(slogan, delete, submit);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(32);
        vBox.setPrefHeight(150);
        vBox.setPrefWidth(250);
        vBox.setLayoutX(700);
        vBox.setLayoutY(450);
        anchorPane.getChildren().addAll(vBox);
        slogan.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.isEmpty())
                slogan.setStyle("-fx-border-color: white");
        });
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!slogan.getText().isEmpty())
                    setSlogan(slogan.getText(), anchorPane, vBox);
                else {
                    slogan.setStyle("-fx-border-color: red");
                }
            }
        });
        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                removeSlogan(anchorPane, vBox);
            }
        });
    }

    public void changeAvatar(MouseEvent mouseEvent) throws Exception {
        start(LoginMenu.stage);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(ProfileController.class.getResource("/fxml/chooseAvatar.fxml"));
        FileChooser fileChooser = new FileChooser();
        Button chooseFile = new Button("Choose your photo");
        chooseFile.setLayoutX(700);
        chooseFile.setLayoutY(600);
        chooseFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                File selectedFile = fileChooser.showOpenDialog(stage);
                try {
                    setAvatar(selectedFile);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        anchorPane.getChildren().add(chooseFile);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    private void setAvatar(File selectedFile) throws Exception {
        String url = selectedFile.getAbsolutePath();
        Player player = Player.getCurrentPlayer();
        player.setAvatarResource(url);
        Player.savePlayers();
        new ProfileMenu().start(LoginMenu.stage);
    }

    public void chooseAvatar(MouseEvent mouseEvent) throws Exception {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        Player player = Player.getCurrentPlayer();
        player.setAvatarResource(imageView.getImage().getUrl());
        Player.savePlayers();
        new ProfileMenu().start(LoginMenu.stage);
    }

    public void showScoreboard(MouseEvent mouseEvent) throws Exception {
        new Scoreboard().start(LoginMenu.stage);
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }
}