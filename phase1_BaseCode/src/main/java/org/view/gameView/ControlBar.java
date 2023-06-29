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
    public Group imagePlaces = new Group();
    Rectangle tower, industry, farm, castle, foood, weapon;
    Circle circle1, circle2, circle3, circle4, circle5, circle6;
    private boolean menuFlag = false, category = true;
    HashMap<String, Image> images = new HashMap<>();

    public ControlBar(Pane pane, Scene scene) {
        this.pane = pane;
        this.scene = scene;
        addImage();
        BuildingMenu();
        BuildingCategory();
        addReport();
        addTax();
        addFear();
        addFood();
        addPopularity();
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
                        pane.getChildren().add(BuildingCategory);
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
        food.setTranslateY(780);
        Button fearAndTax = new Button("Fear & Tax");
        fearAndTax.setTranslateX(570);
        fearAndTax.setTranslateY(825);
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
        clickOnCategory(tower, industry, farm, castle, foood, weapon);
    }

    private void clickOnCategory(Rectangle tower, Rectangle industry, Rectangle farm, Rectangle castle, Rectangle foood,
                                 Rectangle weapon) {
        tower.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                circle1.setFill(new ImagePattern(images.get("hovel")));
                circle2.setFill(new ImagePattern(images.get("lookout tower")));
                circle3.setFill(new ImagePattern(images.get("defence turret")));
                circle4.setFill(new ImagePattern(images.get("round tower")));
                circle5.setFill(new ImagePattern(images.get("square tower")));
                circle6.setFill(new ImagePattern(images.get("perimeter")));
                if (pane.getChildren().contains(circle1))
                    pane.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5, circle6);
                pane.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6);
            }
        });
        industry.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                circle1.setFill(new ImagePattern(images.get("quarry")));
                circle2.setFill(new ImagePattern(images.get("iron mine")));
                circle3.setFill(new ImagePattern(images.get("market")));
                circle4.setFill(new ImagePattern(images.get("pitch rig")));
                circle5.setFill(new ImagePattern(images.get("woodcutter")));
                circle6.setFill(new ImagePattern(images.get("mercenary post")));
                if (pane.getChildren().contains(circle1))
                    pane.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5, circle6);
                pane.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6);
            }
        });
        farm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                circle1.setFill(new ImagePattern(images.get("apple orchard")));
                circle2.setFill(new ImagePattern(images.get("diary farmer")));
                circle3.setFill(new ImagePattern(images.get("granary")));
                circle4.setFill(new ImagePattern(images.get("hops farmer")));
                circle5.setFill(new ImagePattern(images.get("hunter post")));
                circle6.setFill(new ImagePattern(images.get("wheat")));
                if (pane.getChildren().contains(circle1))
                    pane.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5, circle6);
                pane.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6);
            }
        });
        castle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                circle1.setFill(new ImagePattern(images.get("barracks")));
                circle2.setFill(new ImagePattern(images.get("engineer guild")));
                circle3.setFill(new ImagePattern(images.get("low wall")));
                circle4.setFill(new ImagePattern(images.get("stone wall")));
                circle5.setFill(new ImagePattern(images.get("small stone gatehouse")));
                circle6.setFill(new ImagePattern(images.get("tunneler guild")));
                if (pane.getChildren().contains(circle1))
                    pane.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5, circle6);
                pane.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6);
            }
        });
        foood.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                circle1.setFill(new ImagePattern(images.get("bakery")));
                circle2.setFill(new ImagePattern(images.get("brewer")));
                circle3.setFill(new ImagePattern(images.get("inn")));
                circle4.setFill(new ImagePattern(images.get("mill")));
                circle5.setFill(new ImagePattern(images.get("stockpile")));
                circle6.setFill(new ImagePattern(images.get("water")));
                if (pane.getChildren().contains(circle1))
                    pane.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5, circle6);
                pane.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6);
            }
        });
        weapon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                circle1.setFill(new ImagePattern(images.get("armoury")));
                circle2.setFill(new ImagePattern(images.get("armourer")));
                circle3.setFill(new ImagePattern(images.get("blacksmith")));
                circle4.setFill(new ImagePattern(images.get("fletcher")));
                circle5.setFill(new ImagePattern(images.get("poleturner")));
                circle6.setFill(new ImagePattern(images.get("tanner")));
                if (pane.getChildren().contains(circle1))
                    pane.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5, circle6);
                pane.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6);
            }
        });
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
    }

    private void BuildingMenu() {
        circle1 = new Circle(430, 750, 32);
        circle2 = new Circle(510, 800, 32);
        circle3 = new Circle(590, 750, 32);
        circle4 = new Circle(670, 800, 32);
        circle5 = new Circle(750, 750, 32);
        circle6 = new Circle(830, 800, 32);
        imagePlaces.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6);
    }

    private void addImage() {
        images.put("armoury", new Image(ControlBar.class.getResource("/img/buildings/weapon/armoury.png").toExternalForm()));
        images.put("armoury1", new Image(ControlBar.class.getResource("/img/buildings/weapon/armoury1.png").toExternalForm()));
        images.put("granary", new Image(ControlBar.class.getResource("/img/buildings/farm/granary.png").toExternalForm()));
        images.put("granary1", new Image(ControlBar.class.getResource("/img/buildings/farm/granary1.png").toExternalForm()));
        images.put("stockpile", new Image(ControlBar.class.getResource("/img/buildings/food/stockpile.png").toExternalForm()));
        images.put("lookout tower", new Image(ControlBar.class.getResource("/img/buildings/towers/lookout tower.png").toExternalForm()));
        images.put("defence turret", new Image(ControlBar.class.getResource("/img/buildings/towers/defence turret.png").toExternalForm()));
        images.put("perimeter", new Image(ControlBar.class.getResource("/img/buildings/towers/perimeter.png").toExternalForm()));
        images.put("hovel", new Image(ControlBar.class.getResource("/img/buildings/towers/hovel.png").toExternalForm()));
        images.put("round tower", new Image(ControlBar.class.getResource("/img/buildings/towers/round tower.png").toExternalForm()));
        images.put("square tower", new Image(ControlBar.class.getResource("/img/buildings/towers/square tower.png").toExternalForm()));
        images.put("apple orchard", new Image(ControlBar.class.getResource("/img/buildings/farm/apple orchard.png").toExternalForm()));
        images.put("diary farmer", new Image(ControlBar.class.getResource("/img/buildings/farm/diary farmer.png").toExternalForm()));
        images.put("hunter post", new Image(ControlBar.class.getResource("/img/buildings/farm/hunter post.png").toExternalForm()));
        images.put("hops farmer", new Image(ControlBar.class.getResource("/img/buildings/farm/hops farmer.png").toExternalForm()));
        images.put("wheat", new Image(ControlBar.class.getResource("/img/buildings/farm/wheat.png").toExternalForm()));
        images.put("bakery", new Image(ControlBar.class.getResource("/img/buildings/food/bakery.png").toExternalForm()));
        images.put("brewer", new Image(ControlBar.class.getResource("/img/buildings/food/brewer.png").toExternalForm()));
        images.put("inn", new Image(ControlBar.class.getResource("/img/buildings/food/inn.png").toExternalForm()));
        images.put("mill", new Image(ControlBar.class.getResource("/img/buildings/food/mill.png").toExternalForm()));
        //images.put("large stone gatehouse", new Image(ControlBar.class.getResource("/img/buildings/gatehouse/large gate.png").toExternalForm()));
        images.put("small stone gatehouse", new Image(ControlBar.class.getResource("/img/buildings/castle/small stone gatehouse.png").toExternalForm()));
        images.put("armourer", new Image(ControlBar.class.getResource("/img/buildings/weapon/armourer.png").toExternalForm()));
        images.put("fletcher", new Image(ControlBar.class.getResource("/img/buildings/weapon/fletcher.png").toExternalForm()));
        images.put("blacksmith", new Image(ControlBar.class.getResource("/img/buildings/weapon/blacksmith.png").toExternalForm()));
        images.put("tanner", new Image(ControlBar.class.getResource("/img/buildings/weapon/tanner.png").toExternalForm()));
        images.put("poleturner", new Image(ControlBar.class.getResource("/img/buildings/weapon/poleturner.png").toExternalForm()));
        images.put("barracks", new Image(ControlBar.class.getResource("/img/buildings/castle/barracks.png").toExternalForm()));
        images.put("engineer guild", new Image(ControlBar.class.getResource("/img/buildings/castle/engineer guild.png").toExternalForm()));
        images.put("mercenary post", new Image(ControlBar.class.getResource("/img/buildings/castle/mercenary post.png").toExternalForm()));
        images.put("tunneler guild", new Image(ControlBar.class.getResource("/img/buildings/castle/tunneler guild.png").toExternalForm()));
        images.put("low wall", new Image(ControlBar.class.getResource("/img/buildings/castle/low wall.png").toExternalForm()));
        images.put("stone wall", new Image(ControlBar.class.getResource("/img/buildings/castle/stone wall.png").toExternalForm()));
        images.put("iron mine", new Image(ControlBar.class.getResource("/img/buildings/industry/iron mine.png").toExternalForm()));
        images.put("market", new Image(ControlBar.class.getResource("/img/buildings/industry/Market.png").toExternalForm()));
        images.put("quarry", new Image(ControlBar.class.getResource("/img/buildings/industry/quarry.png").toExternalForm()));
        images.put("woodcutter", new Image(ControlBar.class.getResource("/img/buildings/industry/woodcutter.png").toExternalForm()));
        images.put("pitch rig", new Image(ControlBar.class.getResource("/img/buildings/industry/pitch rig.png").toExternalForm()));
        images.put("water", new Image(ControlBar.class.getResource("/img/buildings/food/water.png").toExternalForm()));
    }
}
