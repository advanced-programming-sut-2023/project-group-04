package org.controller;

import javafx.scene.Group;
import javafx.scene.text.Text;
import org.model.Empire;
import org.model.Game;
import org.model.Trade;
import org.view.CommandsEnum.TradeMessages;
import org.model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class TradeController {


    public TradeMessages setTrade(String resourceName, String senderMessage, int amount, int price, Empire tradeGetter) {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        if (empire.getAvailableResource(resourceName) < amount) return TradeMessages.LACK_OF_PRODUCT;
        Trade trade = new Trade(resourceName, amount, price, senderMessage, tradeGetter);
        Game.getCurrentGame().getCurrentEmpire().getAllTrades().add(trade);
        Game.getCurrentGame().addTrade(trade);
        return TradeMessages.SET_TRADE;
    }

    public String showTradeList() {
        StringBuilder list = new StringBuilder("LIST OF TRADE :");
        for (Trade trade : Game.getCurrentGame().getAllTrades()) {
            list.append("\nResource name : ").append(trade.getResourceName());
            list.append("   Amount : ").append(trade.getResourceAmount());
            list.append("   Price : ").append(trade.getPrice());
            list.append("   ID : << ").append(trade.getId()).append(" >>");
        }
        return list.toString();
    }

    public TradeMessages acceptTrade() {
        String receiverMessage;
        int id = 5;
        Trade trade = Game.getCurrentGame().getTradeById(id);
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        if (trade.getPrice() > empire.getAvailableResource("gold")) return TradeMessages.LACK_OF_MONEY;
        empire.changeResourceAmount("gold", trade.getPrice());
        Game.getCurrentGame().removeTrade(trade);
        return TradeMessages.ACCEPT_TRADE;
    }

    public void rejectTrade(int id) {
        Trade trade = Game.getCurrentGame().getTradeById(id);
        Game.getCurrentGame().removeTrade(trade);
    }

    public String showTradeHistory() {
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        if (currentEmpire == null)
            return "Your don't have any new trade request";
        StringBuilder string = new StringBuilder("Your Trade List : \n");
        for (Trade trade : currentEmpire.getAllTrades()) {
            string.append("Resource Name : ").append(trade.getResourceName());
            string.append("     Sender Message : ").append(trade.getSenderMessage());
            string.append("     Receiver Message : ").append(trade.getReceiverMessage());
//            string.append("     Owner's Nickname : ").append(trade.getTradeOwner().getOwner().getNickname()).append("\n\n");
        }
        currentEmpire.getAllTrades().clear();
        return string.toString();
    }

    public void setPlayersList(Text player1, Text player2, Text player3, Text player4, Text player5, Text player6) {
        Group text = new Group(player1, player2, player3, player4, player5, player6);
        int empireSize = Game.getCurrentGame().getAllEmpires().size();
        ArrayList<Empire> allEmpire = Game.getCurrentGame().getAllEmpires();
        HashMap<String, Empire> username = Game.getCurrentGame().getEmpires();
        ArrayList<Player> players = Player.getAllPlayers();
        for (int i = 0; i < players.size(); i++) {
            if (Game.getCurrentGame().getCurrentEmpire().getOwner() == players.get(i)) {
                continue;
            }
            ((Text) text.getChildren().get(i)).setText(players.get(i).getUsername());
        }
    }

    private String removeQuotation(String buffer) {
        return buffer.replaceAll("\"", "");
    }
}
