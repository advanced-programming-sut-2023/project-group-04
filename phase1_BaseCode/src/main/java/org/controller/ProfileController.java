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

    public void setUsername(String username, AnchorPane anchorPane, VBox vBox, Text usernameError) {
        if (isUsernameFormatCorrect(username) && !isUsernameDuplicated(username)) {
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

    private boolean isUsernameFormatCorrect(String username) {
        return username.matches("[a-zA-Z_0-9]+");
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
            } else if (isUsernameDuplicated(newText)) {
                username.setStyle("-fx-border-color: red");
                usernameError.setText("this username already exists!");
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
            } else if (isEmailDuplicated(newText)) {
                submit.setDisable(true);
                email.setStyle("-fx-border-color: red");
                emailError.setText(ProfileMessages.EXISTENCE_EMAIL.getMessage());
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
        if (!isEmailDuplicated(email)) {
            Player.getCurrentPlayer().setEmail(email);
            Player.savePlayers();
            Menu.getSignupController().
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Email successfully changed!");
            anchorPane.getChildren().removeAll(vBox, emailError);
            initialize();
        }
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