package de.craid.bound.multiplayer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import de.craid.bound.net.Client;

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
		nameLabel.setX(320-20);
		nameLabel.setY(240);
		TextField nameText = new TextField("Craid", skin);
		nameText.setX(320+40);
		nameText.setY(240);
		Label addressLabel = new Label("Address:", skin);
		addressLabel.setX(320-34);
		addressLabel.setY(180);
		TextField addressText = new TextField("localhost", skin);
		addressText.setX(320+40);
		addressText.setY(180);
		Label portLabel = new Label("Eigener Port der verwendet werden soll:", skin);
		portLabel.setX(320-260);
		portLabel.setY(120);
		final TextField portText = new TextField("8083", skin);
		portText.setX(320+40);
		portText.setY(120);
		TextButton b = new TextButton("Connect",skin);
		b.setX(320);
		b.setY(60);
		b.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		        Client.singleton.ownPort = Integer.parseInt(portText.getText());
				System.out.println(Client.singleton.ownPort);
				do{
					System.out.println("Trying to get Connection");
					Client.singleton.startConnection();
				}while(Client.singleton.player.id == 0);
				System.out.println(Client.singleton.player.id);
		        return true;
		    }

		    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
		    }
		});

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
