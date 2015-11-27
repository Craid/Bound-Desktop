package de.craid.bound.multiplayer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MultiplayerTestLoginScreen implements Screen{

	private Stage stage;
	private Table table;
	private Game game;
	
	public MultiplayerTestLoginScreen(Game game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		stage = new Stage(new FitViewport(640,480));
		
		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	public void resize(int width, int height) {
		stage.getViewport().update(width, height,true);
	}
	public void pause() {}
	public void resume() {}
	public void hide() {}
	
	public void dispose() {
		stage.dispose();
	}

}
