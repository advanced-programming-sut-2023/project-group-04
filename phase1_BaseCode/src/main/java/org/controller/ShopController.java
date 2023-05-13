package org.controller;

import org.model.Empire;
import org.model.Game;
import org.model.ResourcesDictionary;
import org.view.CommandsEnum.ShopMessages;

import java.util.regex.Matcher;

public class ShopController {
    public String showList() {
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        String list = "YOUR RESOURCES:\n";
        for (ResourcesDictionary resourcesDictionary : ResourcesDictionary.values()) {
            list += "resource name : " + resourcesDictionary.getName();
            list += "\namount : " + currentEmpire.getResources().get(resourcesDictionary.getName());
            list += "\nbuy price : " + resourcesDictionary.getPrice();
            list += "\nsell price : " + resourcesDictionary.getPrice() / 2;
        }
        return list;
    }

    public ShopMessages buyThing(Matcher matcher) {
        String itemsName = removeQuotation(matcher.group("itemsName"));
        int itemsAmount = Integer.parseInt(matcher.group("itemsAmount"));
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        int price = ResourcesDictionary.getDictionaryByName(itemsName).getPrice() * itemsAmount;
        currentEmpire.changeResourceAmount(itemsName, itemsAmount);
        currentEmpire.changeResourceAmount("gold", -1 * price);
        return ShopMessages.BUY_SUCCESSFULLY;
    }

    public ShopMessages sellThing(Matcher matcher) {
        String itemsName = removeQuotation(matcher.group("itemsName"));
        int itemsAmount = Integer.parseInt(matcher.group("itemsAmount"));
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        int price = ResourcesDictionary.getDictionaryByName(itemsName).getPrice() * itemsAmount;
        currentEmpire.changeResourceAmount(itemsName, -1 * itemsAmount);
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
        if (currentEmpire.getAvailableResource(itemsName) < itemsAmount)
            return ShopMessages.LACK_OF_PRODUCT;
        return ShopMessages.CERTAINTY;
    }
}
