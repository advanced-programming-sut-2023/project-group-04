package org.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeCommands {
    SET_TRADE(""),
    TRADE_LIST("^[ \t]*trade\\s+list[ \t]*$"),
    ACCEPT_TRADE(""),
    TRADE_HISTORY("^[ \t]*trade\\s+history[ \t]*$"),
    BACK("^[ \t]*back[ \t]*$");


    private final String regex;

    private TradeCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, TradeCommands tradeCommands) {
        Matcher matcher = Pattern.compile(tradeCommands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
