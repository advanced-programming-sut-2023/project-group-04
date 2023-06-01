package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapCommands {


    CHANGE_VIEW("\\s*change\\s+map\\s+view\\s+(?:(?<up>(up\\s*)(?<upNumber>\\d+)?(\\s*))?()|(?<down>(down\\s*)" +
            "(?<downNumber>\\d+)?(\\s*))?()|" +
            "(?<right>(right\\s*)(?<rightNumber>\\d+)?(\\s*))?()|(?<left>(left\\s*)(?<leftNumber>\\d+)?(\\s*))?()){1,}"),
    SHOW_DETAILS("\\s*show\\s+details\\s+-x\\s+(?<xAsis>\\d+)\\s+-y\\s+(?<yAsis>\\d+)\\s*"),
    BACK("\\s*back\\s*"),
    ;

    private String regex;
    private MapCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MapCommands mapCommands) {
        Matcher matcher = Pattern.compile(mapCommands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}


