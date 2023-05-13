package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapCommands {
    CHANGE_VIEW("\\s*change\\s+map\\s+view\\s+(?=.*(?<up>up) (?<upNumber>\\d*))?" +
            "(?=.*(?<down>down) (?<downNumber>\\d*))?(?=.*(?<right>right) (?<rightNumber>\\d*))?" +
            "(?=.*(?<left>left) (?<leftNumber>\\d*))?(\\s+(up (\\d*))|(down (\\d*))|" +
            "(right (\\d*))|(left (\\d*))?){0,4}\\s*/"),
    SHOW_DETAILS("\\s*show\\s+details\\s+-x\\s+(?<xAsis>\\d+)\\s+-y(?<yAsis>\\d+)\\s*"),
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


