package com.tamoxin.gameobjects;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Marco on 3/30/2016.
 */
public class Crocodile extends Scrollable{

    public Crocodile(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed, 0);
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
            setXPosition(originalX + width + 3);
    }
}
