package org.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands {
    CHANGE_USERNAME("^[^\n\\s]*profile\\s+change\\s+-u\\s+((?<username>([^\"][\\S]+))|\"(?<username1>[^\"]+)\")[^\n\\s]*$"),
    CHANGE_NICKNAME("^[^\n\\s]*profile\\s+change\\s+-n\\s+((?<nickname>([^\"][\\S]+))|\"(?<nickname1>[^\"]+)\")[^\n\\s]*$"),
    CHANGE_PASSWORD("^[^\n\\s]*profile\\s+change\\s+password\\s+-o\\s+" +
            "(?<oldpassword>[\\S]+)\\s+-n\\s+(?<newpassword>([\\S]+|random))[^\n\\s]*$"),
    CHANGE_EMAIL("^[^\n\\s]*profile\\s+change\\s+-e\\s+(?<email>[\\S]+)[^\n\\s]*$"),
    CHANGE_SLOGAN("^[^\n]*profile\\s+change\\s+-s\\s+((?<slogan>([^\"][\\S]+))|\"(?<slogan1>[^\"]+)\")[^\n]*$"),
    REMOVE_SLOGAN("^[^\n]*profile\\s+remove\\s+slogan[^\n]*$"),
    DISPLAY_HIGHSCORE("^[^\n]*profile\\s+display\\s+highscore[^\n]*$"),
    DISPLAY_RANK("^[^\n]*profile\\s+display\\s+rank[^\n]*$"),
    DISPLAY_SLOGAN("^[^\n]*profile\\s+display\\s+slogan[^\n]*$"),
    DISPLAY_PROFILE("^[^\n]*profile\\s+display[^\n]*$"),
    BACK("^\\s*back\\s*$");


    private final String regex;

    private ProfileCommands(String regex) {
        this.regex = regex;
    }


    public static Matcher getMatcher(String input, ProfileCommands profileCommands) {
        Matcher matcher = Pattern.compile(profileCommands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

}