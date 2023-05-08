package org.view.CommandsEnum;

public enum TradeMessages {
    NOT_ENOUGH_AMOUNT("You don't have enough amount of this type"),
    ACCEPT_TRADE("Accepted"),
    SET_TRADE("Your trade set successfully"),
    EMPTY_LIST("This list is empty"),
    WRONG_NAME_PRODUCT("Don't have any product with this name!"),
    LACK_OF_MONEY("You don't have enough gold!"),
    LACK_OF_PRODUCT("You don't have enough of the desired product!"),
    WRONG_ID("No trade with this id exist!"),
    EMPTY_FIELD("At least one field is empty");


    private final String message;

    TradeMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
