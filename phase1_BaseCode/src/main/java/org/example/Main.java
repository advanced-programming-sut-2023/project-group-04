package org.example;

import org.model.Player;
import org.view.LoginMenu;

public class Main {
    public static void main(String[] args) throws Exception {
        Player.recoveryPlayers();
        new LoginMenu().run();
    }
}

