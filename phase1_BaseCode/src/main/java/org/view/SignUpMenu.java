package org.view;

import org.view.CommandsEnum.SignUpCommands;
import org.view.CommandsEnum.SignUpMessages;

import java.util.regex.Matcher;

public class SignUpMenu {

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

    public boolean acceptSuggestedUsername(String suggestedUsername) {
        System.out.println("this username already exists!\n" +
                "You can register with this username: \"" + suggestedUsername + "\"\n" +
                "type <YES/NO>");
        while (true) {
            String input = Menu.getScanner().nextLine();
            if (input.equals("YES")) return true;
            else if (input.equals("NO")) return false;
            else System.out.println("Invalid command!");
        }
    }
    // TODO: 4/21/2023 رجکس هارو از کیهان بگیر و جاشو عوض کن یادت باشه تریم نکردیا الان!!
}
