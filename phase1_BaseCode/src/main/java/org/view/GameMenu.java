package org.view;

import org.view.CommandsEnum.MainMenuCommands;

import java.util.regex.Matcher;

public class GameMenu {
    public void run() {
        String input;
        Matcher matcher;
        while (true) {
            input = Menu.getScanner().nextLine();
            if ((matcher = MainMenuCommands.getMatcher(input, MainMenuCommands.SHOW_MAP)) != null) {
                System.out.println(Menu.getMapController().showMap(matcher));
                new MapMenu().run();
            }
        }
    }
}
