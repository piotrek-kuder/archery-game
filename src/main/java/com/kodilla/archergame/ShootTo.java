package com.kodilla.archergame;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class ShootTo {
    public void shootTo(double mass, double power, double angle, ImageView arrow) {

        System.out.println("mass  " + mass + " :: power " + power + " :: angle " + angle);

        Path path = new Path();
        path.getElements().add(new MoveTo(540,450));
        //path.getElements().add(new CubicCurveTo(0, 0, 300, 300, 40, 400));
        path.getElements().add(new ArcTo(200, 200, 0, 20, 450, false, false));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(2000));
        pathTransition.setPath(path);
        pathTransition.setNode(arrow);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        //pathTransition.setCycleCount(Timeline.INDEFINITE);
        //pathTransition.setAutoReverse(true);

        pathTransition.play();
    }
}
