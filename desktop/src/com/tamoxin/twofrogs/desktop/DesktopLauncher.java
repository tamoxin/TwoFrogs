package com.tamoxin.twofrogs.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tamoxin.twofrogs.TwoFrogs;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Two Frogs";
		config.width = 272;
		config.height = 408;
		new LwjglApplication(new TwoFrogs(), config);
	}
}
