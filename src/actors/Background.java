package actors;

import game.Stage;

public class Background extends Actor {

    public Background(Stage stage) {
        super(stage);
        sprites = new String[]{"background.png"};
        speed = 16;
        width = 720;
        height = 2560;
        posX = 0;
        posY = -height / 2;
    }

    public void update() {
        super.update();
        updateSpeed();
    }

    private void updateSpeed() {

        if (posY + speed > 0) {
            posY = -height / 2;
        } else {
            posY += speed;
        }
    }
}
