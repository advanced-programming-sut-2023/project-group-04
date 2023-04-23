package org.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShopCommands {
    SHOW_PRICE_LIST("^[ \t]*show(\\s+)price(\\s+)list[ \t]*$"),
    BUY_ITEM("^[ \t]*buy\\s+(?=.*(-i\\s+(?<name>[\\S]+)))(?=.*(-a\\s+(?<amount>[\\d]+)))"),
    SELL_ITEM("^[ \\t]*sell\\s+(?=.*(-i\\s+(?<name>[\\S]+)))(?=.*(-a\\s+(?<amount>[\\d]+)))"),
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
