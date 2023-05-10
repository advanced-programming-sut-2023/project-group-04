package org.view;

import org.view.CommandsEnum.ProfileCommands;
import org.view.CommandsEnum.ProfileMessages;

import java.util.regex.Matcher;

public class ProfileMenu {
    public void run() {
        String input;
        Matcher matcher;

        while (true) {
            input = Menu.getScanner().nextLine();

            if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_USERNAME)) != null) {
                ProfileMessages profileMessages = Menu.getProfileController().changeUsername(matcher);
                System.out.println(profileMessages.getMessage());

                if (profileMessages.equals(ProfileMessages.REPEATED_USERNAME)) {
                    String username = matcher.group("username");
                    System.out.println(isSuggestedUsernameAccepted(username).getMessage());
                }
            } else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_NICKNAME)) != null)
                System.out.println(Menu.getProfileController().changeNickname(matcher).getMessage());

            else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_EMAIL)) != null)
                System.out.println(Menu.getProfileController().changeEmail(matcher).getMessage());

            else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_SLOGAN)) != null) {
                if (matcher.group("slogan").contains("random")) {
                    String randomSlogan = Menu.getProfileController().giveRandomSlogan();
                    System.out.println("Your slogan is: " + randomSlogan);
                    System.out.println(Menu.getProfileController().changeRandomSlogan(randomSlogan).getMessage());
                } else
                    System.out.println(Menu.getProfileController().changeSlogan(matcher).getMessage());
            } else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_PASSWORD)) != null) {
                //TODO : ADD CAPTCHA FOR THIS PART FROM ABOLFAZL
                if (matcher.group("newPassword").contains("random")) {
                    System.out.println(isRandomPasswordAccepted());
                } else {
                    ProfileMessages profileMessages = Menu.getProfileController().changePassword(matcher);
                    System.out.println(Menu.getProfileController().changePassword(matcher).getMessage());
                    String password = matcher.group("newPassword").replaceAll("\"", "");
                    if (profileMessages.equals(ProfileMessages.CHANGE_SUCCESSFULLY)) {
                        if (!confirmPassword(password))
                            System.out.println(ProfileMessages.INCORRECT_CURRENT_PASSWORD.getMessage());
                        else System.out.println(ProfileMessages.CHANGE_SUCCESSFULLY.getMessage());
                    }
                    else System.out.println(ProfileMessages.CHANGING_PASSWORD_FAILED.getMessage());
                }
            } else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_PROFILE) != null)
                System.out.println(Menu.getProfileController().displayProfile());

            else if (ProfileCommands.getMatcher(input, ProfileCommands.REMOVE_SLOGAN) != null)
                System.out.println(Menu.getProfileController().removeSlogan().getMessage());

            else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_RANK) != null)
                System.out.println(Menu.getProfileController().showRank());

            else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_SLOGAN) != null)
                System.out.println(Menu.getProfileController().showSlogan());

            else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_HIGH_SCORE) != null)
                System.out.println(Menu.getProfileController().highScore());

            else if (ProfileCommands.getMatcher(input, ProfileCommands.BACK) != null) {
                System.out.println("You are in main menu now!");
                return;
            }
            else System.out.println("Invalid command");
        }
    }

    private ProfileMessages isSuggestedUsernameAccepted(String username) {
        String suggestedUsername = Menu.getProfileController().suggestNewUsername(username);
        System.out.println("You can register with this username: \"" + suggestedUsername
                + "\"\n" + "If you want it type <yes> else type <no>");
        if (Menu.getScanner().nextLine().equalsIgnoreCase("yes")) {
            Menu.getProfileController().setUsername(suggestedUsername);
            return ProfileMessages.CHANGE_SUCCESSFULLY;
        }
        return ProfileMessages.CHANGING_USERNAME_FAILED;
    }

    private ProfileMessages isRandomPasswordAccepted() {
        String randomPassword = Menu.getProfileController().generateRandomPassword();
        System.out.println("This password that i generate for you :" + randomPassword +
                "\nIf you want it re-enter it again:");
        if (Menu.getScanner().nextLine().equals(randomPassword)) {
            Menu.getProfileController().setPassword(randomPassword);
            return ProfileMessages.CHANGE_SUCCESSFULLY;
        } else
            return ProfileMessages.CHANGING_PASSWORD_FAILED;
    }

    private boolean confirmPassword(String password) {
        System.out.println("Please re-enter your new password again:");
        if (Menu.getScanner().nextLine().equals(password)) {
            Menu.getProfileController().setPassword(password);
            return true;
        }
        return false;
    }
}
