package org.view.CommandsEnum;

public enum ShopMessages {
    BUY_SUCCESSFULLY("Your purchase was successful"),
    CERTAINTY("Are you sure?"),
    SELL_SUCCESSFULLY("Your sell was successful"),
    WRONG_NAME_PRODUCT("Don't have any product with this name!"),
    LACK_OF_MONEY("You don't have enough gold!"),
    LACK_OF_PRODUCT("You don't have enough of the desired product!"),
    CANCEL("Canceled!");



    private final String message;

    ShopMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
