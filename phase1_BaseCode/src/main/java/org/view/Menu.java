package org.view;

import javafx.stage.Stage;
import org.controller.*;
import org.view.gameView.ControlBar;

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SignupController signupController = new SignupController();
    private static final LoginController loginController = new LoginController();
    private static final ProfileController profileController = new ProfileController();
    private static final EnvironmentController environmentController = new EnvironmentController();
    private static final MapController mapController = new MapController();
    private static final GameController gameController = new GameController();
    private static final ShopController shopController = new ShopController();
    private static final TradeController tradeController = new TradeController();
    private static Stage stage;
    public static ControlBar bar;

    public static Scanner getScanner() {
        return scanner;
    }

    public static SignupController getSignupController() {
        return signupController;
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

    public static LoginController getLoginController() {
        return loginController;
    }

    public static void setStage(Stage stage) {
        Menu.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }
}
