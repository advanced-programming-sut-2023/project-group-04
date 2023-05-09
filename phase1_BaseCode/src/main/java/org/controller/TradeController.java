package org.controller;

import org.model.Empire;
import org.model.Game;
import org.model.ResourcesDictionary;
import org.model.Trade;
import org.view.CommandsEnum.TradeMessages;

import java.util.regex.Matcher;

public class TradeController {
    public TradeMessages setTrade(Matcher matcher) {
        if (matcher.group("type") == null || matcher.group("amount") == null ||
                matcher.group("price") == null || matcher.group("message") == null)
            return TradeMessages.EMPTY_FIELD;
        String resourceName = removeQuotation(matcher.group("type"));
        String senderMessage = removeQuotation(matcher.group("message"));
        int amount = Integer.parseInt(matcher.group("amount"));
        int price = Integer.parseInt(matcher.group("price"));
        if (ResourcesDictionary.getDictionaryByName(resourceName) == null)
            return TradeMessages.WRONG_NAME_PRODUCT;
        Trade trade = new Trade(resourceName, amount, price, senderMessage, Game.getCurrentGame().getCurrentEmpire());
        Game.getCurrentGame().getCurrentEmpire().getAllTrades().add(trade);
        Trade.getAllTrades().add(trade);
        return TradeMessages.SET_TRADE;
    }

    public String showTradeList() {
        StringBuilder list = new StringBuilder("LIST OF TRADE :");
        for (Trade trade : Trade.getAllTrades()) {
            list.append("\nResource name : ").append(trade.getResourceName());
            list.append("   Amount : ").append(trade.getResourceAmount());
            list.append("   Price : ").append(trade.getPrice());
            list.append("   ID : << ").append(trade.getId()).append(" >>");
        }
        return list.toString();
    }

    public TradeMessages acceptTrade(Matcher matcher) {
        String receiverMessage;
        if (matcher.group("id") == null || matcher.group("message") == null)
            return TradeMessages.EMPTY_FIELD;
        receiverMessage = removeQuotation(matcher.group("message"));
        int id = Integer.parseInt(matcher.group("id"));
        Trade trade = Trade.getTradeById(id);
        if (trade == null)
            return TradeMessages.WRONG_ID;
        trade.setReceiverMessage(receiverMessage);
        trade.removeTrade();
        return TradeMessages.ACCEPT_TRADE;
    }

    public String showTradeHistory() {
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        if (currentEmpire == null)
            return "Your don't have any new trade request";
        StringBuilder string = new StringBuilder();
        for (Trade trade : currentEmpire.getAllTrades()) {
            string.append("Resource Name : ").append(trade.getResourceName());
            string.append("     Sender Message : ").append(trade.getSenderMessage());
            string.append("     Receiver Message : ").append(trade.getReceiverMessage());
            string.append("     Owner's Nickname : ").append(trade.getTradeOwner().getOwner().getNickname()).append("\n");
        }
        return string.toString();
    }

    private String removeQuotation(String buffer) {
        return buffer.replaceAll("\"", "");
    }
}
