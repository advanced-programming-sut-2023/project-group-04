package org.controller;


import org.model.Player;
import org.view.CommandsEnum.ProfileMessages;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;

import static java.lang.Math.random;

public class ProfileController {

    public ProfileMessages changeUsername(Matcher matcher) {
        ProfileMessages profileMessages = checkUsername(matcher);

        if (profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY))
            setUsername(removeQuotation(matcher.group("username")));
        return profileMessages;
    }

    public void setUsername(String username) {
        Player.getCurrentPlayer().setUsername(username);
        Player.savePlayers();
    }

    public void setPassword(String password) {
        Player.getCurrentPlayer().setPassword(password);
        Player.savePlayers();
    }

    public ProfileMessages changeNickname(Matcher matcher) {
        String nickname = removeQuotation(matcher.group("nickname"));

        if (nickname.isEmpty()) {
            return ProfileMessages.EMPTY_FIELD;
        }
        Player.getCurrentPlayer().setNickname(nickname);
        Player.savePlayers();
        return ProfileMessages.CHANGE_SUCCESSFULLY;
    }

    public ProfileMessages changePassword(Matcher matcher) {
        String newPassword = removeQuotation(matcher.group("newPassword"));
        ProfileMessages profileMessages = checkPassword(matcher);
        if (profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY))
            setPassword(newPassword);
        return profileMessages;
    }

    public ProfileMessages changeEmail(Matcher matcher) {
        String email = removeQuotation(matcher.group("email"));
        ProfileMessages profileMessages = checkEmail(matcher);
        if (profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY)) {
            Player.getCurrentPlayer().setEmail(email);
            Player.savePlayers();
        }
        return profileMessages;
    }

    public ProfileMessages changeSlogan(Matcher matcher) {
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
        Player.getCurrentPlayer().setSlogan(null);
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
        String email = removeQuotation(matcher.group("email"));
        if (email.isEmpty())
            return ProfileMessages.EMPTY_FIELD;
        else if (isEmailDuplicated(email))
            return ProfileMessages.EXISTENCE_EMAIL;
        else if (!email.matches("^\\w+@\\w+\\.\\w+$") | email.contains(" "))
            return ProfileMessages.INCORRECT_EMAIL_FORMAT;
        else
            return ProfileMessages.CHANGE_SUCCESSFULLY;
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
        String slogan = removeQuotation(matcher.group("slogan"));
        if (slogan.isEmpty())
            return ProfileMessages.EMPTY_FIELD;
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


}
