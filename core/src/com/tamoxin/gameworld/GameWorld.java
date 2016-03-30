package com.tamoxin.gameworld;

import com.tamoxin.gameobjects.Frog;

/**
 * Created by Marco on 3/27/2016.
 */
public class GameWorld {

    private Frog leftFrog, rightFrog;

    public GameWorld(int bottom) {
        leftFrog = new Frog(2, bottom - 28, 26, 23);
        rightFrog = new Frog(73, bottom - 28, 26, 23);
    }

    public void update(float delta) {
        leftFrog.update(delta);
    }

    public Frog getLeftFrog() {
        return leftFrog;
    }

    public Frog getRightFrog() {
        return rightFrog;
    }
}
