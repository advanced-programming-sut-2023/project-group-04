package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {
    SHOW_POPULARITY_FACTORS("^\\s*show\\s+popularity\\s+factors\\s*$"),
    SHOW_POPULARITY("^\\s*show\\s+popularity\\s*$"),
    SHOW_FOODlIST("^\\s*show\\s+food\\s+list\\s*$"),
    CHANGE_FOOD_RATE("^\\s*food\\s+rate(?=.*-r\\s+(?<foodRate>[\\d]*))(\\s*(-r\\s+([\\d]*)))\\s*$"),
    SHOW_FOOD_RATE("^\\s*food\\s+rate\\s+show\\s*$"),
    CHANGE_TAX_RATE("^\\s*tax\\s+rate(?=.*-r\\s+(?<taxRate>[\\d]*))(\\s*(-r\\s+([\\d]*)))\\s*$"),
    SHOW_TAX_RATE("^\\s*tax\\s+rate\\s+show\\s*$"),
    CHANGE_FEAR_RATE("^\\s*fear\\s+rate(?=.*-r\\s+(?<fearRate>[\\d]*))(\\s*(-r\\s+([\\d]*)))\\s*$"),
    SHOW_FEAR_RATE("^\\s*fear\\s+rate\\s+show\\s*$"),
    DROP_BUILDING("^\\s*drop\\s+building(?=.*-type\\s+(?<type>\"[^\"]*\"|[^\\s\"]*))(?=.*-x\\s+(?<x>[\\d]*))" +
            "(?=.*-y\\s+(?<y>[\\d]*))(?=.*-d\\s+(?<direction>up|right))?(\\s*(-type\\s+(\"[^\"]*\"|[^\\s\"]*)|" +
            "-x\\s+([\\d]*)|-y\\s+([\\d]*)|-d\\s+(up|right))?){4}\\s*$"),
    SELECT_BUILDING("^\\s*select\\s+building(?=.*-x\\s+(?<x>[\\d]*))" +
            "(?=.*-y\\s+(?<y>[\\d]*))(\\s*(-x\\s+([\\d]*)|-y\\s+([\\d]*))){2}\\s*$"),
    CREATE_UNIT("^\\s*create\\s+unit(?=.*-type\\s+(?<type>\"[^\"]*\"|[^\\s\"]*))(?=.*-c\\s+(?<count>[\\d]*))" +
            "(\\s*(-type\\s+(\"[^\"]*\"|[^\\s\"]*)|-c\\s+([\\d]*))){2}\\s*$"),
    REPAIR("^\\s*repair\\s*$"),
    SELECT_UNIT("^\\s*select\\s+unit(?=.*-x\\s+(?<x>[\\d]*))" +
            "(?=.*-y\\s+(?<y>[\\d]*))" +
            "(?=.*-type\\s+(?<type>\"[^\"]*\"|[^\\s\"]*))?" +
            "(\\s*(-x\\s+([\\d]*)|-y\\s+([\\d]*)|(-type\\s+(\"[^\"]*\"|[^\\s]*))?)){2,3}\\s*$"),
    MOVE_UNIT("^\\s*move\\s+unit\\s+to(?=.*-x\\s+(?<x>[\\d]*))(?=.*-y\\s+(?<y>[\\d]*))" +
            "(\\s*(-x\\s+([\\d]*)|-y\\s+([\\d]*))){2}\\s*$"),
    PATROL_UNIT("^\\s*patrol\\s+unit(?=.*-x1\\s+(?<x1>[\\d]*))(?=.*-x2\\s+(?<x2>[\\d]*))(?=.*-y1\\s+(?<y1>[\\d]*))" +
            "(?=.*-y2\\s+(?<y2>[\\d]*))(\\s*(-x1\\s+([\\d]*)|-x2\\s+([\\d]*)|-y1\\s+([\\d]*)|-y2\\s+([\\d]*))){4}\\s*$"),
    SET_UNIT_CONDITION("^\\s*set(?=.*-s\\s+(?<state>standing|defensive|offensive))(?=.*-x\\s+(?<x>[\\d]*))" +
            "(?=.*-y\\s+(?<y>[\\d]*))(\\s*(-s\\s+standing|defensive|offensive|-x\\s+([\\d]*)|-y\\s+([\\d]*))){3}\\s*$"),
    ATTACK_ENEMY("^\\s*attack(?=.*-x\\s+(?<x>[\\d]*))(?=.*-y\\s+(?<y>[\\d]*))" +
            "(\\s*(-x\\s+([\\d]*)|-y\\s+([\\d]*))){2}\\s*$"),
    AIR_ATTACK("^\\s*air\\s+attack(?=.*-x\\s+(?<x>[\\d]*))(?=.*-y\\s+(?<y>[\\d]*))" +
            "(\\s*(-x\\s+([\\d]*)|-y\\s+([\\d]*))){2}\\s*$"),
    POUR_OIL("^\\s*pour\\s+oil(?=.*-d\\s+(?<direction>\"[^\"]*\"|[^\\s\"]*))" +
            "(\\s*(-d\\s+(\"[^\"]*\"|[^\\s\"]*))){1}\\s*$"),
    DIG_TUNNEL("^\\s*dig\\s+tunnel(?=.*-x\\s+(?<x>[\\d]*))(?=.*-y\\s+(?<y>[\\d]*))" +
            "(\\s*(-x\\s+([\\d]*)|-y\\s+([\\d]*))){2}\\s*$"),
    BUILD_EQUIPMENT("^\\s*build(?=.*-q\\s+(?<equipmentName>\"[^\"]*\"|[^\\s\"]*))(?=.*-x\\s+(?<x>[\\d]*))" +
            "(?=.*-y\\s+(?<y>[\\d]*))(\\s*(-q\\s+(\"[^\"]*\"|[^\\s\"]*)|-x\\s+([\\d]*)|-y\\s+([\\d]*))){3}\\s*$"),
    SET_OUTPUT("^\\s*set\\s+output(?=.*-o\\s+(?<output>\"[^\"]*\"|[^\\s\"]*))" +
            "(\\s*(-q\\s+(\"[^\"]*\"|[^\\s\"]*))){1}\\s*$"),
    DISBAND_UNIT("^\\s*disband\\s+unit\\s*$"),
    SHOW_MAP("\\s*show\\s+map\\s+-x\\s+(?<xAsis>\\d+)\\s+-y\\s+(?<yAsis>\\d+)\\s*"),
    NEXT_TURN("\\s*next\\s+turn\\s+"),
    ENTER_TRADE_MENU("\\s*enter\\s+trade\\s+menu\\s*"),

    BACK("\\s*back\\s*");


    private final String regex;

    private GameCommands(String regex) {
        this.regex = regex;
    }


    public static Matcher getMatcher(String input, GameCommands gameCommands) {
        Matcher matcher = Pattern.compile(gameCommands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
