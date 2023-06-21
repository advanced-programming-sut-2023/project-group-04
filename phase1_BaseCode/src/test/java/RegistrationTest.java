//import org.controller.SignupController;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.model.Player;
//import org.view.CommandsEnum.SignUpCommands;
//import org.view.CommandsEnum.SignUpMessages;
//
//import java.util.regex.Matcher;
//
//import static org.view.CommandsEnum.SignUpMessages.INCORRECT_PASSWORD;
//
//public class RegistrationTest {
//
//    /*
//    we have a user in json file for test:
//    username = abolfazl , password = Aa1!1111 , email = abolfazlsheikhha.833@gmail.com
//     */
//    @BeforeAll
//    public static void recovery() {
//        Player.recoveryPlayers();
//    }
//
//    SignupController loginController = new SignupController();
//
//    @Test
//    public void emptySloganCheck() {
//        String input = "  create  user  -u usef -p random   -e  usef@gmail.com  -n \"busy engineer\"  -s";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT);
//        String result = loginController.SignUp(matcher, input);
//        Assertions.assertEquals(SignUpMessages.EMPTY_SLOGAN.getMessage(), result);
//    }
//
//    @Test
//    public void usernameIncorrectFormat() {
//        String input = "  create  user  -u u#sef -p random   -e  usef@gmail.com  -n \"busy engineer\"  -s random";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT);
//        String result = loginController.SignUp(matcher, input);
//        Assertions.assertEquals(SignUpMessages.INCORRECT_USERNAME_FORMAT.getMessage(), result);
//    }
//
//    @Test
//    public void passwordLength() {
//        String input = "  create  user  -u usef -p Aaa Aaa  -e  usef@gmail.com  -n \"busy engineer\"  -s random";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT);
//        String result = loginController.SignUp(matcher, input);
//        Assertions.assertEquals(SignUpMessages.PASSWORD_LENGTH_WEAK.getMessage(), result);
//    }
//
//    @Test
//    public void passwordUppercase() {
//        String input = "  create  user  -u usef -p aaaa2#aa aaaa2#aa  -e  usef@gmail.com  -n \"busy engineer\"  -s random";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT);
//        String result = loginController.SignUp(matcher, input);
//        Assertions.assertEquals(SignUpMessages.PASSWORD_UPPERCASE_WEAK.getMessage(), result);
//    }
//
//    @Test
//    public void passwordLowercase() {
//        String input = "  create  user  -u usef -p AA22## AA22##  -e  usef@gmail.com  -n \"busy engineer\"  -s random";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT);
//        String result = loginController.SignUp(matcher, input);
//        Assertions.assertEquals(SignUpMessages.PASSWORD_LOWERCASE_WEAK.getMessage(), result);
//    }
//
//    @Test
//    public void passwordNumber() {
//        String input = "  create  user  -u usef -p Aaa### Aaa###  -e  usef@gmail.com  -n \"busy engineer\"  -s random";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT);
//        String result = loginController.SignUp(matcher, input);
//        Assertions.assertEquals(SignUpMessages.PASSWORD_NUMBER_WEAK.getMessage(), result);
//    }
//
//    @Test
//    public void passwordSpecificChar() {
//        String input = "  create  user  -u usef -p Aaa123 Aaa123  -e  usef@gmail.com  -n \"busy engineer\"  -s random";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT);
//        String result = loginController.SignUp(matcher, input);
//        Assertions.assertEquals(SignUpMessages.PASSWORD_SPECIFIC_CHAR_WEAK.getMessage(), result);
//    }
//
//    @Test
//    public void emailExistence() {
//        String input = "  create  user  -u usef -p Aa1!1111 Aa1!1111  -e AbolFazlSheiKhha.833@Gmail.cOm  -n \"busy engineer\"  -s \"random slogan\"";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT);
//        String result = loginController.SignUp(matcher, input);
//        Assertions.assertEquals(SignUpMessages.EXISTENCE_EMAIL.getMessage(), result);
//    }
//
//    @Test
//    public void emailFormat() {
//        String input = "  create  user  -u usef -p Aa1!1111 Aa1!1111  -e  usef@gmailcom  -n \"busy engineer\"  -s random";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT);
//        String result = loginController.SignUp(matcher, input);
//        Assertions.assertEquals(SignUpMessages.INCORRECT_EMAIL_FORMAT.getMessage(), result);
//    }
//
//    @Test
//    public void randomSlogan() {
//        String input = "  create  user  -u usef -p Aa1!1111 Aa1!1111  -e usef@gmail.com  -n \"busy engineer\"  -s random";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT);
//        String result = loginController.SignUp(matcher, input);
//        boolean excepted = result.contains("Your random slogan is");
//        Assertions.assertTrue(excepted);
//    }
//
//    @Test
//    public void  randomPassword() {
//        String input = "  create  user  -u usef -p random  -e usef@gmail.com  -n \"busy engineer\"  -s rammmndom";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT);
//        String result = loginController.SignUp(matcher, input);
//        boolean excepted = result.contains("Your random password is");
//        Assertions.assertTrue(excepted);
//    }
//
//    @Test
//    public void successfulRegister() {
//        String input = "  create  user  -u usef -p Aa1!1111 Aa1!1111  -e usef@gmail.com  -n \"busy engineer\"  -s randggom";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT);
//        String result = loginController.SignUp(matcher, input);
//        Assertions.assertEquals(SignUpMessages.REGISTRATION_SUCCESSFUL.getMessage(), result);
//    }
//
//    @Test
//    public void randomPasswordAndRandomSlogan() {
//        String input = "  create  user  -u usef -p random  -e usef@gmail.com  -n \"busy engineer\"  -s random";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT);
//        String result = loginController.SignUp(matcher, input);
//        boolean expected = result.contains("Your slogan is");
//        Assertions.assertTrue(expected);
//    }
//
//    @Test
//    public void securityQuestionNumber() {
//        String input = "    question    pick    -q 4 -a dog -c fish";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.PICK_SECURITY_QUESTION);
//        SignUpMessages result = loginController.setSecurityQuestion(matcher);
//        Assertions.assertEquals(SignUpMessages.INVALID_QUESTION_NUMBER, result);
//    }
//
//    @Test
//    public void securityConfirmDoesNotMatch() {
//        String input = "    question    pick    -q 1 -a dog -c fish";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.PICK_SECURITY_QUESTION);
//        SignUpMessages result = loginController.setSecurityQuestion(matcher);
//        Assertions.assertEquals(SignUpMessages.ANSWER_CONFIRM_DOES_NOT_MATCH, result);
//    }
//
//    @Test
//    public void successfulSetQuestion() {
//        String input = "    question    pick    -q 1 -a dog -c dog  ";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.PICK_SECURITY_QUESTION);
//        SignUpMessages result = loginController.setSecurityQuestion(matcher);
//        Assertions.assertEquals(SignUpMessages.SET_QUESTION_SUCCESSFUL, result);
//    }
//
//    @Test
//    public void suggestUsername() {
//        String input = "  create  user  -u abolfazl -p Aa1!1111 Aa1!1111  -e usef@gmail.com  -n \"busy engineer\"  -s randggom";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE_ACCOUNT);
//        String result = loginController.suggestNewUsername("abolfazl");
//        boolean expected = result.contains("abolfazl");
//        Assertions.assertTrue(expected);
//    }
//
//    @Test
//    public void usernameDoesntExist() throws Exception {
//        String input = "  user  login    -u usef    -p Aaaa";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.LOGIN_USER);
//        String result = loginController.signIn(matcher);
//        Assertions.assertEquals(SignUpMessages.USER_DOES_NOT_EXIST.getMessage(), result);
//    }
//
//    @Test
//    public void incorrectPassword() throws Exception {
//        String input = "  user  login    -u abolfazl    -p Aa1!111";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.LOGIN_USER);
//        String result = loginController.signIn(matcher);
//        String expected = INCORRECT_PASSWORD.getMessage() + "\nPlease try again after <<"
//                + Player.getNumberOfAttempts() * 5 + ">> seconds!";
//        Assertions.assertEquals(expected, result);
//    }
//
//    @Test
//    public void getUserSecurityQuestionWhoHaveNotRegisterYet() {
//        String username = "usef";
//        String result = loginController.getSecurityQuestion(username);
//        Assertions.assertEquals(SignUpMessages.USER_NOT_FOUND.getMessage(), result);
//    }
//
//    @Test
//    public void getUserSecurityQuestion() {
//        String username = "abolfazl";
//        String result = loginController.getSecurityQuestion(username);
//        Assertions.assertEquals(Player.getPlayerByUsername(username).getSecurityQuestion(), result);
//    }
//
//    @Test
//    public void incorrectSecurityAnswer() {
//        Player.setCurrentPlayer(Player.getPlayerByUsername("abolfazl"));
//        String answer = "Group-04";
//        String result = loginController.checkSecurityAnswer(answer);
//        String expected = SignUpMessages.ANSWER_DOES_NOT_MATCH.getMessage()
//                + "\nre-enter your answer:";
//        Player.setCurrentPlayer(null);
//        Assertions.assertEquals(expected, result);
//    }
//
//    @Test
//    public void correctAnswer() {
//        Player.setCurrentPlayer(Player.getPlayerByUsername("abolfazl"));
//        String answer = "dog";
//        String result = loginController.checkSecurityAnswer(answer);
//        String expected = "please enter your new password: ";
//        Player.setCurrentPlayer(null);
//        Assertions.assertEquals(expected, result);
//    }
//
//    @Test
//    public void newPasswordError() {
//        String input = "  new   password   -p Bb1!1111    -c Bb1!111";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.SET_NEW_PASSWORD);
//        SignUpMessages result = loginController.setNewPassword(matcher);
//        Assertions.assertEquals(SignUpMessages.PASSWORD_CONFIRM_DOES_NOT_MATCH, result);
//    }
//
//    @Test
//    public void setSuccessfulNewPassword() {
//        Player.setCurrentPlayer(Player.getPlayerByUsername("abolfazl"));
//        String input = "  new   password   -p Bb1!1111    -c Bb1!1111";
//        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.SET_NEW_PASSWORD);
//        loginController.setNewPassword(matcher);
//        Assertions.assertTrue(Player.getCurrentPlayer().isPasswordCorrect("Bb1!1111"));
//        Player.getCurrentPlayer().setPassword("Aa1!1111");
//        Player.savePlayers();
//    }
//}
