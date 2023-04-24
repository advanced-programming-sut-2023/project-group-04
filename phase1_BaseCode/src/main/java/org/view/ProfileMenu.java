package org.view;

import org.controller.ProfileController;

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
                    String suggestedUsername = Menu.getProfileController().suggestNewUsername(username);
                    System.out.println(acceptSuggestedUsername(suggestedUsername));
                }
            } else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_NICKNAME)) != null)
                System.out.println(Menu.getProfileController().changeNickname(matcher).getMessage());

            else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_EMAIL)) != null)
                System.out.println(Menu.getProfileController().changeEmail(matcher).getMessage());

            else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_SLOGAN)) != null)
                System.out.println(Menu.getProfileController().changeSlogan(matcher).getMessage());

            else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_PASSWORD)) != null)
                System.out.println(Menu.getProfileController().changePassword(matcher).getMessage());

            else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_PROFILE) != null)
                System.out.println(Menu.getProfileController().displayProfile());

            else if (ProfileCommands.getMatcher(input, ProfileCommands.REMOVE_SLOGAN) != null)
                System.out.println(Menu.getProfileController().removeSlogan().getMessage());

            else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_RANK) != null)
                System.out.println(Menu.getProfileController().showRank());

            else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_SLOGAN) != null)
                System.out.println(Menu.getProfileController().showSlogan());

            else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_HIGHSCORE) != null)
                System.out.println(Menu.getProfileController().highScore());

            else if (ProfileCommands.getMatcher(input, ProfileCommands.BACK) != null)
                return;

            else
                System.out.println("Invalid command");
        }
    }

    public ProfileMessages acceptSuggestedUsername(String suggestedUsername) {
        System.out.println("You can register with this username: \"" + suggestedUsername
                + "\"\n" + "If you want it type <yes> else type <no>");
        if (Menu.getScanner().nextLine().equalsIgnoreCase("yes")) {
            Menu.getProfileController().setUsername(suggestedUsername);
            return ProfileMessages.CHANGE_SUCCESSFULLY;
        }
        return ProfileMessages.CHANGING_USERNAME_FAILED;
    }
}
