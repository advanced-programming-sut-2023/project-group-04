package org.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.model.map.Map;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;

public class Player {

    private static Player currentPlayer;
    private static ArrayList<Player> allPlayers = new ArrayList<>();
    private String username;
    private String password;
    private String nickname;
    private String Email;
    private String securityQuestion;
    private String securityAnswer;
    private String slogan;
    private String avatarResource;
    private int score;
    private static int numberOfAttempts = 0;

    public Player(String username, String password, String nickname, String email, String slogan) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        Email = email;
        this.slogan = slogan;
        this.score = 0;
        this.avatarResource = null;
    }

    public static void setCurrentPlayer(Player player) {
        Player.currentPlayer = player;
    }

    public static ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public static Object getPlayerByEmail(String email) {
        for (Player player : Player.allPlayers)
            if (player.getEmail().equals(email)) return player;
        return null;
    }

    public static void recoveryPlayers() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("PLAYERS.json"));
            ArrayList<Player> allPlayers = new ArrayList<>();
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
            if (jsonArray != null) {
                for (JsonElement jsonElement : jsonArray) {
                    allPlayers.add(gson.fromJson(jsonElement, Player.class));
                }
                Player.allPlayers = allPlayers;
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void savePlayers() {
        Gson gson = new Gson();
        String data = gson.toJson(Player.getAllPlayers());
        try {
            FileWriter output = new FileWriter("PLAYERS.json");
            output.write(data);
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void setStayLogin() {
        Gson gson = new Gson();
        String data = gson.toJson(this);
        try {
            FileWriter output = new FileWriter("STAY_LOGIN.json");
            output.write(data);
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static Player getStayLogin() {
        Player player = null;
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("STAY_LOGIN.json"));
            player = gson.fromJson(reader, Player.class);
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return player;
    }

    public static void removeStayLogin() {
        try {
            FileWriter output = new FileWriter("STAY_LOGIN.json");
            output.write("");
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return Email;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public boolean isSecurityAnswerCorrect(String answer) {
        return this.securityAnswer.equals(answer);
    }

    public String getSlogan() {
        return slogan;
    }

    public int getScore() {
        return score;
    }

    public static void addPlayer(Player player) {
        Player.allPlayers.add(player);
    }

    public static void removePlayer(Player player) {
        Player.allPlayers.remove(player);
    }

    public static void removePlayer(int index) {
        Player.allPlayers.remove(index);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public static Player getPlayerByUsername(String username) {
        for (Player player : allPlayers)
            if (player.username.equals(username))
                return player;
        return null;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void increaseNumberOfAttempts() {
        numberOfAttempts++;
    }

    public static int getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public static void resetNumberOfAttempts() {
        numberOfAttempts = 0;
    }

    public static String getSHA256Hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not available.");
            return null;
        }
    }

    public String getAvatarResource() {
        return avatarResource;
    }

    public void setAvatarResource(String avatarResource) {
        this.avatarResource = avatarResource;
    }

    public static ArrayList<Player> getSortedPlayers() {
        ArrayList<Player> players = allPlayers;
        for (int i = 0; i < players.size() - 1; i++)
            for (int j = i + 1; j < players.size(); j++)
                if (players.get(i).getScore() < players.get(j).getScore())
                    Collections.swap(players, i, j);
        return players;
    }
}
