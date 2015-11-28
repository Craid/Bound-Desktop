package de.craid.bound.multiplayer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MultiplayerTestLoginScreen implements Screen {

	private Stage stage;
	private SpriteBatch batch;
	private Table table;
	private Game game;

	public MultiplayerTestLoginScreen(SpriteBatch batch, Game game) {
		this.batch = batch;
		this.game = game;
	}

	@Override
	public void show() {
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		Skin skin = new Skin(Gdx.files.internal("style/uiskin.json"));

		Label nameLabel = new Label("Name:", skin);
		nameLabel.setX(320-40);
		nameLabel.setY(240);
		TextField nameText = new TextField("Craid", skin);
		nameText.setX(320+40);
		nameText.setY(240);
		Label addressLabel = new Label("Address:", skin);
		addressLabel.setX(320-40);
		addressLabel.setY(180);
		TextField addressText = new TextField("localhost", skin);
		addressText.setX(320+40);
		addressText.setY(180);
		Label portLabel = new Label("Port:", skin);
		portLabel.setX(320-40);
		portLabel.setY(120);
		TextField portText = new TextField("8082", skin);
		portText.setX(320+40);
		portText.setY(120);
		
		Button b = new Button(skin,"default");
		b.setName("Connect");
		b.setX(320);
		b.setY(40);

		stage.addActor(portText);
		stage.addActor(portLabel);
		stage.addActor(addressText);
		stage.addActor(addressLabel);
		stage.addActor(nameText);
		stage.addActor(nameLabel);
		stage.addActor(b);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	public void pause() {
	}

	public void resume() {
	}

	public void hide() {
	}

	public void dispose() {
		stage.dispose();
	}

}
