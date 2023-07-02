package org.view.gameView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.model.Empire;
import org.model.Game;
import org.view.Menu;

import java.io.IOException;
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
    public Slider fearSlider, taxSlider, foodSlider;
    public Text meatText = new Text(), appleText = new Text(), breadText = new Text(), cheeseText = new Text();
    Rectangle tower, industry, farm, castle, foood, weapon;
    Circle circle1, circle2, circle3, circle4, circle5, circle6;
    private boolean menuFlag = false, category = true;
    public static HashMap<String, Image> buildingImages = new HashMap<>();
    private int catNum = -1;
    public static Building clickedBuilding = null;
    public VBox detailBox;

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
        getClickedBuilding();
        addDetailBox();
    }

    void addDetailBox() {
        Text text = new Text("ufaduhka");
        VBox detailBox = new VBox(text);
        detailBox.setViewOrder(0);
        detailBox.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
        detailBox.setPrefSize(200, 200);
        detailBox.setTranslateY(510);
        pane.getChildren().add(detailBox);
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
                    //pane.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5, circle6);
                    menuFlag = true;
                    category = false;
                } else {
                    pane.getChildren().removeAll(report, food, fearAndTax, popularity);
                    //pane.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5, circle6);
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
        Text fear = new Text(500, 840, "Fear");
        Text ale = new Text(700, 760, "Ale Coverage");
        Text religion = new Text(700, 800, "Religion");
        Text sum = new Text(650, 850, "In The Coming Month");
        Circle foodFace = new Circle(480, 755, 13);
        Circle taxFace = new Circle(480, 795, 13);
        Circle fearFace = new Circle(480, 835, 13);
        Circle aleFace = new Circle(680, 755, 13);
        Circle religionFace = new Circle(680, 795, 13);
        Circle sumFace = new Circle(630, 845, 13);
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
        meatText.setTranslateX(500);
        meatText.setTranslateY(790);
        meat.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/img/food/Meat.png").toExternalForm())));
        Circle cheese = new Circle(600, 775, 25);
        cheeseText.setTranslateX(600);
        cheeseText.setTranslateY(790);
        cheese.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/img/food/cheese.png").toExternalForm())));
        Circle apple = new Circle(700, 775, 25);
        appleText.setTranslateX(700);
        appleText.setTranslateY(790);
        apple.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/img/food/apple.jpg").toExternalForm())));
        Circle bread = new Circle(800, 775, 17);
        breadText.setTranslateX(800);
        breadText.setTranslateY(790);
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
        food.getChildren().addAll(meat, text, cheese, apple, bread, foodSlider, meatText, cheeseText, appleText, breadText);
    }

    private void addFear() {
        Text fearText = new Text(360, 745, "FEAR");
        fearText.setFont(Font.font(20));
        fearSlider = new Slider();
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
        taxSlider = new Slider();
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
                catNum = 1;
                circle1.setFill(new ImagePattern(buildingImages.get("hovel")));
                circle2.setFill(new ImagePattern(buildingImages.get("lookout tower")));
                circle3.setFill(new ImagePattern(buildingImages.get("defence turret")));
                circle4.setFill(new ImagePattern(buildingImages.get("round tower")));
                circle5.setFill(new ImagePattern(buildingImages.get("square tower")));
                circle6.setFill(new ImagePattern(buildingImages.get("perimeter")));
                if (pane.getChildren().contains(circle1))
                    pane.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5, circle6);
                pane.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6);
            }
        });
        industry.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                catNum = 2;
                circle1.setFill(new ImagePattern(buildingImages.get("quarry")));
                circle2.setFill(new ImagePattern(buildingImages.get("iron mine")));
                circle3.setFill(new ImagePattern(buildingImages.get("market")));
                circle4.setFill(new ImagePattern(buildingImages.get("pitch rig")));
                circle5.setFill(new ImagePattern(buildingImages.get("woodcutter")));
                circle6.setFill(new ImagePattern(buildingImages.get("mercenary post")));
                if (pane.getChildren().contains(circle1))
                    pane.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5, circle6);
                pane.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6);
            }
        });
        farm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                catNum = 3;
                circle1.setFill(new ImagePattern(buildingImages.get("apple orchard")));
                circle2.setFill(new ImagePattern(buildingImages.get("diary farmer")));
                circle3.setFill(new ImagePattern(buildingImages.get("granary")));
                circle4.setFill(new ImagePattern(buildingImages.get("hops farmer")));
                circle5.setFill(new ImagePattern(buildingImages.get("hunter post")));
                circle6.setFill(new ImagePattern(buildingImages.get("wheat")));
                if (pane.getChildren().contains(circle1))
                    pane.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5, circle6);
                pane.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6);
            }
        });
        castle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                catNum = 4;
                circle1.setFill(new ImagePattern(buildingImages.get("barracks")));
                circle2.setFill(new ImagePattern(buildingImages.get("engineer guild")));
                circle3.setFill(new ImagePattern(buildingImages.get("low wall")));
                circle4.setFill(new ImagePattern(buildingImages.get("stone wall")));
                circle5.setFill(new ImagePattern(buildingImages.get("small stone gatehouse")));
                circle6.setFill(new ImagePattern(buildingImages.get("tunneler guild")));
                if (pane.getChildren().contains(circle1))
                    pane.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5, circle6);
                pane.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6);
            }
        });
        foood.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                catNum = 5;
                circle1.setFill(new ImagePattern(buildingImages.get("bakery")));
                circle2.setFill(new ImagePattern(buildingImages.get("brewer")));
                circle3.setFill(new ImagePattern(buildingImages.get("inn")));
                circle4.setFill(new ImagePattern(buildingImages.get("mill")));
                circle5.setFill(new ImagePattern(buildingImages.get("stockpile")));
                circle6.setFill(new ImagePattern(buildingImages.get("water")));
                if (pane.getChildren().contains(circle1))
                    pane.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5, circle6);
                pane.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6);
            }
        });
        weapon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                catNum = 6;
                circle1.setFill(new ImagePattern(buildingImages.get("armoury")));
                circle2.setFill(new ImagePattern(buildingImages.get("armourer")));
                circle3.setFill(new ImagePattern(buildingImages.get("blacksmith")));
                circle4.setFill(new ImagePattern(buildingImages.get("fletcher")));
                circle5.setFill(new ImagePattern(buildingImages.get("poleturner")));
                circle6.setFill(new ImagePattern(buildingImages.get("tanner")));
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
                //updatePopularity();
            }
        });
        r2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(report, fearAndTax, weapon, popularity, BuildingCategory);
                pane.getChildren().add(food);
                //updateFood();
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

