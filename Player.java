package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Player class to record players
 */
public class Player
{
	/**
	 * Name of player
	 */
	private SimpleStringProperty name;

	/**
	 * Max score of player
	 */
	private SimpleIntegerProperty score;

	/**
	 * Date the highScore was made
	 */
//	private SimpleStringProperty date;

	public Player (String name, int score)//, String date)
	{
		this.name = new SimpleStringProperty(name);
		this.score = new SimpleIntegerProperty(score);
//		this.date = new SimpleStringProperty(date);
	}
}
