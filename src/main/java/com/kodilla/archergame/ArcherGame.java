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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ArcherGame extends Application {

    Images images = new Images();
    ShootTo shoot = new ShootTo();
    SetAngle setAngle = new SetAngle();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // title - main window
        primaryStage.setTitle("JavaFX archery");

        //set style decoration: DECORATED UNDECORATED TRANSPARENT UNIFIED UTILITY
        primaryStage.initStyle(StageStyle.DECORATED);

        //stage width and height and resizable
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);

        //sliders
        Slider massSlider = new Slider(10, 30, 10);
        massSlider.setMajorTickUnit(5);
        massSlider.setMinorTickCount(4);
        massSlider.setShowTickMarks(true);
        massSlider.setShowTickLabels(true);
        massSlider.setSnapToTicks(false);
        massSlider.setMaxSize(100, 50);

        Slider powerSlider = new Slider(5, 45, 3);
        powerSlider.setMajorTickUnit(5);
        powerSlider.setMinorTickCount(4);
        powerSlider.setShowTickMarks(true);
        powerSlider.setShowTickLabels(true);
        powerSlider.setSnapToTicks(false);
        powerSlider.setMaxSize(100, 50);

        Slider angleSlider = new Slider(5, 85, 5);
        angleSlider.setMajorTickUnit(15);
        angleSlider.setMinorTickCount(4);
        angleSlider.setShowTickMarks(true);
        angleSlider.setShowTickLabels(true);
        angleSlider.setSnapToTicks(false);
        angleSlider.setMaxSize(100, 50);
        //angleSlider.setOnMouseDragged(event -> System.out.println("angleSlider move" + angleSlider.getValue()));
        //angleSlider.setOnMouseDragged(event -> setAngle.setAngle(images.getBowView(), angleSlider.getValue()));
        angleSlider.setOnMouseReleased(event -> setAngle.setAngle(images.getBowView(), angleSlider.getValue()));

        //labels for sliders
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

        //start button
        Button buttonStart = new Button("GO!", images.getFlameView());
        buttonStart.setFont(Font.font(20));
        buttonStart.setMaxSize(110, 40);
        images.getFlameView().setFitWidth(30);
        images.getFlameView().setPreserveRatio(true);
        buttonStart.setOnAction( e -> {
            shoot.shootTo(massSlider.getValue(), powerSlider.getValue(), angleSlider.getValue(), images.getArrowView());
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

        controlBox.getChildren().add(buttonStart);

        //group: shield, bow, archer, arrow
        Group group = new Group();
        images.getShieldView().setFitWidth(35);
        images.getShieldView().setFitHeight(150);
        images.getShieldView().setX(5);
        images.getShieldView().setY(350);
        group.getChildren().add(images.getShieldView());

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

        images.getArrowView().setFitWidth(100);
        images.getArrowView().setPreserveRatio(true);
        images.getArrowView().setX(540);
        images.getArrowView().setY(450);
        group.getChildren().add(images.getArrowView());

        Circle circle = new Circle(370, 200, 3, Color.RED);

        //root node for scene
        AnchorPane gameArea = new AnchorPane();
        gameArea.setBackground(images.getBackground());
        AnchorPane.setTopAnchor(controlBox, 15.0);
        AnchorPane.setRightAnchor(controlBox, 15.0);
        gameArea.getChildren().addAll(controlBox, group, circle);

        //scene creating with root node, width, height
        Scene scene = new Scene(gameArea);
        scene.setFill(Color.WHEAT);
        scene.setCursor(images.getCursor());

        //adding scene to stage
        primaryStage.setScene(scene);

        //showing stage in window
        primaryStage.show();

    }
}
