package de.craid.bound.multiplayer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.craid.bound.Player;
import de.craid.bound.net.Client;

public class MultiplayerTestGameScreen implements Screen{
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Game game;
	private Player player;

	public MultiplayerTestGameScreen(SpriteBatch batch, Game game) {
		this.batch = batch;
		this.game = game;
	}
	
	public void show() {
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(5, h/w*5);
		batch = new SpriteBatch();
		
		player = new Player(1);
		
		
	}

	public void render(float deltaTime) {
		receive();
		update(deltaTime);
		send();
		renderAll();
	}

	private void receive() {
		player.id = Client.singleton.player.id;
	}

	private void update(float deltaTime) {
		player.update(deltaTime);
	}

	private void send() {
		Client.singleton.sendPlayerData();
	}

	private void renderAll() {
		Gdx.gl.glClearColor(0.1f, 0.8f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		player.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}

	public void resize(int width, int height) {}
	public void pause() {}
	public void resume() {}
	public void hide() {}

}
