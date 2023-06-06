package org.view.CommandsEnum;

public enum SignUpMessages {
    EMPTY_SLOGAN("The slogan is empty!"),
    EMPTY_FIELD("A field is empty!"),
    INCORRECT_USERNAME_FORMAT("Username format is incorrect!"),
    REGISTRATION_FAILED("Registration failed!"),
    REGISTRATION_SUCCESSFUL("Registration successful!"),
    PASSWORD_LENGTH_WEAK("The length of the password must be at least <<6>>!"),
    PASSWORD_UPPERCASE_WEAK("The password must have at least one <<UPPERCASE>> letter!"),
    PASSWORD_LOWERCASE_WEAK("The password must have at least one <<lowercase>> letter!"),
    PASSWORD_NUMBER_WEAK("The password must have at least one <<digit>>!"),
    PASSWORD_SPECIFIC_CHAR_WEAK("The password must have at least one <<specific character>>!"),
    PASSWORD_STRONG("Password is strong:)"),
    PASSWORD_CONFIRM_DOES_NOT_MATCH("Password confirmation doesn't matches!"),
    EXISTENCE_EMAIL("A user with this Email is Already exists!"),
    INCORRECT_EMAIL_FORMAT("Email format is incorrect!"),
    INVALID_QUESTION_NUMBER("You must enter a number between [1,3]!"),
    ANSWER_CONFIRM_DOES_NOT_MATCH("The security answer confirmation doesn't matches!"),
    SET_QUESTION_SUCCESSFUL("Set question was successful!"),
    LOGIN_SUCCESSFUL("User logged in successfully!"),
    USER_DOES_NOT_EXIST("Username and password didn’t match!"),
    INCORRECT_PASSWORD("Username and password didn’t match!"),
    ANSWER_DOES_NOT_MATCH("Security answer is incorrect!"),
    PASSWORD_CHANGED("Your password successfully changed!"),
    CAPTCHA_CORRECT("CAPTCHA is correct"),
    WITHOUT_ERROR("Password haven't any error!"),
    PASSWORD_INCORRECT_FORMAT("Password format is incorrect!"),
    USER_NOT_FOUND("User not found!"),
    USER_EXIST("A user with this username already exists!"),
    CAPTCHA_IS_WRONG("captcha is wrong!");

    private final String message;

    private SignUpMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
