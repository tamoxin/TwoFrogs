package com.tamoxin.gameobjects;

import java.util.Random;

/**
 * Created by Marco on 3/29/2016.
 */
public class Fly extends Scrollable {

    // ID = 1
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
        side = random.nextInt(10);
        if(side < 5)
            setXPosition(originalX);
        else
            setXPosition(originalX + width + 20);
    }

    @Override
    protected void setCircle() {
        circle.set(position.x + width/2 - 1, position.y + height/2 + 1, width / 2 - 1);
    }
}
