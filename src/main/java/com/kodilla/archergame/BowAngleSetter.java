package com.kodilla.archergame;

import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class BowAngleSetter {



    public void setAngle(ImageView bow, ImageView arrow, double angle) {

        arrow.getTransforms().remove(0, arrow.getTransforms().size());
        bow.getTransforms().remove(0, bow.getTransforms().size());
        arrow.setRotate(0);

        Rotate rotateBow = new Rotate();
        rotateBow.setAngle(angle);
        rotateBow.setPivotX(720);
        rotateBow.setPivotY(450);
        bow.getTransforms().add(rotateBow);

        Rotate rotateArrow = new Rotate();
        rotateArrow.setAngle(angle);
        rotateArrow.setPivotX(715);
        rotateArrow.setPivotY(450);
        arrow.getTransforms().add(rotateArrow);
    }
}
