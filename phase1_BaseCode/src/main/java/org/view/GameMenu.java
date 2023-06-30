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
            if ((matcher = GameCommands.getMatcher(input, GameCommands.DROP_BUILDING)) != null) {
                System.out.println(Menu.getGameController().dropBuilding(0, 0, "h", true).getMessage());//TODO
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.SELECT_BUILDING)) != null) {
                GameMessages output = Menu.getGameController().selectBuilding(0, 0);//TODO
                System.out.println(output.getMessage());
                if (output.equals(GameMessages.ENTER_SHOP_MENU)) new ShopMenu().run();
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.CREATE_UNIT)) != null) {
                System.out.println(Menu.getGameController().createUnit(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.SELECT_UNIT)) != null) {
                System.out.println(Menu.getGameController().selectUnit(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.MOVE_UNIT)) != null) {
                System.out.println(Menu.getGameController().moveUnit(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.AIR_ATTACK)) != null) {
                System.out.println(Menu.getGameController().airAttack(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.ATTACK_ENEMY)) != null) {
                System.out.println(Menu.getGameController().Attack(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.POUR_OIL)) != null) {  //akhar
                System.out.println(Menu.getGameController().pourOil(matcher).getMessage());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.DIG_TUNNEL)) != null) { //akhar
                System.out.println(Menu.getGameController().digTunnel(matcher).getMessage());
            } else if (GameCommands.getMatcher(input, GameCommands.DISBAND_UNIT) != null) {
                System.out.println(Menu.getGameController().disbandUnit().getMessage());
            } else if (GameCommands.getMatcher(input, GameCommands.NEXT_TURN) != null) { //space key
                System.out.println(Menu.getGameController().nextTurn());
            } else if ((matcher = GameCommands.getMatcher(input, GameCommands.SET_OUTPUT)) != null) { //akhar
                System.out.println(Menu.getGameController().setOutput(matcher).getMessage());
            } else if (input.matches("show resources")) {
                System.out.println(Menu.getGameController().showResources());
            } else if ((GameCommands.getMatcher(input, GameCommands.ENTER_TRADE_MENU)) != null) {
                System.out.println("Entered trade menu successfully!");
                new TradeMenu().run();
            }
        }
    }
}
