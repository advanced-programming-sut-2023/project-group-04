package org.view.gameView;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.model.Empire;
import org.model.Game;
import org.model.ResourcesDictionary;
import org.view.ShopMenu;
import org.view.TradeMenu;

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
    public Text status11;
    public Text status22;
    public Text status33;
    public Text status44;
    public Text player1;
    public Text player5;
    public Text player6;
    public Text player2;
    public Text player3;
    public Text player4;
    public Empire tradeGetter;
    public String resource;
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
    public Text id4;
    public Text id3;
    public Text id2;
    public Text id1;


    public void enterShopMenu(MouseEvent mouseEvent) throws Exception {
        new ShopMenu().start(ShopMenu.stage);
    }

    public void showPlayersList(MouseEvent mouseEvent) {
        create.setVisible(false);
        previous.setVisible(false);
        mainBack.setVisible(false);
        playersList.setVisible(true);
        back1.setVisible(true);
        back11.setVisible(true);
        //clickedOnPlayer();
    }

    private void clickedOnPlayer() {
        player1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!(player1.getText().equals("empty")))
                    tradeGetter = Game.getCurrentGame().getEmpires().get(player1.getText());
            }
        });
        player2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!(player2.getText().equals("empty")))
                    tradeGetter = Game.getCurrentGame().getEmpires().get(player2.getText());
            }
        });
        player3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!(player3.getText().equals("empty")))
                    tradeGetter = Game.getCurrentGame().getEmpires().get(player3.getText());
            }
        });
        player4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!(player4.getText().equals("empty")))
                    tradeGetter = Game.getCurrentGame().getEmpires().get(player4.getText());
            }
        });
        player5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!(player5.getText().equals("empty")))
                    tradeGetter = Game.getCurrentGame().getEmpires().get(player5.getText());
            }
        });
        player6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!(player6.getText().equals("empty")))
                    tradeGetter = Game.getCurrentGame().getEmpires().get(player6.getText());
            }
        });
    }

    public void showResourceList(MouseEvent mouseEvent) {
        playersList.setVisible(false);
        resourcesGrid.setVisible(true);
        amount.setVisible(true);
        amount1.setVisible(true);
        clickOnResource();
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
    }

    private void clickOnButton() {
        donate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resourceNumber = Integer.parseInt(itemNumber.getText());
                price = 0;
            }
        });
        request.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resourceNumber = Integer.parseInt(itemNumber.getText());
                price = ResourcesDictionary.getDictionaryByName(resource).getPrice();
            }
        });

    }

    public void enterTradeMenu(MouseEvent mouseEvent) throws Exception {
        new TradeMenu().start(ShopMenu.stage);
    }

    public void viewPreviousTrades(MouseEvent mouseEvent) {
        create.setVisible(false);
        previous.setVisible(false);
        mainBack.setVisible(false);
        back1.setVisible(true);
        back11.setVisible(true);
        submitted.setVisible(true);
        received.setVisible(true);
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
}
