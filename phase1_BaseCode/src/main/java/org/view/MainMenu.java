package org.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.view.CommandsEnum.GameMessages;
import org.view.CommandsEnum.MainMenuCommands;

import java.util.regex.Matcher;

public class MainMenu extends Application {
    public MainMenu() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(MainMenu.class.getResource("/fxml/mainMenu.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void run() throws Exception {
        String input;
        Matcher matcher;
        while (true) {
            input = Menu.getScanner().nextLine();
            if (MainMenuCommands.getMatcher(input, MainMenuCommands.LOGOUT) != null) {
                Menu.getLoginController().clearStayLogin();
                System.out.println("user logged out successfully!");
                return;
            } else if ((MainMenuCommands.getMatcher(input, MainMenuCommands.ENTER_PROFILE_MENU)) != null) {
                System.out.println("you successfully entered \"profile menu!\"");
                new ProfileMenu().run();
            } else if ((matcher = MainMenuCommands.getMatcher(input, MainMenuCommands.START_GAME)) != null) {
                GameMessages messages = Menu.getGameController().startGame(matcher);
                System.out.println(messages.getMessage());
                if (messages.equals(GameMessages.GAME_STARTED)) new GameMenu().run();
            } else if (MainMenuCommands.getMatcher(input, MainMenuCommands.ENTER_ENVIRONMENT_MENU) != null) {
                System.out.println("you successfully entered \"environment menu\"");
                new EnvironmentMenu().run();
            } else {
                System.out.println("invalid command!");
            }
        }
    }
}