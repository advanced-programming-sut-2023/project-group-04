package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    LOGOUT("\\s*user\\s+logout\\s*"),
    ENTER_PROFILE_MENU("\\s*enter\\s+profile\\s+menu\\s*"),
    ENTER_ENVIRONMENT_MENU("\\s*enter\\s+environment\\s+menu\\s*"),
    ENTER_SHOP_MENU("\\s*enter\\s+shop\\s+menu\\s*"),
    START_GAME("\\s*start\\s+game\\s+-playerNumber\\s+(?<playerNumbers>\\d+)\\s+" +
            "-user\\s+(?<username>[^\"\\s]+|\"[^\"]+\")+"),
    //TODO fix start game regex keyhaaaaaaan!
    SHOW_MAP("\\s*show\\s+map\\s+-x\\s+(?<xAsis>\\d+)\\s+-y\\s+(?<yAsis>\\d+)\\s*"),
    BACK("\\s*back\\s*"),
    ;
    private final String regex;

    private MainMenuCommands(String regex) {
        this.regex = regex;
    }


    public static Matcher getMatcher(String input, MainMenuCommands mainMenuCommands) {
        Matcher matcher = Pattern.compile(mainMenuCommands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}