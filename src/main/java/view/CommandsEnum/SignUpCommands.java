package view.CommandsEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignUpCommands {
    CREATE_ACCOUNT("user create -u( (?<username>[^\"\\s]+ |\"[^\"]+\" )?)-p" +
            "( (?<password>random |\\S+ )?)((?<passwordConfirm>\\S+ ))?-email" +
            "( (?<Email>\\S+ )?)-n( ?(?<nickName>[^\"\\s]+ ?|\"[^\"]+\" ?)?" +
            "(?<sloganCommand>-s)?)( (?<slogan>[^\"\\s]+|\"[^\"]+\"))?"),
    PICK_SECURITY_QUESTION("question pick -q (?<questionNumber>\\d+) -a " +
            "(?<answer>[^\\s\"]+|\"[^\"]+\") -c (?<answerConfirm>[^\\s\"]+|\"[^\"]+\")"),
    LOGIN_USER("user login -u (?<username>[^\\s\"]+|\"[^\"]+\") -p (?<password>\\S+)(?<status> --stay-logged-in)?"),
    FORGET_PASSWORD("forgot my password -u (?<username>[^\\s\"]+|\"[^\"]+\")"),
    LOGOUT("user logout"),
    I_DONT_HAVE_ACCOUNT("i dont have an account!")
    ;


    private final String regex;

    private SignUpCommands(String regex) {
        this.regex = regex;
    }


    public static Matcher getMatcher(String input, SignUpCommands signUpCommands) {
        Matcher matcher = Pattern.compile(signUpCommands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
