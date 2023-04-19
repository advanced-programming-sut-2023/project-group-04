package org.view;

import org.controller.*;

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LoginController loginController = new LoginController();
    private static final ProfileController profileController = new ProfileController();
    private static final EnvironmentController environmentController = new EnvironmentController();
    private static final MapController mapController = new MapController();
    private static final GameController gameController = new GameController();
    private static final ShopController shopController = new ShopController();
    private static final TradeController tradeController = new TradeController();

    public static Scanner getScanner() {
        return scanner;
    }
    public static LoginController getLoginController() {
        return loginController;
    }
    public static ProfileController getProfileController() {
        return profileController;
    }
    public static EnvironmentController getEnvironmentController() {
        return environmentController;
    }
    public static MapController getMapController() {
        return mapController;
    }
    public static GameController getGameController() {
        return gameController;
    }
    public static ShopController getShopController() {
        return shopController;
    }
    public static TradeController getTradeController() {
        return tradeController;
    }
}
