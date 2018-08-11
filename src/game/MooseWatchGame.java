package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

import actors.*;


public class MooseWatchGame extends Stage implements KeyListener {

    private Player player;
    private Background background;
    private BufferStrategy strategy;	 //double buffering strategy
    private InputHandler keyPressedHandler;
    private InputHandler keyReleasedHandler;
    private Random random = new Random();

    public MooseWatchGame() {
        //init the UI
        
        
        setBounds(0,0,Stage.WIDTH,Stage.HEIGHT);
        setBackground(Color.BLACK);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(Stage.WIDTH,Stage.HEIGHT));
        panel.setLayout(null);

        panel.add(this);

        JFrame frame = new JFrame("Moose Watch");
        frame.add(panel);

        frame.setBounds(0,0,Stage.WIDTH,Stage.HEIGHT);
        frame.setResizable(false);
        frame.setVisible(true);

        //cleanup resources on exit
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                  ResourceLoader.getInstance().cleanup();
              System.exit(0);
            }
        });

        addKeyListener(this);

        //create a double buffer
        createBufferStrategy(2);
        strategy = getBufferStrategy();
        requestFocus();
        initWorld();
    }
    
    public void initWorld() {
        actors = new ArrayList<>();
        gameOver = false;
        gameWon = false;
        //add a player
        player = new Player(this);
        background = new Background(this);
        keyPressedHandler = new InputHandler(this, player);
        keyPressedHandler.action = InputHandler.Action.PRESS;
        keyReleasedHandler = new InputHandler(this, player);
        keyReleasedHandler.action = InputHandler.Action.RELEASE;
    }
    
    
    public void game() {

        long lastTime = System.nanoTime();
        double ns = 1000000000 / (double) DESIRED_FPS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(isVisible()){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){

                spawnObstacles();
                updateWorld();
                updates++;
                delta--;

                //System.out.printf("Score: %d\n", player.getScore());
            }
            paintWorld();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

		//loopSound("music.wav");		
//		usedTime= 0;
//		while(isVisible()) {
//			long startTime = System.currentTimeMillis();
			
			/*backgroundY--;
			if (backgroundY < 0)
				backgroundY = backgroundTile.getHeight();

			if (super.gameOver) { 
				paintGameOver();
				break;
			} 
			else if (super.gameWon) {
				paintGameWon();
				break;
			}*/
			
//			int random = (int)(Math.random() * 500);
//			if (random == 200 || random == 300 || random == 400) {
//				Actor moose = new Moose(this);
//				moose.setX((int) (Math.random() * WIDTH));
//				moose.setY(-128);
//				actors.add(moose);
//			}
			
			//updateWorld();
			//paintWorld();
			
//			usedTime = System.currentTimeMillis() - startTime;
			
			//calculate sleep time
//			if (usedTime == 0) usedTime = 1;
//			int timeDiff = (int) ((1000/usedTime) - DESIRED_FPS);
//			if (timeDiff > 0) {
//				try {
//					Thread.sleep(timeDiff/100);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}
	}

	private void spawnObstacles() {

        int randomInt = random.nextInt(300);

        // Spawn moose
        if (randomInt == 1 || randomInt == 100 || randomInt == 200) {
            Actor moose = new Moose(this);
            moose.setX((int) (Math.random() * WIDTH));
            moose.setY(-128);
            actors.add(moose);
        }

        // Spawn car
        if (randomInt == 149) {
            Actor car = new Car(this);
            car.setX(random.nextBoolean() ? (int) (WIDTH * 0.6) : (int) (WIDTH * 0.8));
            car.setY(-256);
            actors.add(car);
        }
    }
    
    public void updateWorld() {		
        player.update();
        background.update();

        // Check each actor for collision
        for (Actor actor : actors) {
            actor.update();

            // Collision with player
            if (actor.getBounds().intersects(player.getBounds())) {
                System.out.println(player.getScore());

                // Set to game over and display menu/psa

                // if play again, run this line
                initWorld();
            }
        }
    }
    
    public void paintWorld() {
			
        //get the graphics from the buffer
        Graphics g = strategy.getDrawGraphics();
        //init image to background
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        //load subimage from the background
        //g.drawImage( background,0,0,Stage.WIDTH,Stage.HEIGHT,0,backgroundY,Stage.WIDTH,backgroundY+Stage.HEIGHT,this);

        background.paint(g);

        //paint the actors
        for (Actor actor : actors) {
            actor.paint(g);
        }

        player.paint(g);
        //paintScore(g);		
        //paintFPS(g);
        //swap buffer
        strategy.show();		
    }
    
    public void keyPressed(KeyEvent e) {	
        keyPressedHandler.handleInput(e);
	}

	public void keyReleased(KeyEvent e) {
        keyReleasedHandler.handleInput(e);
	}

	public void keyTyped(KeyEvent e) {
	}

    public static void main(String[] args) {
        MooseWatchGame game = new MooseWatchGame();
        game.game();
    }
}
