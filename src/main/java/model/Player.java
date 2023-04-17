package model;

import java.util.ArrayList;

public class Player {

    public Player(String username, String password,
                  String nickname, String Email,
                  String securityQuestion,
                  String securityAnswer, String slogan) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.Email = Email;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.numberOfAttempts = 0;
        if (slogan != null)
            this.slogan = slogan;
    }

    private static final ArrayList<Player> players = new ArrayList<>();
    private String username;
    private String password;
    private String nickname;
    private String Email;
    private String slogan;
    private String securityQuestion;
    private String securityAnswer;
    private int numberOfAttempts;
    private Player loggedInPlayer;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public boolean isPasswordCorrect(String password) {
        return password.equals(this.password);
    }

    public boolean isSecurityAnswerCorrect(String securityAnswer) {
        return securityAnswer.equals(this.securityAnswer);
    }

    public static Player getPlayerByUsername(String username) {
        for (Player player : players)
            if (player.getUsername().equals(username))
                return player;
        return null;
    }

    public static Player getPlayerByEmail(String Email) {
        for (Player player : players)
            if (player.getEmail().equalsIgnoreCase(Email))
                return player;
        return null;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void setNumberOfAttemptsToZero() {
        this.numberOfAttempts = 0;
    }

    public void increaseNumberOfAttempts() {
        this.numberOfAttempts += 1;
    }

    public void setLoggedInPlayer(Player player) {
        this.loggedInPlayer = player;
    }

    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }
}
