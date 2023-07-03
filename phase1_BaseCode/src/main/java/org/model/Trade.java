package org.model;

public class Trade {
    public static int lastId = 100;
    private final Empire tradeOwner;
    private final String tradeGetter;
    private final String resourceName;
    private final int resourceAmount;
    private final int price;
    private final String senderMessage;
    private String receiverMessage;
    private final int id;

    public Trade(String resourceName, int resourceAmount, int price, String senderMessage, Empire tradeOwner, String tradeGetter) {
        this.tradeOwner = tradeOwner;
        this.resourceName = resourceName;
        this.resourceAmount = resourceAmount;
        this.price = price;
        this.senderMessage = senderMessage;
        this.tradeGetter = tradeGetter;
        id = lastId;
        lastId++;
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

    public Empire getTradeOwner() {
        return tradeOwner;
    }

}
