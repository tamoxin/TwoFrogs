package com.tamoxin.twofrogs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tamoxin.helpers.AssetLoader;
import com.tamoxin.screens.GameScreen;

public class TwoFrogs extends Game {

	@Override
	public void create() {
		Gdx.app.log("ZBGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
