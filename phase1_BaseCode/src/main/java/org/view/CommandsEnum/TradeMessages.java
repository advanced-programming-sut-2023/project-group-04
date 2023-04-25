package org.view.CommandsEnum;

public enum TradeMessages {
    NOT_ENOUGH_AMOUNT("You don't have enough amount of this type"),
    ACCEPT_TRADE("Accepted"),
    SET_TRADE("Your trade set successfully"),
    EMPTY_LIST("This list is empty");


    private final String message;

    TradeMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
