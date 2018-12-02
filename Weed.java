package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * Used for weed mode
 */
public class Weed implements Collidable
{
	/**
	 * X coordinate
	 */
    int currX;

	/**
	 * Y coordinate
	 */
	int currY;

	/**
	 * Check activation
	 */
    static boolean isActivated;

	/**
	 * Start time of weed mode
	 */
	static long activationStarts;
    static final String WeedURL = "https://bitbucket.org/karan2dec/ap-game-images/downloads/Weed.png";
    static final String Weed_Image = "Weed.png";
    private Image weedImage = new Image("file:" + Weed_Image, true);
    private ImageView Weed = new ImageView(weedImage);
    Group weed = new Group(Weed);
    Random r = new Random();

    Weed()
    {
        Weed.setFitWidth(50);
        Weed.setFitHeight(50);
    }
    @Override
    public boolean checkCollision() {
        if (Controller.snake.x == currX + 20 && !(isActivated) )
        {
            if ( currY < 300 )
                return false;
            int snakeCircle =  (currY - 300) / (21);
            if ( snakeCircle >= 5 )
                return false;
            if ( Controller.snake.snake.get(snakeCircle).getOpacity() != 0 && Weed.getOpacity() != 0 )
            {
                System.out.println("Valid collision with weed");
                onCollisionDo();
            }
        }
        return false;
    }

    @Override
    public void onCollisionDo() {
    	weed.setOpacity(0);
        activationStarts = System.nanoTime();
        activationStarts /= 1e9;
        isActivated = true;
        Controller.normFactor = Controller.factor;
        Controller.factor += 2;
        Controller.weedFactor = Controller.factor;
    }

    @Override
    public int[] randomCoord() {
        int x,y;
        x = r.nextInt(5);
        x = 40 + 120*x;
        System.out.println("Weed x: " + x);
        currX = x;
        y = -150;
        int[] arr = {x,y};
        return arr;
    }

    @Override
    public boolean validateCoords() {
        return false;
    }

    @Override
    public void spawn(int x, int y) {
        weed.setOpacity(1);
        weed.relocate(x, y);
    }
}
