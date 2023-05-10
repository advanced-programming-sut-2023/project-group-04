package org.controller;

import java.util.Random;
import java.util.regex.Matcher;

import org.model.ASCIIArtGenerator;
import org.model.Player;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.view.CommandsEnum.*;
import org.view.Menu;
import org.view.SignUpMenu;

import java.util.ArrayList;

import static org.view.CommandsEnum.SignUpMessages.*;

public class LoginController {

    public String SignUp(Matcher matcher, String input) {
        String username = matcher.group("username").replaceAll("\"", "");
        String password = matcher.group("password");
        String passwordConfirmation = matcher.group("passwordConfirm");
        String email = matcher.group("email").replaceAll("\"", "").toLowerCase();
        String nickname = matcher.group("nickname").replaceAll("\"", "");
        String slogan = "";
        if (matcher.group("slogan") != null)
            slogan = matcher.group("slogan").replaceAll("\"", "");
        if (checkSignUpErrors(username, password, passwordConfirmation, email, nickname, slogan, input) == WITHOUT_ERROR) {
        } else {
            return checkSignUpErrors(username, password, passwordConfirmation, email, nickname, slogan, input).getMessage();
        }
        if (Player.getPlayerByUsername(username) != null) {
            String newUsername = suggestNewUsername(username);
            if (new SignUpMenu().acceptSuggestedUsername(newUsername)) {
                username = newUsername;
            } else
                return REGISTRATION_FAILED.getMessage();
        }
        if (password.equals("random")) {
            password = generateRandomPassword();
            if (slogan.equals("random")) {
                slogan = giveRandomSlogan();
                Player player = new Player(username, password, nickname, email, slogan);
                Player.setCurrentPlayer(player);
                return showSloganAndPassword(slogan, password);
            } else {
                Player player = new Player(username, password, nickname, email, slogan);
                Player.setCurrentPlayer(player);
                return "Your random password is: " + password;
            }
        } else {
            if (slogan != null)
                if (slogan.equals("random")) {
                    slogan = giveRandomSlogan();
                    Player player = new Player(username, password, nickname, email, slogan);
                    Player.setCurrentPlayer(player);
                    return "Your random slogan is \"" + slogan + "\"";
                }
        }
        Player player = new Player(username, password, nickname, email, slogan);
        Player.setCurrentPlayer(player);
        return REGISTRATION_SUCCESSFUL.getMessage();
    }

    private String giveSecurityQuestionWithNumber(Matcher matcher) {
        switch (matcher.group("questionNumber")) {
            case "1":
                return SecurityQuestion.getQuestion(SecurityQuestion.PET);
            case "2":
                return SecurityQuestion.getQuestion(SecurityQuestion.TEACHER);
            default:
                return SecurityQuestion.getQuestion(SecurityQuestion.CAR);
        }
    }

    private String showSloganAndPassword(String slogan, String password) {
        return "Your slogan is \"" + slogan + "\"\nYour random password is: " + password;
    }

    public String showSecurityQuestions() {
        return "Pick your security question:\n1. " + SecurityQuestion.getQuestion(SecurityQuestion.PET)
                + "\n2. " + SecurityQuestion.getQuestion(SecurityQuestion.TEACHER)
                + "\n3. " + SecurityQuestion.getQuestion(SecurityQuestion.CAR);
    }

    private SignUpMessages checkSignUpErrors(String username, String password,
                                             String passwordConfirmation, String email,
                                             String nickname, String slogan, String input) {
        if (slogan == "" && input.contains("-s")) return EMPTY_SLOGAN;
        if (username == null || password == null || email == null || nickname == null) return EMPTY_FIELD;
        else if (!isUsernameFormatCorrect(username)) return INCORRECT_USERNAME_FORMAT;
        else if (checkPassword(password, passwordConfirmation).equals(PASSWORD_STRONG)) {
        } else
            return checkPassword(password, passwordConfirmation);
        if (checkEmailExistence(email))
            return EXISTENCE_EMAIL;
        else if (!email.matches("[a-zA-Z0-9_.]+@[a-zA-Z0-9_.]+\\.[a-zA-Z0-9_.]+"))
            return INCORRECT_EMAIL_FORMAT;
        return WITHOUT_ERROR;
    }

