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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class ControlBar {
    private Pane pane;
    private Scene scene;
    public Text goldAmount;
    public Group popularity = new Group();
    public Group report = new Group();
    public Group food = new Group();
    public Group fearAndTax = new Group();
    public Group BuildingCategory = new Group();
    public Group imagePlace = new Group();
    Rectangle tower;
    Rectangle industry;
    Rectangle farm;
    Rectangle castle;
    Rectangle foood;
    Rectangle weapon;
    Circle circle1;
    Circle circle2;
    Circle circle3;
    Circle circle4;
    Circle circle5;
    Circle circle6;
    private boolean menuFlag = false, category = true;
    HashMap<String, Image> images = new HashMap<>();
    private ArrayList<Image> currentGroup = new ArrayList<>();

    public ControlBar(Pane pane, Scene scene) {
        this.pane = pane;
        this.scene = scene;
        BuildingCategory();
        addReport();
        addTax();
        addFear();
        addFood();
        addPopularity();
        BuildingMenu();
    }


    public void showGoldAmount() {
        goldAmount = new Text("200");
        goldAmount.setTranslateX(910);
        goldAmount.setTranslateY(800);
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
                //System.out.println(mouseX + "    " + mouseY);
                if ((mouseX < 1010 && mouseX > 895) && (mouseY > 740 && mouseY < 840) && !menuFlag) {
                    pane.getChildren().add(report);
                    pane.getChildren().removeAll(food, fearAndTax, BuildingCategory, popularity);
                    menuFlag = true;
                    category = false;
                } else {
                    pane.getChildren().removeAll(report, food, fearAndTax, popularity);
                    if (!category) {
                        category = true;
                    }
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
    }


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
//        Button stores = new Button("Stores");
//        stores.setTranslateX(570);
//        stores.setTranslateY(840);
        report.getChildren().addAll(popularity, food, fearAndTax);
        for (Node node : report.getChildren()) {
            ((Button) node).setPrefSize(200, 20);
            ((Button) node).setBackground(Background.fill(Color.LIGHTYELLOW));
        }
        clickOnMenu(popularity, food, fearAndTax);
    }

    private void BuildingCategory() {
        tower = new Rectangle(360, 840, 30, 30);
        industry = new Rectangle(400, 840, 30, 30);
        farm = new Rectangle(520, 840, 30, 30);
        castle = new Rectangle(480, 840, 30, 30);
        foood = new Rectangle(440, 840, 30, 30);
        weapon = new Rectangle(560, 840, 30, 30);
        industry.setFill(new ImagePattern(new Image(ControlBar.class.getResource("/img/controlBar/hammer.jpg").toExternalForm())));
        foood.setFill(new ImagePattern(new Image(ControlBar.class.getResource("/img/controlBar/apple.png").toExternalForm())));
        castle.setFill(new ImagePattern(new Image(ControlBar.class.getResource("/img/controlBar/home.png").toExternalForm())));
        tower.setFill(new ImagePattern(new Image(ControlBar.class.getResource("/img/controlBar/tower.png").toExternalForm())));
        farm.setFill(new ImagePattern(new Image(ControlBar.class.getResource("/img/controlBar/wheati.png").toExternalForm())));
        weapon.setFill(new ImagePattern(new Image(ControlBar.class.getResource("/img/controlBar/sheild.png").toExternalForm())));
        BuildingCategory.getChildren().addAll(tower, industry, farm, castle, foood, weapon);
    }

    private void clickOnMenu(Button r1, Button r2, Button r3) {
        r1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(food, fearAndTax, weapon, report, BuildingCategory);
                pane.getChildren().add(popularity);
            }
        });
        r2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(report, fearAndTax, weapon, popularity, BuildingCategory);
                pane.getChildren().add(food);
            }
        });
        r3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(food, report, weapon, popularity, BuildingCategory);
                pane.getChildren().add(fearAndTax);
            }
        });
//        r4.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                pane.getChildren().removeAll(food, fearAndTax, weapon, popularity, report, BuildingCategory);
//                pane.getChildren().add(stores);
//            }
//        });
    }

    private void BuildingMenu() {
        circle1 = new Circle(430, 745, 32);
        circle2 = new Circle(510, 800, 32);
        circle3 = new Circle(590, 745, 32);
        circle4 = new Circle(670, 800, 32);
        circle5 = new Circle(750, 745, 32);
        circle6 = new Circle(830, 800, 32);
        imagePlace.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6);
    }

    private ArrayList<Image> getCurrentGroup(int i) {
        ArrayList<Image> imageGroup = new ArrayList<>();
        switch (i) {
            case 1: {

            }
            case 2: {

            }
            case 3: {

            }
            case 4: {

            }
            case 5: {

            }
            case 6: {

            }
        }
        return imageGroup;
    }

    private void addImage() {
        images.put("armoury", new Image(ControlBar.class.getResource("/img/fullasset/storage/armoury.png").toExternalForm()));
        images.put("armoury1", new Image(ControlBar.class.getResource("/img/fullasset/storage/armoury1.png").toExternalForm()));
        images.put("granary", new Image(ControlBar.class.getResource("/img/fullasset/storage/granary.png").toExternalForm()));
        images.put("granary1", new Image(ControlBar.class.getResource("/img/fullasset/storage/granary1.png").toExternalForm()));
        images.put("stockpile", new Image(ControlBar.class.getResource("/img/fullasset/storage/stockpile.png").toExternalForm()));
        images.put("lookout tower", new Image(ControlBar.class.getResource("/img/fullasset/towers/lookout tower.png").toExternalForm()));
        images.put("defence turret", new Image(ControlBar.class.getResource("/img/fullasset/towers/defence turret.png").toExternalForm()));
        images.put("perimeter", new Image(ControlBar.class.getResource("/img/fullasset/towers/perimeter.png").toExternalForm()));
        images.put("round tower", new Image(ControlBar.class.getResource("/img/fullasset/towers/round tower.png").toExternalForm()));
        images.put("square tower", new Image(ControlBar.class.getResource("/img/fullasset/towers/square tower.png").toExternalForm()));



    }
}
