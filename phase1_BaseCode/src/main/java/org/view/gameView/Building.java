package org.view.gameView;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controller.GameController;
import org.view.CommandsEnum.GameMessages;
import org.view.GameMenu;
import org.view.LoginMenu;
import org.view.Menu;
import org.view.ShopMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Building extends ImageView {

    private static ArrayList<Building> buildings = new ArrayList<>();
    private int x;
    private int y;
    private String type;
    private boolean added;
    private boolean fixAble;
    private static Building selectedBuilding = null;

    public Building(String type) throws IOException {
        if (ControlBar.buildingImages.get(type) == null) return;
        this.type = type;
        this.setImage(ControlBar.buildingImages.get(type));
        double width = Tile.tileWidth - 10;
        double height = this.getImage().getHeight() / this.getImage().getWidth() * width;
        this.setFitWidth(width);
        this.setFitHeight(height);
        this.setMouseTransparent(true);
        this.setViewOrder(-2);
        added = false;
        fixAble = true;
        buildings.add(this);
    }

    public void movingBuilding(Tile hoveredTile) {
        this.x = hoveredTile.getXCoordinate();
        this.y = hoveredTile.getYCoordinate();
        double xPos = hoveredTile.getTranslateX() + 5;
        double yPos = hoveredTile.getTranslateY() + (double) Tile.tileHeight - this.getFitHeight() - 2;
        this.setTranslateX(xPos);
        this.setTranslateY(yPos);
        if (!added) {
            added = true;
            GameMapView.getCurrentGameMapView().getMap().getChildren().add(this);
        }
    }

    public void fixBuilding() {
        this.setMouseTransparent(false);
        ControlBar.clickedBuilding = null;
        this.setShadow(null);
        clickEvent();
    }

    private void clickEvent() {
        Building thisBuilding = this;
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    if (mouseEvent.getClickCount() >= 2) {
                        GameMessages message = Menu.getGameController().selectBuilding(
                                thisBuilding.getXCoordinate(), thisBuilding.getYCoordinate());
                        if (message.equals(GameMessages.NOT_OWNING_THE_BUILDING))
                            thisBuilding.setShadow(Color.YELLOW);
                        else {
                            thisBuilding.setShadow(Color.WHITE);
                            if (message.equals(GameMessages.ENTER_SHOP_MENU)) {
                                try {
                                    new ShopMenu().start(new Stage());
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            } // todo : enter shop menu
                        }
                    } else {
                        if (selectedBuilding != null) selectedBuilding.setShadow(null);
                        selectedBuilding = thisBuilding;
                        selectedBuilding.setShadow(Color.AQUA);
                    }
                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) unSelectBuilding();
            }
        });
    }

    public static void unSelectBuilding() {
        if (selectedBuilding != null) {
            selectedBuilding.setShadow(null);
            selectedBuilding = null;
        }
    }

    public void setShadow(Color color) {
        if (color == null) this.setEffect(null);
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(color);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        this.setEffect(borderGlow);
    }

    public static void buildingCopy() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(selectedBuilding.getType());
        clipboard.setContent(content);
    }

    public static void buildingPaste() throws IOException {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        String type = (String) clipboard.getContent(DataFormat.PLAIN_TEXT);
        ControlBar.clickedBuilding = new Building(type);
        System.out.println(type);
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getXCoordinate() {
        return x;
    }

    public int getYCoordinate() {
        return y;
    }

    public String getType() {
        return type;
    }

    public boolean isFixAble() {
        return fixAble;
    }

    public void setFixAble(boolean fixAble) {
        this.fixAble = fixAble;
    }

    public static Building getSelectedBuilding() {
        return selectedBuilding;
    }
}
