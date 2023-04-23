package org.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands {
    CHANGE_USERNAME("^[ \t]**profile\\s+change\\s+-u\\s+((?<username>([^\"][\\S]+))|\"(?<username1>[^\"]+)\")[ \t]*$"),
    CHANGE_NICKNAME("^[ \t]**profile\\s+change\\s+-n\\s+((?<nickname>([^\"][\\S]+))|\"(?<nickname1>[^\"]+)\")[ \t]*$"),
    CHANGE_PASSWORD("^[ \t]**profile\\s+change\\s+password\\s+-o\\s+" +
            "(?<oldpassword>[\\S]+)\\s+-n\\s+(?<newpassword>([\\S]+|random))[ \t]*$"),
    CHANGE_EMAIL("^[ \t]**profile\\s+change\\s+-e\\s+(?<email>[\\S]+)[ \t]*$"),
    CHANGE_SLOGAN("^[ \t]*profile\\s+change\\s+-s\\s+((?<slogan>([^\"][\\S]+))|\"(?<slogan1>[^\"]+)\")[ \t]*$"),
    REMOVE_SLOGAN("^[ \t]*profile\\s+remove\\s+slogan[ \t]*$"),
    DISPLAY_HIGHSCORE("^[ \t]*profile\\s+display\\s+highscore[ \t]*$"),
    DISPLAY_RANK("^[ \t]*profile\\s+display\\s+rank[ \t]*$"),
    DISPLAY_SLOGAN("^[ \t]*profile\\s+display\\s+slogan[ \t]*$"),
    DISPLAY_PROFILE("^[ \t]*profile\\s+display[ \t]*$"),
    BACK("^[ \t]*back[ \t]*$");


    private final String regex;

    private ProfileCommands(String regex) {
        this.regex = regex;
    }


    public static Matcher getMatcher(String input, ProfileCommands profileCommands) {
        Matcher matcher = Pattern.compile(profileCommands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

}