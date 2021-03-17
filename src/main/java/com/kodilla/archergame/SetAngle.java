package com.kodilla.archergame;

import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class SetAngle {

    double startAngle = 0;
    double finalAngle = 0;

    public void setAngle(ImageView image, double angle) {

        //ujemne kąty
            finalAngle = angle - startAngle;
            startAngle = angle;

        //System.out.println("angle::: " + angle);

        image.setX(640);
        image.setY(370);

        Rotate rotateTransform = new Rotate();
        rotateTransform.setAngle(finalAngle);
        rotateTransform.setPivotX(715);
        rotateTransform.setPivotY(450);

        image.getTransforms().add(rotateTransform);

        System.out.println("st: " + startAngle + "  an: " + angle + "  fi: " + finalAngle);
    }
}
