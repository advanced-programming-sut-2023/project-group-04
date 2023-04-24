package org.view;

public enum TradeMessages {
    NOT_ENOUGH_AMOUNT("you don't have enough amount of this type"),
    ACCEPT_TRADE("Accepted"),
    SET_TRADE("your trade set successfully"),
    EMPTY_LIST("this list is empty");


    private final String message;

    TradeMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
