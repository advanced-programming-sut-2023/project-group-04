package org.view.gameView;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.controller.GameController;
import org.view.CommandsEnum.GameMessages;
import org.view.Menu;

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

    public Building(String type) throws IOException {
        this.type = type;
        this.setImage(ControlBar.buildingImages.get(type));
        double width = Tile.tileWidth - 10;
        double height = this.getImage().getHeight() / this.getImage().getWidth() * width;
        this.setFitWidth(width);
        this.setFitHeight(height);
        this.setMouseTransparent(true);
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
//                GameMessages message = Menu.getGameController().selectBuilding(
//                        thisBuilding.getXCoordinate(), thisBuilding.getYCoordinate());

//                if (message.equals(GameMessages.NOT_OWNING_THE_BUILDING))
                    thisBuilding.setShadow(Color.YELLOW);
                }
            }
        });
    }

    public void setShadow(Color color) {
        if (color == null) this.setEffect(null);
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(color);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        this.setEffect(borderGlow);
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
}