//    private void updatePopularity() {
//        HashMap<String, Integer> popularityFactors = Menu.getGameController().showPopularity();
//        Text text = ((Text) popularity.getChildren().get(0));
//        Integer factorNum = popularityFactors.get("food");
//        text.setText(text.getText() + "  " + factorNum);
//        if (factorNum <= 2 && factorNum >= -2)
//            ((Circle) popularity.getChildren().get(6)).setFill(new ImagePattern(buildingImages.get("poker")));
//        else if (factorNum < -2)
//            ((Circle) popularity.getChildren().get(6)).setFill(new ImagePattern(buildingImages.get("sad")));
//        else
//            ((Circle) popularity.getChildren().get(6)).setFill(new ImagePattern(buildingImages.get("happy")));
//        ///////////
//        text = ((Text) popularity.getChildren().get(1));
//        factorNum = popularityFactors.get("tax");
//        text.setText(text.getText() + "  " + factorNum);
//        if (factorNum <= 2 && factorNum >= -2)
//            ((Circle) popularity.getChildren().get(7)).setFill(new ImagePattern(buildingImages.get("poker")));
//        else if (factorNum < -2)
//            ((Circle) popularity.getChildren().get(7)).setFill(new ImagePattern(buildingImages.get("sad")));
//        else
//            ((Circle) popularity.getChildren().get(7)).setFill(new ImagePattern(buildingImages.get("happy")));
//        //////////////////
//        text = ((Text) popularity.getChildren().get(2));
//        factorNum = popularityFactors.get("fear");
//        text.setText(text.getText() + "  " + factorNum);
//        if (factorNum <= 2 && factorNum >= -2)
//            ((Circle) popularity.getChildren().get(8)).setFill(new ImagePattern(buildingImages.get("poker")));
//        else if (factorNum < -2)
//            ((Circle) popularity.getChildren().get(8)).setFill(new ImagePattern(buildingImages.get("sad")));
//        else
//            ((Circle) popularity.getChildren().get(8)).setFill(new ImagePattern(buildingImages.get("happy")));
//        //////////////////////
//        text = ((Text) popularity.getChildren().get(3));
//        factorNum = popularityFactors.get("ale");
//        text.setText(text.getText() + "  " + factorNum);
//        if (factorNum <= 2 && factorNum >= -2)
//            ((Circle) popularity.getChildren().get(9)).setFill(new ImagePattern(buildingImages.get("poker")));
//        else if (factorNum < -2)
//            ((Circle) popularity.getChildren().get(9)).setFill(new ImagePattern(buildingImages.get("sad")));
//        else
//            ((Circle) popularity.getChildren().get(9)).setFill(new ImagePattern(buildingImages.get("happy")));
//        //////////////////
//        text = ((Text) popularity.getChildren().get(4));
//        factorNum = popularityFactors.get("religion");
//        text.setText(text.getText() + "  " + factorNum);
//        if (factorNum <= 2 && factorNum >= -2)
//            ((Circle) popularity.getChildren().get(10)).setFill(new ImagePattern(buildingImages.get("poker")));
//        else if (factorNum < -2)
//            ((Circle) popularity.getChildren().get(10)).setFill(new ImagePattern(buildingImages.get("sad")));
//        else
//            ((Circle) popularity.getChildren().get(10)).setFill(new ImagePattern(buildingImages.get("happy")));
//        /////////////////
//        text = ((Text) popularity.getChildren().get(5));
//        factorNum = popularityFactors.get("sum");
//        text.setText(text.getText() + "  " + factorNum);
//        if (factorNum <= 5 && factorNum >= -5)
//            ((Circle) popularity.getChildren().get(11)).setFill(new ImagePattern(buildingImages.get("poker")));
//        else if (factorNum < -5)
//            ((Circle) popularity.getChildren().get(11)).setFill(new ImagePattern(buildingImages.get("sad")));
//        else
//            ((Circle) popularity.getChildren().get(11)).setFill(new ImagePattern(buildingImages.get("happy")));
//    }

    private void updateFood() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        HashMap<String, Integer> foodList = Menu.getGameController().showFoodList();
        meatText.setText("" + foodList.get("meat"));
        cheeseText.setText("" + foodList.get("cheese"));
        breadText.setText("" + foodList.get("bread"));
        appleText.setText("" + foodList.get("apple"));
        foodSlider.setValue(empire.getFoodRate());
        foodSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                empire.setFoodRate((Integer) number);
            }
        });
    }

    private void updateFearAndTax() {
        Empire empire = Game.getCurrentGame().getCurrentEmpire();
        fearSlider.setValue(empire.getFearRate());
        taxSlider.setValue(empire.getTaxRate());
        fearSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                empire.setFearRate((Integer) number);
            }
        });
        taxSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                empire.setTaxRate((Integer) number);
            }
        });
    }

    private void getClickedBuilding() {
        circle1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (catNum == -1)
                    clickedBuilding = null;
                else if (catNum == 1) {
                    try {
                        clickedBuilding = new Building("hovel");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 2) {
                    try {
                        clickedBuilding = new Building("quarry");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 3) {
                    try {
                        clickedBuilding = new Building("apple orchard");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 4) {
                    try {
                        clickedBuilding = new Building("barracks");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 5) {
                    try {
                        clickedBuilding = new Building("bakery");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 6) {
                    try {
                        clickedBuilding = new Building("armoury");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        circle2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (catNum == -1)
                    clickedBuilding = null;
                else if (catNum == 1) {
                    try {
                        clickedBuilding = new Building("lookout tower");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 2) {
                    try {
                        clickedBuilding = new Building("iron mine");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 3) {
                    try {
                        clickedBuilding = new Building("diary farmer");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 4) {
                    try {
                        clickedBuilding = new Building("engineer guild");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 5) {
                    try {
                        clickedBuilding = new Building("brewer");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 6) {
                    try {
                        clickedBuilding = new Building("armourer");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        circle3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (catNum == -1)
                    clickedBuilding = null;
                else if (catNum == 1) {
                    try {
                        clickedBuilding = new Building("defence turret");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 2) {
                    try {
                        clickedBuilding = new Building("market");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 3) {
                    try {
                        clickedBuilding = new Building("granary");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 4) {
                    try {
                        clickedBuilding = new Building("low wall");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 5) {
                    try {
                        clickedBuilding = new Building("inn");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 6) {
                    try {
                        clickedBuilding = new Building("blacksmith");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        circle4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (catNum == -1)
                    clickedBuilding = null;
                else if (catNum == 1) {
                    try {
                        clickedBuilding = new Building("round tower");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 2) {
                    try {
                        clickedBuilding = new Building("pitch rig");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 3) {
                    try {
                        clickedBuilding = new Building("hops farmer");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 4) {
                    try {
                        clickedBuilding = new Building("stone wall");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 5) {
                    try {
                        clickedBuilding = new Building("mill");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 6) {
                    try {
                        clickedBuilding = new Building("fletcher");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        circle5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (catNum == -1)
                    clickedBuilding = null;
                else if (catNum == 1) {
                    try {
                        clickedBuilding = new Building("square tower");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 2) {
                    try {
                        clickedBuilding = new Building("woodcutter");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 3) {
                    try {
                        clickedBuilding = new Building("hunter post");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 4) {
                    try {
                        clickedBuilding = new Building("small stone gatehouse");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 5) {
                    try {
                        clickedBuilding = new Building("stockpile");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 6) {
                    try {
                        clickedBuilding = new Building("poleturner");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        circle6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (catNum == -1)
                    clickedBuilding = null;
                else if (catNum == 1) {
                    try {
                        clickedBuilding = new Building("perimeter");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 2) {
                    try {
                        clickedBuilding = new Building("mercenary post");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 3) {
                    try {
                        clickedBuilding = new Building("wheat");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 4) {
                    try {
                        clickedBuilding = new Building("tunneler guild");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 5) {
                    try {
                        clickedBuilding = new Building("water");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (catNum == 6) {
                    try {
                        clickedBuilding = new Building("tanner");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void addImage() {
        buildingImages.put("armoury", new Image(ControlBar.class.getResource("/img/buildings/weapon/armoury.png").toExternalForm()));
        buildingImages.put("armoury1", new Image(ControlBar.class.getResource("/img/buildings/weapon/armoury1.png").toExternalForm()));
        buildingImages.put("granary", new Image(ControlBar.class.getResource("/img/buildings/farm/granary.png").toExternalForm()));
        buildingImages.put("granary1", new Image(ControlBar.class.getResource("/img/buildings/farm/granary1.png").toExternalForm()));
        buildingImages.put("stockpile", new Image(ControlBar.class.getResource("/img/buildings/food/stockpile.png").toExternalForm()));
        buildingImages.put("lookout tower", new Image(ControlBar.class.getResource("/img/buildings/towers/lookout tower.png").toExternalForm()));
        buildingImages.put("defence turret", new Image(ControlBar.class.getResource("/img/buildings/towers/defence turret.png").toExternalForm()));
        buildingImages.put("perimeter", new Image(ControlBar.class.getResource("/img/buildings/towers/perimeter.png").toExternalForm()));
        buildingImages.put("hovel", new Image(ControlBar.class.getResource("/img/buildings/towers/hovel.png").toExternalForm()));
        buildingImages.put("round tower", new Image(ControlBar.class.getResource("/img/buildings/towers/round tower.png").toExternalForm()));
        buildingImages.put("square tower", new Image(ControlBar.class.getResource("/img/buildings/towers/square tower.png").toExternalForm()));
        buildingImages.put("apple orchard", new Image(ControlBar.class.getResource("/img/buildings/farm/apple orchard.png").toExternalForm()));
        buildingImages.put("diary farmer", new Image(ControlBar.class.getResource("/img/buildings/farm/diary farmer.png").toExternalForm()));
        buildingImages.put("hunter post", new Image(ControlBar.class.getResource("/img/buildings/farm/hunter post.png").toExternalForm()));
        buildingImages.put("hops farmer", new Image(ControlBar.class.getResource("/img/buildings/farm/hops farmer.png").toExternalForm()));
        buildingImages.put("wheat", new Image(ControlBar.class.getResource("/img/buildings/farm/wheat.png").toExternalForm()));
        buildingImages.put("bakery", new Image(ControlBar.class.getResource("/img/buildings/food/bakery.png").toExternalForm()));
        buildingImages.put("brewer", new Image(ControlBar.class.getResource("/img/buildings/food/brewer.png").toExternalForm()));
        buildingImages.put("inn", new Image(ControlBar.class.getResource("/img/buildings/food/inn.png").toExternalForm()));
        buildingImages.put("mill", new Image(ControlBar.class.getResource("/img/buildings/food/mill.png").toExternalForm()));
        buildingImages.put("small stone gatehouse", new Image(ControlBar.class.getResource("/img/buildings/castle/small stone gatehouse.png").toExternalForm()));
        buildingImages.put("armourer", new Image(ControlBar.class.getResource("/img/buildings/weapon/armourer.png").toExternalForm()));
        buildingImages.put("fletcher", new Image(ControlBar.class.getResource("/img/buildings/weapon/fletcher.png").toExternalForm()));
        buildingImages.put("blacksmith", new Image(ControlBar.class.getResource("/img/buildings/weapon/blacksmith.png").toExternalForm()));
        buildingImages.put("tanner", new Image(ControlBar.class.getResource("/img/buildings/weapon/tanner.png").toExternalForm()));
        buildingImages.put("poleturner", new Image(ControlBar.class.getResource("/img/buildings/weapon/poleturner.png").toExternalForm()));
        buildingImages.put("barracks", new Image(ControlBar.class.getResource("/img/buildings/castle/barracks.png").toExternalForm()));
        buildingImages.put("engineer guild", new Image(ControlBar.class.getResource("/img/buildings/castle/engineer guild.png").toExternalForm()));
        buildingImages.put("mercenary post", new Image(ControlBar.class.getResource("/img/buildings/castle/mercenary post.png").toExternalForm()));
        buildingImages.put("tunneler guild", new Image(ControlBar.class.getResource("/img/buildings/castle/tunneler guild.png").toExternalForm()));
        buildingImages.put("low wall", new Image(ControlBar.class.getResource("/img/buildings/castle/low wall.png").toExternalForm()));
        buildingImages.put("stone wall", new Image(ControlBar.class.getResource("/img/buildings/castle/stone wall.png").toExternalForm()));
        buildingImages.put("iron mine", new Image(ControlBar.class.getResource("/img/buildings/industry/iron mine.png").toExternalForm()));
        buildingImages.put("market", new Image(ControlBar.class.getResource("/img/buildings/industry/Market.png").toExternalForm()));
        buildingImages.put("quarry", new Image(ControlBar.class.getResource("/img/buildings/industry/quarry.png").toExternalForm()));
        buildingImages.put("woodcutter", new Image(ControlBar.class.getResource("/img/buildings/industry/woodcutter.png").toExternalForm()));
        buildingImages.put("pitch rig", new Image(ControlBar.class.getResource("/img/buildings/industry/pitch rig.png").toExternalForm()));
        buildingImages.put("water", new Image(ControlBar.class.getResource("/img/buildings/food/water.png").toExternalForm()));
        buildingImages.put("happy", new Image(ControlBar.class.getResource("/img/imoji/happy.png").toExternalForm()));
        buildingImages.put("sad", new Image(ControlBar.class.getResource("/img/imoji/sad.png").toExternalForm()));
        buildingImages.put("poker", new Image(ControlBar.class.getResource("/img/imoji/poker.png").toExternalForm()));
    }
}
