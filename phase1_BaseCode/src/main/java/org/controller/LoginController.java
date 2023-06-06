package org.controller;

import javafx.scene.input.MouseEvent;
import org.view.LoginMenu;
import org.view.SignUpMenu;

public class LoginController {

    public void runSignupMenu(MouseEvent mouseEvent) throws Exception {
        new SignUpMenu().start(LoginMenu.stage);
    }
}
