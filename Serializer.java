package sample;

import java.io.*;
import java.util.Scanner;

/**
 * Class to serialize all fields of screen elements
 */
public class Serializer implements Serializable
{
	transient static PrintWriter writer;
	transient static Scanner scanner;

	// Snake
	double snakeX;
	int snakeLength;
	int[] snakeRGB = new int[3];

	// Block
	double blockY;
	boolean[] blockWasSpawned = new boolean[5];
	String[] blockTextValues = new String[5];
	
	// Coin
	int coinX;
	int coinY;
	String coinValue;
	boolean coinWasSpawned;
	
	// Magnet
	int magnetX;
	int magnetY;
	boolean magnetIsActivated;

	// Hammer
	int hammerX;
	int hammerY;
	boolean hammerIsActivated;

	// Shield
	int shieldX;
	int shieldY;
	boolean shieldIsActivated;

	// Wall
//	int wallX;
	int wallY;
//	int wallLength;
	boolean[] wallWasSpawned = new boolean[6];

	// Score
	long gameScore;
	
	// Weed

	/**
	 * Read all values of the given fields
	 * @return
	 * @throws Exception
	 */
	static Serializer readAllValues() throws Exception
	{
		ObjectInputStream in = null;
		Serializer temp = null;

		try
		{
			in = new ObjectInputStream(new FileInputStream("game.txt"));
			scanner = new Scanner("game.txt");

			if (scanner.next().equals(""))
				return null;

			temp = (Serializer) in.readObject();
		}
		catch (Exception ex) {
			System.out.println("LOL");
		}
		finally
		{
			if ( in != null )
				in.close();
		}

		return temp;
	}

	/**
	 * Serialize all values of the fields
	 */
	void writeAllValues()
	{
		try
		{
			writer = new PrintWriter("game.txt");
			writer.write("");
			writer.flush();
			writer.close();
		}
		catch (Exception e)
		{
			System.out.println("inside printWriter");
		}

		ObjectOutputStream out = null;

		try
		{
			out = new ObjectOutputStream(new FileOutputStream("game.txt"));
			out.writeObject(this);
		}
		catch (Exception e)
		{
			System.out.println("in writeAllValues");
		}
		finally
		{
			try
			{
				out.close();
			}
			catch (Exception e)
			{

			}
		}
	}


	/**
	 * Deserialize snake (similar for every other alike tokens)
	 * @param snake
	 */
	void deserializeSnake(Snake snake)
	{
		snake.x = snakeX;
		Snake.length = snakeLength;
		snake.R = snakeRGB[0];
		snake.G = snakeRGB[1];
		snake.B = snakeRGB[2];
	}

	void deserializeBlock(Block block)
	{
		block.currY = blockY;

		for (int i = 0; i < 5; ++i)
		{
			block.blocks[i].setOpacity(0);
			block.values[i].setOpacity(0);

			if (blockWasSpawned[i])
			{
				block.blocks[i].setOpacity(1);
				block.values[i].setOpacity(1);
			}
		}

		for (int i = 0; i < 5; ++i)
			block.values[i].setText(blockTextValues[i]);
	}
	
	void deserializeCoin(Coin coin)
	{
		coin.currX = coinX;
		coin.currY = coinY;
		coin.Value.setText(coinValue);
		coin.coin.setOpacity(0);
		coin.Value.setOpacity(0);
		
		if (coinWasSpawned)
		{
			coin.coin.setOpacity(1);
			coin.Value.setOpacity(1);
		}
	}

	void deserializeMagnet(Magnet magnet)
	{
		magnet.currX = magnetX;
		magnet.currY = magnetY;
		magnet.magnet.setOpacity(0);
	
		if (magnetIsActivated)
			magnet.magnet.setOpacity(1);
	}

	void deserializeHammer(Hammer hammer)
	{
		hammer.currX = hammerX;
		hammer.currY = hammerY;
		hammer.hammer.setOpacity(0);

		if (hammerIsActivated)
			hammer.hammer.setOpacity(1);
	}

	void deserializeShield(Shield shield)
	{
		shield.currX = shieldX;
		shield.currY = shieldY;
		shield.shield.setOpacity(0);

		if (shieldIsActivated)
			shield.shield.setOpacity(1);
	}

	void deserializeWall(Wall wall)
	{
//		wall.currX = wallX;
		wall.currY = wallY;
		for (int i = 0; i < 6; i++ )
		{
			wall.walls[i].setOpacity(0);

			if (wallWasSpawned[i])
				wall.walls[i].setOpacity(1);
		}
	}

	long deserializeScore()
	{
		return gameScore;
	}

	/**
	 * Serialize back the values of snake (similar for every other alike token)
 	 * @param snake
	 */
	void serializeSnake(Snake snake)
	{
		snakeX = snake.x;
		snakeLength = Snake.length;
		snakeRGB[0] = snake.R;
		snakeRGB[1] = snake.G;
		snakeRGB[2] = snake.B;

		if ( Snake.length <= 5 )
		{
			for ( int i = 0; i < Snake.length; i++ )
				snake.snake.get(4-i).setOpacity(1);
			if ( Snake.length == 0 )
			{
				for ( int i = 0; i < 5; i++ )
					snake.snake.get(4-i).setOpacity(0);
			}
		}
	}

	void serializeBlock(Block block)
	{
		blockY = block.currY;

		for (int i = 0; i < 5; ++i)
			blockWasSpawned[i] = !(block.blocks[i].getOpacity() == 0);

		for (int i = 0; i < 5; ++i)
			blockTextValues[i] = block.values[i].getText();
	}

	void serializeCoin(Coin coin)
	{
		coinX = coin.currX + 5;
		coinY = coin.currY;

		coinWasSpawned = !(coin.Coin.getOpacity() == 0);
		coinValue = coin.Value.getText();
	}

	void serializeMagnet(Magnet magnet)
	{
		magnetX = magnet.currX + 5;
		magnetY = magnet.currY;

		magnetIsActivated = !(magnet.Magnet.getOpacity() == 0);
	}

	void serializeHammer(Hammer hammer)
	{
		hammerX = hammer.currX + 5;
		hammerY = hammer.currY;

		hammerIsActivated = !(hammer.Hammer.getOpacity() == 0);
	}

	void serializeShield(Shield shield)
	{
		shieldX = shield.currX + 5;
		shieldY = shield.currY;

		shieldIsActivated = !(shield.Shield.getOpacity() == 0);
	}

	void serializeWall(Wall wall)
	{
//		wallX = wall.currX;
		wallY = wall.currY;
//		wallLength = wall.walls.length;

		for (int i = 0; i < 6; ++i)
			wallWasSpawned[i] = !(wall.walls[i].getOpacity() == 0);
	}

	void serializeScore(long score)
	{
		gameScore = score;
	}
}
