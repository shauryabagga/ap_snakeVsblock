package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

/**
 * Simulates the blocks in the game
 */
class Block implements Obstacle
{
	//------------------
	// FINAL ATTRIBUTES
	//------------------
	transient static final int numBlocks = 5;
	transient static final int blockHeight = 120;
	transient static final int blockWidth = 120;
	transient static final int blockArcHeight = 20;
	transient static final int blockArcWidth = 20;
	transient static final int blocksXIncrement = 100;
	transient static final int spawnProbability = 7; // out of 10

	transient Rectangle blocks[] = new Rectangle[numBlocks];
	transient Text values[] = new Text[numBlocks];
	transient Group allBlocksAndValues = null;
	transient Random r = new Random();
	transient double currX, currY = -125.0;
	transient double temp = -1;
	transient static String ExlplosionImage = "https://vignette.wikia.nocookie.net/robloxpokemonbrickbronze/images/1/17/Explosion.gif/revision/latest?cb=20180130164931";
	transient static Image explosionImage = new Image(ExlplosionImage);
	transient static ImageView explode = new ImageView(explosionImage);
	transient static Group ex = new Group(explode);

	void initialiseBlock(int upperBound)
	{
		ex.setOpacity(0);
		allBlocksAndValues = new Group();
		int flag = 0;
		for (int i = 0; i < 5; ++i)
		{
			Text value = new Text();
			value.setY(-blockWidth / 2);
			value.setX(i * blockWidth + (7 * blockWidth) / 16);

			Rectangle temp = new Rectangle();
			temp.setHeight(blockHeight);
			temp.setWidth(blockWidth);
			temp.setX(i * blockWidth);
			temp.setY(-blockWidth);
			temp.setArcWidth(blockArcWidth);
			temp.setArcHeight(blockArcHeight);

			int determineBlockValue = r.nextInt(10);
			int determineVisibility = r.nextInt(100);
			int blockValue;
			if (i == 4 && flag == 0)
			{
				blockValue = r.nextInt(upperBound);
				if (blockValue == 0)
				{
					value.setOpacity(0);
					temp.setOpacity(0);
				}
				value.setText(String.valueOf(blockValue));
			}
			else
			{
				if (determineBlockValue <= 5)
				{
					flag = 1;
					blockValue = r.nextInt(upperBound);
					if (blockValue == 0)
					{
						value.setOpacity(0);
						temp.setOpacity(0);
					}
					value.setText(String.valueOf(blockValue));
				}
				else
				{
					blockValue = r.nextInt(5 * upperBound) + 2;
					if (blockValue < upperBound)
					{
						flag = 1;
					}
					value.setText(String.valueOf(blockValue));
				}
			}
			value.setFill(Color.WHITE);
			values[i] = (value);
			blocks[i] = (temp);

			if (determineVisibility <= 40)
			{
				blocks[i].setOpacity(0);
				values[i].setOpacity(0);
			}
		}

		blocks[0].setFill(Color.BLUE);
		blocks[1].setFill(Color.ORANGE);
		blocks[2].setFill(Color.GREY);
		blocks[3].setFill(Color.DARKRED);
		blocks[4].setFill(Color.CYAN);

		for (int i = 0; i < 5; i++)
			allBlocksAndValues.getChildren().add(blocks[i]);

		for (int i = 0; i < 5; i++)
			allBlocksAndValues.getChildren().add(values[i]);
	}

	@Override
	public void reduceLength()
	{

	}

	@Override
	public boolean checkCollision()
	{
		return false;
	}

	@Override
	public void onCollisionDo()
	{

	}

	@Override
	public int[] randomCoord()
	{
		return new int[0];
	}

	@Override
	public boolean validateCoords()
	{
		return false;
	}

	@Override
	public void spawn(int x, int y)
	{
		initialiseBlock(Snake.length);
//        System.out.println(Snake.length);
	}

	public void checkCollisionWithSnake()
	{
		if (Hammer.isActivated)
		{
			int blockValue;
			for (int i = 0; i < 5; i++)
			{
				blocks[i].setOpacity(0);
				values[i].setOpacity(0);
				blockValue = Integer.valueOf(values[i].getText());
				Controller.score += blockValue;
			}
			Hammer.isActivated = false;
			return;
		}

		int blockNumber = (int) (Controller.snake.x - 60) / (120);
		int blockValue = Integer.valueOf(values[blockNumber].getText());
		if (blocks[blockNumber].getOpacity() != 0)
		{
			if (currY < 300)
				return;
			if (blockValue == 0)
				return;
			int snakeCircle = (int) (currY - 300) / (21);
			if (snakeCircle >= 5)
				return;

			if (Controller.snake.snake.get(snakeCircle).getOpacity() != 0)
			{
				if (blockValue <= 5)
				{
					if (Snake.length > blockValue)
					{
						Snake.length -= blockValue;
						Controller.score += (blockValue-1);
						blockValue = 1;
						if (Snake.length <= 5)
						{
							for (int i = 0; i < 5 - Snake.length; i++)
							{
								Controller.snake.snake.get(i).setOpacity(0);
							}
						}
					}
					else
					{
						Snake.length = 0;
						for (int i = 0; i < 5; i++)
						{
							Controller.snake.snake.get(i).setOpacity(0);
						}
					}
				}
				System.out.println("Collision with Block: " + blockNumber);
				if (temp < 0)
					temp = Controller.factor;
				if (Snake.length <= 5)
				{
					Controller.factor = temp;
					if (!Shield.isActivated)
					{
						Controller.snake.snake.get(snakeCircle).setOpacity(0);
					}
				}
				else
				{
					Controller.factor = 0.1;
				}
			}
			blockValue--;
			Controller.score++;
			values[blockNumber].setText(String.valueOf(blockValue));
			if (!Shield.isActivated)
				Snake.length--;

			if (Snake.length <= 0)
			{
				System.out.println("You Lost");
			}
			if (blockValue == 0)
			{
				Controller.factor = temp;
				temp = -1;
				values[blockNumber].setFill(Color.MEDIUMPURPLE);
				blocks[blockNumber].setFill(Color.MEDIUMPURPLE);
				blocks[blockNumber].setOpacity(0);
				values[blockNumber].setOpacity(0);
				Shield.isActivated = false;
				ex.relocate(120 * blockNumber, currY);
				Controller.explosionX = 120 * blockNumber;
				ex.setOpacity(1);
				Controller.explosionStart = System.nanoTime();
				Controller.explosionStart /= 1e6;
				Controller.explosionEnd = Controller.explosionStart;
			}
		}
		else
		{
			Controller.factor = temp;
		}
	}
}