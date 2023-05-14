package org.view;

import org.view.CommandsEnum.GameCommands;
import org.view.CommandsEnum.GameMessages;

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
                GameMessages output = Menu.getGameController().selectBuilding(matcher);
                System.out.println(output.getMessage());
                if (output.equals(GameMessages.ENTER_SHOP_MENU)) new ShopMenu().run();
            } else if (GameCommands.getMatcher(input, GameCommands.REPAIR) != null) {
                System.out.println(Menu.getGameController().repair().getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.CREATE_UNIT)) != null) {
                System.out.println(Menu.getGameController().createUnit(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.SELECT_UNIT)) != null) {
                System.out.println(Menu.getGameController().selectUnit(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.MOVE_UNIT)) != null) {
                System.out.println(Menu.getGameController().moveUnit(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.PATROL_UNIT)) != null) {
                System.out.println(Menu.getGameController().patrolUnit(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.SET_UNIT_CONDITION)) != null) {
                System.out.println(Menu.getGameController().setUnitMode(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.AIR_ATTACK)) != null) {
                System.out.println(Menu.getGameController().airAttack(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.ATTACK_ENEMY)) != null) {
                System.out.println(Menu.getGameController().Attack(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.POUR_OIL)) != null) {
                System.out.println(Menu.getGameController().pourOil(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.DIG_TUNNEL)) != null) {
                System.out.println(Menu.getGameController().digTunnel(matcher).getMessage());
            } else if (GameCommands.getMatcher(input, GameCommands.DISBAND_UNIT) != null) {
                System.out.println(Menu.getGameController().disbandUnit().getMessage());
            } else if (GameCommands.getMatcher(input, GameCommands.NEXT_TURN) != null) {
                Menu.getGameController().nextTurn();
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.SET_OUTPUT)) != null) {
                System.out.println(Menu.getGameController().setOutput(matcher).getMessage());
            } else if (input.matches("show resources")) {
                System.out.println(Menu.getGameController().showResources());
            } else if ((GameCommands.getMatcher(input, GameCommands.ENTER_TRADE_MENU)) != null) {
                System.out.println("Entered trade menu successfully!");
                new TradeMenu().run();
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.BUILD_EQUIPMENT)) != null) {
                System.out.println(Menu.getGameController().buildEquipment(matcher).getMessage());
            } else System.out.println("Invalid command!");
        }
    }
}
