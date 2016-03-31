package com.tamoxin.gameobjects;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Marco on 3/30/2016.
 */
public class Crocodile extends Scrollable{

    private float originalY;

    public Crocodile(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        originalY = y;
    }

    // Puts the position in (0, 0)
    public Crocodile(int width, int height, float scrollSpeed) {
        super(width, height, scrollSpeed);
    }

    @Override
    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

        if(position.y > gameHeight + 10)
            position.y = originalY;
    }

    public float getOriginalY() {
        return originalY;
    }

    @Override
    public void reset(float newY) {
        super.reset(newY);
    }
}
