package org.view;

import org.view.CommandsEnum.GameMessages;
import org.view.CommandsEnum.MainMenuCommands;

import java.util.regex.Matcher;

public class GameMenu {
    public void run() {
        String input, result;
        Matcher matcher;
        while (true) {
            input = Menu.getScanner().nextLine();
            if ((matcher = MainMenuCommands.getMatcher(input, MainMenuCommands.SHOW_MAP)) != null) {
                result = Menu.getMapController().showMap(Integer.parseInt(matcher.group("xAsis")),
                        Integer.parseInt(matcher.group("yAsis")));
                System.out.println(result);
                if (!result.equals(GameMessages.INVALID_POSITION.getMessage())) {
                    System.out.println("You successfully entered \"Map Menu\"");
                    new MapMenu().run();
                }
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}
