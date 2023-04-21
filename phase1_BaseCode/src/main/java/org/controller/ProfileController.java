package org.controller;


import org.model.Player;

import java.util.regex.Matcher;

public class ProfileController {

    public String changeUsername(Matcher matcher) {
        Player.getLoggedInPlayer().setUsername(matcher.group("username"));
        return null;
    }

    public String changeNickname(Matcher matcher) {
        return null;
    }

    public String changePassword(Matcher matcher) {
        return null;
    }

    public String changeEmail(Matcher matcher) {
        return null;
    }

    public String changeSlogan(Matcher matcher) {
        return null;
    }

    public String removeSlogan() {
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

}
