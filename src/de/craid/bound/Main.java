package de.craid.bound;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Bound";
		cfg.width = 720;
		cfg.height = 480;
		cfg.backgroundFPS = 0;
		cfg.foregroundFPS = 0;
		cfg.vSyncEnabled = false;
		cfg.useGL30 = false;
		new LwjglApplication(new MultiplayerTestGame(), cfg);
	}
}
