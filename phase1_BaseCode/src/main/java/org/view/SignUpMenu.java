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
                result = Menu.getLoginController().SignUp(matcher);
                signUp(result);
            }
        }

    }

    private void signUp(String result) throws Exception {
        if (!result.equals(SignUpMessages.REGISTRATION_SUCCESSFUL.getMessage()))
            System.out.println(result);
        if ((SignUpCommands.getMatcher(result, SignUpCommands.RANDOM_SLOGAN_AND_PASSWORD)) != null) {
            enterPassword();
            pickQuestion(result);
        }
        else if ((SignUpCommands.getMatcher(result, SignUpCommands.RANDOM_PASSWORD)) != null) {
            enterPassword();
            pickQuestion(result);
        }
        else if (result.contains("Your random slogan is")) {
            pickQuestion(result);
        }
        pickQuestion(result);
    }

    private void pickQuestion(String result) throws Exception {
        System.out.println(Menu.getLoginController().showSecurityQuestions());
        Matcher pickQuestionMatcher = SignUpCommands
                .getMatcher(Menu.getScanner().nextLine(), SignUpCommands.PICK_SECURITY_QUESTION);
        if (pickQuestionMatcher != null) {
            result = Menu.getLoginController().setSecurityQuestion(pickQuestionMatcher).getMessage();
            System.out.println(result);
            if (result.equals(SignUpMessages.SET_QUESTION_SUCCESSFUL.getMessage()))
                System.out.println(Menu.getLoginController().finishRegister(pickQuestionMatcher).getMessage());
            new LoginMenu().run();
        }
        else System.out.println("invalid command!");
    }

    private void enterPassword() {
        String password, result;
        System.out.print("Please re-enter your password here: ");
        while (true) {
            password = Menu.getScanner().nextLine();
            result = Menu.getLoginController().checkRandomPassword(password);
            System.out.println(result);
            if (result.equals("password confirmed!"))
                return;
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
