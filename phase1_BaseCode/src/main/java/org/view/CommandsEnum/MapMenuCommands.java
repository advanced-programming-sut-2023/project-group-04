package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {

    CREATE_NEW_MAP("^\\s*create\\s+new\\s+map(?=.*-n (?<mapName>\"[^\"]*\"|[^\\s\"]*))" +
            "(?=.*-s (?<size>\\d+))(\\s*(-n (\\d+))|(-s (\"[^\"]*\"|[^\\s\"]*))){2}\\s*$"),
    CUSTOM_EXITING_MAP("^\\s*custom\\s+map(?=.*-n (?<mapName>\"[^\"]*\"|[^\\s\"]*))(\\s*(-n (\"[^\"]*\"|[^\\s\"]*))){1}\\s*$"),
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
    SET_HEADQUARTER("^\\s*set\\s+headQuarter(?=.*-x (?<x>\\d+))(?=.*-y (?<y>\\d+))(?=.*-p (?<color>\"[^\"]*\"|[^\\s\"]*))" +
            "(\\s+(-x (\\d+))|(-y (\\d+))|(-p (\"[^\"]*\"|[^\\s\"]*))){3}\\s*$"),
    DROP_BUILDING("^\\s*drop\\s+building(?=.*-p (?<color>\"[^\"]*\"|[^\\s\"]*))" +
            "(?=.*-t\\s+(?<type>\"[^\"]*\"|[^\\s\"]*))(?=.*-x\\s+(?<x>[\\d]*))(?=.*-y\\s+(?<y>[\\d]*))" +
            "(\\s*(-t\\s+(\"[^\"]*\"|[^\\s\"]*)|-x\\s+([\\d]*)|-y\\s+([\\d]*)|-p\\s+(\"[^\"]*\"|[^\\s\"]*))){4}\\s*$"),
    DROP_UNIT("^\\s*drop\\s+unit(?=.*-p (?<color>\"[^\"]*\"|[^\\s\"]*))(?=.*-x\\s+(?<x>[\\d]*))(?=.*-y\\s+(?<y>[\\d]*))" +
            "(?=.*-t\\s+(?<type>\"[^\"]*\"|[^\\s\"]*))(?=.*-c\\s+(?<x>[\\d]*))" +
            "(\\s*(-t\\s+(\"[^\"]*\"|[^\\s\"]*)|-c\\s+([\\d]*)|-x\\s+([\\d]*)|-y\\s+([\\d]*)|-p\\s+(\"[^\"]*\"|[^\\s\"]*))){5}\\s*$"),
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
