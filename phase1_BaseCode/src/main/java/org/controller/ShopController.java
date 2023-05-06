package org.controller;

import org.model.Empire;
import org.model.Game;
import org.model.ResourcesDictionary;
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
        int price = ResourcesDictionary.getDictionaryByName(itemsName).getPrice() * itemsAmount;
        currentEmpire.getResources().computeIfPresent(itemsName, (key, val) -> val + itemsAmount);
        currentEmpire.getResources().computeIfPresent("gold", (key, val) -> val - price);
        return ShopMessages.BUY_SUCCESSFULLY;
    }

    public ShopMessages sellThing(Matcher matcher) {
        String itemsName = removeQuotation(matcher.group("itemsName"));
        int itemsAmount = Integer.parseInt(matcher.group("itemsAmount"));
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        int price = ResourcesDictionary.getDictionaryByName(itemsName).getPrice() * itemsAmount;
        currentEmpire.getResources().computeIfPresent(itemsName, (key, val) -> val - itemsAmount);
        currentEmpire.getResources().computeIfPresent("gold", (key, val) -> val + price);
        return ShopMessages.SELL_SUCCESSFULLY;
    }

    private String removeQuotation(String buffer) {
        return buffer.replaceAll("\"", "");
    }


    public ShopMessages checkForBuy(Matcher matcher) {
        String itemsName = removeQuotation(matcher.group("itemsName"));
        int itemsAmount = Integer.parseInt(matcher.group("itemsAmount"));
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        if (ResourcesDictionary.getDictionaryByName(itemsName) == null)
            return ShopMessages.WRONG_NAME_PRODUCT;
        int price = ResourcesDictionary.getDictionaryByName(itemsName).getPrice() * itemsAmount;
        if (price > currentEmpire.getResources().get("gold"))
            return ShopMessages.LACK_OF_MONEY;
        return ShopMessages.CERTAINTY;
    }

    public ShopMessages checkForSell(Matcher matcher) {
        String itemsName = removeQuotation(matcher.group("itemsName"));
        int itemsAmount = Integer.parseInt(matcher.group("itemsAmount"));
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        if (ResourcesDictionary.getDictionaryByName(itemsName) == null)
            return ShopMessages.WRONG_NAME_PRODUCT;
        if (currentEmpire.getResources().get(itemsName) < itemsAmount)
            return ShopMessages.LACK_OF_PRODUCT;
        return ShopMessages.CERTAINTY;
    }
}
