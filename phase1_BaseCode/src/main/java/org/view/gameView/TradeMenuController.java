package org.view.gameView;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.model.Empire;
import org.model.Game;
import org.model.ResourcesDictionary;
import org.model.*;
import org.view.CommandsEnum.TradeMessages;
import org.view.GameMenu;
import org.view.Menu;
import org.view.ShopMenu;
import org.view.TradeMenu;

import java.util.ArrayList;
import java.util.HashMap;

public class TradeMenuController {
    public Button create;
    public Button previous;
    public ImageView mainBack;
    public ImageView back1;
    public ImageView back11;
    public GridPane playersList;
    public GridPane resourcesGrid;
    public HBox amount;
    public HBox amount1;
    public AnchorPane newTrade;
    public ImageView selectedResource;
    public Button submitted;
    public Button received;
    public GridPane submittedOffers;
    public GridPane receivedOffers;
    public ImageView accept1;
    public ImageView accept2;
    public ImageView accept3;
    public ImageView accept4;
    public ImageView reject1;
    public ImageView reject2;
    public ImageView reject3;
    public ImageView reject4;
    public Text player1;
    public Text player5;
    public Text player6;
    public Text player2;
    public Text player3;
    public Text player4;
    public Text player7;
    public Empire tradeGetter;
    public String resource = null;
    public Text crossbowNumber;
    public Text maceNumber;
    public Text leatherNumber;
    public Text metalNumber;
    public Text pikeNumber;
    public Text spearNumber;
    public Text swordNumber;
    public Text ironNumber;
    public Text stoneNumber;
    public Text woodNumber;
    public Text appleNumber;
    public Text breadNumber;
    public Text cheeseNumber;
    public Text flourNumber;
    public Text hopsNumber;
    public Text meatNumber;
    public Text pitchNumber;
    public Text wheatNumber;
    public Text aleNumber;
    public Text bowNumber;
    public Button request;
    public Button donate;
    public Button submit;
    public ImageView apple;
    public ImageView bread;
    public ImageView cheese;
    public ImageView flour;
    public ImageView hops;
    public ImageView meat;
    public ImageView pitch;
    public ImageView wheat;
    public ImageView ale;
    public ImageView bow;
    public ImageView crossbow;
    public ImageView leatherArmour;
    public ImageView mace;
    public ImageView metalArmour;
    public ImageView pike;
    public ImageView spear;
    public ImageView sword;
    public ImageView iron;
    public ImageView stone;
    public ImageView wood;
    public int price;
    public int resourceNumber;
    public TextField itemNumber;
    public TextArea tradeMessage;
    public String senderMessage;
    public Text id4;
    public Text id3;
    public Text id2;
    public Text id1;

    /////////////
    public Text resource11;
    public Text amount11;
    public Text user11;
    public Text status11;
    public Text status22;
    public Text status33;
    public Text status44;
    public Text resource22;
    public Text resource33;
    public Text resource44;
    public Text amount22;
    public Text amount33;
    public Text amount44;
    public Text user22;
    public Text user33;
    public Text user44;
    public TextArea message11;
    public TextArea message22;
    public TextArea message33;
    public TextArea message44;


    @FXML
    public void initialize() {
        Group acAndRe = new Group(accept1, accept2, accept3, accept4, reject1, reject2, reject3, reject4);
        ArrayList<Empire> opponentEmpires = Game.getCurrentGame().getOpponentEmpires();
        if (opponentEmpires != null && opponentEmpires.size() != 0) {
            if (opponentEmpires.size() < 4)
                player4.setText("empty");
            else player4.setText(opponentEmpires.get(3).getOwner().getUsername());
            if (opponentEmpires.size() < 3)
                player3.setText("empty");
            else player3.setText(opponentEmpires.get(2).getOwner().getUsername());
            if (opponentEmpires.size() < 2)
                player2.setText("empty");
            else player2.setText(opponentEmpires.get(1).getOwner().getUsername());
            player1.setText(opponentEmpires.get(0).getOwner().getUsername());
        }
    }

    public void enterShopMenu(MouseEvent mouseEvent) throws Exception {
        new ShopMenu().start(TradeMenu.Stage);
    }

    public void showPlayersList(MouseEvent mouseEvent) {
        create.setVisible(false);
        previous.setVisible(false);
        mainBack.setVisible(false);
        playersList.setVisible(true);
        back1.setVisible(true);
        back11.setVisible(true);
        clickedOnPlayer();
    }

