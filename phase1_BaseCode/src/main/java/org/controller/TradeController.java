package org.controller;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.model.Empire;
import org.model.Game;
import org.model.Trade;
import org.view.CommandsEnum.TradeMessages;

public class TradeController {

    public Button create;
    public Button previous;
    public ImageView mainBack;
    public ImageView back1;
    public ImageView back11;
    public GridPane playersList;
    public GridPane resourcesGrid;
    public HBox amount;
    public HBox amount1;
    public AnchorPane newTrade;
    public ImageView selectedResource;
    public Button submitted;
    public Button received;
    public GridPane submittedOffers;
    public GridPane receivedOffers;
    public ImageView accept1;
    public ImageView accept2;
    public ImageView accept3;
    public ImageView accept4;
    public ImageView reject1;
    public ImageView reject2;
    public ImageView reject3;
    public ImageView reject4;
    public Text status11;
    public Text status22;
    public Text status33;
    public Text status44;

    public TradeMessages setTrade(String resourceName, String senderMessage, int amount, int price, String tradeGetter) {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        if (empire.getAvailableResource(resourceName) < amount) return TradeMessages.LACK_OF_PRODUCT;
        Trade trade = new Trade(resourceName, amount, price, senderMessage, Game.getCurrentGame().getCurrentEmpire(), tradeGetter);
        empire.changeResourceAmount(resourceName, -1 * price);
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
        int id = 2;
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
            string.append("     Owner's Nickname : ").append(trade.getTradeOwner().getOwner().getNickname()).append("\n\n");
        }
        currentEmpire.getAllTrades().clear();
        return string.toString();
    }

    private String removeQuotation(String buffer) {
        return buffer.replaceAll("\"", "");
    }
}
