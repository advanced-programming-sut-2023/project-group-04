package org.view.CommandsEnum;

public enum GameMessages {
    INVALID_POSITION("Position is invalid!"),
    UNSUITABLE("Ground texture isn't suitable for drop this building!"),
    EMPTY_FIELD("A field is empty!"),
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
    INVALID_TEXTURE_TREE("Building can't be build(invalid texture or tree in selected tile)!"),
    MAP_NOT_EXIST("No map with entered id exist!"),
    USER_NOT_EXIST("Any user with this username doesn't exist!"),
    NOT_ENOUGH_HEAD_QUARTER("Head quarters not enough for this players!"),
    GAME_STARTED("Game successfully started!"),
    INVALID_BUILDING_FOR_CREATE("invalid building type for creating unit!"),
    INVALID_UNIT_TYPE("Invalid unit type!"),
    INVALID_TROOP_COUNT("invalid troop count!"),
    NOT_ENOUGH_GOLD("Your gold isn't enough!"),
    SUCCESSFUL_CREATE_UNIT("Create unit was successful!"),
    NOT_ENOUGH_WEAPON("Your weapon and armour isn't enough!"),
    NOT_ENOUGH_POPULATION("Your population isn't enough for creating unit!"),
    TROOP_NOT_EXIST("Not exist any troop with this type!"),
    SUCCESSFUL_SELECT_UNIT("unit selected successfully!"),
    EMPTY_UNIT_SELECT("No unit have been selected!"),
    SUCCESSFUL_TROOP_MOVE("Moving troop was successful!"),
    SUCCESSFUL_PATROL_UNIT("Patrolling unit was successful!"),
    INVALID_MOVE_NAME("invalid mode name"),
    SUCCESSFUL_SET_MODE("Setting mode was successfully!"),
    NOT_SOLDIER("Selected unit is not soldier!"),
    FEW_RANGE("Selected soldiers haven't long range!"),
    AIM_OUT_OF_RANGE("Aim out of range!"),
    SUCCESSFUL_ATTACK("Attacking was successful!"),
    NOT_ENGINEER("Selected unit isn't engineer!"),
    INVALID_DIRECTION("Invalid direction!"),
    SUCCESSFUL_POUR("Pouring oil was successful!"),
    DOES_NOT_HAVE_OIL("Selected engineer doesn't have oil!"),
    NOT_TUNNELER("Selected unit isn't tunneler!"),
    SET_TUNNEL_SUCCESSFUL("Setting tunnel for tunneler was successful!"),
    SUCCESSFUL_DISBAND("Unit disbanded!"),
    NO_ENEMY("No enemy or building in target tile!"),
    INCORRECT_OUTPUT("This building can't produce selected good!"),
    SET_OUTPUT_SUCCESSFUL("Selected object set successful!"),
    TAX_NOT_ACTIVE("You can't change tax rate yet!"),
    ENTER_SHOP_MENU("Entered shop menu successfully!"),
    NO_WAY("there isn't any way to selected entered destination!"),
    NO_SELECTED_BUILDING("You didn't select any building");

    private final String message;

    private GameMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}