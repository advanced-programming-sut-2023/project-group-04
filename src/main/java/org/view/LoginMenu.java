package org.view;

import org.view.CommandsEnum.SignUpCommands;
import org.view.CommandsEnum.SignUpMessages;

import java.util.regex.Matcher;

public class LoginMenu {
    public LoginMenu() {
    }

    public void run() throws Exception {
        String input, result;
        Matcher matcher;
        while (true) {
            input = Menu.getScanner().nextLine();
            if (SignUpCommands.getMatcher(input, SignUpCommands.I_DONT_HAVE_ACCOUNT) != null) {
                System.out.println("now you can create a new account!");
                new SignUpMenu().run();
            }
            else if ((matcher = SignUpCommands.getMatcher(input, SignUpCommands.LOGIN_USER)) != null) {
                result = Menu.getLoginController().signIn(matcher).getMessage();
                System.out.println(result);
                if (result.equals(SignUpMessages.LOGIN_SUCCESSFUL.getMessage()))
                    new MainMenu().run();
            }
            else if ((matcher = SignUpCommands.getMatcher(input, SignUpCommands.FORGET_PASSWORD)) != null)
                System.out.println(Menu.getLoginController().forgetPassword(matcher).getMessage());
        }
    }

}
