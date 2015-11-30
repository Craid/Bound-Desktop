package de.craid.bound.multiplayer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MultiplayerTestGame extends Game {

	SpriteBatch batch;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		Screen screen = new MultiplayerTestLoginScreen();
		setScreen(screen);
	}

	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		screen.render(deltaTime);
	}

	public void pause() {}
	public void resume() {}
	public void dispose() {}
	public void resize(int width, int height) {}
	
}