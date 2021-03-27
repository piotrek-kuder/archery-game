package com.kodilla.archergame;

import javafx.scene.image.ImageView;

import java.util.Random;

public class ShieldPositionCalculator {

    Random randomGenerator = new Random();

    public void placeShield(ImageView shield) {

        int shieldPosition = randomGenerator.nextInt(400);

        shield.setY(shieldPosition + 30);
    }

}
