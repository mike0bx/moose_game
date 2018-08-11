package actors;

import game.Stage;

public class Car extends Actor {

    public Car(Stage stage) {
        super(stage);
        sprites = new String[]{"greenBox.png"};
        speed = 2;
        width = 64;
        height = 128;
    }
    
    public void update() {
        super.update();
        updateSpeed();
    }
	
    protected void updateSpeed() {
        posY += speed;
    }
}
