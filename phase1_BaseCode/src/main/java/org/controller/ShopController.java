package org.controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.model.Empire;
import org.model.Game;
import org.model.ResourcesDictionary;
import org.view.CommandsEnum.ShopMessages;

import javafx.scene.image.*;
import org.view.LoginMenu;
import org.view.ShopMenu;
import org.view.TradeMenu;

import java.awt.*;
import java.util.regex.Matcher;

public class ShopController {

    public ImageView food;
    public ImageView rawMaterials;
    public ImageView weapons;
    public ImageView mainBack;
    public GridPane foodsGrid;
    public GridPane rawMaterialsGrid;
    public GridPane weaponsGrid;
    public HBox allItems;

    public String showList() {
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        String list = "YOUR RESOURCES:";
        for (ResourcesDictionary resourcesDictionary : ResourcesDictionary.values()) {
            list += "\nresource name : " + resourcesDictionary.getName();
            list += "\namount : " + currentEmpire.getAvailableResource(resourcesDictionary.getName());
            list += "\nbuy price : " + resourcesDictionary.getPrice();
            list += "\nsell price : " + (resourcesDictionary.getPrice() / 3) * 2;
            list += "\n------------------";
        }
        return list;
    }

    public ShopMessages buyThing(Matcher matcher) {
        String itemsName = removeQuotation(matcher.group("itemsName"));
        int itemsAmount = Integer.parseInt(matcher.group("itemsAmount"));
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        int price = ResourcesDictionary.getDictionaryByName(itemsName).getPrice() * itemsAmount;
        currentEmpire.changeResourceAmount(itemsName, itemsAmount);
        currentEmpire.changeResourceAmount("gold", -1 * price);
        return ShopMessages.BUY_SUCCESSFULLY;
    }

    public ShopMessages sellThing(Matcher matcher) {
        String itemsName = removeQuotation(matcher.group("itemsName"));
        int itemsAmount = Integer.parseInt(matcher.group("itemsAmount"));
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        int price = ResourcesDictionary.getDictionaryByName(itemsName).getPrice() * itemsAmount;
        currentEmpire.changeResourceAmount(itemsName, -1 * itemsAmount);
        return ShopMessages.SELL_SUCCESSFULLY;
    }

    private String removeQuotation(String buffer) {
        return buffer.replaceAll("\"", "");
    }


    public ShopMessages checkForBuy(Matcher matcher) {
        String itemsName = removeQuotation(matcher.group("itemsName"));
        int itemsAmount = Integer.parseInt(matcher.group("itemsAmount"));
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        if (ResourcesDictionary.getDictionaryByName(itemsName) == null)
            return ShopMessages.WRONG_NAME_PRODUCT;
        int freeSpace = currentEmpire.getFreeSpaceByResourceName(itemsName);
        if (freeSpace < itemsAmount)
            return ShopMessages.LACK_OF_FREE_SPACE;
        int price = ResourcesDictionary.getDictionaryByName(itemsName).getPrice() * itemsAmount;
        if (price > currentEmpire.getResources().get("gold"))
            return ShopMessages.LACK_OF_MONEY;
        return ShopMessages.CERTAINTY;
    }

    public ShopMessages checkForSell(Matcher matcher) {
        String itemsName = removeQuotation(matcher.group("itemsName"));
        int itemsAmount = Integer.parseInt(matcher.group("itemsAmount"));
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        if (ResourcesDictionary.getDictionaryByName(itemsName) == null)
            return ShopMessages.WRONG_NAME_PRODUCT;
        if (currentEmpire.getAvailableResource(itemsName) < itemsAmount)
            return ShopMessages.LACK_OF_PRODUCT;
        return ShopMessages.CERTAINTY;
    }

    public void openFoodShop(MouseEvent mouseEvent) {
        allItems.setVisible(false);
        mainBack.setVisible(false);
        foodsGrid.setVisible(true);
    }

    public void openMaterialsShop(MouseEvent mouseEvent) {
        allItems.setVisible(false);
        mainBack.setVisible(false);
        rawMaterialsGrid.setVisible(true);
    }

    public void openWeaponsShop(MouseEvent mouseEvent) {
        allItems.setVisible(false);
        mainBack.setVisible(false);
        weaponsGrid.setVisible(true);
    }

    public void back(MouseEvent mouseEvent) {
        foodsGrid.setVisible(false);
        weaponsGrid.setVisible(false);
        rawMaterialsGrid.setVisible(false);
        allItems.setVisible(true);
        mainBack.setVisible(true);
    }

    public void enterTradeMenu(MouseEvent mouseEvent) throws Exception {
        new TradeMenu().start(ShopMenu.stage);
    }
}
