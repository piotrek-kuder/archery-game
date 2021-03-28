package com.kodilla.archergame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ArcherGame extends Application {

    Images images = new Images();
    ShootAnimation shootAnimation = new ShootAnimation();
    BowAngleSetter setAngle = new BowAngleSetter();
    ShieldPositionCalculator placeShield = new ShieldPositionCalculator();
    WindSettingsCalculator setWind = new WindSettingsCalculator();
    ShootCalculator shootCalculator = new ShootCalculator();
    ScoreChecker scoreChecker = new ScoreChecker();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final int WINDOW_WIDTH = 800;
        final int WINDOW_HEIGHT = 600;

        //sliders
        Slider massSlider = new Slider(10, 30, 10);
        massSlider.setMajorTickUnit(5);
        massSlider.setMinorTickCount(4);
        massSlider.setShowTickMarks(true);
        massSlider.setShowTickLabels(true);
        massSlider.setSnapToTicks(false);
        massSlider.setMaxSize(100, 50);

        Slider powerSlider = new Slider(5, 30, 5);
        powerSlider.setMajorTickUnit(5);
        powerSlider.setMinorTickCount(4);
        powerSlider.setShowTickMarks(true);
        powerSlider.setShowTickLabels(true);
        powerSlider.setSnapToTicks(false);
        powerSlider.setMaxSize(100, 50);

        Slider angleSlider = new Slider(5, 85, 5);
        angleSlider.setMajorTickUnit(25);
        angleSlider.setShowTickMarks(true);
        angleSlider.setShowTickLabels(true);
        angleSlider.setSnapToTicks(false);
        angleSlider.setMaxSize(100, 50);
        angleSlider.setOnMouseReleased(event -> {
            shootAnimation.initialPosition(images.getArrowView());
            setAngle.setAngle(images.getBowView(), images.getArrowView(), angleSlider.getValue());
        });

        //labels for sliders, wind info, shots left
        Label massLabel = new Label("Arrow mass");
        massLabel.setFont(Font.font(18));
        massLabel.setTextFill(Color.BLACK);
        massLabel.setTextAlignment(TextAlignment.CENTER);

        Label powerLabel = new Label("Shot power");
        powerLabel.setFont(Font.font(18));
        powerLabel.setTextFill(Color.BLACK);
        powerLabel.setTextAlignment(TextAlignment.CENTER);

        Label angleLabel = new Label("Shot angle");
        angleLabel.setFont(Font.font(18));
        angleLabel.setTextFill(Color.BLACK);
        angleLabel.setTextAlignment(TextAlignment.CENTER);

        Label windPowerLabel = new Label("Wind speed: " + 0);
        windPowerLabel.setFont(Font.font(16));
        windPowerLabel.setTextFill(Color.BLACK);
        windPowerLabel.setTextAlignment(TextAlignment.CENTER);
        windPowerLabel.setWrapText(true);

        Label windDirectionLabel = new Label("Wind direction");
        windDirectionLabel.setFont(Font.font(16));
        windDirectionLabel.setTextFill(Color.BLACK);
        windDirectionLabel.setTextAlignment(TextAlignment.CENTER);
        windPowerLabel.setWrapText(true);

        Label chancesLabel = new Label("Chances: 0");
        chancesLabel.setFont(Font.font(16));
        chancesLabel.setTextFill(Color.BLACK);
        chancesLabel.setTextAlignment(TextAlignment.CENTER);
        windPowerLabel.setWrapText(true);

        //start game button
        Button buttonStart = new Button("NEW GAME");
        buttonStart.setFont(Font.font(22));
        buttonStart.setPrefSize(230, 50);
        buttonStart.setLayoutX(250);
        buttonStart.setLayoutY(300);

        //shoot arrow button
        Button buttonShoot = new Button("GO!", images.getFlameView());
        buttonShoot.setFont(Font.font(20));
        buttonShoot.setMaxSize(110, 40);
        buttonShoot.setVisible(false);
        images.getFlameView().setFitWidth(30);
        images.getFlameView().setPreserveRatio(true);

        //actions for buttons
        buttonShoot.setOnAction(e -> {
            takeShoot(massSlider.getValue(), powerSlider.getValue(), angleSlider.getValue(), setWind.getWindSpeed());
            checkResult(buttonShoot, buttonStart, primaryStage, chancesLabel);
            System.out.println("aimed shot :  " + shootCalculator.checkHit(images.getShieldView()));
        });

        buttonStart.setOnAction(event -> {
            //prepare game area: shield position and wind power and direction
            prepareGameplay(chancesLabel, windPowerLabel, buttonStart, buttonShoot, scoreChecker);
        });

        //vbox for sliders, labels and button
        VBox controlBox = new VBox();
        controlBox.setMaxSize(250, 320);
        controlBox.setSpacing(5);
        controlBox.setAlignment(Pos.CENTER);

        controlBox.getChildren().add(massLabel);
        controlBox.getChildren().add(massSlider);
        VBox.setMargin(massSlider, new Insets(0, 10, 10,10));

        controlBox.getChildren().add(powerLabel);
        controlBox.getChildren().add(powerSlider);
        VBox.setMargin(powerSlider, new Insets(0, 10, 10,10));

        controlBox.getChildren().add(angleLabel);
        controlBox.getChildren().add(angleSlider);
        VBox.setMargin(angleSlider, new Insets(0, 10, 10,10));

        controlBox.getChildren().add(buttonShoot);

        //HBox for wind and chances
        HBox infoBox = new HBox();
        infoBox.setMaxSize(600, 100);
        infoBox.setSpacing(20);
        infoBox.setAlignment(Pos.CENTER);
        infoBox.getChildren().addAll(chancesLabel, windPowerLabel, windDirectionLabel, images.getWindArrow());

        //group: shield, bow, archer, arrow, wind direction arrow
        Group group = new Group();

        images.getShieldView().setFitWidth(35);
        images.getShieldView().setFitHeight(160);
        images.getShieldView().setX(5);
        images.getShieldView().setY(350);
        group.getChildren().add(images.getShieldView());

        images.getArrowView().setFitWidth(100);
        images.getArrowView().setPreserveRatio(true);
        images.getArrowView().setX(615);
        images.getArrowView().setY(440);
        group.getChildren().add(images.getArrowView());

        images.getArcherView().setFitHeight(200);
        images.getArcherView().setPreserveRatio(true);
        images.getArcherView().setX(660);
        images.getArcherView().setY(360);
        group.getChildren().add(images.getArcherView());

        images.getBowView().setFitHeight(150);
        images.getBowView().setPreserveRatio(true);
        images.getBowView().setX(640);
        images.getBowView().setY(370);
        group.getChildren().add(images.getBowView());

        images.getWindArrow().setFitHeight(40);
        images.getWindArrow().setFitWidth(60);
        images.getWindArrow().setX(400);
        images.getWindArrow().setY(10);

        Rectangle rectangleYellow = new Rectangle(50, 0, 500, 60);
        rectangleYellow.setFill(Color.YELLOW);
        rectangleYellow.setViewOrder(1);

        //root node for scene
        AnchorPane anchorPaneGameArea = new AnchorPane();
        anchorPaneGameArea.setBackground(images.getBackground());
        AnchorPane.setTopAnchor(controlBox, 15.0);
        AnchorPane.setRightAnchor(controlBox, 15.0);
        AnchorPane.setLeftAnchor(infoBox, 55.0);
        AnchorPane.setTopAnchor(infoBox, 10.0);
        anchorPaneGameArea.getChildren().addAll(controlBox, infoBox, group, rectangleYellow, buttonStart);

        //scene creating with root node, width, height
        Scene scene = new Scene(anchorPaneGameArea);
        scene.setFill(Color.WHEAT);
        scene.setCursor(images.getCursor());

        //main window properties
        primaryStage.setTitle("JavaFX archery");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(false);

        //adding scene to stage
        primaryStage.setScene(scene);

        //showing stage in window
        primaryStage.show();
    }

    public void prepareGameplay(Label chancesLabel, Label windPowerLabel, Button buttonStart,
                                Button buttonShoot, ScoreChecker scoreChecker) {

        placeShield.placeShield(images.getShieldView());
        setWind.setWindDirection(images.getWindArrow());
        setWind.setWindSpeed();
        scoreChecker.setChances(3);
        scoreChecker.setScore(0);
        chancesLabel.setText("Chances:  " + scoreChecker.getChances());
        windPowerLabel.setText("Wind speed: " + setWind.getWindSpeedForLabel());
        buttonStart.setVisible(false);
        buttonShoot.setVisible(true);

    }

    public void takeShoot(double mass, double power, double angle, int windSpeed) {

        setAngle.setAngle(images.getBowView(), images.getArrowView(), angle);
        shootCalculator.calculateCurve(mass, power, angle, windSpeed);
        shootAnimation.shootTo(shootCalculator.getControlX(),
                shootCalculator.getControlY(),
                shootCalculator.getXFinal(),
                shootCalculator.getYFinal(),
                angle,
                images.getArrowView());

    }

    public void checkResult(Button buttonShoot, Button buttonStart, Stage primaryStage, Label chancesLabel) {

       int result = scoreChecker.checkScore(shootCalculator.checkHit(images.getShieldView()));

       chancesLabel.setText("Chances:  " + scoreChecker.getChances());

       Text summaryText = new Text();
       summaryText.setFont(Font.font(22));
       summaryText.setTextAlignment(TextAlignment.CENTER);

       Button exitButton = new Button("Exit game");
       exitButton.setPrefSize(200, 100);
       exitButton.setFont(Font.font(20));

       Button playAgainButton = new Button("Play again");
       playAgainButton.setPrefSize(200, 100);
       playAgainButton.setFont(Font.font(20));

       VBox summaryVBox = new VBox();
       summaryVBox.setAlignment(Pos.CENTER);

       Scene summaryScene = new Scene(summaryVBox, 300, 350, Color.CORNFLOWERBLUE);
       Stage summaryStage = new Stage();

       summaryStage.setResizable(false);
       summaryStage.initStyle(StageStyle.TRANSPARENT);
       summaryStage.initModality(Modality.APPLICATION_MODAL);
       summaryStage.initOwner(primaryStage);
       summaryStage.setAlwaysOnTop(true);
       summaryStage.setTitle("Play finished");
       summaryStage.setWidth(250);
       summaryStage.setHeight(300);

       summaryVBox.getChildren().addAll(summaryText, playAgainButton, exitButton);
       summaryStage.setScene(summaryScene);

       exitButton.setOnAction(event -> {
            summaryStage.close();
            primaryStage.close();
       });

       playAgainButton.setOnAction(event -> {
            buttonStart.setVisible(true);
            summaryStage.close();
       });

       if(result == 1 && scoreChecker.getScore() > 0) {
           buttonShoot.setVisible(false);
           summaryStage.show();
           summaryText.setText("\nWinner!!!\n");
       } else if (result == 1 && scoreChecker.getScore() <= 0){
           buttonShoot.setVisible(false);
           summaryStage.show();
           summaryText.setText("\n  You had no luck...\n");
       }
    }
}
