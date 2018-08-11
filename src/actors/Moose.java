package actors;

import game.Stage;

import java.util.Random;

public class Moose extends Actor {

    private int walkSpeed;
    private Random random;

    public Moose(Stage stage) {
        super(stage);
        random = new Random();
        sprites = new String[]{"moose.png"};
        speed = 16;
        width = 64;
        height = 64;
        walkSpeed = random.nextBoolean() ? 2 : -2;
    }

    public void update() {
        super.update();
        updateSpeed();
    }

    private void updateSpeed() {
        posX += walkSpeed;
        posY += speed;
    }
}
