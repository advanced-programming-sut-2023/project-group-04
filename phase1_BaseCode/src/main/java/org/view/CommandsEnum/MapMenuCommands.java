package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    CREATE_NEW_MAP("(\\s*)create(\\s+)new(\\s+)map(\\s+)(-i(\\s+)(?<mapId>\\d+))(\\s+)(-s(\\s+)(?<size>\\d+))(\\s*)"),
    CUSTOM_EXITING_MAP("(\\s*)custom(\\s+)map(\\s+)(-i(\\s+)(?<mapId>\\d+))(\\s*)"),
    SET_CELL_TEXTURE("(\\s*)set(\\s+)texture(\\s+)(-x(\\s+)(?<x>\\d+))(\\s+)(-y(\\s+)(?<y>\\d+))(\\s+)(-t(\\s+)(?<type>\\S+))(\\s*)"),
    SET_AREA_TEXTURE("(\\s*)set(\\s+)texture(\\s+)(-x1(\\s+)(?<x1>\\d+))(\\s+)(-y1(\\s+)(?<y1>\\d+))(\\s+)" +
                           "(-x2(\\s+)(?<x2>\\d+))(\\s+)(-y2(\\s+)(?<y2>\\d+))(\\s+)(-t(\\s+)(?<type>\\S+))(\\s*)"),
    CLEAR_MAP_CELL("(\\s*)clear(\\s+)(-x(\\s+)(?<x>\\d+))(\\s+)(-y(\\s+)(?<y>\\d+))(\\s*)"),
    DROP_ROCK("(\\s*)drop(\\s+)rock(\\s+)(-x(\\s+)(?<x>\\d+))(\\s+)(-y(\\s+)(?<y>\\d+))(\\s+)(-d(\\s+)(?<direction>\\w))(\\s*)"),
    DROP_TREE("(\\s*)drop(\\s+)tree(\\s+)(-x(\\s+)(?<x>\\d+))(\\s+)(-y(\\s+)(?<y>\\d+))(\\s+)(-t(\\s+)(<type>\\S+))(\\s*)"),
    BACK("(\\s*)back(\\s*)");

    private String regex;
    private MapMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MapMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
