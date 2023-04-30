package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {

    NOTHING("");
    private String regex;
    private GameCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameCommands gameCommands) {
        Matcher matcher = Pattern.compile(gameCommands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
