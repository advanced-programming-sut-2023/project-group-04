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
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}
