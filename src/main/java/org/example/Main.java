package org.example;

import controller.LoginController;
import model.ASCIIArtGenerator;
import view.LoginMenu;

public class Main {
    public static void main(String[] args) throws Exception {
        LoginMenu loginMenu = new LoginMenu(new LoginController());
        loginMenu.run();
    }
}