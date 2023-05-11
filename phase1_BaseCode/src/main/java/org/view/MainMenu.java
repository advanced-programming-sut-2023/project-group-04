package org.view;

import org.view.CommandsEnum.GameMessages;
import org.view.CommandsEnum.MainMenuCommands;

import java.util.regex.Matcher;

public class MainMenu {
    public MainMenu() {
    }
    public void run() throws Exception {
        String input;
        Matcher matcher;
        while (true) {
            input = Menu.getScanner().nextLine();
            if (MainMenuCommands.getMatcher(input, MainMenuCommands.LOGOUT) != null) {
                System.out.println("user logged out successfully!");
                return;
            } else if ((MainMenuCommands.getMatcher(input, MainMenuCommands.ENTER_PROFILE_MENU)) != null) {
                System.out.println("you successfully entered \"profile menu!\"");
                new ProfileMenu().run();
            } else if ((matcher = MainMenuCommands.getMatcher(input, MainMenuCommands.START_GAME)) != null) {
                GameMessages gameMessage = Menu.getGameController().startGame(matcher);
                System.out.println(gameMessage.getMessage());
                if (gameMessage.equals(GameMessages.GAME_STARTED))
                    new GameMenu().run();
            } else if (MainMenuCommands.getMatcher(input, MainMenuCommands.ENTER_ENVIRONMENT_MENU) != null) {
                System.out.println("you successfully entered \"environment menu\"");
                new EnvironmentMenu().run();
            } else {
                System.out.println("invalid command!");
            }
        }
    }
}