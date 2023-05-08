package org.controller;

import org.model.Game;
import org.model.ResourcesDictionary;
import org.model.Trade;
import org.view.CommandsEnum.TradeMessages;

import java.util.regex.Matcher;

public class TradeController {
    public TradeMessages setTrade(Matcher matcher) {
        String resourceName = removeQuotation(matcher.group("type"));
        String quantity = matcher.group("amount");
        String cost = matcher.group("price");
        String message = removeQuotation(matcher.group("message"));
        if (resourceName.isEmpty() || quantity.isEmpty() || cost.isEmpty() || message.isEmpty()) {
            return TradeMessages.EMPTY_FIELD;
        }
        int amount = Integer.parseInt(quantity);
        int price = Integer.parseInt(cost);
        if (ResourcesDictionary.getDictionaryByName(resourceName) == null)
            return TradeMessages.WRONG_NAME_PRODUCT;
        Trade trade = new Trade(resourceName, amount, price, message, Game.getCurrentGame().getCurrentEmpire());
        Trade.getAllTrades().add(trade);
        return TradeMessages.SET_TRADE;
    }

    public String showTradeList() {
        String list = "LIST OF TRADE :";
        for (Trade trade : Trade.getAllTrades()) {
            list += "\nResource name : " + trade.getResourceName();
            list += "   Amount : " + trade.getResourceAmount();
            list += "   Price : " + trade.getPrice();
            list += "   ID : << " + trade.getId() + " >>";
        }
        return list;
    }

    public TradeMessages acceptTrade(Matcher matcher) {
        String idCheck = matcher.group("id");
        String message = removeQuotation(matcher.group("message"));
        if (idCheck == null || message == null)
            return TradeMessages.EMPTY_FIELD;
        int id = Integer.parseInt(idCheck);
        Trade trade = Trade.getTradeById(id);
        if (trade == null)
            return TradeMessages.WRONG_ID;
        trade.removeTrade();
        return TradeMessages.ACCEPT_TRADE;
    }

    public String showTradeHistory() {
        return null;
    }

    private String removeQuotation(String buffer) {
        return buffer.replaceAll("\"", "");
    }
}
