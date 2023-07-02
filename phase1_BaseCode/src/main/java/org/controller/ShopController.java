package org.controller;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.model.Empire;
import org.model.Game;
import org.model.ResourcesDictionary;
import org.view.CommandsEnum.ShopMessages;
import org.view.ShopMenu;
import org.view.TradeMenu;

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
    public ImageView buyWheat;
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

    public ShopMessages buyThing(String itemsName, int itemsAmount) {
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        int freeSpace = currentEmpire.getFreeSpaceByResourceName(itemsName);
        if (freeSpace < itemsAmount)
            return ShopMessages.LACK_OF_FREE_SPACE;
        int price = ResourcesDictionary.getDictionaryByName(itemsName).getPrice() * itemsAmount;
        if (price > currentEmpire.getResources().get("gold"))
            return ShopMessages.LACK_OF_MONEY;
        currentEmpire.changeResourceAmount(itemsName, itemsAmount);
        currentEmpire.changeResourceAmount("gold", -1 * price);
        return ShopMessages.BUY_SUCCESSFULLY;
    }

    public ShopMessages sellThing(String itemsName, int itemsAmount) {
        Empire currentEmpire = Game.getCurrentGame().getCurrentEmpire();
        if (currentEmpire.getAvailableResource(itemsName) < itemsAmount)
            return ShopMessages.LACK_OF_PRODUCT;
        int price = ResourcesDictionary.getDictionaryByName(itemsName).getPrice() * itemsAmount;
        currentEmpire.changeResourceAmount(itemsName, -1 * itemsAmount);
        currentEmpire.changeResourceAmount("gold", price);
        return ShopMessages.SELL_SUCCESSFULLY;
    }

    public void openFoodShop(MouseEvent mouseEvent) {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        allItems.setVisible(false);
        mainBack.setVisible(false);
        foodsGrid.setVisible(true);
        appleStock.setText("" + empire.getFood().get("apple"));
        meatStock.setText("" + empire.getFood().get("meat"));
        cheeseStock.setText("" + empire.getFood().get("cheese"));
        hopsStock.setText("" + empire.getResources().get("hops"));
        aleStock.setText("" + empire.getResources().get("ale"));
        wheatStock.setText("" + empire.getResources().get("wheat"));
        flourStock.setText("" + empire.getResources().get("flour"));
        breadStock.setText("" + empire.getFood().get("bread"));
    }


    public void openMaterialsShop(MouseEvent mouseEvent) {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        allItems.setVisible(false);
        mainBack.setVisible(false);
        rawMaterialsGrid.setVisible(true);
        woodStock.setText("" + empire.getResources().get("wood"));
        ironStock.setText("" + empire.getResources().get("iron"));
        pitchStock.setText("" + empire.getResources().get("pitch"));
        stoneStock.setText("" + empire.getResources().get("stone"));

    }

    public void openWeaponsShop(MouseEvent mouseEvent) {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        allItems.setVisible(false);
        mainBack.setVisible(false);
        weaponsGrid.setVisible(true);
        spearStock.setText("" + empire.getWeaponAndArmour().get("spear"));
        bowStock.setText("" + empire.getWeaponAndArmour().get("bow"));
        crossbowStock.setText("" + empire.getWeaponAndArmour().get("crossbow"));
        maceStock.setText("" + empire.getWeaponAndArmour().get("mace"));
        leatherArmorStock.setText("" + empire.getWeaponAndArmour().get("leatherArmour"));
        pikeStock.setText("" + empire.getWeaponAndArmour().get("pike"));
        swordStock.setText("" + empire.getWeaponAndArmour().get("sword"));
        metalArmorStock.setText("" + empire.getWeaponAndArmour().get("metalArmour"));
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

    private void shopOperation() {
        buyApple.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("apple", Integer.parseInt(appleNumber.getText()));
            }
        });
        buyMeat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("meat", Integer.parseInt(meatNumber.getText()));
            }
        });
        buyCheese.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("cheese", Integer.parseInt(cheeseNumber.getText()));
            }
        });
        buyhops.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("hops", Integer.parseInt(hopsNumber.getText()));
            }
        });
        buyAle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("ale", Integer.parseInt(aleNumber.getText()));
            }
        });
        buyWheat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("wheat", Integer.parseInt(wheatNumber.getText()));
            }
        });
        buyFlour.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("flour", Integer.parseInt(flourNumber.getText()));
            }
        });
        buyBread.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("bread", Integer.parseInt(breadNumber.getText()));
            }
        });
        buyWood.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("wood", Integer.parseInt(woodNumber.getText()));
            }
        });
        buyStone.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("stone", Integer.parseInt(stoneNumber.getText()));
            }
        });
        buyIron.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("iron", Integer.parseInt(ironNumber.getText()));
            }
        });
        buyPitch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("pitch", Integer.parseInt(pitchNumber.getText()));
            }
        });
        buySpear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("spear", Integer.parseInt(spearNumber.getText()));
            }
        });
        buyBow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("bow", Integer.parseInt(bowNumber.getText()));
            }
        });
        buyCrossbow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("crossbow", Integer.parseInt(crossbowNumber.getText()));
            }
        });
        buyMace.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("mace", Integer.parseInt(maceNumber.getText()));
            }
        });
        buyLeather.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("leather armour", Integer.parseInt(leatherArmorNumber.getText()));
            }
        });
        buyPike.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("pike", Integer.parseInt(pikeNumber.getText()));
            }
        });
        buySword.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("sword", Integer.parseInt(swordNumber.getText()));
            }
        });
        buyMetal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ShopMessages message = buyThing("metal armour", Integer.parseInt(metalArmorNumber.getText()));
            }
        });


    }

    private void sellOperation() {
    }
}
