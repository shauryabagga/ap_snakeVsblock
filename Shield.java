package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * The methods and fields definitions are same for all similar tokens and have not been explained again for brevity
 */
public class Shield extends PowerUp
{
	Random r = new Random();
	/**
	 * X coordinate
	 */
	int currX = r.nextInt(500);

	/**
	 * Y coordinate
	 */
	int currY = -325;

	/**
	 * Tells whether the powerup is activated
	 */
	static boolean isActivated;
	static final String ShieldURL = "https://bitbucket.org/karan2dec/ap-game-images/downloads/shield.png";
	static final String Shield_Image = "shield.png";
	Image shieldImage = new Image("file:" + Shield_Image, true);
	ImageView Shield = new ImageView(shieldImage);
	Group shield = new Group(Shield);

	Shield()
	{
		Shield.setFitHeight(50);
		Shield.setFitWidth(50);
	}

	private void makeInvincible()
	{

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

	/**
	 * Check for collision
	 * @return
	 */
	@Override
	public boolean checkCollision()
	{
		if (Controller.snake.x == currX + 20)
		{
			if (currY < 300)
			{
				return false;
			}
			int snakeCircle = (currY - 300) / (21);
			if (snakeCircle >= 5)
				return false;
			if (Controller.snake.snake.get(snakeCircle).getOpacity() != 0 && Shield.getOpacity() != 0)
			{
				onCollisionDo();
				return true;
			}
		}
		return false;
	}

	/**
	 * What to do when collision with snake occurs
	 */
	@Override
	public void onCollisionDo()
	{
		Shield.setOpacity(0);
		activate();
	}

	@Override
	public int[] randomCoord()
	{
		int x, y;
		x = r.nextInt(5);
		x = 40 + 120 * x;
		currX = x;
		y = -125;
		int[] arr = {x, y};
		return arr;
	}

	@Override
	public boolean validateCoords()
	{
		return false;
	}

	/**
	 * Spawn at random coords
	 * @param x
	 * @param y
	 */
	@Override
	public void spawn(int x, int y)
	{
		Shield.setVisible(true);
		Shield.setOpacity(1);
		shield.relocate(x, y);
	}
}
