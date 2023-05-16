package org.view.CommandsEnum;

public enum MapMenuMessages {
    INVALID_SIZE("Invalid size for map (size should be 200 or 400)!"),
    MAP_CREATION_SUCCESSFUL("New map created successfully!"),
    MAP_NOT_EXIST("No map with entered id exist!"),
    MAP_SELECT_SUCCESSFUL("Saved map selected successfully!"),
    INCORRECT_COORDINATES("Entered coordinates not exist in map!"),
    INVALID_GROUND_TEXTURE("Invalid ground type!"),
    SET_TEXTURE_SUCCESSFUL("Tile texture changed successfully!"),
    TILE_CLEAR_SUCCESSFUL("Tile texture cleared successfully!"),
    INVALID_DIRECTION("Invalid rock direction!"),
    ROCK_SET_SUCCESSFUL("Rock dropped successfully!"),
    INVALID_TREE_TYPE("Invalid tree type!"),
    SET_TREE_SUCCESSFUL("Tree dropped successfully!"),
    TREE_GROUND_TEXTURE_ERROR("You can't drop tree on this Tile (ground type not match)!"),
    ROCK_GROUND_TEXTURE_ERROR("You can't drop rock on this Tile (ground type not match)!"), 
    INVALID_COLOR("This color is invalid!"), 
    USED_COLOR("Headquarter has been set for this color before!"), 
    USED_TILE("Selected tile is in use!"), 
    HEADQUARTER_SET("Headquarter set successful!"),
    INVALID_SOLDIER_NUMBER("Soldiers count is invalid!"),
    INVALID_SOLDIER_TYPE("Entered Soldier type is invalid!"),
    DROP_UNIT_SUCCESS("Unit dropped successfully!"),
    INVALID_BUILDING_TYPE("Entered building type is invalid!"),
    DROP_BUILDING_SUCCESS("Building dropped successfully!"),
    TREE_EXIST("A tree exist in this block!"),
    MAP_EXIST("A map with entered name already exist!"),
    HEADQUARTER_NOT_SET("There is no headquarter with this color!");

    private final String message;
    private MapMenuMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
