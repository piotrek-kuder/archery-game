package com.kodilla.archergame;

import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.util.Duration;

public class ShootAnimation {
    public void shootTo(double controlX, double controlY, double x, double y, double angle, ImageView arrow) {

        //a very important line :)
        arrow.getTransforms().remove(0, arrow.getTransforms().size());

        Path path = new Path();
        path.getElements().add(new MoveTo(665,440 - angle));
        path.getElements().add(new QuadCurveTo(controlX, controlY, x, y));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(1000));
        pathTransition.setPath(path);
        pathTransition.setNode(arrow);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(800), arrow);
        rotateTransition.setFromAngle(angle);
        rotateTransition.setToAngle(-1 * angle);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(pathTransition, rotateTransition);
        parallelTransition.play();
       // parallelTransition.setOnFinished(event -> System.out.println("arrow y position" + + arrow.getTranslateY()));
    }

    public void initialPosition(ImageView arrow) {

        Path path = new Path();
        path.getElements().add(new MoveTo(20,450));
        path.getElements().add(new QuadCurveTo(370, 200, 665, 450));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(1));
        pathTransition.setPath(path);
        pathTransition.setNode(arrow);
        pathTransition.play();
    }
}
