package org.view;

import org.view.CommandsEnum.MapMenuCommands;
import org.view.CommandsEnum.MapMenuMessages;

import java.util.regex.Matcher;

public class EnvironmentMenu {
    public void run() {
        System.out.println("You can change map environment now!");
        if (!getWorkingMap()) {
            System.out.println("You are in main menu now!");
            return;
        }
        while (true) {
            String command = Menu.getScanner().nextLine();
            Matcher matcher;
            if ((matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.SET_CELL_TEXTURE)) != null)
                System.out.println(Menu.getEnvironmentController().changeBlockTexture(matcher).getMessage());
            else if ((matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.SET_AREA_TEXTURE)) != null)
                System.out.println(Menu.getEnvironmentController().changeAreaTexture(matcher).getMessage());
            else if ((matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.CLEAR_MAP_CELL)) != null)
                System.out.println(Menu.getEnvironmentController().clearBlock(matcher).getMessage());
            else if ((matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.DROP_ROCK)) != null)
                System.out.println(Menu.getEnvironmentController().setRock(matcher).getMessage());
            else if ((matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.DROP_TREE)) != null)
                System.out.println(Menu.getEnvironmentController().setTree(matcher).getMessage());
            else if ((matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.SET_HEADQUARTER)) != null)
                System.out.println(Menu.getEnvironmentController().setHeadQuarter(matcher).getMessage());
            else if ((matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.DROP_UNIT)) != null)
                System.out.println(Menu.getEnvironmentController().dropUnit(matcher).getMessage());
            else if ((matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.DROP_BUILDING)) != null)
                System.out.println(Menu.getEnvironmentController().dropBuilding(matcher).getMessage());
            else if (MapMenuCommands.getMatcher(command, MapMenuCommands.BACK) != null) {
                Menu.getEnvironmentController().save();
                System.out.println("You are in main menu now!");
                return;
            }else System.out.println("Invalid command");
        }
    }

    private boolean getWorkingMap() {
        System.out.println("crate new map or custom an exiting map?");
        while (true) {
            String command = Menu.getScanner().nextLine();
            Matcher matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.CREATE_NEW_MAP);
            if (matcher != null) {
                MapMenuMessages mapMenuMessage = Menu.getEnvironmentController().createMap(matcher);
                System.out.println(mapMenuMessage.getMessage());
                if (mapMenuMessage.equals(MapMenuMessages.MAP_CREATION_SUCCESSFUL)) return true;
            } else if ((matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.CUSTOM_EXITING_MAP)) != null) {
                MapMenuMessages mapMenuMessage = Menu.getEnvironmentController().customExistingMap(matcher);
                System.out.println(mapMenuMessage.getMessage());
                if (mapMenuMessage.equals(MapMenuMessages.MAP_SELECT_SUCCESSFUL)) return true;
            } else if (MapMenuCommands.getMatcher(command, MapMenuCommands.BACK) != null) return false;
            else System.out.println("Invalid command");
        }
    }

}
