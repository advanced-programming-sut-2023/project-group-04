package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {

    CREATE_NEW_MAP("^\\s*create\\s+new\\s+map(?=.*-i (?<mapId>\\d+))" +
            "(?=.*-s (?<size>\\d+))(\\s*(-i (\\d+))|(-s (\\d+))){2}\\s*$"),
    CUSTOM_EXITING_MAP("^\\s*custom\\s+map(?=.*-i (?<mapId>\\d+))(\\s*(-i (\\d+))){1}\\s*$"),
    SET_CELL_TEXTURE("^\\s*set\\s+texture(?=.*-x (?<x>\\d+))(?=.*-y (?<y>\\d+))(?=.*-t (?<type>\"[^\"]*\"|[^\\s\"]*))" +
            "(\\s+(-x (\\d+))|(-y (\\d+))|(-t (\"[^\"]*\"|[^\\s\"]*))){3}\\s*$"),
    SET_AREA_TEXTURE("^\\s*set\\s+texture(?=.*-x1 (?<x1>\\d+))(?=.*-y1 (?<y1>\\d+))(?=.*-x2 (?<x2>\\d+))" +
            "(?=.*-y2 (?<y2>\\d+))(?=.*-t (?<type>\"[^\"]*\"|[^\\s\"]*))" +
            "(\\s+(-x1 (\\d+))|(-y1 (\\d+))|(-x2 (\\d+))|(-y2 (\\d+))|(-t (\"[^\"]*\"|[^\\s\"]*))){5}\\s*$"),
    CLEAR_MAP_CELL("^\\s*clear(?=.*-x (?<x>\\d+))(?=.*-y (?<y>\\d+))" +
            "(\\s+(-x (\\d+))|(-y (\\d+))){2}\\s*$"),
    DROP_ROCK("^\\s*drop\\s+rock(?=.*-x (?<x>\\d+))(?=.*-y (?<y>\\d+))(?=.*-d (?<direction>\"[^\"]*\"|[^\\s\"]*))" +
            "(\\s+(-x (\\d+))|(-y (\\d+))|(-d (\"[^\"]*\"|[^\\s\"]*))){3}\\s*$"),
    DROP_TREE("^\\s*drop\\s+tree(?=.*-x (?<x>\\d+))(?=.*-y (?<y>\\d+))(?=.*-t (?<type>\"[^\"]*\"|[^\\s\"]*))" +
            "(\\s+(-x (\\d+))|(-y (\\d+))|(-t (\"[^\"]*\"|[^\\s\"]*))){3}\\s*$"),
    BACK("(\\s*)back(\\s*)");

    private final String regex;
    private MapMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MapMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
