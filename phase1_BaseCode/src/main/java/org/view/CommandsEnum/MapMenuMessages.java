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
    ROCK_GROUND_TEXTURE_ERROR("You can't drop rock on this Tile (ground type not match)!");

    private String message;
    private MapMenuMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
