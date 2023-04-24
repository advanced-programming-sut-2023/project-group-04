package org.controller;


import org.model.Player;
import org.view.Menu;
import org.view.ProfileMessages;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;

public class ProfileController {

    public ProfileMessages changeUsername(Matcher matcher) {
        ProfileMessages profileMessages = checkUsername(matcher);

        if (profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY))
            setUsername(removeQuotation(matcher.group("username")));
        return profileMessages;
    }

    public void setUsername(String username) {
        Player.getLoggedInPlayer().setUsername(username);
    }

    public void setPassword(String password) {
        Player.getLoggedInPlayer().setPassword(password);
    }

    public ProfileMessages changeNickname(Matcher matcher) {
        String nickname = removeQuotation(matcher.group("nickname"));

        if (nickname.isEmpty()) {
            return ProfileMessages.EMPTY_FIELD;
        }
        Player.getLoggedInPlayer().setNickname(nickname);
        return ProfileMessages.CHANGE_SUCCESSFULLY;
    }

    public ProfileMessages changePassword(Matcher matcher) {
        String newPassword = removeQuotation(matcher.group("newPassword"));
        ProfileMessages profileMessages;
        profileMessages = checkPassword(matcher);
        System.out.println(profileMessages.getMessage());
        if (profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY))
            setPassword(newPassword);
        return profileMessages;
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
        String username = removeQuotation(matcher.group("username"));

        if (username.isEmpty())
            return ProfileMessages.EMPTY_FIELD;

        if (isUsernameFormatCorrect(username))
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
        String oldPassword = removeQuotation(matcher.group("oldPassword"));
        String newPassword = removeQuotation(matcher.group("newPassword"));

        if (oldPassword.isEmpty() | newPassword.isEmpty())
            return ProfileMessages.EMPTY_FIELD;
        else if (!Player.getLoggedInPlayer().isPasswordCorrect(oldPassword))
            return ProfileMessages.INCORRECT_PASSWORD;
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

    private boolean isUsernameDuplicated(String username) {
        for (Player player : Player.getAllPlayers()) {
            if (player.getUsername().equals(username))
                return true;
        }
        return false;
    }

    private ProfileMessages checkSlogan(Matcher matcher) {
        return null;
    }

    private String removeQuotation(String buffer) {
        return buffer.replaceAll("\"", "");
    }


}
