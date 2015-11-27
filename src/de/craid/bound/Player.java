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
	public int id = 0; // Player ID

	public Player(int id) {
		this();
		this.id = id;

	}

	public Player() {
		texture = new Texture(Gdx.files.internal("img/001.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		TextureRegion region = new TextureRegion(texture);
		sprite = new Sprite(region);

		sprite.setSize(0.4f, 0.4f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);

		position = new Vector2(0, 0);
		direction = new Vector2();
		velocity = new Vector2();
	}

	public void initialize() {
	}

	public void render(SpriteBatch batch) {
		sprite.setRotation(direction.angle());
		sprite.setPosition(position.x, position.y);
		sprite.draw(batch);
	}

	public void receive(byte[] a) {
		byte b[];

		id = (int) ((a[0] & 0xFF) * (int) Math.pow(2, 24))
				+ (int) ((a[1] & 0xFF) * (int) Math.pow(2, 16))
				+ (int) ((a[2] & 0xFF) * (int) Math.pow(2, 8))
				+ (int) (a[3] & 0xFF);

		b = new byte[] { a[4], a[5], a[6], a[7] };
		position.x = ByteBuffer.wrap(b).getFloat();

		b = new byte[] { a[8], a[9], a[10], a[11] };
		position.y = ByteBuffer.wrap(b).getFloat();

		b = new byte[] { a[12], a[13], a[14], a[15] };
		direction.x = ByteBuffer.wrap(b).getFloat();

		b = new byte[] { a[16], a[17], a[18], a[19] };
		direction.y = ByteBuffer.wrap(b).getFloat();
	}

	public byte[] send() {
		byte[] b = new byte[20];

		// id
		b[0] = (byte) ((id >>> 24)); // bit-shift... 24 verschoben
		b[1] = (byte) ((id >>> 16));
		b[2] = (byte) ((id >>> 8));
		b[3] = (byte) ((id));
		// Position x
		int px = Float.floatToIntBits(position.x);
		b[4] = (byte) ((px >> 24)); // bit-shift... 24 verschoben
		b[5] = (byte) ((px >> 16));
		b[6] = (byte) ((px >> 8));
		b[7] = (byte) ((px));
		// Position y
		int py = Float.floatToIntBits(position.y);
		b[8] = (byte) ((py >> 24)); // bit-shift... 24 verschoben
		b[9] = (byte) ((py >> 16));
		b[10] = (byte) ((py >> 8));
		b[11] = (byte) ((py));
		// Direction x
		int dx = Float.floatToIntBits(direction.x);
		b[12] = (byte) ((dx >> 24));
		b[13] = (byte) ((dx >> 16));
		b[14] = (byte) ((dx >> 8));
		b[15] = (byte) ((dx));
		// Direction y
		int dy = Float.floatToIntBits(direction.y);
		b[16] = (byte) (dy >> 24);
		b[17] = (byte) (dy >> 16);
		b[18] = (byte) (dy >> 8);
		b[19] = (byte) (dy);

		return b;
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
