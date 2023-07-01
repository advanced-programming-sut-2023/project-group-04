package org.controller;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
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
    public Text appleStock;
    public Text meatStock;
    public Text cheeseStock;
    public Text hopsStock;
    public Text aleStock;
    public Text wheatStock;
    public Text flourStock;
    public Text breadStock;
    public TextField appleNumber;
    public TextField meatNumber;
    public TextField cheeseNumber;
    public TextField hopsNumber;
    public TextField aleNumber;
    public TextField wheatNumber;
    public TextField flourNumber;
    public TextField breadNumber;
    public ImageView buyApple;
    public ImageView buyMeat;
    public ImageView buyCheese;
    public ImageView buyhops;
    public ImageView buyAle;
    public ImageView buywheat;
    public ImageView buyFlour;
    public ImageView buyBread;
    public ImageView sellApple;
    public ImageView sellMeat;
    public ImageView sellCheese;
    public ImageView sellHops;
    public ImageView sellAle;
    public ImageView sellWheat;
    public ImageView sellFlour;
    public ImageView sellBread;
    public Text woodStock;
    public Text stoneStock;
    public Text ironStock;
    public Text pitchStock;
    public TextField woodNumber;
    public TextField stoneNumber;
    public TextField ironNumber;
    public TextField pitchNumber;
    public ImageView buyWood;
    public ImageView buyStone;
    public ImageView buyIron;
    public ImageView buyPitch;
    public ImageView sellWood;
    public ImageView sellIron;
    public ImageView sellStone;
    public ImageView sellPitch;
    public Text spearStock;
    public Text bowStock;
    public Text crossbowStock;
    public Text maceStock;
    public Text leatherArmorStock;
    public Text pikeStock;
    public Text swordStock;
    public Text metalArmorStock;
    public TextField spearNumber;
    public TextField bowNumber;
    public TextField crossbowNumber;
    public TextField maceNumber;
    public TextField leatherArmorNumber;
    public TextField pikeNumber;
    public TextField swordNumber;
    public TextField metalArmorNumber;
    public ImageView buySpear;
    public ImageView buyBow;
    public ImageView buyCrossbow;
    public ImageView buyMace;
    public ImageView buyLeather;
    public ImageView buyPike;
    public ImageView buySword;
    public ImageView buyMetal;
    public ImageView sellSpear;
    public ImageView sellBow;
    public ImageView sellCrossbow;
    public ImageView sellMace;
    public ImageView sellLeather;
    public ImageView sellPike;
    public ImageView sellSword;
    public ImageView sellMetal;


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
