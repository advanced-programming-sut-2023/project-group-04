package org.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.model.ASCIIArtGenerator;
import org.model.Player;
import org.model.map.Map;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.view.CommandsEnum.SecurityQuestion;
import org.view.CommandsEnum.SignUpMessages;
import org.view.LoginMenu;
import org.view.Menu;
import org.view.SignUpMenu;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;

import javafx.scene.image.ImageView;


import javax.swing.*;

import static org.view.CommandsEnum.SignUpMessages.*;

public class SignupController {

    public TextField username;
    public PasswordField password;
    public TextField showPassword;
    public Text usernameError;
    public Text passwordError;
    public ImageView showPasswordIcon;
    public ImageView hidePassword;
    public TextField email;
    public Text emailError;
    public CheckBox randomPassword;
    public CheckBox chooseSlogan;
    public TextField slogan;
    public CheckBox randomSlogan;
    public TextField nickname;
    public ChoiceBox securityQuestion;
    public TextField securityAnswer;
    public Button submitAnswer;
    public ImageView captchaImage;
    public TextField captcha;
    public Button submitCaptcha;
    public ImageView change;
    public Text nicknameError;
    public Text captchaError;
    public ChoiceBox famousSlogan;

    @FXML
    public void initialize() {
        username.textProperty().addListener((observable, oldText, newText) -> {
            if (!isUsernameFormatCorrect(newText)) {
                usernameError.setText(SignUpMessages.INCORRECT_USERNAME_FORMAT.getMessage());
                username.setStyle("-fx-border-color: red");
            } else if (Player.getPlayerByUsername(newText) != null) {
                usernameError.setText(SignUpMessages.USER_EXIST.getMessage());
                username.setStyle("-fx-border-color: red");
            } else {
                usernameError.setText("");
                username.setStyle("-fx-border-color: purple");
            }
        });
        password.textProperty().addListener(((observableValue, oldText, newText) -> {
            SignUpMessages signUpMessages = checkPassword(newText);
            showPassword.setText(newText);
            if (!signUpMessages.equals(SignUpMessages.PASSWORD_STRONG)) {
                passwordError.setText(signUpMessages.getMessage());
                password.setStyle("-fx-border-color: red");
                showPassword.setStyle("-fx-border-color: red");
            } else {
                passwordError.setText("");
                password.setStyle("-fx-border-color: purple");
                showPassword.setStyle("-fx-border-color: purple");
            }
        }));
        email.textProperty().addListener(((observableValue, oldText, newText) -> {
            SignUpMessages signUpMessages = checkEmailErrors(newText);
            if (signUpMessages.equals(SignUpMessages.WITHOUT_ERROR)) {
                emailError.setText("");
                email.setStyle("-fx-border-color: purple");
            } else {
                emailError.setText(signUpMessages.getMessage());
                email.setStyle("-fx-border-color: red");
            }
        }));
        securityAnswer.textProperty().addListener(((observableValue, s, t1) -> {
            if (t1.length() > 0) {
                submitAnswer.setVisible(true);
            } else submitAnswer.setVisible(false);
        }));
        nickname.textProperty().addListener(((observableValue, s, t1) -> {
            if (t1.length() > 0) {
                nickname.setStyle("-fx-border-color: purple");
                nicknameError.setText("");
            }
        }));
    }

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
                Player player = new Player(username, Player.getSHA256Hash(password), nickname, email, slogan);
                Player.setCurrentPlayer(player);
                return showSloganAndPassword(slogan, password);
            } else {
                Player player = new Player(username, Player.getSHA256Hash(password), nickname, email, slogan);
                Player.setCurrentPlayer(player);
                return "Your random password is: " + password;
            }
        } else {
            if (slogan != null)
                if (slogan.equals("random")) {
                    slogan = giveRandomSlogan();
                    Player player = new Player(username, Player.getSHA256Hash(password), nickname, email, slogan);
                    Player.setCurrentPlayer(player);
                    return "Your random slogan is \"" + slogan + "\"";
                }
        }
        Player player = new Player(username, Player.getSHA256Hash(password), nickname, email, slogan);
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
        else if (checkPassword(password).equals(PASSWORD_STRONG)) {
        } else
            return checkPassword(password);
        return WITHOUT_ERROR;
    }

    public SignUpMessages generateCaptcha() throws Exception {
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

    private SignUpMessages checkEmailErrors(String email) {
        if (Player.getPlayerByEmail(email) != null)
            return SignUpMessages.EXISTENCE_EMAIL;
        else if (!email.matches("[a-zA-Z0-9_.]+@[a-zA-Z0-9_.]+\\.[a-zA-Z0-9_.]+"))
            return SignUpMessages.INCORRECT_EMAIL_FORMAT;
        return SignUpMessages.WITHOUT_ERROR;
    }

    private SignUpMessages checkPassword(String password) {
        if (password.matches(".*\\s.*"))
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
        if (!Player.getPlayerByUsername(username).isPasswordCorrect(Player.getSHA256Hash(password))) {
            Player.getPlayerByUsername(username).increaseNumberOfAttempts();
            return INCORRECT_PASSWORD.getMessage() + "\nPlease try again after <<"
                    + Player.getNumberOfAttempts() * 5 + ">> seconds!";
        }
        // TODO: 4/19/2023  //Stay logged in not handled!!
        if (generateCaptcha().equals(CAPTCHA_CORRECT)) Player.resetNumberOfAttempts();
        Player player = Player.getPlayerByUsername(username);
        Player.setCurrentPlayer(player);
        if (status != null) player.setStayLogin();
        return LOGIN_SUCCESSFUL.getMessage();
    }

    public String getSecurityQuestion(String username) {
        Player player = Player.getPlayerByUsername(username.replaceAll("\"", ""));
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
        SignUpMessages signUpMessage = checkPassword(password);
        if (!signUpMessage.equals(SignUpMessages.PASSWORD_STRONG)) return signUpMessage;
        Player.getCurrentPlayer().setPassword(Player.getSHA256Hash(password));
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

    public void clearStayLogin() {
        Player.removeStayLogin();
    }

    public boolean runProgram() {
        Map.recoveryMaps();
        Player.recoveryPlayers();
        Player player = Player.getStayLogin();
        if (player != null) {
            Player.setCurrentPlayer(player);
            return true;
        }
        return false;
    }

    public void showPassword(MouseEvent mouseEvent) {
        showPassword.setVisible(true);
        showPasswordIcon.setVisible(false);
        hidePassword.setVisible(true);
        password.setVisible(false);
        showPassword.textProperty().addListener(((observableValue, oldText, newText) -> {
            SignUpMessages signUpMessages = checkPassword(newText);
            password.setText(newText);
            if (!signUpMessages.equals(SignUpMessages.PASSWORD_STRONG)) {
                passwordError.setText(signUpMessages.getMessage());
                password.setStyle("-fx-border-color: red");
                showPassword.setStyle("-fx-border-color: red");
            } else {
                passwordError.setText("");
                password.setStyle("-fx-border-color: purple");
                showPassword.setStyle("-fx-border-color: purple");
            }
        }));
    }

    public void hidePassword(MouseEvent mouseEvent) {
        showPassword.setVisible(false);
        showPasswordIcon.setVisible(true);
        hidePassword.setVisible(false);
        password.setVisible(true);
    }

    public void setRandomPassword(MouseEvent mouseEvent) {
        if (randomPassword.isSelected()) {
            String password = generateRandomPassword();
            this.password.setText(password);
        } else {
            this.password.setText("");
        }
    }

    public void chooseSlogan(MouseEvent mouseEvent) {
        if (chooseSlogan.isSelected()) {
            slogan.setDisable(false);
            randomSlogan.setDisable(false);
            famousSlogan.setVisible(true);

        } else {
            slogan.setDisable(true);
            slogan.setText("");
            randomSlogan.setDisable(true);
            randomSlogan.setSelected(false);
            famousSlogan.setVisible(false);
        }
    }

    public void getRandomSlogan(MouseEvent mouseEvent) {
        String slogan = giveRandomSlogan();
        if (randomSlogan.isSelected()) {
            this.slogan.setText(slogan);
        } else {
            this.slogan.setText("");
        }
    }

    public void submitUser(MouseEvent mouseEvent) {
        String username = this.username.getText();
        String password = this.password.getText();
        String nickname = this.nickname.getText();
        String email = this.email.getText();
        String slogan = this.slogan.getText();
        if (haveError(username, password, nickname, slogan, email)) {

        } else
            register(username, password, nickname, slogan, email);
    }

    private void register(String username, String password, String nickname, String slogan, String email) {
        JOptionPane.showMessageDialog(null,"your information accepted!"
                , "Successful", JOptionPane.INFORMATION_MESSAGE);
        securityQuestion.setVisible(true);
        final String[] question = {""};
        securityQuestion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                question[0] = securityQuestion.getValue().toString();
                securityAnswer.setVisible(true);
            }
        });
    }

    private boolean haveError(String username, String password, String nickname, String slogan, String email) {
        if (username.isEmpty()) {
            usernameError.setText(SignUpMessages.EMPTY_FIELD.getMessage());
            this.username.setStyle("-fx-border-color: red");
        }
        else if (!isUsernameFormatCorrect(username)) {
            usernameError.setText(SignUpMessages.INCORRECT_USERNAME_FORMAT.getMessage());
            this.username.setStyle("-fx-border-color: red");
        } else if (Player.getPlayerByUsername(username) != null) {
            usernameError.setText(SignUpMessages.USER_EXIST.getMessage());
            this.username.setStyle("-fx-border-color: red");
        }
        SignUpMessages signUpMessages = checkPassword(password);
        showPassword.setText(password);
        if (password.isEmpty()) {
            passwordError.setText(SignUpMessages.EMPTY_FIELD.getMessage());
            this.password.setStyle("-fx-border-color: red");
            showPassword.setStyle("-fx-border-color: red");
        }
        else if (!signUpMessages.equals(SignUpMessages.PASSWORD_STRONG)) {
            passwordError.setText(signUpMessages.getMessage());
            this.password.setStyle("-fx-border-color: red");
            showPassword.setStyle("-fx-border-color: red");
        }
        SignUpMessages signUpMessages1 = checkEmailErrors(email);
        if (email.isEmpty()) {
            emailError.setText(SignUpMessages.EMPTY_FIELD.getMessage());
            this.email.setStyle("-fx-border-color: red");
        }
        else if (!signUpMessages1.equals(SignUpMessages.WITHOUT_ERROR)) {
            emailError.setText(signUpMessages1.getMessage());
            this.email.setStyle("-fx-border-color: red");
        }
        if (nickname.isEmpty()) {
            nicknameError.setText(SignUpMessages.EMPTY_FIELD.getMessage());
            this.nickname.setStyle("-fx-border-color: red");
        }
        if (nothingCauseError(username, password, nickname, slogan, email))
            return false;
        return true;
    }

    private boolean nothingCauseError(String username, String password, String nickname, String slogan, String email) {
        return isUsernameFormatCorrect(username) && Player.getPlayerByUsername(username) == null &&
                checkPassword(password).equals(SignUpMessages.PASSWORD_STRONG) &&
                checkEmailErrors(email).equals(SignUpMessages.WITHOUT_ERROR) && !username.isEmpty() &&
                !password.isEmpty() && !nickname.isEmpty() && !email.isEmpty();
    }

    public void generateCaptcha1(MouseEvent mouseEvent) {
        if (slogan.getText().isEmpty())
            slogan.setText("");
        Player player = new Player(username.getText(), password.getText(), nickname.getText(), email.getText(), slogan.getText());
        Player.addPlayer(player);
        player.setSecurityQuestion(securityQuestion.getValue().toString());
        player.setSecurityAnswer(securityAnswer.getText());
        int fileNumber = getFileNumber();
        captchaImage.setImage(new Image(SignupController.class.getResource("/images/captcha/" + fileNumber + ".png").toExternalForm()));
        captchaImage.setVisible(true);
        captcha.setVisible(true);
        submitCaptcha.setVisible(true);
        change.setVisible(true);
    }

    private int getFileNumber() {
        int[] fileNumbers = {1181, 1381, 1491, 1722, 1959, 2163, 2177, 2723, 2785, 3541, 3847, 3855, 3876, 3967, 4185,
                4310, 4487, 4578, 4602, 4681, 4924, 5326, 5463, 5771, 5849, 6426, 6553, 6601, 6733, 6960,
                7415, 7609, 7755, 7825, 7905, 8003, 8010, 8368, 8455, 8506, 8555, 8583, 8692, 8776, 8972,
                8996, 9061, 9386, 9582, 9633};
        int random = (int) (Math.random() * (50));
        return fileNumbers[random];
    }

    public void changeCaptcha(MouseEvent mouseEvent) {
        generateCaptcha1(mouseEvent);
    }

    public void submitCaptcha(MouseEvent mouseEvent) throws Exception {
        String captchaAnswer = captcha.getText();
        String url = captchaImage.getImage().getUrl();
        String correctAnswer = url.substring(url.length() - 8, url.length() - 4);
        if (captchaAnswer.equals(correctAnswer)) {
            showAlert(Alert.AlertType.INFORMATION, "SignUp", "You successfully registered!");
            Player.savePlayers();
            new LoginMenu().start(LoginMenu.stage);
        } else {
            generateCaptcha1(mouseEvent);
            captcha.setStyle("-fx-border-color: red");
            captchaError.setText(SignUpMessages.CAPTCHA_IS_WRONG.getMessage());
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void getFamousSlogan(ActionEvent actionEvent) {
        String slogan = famousSlogan.getValue().toString();
        this.slogan.setText(slogan);
    }
}