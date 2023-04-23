package org.view;

public enum ShopMessages {
    BUY_SUCCESSFULLY("your purchase was successful"),
    CERTAINTY("Are you sure?"),
    SELL_SUCCESSFULLY("your sell was successful");


    private final String message;

    ShopMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
