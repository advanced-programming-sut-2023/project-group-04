package org.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.view.CommandsEnum.TradeCommands;

import java.util.regex.Matcher;

public class TradeMenu extends Application {
    public void run() {
        String input;
        Matcher matcher;

        while (true) {
            input = Menu.getScanner().nextLine();
            if ((matcher = TradeCommands.getMatcher(input, TradeCommands.SET_TRADE)) != null)
                System.out.println(Menu.getTradeController().setTrade(matcher).getMessage());
            else if (TradeCommands.getMatcher(input, TradeCommands.TRADE_LIST) != null)
                System.out.println(Menu.getTradeController().showTradeList());
            else if (TradeCommands.getMatcher(input, TradeCommands.TRADE_HISTORY) != null)
                System.out.println(Menu.getTradeController().showTradeHistory());
            else if ((matcher = TradeCommands.getMatcher(input, TradeCommands.ACCEPT_TRADE)) != null)
                System.out.println(Menu.getTradeController().acceptTrade(matcher).getMessage());
            else if (TradeCommands.getMatcher(input, TradeCommands.BACK) != null) {
                System.out.println("Back to game menu successfully!");
                return;
            }else
            System.out.println("Invalid command");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(TradeMenu.class.getResource("/fxml/tradeMenu.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }
}
