package controller;

import model.ASCIIArtGenerator;
import model.Player;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import view.CommandsEnum.SecurityQuestion;
import view.CommandsEnum.SignUpCommands;
import view.CommandsEnum.SignUpMessages;
import view.LoginMenu;
import view.Menu;
import view.SignUpMenu;

import java.util.ArrayList;
import java.util.regex.Matcher;

import static view.CommandsEnum.SignUpMessages.*;


public class LoginController {
    private SignUpMenu signUpMenu;
    private LoginMenu loginMenu;

    public LoginController() {
        this.signUpMenu = new SignUpMenu(this);
        this.loginMenu = new LoginMenu(this);
    }

    public void run() throws Exception {
        loginMenu.run();
    }

    public SignUpMessages SignUp(String username, String password, String passwordConfirmation,
                                 String email, String nickname, String slogan) throws Exception {
        switch (checkSignUpErrors(username, password, passwordConfirmation, email, nickname)) {
            case WITHOUT_ERROR:
                break;
            default:
                return checkSignUpErrors(username, password, passwordConfirmation, email, nickname);
        }
            if (password.equals("random")) {
                password = generateRandomPassword();
                if (slogan.equals("random")) {
                    slogan = giveRandomSlogan();
                    showSloganAndPassword(slogan, password);
                    while (!Menu.getScanner().nextLine().equals(password)) {
                        System.out.println("confirmation failed!\nplease enter password correctly!: ");
                    }
                } else {
                    System.out.print("Your random password is: " + password + "\n" +
                            "Please re-enter your password here: ");
                    while (!Menu.getScanner().nextLine().equals(password)) {
                        System.out.println("confirmation failed!\nplease enter your password correctly!: ");
                    }
                }
            } else {
                if (slogan != null)
                    if (slogan.equals("random")) {
                        slogan = giveRandomSlogan();
                        System.out.println("Your random slogan is \"" + slogan + "\"");
                    }
            }
            showSecurityQuestions();
            Matcher matcher = SignUpCommands.getMatcher(Menu.getScanner().nextLine(), SignUpCommands.PICK_SECURITY_QUESTION);
            if (setSecurityQuestion(matcher).equals(SET_QUESTION_SUCCESSFUL)) {
                String securityQuestion, securityAnswer = matcher.group("answer");
                securityQuestion = giveSecurityQuestionWithNumber(matcher);
                if (generateCaptcha().equals(CAPTCHA_CORRECT)) {
                    Player player = new Player(username, password, nickname, email, securityQuestion, securityAnswer, slogan);
                    player.addPlayer(player);
                }
            } else {
                return setSecurityQuestion(matcher);
            }
            return REGISTRATION_SUCCESSFUL;
    }

    private String giveSecurityQuestionWithNumber(Matcher matcher) {
        switch (matcher.group("questionNumber")) {
            case "1":
                return SecurityQuestion.getQuestion(SecurityQuestion.PET);
            case "2":
                return  SecurityQuestion.getQuestion(SecurityQuestion.TEACHER);
            default:
                return SecurityQuestion.getQuestion(SecurityQuestion.CAR);
        }
    }

    private void showSloganAndPassword(String slogan, String password) {
        System.out.print("Your slogan is \"" + slogan + "\"\n" +
                "Your random password is: " + password + "\n" +
                "Please re-enter your password here: ");
    }

    private void showSecurityQuestions() {
        System.out.println("Pick your security question: 1. " + SecurityQuestion.getQuestion(SecurityQuestion.PET)
                + "\n2. " + SecurityQuestion.getQuestion(SecurityQuestion.TEACHER)
                + "\n3. " + SecurityQuestion.getQuestion(SecurityQuestion.CAR));
    }

    private SignUpMessages checkSignUpErrors(String username, String password,
                                             String passwordConfirmation, String email, String nickname) {
        if (username == null || password == null || email == null || nickname == null)
            return EMPTY_FIELD;
        else if (!isUsernameFormatCorrect(username))
            return INCORRECT_USERNAME_FORMAT;
        else if (Player.getPlayerByUsername(username) != null) {
            if (acceptSuggestedUsername(suggestNewUsername(username)))
                username = suggestNewUsername(username);
            else
                return REGISTRATION_FAILED;
        } else if (checkPassword(password).equals(PASSWORD_STRONG)) {
        } else
            return checkPassword(password);
        if (!password.equals("random") && !password.equals(passwordConfirmation))
            return PASSWORD_CONFIRM_DOES_NOT_MATCH;
        else if (checkEmailExistence(email))
            return EXISTENCE_EMAIL;
        else if (!email.matches("[a-zA-Z0-9_.]+@[a-zA-Z0-9_.]+\\.[a-zA-Z0-9_.]+"))
            return INCORRECT_EMAIL_FORMAT;
        return WITHOUT_ERROR;
    }

