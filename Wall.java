package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * Class for implementing the wall obstacle
 */
public class Wall implements Obstacle
{
	Random r = new Random();
	//    private boolean flag = true;
	int currX = r.nextInt(600);
	int currY = -350;
	int movementToward;

	/**
	 * Height of wall
	 */
	static final int wallHeight = 140;
	Group wall = new Group();
	Rectangle[] walls = new Rectangle[6];

	Wall()
	{
		initialise();
//        flag = false;
	}

	private void restrictMovement()
	{

	}

	@Override
	public void reduceLength()
	{

	}

	@Override
	public boolean checkCollision()
	{
		if (currY >= 300)
		{
			int snakeCircle = (currY - 300) / (21);

			if (snakeCircle <= 4 && Controller.snake.snake.get(snakeCircle).getOpacity() != 0)
			{
				if (movementToward == 1)
				{
					if (((int) Controller.snake.x + 60) / 120 <= 4 && walls[((int) Controller.snake.x + 60) / 120].getOpacity() != 0)
					{
						System.out.println("collision with wall : " + ((int) Controller.snake.x + 60) / 120 );
						onCollisionDo();
						return true;
					}
					return false;
				}
				else if (movementToward == 0)
				{
					if (((int) Controller.snake.x - 60) / 120 >= 1 && walls[((int) Controller.snake.x - 60) / 120].getOpacity() != 0)
					{
						System.out.println("collision with wall : " + ((int) Controller.snake.x - 60) / 120);
						onCollisionDo();
						return true;
					}
					return false;
				}
			}
			if (snakeCircle > 4)
			{
				if (currY - wallHeight <= 410)
				{
					if (movementToward == 1)
					{
						if (((int) Controller.snake.x + 60) / 120 <= 4 && walls[((int) Controller.snake.x + 60) / 120].getOpacity() != 0)
						{
							onCollisionDo();
							return true;
						}
						return false;
					}
					else if (movementToward == 0)
					{
						if (((int) Controller.snake.x - 60) / 120 >= 1 && walls[((int) Controller.snake.x - 60) / 120].getOpacity() != 0)
						{
							onCollisionDo();
							return true;
						}
						return false;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void onCollisionDo()
	{
		System.out.println("Collided with wall HAHA");
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

	private void initialise()
	{
		wall = new Group();
		for (int i = 0; i < 6; i++)
		{
			Rectangle temp = new Rectangle();
			temp.setFill(Color.BROWN);
			temp.setX(120 * i);
			temp.setY(-175);
			temp.setHeight(wallHeight);
			temp.setWidth(3);
			int visibilty = r.nextInt(1000);
			if (visibilty >= 400)
				temp.setOpacity(0);
			if (i == 0 || i == 5)
				temp.setOpacity(0);
			walls[i] = temp;
			wall.getChildren().add(temp);
		}
	}

	@Override
	public void spawn(int x, int y)
	{
		initialise();
	}
}
