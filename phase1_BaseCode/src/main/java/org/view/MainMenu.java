package org.view;

import org.view.CommandsEnum.MainMenuCommands;

import java.util.regex.Matcher;

public class MainMenu {
    public MainMenu() {

    }

    public void run() {
        String input;
        Matcher matcher;
        while (true) {
            input = Menu.getScanner().nextLine();
            if (MainMenuCommands.getMatcher(input, MainMenuCommands.LOGOUT) != null) {
                System.out.println("user logged out successfully!");
                // TODO: 5/4/2023 save users useeeeeeeeeeeeeeeeeeeeeeeeeeeeef!!!
                return;
            } else if ((MainMenuCommands.getMatcher(input, MainMenuCommands.ENTER_PROFILE_MENU)) != null) {
                System.out.println("you successfully entered \"profile menu!\"");
                new ProfileMenu().run();
            } else if ((MainMenuCommands.getMatcher(input, MainMenuCommands.START_GAME)) != null) {
                System.out.println("the game has started!");
                new GameMenu().run();
            }
        }
    }
}
