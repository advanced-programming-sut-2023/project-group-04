package org.view;

public enum ProfileMessages {
    //TODO : update the regex of changing functions

    EMPTY_SLOGAN("the slogan is empty!"),
    EMPTY_FIELD("A field is empty!"),
    INCORRECT_USERNAME_FORMAT("username format is incorrect!"),
    CHANGING_USERNAME_FAILED("your username didn't change"),
    CHANGING_PASSWORD_FAILED("your password didn't change"),
    CHANGING_NICKNAME_FAILED("your nickname didn't change"),
    CHANGING_EMAIL_FAILED("your email didn't change"),
    CHANGING_SLOGAN_FAILED("your slogan didn't change"),
    CHANGE_SUCCESSFULLY("Profile updated successfully"),
    PASSWORD_LENGTH_WEAK("The length of the password must be at least <<6>>!"),
    PASSWORD_UPPERCASE_WEAK("The password must have at least one <<UPPERCASE>> letter!"),
    PASSWORD_LOWERCASE_WEAK("The password must have at least one <<lowercase>> letter!"),
    PASSWORD_NUMBER_WEAK("The password must have at least one <<digit>>!"),
    PASSWORD_SPECIFIC_CHAR_WEAK("The password must have at least one <<specific character>>!"),
    PASSWORD_HAVE_SPACE("The password must not contain <<space>>"),
    PASSWORD_CONFIRM_DOES_NOT_MATCH("password confirmation doesn't matches!"),
    EXISTENCE_EMAIL("A user with this Email is Already exists!"),
    INCORRECT_EMAIL_FORMAT("Email format is incorrect!"),
    INVALID_QUESTION_NUMBER("you must enter a number between 1 , 3 !"),
    USER_DOES_NOT_EXIST("Username and password didn't match!"),
    REPEATED_USERNAME("This username is already selected"),
    INCORRECT_PASSWORD("Username and password didn't match!"),
    PASSWORD_CHANGED("your password successfully changed!"),
    CAPTCHA_CORRECT("CAPTCHA is correct"),
    INCORRECT_CURRENT_PASSWORD("Current password is incorrect!"),
    WITHOUT_ERROR("This hasn't any error!");


    private final String message;

    ProfileMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
