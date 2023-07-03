package org.view.gameView;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
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
    }

    public void showResourceList(MouseEvent mouseEvent) {
        playersList.setVisible(false);
//        String opponentUsername = mouseEvent.getSource().toString();
//        Player opponent = Player.getPlayerByUsername(opponentUsername);
        resourcesGrid.setVisible(true);
        amount.setVisible(true);
        amount1.setVisible(true);
    }

    public void createNewTrade(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        selectedResource.setImage(imageView.getImage());
        resourcesGrid.setVisible(false);
        amount.setVisible(false);
        amount1.setVisible(false);
        newTrade.setVisible(true);
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
