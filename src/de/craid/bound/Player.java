package de.craid.bound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	Vector2 velocity;
	Vector2 position;
	Vector2 direction;
	Texture texture;
	Sprite sprite;
	float scaler = 1f; //bestimmt die Skalierung der abgebildeten Textur
	int id = 0; //Player ID
	
	public Player(int id){
		texture = new Texture(Gdx.files.internal("img/001.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		TextureRegion region = new TextureRegion(texture);
		sprite = new Sprite(region);
		
		sprite.setSize(0.4f, 0.4f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		initialize(); // Spieler wird initialisiert
		
		direction = new Vector2();
		velocity = new Vector2();
		this.id = id;
		
	}
	public Player(){
		texture = new Texture(Gdx.files.internal("img/001.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		TextureRegion region = new TextureRegion(texture);
		sprite = new Sprite(region);
		
		sprite.setSize(0.4f, 0.4f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		initialize(); // Spieler wird initialisiert
		
		direction = new Vector2();
		velocity = new Vector2();
		this.id = 0;
	}
	
	public void initialize(){
		position = new Vector2(0,0);
	}
	
	public void render(SpriteBatch batch){
		sprite.setRotation(direction.angle());
		sprite.setPosition(position.x, position.y);
		sprite.draw(batch);
	}
	
	public void receive(byte[] ba){
		//Vector2 position;		8byte
		//Vector2 direction;    8byte
		//int id				4byte
		
	}
	
	public byte[] send(){
		byte[] b = new byte[20];
		
		//id
		b[0] = (byte)(id >>> 24); //bit-shift... 24 verschoben
		b[1] = (byte)(id >>> 16);
		b[2] = (byte)(id >>> 8);
		b[3] = (byte)(id);
		// Position x
		b[4] = (byte)(position.x >>> 24); //bit-shift... 24 verschoben
		b[5] = (byte)(id >>> 16);
		b[6] = (byte)(id >>> 8);
		b[7] = (byte)(id);
		// Position y
		b[8] = (byte)(id >>> 24); //bit-shift... 24 verschoben
		b[9] = (byte)(id >>> 16);
		b[10] = (byte)(id >>> 8);
		b[11] = (byte)(id);
		// Direction x
		b[12] = (byte)(id >>> 24); //bit-shift... 24 verschoben
		b[13] = (byte)(id >>> 16);
		b[14] = (byte)(id >>> 8);
		b[15] = (byte)(id); //
		// Direction y
		b[16] = (byte)(id >>> 24); //bit-shift... 24 verschoben
		b[17] = (byte)(id >>> 16);
		b[18] = (byte)(id >>> 8);
		b[19] = (byte)(id);
		return b;
	}
	
	public void update(float deltaTime){
		boolean up = Gdx.input.isKeyPressed(Input.Keys.UP);
		boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN);
	    boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
		if(up)
			velocity.add(0, 1);
		if(down)
			velocity.add(0, -1);
		if(left)
			velocity.add(-1, 0);
		if(right)
			velocity.add(1, 0);
		
		velocity.limit(1);
		direction.set(velocity);
		direction.rotate(90f);
		direction.nor();
		
		System.out.println(position.x);
		position.x += velocity.x*deltaTime;
		position.y += velocity.y*deltaTime;
		
	}
	
}
