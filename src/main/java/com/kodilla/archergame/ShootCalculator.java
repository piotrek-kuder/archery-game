package com.kodilla.archergame;

import javafx.scene.image.ImageView;

public class ShootCalculator {

    private double controlX = 0;
    private double controlY = 0;
    private double xFinal = 0;
    private double yFinal = 0;
    private double yArrow;
    private final double xArrowPosition = 665;
    private double yArrowPosition = 0;

    public void calculateCurve(double mass, double power, double angle, int windSpeed) {

        double angleInRadians = Math.toRadians(angle);
        double f = 9.82 * power;  // shoot power
        double s = 0.5;        //dlugosc naciagu luku
        double vStart = Math.sqrt((f * s)/(mass * .001));
        double x = 0;
        double y = 0;

        while (y >= 0) {
            xFinal = x;
            yFinal = y;
            y = 2.5 * (x * Math.tan(angleInRadians) - ((9.81 * Math.pow(x, 2))/(2 * Math.pow(((vStart + .6 * windSpeed) * Math.cos(angleInRadians)), 2))));
            x++;
        }

        controlX = xFinal/2;
        controlY = 2.5 * (controlX * Math.tan(angleInRadians) - ((9.81 * Math.pow(controlX, 2))/(2 * Math.pow(((vStart + .6 * windSpeed) * Math.cos(angleInRadians)), 2))));
        yArrow = 2.5 * (xArrowPosition * Math.tan(angleInRadians) - ((9.81 * Math.pow(xArrowPosition, 2))/(2 * Math.pow(((vStart + .6 * windSpeed) * Math.cos(angleInRadians)), 2))));
        yArrowPosition = 440 - yArrow;
    }

    public boolean checkHit(ImageView shield) {

        System.out.println("\nyArrowPosition  " + yArrowPosition);

        double shieldBeginPosition = shield.getY();
        double shieldEndPosition = shieldBeginPosition + 160;

        System.out.println(" shield begin:  " + shieldBeginPosition + "  shield end:  " + shieldEndPosition);

        return yArrowPosition > shieldBeginPosition && yArrowPosition < shieldEndPosition;
    }

    public double getControlX() {
        return 665 - controlX;
    }

    public double getControlY() {
        return 440 - controlY;
    }

    public double getXFinal() {
        if(655 - xFinal < 0) {
            return 50;
        }
        return 655 - xFinal;
    }

    public double getYFinal() {
        if(665 - xFinal < 0) {
            //System.out.println("ret 1  " + (yArrowPosition - 20));
            return yArrowPosition - 25;
        }
        if(yFinal > 0) {
            //System.out.println("ret 2  " + yArrowPosition);
            return 430 - yFinal;
        } else {
            //System.out.println("ret 3  " + yArrowPosition);
            return 430 + yFinal;
        }
    }
}
