package actors;

import game.Stage;
import java.awt.event.KeyEvent;

public class Player extends Actor implements KeyboardControllable {

    private boolean up,down,left,right;
    private int turningSpeed;
    private int score;

    public Player(Stage stage) {
        super(stage);
        sprites = new String[]{"redBox.png"};
        turningSpeed = 10;
        speed = 11;
        width = 128;
        height = 256;
        posX = Stage.WIDTH/2 - width/2;
        posY = Stage.HEIGHT/2 + height/2;
    }
    
    public void update() {
        super.update();
        updateScore();
        updateSpeed();
    }

    public int getScore() {
        return score;
    }

    private void updateScore() {
        score += 1;
    }
	
    protected void updateSpeed() {

        int horizontalDirection = (right ? 1 : 0) - (left ? 1 : 0);
        int verticalDirection = (down ? 1 : 0) - (up ? 1 : 0);

        vx = horizontalDirection * turningSpeed;

        if (verticalDirection == 0) {
            vy = 4;
        } else {
            vy = verticalDirection * speed;
        }

        //don't allow scrolling off the edge of the screen		
        if (posX - width/2 > 0 && vx < 0)
                posX += vx;
        else if (posX + width  + (width/2) < Stage.WIDTH && vx > 0)
                posX += vx;
        
        if (posY - height/2 > 0 && vy < 0)
                posY += vy;
        else if (posY + height + (height/2) < Stage.HEIGHT && vy > 0)
                posY += vy;
    }
    
    public void triggerKeyRelease(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_DOWN:
                down = false;
                break;
        case KeyEvent.VK_UP:
                up = false;
                break;
        case KeyEvent.VK_LEFT:
                left = false;
                break;
        case KeyEvent.VK_RIGHT:
                right = false;
                break;
        }
    }

    public void triggerKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
                up = true;
                break;
        case KeyEvent.VK_LEFT:
                left = true;
                break;
        case KeyEvent.VK_RIGHT:
                right = true;
                break;
        case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }
}
