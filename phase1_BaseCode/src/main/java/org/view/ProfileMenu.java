package org.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.view.CommandsEnum.ProfileMessages;

public class ProfileMenu extends Application {

    public static AnchorPane anchorPane;
    public static Stage stage;

//    public void run() throws Exception {
//        String input;
//        Matcher matcher;
//
//        while (true) {
//            input = Menu.getScanner().nextLine();
//
//            if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_USERNAME)) != null) {
//                ProfileMessages profileMessages = Menu.getProfileController().changeUsername(matcher);
//                System.out.println(profileMessages.getMessage());
//
//                if (profileMessages.equals(ProfileMessages.REPEATED_USERNAME)) {
//                    String username = matcher.group("username");
//                    System.out.println(isSuggestedUsernameAccepted(username).getMessage());
//                }
//            } else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_NICKNAME)) != null)
//                System.out.println(Menu.getProfileController().changeNickname(matcher).getMessage());
//
//            else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_EMAIL)) != null)
//                System.out.println(Menu.getProfileController().changeEmail(matcher).getMessage());
//
//            else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_SLOGAN)) != null) {
//                if (matcher.group("slogan").contains("random")) {
//                    String randomSlogan = Menu.getProfileController().giveRandomSlogan();
//                    System.out.println("Your slogan is: " + randomSlogan);
//                    System.out.println(Menu.getProfileController().changeRandomSlogan(randomSlogan).getMessage());
//                } else
//                    System.out.println(Menu.getProfileController().changeSlogan(matcher).getMessage());
//            } else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_PASSWORD)) != null) {
//                if (matcher.group("newPassword").contains("random")) {
//                    System.out.println(isRandomPasswordAccepted().getMessage());
//                } else {
//                    ProfileMessages profileMessages = Menu.getProfileController().changePassword(matcher);
//                    if (!profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY))
//                        System.out.println(profileMessages.getMessage());
//                    String password = matcher.group("newPassword").replaceAll("\"", "");
//                    if (profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY)) {
//                        if (!confirmPassword(password))
//                            System.out.println(ProfileMessages.INCORRECT_CURRENT_PASSWORD.getMessage());
//                        else System.out.println(ProfileMessages.CHANGE_SUCCESSFULLY.getMessage());
//                    } else System.out.println(ProfileMessages.CHANGING_PASSWORD_FAILED.getMessage());
//                }
//            } else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_PROFILE) != null)
//                System.out.println(Menu.getProfileController().displayProfile());
//
//            else if (ProfileCommands.getMatcher(input, ProfileCommands.REMOVE_SLOGAN) != null)
//                System.out.println(Menu.getProfileController().removeSlogan().getMessage());
//
//            else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_RANK) != null)
//                System.out.println(Menu.getProfileController().showRank());
//
//            else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_SLOGAN) != null)
//                System.out.println(Menu.getProfileController().showSlogan());
//
//            else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_HIGH_SCORE) != null)
//                System.out.println(Menu.getProfileController().highScore());
//
//            else if (ProfileCommands.getMatcher(input, ProfileCommands.BACK) != null) {
//                System.out.println("You are in main menu now!");
//                return;
//            } else System.out.println("Invalid command");
//        }
//    }

//    private ProfileMessages isSuggestedUsernameAccepted(String username) {
//        String suggestedUsername = Menu.getProfileController().suggestNewUsername(username);
//        System.out.println("You can register with this username: \"" + suggestedUsername
//                + "\"\n" + "If you want it type <yes> else type <no>");
//        if (Menu.getScanner().nextLine().equalsIgnoreCase("yes")) {
//            Menu.getProfileController().setUsername(suggestedUsername, anchorPane, vBox, usernameError);
//            return ProfileMessages.CHANGE_SUCCESSFULLY;
//        }
//        return ProfileMessages.CHANGING_USERNAME_FAILED;
//    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(ProfileMenu.class.getResource("/fxml/profileMenu.fxml"));
        ProfileMenu.anchorPane = anchorPane;
        ProfileMenu.stage = stage;
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

//    private ProfileMessages isRandomPasswordAccepted() throws Exception {
//        SignUpMessages signUpMessages = Menu.getSignupController().generateCaptcha();
//        String randomPassword = Menu.getProfileController().generateRandomPassword();
//        if (signUpMessages.equals(SignUpMessages.CAPTCHA_CORRECT)) {
//            System.out.println("This password that i generate for you :" + randomPassword +
//                    "\nIf you want it re-enter it again:");
//        }
//        if (Menu.getScanner().nextLine().equals(randomPassword)) {
//            Menu.getProfileController().setPassword(randomPassword);
//            return ProfileMessages.CHANGE_SUCCESSFULLY;
//        } else
//            return ProfileMessages.CHANGING_PASSWORD_FAILED;
//    }

//    private boolean confirmPassword(String password) throws Exception {
//        Menu.getSignupController().generateCaptcha();
//        System.out.println("Please re-enter your new password again:");
//        while (true) {
//            String newPassword = Menu.getScanner().nextLine();
//            if (newPassword.equals(password)) {
//                Menu.getProfileController().setPassword(password);
//                return true;
//            }
//            System.out.println("Please enter your new password correctly!:");
//        }
//    }
}
