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

    // Left Crocodile assets
    public static Texture leftCrocodileTexture;
    public static Animation leftCrocodileAnimation;
    public static TextureRegion[] leftCrocodile = new TextureRegion[5];

    // Right Crocodile assets
    public static Texture rightCrocodileTexture;
    public static Animation rightCrocodileAnimation;
    public static TextureRegion[] rightCrocodile = new TextureRegion[5];

    // Left Fly assets
    private static Texture leftFlyTexture;
    private static Animation leftFlyAnimation;
    private static TextureRegion lFly1, lFly2, lFly3, lFly4;

    // Right Fly assets
    private static Texture rightFlyTexture;
    private static Animation rightFlyAnimation;
    private static TextureRegion rFly1, rFly2, rFly3, rFly4;

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

        // Left Crocodile
        leftCrocodileTexture = new Texture(Gdx.files.internal("data/leftCroc.png"));
        leftCrocodileTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        for(int i = 0, x = 0; i < leftCrocodile.length; i++, x+=32) {
            leftCrocodile[i] = new TextureRegion(leftCrocodileTexture, x, 0, 32, 35);
            leftCrocodile[i].flip(false, true);
        }
        leftCrocodileAnimation = new Animation(0.10f, leftCrocodile);
        leftCrocodileAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // Right Crocodile
        rightCrocodileTexture = new Texture(Gdx.files.internal("data/rightCroc.png"));
        rightCrocodileTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        for(int i = 0, x = 0; i < rightCrocodile.length; i++, x+=32) {
            rightCrocodile[i] = new TextureRegion(rightCrocodileTexture, x, 0, 32, 35);
            rightCrocodile[i].flip(false, true);
        }
        rightCrocodileAnimation = new Animation(0.10f, rightCrocodile);
        rightCrocodileAnimation.setPlayMode(Animation.PlayMode.LOOP);


    }

    public static void dispose() {
        bg.dispose();
        leftFrogTexture.dispose();
    }
}
