package org.model;

import java.util.ArrayList;

public class Player {

    private static Player loggedInPlayer;
    private static ArrayList<Player> allPlayers;
    private String username;
    private String password;
    private String nickname;
    private String Email;
    private String securityQuestion;
    private String securityAnswer;
    private String slogan;
    private String score;
    private ArrayList<Map> maps;

    public Player(String username, String password, String nickname, String email, String slogan, String score) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        Email = email;
        this.slogan = slogan;
        this.score = score;
        this.maps = new ArrayList<>();
    }

    public static ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public String getUsername() {
        return username;
    }

    public static Player getLoggedInPlayer() {
        return loggedInPlayer;
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

    public String getScore() {
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

    public void setScore(String score) {
        this.score = score;
    }

    public static Player getPlayerByUsername(String username) {
        for (Player player : allPlayers)
            if (player.username.equals(username))
                return player;
        return null;
    }

    public static Player getLoggedInPlayer() {
        return loggedInPlayer;
    }

    public Map getMapById(int id) {
        for (Map map1 : maps)
            if (map1.getId() == id)
                return map1;
        return null;
    }
    public ArrayList<Map> getAllMaps() {
        return this.maps;
    }

    public void addMap(Map map) {
        this.maps.add(map);
    }
}
