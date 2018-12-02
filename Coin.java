package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.Random;

/**
 * Simulates the coins (balls) that increase score
 */
public class Coin implements Collidable
{
	Random r = new Random();
	int currX = r.nextInt(500), currY = -375;
	static int flag = 0;
	private int value;
	Text Value = new Text();
	static final String CoinURL = "https://bitbucket.org/karan2dec/ap-game-images/downloads/coin.png";
	static final String Coin_Image = "coin.png";
	Image coinImage = new Image("file:" + Coin_Image, true);
	ImageView Coin = new ImageView(coinImage);
	Group coin = new Group(Coin, Value);

	public Coin()
	{
		Value.setX(22);
		Coin.setFitHeight(50);
		Coin.setFitWidth(50);
	}

	@Override
	public boolean checkCollision()
	{
		if (Magnet.isActivated && Coin.getOpacity() != 0 && currY >= 300)
		{
			Controller.specialFlag = true;
			Magnet.isActivated = false;
		}
		if (Controller.snake.x == currX + 30)
		{
			Controller.specialFlag = false;
			if (currY < 300)
				return false;

			int snakeCircle = (currY - 300) / 21;

			if (snakeCircle >= 5)
				return false;

			if (Controller.snake.snake.get(snakeCircle).getOpacity() != 0 && Coin.getOpacity() != 0)
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
		Snake.length += value;
//		Coin.setVisible(false);
//		Value.setVisible(false);
		Coin.setOpacity(0);
		Value.setOpacity(0);
//		System.out.println("Snake length is " + Snake.length);
		if (Snake.length >= 5)
		{
			for (int i = 0; i < 5; i++)
				Controller.snake.snake.get(i).setOpacity(1);
		}
		else
		{
			for (int i = 4; i >= 5 - Snake.length; i--)
				Controller.snake.snake.get(i).setOpacity(1);
		}
	}

	@Override
	public int[] randomCoord()
	{
		int x, y;
		x = r.nextInt(5);
		x = 30 + 120 * x;
		System.out.println("Coin x: " + x);
		currX = x;
		y = -125;
		int arr[] = {x, y};
		return arr;
	}

	@Override
	public boolean validateCoords()
	{
		return false;
	}

	public void spawn(int x, int y)
	{
		value = r.nextInt(10) + 2;
		Value.setText(String.valueOf(value));
		Coin.setOpacity(1);
		Value.setOpacity(1);
//		Coin.setVisible(true);
//		Value.setVisible(true);
		coin.relocate(x, y);
	}
}
