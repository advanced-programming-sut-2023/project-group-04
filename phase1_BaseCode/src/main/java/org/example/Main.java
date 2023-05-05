package org.example;

import com.google.gson.Gson;
import org.model.Player;
import org.view.LoginMenu;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
//        Player.recoveryPlayers();
        new LoginMenu().run();
    }
}