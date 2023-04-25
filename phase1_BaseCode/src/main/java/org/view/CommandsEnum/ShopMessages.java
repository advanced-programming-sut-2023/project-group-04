package org.view.CommandsEnum;

public enum ShopMessages {
    BUY_SUCCESSFULLY("Your purchase was successful"),
    CERTAINTY("Are you sure?"),
    SELL_SUCCESSFULLY("Your sell was successful");


    private final String message;

    ShopMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
