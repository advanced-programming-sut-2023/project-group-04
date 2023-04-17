package view;

import controller.LoginController;
import model.Player;
import view.CommandsEnum.SignUpCommands;

public class MainMenu {
    private Player loggedInPlayer;
    public MainMenu(Player player) {
        this.loggedInPlayer = player;
    }

    public void run() throws Exception {
        String input;
        while (true) {
            input = Menu.getScanner().nextLine();
            if (SignUpCommands.getMatcher(input, SignUpCommands.LOGOUT) != null) {
                logout();
            }
        }
    }

    private void logout() throws Exception {
        loggedInPlayer = null;
        System.out.println("user logged out successfully!");
        new LoginMenu(new LoginController()).run();
    }
}
