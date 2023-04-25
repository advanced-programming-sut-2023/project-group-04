package org.view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeCommands {
    SET_TRADE("^[ \t]*trade(?=.*-t\\s*(?<type>\"[^\"]*\"|[^\\s\"]*))(?=.*-m\\s*(?<message>\"[^\"]*\"|[^\\s\"]*))" +
            "(?=.*-p\\s*(?<price>[\\d]*))(?=.*-a\\s*(?<amount>[\\d]*))" +
            "(\\s*(-t\\s*(\"[^\"]*\"|[^\\s\"]*)|-m\\s*(\"[^\"]*\"|[^\\s\"]*)|-p\\s*([\\d]*)|-a\\s*([\\d]*))){4}[ \t]*$"),
    TRADE_LIST("^[ \t]*trade\\s+list[ \t]*$"),
    ACCEPT_TRADE("^[ \t]*trade\\s+accept(?=.*-m\\s*(?<message>\"[^\"]*\"|[^\\s\"]*))(?=.*-i\\s*(?<id>[\\d]*))" +
            "(\\s*(-m\\s*(\"[^\"]*\"|[^\\s\"]*)|-i\\s*([\\d]*))){2}[ \t]*$"),
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
