package com.tamoxin.gameworld;

import com.tamoxin.gameobjects.Frog;

/**
 * Created by Marco on 3/27/2016.
 */
public class GameWorld {

    private Frog leftFrog;

    public GameWorld(int bottom) {
        leftFrog = new Frog(6, bottom - 23, 20, 18);
    }

    public void update(float delta) {
        leftFrog.update(delta);
    }

    public Frog getLeftFrog() {
        return leftFrog;
    }
}
