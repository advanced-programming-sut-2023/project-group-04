package org.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ChatController {
    public HBox chatType;
    public GridPane privatePlayers;
    public Text user1;
    public Text user2;
    public Text user3;
    public GridPane roomPlayers;
    public CheckBox chooseRoom1;
    public CheckBox chooseRoom2;
    public CheckBox chooseRoom3;
    public Text roomUser1;
    public Text roomUser2;
    public Text roomUser3;

    public void hideChatType(MouseEvent mouseEvent) {
        chatType.setVisible(false);
    }

    public void showPlayersList(MouseEvent mouseEvent) {
        chatType.setVisible(false);
        privatePlayers.setVisible(true);
    }

    public void chooseReceiver(MouseEvent mouseEvent) {
        Player receiver;
        if (mouseEvent.getSource().equals("choosePlayer1"))
            receiver = Player.getPlayerByUsername(user1.getText());
        else if (mouseEvent.getSource().equals("choosePlayer2"))
            receiver = Player.getPlayerByUsername(user2.getText());
        else
            receiver = Player.getPlayerByUsername(user3.getText());
        startPrivateChat();
    }

    private void startPrivateChat() {
        // TODO: 7/3/2023 Fill this!
    }

    public void showRoomPlayers(MouseEvent mouseEvent) {
        roomPlayers.setVisible(true);
        chatType.setVisible(false);
    }

    public void startRoom(MouseEvent mouseEvent) {
        ArrayList<Player> players = new ArrayList<>();
        if (chooseRoom1.isSelected())
            players.add(Player.getPlayerByUsername(roomUser1.getText()));
        if (chooseRoom2.isSelected())
            players.add(Player.getPlayerByUsername(roomUser2.getText()));
        if (chooseRoom3.isSelected())
            players.add(Player.getPlayerByUsername(roomUser3.getText()));
        startRoomChat();
    }

    private void startRoomChat() {
        // TODO: 7/3/2023 Fill this!
    }
}