package org.view;

import org.view.CommandsEnum.MapCommands;
import org.view.CommandsEnum.MapMenuCommands;

import java.util.regex.Matcher;

public class MapMenu {
    public void run() {
        String input;
        Matcher matcher;
        while (true) {
            input = Menu.getScanner().nextLine();
            if ((matcher = MapCommands.getMatcher(input, MapCommands.CHANGE_VIEW)) != null) {
                System.out.println(Menu.getMapController().changeMapView(matcher));
            } else if ((matcher = MapCommands.getMatcher(input, MapCommands.SHOW_DETAILS)) != null) {
                System.out.println(Menu.getMapController().showMapDetail(matcher));
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}
