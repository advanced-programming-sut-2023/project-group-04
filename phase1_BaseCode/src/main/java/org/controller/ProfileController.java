package org.controller;


import org.model.Player;
import org.view.Menu;
import org.view.ProfileMessages;

import java.util.Random;
import java.util.regex.Matcher;

public class ProfileController {

    public ProfileMessages changeUsername(Matcher matcher) {
        ProfileMessages profileMessages = checkUsername(matcher);
        if (profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY))
            setUsername(matcher.group("username"));
        return profileMessages;
    }

    public void setUsername(String username) {
        Player.getLoggedInPlayer().setUsername(username);
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

    public String highScore() {
        return null;
    }

    public String showRank() {
        return null;
    }

    public String showSlogan() {
        return null;
    }

    public String displayProfile() {
        return null;
    }


    private ProfileMessages checkUsername(Matcher matcher) {
        String username = matcher.group("username");
        if (username.isEmpty())
            return ProfileMessages.EMPTY_FIELD;

        username = username.replaceAll("\"", "");
        if (isUsernameFormatCorrect(username))
            return ProfileMessages.INCORRECT_USERNAME_FORMAT;

        else if (Player.getPlayerByUsername(username) != null)
            return ProfileMessages.REPEATED_USERNAME;

        else return ProfileMessages.CHANGE_SUCCESSFULLY;
    }

    public String suggestNewUsername(String username) {
        while (Player.getPlayerByUsername(username) != null) {
            Random rand = new Random();
            int randomNum = rand.nextInt(1000);
            if (Player.getPlayerByUsername(username + randomNum) == null)
                return username + randomNum;
        }
        return null;
    }

    private boolean isUsernameFormatCorrect(String username) {
        return username.matches("[a-zA-Z_0-9]+");
    }

    private ProfileMessages checkPassword(Matcher matcher) {
        String oldPassword = matcher.group("oldPassword");
        if (!Player.getLoggedInPlayer().isPasswordCorrect(oldPassword))
            return ProfileMessages.PASSWORD_CONFIRM_DOES_NOT_MATCH;
        return null;
    }

    private ProfileMessages checkEmail(Matcher matcher) {
        String email = matcher.group("email");
        if (email.isEmpty())
            return ProfileMessages.EMPTY_FIELD;
        else if (isEmailDuplicated(email))
            return ProfileMessages.EXISTENCE_EMAIL;
        else if (!email.matches("^\\w+@\\w+\\.\\w+$"))
            return ProfileMessages.INCORRECT_EMAIL_FORMAT;
        else
            return ProfileMessages.WITHOUT_ERROR;
    }

    private boolean isEmailDuplicated(String email) {
        for (Player player : Player.getAllPlayers()) {
            if (player.getEmail().equals(email))
                return true;
        }
        return false;
    }

    private ProfileMessages checkNickname(Matcher matcher) {
        return null;
    }

    private ProfileMessages checkSlogan(Matcher matcher) {
        return null;
    }
}
