package org.view;

import org.view.CommandsEnum.TradeCommands;

import java.util.regex.Matcher;

public class TradeMenu {
    public void run() {
        String input;
        Matcher matcher;

        while (true) {
            input = Menu.getScanner().nextLine();

            if ((matcher = TradeCommands.getMatcher(input,TradeCommands.SET_TRADE)) != null)
                System.out.println(Menu.getTradeController().setTrade(matcher));
            else if (TradeCommands.getMatcher(input,TradeCommands.TRADE_LIST) != null)
                System.out.println(Menu.getTradeController().showTradeList());
            else if (TradeCommands.getMatcher(input,TradeCommands.TRADE_HISTORY) != null)
                System.out.println(Menu.getTradeController().showTradeHistory());
            else if ((matcher = TradeCommands.getMatcher(input , TradeCommands.ACCEPT_TRADE)) != null)
                System.out.println(Menu.getTradeController().acceptTrade(matcher));
            else
                System.out.println("Invalid command");

        }

    }
}
