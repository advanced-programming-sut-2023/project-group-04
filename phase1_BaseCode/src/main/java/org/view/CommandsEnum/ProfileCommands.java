package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands {
    CHANGE_USERNAME("^[ \t]*profile\\s+change(?=.*-u (?<username>\"[^\"]*\"|[^\\s\"]*))" +
            "(\\s*(-u (\"[^\"]*\"|[^\\s\"]*))){1}[ \t]*$"),
    CHANGE_NICKNAME("^[ \t]*profile\\s+change(?=.*-n (?<nickname>\"[^\"]*\"|[^\\s\"]*))" +
            "(\\s*(-n (\"[^\"]*\"|[^\\s\"]*))){1}[ \t]*$"),
    CHANGE_PASSWORD("^[ \t]*profile\\s+change(?=.*-o\\s+(?<oldPassword>\"[^\"]*\"|[^\\s\"]*))" +
            "(?=.*-n\\s+(?<newPassword>\"[^\"]*\"|[^\\s\"]*))" +
            "(\\s*(-o\\s+(\"[^\"]*\"|[^\\s\"]*)|-n\\s+(\"[^\"]*\"|[^\\s\"]*))){2}[ \t]*$"),
    CHANGE_EMAIL("^[ \t]*profile\\s+change(?=.*-e (?<email>\"[^\"]*\"|[^\\s\"]*))" +
            "(\\s*(-e (\"[^\"]*\"|[^\\s\"]*))){1}[ \t]*$"),
    CHANGE_SLOGAN("^[ \t]*profile\\s+change(?=.*-s (?<slogan>\"[^\"]*\"|[^\\s\"]*))" +
            "(\\s*(-s (\"[^\"]*\"|[^\\s\"]*))){1}[ \t]*$"),
    REMOVE_SLOGAN("^[ \t]*profile\\s+remove\\s+slogan[ \t]*$"),
    DISPLAY_HIGH_SCORE("^[ \t]*profile\\s+display\\s+highscore[ \t]*$"),
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