package com.kodilla.archergame;

import javafx.scene.image.ImageView;

public class ScoreChecker {

    private int chances =  0;
    private int score = 0;

    //return 0 = continue playing      1 = finish and check if winner
    public int checkScore(boolean aimedShot) {

        if(aimedShot) {
            score++;
            return 1;
        }

        if(chances <= 1) {
            return 1;
        } else {
            chances--;
            return 0;
        }
    }

    public int getScore() {
        return score;
    }

    public int getChances() {
        return chances;
    }

    public void setChances(int chances) {
        this.chances = chances;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
