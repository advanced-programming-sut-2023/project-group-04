package org.view;

import org.view.CommandsEnum.ShopCommands;
import org.view.CommandsEnum.ShopMessages;

import java.util.regex.Matcher;

public class ShopMenu {
    public void run() {
        String input;
        Matcher matcher;

        while (true) {
            input = Menu.getScanner().nextLine();

            if (ShopCommands.getMatcher(input, ShopCommands.SHOW_PRICE_LIST) != null)
                System.out.println(Menu.getShopController().showList());

            else if ((matcher = ShopCommands.getMatcher(input, ShopCommands.BUY_ITEM)) != null) {
                ShopMessages shopMessages = Menu.getShopController().checkForBuy(matcher);
                System.out.println(shopMessages.getMessage());
                if (shopMessages.equals(ShopMessages.CERTAINTY)) {
                    input = Menu.getScanner().nextLine();
                    if (input.matches("yes"))
                        System.out.println(Menu.getShopController().buyThing(matcher));
                    else
                        System.out.println(ShopMessages.CANCEL.getMessage());
                }
            }
            else if ((matcher = ShopCommands.getMatcher(input, ShopCommands.SELL_ITEM)) != null) {
                ShopMessages shopMessages = Menu.getShopController().checkForSell(matcher);
                System.out.println(shopMessages.getMessage());
                if (shopMessages.equals(ShopMessages.CERTAINTY)) {
                    input = Menu.getScanner().nextLine();
                    if (input.matches("yes"))
                        System.out.println(Menu.getShopController().sellThing(matcher));
                    else
                        System.out.println(ShopMessages.CANCEL.getMessage());
                }
            }

            else if (ShopCommands.getMatcher(input, ShopCommands.BACK) != null)
                return;

            else
                System.out.println("Invalid command");
        }

    }
}
