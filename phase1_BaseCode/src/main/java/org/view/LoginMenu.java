package org.view;

import org.view.CommandsEnum.SignUpCommands;
import org.view.CommandsEnum.SignUpMessages;

import java.util.regex.Matcher;

public class LoginMenu {

    public void run() throws Exception {
        String result;
        Matcher matcher;
        while (true) {
            String input = Menu.getScanner().nextLine();
            if (SignUpCommands.getMatcher(input, SignUpCommands.I_DONT_HAVE_ACCOUNT) != null) {
                System.out.println("now you can create a new account!");
                new SignUpMenu().run();
            } else if ((matcher = SignUpCommands.getMatcher(input, SignUpCommands.LOGIN_USER)) != null) {
                result = Menu.getLoginController().signIn(matcher);
                System.out.println(result);
                if (result.contains(SignUpMessages.INCORRECT_PASSWORD.getMessage())) Menu.getLoginController().delay();
                else if (result.equals(SignUpMessages.LOGIN_SUCCESSFUL.getMessage())) new MainMenu().run();
            }
            else if ((matcher = SignUpCommands.getMatcher(input, SignUpCommands.FORGET_PASSWORD)) != null) {
                String username = matcher.group("username");
                forgetPassword(username);
            }
        }
    }

    private void forgetPassword(String username) {
        //TODO: handle random password
        String output = Menu.getLoginController().getSecurityQuestion(username);
        System.out.println(output);
        if (!output.equals(SignUpMessages.USER_DOES_NOT_EXIST.getMessage())) {
            String securityAnswer = Menu.getScanner().nextLine();
            if (SignUpCommands.getMatcher(securityAnswer,SignUpCommands.BACK) != null) return;
            output = Menu.getLoginController().checkSecurityAnswer(securityAnswer);
            System.out.println(output);
            if (!output.equals(SignUpMessages.ANSWER_DOES_NOT_MATCH.getMessage())) {
                while (true) {
                    String newPassword = Menu.getScanner().nextLine();
                    Matcher matcher = SignUpCommands.getMatcher(newPassword,SignUpCommands.SET_NEW_PASSWORD);
                    if (matcher != null) {
                        SignUpMessages signUpMessage = Menu.getLoginController().setNewPassword(matcher);
                        System.out.println(signUpMessage.getMessage());
                        if (signUpMessage.equals(SignUpMessages.PASSWORD_CHANGED)) return;
                    }else if (SignUpCommands.getMatcher(newPassword, SignUpCommands.BACK) != null) return;
                    else System.out.println("Invalid command");
                }
            }
        }
    }
}
