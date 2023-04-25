package org.view;

import org.view.CommandsEnum.SignUpCommands;
import org.view.CommandsEnum.SignUpMessages;

import java.util.regex.Matcher;

public class SignUpMenu {

    public SignUpMenu() {
    }

    public void run() throws Exception {
        Matcher matcher;
        String input, result;
        while (true) {
            input = Menu.getScanner().nextLine();
            if ((matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT)) != null) {
                result = Menu.getLoginController().SignUp(matcher).getMessage();
                System.out.println(result);
                if (result.equals(SignUpMessages.REGISTRATION_SUCCESSFUL.getMessage()))
                    new LoginMenu().run();
            }
        }

    }
    // TODO: 4/21/2023 رجکس هارو از کیهان بگیر و جاشو عوض کن یادت باشه تریم نکردیا الان!!
}
