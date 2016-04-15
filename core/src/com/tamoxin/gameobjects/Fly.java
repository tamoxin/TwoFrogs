package com.tamoxin.gameobjects;

import java.util.Random;

/**
 * Created by Marco on 3/29/2016.
 */
public class Fly extends Scrollable {

    public Fly(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed, 1);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void reset(float newY) {
        super.reset(newY);
        setRandomSide();
    }

    @Override
    protected void setRandomSide() {
        side = random.nextInt(2);
        if(side == 0)
            setXPosition(originalX);
        else
            setXPosition(originalX + width + 20);
    }
}