    private SignUpMessages generateCaptcha() throws Exception {
        String input = null;
        ASCIIArtGenerator asciiArtGenerator = new ASCIIArtGenerator();
        int randomCaptchaNumber = (int) (Math.random() * (99999999 - 1000 + 1) + 1000);
        asciiArtGenerator.printTextArt(String.valueOf(randomCaptchaNumber), ASCIIArtGenerator.ART_SIZE);
        System.out.println("enter the Captcha code / if it is not readable type : <<change captcha>>");
        input = Menu.getScanner().nextLine();
        if (input.matches("\\s*change\\s+captcha\\s*"))
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


    public SignUpMessages setSecurityQuestion(Matcher matcher) {
        int questionNumber = Integer.parseInt(matcher.group("questionNumber"));
        String answer = matcher.group("answer").replaceAll("\"", "");
        String answerConfirm = matcher.group("answerConfirm").replaceAll("\"", "");
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
        String password = new PasswordGenerator().generatePassword(6, lowerCase, uppercase, digit);
        String specificChars = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            char randomChar = specificChars.charAt(random.nextInt(specificChars.length() - 1));
            int charAddPlace = random.nextInt(5 + i);
            password = password.substring(0, charAddPlace) + randomChar + password.substring(charAddPlace);
        }
        return password;
    }

    private boolean checkEmailExistence(String email) {
        return Player.getPlayerByEmail(email) != null;
    }

    private SignUpMessages checkPassword(String password, String passwordConfirm) {
        if (password.equals("random"))
            return PASSWORD_STRONG;
        else if (password.matches(".*\\s.*"))
            return PASSWORD_INCORRECT_FORMAT;
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
        else if (!password.equals(passwordConfirm))
            return PASSWORD_CONFIRM_DOES_NOT_MATCH;
        return PASSWORD_STRONG;
    }

    public String suggestNewUsername(String username) {
        Random rand = new Random();
        while (true) {
            int randomNum = rand.nextInt(1000);
            if (Player.getPlayerByUsername(username + randomNum) == null)
                return username + randomNum;
        }
    }

    private boolean isUsernameFormatCorrect(String username) {
        return username.matches("\\w+");
    }

    public String signIn(Matcher matcher) throws Exception {
        String username = matcher.group("username").replaceAll("\"", "");
        String password = matcher.group("password");
        String status = matcher.group("status");
        if (Player.getPlayerByUsername(username) == null) return USER_DOES_NOT_EXIST.getMessage();
        if (!Player.getPlayerByUsername(username).isPasswordCorrect(password)) {
            Player.getPlayerByUsername(username).increaseNumberOfAttempts();
            return INCORRECT_PASSWORD.getMessage() + "\nPlease try again after <<"
                    + Player.getNumberOfAttempts() * 5 + ">> seconds!";
        }
        // TODO: 4/19/2023  //Stay logged in not handled!!
        if (generateCaptcha().equals(CAPTCHA_CORRECT)) Player.resetNumberOfAttempts();
        Player.setCurrentPlayer(Player.getPlayerByUsername(username));
        return LOGIN_SUCCESSFUL.getMessage();
    }

    public String getSecurityQuestion(String username) {
        Player player = Player.getPlayerByUsername(username.replaceAll("\"" , ""));
        if (player == null) return USER_NOT_FOUND.getMessage();
        Player.setCurrentPlayer(player);
        return player.getSecurityQuestion();
    }

    public String checkSecurityAnswer(String securityAnswer) {
        Player player = Player.getCurrentPlayer();
        if (!player.isSecurityAnswerCorrect(securityAnswer)) return SignUpMessages.ANSWER_DOES_NOT_MATCH.getMessage()
                + "\nre-enter your answer:";
        else return "please enter your new password: ";
    }

    public SignUpMessages setNewPassword(Matcher matcher) {
        String password = matcher.group("password").replaceAll("\"", "");
        String passwordConfirm = matcher.group("passwordConfirm").replaceAll("\"", "");
        SignUpMessages signUpMessage = checkPassword(password, passwordConfirm);
        if (!signUpMessage.equals(SignUpMessages.PASSWORD_STRONG)) return signUpMessage;
        Player.getCurrentPlayer().setPassword(password);
        Player.savePlayers();
        return SignUpMessages.PASSWORD_CHANGED;
    }

    public void delay() {
        long startTime = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() == startTime + 5000L * Player.getNumberOfAttempts()) return;
        }
    }

    public SignUpMessages finishRegister(Matcher pickQuestionMatcher) throws Exception {
        String securityQuestion,
                securityAnswer = pickQuestionMatcher.group("answer").replaceAll("\"", "");
        securityQuestion = giveSecurityQuestionWithNumber(pickQuestionMatcher);
        if (generateCaptcha().equals(CAPTCHA_CORRECT)) {
            Player player = Player.getCurrentPlayer();
            player.setSecurityQuestion(securityQuestion);
            player.setSecurityAnswer(securityAnswer);
            Player.addPlayer(player);
            Player.savePlayers();
        }
        return SignUpMessages.REGISTRATION_SUCCESSFUL;
    }

    public String checkRandomPassword(String password) {
        if (Player.getCurrentPlayer().isPasswordCorrect(password))
            return "password confirmed!";
        return "confirmation failed!\nplease enter password correctly!: ";
    }
}