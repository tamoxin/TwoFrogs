package com.tamoxin.twofrogs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tamoxin.helpers.AssetLoader;
import com.tamoxin.screens.GameScreen;
import com.tamoxin.screens.SplashScreen;

public class TwoFrogs extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
