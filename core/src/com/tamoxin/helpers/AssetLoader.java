package com.tamoxin.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Marco on 3/27/2016.
 */
public class AssetLoader {

    public static Texture bg;
    public static TextureRegion background;

    public static Texture leftFrogTexture;
    public static Animation leftFrogAnimation;
    public static TextureRegion lFrog, lFrogStart, lFrogEnd;

    public static void load() {

        // Background
        bg = new Texture(Gdx.files.internal("data/background.png"));
        bg.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        background = new TextureRegion(bg);

        //Frogs
        leftFrogTexture = new Texture(Gdx.files.internal("data/leftFrog.png"));
        leftFrogTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        lFrog = new TextureRegion(leftFrogTexture, 0, 0, 44, 36);
        lFrog.flip(false, true);
        lFrogStart = new TextureRegion(leftFrogTexture, 45, 0, 52, 36);
        lFrogStart.flip(false, true);
        lFrogEnd = new TextureRegion(leftFrogTexture, 98, 0, 52, 46);
        lFrogEnd.flip(false, true);

        TextureRegion[] leftFrog = { lFrog, lFrogStart, lFrogEnd };
        leftFrogAnimation = new Animation(0.15f, leftFrog);
        leftFrogAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public static void dispose() {
        bg.dispose();
        leftFrogTexture.dispose();
    }
}
