package com.tamoxin.gameobjects;

import java.util.Random;

/**
 * Created by Marco on 3/29/2016.
 */
public class Fly extends Scrollable {

    public Fly(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }

    @Override
    public void reset(float newY) {
        super.reset(newY);
    }
}
