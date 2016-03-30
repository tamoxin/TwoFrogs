package com.tamoxin.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Marco on 3/27/2016.
 */
public class AssetLoader {

    // Background assets
    public static Texture bg;
    public static TextureRegion background;

    // Left Frog assets
    public static Texture leftFrogTexture;
    public static Animation leftFrogAnimation;
    public static TextureRegion lFrog, lFrogStart, lFrogEnd;

    // Right Frog assets
    public static Texture rightFrogTexture;
    public static Animation rightFrogAnimation;
    public static TextureRegion rFrog, rFrogStart, rFrogEnd;

    public static void load() {

        // Background
        bg = new Texture(Gdx.files.internal("data/background.png"));
        bg.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        background = new TextureRegion(bg);

        // Left Frog
        leftFrogTexture = new Texture(Gdx.files.internal("data/leftFrog.png"));
        leftFrogTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        lFrog = new TextureRegion(leftFrogTexture, 0, 0, 22, 23);
        lFrog.flip(false, true);
        lFrogStart = new TextureRegion(leftFrogTexture, 23, 0, 26, 23);
        lFrogStart.flip(false, true);
        lFrogEnd = new TextureRegion(leftFrogTexture, 50, 0, 26, 23);
        lFrogEnd.flip(false, true);

        TextureRegion[] leftFrog = { lFrog, lFrogStart, lFrogEnd };
        leftFrogAnimation = new Animation(0.15f, leftFrog);
        leftFrogAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // Right Frog
        rightFrogTexture = new Texture(Gdx.files.internal("data/rightFrog.png"));
        rightFrogTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        rFrog = new TextureRegion(rightFrogTexture, 0, 0, 22, 23);
        rFrog.flip(false, true);
        rFrogStart = new TextureRegion(rightFrogTexture, 23, 0, 26, 23);
        rFrogStart.flip(false, true);
        rFrogEnd = new TextureRegion(rightFrogTexture, 50, 0, 26, 23);
        rFrogEnd.flip(false, true);

        TextureRegion[] rightFrog = {rFrog, rFrogStart, rFrogEnd};
        rightFrogAnimation = new Animation(0.15f, rightFrog);
        rightFrogAnimation.setPlayMode(Animation.PlayMode.LOOP);


    }

    public static void dispose() {
        bg.dispose();
        leftFrogTexture.dispose();
    }
}
