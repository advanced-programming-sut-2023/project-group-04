package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {
    SHOW_POPULARITY_FACTORS("^[ \\t]*show\\s+popularity\\s+factors[ \\t]*$"),
    SHOW_POPULARITY("^[ \\t]*show\\s+popularity[ \\t]*$"),
    SHOW_FOODlIST("^[ \\t]*show\\s+food\\s+list[ \\t]*$"),
    CHANGE_FOOD_RATE("^[ \\t]*food\\s+rate(?=.*-r\\s+(?<foodRate>[\\d]*))+(\\s*(-r\\s+([\\d]*)))[ \\t]*$"),
    SHOW_FOOD_RATE("^[ \\t]*food\\s+rate\\s+show[ \\t]*$"),
    CHANGE_TAX_RATE("^[ \\t]*tax\\s+rate(?=.*-r\\s+(?<taxRate>[\\d]*))+(\\s*(-r\\s+([\\d]*)))[ \\t]*$"),
    SHOW_TAX_RATE("^[ \\t]*tax\\s+rate\\s+show[ \\t]*$"),
    CHANGE_FEAR_RATE("^[ \\t]*fear\\s+rate(?=.*-r\\s+(?<fearRate>[\\d]*))+(\\s*(-r\\s+([\\d]*)))[ \\t]*$"),
    SHOW_FEAR_RATE("^[ \\t]*fear\\s+rate\\s+show[ \\t]*$"),
    DROP_BUILDING("^[ \t]*dropbuilding(?=.*-type\\s+(?<type>\"[^\"]*\"|[^\\s\"]*))(?=.*-x\\s+(?<x>[\\d]*))" +
            "(?=.*-y\\s+(?<y>[\\d]*))(\\s*(-type\\s+(\"[^\"]*\"|[^\\s\"]*)|-x\\s+([\\d]*)|-y\\s+([\\d]*))){3}[ \t]*$"),
    SELECT_BUILDING("^[ \t]*select\\s+building(?=.*-x\\s+(?<x>[\\d]*))" +
            "(?=.*-y\\s+(?<y>[\\d]*))(\\s*(-x\\s+([\\d]*)|-y\\s+([\\d]*))){2}[ \t]*$"),
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
    DISBAND_UNIT("");


    private final String regex;

    private GameCommands(String regex) {
        this.regex = regex;
    }


    public static Matcher getMatcher(String input, GameCommands gameCommands) {
        Matcher matcher = Pattern.compile(gameCommands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
