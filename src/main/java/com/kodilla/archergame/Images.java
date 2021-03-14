package com.kodilla.archergame;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class Images {

    //cursor
    private ImageCursor cursor = new ImageCursor(new Image("file:src/main/resources/cursor.png"));

    //game objects
    private ImageView flameView = new ImageView(new Image("file:src/main/resources/flame.png"));
    private ImageView bowView = new ImageView(new Image("file:src/main/resources/bow.png"));
    private ImageView shieldView = new ImageView(new Image("file:src/main/resources/shield.png"));
    private ImageView archerView = new ImageView(new Image("file:src/main/resources/archer.png"));
    private ImageView arrowView = new ImageView(new Image("file:src/main/resources/arrow.png"));

    //backgroung
    private Image backImage = new Image("file:src/main/resources/background.png");
    private BackgroundSize backgroundSize = new BackgroundSize(100, 100,
            true, true, true, false);
    private BackgroundImage backgroundImage = new BackgroundImage(backImage,
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT, backgroundSize);
    private Background background = new Background(backgroundImage);

    public ImageCursor getCursor() {
        return cursor;
    }

    public ImageView getFlameView() {
        return flameView;
    }

    public ImageView getBowView() {
        return bowView;
    }

    public ImageView getShieldView() {
        return shieldView;
    }

    public ImageView getArcherView() {
        return archerView;
    }

    public Image getBackImage() {
        return backImage;
    }

    public BackgroundSize getBackgroundSize() {
        return backgroundSize;
    }

    public BackgroundImage getBackgroundImage() {
        return backgroundImage;
    }

    public Background getBackground() {
        return background;
    }

    public ImageView getArrowView() {
        return arrowView;
    }
}
