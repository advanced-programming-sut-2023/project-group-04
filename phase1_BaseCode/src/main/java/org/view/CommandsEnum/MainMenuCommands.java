package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    LOGOUT("\\s*user\\s+logout\\s*"),
    ENTER_PROFILE_MENU("\\s*enter\\s+profile\\s+menu\\s*"),
    ENTER_ENVIRONMENT_MENU("\\s*enter\\s+environment\\s+menu\\s*"),
    ENTER_SHOP_MENU("\\s*enter\\s+shop\\s+menu\\s*"),
    START_GAME("\\s*start\\s+game(?=.*-n\\s+(?<mapName>\\\"[^\\\"]*\\\"|[^\\s\\\"]*))(?=.*-users\\s+" +
            "\\{(?<allUsers>[^\\s\\\"]+)})(\\s+((-n\\s+(\\\"[^\\\"]*\\\"|[^\\s\\\"]*))|(-users\\s+\\{\\S+}))){2}\\s*")
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