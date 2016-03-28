package com.tamoxin.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tamoxin.gameobjects.Frog;
import com.tamoxin.helpers.AssetLoader;

/**
 * Created by Marco on 3/27/2016.
 */
public class GameRenderer {

    private GameWorld gameWorld;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batch;

    private int gameHeight;
    private int bottom;

    public GameRenderer(GameWorld world, int bottom) {
        gameWorld = world;

        this.gameHeight = gameHeight;
        this.bottom = bottom;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, bottom);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render(float runTime) {
        Frog leftFrog = gameWorld.getLeftFrog();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.disableBlending();
        batch.draw(AssetLoader.background, 0, 0, 136, bottom);

        batch.enableBlending();

        batch.draw(AssetLoader.leftFrogAnimation.getKeyFrame(runTime),
                leftFrog.getX(), leftFrog.getY(), leftFrog.getWidth(), leftFrog.getHeight());

        batch.end();
    }
}
