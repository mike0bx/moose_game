package actors;

import game.ResourceLoader;
import game.Stage;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Actor {

	protected int posX;
	protected int posY;
	protected int height;
	protected int width;
	protected int frame;
	protected int frameSpeed;
	protected int speed;
	protected int time;
	protected String[] sprites = null; 
	protected Stage stage = null;

	public Actor(Stage stage) {
		this.stage = stage;
		frame = 0;
		frameSpeed = 1;
		speed = 10;
		time = 0;
	}
	
	public void update() {
		updateSpriteAnimation();
	}
	
	private void updateSpriteAnimation() {
		time++;
		if (time % frameSpeed == 0) {
			time = 0;
			frame = (frame + 1) % sprites.length;
		}
	}
	
//	public void playSound(final String name) {
//		new Thread(new Runnable() {
//			public void run() {
//				ResourceLoader.getInstance().getSound(name).play();
//			}
//		}).start();
//	}

			
	public void paint(Graphics g) {		
		g.drawImage(ResourceLoader.getInstance().getSprite(sprites[frame]), posX, posY, stage);
	}
	
	public void setX(int posX) {
		this.posX = posX;
	}
	
	public void setY(int posY) {
		this.posY = posY;
	}

	public Rectangle getBounds() {
		return new Rectangle(posX,posY,width, height);
	}
}
