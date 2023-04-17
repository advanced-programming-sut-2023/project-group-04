package view;

import controller.LoginController;
import view.CommandsEnum.SignUpCommands;

import java.util.regex.Matcher;

public class SignUpMenu {
    private LoginController loginController;

    public SignUpMenu(LoginController loginController) {
        this.loginController = loginController;
    }

    public void run() throws Exception {
        Matcher matcher;
        String input, result;
        while (true) {
            input = Menu.getScanner().nextLine();
            if ((matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT)) != null) {
                result = createAccount(matcher);
                System.out.println(result);
                if (result.equals("registration successful!"))
                    loginController.getLoginMenu().run();
            }
        }

    }

    private String createAccount(Matcher matcher) throws Exception {
        String username = removeQuotes(matcher.group("username"));
        String password = removeQuotes(matcher.group("password"));
        String passwordConfirmation = removeQuotes(matcher.group("passwordConfirm"));
        String Email = removeQuotes(matcher.group("Email"));
        String nickname = removeQuotes(matcher.group("nickName"));
        String slogan = removeQuotes(matcher.group("slogan"));
        if (matcher.group("sloganCommand") != null && slogan == null)
            return "the slogan is empty!";
        switch (loginController.SignUp(username, password, passwordConfirmation, Email, nickname, slogan)) {
            case EMPTY_FIELD:
                return "A field is empty!";
            case INCORRECT_USERNAME_FORMAT:
                return "username format is incorrect!";
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
            case EXISTENCE_EMAIL:
                return "A user with this Email is Already exists!";
            case INCORRECT_EMAIL_FORMAT:
                return "Email format is incorrect!";
            case REGISTRATION_FAILED:
                return "registration failed!";
            case INVALID_QUESTION_NUMBER:
                return "you must enter a number between 1 , 3 !";
            case ANSWER_CONFIRM_DOES_NOT_MATCH:
                return "the security answer confirmation doesn't matches!";
            default:
                return "registration successful!";

        }
    }

    private String removeQuotes(String input) {
        if (input != null) {
            if (input.charAt(0) == '"') {
                if (input.charAt(input.length() - 1) != ' ')
                    return input.substring(1, input.length() - 1);
                return input.substring(1, input.length() - 2);
            }
            else {
                if (input.charAt(input.length() - 1) != ' ')
                    return input;
                return input.substring(0, input.length() - 1);
            }
        }
        return null;
    }
}
