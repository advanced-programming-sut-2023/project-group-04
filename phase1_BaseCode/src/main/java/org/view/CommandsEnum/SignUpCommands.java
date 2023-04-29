package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public enum SignUpCommands {
    CREATE_ACCOUNT("\\s*create user (?=.*-u (?<username>\"[^\\\"]*\"|[^\\s\\\"]*))" +
            "(?=.*-p (?<password>\"[^\\\"]*\"|[^\\s\\\"]*)\\s*(?<passwordConfirm>\"[^\\\"]*\"|[^\\s\\\"]*))" +
            "(?=.*-e (?<email>\"[^\\\"]*\"|[^\\s\\\"]*))(?=.*-n (?<nickname>\"[^\\\"]*\"|[^\\s\\\"]*))" +
            "(?=.*-s (?<slogan>\"[^\\\"]*\"|[^\\s\\\"]*))?(\\s+(-u (\"[^\\\"]*\"|[^\\s\\\"]*))|" +
            "(-p (\"[^\\\"]*\"|[^\\s\\\"]*)\\s+(\"[^\\\"]*\"|[^\\s\\\"]*))|(-e (\"[^\\\"]*\"|[^\\s\\\"]*))|" +
            "(-n (\"[^\\\"]*\"|[^\\s\\\"]*))|(-s (\"[^\\\"]*\"|[^\\s\\\"]*))?){4,5}\\s*"),
    PICK_SECURITY_QUESTION("\\s*question pick(?=.*-q (?<questionNumber>\"[^\\\"]*\"|[^\\s\\\"]*))" +
            "(?=.*-a (?<answer>\"[^\\\"]*\"|[^\\s\\\"]*))(?=.*-c (?<answerConfirm>\"[^\\\"]*\"|[^\\s\\\"]*))" +
            "(\\s+((-q (\"[^\\\"]*\"|[^\\s\\\"]*))|(-a (\"[^\\\"]*\"|[^\\s\\\"]*))|" +
            "(-c (\"[^\\\"]*\"|[^\\s\\\"]*)))){3}\\s*"),
    LOGIN_USER("^\\s*user\\s+login(?=.*-u (?<username>\"[^\"]*\"|[^\\s\"]*))" +
            "(?=.*-p (?<password>\"[^\"]*\"|[^\\s\"]*))(?=.*(?<status>--stay-logged-in))(\\s+((-u (\"[^\"]*\"|[^\\s\"]*))" +
            "|(-p (\"[^\"]*\"|[^\\s\"]*))|--stay-logged-in)){3}\\s*$"),
    FORGET_PASSWORD("^\\s*forget\\s+my\\s+password(?=.*-u (?<username>\"[^\"]*\"|[^\\s\"]*))" +
            "(\\s+((-u (\"[^\"]*\"|[^\\s\"]*)))){1}\\s*$"),
    LOGOUT("\\s*user\\s+logout\\s*"),
    BACK("\\s*back\\s*"),
    I_DONT_HAVE_ACCOUNT("\\s*i\\s+dont\\s+have\\s+an\\s+account!\\s*"),
    SET_NEW_PASSWORD("^\\s*new\\s+password(?=.*-p (?<password>\"[^\"]*\"|[^\\s\"]*))" +
            "(?=.*-c (?<passwordConfirm>\"[^\"]*\"|[^\\s\"]*))(\\s+((-p (\"[^\"]*\"|[^\\s\"]*))" +
            "|(-c (\"[^\"]*\"|[^\\s\"]*)))){2}\\s*$"),
    RANDOM_SLOGAN_AND_PASSWORD("Your slogan is \"[^\"]+\"\nYour random password is: \\S+"),
    RANDOM_PASSWORD("Your random password is: \\S+");

    private final String regex;

    private SignUpCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, SignUpCommands signUpCommands) {
        Matcher matcher = Pattern.compile(signUpCommands.regex).matcher(input);
        return matcher.find() ? matcher : null;
    }
}
