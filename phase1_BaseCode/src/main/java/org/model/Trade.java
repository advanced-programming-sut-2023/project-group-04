package org.model;

import java.util.ArrayList;

public class Trade {
    private static final ArrayList<Trade> allTrades = new ArrayList<>();
    public static int lastId = 100;
    private Empire tradeOwner;
    private String resourceName;
    private int resourceAmount;
    private int price;
    private final String senderMessage;
    private String receiverMessage;
    private int id;

    public Trade(String resourceName, int resourceAmount, int price, String senderMessage, Empire tradeOwner) {
        this.tradeOwner = tradeOwner;
        this.resourceName = resourceName;
        this.resourceAmount = resourceAmount;
        this.price = price;
        this.senderMessage = senderMessage;
        id = lastId;
        lastId++;
        allTrades.add(this);
    }

    public String getResourceName() {
        return resourceName;
    }

    public int getResourceAmount() {
        return resourceAmount;
    }

    public int getPrice() {
        return price;
    }

    public String getSenderMessage() {
        return senderMessage;
    }

    public String getReceiverMessage() {
        return receiverMessage;
    }

    public void setReceiverMessage(String receiverMessage) {
        this.receiverMessage = receiverMessage;
    }

    public int getId() {
        return id;
    }

    public void removeTrade() {
        allTrades.remove(this);
    }

    public Empire getTradeOwner() {
        return tradeOwner;
    }

    public static ArrayList<Trade> getAllTrades() {
        return allTrades;
    }

    public static Trade getTradeById(int id) {
        for (Trade trade : allTrades) {
            if (trade.getId() == id)
                return trade;
        }
        return null;
    }
}
