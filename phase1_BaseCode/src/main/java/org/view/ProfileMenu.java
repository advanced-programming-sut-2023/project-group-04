package org.view;

import java.util.regex.Matcher;

public class ProfileMenu {
    public void run() {
        String input;
        Matcher matcher;

        while (true) {
            input = Menu.getScanner().nextLine();
            if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_USERNAME)) != null)
                System.out.println(Menu.getProfileController().changeUsername(matcher).getMessage());
            else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_NICKNAME)) != null)
                System.out.println(Menu.getProfileController().changeNickname(matcher).getMessage());
            else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_EMAIL)) != null)
                System.out.println(Menu.getProfileController().changeEmail(matcher).getMessage());
            else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_SLOGAN)) != null)
                System.out.println(Menu.getProfileController().changeSlogan(matcher).getMessage());
            else if ((matcher = ProfileCommands.getMatcher(input, ProfileCommands.CHANGE_PASSWORD)) != null)
                System.out.println(Menu.getProfileController().changePassword(matcher).getMessage());
            else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_PROFILE) != null)
                System.out.println(Menu.getProfileController().displayProfile().getMessage());
            else if (ProfileCommands.getMatcher(input, ProfileCommands.REMOVE_SLOGAN) != null)
                System.out.println(Menu.getProfileController().removeSlogan().getMessage());
            else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_RANK) != null)
                System.out.println(Menu.getProfileController().showRank().getMessage());
            else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_SLOGAN) != null)
                System.out.println(Menu.getProfileController().showSlogan().getMessage());
            else if (ProfileCommands.getMatcher(input, ProfileCommands.DISPLAY_HIGHSCORE) != null)
                System.out.println(Menu.getProfileController().highScore().getMessage());
            else if (ProfileCommands.getMatcher(input, ProfileCommands.BACK) != null)
                return;
            else
                System.out.println("Invalid command");
        }

    }
}
