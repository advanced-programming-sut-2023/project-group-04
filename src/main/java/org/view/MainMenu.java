package org.view;

import org.view.CommandsEnum.SignUpCommands;

public class MainMenu {
    public MainMenu() {

    }

    public void run() {
        String input;
        while (true) {
            input = Menu.getScanner().nextLine();
            if (SignUpCommands.getMatcher(input, SignUpCommands.LOGOUT) != null) {
                System.out.println("user logged out successfully!");
                return;
            }
        }
    }
}
