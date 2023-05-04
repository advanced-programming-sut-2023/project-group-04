package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {
    SHOW_POPULARITY_FACTORS(""),
    SHOW_POPULARITY(""),
    SHOW_FOODlIST(""),
    CHANGE_FOOD_RATE(""),
    SHOW_FOOD_RATE(""),
    CHANGE_TAX_RATE(""),
    SHOW_TAX_RATE(""),
    CHANGE_FEAR_RATE(""),
    SHOW_FEAR_RATE(""),
    DROP_BUILDING(""),
    SELECT_BUILDING(""),
    CREATE_UNIT(""),
    REPAIR(""),
    SELECT_UNIT(""),
    MOVE_UNIT(""),
    PATROL_UNIT(""),
    SET_UNIT_CONDITION(""),
    ATTACK_ENEMY(""),
    AIR_ATTACK(""),
    POUR_OIL(""),
    DIG_TUNNEL(""),
    BUILD_EQUIPMENT(""),
    DISBAND_UNIT(""),
    SHOW_MAP("\\s*show\\s+map\\s+-x\\s+(?<xAsis>\\d+)\\s+-y\\s+(?<yAsis>\\d+)\\s*");

    private final String regex;

    private GameCommands(String regex) {
        this.regex = regex;
    }


    public static Matcher getMatcher(String input, GameCommands gameCommands) {
        Matcher matcher = Pattern.compile(gameCommands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