    private SignUpMessages generateCaptcha() throws Exception {
        String input = null;
        ASCIIArtGenerator asciiArtGenerator = new ASCIIArtGenerator();
        int randomCaptchaNumber = (int) (Math.random() * (99999999 - 1000 + 1) + 1000);
        asciiArtGenerator.printTextArt(String.valueOf(randomCaptchaNumber), ASCIIArtGenerator.ART_SIZE_SMALL);
        System.out.println("enter the Captcha code / if it is not readable type : <<change captcha>>");
        input = Menu.getScanner().nextLine();
        if (input.equals("change captcha"))
            generateCaptcha();
        else {
            if (input.equals(String.valueOf(randomCaptchaNumber)))
                return CAPTCHA_CORRECT;
            else {
                System.out.println("captcha code is wrong!");
                generateCaptcha();
            }
        }
        return CAPTCHA_CORRECT;
    }


    private SignUpMessages setSecurityQuestion(Matcher matcher) {
        int questionNumber = Integer.parseInt(matcher.group("questionNumber"));
        String answer = matcher.group("answer");
        String answerConfirm = matcher.group("answer");
        if (questionNumber > 3)
            return INVALID_QUESTION_NUMBER;
        else if (!answer.equals(answerConfirm))
            return ANSWER_CONFIRM_DOES_NOT_MATCH;
        else {
            return SET_QUESTION_SUCCESSFUL;
        }
    }

    private String giveRandomSlogan() {
        ArrayList<String> slogans = new ArrayList<>();
        slogans.add("I shall have my revenge, in this life or the next!");
        slogans.add("I will not lose I either win or learn!");
        slogans.add("You are too weak to play with me:)!");
        slogans.add("If I lose, I will lose in such a way that you doubt that you will win!");
        int number = (int) (Math.random() * (4));
        return slogans.get(number);
    }

    private String generateRandomPassword() {
        CharacterRule lowerCase = new CharacterRule(EnglishCharacterData.LowerCase, 2);
        CharacterRule uppercase = new CharacterRule(EnglishCharacterData.UpperCase, 2);
        CharacterRule digit = new CharacterRule(EnglishCharacterData.Digit, 2);
        CharacterRule specificCharacter = new CharacterRule(EnglishCharacterData.Special, 2);
        return new PasswordGenerator().generatePassword(8, specificCharacter, lowerCase, uppercase, digit);
    }

    private boolean checkEmailExistence(String email) {
        return Player.getPlayerByEmail(email) != null;
    }

    private SignUpMessages checkPassword(String password) {
        if (password.equals("random"))
            return PASSWORD_STRONG;
        else if (password.length() < 6)
            return PASSWORD_LENGTH_WEAK;
        else if (!password.matches(".*[A-Z].*"))
            return PASSWORD_UPPERCASE_WEAK;
        else if (!password.matches(".*[a-z].*"))
            return PASSWORD_LOWERCASE_WEAK;
        else if (!password.matches(".*[0-9].*"))
            return PASSWORD_NUMBER_WEAK;
        else if (!password.matches(".*[^a-zA-Z0-9].*"))
            return PASSWORD_SPECIFIC_CHAR_WEAK;
        return PASSWORD_STRONG;
    }

    private String suggestNewUsername(String username) {
        int counter = 11;
        while (Player.getPlayerByUsername(username) != null) {
            username += counter;
            counter++;
        }
        return username;
    }

    private boolean acceptSuggestedUsername(String suggestedUsername) {
        System.out.println("this username already exists!\nYou can register with this username: \"" + suggestedUsername + "\"\n" +
                "type <YES> to accept!");
        return Menu.getScanner().nextLine().equals("YES");
    }

    private boolean isUsernameFormatCorrect(String username) {
        return username.matches("[a-zA-Z\\s\\_0-9]+");
    }


    public SignUpMessages signIn(String username, String password, String status) {
        if (Player.getPlayerByUsername(username) == null)
            return USER_DOES_NOT_EXIST;
        if (!Player.getPlayerByUsername(username).isPasswordCorrect(password)) {
            Player.getPlayerByUsername(username).increaseNumberOfAttempts();
            System.out.println("Please try again after <<"
                    + Player.getPlayerByUsername(username).getNumberOfAttempts() * 5 + ">> seconds!");
            return INCORRECT_PASSWORD;
        }
        //Stay logged in not handled!!
        Player.getPlayerByUsername(username).setNumberOfAttemptsToZero();
        return LOGIN_SUCCESSFUL;
    }

    public SignUpMenu getSignUpMenu() {
        return signUpMenu;
    }

    public LoginMenu getLoginMenu() {
        return loginMenu;
    }

    public SignUpMessages forgetMyPassword(String username) {
        if (Player.getPlayerByUsername(username) == null)
            return USER_DOES_NOT_EXIST;
        Player player = Player.getPlayerByUsername(username);
        System.out.println("Do you remember the answer of your security question?");
        System.out.println(player.getSecurityQuestion());
        String securityAnswer = Menu.getScanner().nextLine();
        if (!player.isSecurityAnswerCorrect(securityAnswer))
            return ANSWER_DOES_NOT_MATCH;
        System.out.print("please enter your new password: ");
        String password = Menu.getScanner().nextLine();
        if (checkPassword(password).equals(PASSWORD_STRONG))
        {
            System.out.print("please enter your password again to confirm: ");
            String passwordConfirm = Menu.getScanner().nextLine();
            if (!passwordConfirm.equals(password))
                return PASSWORD_CONFIRM_DOES_NOT_MATCH;
            player.setPassword(password);
            return PASSWORD_CHANGED;
        }
        else
            return checkPassword(password);
    }
}
