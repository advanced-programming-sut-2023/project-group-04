package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    ENTER_SIZE("set map size -s (\\d+)"),
    SET_CELL_TEXTURE("(\\s*)settexture(\\s+)(-x(\\s+)(?<x>\\d+))(\\s+)(-y(\\s+)(?<y>\\d+))(\\s+)(-t(\\s+)(?<type>\\S+))(\\s*)"),
    SET_AREA_TEXTURE("(\\s*)settexture(\\s+)(-x1(\\s+)(?<x1>\\d+))(\\s+)(-y1(\\s+)(?<y1>\\d+))(\\s+)" +
                           "(-x2(\\s+)(?<x2>\\d+))(\\s+)(-y2(\\s+)(?<y2>\\d+))(\\s+)(-t(\\s+)(?<type>\\S+))(\\s*)"),
    CLEAR_MAP_CELL("(\\s*)clear(\\s+)(-x(\\s+)(?<x>\\d+))(\\s+)(-y(\\s+)(?<y>\\d+))(\\s*)"),
    DROP_ROCK("(\\s*)droprock(\\s+)(-x(\\s+)(?<x>\\d+))(\\s+)(-y(\\s+)(?<y>\\d+))(\\s+)(-d(\\s+)(?<direction>\\w))(\\s*)"),
    DROP_TREE("(\\s*)droptree(\\s+)(-x(\\s+)(?<x>\\d+))(\\s+)(-y(\\s+)(?<y>\\d+))(\\s+)(-t(\\s+)(<type>\\S+))(\\s*)");

    private String regex;
    private MapMenuCommands(String regex) {
        this.regex = regex;
    }

    public Matcher getMatcher(String input, MapMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
