package org.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.view.CommandsEnum.SignUpCommands;
import org.view.CommandsEnum.SignUpMessages;

import java.util.regex.Matcher;

public class LoginMenu extends Application {

    public void run() throws Exception {
        boolean loginStatus = Menu.getLoginController().runProgram();
        String result;
        Matcher matcher;
        while (true) {
            if (loginStatus) new MainMenu().run();
            String input = Menu.getScanner().nextLine();
            if (SignUpCommands.getMatcher(input, SignUpCommands.I_DONT_HAVE_ACCOUNT) != null) {
                System.out.println("now you can create a new account!");
                new SignUpMenu().run();
            } else if ((matcher = SignUpCommands.getMatcher(input, SignUpCommands.LOGIN_USER)) != null) {
                result = Menu.getLoginController().signIn(matcher);
                System.out.println(result);
                if (result.contains(SignUpMessages.INCORRECT_PASSWORD.getMessage())) Menu.getLoginController().delay();
                else if (result.equals(SignUpMessages.LOGIN_SUCCESSFUL.getMessage())) new MainMenu().run();
            } else if ((matcher = SignUpCommands.getMatcher(input, SignUpCommands.FORGET_PASSWORD)) != null) {
                String username = matcher.group("username");
                forgetPassword(username);
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    private void forgetPassword(String username) {
        String output = Menu.getLoginController().getSecurityQuestion(username);
        System.out.println(output);
        if (!output.equals(SignUpMessages.USER_NOT_FOUND.getMessage())) {
            while (true) {
                String securityAnswer = Menu.getScanner().nextLine();
                if (SignUpCommands.getMatcher(securityAnswer, SignUpCommands.BACK) != null) {
                    System.out.println("You are in login menu now!");
                    return;
                }
                output = Menu.getLoginController().checkSecurityAnswer(securityAnswer.replaceAll("\"", ""));
                System.out.println(output);
                if (!output.contains(SignUpMessages.ANSWER_DOES_NOT_MATCH.getMessage())) {
                    while (true) {
                        String newPassword = Menu.getScanner().nextLine();
                        Matcher matcher = SignUpCommands.getMatcher(newPassword, SignUpCommands.SET_NEW_PASSWORD);
                        if (matcher != null) {
                            SignUpMessages signUpMessage = Menu.getLoginController().setNewPassword(matcher);
                            System.out.println(signUpMessage.getMessage());
                            if (signUpMessage.equals(SignUpMessages.PASSWORD_CHANGED)) return;
                        } else if (SignUpCommands.getMatcher(newPassword, SignUpCommands.BACK) != null) {
                            System.out.println("You are in login menu now!");
                            return;
                        } else System.out.println("Invalid command");
                    }
                }
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(LoginMenu.class.getResource("/fxml/loginMenu.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }
}
