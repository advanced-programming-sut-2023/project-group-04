package org.controller;


import org.model.Player;
import org.view.Menu;
import org.view.ProfileMessages;

import java.util.regex.Matcher;

public class ProfileController {

    public ProfileMessages changeUsername(Matcher matcher) {

        return null;
    }

    public ProfileMessages changeNickname(Matcher matcher) {
        return null;
    }

    public ProfileMessages changePassword(Matcher matcher) {
        return null;
    }

    public ProfileMessages changeEmail(Matcher matcher) {
        return null;
    }

    public ProfileMessages changeSlogan(Matcher matcher) {
        return null;
    }

    public ProfileMessages removeSlogan() {
        return null;
    }

    public ProfileMessages highScore() {
        return null;
    }

    public ProfileMessages showRank() {
        return null;
    }

    public ProfileMessages showSlogan() {
        return null;
    }

    public ProfileMessages displayProfile() {
        return null;
    }


    private ProfileMessages checkUsername(Matcher matcher) {
        String username;
        if (matcher.group("username") == null && matcher.group("username1") == null)
            return ProfileMessages.EMPTY_FIELD;
        if (matcher.group("username") != null) {
            username = matcher.group("username");
            if (!isUsernameFormatCorrect(username))
                return ProfileMessages.INCORRECT_USERNAME_FORMAT;
            if (Player.getPlayerByUsername(username) != null) {
                if (acceptSuggestedUsername(suggestNewUsername(username)))
                    username = suggestNewUsername(username);
                else
                    return ProfileMessages.CHANGING_USERNAME_FAILED;
            }

        }

    }

    private String suggestNewUsername(String username) {
        int counter = 30;
        while (Player.getPlayerByUsername(username) != null) {
            username += counter;
            counter++;
        }
        return username;
    }

    public boolean acceptSuggestedUsername(String suggestedUsername) {
        System.out.println("this username already exists!\nYou can register with this username: \"" + suggestedUsername
                + "\"\n" + "type <YES> to accept!");
        return Menu.getScanner().nextLine().equals("YES");
    }

    private boolean isUsernameFormatCorrect(String username) {
        return username.matches("[a-zA-Z_0-9]+");
    }

    private ProfileMessages checkPassword(Matcher matcher) {
        return null;
    }

    private ProfileMessages checkEmail(Matcher matcher) {
        return null;
    }

    private ProfileMessages checkNickname(Matcher matcher) {
        return null;
    }

    private ProfileMessages checkSlogan(Matcher matcher) {
        return null;
    }
}
