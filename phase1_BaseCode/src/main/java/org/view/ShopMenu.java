package org.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.view.CommandsEnum.ShopCommands;

import java.util.regex.Matcher;

public class ShopMenu extends Application {
    public static Stage stage;

    public void run() {
        String input;
        Matcher matcher;

        while (true) {
            input = Menu.getScanner().nextLine();

            if (ShopCommands.getMatcher(input, ShopCommands.SHOW_PRICE_LIST) != null)
                System.out.println(Menu.getShopController().showList());

//            else if ((matcher = ShopCommands.getMatcher(input, ShopCommands.BUY_ITEM)) != null) {
//                ShopMessages shopMessages = Menu.getShopController().checkForBuy(matcher);
//                System.out.println(shopMessages.getMessage());
//                if (shopMessages.equals(ShopMessages.CERTAINTY)) {
//                    input = Menu.getScanner().nextLine();
//                    if (input.matches("yes"))
//                        System.out.println(Menu.getShopController().buyThing(matcher).getMessage());
//                    else
//                        System.out.println(ShopMessages.CANCEL.getMessage());
//                }
//            } else if ((matcher = ShopCommands.getMatcher(input, ShopCommands.SELL_ITEM)) != null) {
//                ShopMessages shopMessages = Menu.getShopController().checkForSell(matcher);
//                System.out.println(shopMessages.getMessage());
//                if (shopMessages.equals(ShopMessages.CERTAINTY)) {
//                    input = Menu.getScanner().nextLine();
//                    if (input.matches("yes"))
//                        System.out.println(Menu.getShopController().sellThing(matcher).getMessage());
//                    else
//                        System.out.println(ShopMessages.CANCEL.getMessage());
//                }
//            }
            if (ShopCommands.getMatcher(input, ShopCommands.BACK) != null) {
                System.out.println("Back to game menu successfully!");
                return;
            } else
                System.out.println("Invalid command");
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        ShopMenu.stage = stage;
        AnchorPane anchorPane = FXMLLoader.load(ShopMenu.class.getResource("/fxml/shopMenu.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        Menu.getShopController().buyOperation();
        Menu.getShopController().sellOperation();
    }
}
