package org.view.gameView;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
    public Group tax = new Group();
    public Group weapon = new Group();
    public Group fear = new Group();
    public Group stores = new Group();
    private boolean menuFlag = false;

    public ControlBar(Pane pane, Scene scene) {
        this.pane = pane;
        this.scene = scene;
    }


    public void showGoldAmount() {
        goldAmount = new Text();
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
                    pane.getChildren().removeAll(food, fear, tax, weapon, popularity, stores);
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
        Circle meat = new Circle(500,775, 25);
        meat.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/img/food/Meat.png").toExternalForm())));
        Circle cheese = new Circle(600,775, 25);
        cheese.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/img/food/cheese.png").toExternalForm())));
        Circle apple = new Circle(700,775, 25);
        apple.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/img/food/apple.jpg").toExternalForm())));
        Circle bread = new Circle(800,775, 17);
        bread.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/img/food/bread.jpg").toExternalForm())));
        Slider slider = new Slider();
        slider.setMin(-2);
        slider.setMax(2);
        slider.setMajorTickUnit(1);
        slider.setTranslateX(520);
        slider.setTranslateY(830);
        slider.setBlockIncrement(1);
        slider.setShowTickLabels(true);
        slider.setPrefWidth(250);
        slider.valueProperty().addListener((bar, oldval, newVal) ->
                slider.setValue(Math.round(newVal.doubleValue())));
        food.getChildren().addAll(meat, text, cheese, apple, bread,slider);
        pane.getChildren().addAll(food);
    }

    private void addStores() {

    }

    private void addFear() {

    }

    private void addTax() {

    }

    private void addWeapon() {

    }


    private void addReport() {
        Button popularity = new Button("Popularity");
        popularity.setTranslateX(500);
        popularity.setTranslateY(750);
        Button food = new Button("Food");
        food.setTranslateX(500);
        food.setTranslateY(790);
        Button stores = new Button("Stores");
        stores.setTranslateX(500);
        stores.setTranslateY(830);
        Button fear = new Button("Fear");
        fear.setTranslateX(700);
        fear.setTranslateY(750);
        Button tax = new Button("Tax");
        tax.setTranslateX(700);
        tax.setTranslateY(790);
        Button weapon = new Button("Weapons");
        weapon.setTranslateX(700);
        weapon.setTranslateY(830);
        report.getChildren().addAll(popularity, food, stores, fear, weapon, tax);
        for (Node text : report.getChildren()) {
            ((Button) text).setPrefSize(100, 20);
        }
        clickOnMenu(popularity, food, stores, fear, tax, weapon);
        pane.getChildren().add(report);
    }

    private void clickOnMenu(Button r1, Button r2, Button r3, Button r4, Button r5, Button r6) {
        //                     pop         food      stores       fear       tax        weapon
        r1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(food, fear, tax, weapon, report, stores);
                addPopularity();
            }
        });
        r2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(report, fear, tax, weapon, popularity, stores);
                addFood();
            }
        });
        r3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(food, fear, tax, weapon, popularity, report);
                addStores();
            }
        });
        r4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(food, report, tax, weapon, popularity, stores);
                addFear();
            }
        });
        r5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(food, fear, weapon, popularity, report, stores);
                addTax();
            }
        });
        r6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(food, fear, tax, report, popularity, stores);
                addWeapon();
            }
        });
    }
}
