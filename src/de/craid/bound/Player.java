package de.craid.bound;

import java.nio.ByteBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {

	public Vector2 velocity;
	public Vector2 position;
	public Vector2 direction;
	public Texture texture;
	public Sprite sprite;
	public float scaler = 1f; // bestimmt die Skalierung der abgebildeten Textur
	public int id = -1; // Player ID
	private ByteBuffer bb;

	public Player(int id) {
		this();
		this.id = id;

	}

	public Player() {
		try {
			texture = new Texture(Gdx.files.internal("img/001.png"));
			texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			TextureRegion region = new TextureRegion(texture);
			sprite = new Sprite(region);

			sprite.setSize(0.4f, 0.4f * sprite.getHeight() / sprite.getWidth());
			sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
			sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
		} catch (Exception e) {
		}

		position = new Vector2(0, 0);
		direction = new Vector2();
		velocity = new Vector2();
		
		bb = ByteBuffer.allocate(Constants.PACKAGE_SIZE);
	}

	public void initialize() {
	}

	public void render(SpriteBatch batch) {
		sprite.setRotation(direction.angle());
		sprite.setPosition(position.x, position.y);
		sprite.draw(batch);
	}

	public void receive(byte[] a) {
		bb = ByteBuffer.wrap(a);
		id = bb.getInt();
		position.x = bb.getFloat();
		position.y = bb.getFloat();
		direction.x = bb.getFloat();
		direction.y = bb.getFloat();
	}

	public byte[] send() {
		ByteBuffer b = ByteBuffer.allocate(Constants.PACKAGE_SIZE);
		b.putInt(id);
		b.putFloat(position.x).putFloat(position.y);
		b.putFloat(direction.x).putFloat(direction.y);
		return b.array();
	}

	public void update(float deltaTime) {
		boolean up = Gdx.input.isKeyPressed(Input.Keys.UP);
		boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN);
		boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
		if (up)
			velocity.add(0, 1);
		if (down)
			velocity.add(0, -1);
		if (left)
			velocity.add(-1, 0);
		if (right)
			velocity.add(1, 0);

		if (!up && !down && !left && !right) {
			velocity.x *= 0.9995;
			velocity.y *= 0.9995;
		}

		velocity.limit(1);
		direction.set(velocity);
		direction.rotate(90f);
		direction.nor();

		position.x += velocity.x * deltaTime;
		position.y += velocity.y * deltaTime;

	}
}
