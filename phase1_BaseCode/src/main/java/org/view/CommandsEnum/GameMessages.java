package org.view.CommandsEnum;

public enum GameMessages {
    INVALID_POSITION("Position is invalid!"),
    UNSUITABLE("Ground texture isn't suitable for drop this building!"),
    EXISTENCE_BUILDING("A building already exist in this coordinates!"),
    BUILDING_NOT_EXIST("There isn't any building in this coordinates!"),
    NOT_OWNING_THE_BUILDING("You don't own this building!"),
    NOT_ENOUGH_RESOURCE("Your resource isn't enough!"),
    NOT_ENOUGH_PEOPLE("Your population isn't enough!"),
    INVALID_SOLDIER_TYPE("You cannot build this soldier in this building!"),
    BEING_CLOSE_TO_BUILDING("The opponent soldiers is so close to the building!"),
    INVALID_MOVE("You can't move unit to that ground!"),
    INVALID_COMMUTING("You can't commute in this area!"),
    
    ;

    private final String message;

    private GameMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}