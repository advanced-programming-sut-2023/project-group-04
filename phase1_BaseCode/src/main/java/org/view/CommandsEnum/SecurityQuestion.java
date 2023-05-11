package org.view.CommandsEnum;

public enum SecurityQuestion {
    PET("What was my first pet’s name?"),
    TEACHER("What is the name of my first teacher?"),
    CAR("What is the name of my favorite car?");

    private String question;
    private SecurityQuestion(String question) {
        this.question = question;
    }

    public static String getQuestion(SecurityQuestion securityQuestion) {
        return securityQuestion.question;
    }
}
