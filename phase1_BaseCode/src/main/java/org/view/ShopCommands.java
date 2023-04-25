package org.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShopCommands {
    SHOW_PRICE_LIST("^[ \t]*show\\s+price\\s+list[ \t]*$"),
    BUY_ITEM("^[ \t]*buy(?=.*-i\\s+(?<itemsName>\"[^\"]*\"|[^\\s\"]*))" +
            "(?=.*-a\\s+(?<itemsAmount>[\\d]*))(\\s*(-i\\s+(\"[^\"]*\"|[^\\s\"]*)|-a\\s+([\\d]*))){2}[ \t]*$"),
    SELL_ITEM("^[ \t]*sell(?=.*-i\\s+(?<itemsName>\"[^\"]*\"|[^\\s\"]*))" +
            "(?=.*-a\\s+(?<itemsAmount>[\\d]*))(\\s*(-i\\s+(\"[^\"]*\"|[^\\s\"]*)|-a\\s+([\\d]*))){2}[ \t]*$"),
    BACK("^[ \t]*back[ \t]*$");


    private final String regex;

    private ShopCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ShopCommands shopCommands) {
        Matcher matcher = Pattern.compile(shopCommands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
