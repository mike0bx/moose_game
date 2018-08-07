package actors;

import game.Stage;

public class Background extends Actor {

    public Background(Stage stage) {
        super(stage);
        sprites = new String[]{"background.png"};
        frame = 0;
        frameSpeed = 1;
        xSpeed = 0;
        ySpeed = 10;
        width = 1080;
        height = 3840;
        posX = 0;
        posY = -height / 2;
    }

    public void update() {
        super.update();
        updateSpeed();
    }

    private void updateSpeed() {

        if (posY + ySpeed > 0) {
            posY = -height / 2;
        } else {
            posY += ySpeed;
        }
    }
}
