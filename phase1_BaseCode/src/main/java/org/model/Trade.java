package org.model;

public class Trade {
    public static int lastId = 100;
    private final Empire tradeSender;
    private final Empire tradeGetter;
    private final String resourceName;
    private final int resourceAmount;
    private final int price;
    private final String senderMessage;
    private String receiverMessage;
    private final int id;

    public Trade(String resourceName, int resourceAmount, int price, String senderMessage, Empire tradeGetter, Empire tradeSender) {
        this.resourceName = resourceName;
        this.resourceAmount = resourceAmount;
        this.price = price;
        this.senderMessage = senderMessage;
        this.tradeGetter = tradeGetter;
        this.tradeSender = tradeSender;
        id = lastId;
        lastId++;
        this.tradeGetter.getAllTrades().add(this);
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

    public Empire getTradeSender() {
        return tradeSender;
    }

    public Empire getTradeGetter() {
        return tradeGetter;
    }
}
