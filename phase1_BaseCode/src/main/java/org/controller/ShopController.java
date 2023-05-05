package org.controller;

import org.model.Empire;
import org.model.Game;
import org.view.CommandsEnum.ShopMessages;

import java.util.regex.Matcher;

public class ShopController {
    public String showList() {
        StringBuilder string = new StringBuilder();
        return string.toString();
    }

    public ShopMessages buyThing(Matcher matcher) {
        String itemsName = removeQuotation(matcher.group("itemsName"));
        int itemsAmount = Integer.parseInt(matcher.group("itemsAmount"));
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();

        currentEmpire.getResources().computeIfPresent(itemsName,(key,val) -> val + itemsAmount);
        return ShopMessages.BUY_SUCCESSFULLY;

    }

    public ShopMessages sellThing(Matcher matcher) {
        String itemsName = removeQuotation(matcher.group("itemsName"));
        int itemsAmount = Integer.parseInt(matcher.group("itemsAmount"));
        return ShopMessages.SELL_SUCCESSFULLY;
    }

    private String removeQuotation(String buffer) {
        return buffer.replaceAll("\"", "");
    }
}
