package com.tamoxin.helpers;

import com.badlogic.gdx.InputProcessor;
import com.tamoxin.gameobjects.Frog;

/**
 * Created by Marco on 3/27/2016.
 */
public class InputHandler implements InputProcessor {

    private Frog frogLeft;
    private Frog frogRight;

    public InputHandler(Frog frogLeft, Frog frogRight) {
        this.frogLeft = frogLeft;
        this.frogRight = frogRight;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        frogLeft.onClick();
        frogRight.onClick();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
