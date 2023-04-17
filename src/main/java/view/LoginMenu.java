package view;

import controller.LoginController;
import model.Player;
import view.CommandsEnum.SignUpCommands;

import java.util.regex.Matcher;

public class LoginMenu {
    private LoginController loginController;
    public LoginMenu(LoginController loginController) {
        this.loginController = loginController;
    }

    public void run() throws Exception {
        String input, result;
        Matcher matcher;
        while (true) {
            input = Menu.getScanner().nextLine();
            if (SignUpCommands.getMatcher(input, SignUpCommands.I_DONT_HAVE_ACCOUNT) != null) {
                System.out.println("now you can create a new account!");
                loginController.getSignUpMenu().run();
            }
            else if ((matcher = SignUpCommands.getMatcher(input, SignUpCommands.LOGIN_USER)) != null) {
                result = loginUser(matcher);
                System.out.println(result);
                if (result.equals("user logged in successfully!"))
                    new MainMenu(Player.getPlayerByUsername(matcher.group("username"))).run();
            }
            else if ((matcher = SignUpCommands.getMatcher(input, SignUpCommands.FORGET_PASSWORD)) != null)
                System.out.println(forgetPassword(matcher));
        }
    }

    private String forgetPassword(Matcher matcher) {
        String username = matcher.group("username");
        switch (loginController.forgetMyPassword(username)) {
            case USER_DOES_NOT_EXIST:
                return "any user with this username doesn't exist!";
            case ANSWER_DOES_NOT_MATCH:
                return "security answer is incorrect!";
            case PASSWORD_LENGTH_WEAK:
                return "The length of the password must be at least <<6>>!";
            case PASSWORD_LOWERCASE_WEAK:
                return "The password must have at least one <<lowercase>> letter!";
            case PASSWORD_UPPERCASE_WEAK:
                return "The password must have at least one <<UPPERCASE>> letter!";
            case PASSWORD_NUMBER_WEAK:
                return "The password must have at least one <<digit>>!";
            case PASSWORD_SPECIFIC_CHAR_WEAK:
                return "The password must have at least one <<specific character>>!";
            case PASSWORD_CONFIRM_DOES_NOT_MATCH:
                return "password confirmation doesn't matches!";
            case PASSWORD_CHANGED:
                return "your password successfully changed!";
        }
        return null;
    }

    private String loginUser(Matcher matcher)  {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String status = matcher.group("status");
        switch (loginController.signIn(username, password, status)) {
            case USER_DOES_NOT_EXIST:
            case INCORRECT_PASSWORD:
                return "Username and password didn’t match!";
            case LOGIN_SUCCESSFUL:
                return "user logged in successfully!";
        }
        return null;
    }
}
