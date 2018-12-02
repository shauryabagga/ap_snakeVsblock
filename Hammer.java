package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * Class to simulate the hammer power up
 */
public class Hammer extends PowerUp
{
	Random r = new Random();
	int currX = r.nextInt(500);
	int currY = -200;
	static boolean isActivated;
	static final String HammerURL = "https://bitbucket.org/karan2dec/ap-game-images/downloads/hammer.png";
	static final String Hammer_Image = "hammer.png";
	Image hammerImage = new Image("file:" + Hammer_Image, true);
	ImageView Hammer = new ImageView(hammerImage);
	Group hammer = new Group(Hammer);

	Hammer()
	{
		Hammer.setFitHeight(50);
		Hammer.setFitWidth(50);
	}


	private void increaseScore()
	{

	}

	private void destroAllBloacks()
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
			if (Controller.snake.snake.get(snakeCircle).getOpacity() != 0 && Hammer.getOpacity() != 0)
			{
				onCollisionDo();
				return true;
			}
		}
		return false;
	}

	@Override
	public void onCollisionDo()
	{
		Hammer.setOpacity(0);
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
//        hammer.relocate(x,y);
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
		Hammer.setVisible(true);
		Hammer.setOpacity(1);
		hammer.relocate(x, y);
	}
}
