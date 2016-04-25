package com.tamoxin.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.tamoxin.gameworld.GameRenderer;
import com.tamoxin.gameworld.GameWorld;
import com.tamoxin.helpers.InputHandler;

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

        gameWorld = new GameWorld(bottom);
        Gdx.input.setInputProcessor(new InputHandler(gameWorld, screenWidth / gameWidth, screenHeight / gameHeight));
        gameRenderer = new GameRenderer(gameWorld, bottom);
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        gameWorld.update(delta);
        gameRenderer.render(delta, runTime);
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
