package com.tamoxin.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.tamoxin.gameworld.GameRenderer;
import com.tamoxin.gameworld.GameWorld;
import com.tamoxin.helpers.InputHandler;

/**
 * Created by Marco on 3/18/2016.
 */
public class GameScreen implements Screen {

    private GameWorld gameWorld;
    private GameRenderer gameRenderer;
    private float runTime = 0;

    public GameScreen() {
        Gdx.app.log("GameScreen", "Attached");

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int bottom = (int) gameHeight;
        Gdx.app.log("Game Height", gameHeight+"");
        Gdx.app.log("Screen Height", screenHeight+"");
        Gdx.app.log("Game Width", gameWidth+"");
        Gdx.app.log("Screen Width", screenWidth+"");
        Gdx.app.log("Bottom", bottom+"");

        gameWorld = new GameWorld(bottom);
        gameRenderer = new GameRenderer(gameWorld, bottom);
        Gdx.input.setInputProcessor(new InputHandler(gameWorld.getLeftFrog(), gameWorld.getRightFrog()));
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        gameWorld.update(delta);
        gameRenderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void dispose() {
        //Leave blank
    }
}
