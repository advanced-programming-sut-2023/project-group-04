package org.view.gameView;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;


public class GameMenu extends Application {

    public VBox controlBar;
    public VBox game;
    public Text goldAmount;
    public Group popularity = new Group();
    public Group report = new Group();
    private boolean menuFlag = false;


    public static void main(String[] args) {
        launch(args);
    }

    Pane pane;

    @Override
    public void start(Stage stage) throws Exception {
        pane = FXMLLoader.load(new URL(GameMenu.class.getResource("/FXML/GameMenu.fxml").toExternalForm()));
        Scene scene = new Scene(pane);
        showGoldAmount(pane);
        reporterClick(scene);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }


    private void showGoldAmount(Pane pane) {
        goldAmount = new Text();
//        goldAmount.setFont(Font.font("src/main/resources/font/goldNumber.ttf"));
        goldAmount.setTranslateX(908);
        goldAmount.setTranslateY(802);
        goldAmount.setRotate(8);
        goldAmount.setFill(Color.GREEN);
        pane.getChildren().add(goldAmount);
    }

    private void reporterClick(Scene scene) {
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double mouseX = event.getX();
                double mouseY = event.getY();
//                System.out.println(mouseX);
//                System.out.println(mouseY);
//                System.out.println("------------------");
                if ((mouseX < 1010 && mouseX > 910) && (mouseY > 730 && mouseY < 860) && !menuFlag) {
                    addReport();
                    menuFlag = true;
                } else {
                    removePopularity();
                    removeReport();
                    menuFlag = false;
                }


            }
        });
    }

    private void addPopularity() {
        Text food = new Text(500, 760, "Food");
        Text tax = new Text(500, 800, "Tax");
        Text fear = new Text(500, 840, "Fear Factor");
        Text ale = new Text(700, 760, "Ale Coverage");
        Text religion = new Text(700, 800, "Religion");
        Text sum = new Text(620, 850, "In The Coming Month");
        popularity.getChildren().addAll(food, tax, fear, ale, religion, sum);
        for (Node text : popularity.getChildren()) {
            ((Text) text).setFont(Font.font(15));
        }
        pane.getChildren().add(popularity);
    }

    private void removePopularity() {
        pane.getChildren().remove(popularity);
    }

    private void removeReport() {
        pane.getChildren().remove(report);
    }

    private void addReport() {
        Rectangle popularity = new Rectangle();
        popularity.setTranslateX(500);
        popularity.setTranslateY(750);
        Rectangle food = new Rectangle();
        food.setTranslateX(500);
        food.setTranslateY(790);
        Rectangle stores = new Rectangle();
        stores.setTranslateX(500);
        stores.setTranslateY(830);
        Rectangle fear = new Rectangle();
        fear.setTranslateX(700);
        fear.setTranslateY(750);
        Rectangle tax = new Rectangle();
        tax.setTranslateX(700);
        tax.setTranslateY(790);
        Rectangle weapon = new Rectangle();
        tax.setTranslateX(700);
        tax.setTranslateY(830);
        report.getChildren().addAll(popularity, food, stores, fear, tax, weapon);
        for (Node text : report.getChildren()) {
            ((Rectangle) text).setFill(Color.RED);
        }
        clickOnMenu(popularity, food, stores, fear, tax, weapon);
        pane.getChildren().add(report);
    }

    private void addFood() {

    }

    private void removeFood() {

    }

    private void addStores() {

    }

    private void removeStores() {

    }
    private void addFear() {

    }

    private void removeFear() {

    }
    private void addTax() {

    }

    private void removeTax() {

    }

    private void addWeapon() {

    }

    private void removeWeapon() {

    }

    private void clickOnMenu(Rectangle r1, Rectangle r2, Rectangle r3, Rectangle r4, Rectangle r5, Rectangle r6) {
        r1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                removeFood();
                removeFear();
                removeTax();
                removeStores();
                removeWeapon();
                removeReport();
                addPopularity();
            }
        });
        r2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        r3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        r4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        r5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        r6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
    }
}
