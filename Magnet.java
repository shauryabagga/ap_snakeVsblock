package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * Magnet powerup, attracts coins
 */
public class Magnet extends PowerUp
{
	Random r = new Random();
	/**
	 * X coordinate
	 */
	int currX = r.nextInt(500);

	/**
	 * Y coordinate
	 */
	int currY = -275;

	/**
	 * Check activation
	 */
	static boolean isActivated;
	static final String MagnetURL = "https://bitbucket.org/karan2dec/ap-game-images/downloads/magnet.png";
	static final String Magnet_Image = "magnet.png";
	Image magnetImage = new Image("file:" + Magnet_Image, true);
	ImageView Magnet = new ImageView(magnetImage);
	Group magnet = new Group(Magnet);

	public Magnet()
	{
		Magnet.setFitHeight(50);
		Magnet.setFitWidth(50);
	}

	@Override
	protected void activate()
	{
		isActivated = true;
	}

	@Override
	protected void deactivate()
	{
		isActivated = false;
	}

	@Override
	public boolean checkCollision()
	{
		if (Controller.snake.x == currX + 20)
		{
			if (currY < 300)
				return false;

			int snakeCircle = (currY - 300) / (21);

			if (snakeCircle >= 5)
				return false;

			if (Controller.snake.snake.get(snakeCircle).getOpacity() != 0 && Magnet.getOpacity() != 0)
				onCollisionDo();
		}
		return false;
	}

	@Override
	public void onCollisionDo()
	{
		Magnet.setOpacity(0);
		activate();
	}

	@Override
	public int[] randomCoord()
	{
		int x, y;
		x = r.nextInt(5);
		x = 40 + 120 * x;
		currX = x;
		y = -150;
		int[] arr = {x, y};
		return arr;
	}

	@Override
	public boolean validateCoords()
	{
		return false;
	}

	@Override
	public void spawn(int x, int y)
	{
		Magnet.setOpacity(1);
		Magnet.setVisible(true);
		magnet.relocate(x, y);
	}
}
