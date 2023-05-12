package org.view.CommandsEnum;

public enum GameMessages {
    INVALID_POSITION("Position is invalid!"),
    UNSUITABLE("Ground texture isn't suitable for drop this building!"),
    INVALID_FOOD_RATE("Please enter a <integer> number between [-2,2]!"),
    INVALID_TAX_RATE("Please enter a <integer> number between [-3,8]!"),
    INVALID_FEAR_RATE("Please enter a <integer> number between [-5,5]!"),
    EXISTENCE_BUILDING("A building already exist in this coordinates!"),
    BUILDING_NOT_EXIST("There isn't any building in this coordinates!"),
    NOT_OWNING_THE_BUILDING("You don't own this building!"),
    NOT_ENOUGH_RESOURCE("Your resource isn't enough!"),
    NOT_ENOUGH_PEOPLE("Your population isn't enough!"),
    INVALID_SOLDIER_TYPE("You cannot build this soldier in this building!"),
    BEING_CLOSE_TO_BUILDING("The opponent soldiers is so close to the building!"),
    INVALID_MOVE("You can't move unit to that ground!"),
    INVALID_COMMUTING("You can't commute in this area!"),
    SET_SUCCESSFUL("Setting building was successful!"),
    CAN_NOT_REPAIR("You can't repair this building!"),
    CHANGE_FOOD_RATE("Food rate changed successfully!"),
    CHANGE_TAX_RATE("Tax rate changed successfully!"),
    CHANGE_FEAR_RATE("Fear rate changed successfully!"),
    INCORRECT_BUILDING_TYPE("Building type is incorrect!"),
    SUCCESSFUL_DROP("building dropped successfully!"),
    SUCCESSFUL_REPAIR("Repairing was successfully!"),
    INVALID_DRAWBRIDGE_POSITION("Draw bridge could only be placed near gates!"),
    INVALID_TEXTURE_TREE("Building can't be build(invalid texture or tree in selected tile)!");

    private final String message;

    private GameMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}