package com.kodilla.archergame;

import javafx.scene.image.ImageView;

import java.util.Random;

public class WindSettingsCalculator {

    Random randomGenerator = new Random();

    private int windDirection = 0;
    private int windSpeed = 0;

    public void setWindDirection(ImageView windArrowImage) {
        windDirection = randomGenerator.nextInt(2);

        if(windDirection == 0) {
            windArrowImage.setScaleX(1);  //left
        } else {
            windArrowImage.setScaleX(-1);  //right
        }
    }

    public void setWindSpeed() {

        if(windDirection == 0) {
            windSpeed = randomGenerator.nextInt(20) + 1;;
        } else {
            windSpeed = (-1) * (randomGenerator.nextInt(20) + 1);
        }
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getWindSpeedForLabel() {
        if(windSpeed >= 0) {
            return windSpeed;
        } else {
            return windSpeed * (-1);
        }
    }
}
