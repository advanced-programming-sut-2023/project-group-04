package org.controller;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.model.Player;
import org.view.CommandsEnum.ProfileMessages;

import javafx.scene.image.ImageView;
import org.view.Menu;
import org.view.ProfileMenu;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;

import static java.lang.Math.random;

public class ProfileController {
    public Text username;
    public Text email;
    public Text slogan;
    public Text nickname;
    public ImageView avatar;

    @FXML
    public void initialize() {
        Player player = Player.getCurrentPlayer();
        username.setText(player.getUsername());
        email.setText(player.getEmail());
        if (player.getSlogan() == null || player.getSlogan().equals(""))
            slogan.setText("slogan is empty");
        else slogan.setText(player.getSlogan());
        String avatarURL = player.getAvatarResource();
        if (avatarURL != null)
            avatar.setImage(new Image(avatarURL));
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

    public void setPassword(String password) {
        Player.getCurrentPlayer().setPassword(Player.getSHA256Hash(password));
        Player.savePlayers();
    }

    public ProfileMessages changeNickname(Matcher matcher) {
        if (matcher.group("nickname") == null)
            return ProfileMessages.EMPTY_FIELD;
        String nickname = removeQuotation(matcher.group("nickname"));
        Player.getCurrentPlayer().setNickname(nickname);
        Player.savePlayers();
        return ProfileMessages.CHANGE_SUCCESSFULLY;
    }

    public ProfileMessages changePassword(Matcher matcher) {
        if (matcher.group("newPassword") == null)
            return ProfileMessages.EMPTY_FIELD;
        String newPassword = removeQuotation(matcher.group("newPassword"));
        ProfileMessages profileMessages = checkPassword(matcher);
        if (profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY))
            setPassword(newPassword);
        return profileMessages;
    }

    public ProfileMessages changeEmail(Matcher matcher) {
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

    public ProfileMessages changeSlogan(Matcher matcher) {
        if (matcher.group("slogan") == null)
            return ProfileMessages.EMPTY_FIELD;
        ProfileMessages profileMessages = checkSlogan(matcher);
        String slogan = removeQuotation(matcher.group("slogan"));
        if (profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY))
            setSlogan(slogan);
        return profileMessages;
    }

    public void setSlogan(String slogan) {
        Player.getCurrentPlayer().setSlogan(slogan);
        Player.savePlayers();
    }

    public ProfileMessages removeSlogan() {
        Player.getCurrentPlayer().setSlogan("");
        Player.savePlayers();
        return ProfileMessages.CHANGE_SUCCESSFULLY;
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

    private ProfileMessages checkPassword(Matcher matcher) {
        if (matcher.group("oldPassword") == null || matcher.group("newPassword") == null)
            return ProfileMessages.EMPTY_FIELD;
        String oldPassword = removeQuotation(matcher.group("oldPassword"));
        String newPassword = removeQuotation(matcher.group("newPassword"));
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
        usernameError.setY(510);
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
}