package de.craid.bound;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MultiplayerTestGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Player player;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(5, h/w*5);
		batch = new SpriteBatch();
		
		player = new Player(1);
		
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	//Der Startpunkt
	@Override
	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		
		//receive();
		update(deltaTime);
		renderAll();
		//send();
		
	}

	private void renderAll() {
		Gdx.gl.glClearColor(0.1f, 0.8f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		player.render(batch);
		batch.end();
	}

	private void update(float deltaTime) {
		player.update(deltaTime);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	
}