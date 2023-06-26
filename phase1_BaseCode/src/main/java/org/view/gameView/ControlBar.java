package org.view.gameView;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ControlBar {
    private Pane pane;
    private Scene scene;
    public Text goldAmount;
    public Group popularity = new Group();
    public Group report = new Group();
    public Group food = new Group();
    public Group weapon = new Group();
    public Group fearAndTax = new Group();
    public Group stores = new Group();
    private boolean menuFlag = false;

    public ControlBar(Pane pane, Scene scene) {
        this.pane = pane;
        this.scene = scene;
    }


    public void showGoldAmount() {
        goldAmount = new Text("200");
//        goldAmount.setFont(Font.font("src/main/resources/font/goldNumber.ttf"));
        goldAmount.setTranslateX(908);
        goldAmount.setTranslateY(802);
        goldAmount.setRotate(8);
        goldAmount.setFill(Color.GREEN);
        pane.getChildren().add(goldAmount);
    }

    public void reporterClick() {
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
                    pane.getChildren().removeAll(food, fearAndTax, weapon, popularity, stores);
                    menuFlag = true;
                } else {
                    pane.getChildren().removeAll(popularity);
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
        Text sum = new Text(650, 850, "In The Coming Month");
        Circle foodFace = new Circle(480, 755, 13);
        Circle taxFace = new Circle(480, 795, 13);
        Circle fearFace = new Circle(480, 835, 13);
        Circle aleFace = new Circle(680, 755, 13);
        Circle religionFace = new Circle(680, 795, 13);
        Circle sumFace = new Circle(630, 845, 13);
        foodFace.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/img/imoji/sad.png").toExternalForm())));
        aleFace.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/img/imoji/happy.png").toExternalForm())));
        sumFace.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/img/imoji/poker.png").toExternalForm())));
        popularity.getChildren().addAll
                (food, tax, fear, ale, religion, sum, foodFace, taxFace, fearFace, aleFace, religionFace, sumFace);
        for (Node node : popularity.getChildren()) {
            if (node instanceof Text)
                ((Text) node).setFont(Font.font(15));
        }
        pane.getChildren().add(popularity);
    }

    private void addFood() {
        Text text = new Text(360, 750, "FOOD");
        text.resize(70, 30);
        text.setFont(Font.font(25));
        Circle meat = new Circle(500, 775, 25);
        meat.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/img/food/Meat.png").toExternalForm())));
        Circle cheese = new Circle(600, 775, 25);
        cheese.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/img/food/cheese.png").toExternalForm())));
        Circle apple = new Circle(700, 775, 25);
        apple.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/img/food/apple.jpg").toExternalForm())));
        Circle bread = new Circle(800, 775, 17);
        bread.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/img/food/bread.jpg").toExternalForm())));
        Slider foodSlider = new Slider();
        foodSlider.setMin(-2);
        foodSlider.setMax(2);
        foodSlider.setMajorTickUnit(1);
        foodSlider.setTranslateX(520);
        foodSlider.setTranslateY(830);
        foodSlider.setBlockIncrement(1);
        foodSlider.setShowTickLabels(true);
        foodSlider.setPrefWidth(250);
        foodSlider.valueProperty().addListener((bar, oldVal, newVal) ->
                foodSlider.setValue(Math.round(newVal.doubleValue())));
        foodSlider.setBackground(Background.fill(Color.WHEAT));
        food.getChildren().addAll(meat, text, cheese, apple, bread, foodSlider);
        pane.getChildren().addAll(food);
    }

    private void addStores() {

    }

    private void addFear() {
        Text fearText = new Text(360, 745, "FEAR");
        fearText.setFont(Font.font(20));
        Slider fearSlider = new Slider();
        fearSlider.setMin(-5);
        fearSlider.setMax(5);
        fearSlider.setMajorTickUnit(1);
        fearSlider.setTranslateX(520);
        fearSlider.setTranslateY(750);
        fearSlider.setBlockIncrement(1);
        fearSlider.setShowTickLabels(true);
        fearSlider.setPrefWidth(250);
        fearSlider.valueProperty().addListener((bar, oldVal, newVal) ->
                fearSlider.setValue(Math.round(newVal.doubleValue())));
        fearSlider.setBackground(Background.fill(Color.WHEAT));
        fearAndTax.getChildren().addAll(fearSlider, fearText);
    }

    private void addTax() {
        Text taxText = new Text(360, 825, "TAX");
        taxText.setFont(Font.font(20));
        Slider taxSlider = new Slider();
        taxSlider.setMin(-3);
        taxSlider.setMax(8);
        taxSlider.setMajorTickUnit(1);
        taxSlider.setTranslateX(520);
        taxSlider.setTranslateY(830);
        taxSlider.setBlockIncrement(1);
        taxSlider.setShowTickLabels(true);
        taxSlider.setPrefWidth(250);
        taxSlider.valueProperty().addListener((bar, oldVal, newVal) ->
                taxSlider.setValue(Math.round(newVal.doubleValue())));
        taxSlider.setBackground(Background.fill(Color.WHEAT));
        fearAndTax.getChildren().addAll(taxSlider, taxText);
        pane.getChildren().add(fearAndTax);
    }

//    private void addWeapon() {
//
//    }


    private void addReport() {
        Button popularity = new Button("Popularity");
        popularity.setTranslateX(570);
        popularity.setTranslateY(735);
        Button food = new Button("Food");
        food.setTranslateX(570);
        food.setTranslateY(770);
        Button fearAndTax = new Button("Fear & Tax");
        fearAndTax.setTranslateX(570);
        fearAndTax.setTranslateY(805);
        Button stores = new Button("Stores");
        stores.setTranslateX(570);
        stores.setTranslateY(840);
//        Button tax = new Button("Tax");
//        tax.setTranslateX(700);
//        tax.setTranslateY(790);
//        Button weapon = new Button("Weapons");
//        weapon.setTranslateX(700);
//        weapon.setTranslateY(830);
        report.getChildren().addAll(popularity, food, fearAndTax, stores);
        for (Node node : report.getChildren()) {
            ((Button) node).setPrefSize(200, 20);
            ((Button) node).setBackground(Background.fill(Color.LIGHTYELLOW));
        }
        clickOnMenu(popularity, food, fearAndTax, stores);
        pane.getChildren().add(report);
    }

    private void clickOnMenu(Button r1, Button r2, Button r3, Button r4) {
        r1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(food, fearAndTax, weapon, report, stores);
                addPopularity();
            }
        });
        r2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(report, fearAndTax, weapon, popularity, stores);
                addFood();
            }
        });
        r3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(food, report, weapon, popularity, stores);
                addFear();
                addTax();
            }
        });
        r4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(food, fearAndTax, weapon, popularity, report);
                addStores();
            }
        });
//        r5.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                pane.getChildren().removeAll(food, fear, weapon, popularity, report, stores);
//                addTax();
//            }
//        });
//        r6.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                pane.getChildren().removeAll(food, fearAndTax, report, popularity, stores);
//                addWeapon();
//            }
//        });
    }
}