    private void clickedOnPlayer() {
        player1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!(player1.getText().equals("empty"))) {
                    tradeGetter = Game.getCurrentGame().getEmpires().get(player1.getText());
                    showResourceList(event);
                }
            }
        });
        player2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!(player2.getText().equals("empty"))) {
                    tradeGetter = Game.getCurrentGame().getEmpires().get(player2.getText());
                    showResourceList(event);
                }
            }
        });
        player3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!(player3.getText().equals("empty"))) {
                    tradeGetter = Game.getCurrentGame().getEmpires().get(player3.getText());
                    showResourceList(event);
                }
            }
        });
        player4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!(player4.getText().equals("empty"))) {
                    tradeGetter = Game.getCurrentGame().getEmpires().get(player4.getText());
                    showResourceList(event);
                }
            }
        });
        player5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!(player5.getText().equals("empty"))) {
                    tradeGetter = Game.getCurrentGame().getEmpires().get(player5.getText());
                    showResourceList(event);
                }
            }
        });
        player6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!(player6.getText().equals("empty"))) {
                    tradeGetter = Game.getCurrentGame().getEmpires().get(player6.getText());
                    showResourceList(event);
                }
            }
        });
    }

    public void showResourceList(MouseEvent mouseEvent) {
        playersList.setVisible(false);
        resourcesGrid.setVisible(true);
        amount.setVisible(true);
        amount1.setVisible(true);
        clickOnResource();
        showResourceAmount();
    }

    private void showResourceAmount() {
        aleNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getResources().get("ale"));
        wheatNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getResources().get("wheat"));
        appleNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getFood().get("apple"));
        cheeseNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getFood().get("cheese"));
        breadNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getFood().get("bread"));
        flourNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getResources().get("flour"));
        hopsNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getResources().get("hops"));
        meatNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getFood().get("meat"));
        pitchNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getResources().get("pitch"));
        bowNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getWeaponAndArmour().get("bow"));
        crossbowNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getWeaponAndArmour().get("crossbow"));
        leatherNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getWeaponAndArmour().get("leather armour"));
        pikeNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getWeaponAndArmour().get("pike"));
        spearNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getWeaponAndArmour().get("spear"));
        metalNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getWeaponAndArmour().get("metal armour"));
        swordNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getWeaponAndArmour().get("sword"));
        ironNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getResources().get("iron"));
        stoneNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getResources().get("stone"));
        woodNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getResources().get("wood"));
        maceNumber.setText("" + Game.getCurrentGame().getCurrentEmpire().getWeaponAndArmour().get("mace"));
    }

    private void clickOnResource() {
        meat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "meat";
                createNewTrade(event);
            }
        });
        apple.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "apple";
                createNewTrade(event);
            }
        });
        bread.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "bread";
                createNewTrade(event);
            }
        });
        cheese.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "cheese";
                createNewTrade(event);
            }
        });
        flour.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "flour";
                createNewTrade(event);
            }
        });
        hops.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "hops";
                createNewTrade(event);
            }
        });
        pitch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "pitch";
                createNewTrade(event);
            }
        });
        wheat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "wheat";
                createNewTrade(event);
            }
        });
        ale.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "ale";
                createNewTrade(event);
            }
        });
        bow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "bow";
                createNewTrade(event);
            }
        });
        crossbow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "crossbow";
                createNewTrade(event);
            }
        });
        leatherArmour.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "leather armour";
                createNewTrade(event);
            }
        });
        mace.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "mace";
                createNewTrade(event);
            }
        });
        metalArmour.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "metal armour";
                createNewTrade(event);
            }
        });
        pike.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "pike";
                createNewTrade(event);
            }
        });
        spear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "spear";
                createNewTrade(event);
            }
        });
        sword.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "sword";
                createNewTrade(event);
            }
        });
        iron.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "iron";
                createNewTrade(event);
            }
        });
        stone.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "stone";
                createNewTrade(event);
            }
        });
        wood.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resource = "wood";
                createNewTrade(event);

            }
        });


    }

    public void createNewTrade(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        selectedResource.setImage(imageView.getImage());
        resourcesGrid.setVisible(false);
        amount.setVisible(false);
        amount1.setVisible(false);
        newTrade.setVisible(true);
        clickOnButton();
    }

    private void clickOnButton() {
        donate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resourceNumber = Integer.parseInt(itemNumber.getText());
                senderMessage = tradeMessage.getText();
                price = 0;

            }
        });
        request.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resourceNumber = Integer.parseInt(itemNumber.getText());
                senderMessage = tradeMessage.getText();
                if (resource != null)
                    price = resourceNumber * ResourcesDictionary.getDictionaryByName(resource).getPrice();
            }
        });
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TradeMessages messages = Menu.getTradeController().setTrade(resource, senderMessage, resourceNumber,
                        price, tradeGetter);
                new Alert(Alert.AlertType.INFORMATION, messages.getMessage(), ButtonType.OK).showAndWait();
                // TradeMenu.Stage.close();
            }
        });
    }

    public void enterTradeMenu(MouseEvent mouseEvent) throws Exception {
        new TradeMenu().start(TradeMenu.Stage);
    }

    public void viewPreviousTrades(MouseEvent mouseEvent) {
        create.setVisible(false);
        previous.setVisible(false);
        mainBack.setVisible(false);
        back1.setVisible(true);
        back11.setVisible(true);
        submitted.setVisible(true);
        received.setVisible(true);
        showTrade();
        acceptTrade();
    }

    public void showSubmittedTrades(MouseEvent mouseEvent) {
        submitted.setVisible(false);
        received.setVisible(false);
        submittedOffers.setVisible(true);
    }

    public void showReceivedOffers(MouseEvent mouseEvent) {
        submitted.setVisible(false);
        received.setVisible(false);
        receivedOffers.setVisible(true);
        setButtons();
    }

    private void setButtons() {
        if (status11.getText().equals("unsight")) {
            accept1.setVisible(true);
            reject1.setVisible(true);
        }
        if (status22.getText().equals("unsight")) {
            accept2.setVisible(true);
            reject2.setVisible(true);
        }
        if (status33.getText().equals("unsight")) {
            accept3.setVisible(true);
            reject3.setVisible(true);
        }
        if (status44.getText().equals("unsight")) {
            accept4.setVisible(true);
            reject4.setVisible(true);
        }
    }

    private void acceptTrade() {
        id1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(id1.getText());
                if (event.getButton() == MouseButton.PRIMARY) {
                    TradeMessages messages = Menu.getTradeController().acceptTrade(Integer.parseInt(id1.getText()));
                    new Alert(Alert.AlertType.INFORMATION, messages.getMessage(), ButtonType.OK).showAndWait();
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    TradeMessages messages = Menu.getTradeController().rejectTrade(Integer.parseInt(id1.getText()));
                    new Alert(Alert.AlertType.INFORMATION, messages.getMessage(), ButtonType.OK).showAndWait();
                }
            }
        });
        id2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    TradeMessages messages = Menu.getTradeController().acceptTrade(Integer.parseInt(id2.getText()));
                    new Alert(Alert.AlertType.INFORMATION, messages.getMessage(), ButtonType.OK).showAndWait();
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    TradeMessages messages = Menu.getTradeController().rejectTrade(Integer.parseInt(id2.getText()));
                    new Alert(Alert.AlertType.INFORMATION, messages.getMessage(), ButtonType.OK).showAndWait();
                }
            }
        });
        id3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    TradeMessages messages = Menu.getTradeController().acceptTrade(Integer.parseInt(id3.getText()));
                    new Alert(Alert.AlertType.INFORMATION, messages.getMessage(), ButtonType.OK).showAndWait();
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    TradeMessages messages = Menu.getTradeController().rejectTrade(Integer.parseInt(id3.getText()));
                    new Alert(Alert.AlertType.INFORMATION, messages.getMessage(), ButtonType.OK).showAndWait();
                }
            }
        });
        id4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    System.out.println("left");
                    TradeMessages messages = Menu.getTradeController().acceptTrade(Integer.parseInt(id4.getText()));
                    new Alert(Alert.AlertType.INFORMATION, messages.getMessage(), ButtonType.OK).showAndWait();
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    System.out.println("right");
                    TradeMessages messages = Menu.getTradeController().rejectTrade(Integer.parseInt(id4.getText()));
                    new Alert(Alert.AlertType.INFORMATION, messages.getMessage(), ButtonType.OK).showAndWait();
                }
            }
        });
    }

    private void showTrade() {
        new Trade("wood", 10, 200, "salam",
                Game.getCurrentGame().getCurrentEmpire(), Game.getCurrentGame().getCurrentEmpire());
        ArrayList<Trade> allTrades = Game.getCurrentGame().getCurrentEmpire().getAllTrades();
        if (allTrades.size() == 0)
            return;
        resource11.setText(allTrades.get(0).getResourceName());
        amount11.setText("" + allTrades.get(0).getResourceAmount());
        status11.setText("unsight");
        user11.setText(allTrades.get(0).getTradeSender().getOwner().getUsername());
        id1.setText("" + allTrades.get(0).getId());
        if (allTrades.size() >= 2) {
            resource22.setText(allTrades.get(1).getResourceName());
            amount22.setText("" + allTrades.get(1).getResourceAmount());
            status22.setText("unsight");
            user22.setText(allTrades.get(1).getTradeSender().getOwner().getUsername());
            id2.setText("" + allTrades.get(1).getId());
        }
        if (allTrades.size() >= 3) {
            resource33.setText(allTrades.get(2).getResourceName());
            amount33.setText("" + allTrades.get(2).getResourceAmount());
            status33.setText("unsight");
            user33.setText(allTrades.get(2).getTradeSender().getOwner().getUsername());
            id3.setText("" + allTrades.get(2).getId());
        }
        if (allTrades.size() >= 4) {
            resource44.setText(allTrades.get(3).getResourceName());
            amount44.setText("" + allTrades.get(3).getResourceAmount());
            status44.setText("unsight");
            user44.setText(allTrades.get(3).getTradeSender().getOwner().getUsername());
            id4.setText("" + allTrades.get(3).getId());
        }
    }
}
