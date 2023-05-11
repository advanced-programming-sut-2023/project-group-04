package org.view;

import org.view.CommandsEnum.GameCommands;
import org.view.CommandsEnum.GameMessages;
import org.view.CommandsEnum.MainMenuCommands;

import java.util.regex.Matcher;

public class GameMenu {
    public void run() {
        String input, result;
        Matcher matcher;
        while (true) {
            input = Menu.getScanner().nextLine();
            if ((matcher = GameCommands.getMatcher(input, GameCommands.SHOW_MAP)) != null) {
                result = Menu.getMapController().showMap(Integer.parseInt(matcher.group("xAsis")) - 1,
                        Integer.parseInt(matcher.group("yAsis")) - 1);
                System.out.println(result);
                if (!result.equals(GameMessages.INVALID_POSITION.getMessage())) {
                    System.out.println("You successfully entered \"Map Menu\"");
                    new MapMenu().run();
                }
            } else if (GameCommands.getMatcher(input, GameCommands.BACK) != null) {
                System.out.println("You are in main menu now!");
                return;
            } else if (GameCommands.getMatcher(input, GameCommands.SHOW_POPULARITY_FACTORS) != null) {
                System.out.println(Menu.getGameController().showPopularityFactors());
            } else if (GameCommands.getMatcher(input, GameCommands.SHOW_POPULARITY) != null) {
                System.out.println(Menu.getGameController().showPopularity());
            } else if (GameCommands.getMatcher(input, GameCommands.SHOW_FOODlIST) != null) {
                System.out.println(Menu.getGameController().showFoodList());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.CHANGE_FOOD_RATE)) != null) {
                System.out.println(Menu.getGameController().changeFoodRate(matcher).getMessage());
            } else if (GameCommands.getMatcher(input, GameCommands.SHOW_FOOD_RATE) != null) {
                System.out.println(Menu.getGameController().showFoodRate());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.CHANGE_TAX_RATE)) != null) {
                System.out.println(Menu.getGameController().changeTaxRate(matcher).getMessage());
            } else if (GameCommands.getMatcher(input, GameCommands.SHOW_TAX_RATE) != null) {
                System.out.println(Menu.getGameController().showTaxRate());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.CHANGE_FEAR_RATE)) != null) {
                System.out.println(Menu.getGameController().changeFearRate(matcher).getMessage());
            } else if (GameCommands.getMatcher(input, GameCommands.SHOW_FEAR_RATE) != null) {
                System.out.println(Menu.getGameController().showFearRate());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.DROP_BUILDING)) != null) {
                System.out.println(Menu.getGameController().dropBuilding(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.SELECT_BUILDING)) != null) {
                System.out.println(Menu.getGameController().selectBuilding(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.REPAIR)) != null) {
                System.out.println(Menu.getGameController().repair(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.CREATE_UNIT)) != null) {
                System.out.println(Menu.getGameController().createUnit(matcher));
            }else {
                System.out.println("Invalid command!");
            }
        }
    }
}
