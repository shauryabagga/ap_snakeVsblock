package sample;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static sample.Block.blockWidth;
import static sample.Block.ex;

/**
 * Class to integrate each and every component along with FXMLs
 */
public class Controller
{
	/**
	 * Offsets for x
	 */
	private double xOffset = 0;

	/**
	 * Offset for y
	 */
	private double yOffset = 0;

	/**
	 * Factor: Falling rate
	 */
	static double factor = 1;

	/**
	 * Switch between pause / play
	 */
	private static int count = 0;

	/**
	 * Used to start the animation
	 */
	private AnimationTimer timer;

	@FXML
	private AnchorPane splashScreen;

	/**
	 * Handles the event when we click on the splash screen
	 * @param mouseEvent
	 * @throws IOException
	 */
	public void splashScreenTouch(MouseEvent mouseEvent) throws IOException
	{
		Stage stage = (Stage) splashScreen.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("loginRegisterScreen.fxml"));

		root.setOnMousePressed(event ->	{
			xOffset = event.getSceneX(); yOffset = event.getSceneY();
		});

		root.setOnMouseDragged(event ->	{
			stage.setX(event.getScreenX() - xOffset); stage.setY(event.getScreenY() - yOffset);
		});

		stage.setScene(new Scene(root, 600, 400));
	}

	@FXML
	private Button showLogin;

	/**
	 * Renders the Login Screen
	 *
	 * @param mouseEvent
	 * @throws IOException
	 */
	public void showLoginScreen(MouseEvent mouseEvent) throws IOException
	{
		Stage stage = (Stage) showLogin.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));

		root.setOnMousePressed(event ->	{
			xOffset = event.getSceneX(); yOffset = event.getSceneY();
		});

		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - xOffset); stage.setY(event.getScreenY() - yOffset);
		});

		stage.setScene(new Scene(root, 600, 400));
	}

	@FXML
	private Button showRegister;

	/**
	 * Render the Registration Screen
	 *
	 * @param mouseEvent
	 * @throws IOException
	 */
	public void showRegisterScreen(MouseEvent mouseEvent) throws IOException
	{
		Stage stage = (Stage) showRegister.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("registerScreen.fxml"));

		root.setOnMousePressed(event ->	{
			xOffset = event.getSceneX(); yOffset = event.getSceneY();
		});

		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - xOffset); stage.setY(event.getScreenY() - yOffset);
		});

		stage.setScene(new Scene(root, 600, 400));
	}

	@FXML
	private Button backToLoginRegister;

	/**
	 * Method to go back to registration screen
	 * @param mouseEvent
	 * @throws IOException
	 */
	public void showLoginRegisterScreen(MouseEvent mouseEvent) throws IOException
	{
		Stage stage = (Stage) backToLoginRegister.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("loginRegisterScreen.fxml"));

		root.setOnMousePressed(event ->	{
			xOffset = event.getSceneX(); yOffset = event.getSceneY();
		});

		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - xOffset); stage.setY(event.getScreenY() - yOffset);
		});

		stage.setScene(new Scene(root, 600, 400));
	}

	@FXML
	private Button registerButton;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField confirmPassword;

	/**
	 * Checks whether registration is correct or not
	 * @param mouseEvent
	 * @throws IOException
	 */
	public void checkRegistration(MouseEvent mouseEvent) throws IOException
	{
		Stage stage = (Stage) registerButton.getScene().getWindow();
		Parent root = null;

		if (username.getText().length() >= 4 && password.getText().equals(confirmPassword.getText()) && password.getText().length() >= 4)
		{
			System.out.println("Passwords match");
			root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
		}
		else
		{
			System.out.println("Enter password again!");
			root = FXMLLoader.load(getClass().getResource("registerScreen.fxml"));
		}

		root.setOnMousePressed(event ->	{
			xOffset = event.getSceneX(); yOffset = event.getSceneY();
		});

		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - xOffset); stage.setY(event.getScreenY() - yOffset);
		});

		stage.setScene(new Scene(root, 600, 400));
	}

	@FXML
	private Button loginButton;

	/**
	 * Checks whether login credentials are correct
	 * @param mouseEvent
	 * @throws IOException
	 */
	public void checkLogin(MouseEvent mouseEvent) throws IOException
	{
		Stage stage = (Stage) loginButton.getScene().getWindow();
		Parent root = null;
		root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));

		root.setOnMousePressed(event ->	{
			xOffset = event.getSceneX(); yOffset = event.getSceneY();
		});

		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - xOffset); stage.setY(event.getScreenY() - yOffset);
		});

		stage.setScene(new Scene(root, 600, 400));
	}

	@FXML
	private Button settingsButton;

	/**
	 * Shows the settings menu
	 * @param mouseEvent
	 * @throws IOException
	 */
	public void showSettings(MouseEvent mouseEvent) throws IOException
	{
		Stage stage = (Stage) settingsButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("settingsScreen.fxml"));

		root.setOnMousePressed(event ->	{
			xOffset = event.getSceneX(); yOffset = event.getSceneY();
		});

		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - xOffset); stage.setY(event.getScreenY() - yOffset);
		});

		stage.setScene(new Scene(root, 600, 400));
	}

	@FXML
	private Button backToMainMenu;

	/**
	 * Shows the main menu
	 * @param mouseEvent
	 * @throws IOException
	 */
	public void showMainMenu(MouseEvent mouseEvent) throws IOException
	{
		Stage stage = (Stage) backToMainMenu.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));

		root.setOnMousePressed(event ->	{
			xOffset = event.getSceneX(); yOffset = event.getSceneY();
		});

		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - xOffset); stage.setY(event.getScreenY() - yOffset);
		});

		stage.setScene(new Scene(root));
	}

	@FXML
	private Button leaderboardButton;
	@FXML
	private TableView leaderboardTable;
	@FXML
	private TableColumn name;
	@FXML
	private TableColumn maxscore;

	static ObservableList<Player> playerData = FXCollections.observableArrayList(
			new Player("tourist", 3739),
			new Player("Radewoosh", 3266),
			new Player("Um_nik", 3370),
			new Player("Petr", 3597)
	);

	/**
	 * Shows the leaderboard screen
	 * @param mouseEvent
	 * @throws IOException
	 */
	public void showLeaderboard(MouseEvent mouseEvent) throws IOException
	{
		Stage stage = (Stage) leaderboardButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("leaderboardScreen.fxml"));

		root.setOnMousePressed(event ->	{
			xOffset = event.getSceneX(); yOffset = event.getSceneY();
		});

		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - xOffset); stage.setY(event.getScreenY() - yOffset);
		});

		TableColumn<Player, String> name;
		TableColumn<Player, Integer> score;

		name = new TableColumn("name");
		name.setMinWidth(100);
		name.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));

		score = new TableColumn("maxScore");
		score.setMinWidth(100);
		score.setCellValueFactory(new PropertyValueFactory<Player, Integer>("score"));

		leaderboardTable.setItems(playerData);
		leaderboardTable.getColumns().addAll(name, score);

//		final VBox vbox = new VBox();
//		vbox.setSpacing(5);
//		vbox.setPadding(new Insets(10, 0, 0, 10));
//		vbox.getChildren().addAll(leaderboardTable);
//		vbox.setLayoutX(90);

		stage.setScene(new Scene(root, 600, 400));
	}

	@FXML
	private Button resumeGameButton;

	/**
	 * Resumes game from last checkpoint
	 * @param mouseEvent
	 * @throws IOException
	 */
	public void showResumeGame(MouseEvent mouseEvent) throws IOException
	{
		Stage stage = (Stage) leaderboardButton.getScene().getWindow();
		Block block = new Block();
		Weed weed = new Weed();
		Coin coin = new Coin();
		Magnet magnet = new Magnet();
		Shield shield = new Shield();
		Hammer hammer = new Hammer();
		Wall wall = new Wall();
		scoreLabelText = new Text("Score: ");
		scoreLabelText.setX(280);
		scoreLabelText.setY(15);
		scoreText = new Text("0");
		scoreText.setX(340);
		scoreText.setY(15);
		block.spawn(0, 0);
		screenElements = new Group();
		screenElements.getChildren().add(snake.Snake);
		screenElements.getChildren().add(coin.coin);
		screenElements.getChildren().add(magnet.magnet);
		screenElements.getChildren().add(shield.shield);
		screenElements.getChildren().add(hammer.hammer);
		screenElements.getChildren().add(block.allBlocksAndValues);
		screenElements.getChildren().add(weed.weed);
		screenElements.getChildren().add(ex);
		screenElements.getChildren().add(wall.wall);
		screenElements.getChildren().add(scoreLabelText);
		screenElements.getChildren().add(scoreText);
		Scene scene = new Scene(screenElements, 600, 400, Color.MEDIUMPURPLE);

		scene.setOnKeyPressed(event ->
		{
			if (!snakeCanMove)
				return;

			if (event.getCode() == LEFT)
			{
				if (movement.isEmpty())
					movement.add(-1);
			}
			else if (event.getCode() == RIGHT)
			{
				if (movement.isEmpty())
					movement.add(1);
			}
		});

		buttonsGroup = new Group();
		pauseButton = new Button("||");
		pauseButton.setLayoutX(600 - 30);
		pauseButton.setLayoutY(15);
		buttonsGroup.getChildren().add(pauseButton);

		screenElements.getChildren().add(buttonsGroup);

		pauseButton.setOnKeyPressed(event ->
		{
			if (!snakeCanMove)
				return;

			if (event.getCode() == LEFT)
			{
				if (movement.isEmpty())
					movement.add(-1);

			}
			else if (event.getCode() == RIGHT)
			{
				if (movement.isEmpty())
					movement.add(1);
			}
		});

		pauseButton.setOnMouseClicked(e ->
		{
			if (isPause)
			{
				pauseButton.setText("|>");
				timer.stop();
				snakeCanMove = false;
			}
			else
			{
				pauseButton.setText("||");
				timer.start();
				snakeCanMove = true;
			}
			isPause = !isPause;
			serializer.writeAllValues();
			serializer.serializeSnake(snake);
			serializer.serializeBlock(block);
			serializer.serializeCoin(coin);
			serializer.serializeMagnet(magnet);
			serializer.serializeShield(shield);
			serializer.serializeHammer(hammer);
			serializer.serializeWall(wall);
		});

		backButton = new Button("<");
		backButton.setLayoutX(25);
		backButton.setLayoutY(25);
		backButton.setOnMouseClicked(event ->
		{
			try
			{
				timer.stop();
				Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
				stage.setScene(new Scene(root));
			}
			catch (IOException e) { }
		});

		backButton.setOnKeyPressed(event ->
		{
			if (!snakeCanMove)
				return;

			if (event.getCode() == LEFT)
			{
				if (movement.isEmpty())
					movement.add(-1);

			}
			else if (event.getCode() == RIGHT)
			{
				if (movement.isEmpty())
					movement.add(1);
			}
		});

		screenElements.getChildren().add(backButton);

		//
		// DESERIALIZATION
		//
		try
		{
			serializer = Serializer.readAllValues();
		}
		catch (Exception ex) { }

		if (serializer != null)
		{
			serializer.deserializeSnake(snake);
			serializer.deserializeBlock(block);
			serializer.deserializeCoin(coin);
			serializer.deserializeMagnet(magnet);
			serializer.deserializeShield(shield);
			serializer.deserializeHammer(hammer);
			score = serializer.deserializeScore();
//			serializer.deserializeWall(wall);
			coin.coin.relocate(coin.currX, coin.currY);
			magnet.magnet.relocate(magnet.currX, magnet.currY);
			hammer.hammer.relocate(hammer.currX, hammer.currY);
			shield.shield.relocate(shield.currX, shield.currY);
			snake.Snake.relocate(snake.x, 295.0);
			wall.wall = new Group();
			for ( int i = 0; i < 6; i++ )
			{
				Rectangle temp = new Rectangle();
				temp.setFill(Color.BROWN);
				temp.setX(120 * i);
				temp.setY(serializer.wallY);
				temp.setHeight(Wall.wallHeight);
				temp.setWidth(3);
				wall.walls[i].setOpacity(0);
				if (serializer.wallWasSpawned[i])
					wall.walls[i].setOpacity(1);
				wall.walls[i] = temp;
				wall.wall.getChildren().add(temp);
			}
			screenElements.getChildren().add(wall.wall);
			System.out.println(Snake.length);
		}
		else
			serializer = new Serializer();

		AudioClip song = new AudioClip(Paths.get("song.mp3").toUri().toString());
		song.play();

		timer = new AnimationTimer()
		{
			double x = 0.0, y = block.currY;
			double coinX = coin.currX;
			double coinY = coin.currY;
			double hammerX = hammer.currX;
			double hammerY = hammer.currY;
			double magnetX = magnet.currX;
			double magnetY = magnet.currY;
			double shieldX = shield.currX;
			double shieldY = shield.currY;
			double wallX = 0.0;
			double wallY = wall.currY;
			double weedX = 120;
			double weedY = -350;

			@Override
			public void handle(long now)
			{
				adjustFactor();
				if (Snake.length == 0)
				{
					System.out.println("you lost men");
					serializer.writeAllValues();
					serializer.serializeSnake(snake);
					serializer.serializeBlock(block);
					serializer.serializeCoin(coin);
					serializer.serializeMagnet(magnet);
					serializer.serializeShield(shield);
					serializer.serializeHammer(hammer);
					serializer.serializeWall(wall);
					serializer.serializeScore(score);
					Text t = new Text("Game over! Press back key!");
					t.setX(300);
					t.setY(200);
					screenElements.getChildren().add(t);
//						System.out.println(score);
					try
					{
						showMainMenu(mouseEvent);
					}
					catch (Exception e) { }
					timer.stop();
					return;
				}

				scoreText.setText("" + score);

				if (!(movement.isEmpty()))
				{
					endTime = System.nanoTime();
					endTime /= 1e6;
					boolean checkWallCollision;
					if (endTime - startTime >= 2 * delay + 50)
					{
						if (movement.get(0) == -1)
						{
							wall.movementToward = 0;
							checkWallCollision = wall.checkCollision();
							if (!checkWallCollision)
								snake.move(-1);
						}
						else
						{
							wall.movementToward = 1;
							checkWallCollision = wall.checkCollision();
							if (!checkWallCollision)
								snake.move(1);
						}
						for (int i = 0, size = movement.size(); i < size; i++)
							movement.remove(i);
					}
				}
				explosionEnd = System.nanoTime();
				explosionEnd /= 1e6;
				if (explosionEnd - explosionStart >= 400)
				{
					ex.setOpacity(0);
				}
				y += factor;
				block.currY = y + blockWidth;
				coinY += factor;
				coin.currY = (int) coinY + 50;
				hammerY += factor;
				hammer.currY = (int) hammerY + 50;
				shieldY += factor;
				shield.currY = (int) shieldY + 50;
				magnetY += factor;
				magnet.currY = (int) magnetY + 50;
				wallY += factor;
				wall.currY = (int) wallY + Wall.wallHeight;
				weedY += factor;
				weed.currY = (int) weedY + 50;

				block.checkCollisionWithSnake();
				coin.checkCollision();
				snake.adjustColor();
				hammer.checkCollision();
				shield.checkCollision();
				magnet.checkCollision();
				weed.checkCollision();
				long weedModeEnds = System.nanoTime();
				weedModeEnds /= 1e9;
				if (weedModeEnds - Weed.activationStarts > 6 && Weed.isActivated)
				{
					Weed.isActivated = false;
					factor = normFactor;
				}
				if (y > 501)
				{
					block.spawn(0, 0);
					y = -125;
					screenElements.getChildren().add(block.allBlocksAndValues);
				}
				else if (y < 501)
				{
					block.allBlocksAndValues.relocate(x, block.currY - blockWidth);
					ex.relocate(explosionX, y);
				}

				if (coinY > 501)
				{
					int[] getXY = coin.randomCoord();
					coin.spawn(getXY[0], getXY[1]);
					coinX = getXY[0];
					coinY = getXY[1];
				}
				else if (coinY < 501)
				{
					if (specialFlag)
					{
						if (snake.x - 30 > coin.currX)
						{
							coinX += 10;
							coin.currX += 10;
						}
						else if (snake.x - 30 < coin.currX)
						{
							coinX -= 10;
							coin.currX -= 10;
						}
						coinY -= 1;
						coin.currY -= 1;
					}
					coin.coin.relocate(coinX, coinY);
					coin.checkCollision();
				}
				if (magnetY > 501)
				{
					int[] getXY = magnet.randomCoord();
					magnet.spawn(getXY[0], getXY[1]);
					magnetY = getXY[1];
					magnetX = getXY[0];
				}
				else if (magnetY < 501)
				{
					magnet.magnet.relocate(magnetX, magnetY);
				}
				if (hammerY > 501)
				{
					int[] getXY = hammer.randomCoord();
					hammer.spawn(getXY[0], getXY[1]);
					hammerY = getXY[1];
					hammerX = getXY[0];
				}
				else if (hammerY < 501)
				{
					hammer.hammer.relocate(hammerX, hammerY);
				}
				if (shieldY > 501)
				{
					int[] getXY = shield.randomCoord();
					shield.spawn(getXY[0], getXY[1]);
					shieldY = getXY[1];
					shieldX = getXY[0];
				}
				else if (shieldY < 501)
				{
					shield.shield.relocate(shieldX, shieldY);
				}
				if (wallY > 501)
				{
					wall.spawn(0, 0);
//                    wallX = getXY[0];
					wallY = -175;
					screenElements.getChildren().add(wall.wall);
				}
				else if (wallY < 501)
				{
					wall.wall.relocate(wallX, wallY);
				}
				if (weedY > 501)
				{
					int[] getXY = weed.randomCoord();
					weed.spawn(getXY[0], getXY[1]);
					weedY = getXY[1];
					weedX = getXY[0];
				}
				else
					weed.weed.relocate(weedX, weedY);

				buttonsGroup.toFront();
				serializer.writeAllValues();
				serializer.serializeSnake(snake);
				serializer.serializeBlock(block);
				serializer.serializeCoin(coin);
				serializer.serializeMagnet(magnet);
				serializer.serializeShield(shield);
				serializer.serializeHammer(hammer);
				serializer.serializeScore(score);
				serializer.serializeWall(wall);
			}
		};

		stage.setTitle("Snake Vs Block");
		stage.setScene(scene);
		stage.show();
		timer.start();
	}

	@FXML
	private Slider difficultySlider;

	/**
	 * Sets the difficulty of the game
	 * @param event
	 * @throws IOException
	 */
	public void setDifficulty(MouseEvent event) throws IOException
	{
		factor = (int)difficultySlider.getValue();
		System.out.println("Factor: " + factor);
	}

	@FXML
	private Button newGameButton;

	/**
	 * Shows the new game menu
	 * @param mouseEvent
	 * @throws IOException
	 */
	static Serializer serializer = new Serializer();
	static Group screenElements = null;
	static Snake snake = new Snake(5);
	static double weedFactor;
	static boolean specialFlag;
	ArrayList<Integer> movement = new ArrayList<>(10);
	static long startTime;
	static long endTime;
	static long delay = 100;
	static double normFactor = 2;
	static int normMovTime = 100;
	static double explosionX;
	static long explosionStart;
	static long explosionEnd;
	Text scoreLabelText;
	Text scoreText;
	static long score;

	PrintWriter writer;
	File f;
	Scanner scanner;
	Button backButton;
	Button pauseButton;
	Group blocksGroup, buttonsGroup;
	private boolean isPause = true;
	private boolean snakeCanMove = true;

	public void showNewGame(MouseEvent mouseEvent) throws IOException
	{
		Stage stage = (Stage) leaderboardButton.getScene().getWindow();
			Block block = new Block();
			Weed weed = new Weed();
			Coin coin = new Coin();
			Magnet magnet = new Magnet();
			Shield shield = new Shield();
			Hammer hammer = new Hammer();
			Wall wall = new Wall();
			scoreLabelText = new Text("Score: ");
			scoreLabelText.setX(280);
			scoreLabelText.setY(15);
			scoreText = new Text("0");
			scoreText.setX(340);
			scoreText.setY(15);
			block.spawn(0, 0);
			screenElements = new Group();
			screenElements.getChildren().add(snake.Snake);
			screenElements.getChildren().add(coin.coin);
			screenElements.getChildren().add(magnet.magnet);
			screenElements.getChildren().add(shield.shield);
			screenElements.getChildren().add(hammer.hammer);
			screenElements.getChildren().add(block.allBlocksAndValues);
			screenElements.getChildren().add(weed.weed);
			screenElements.getChildren().add(ex);
			screenElements.getChildren().add(wall.wall);
			screenElements.getChildren().add(scoreLabelText);
			screenElements.getChildren().add(scoreText);
			Scene scene = new Scene(screenElements, 600, 400, Color.MEDIUMPURPLE);

			scene.setOnKeyPressed(event ->
			{
				if (!snakeCanMove)
					return;

				if (event.getCode() == LEFT)
				{
					if (movement.isEmpty())
						movement.add(-1);
				}
				else if (event.getCode() == RIGHT)
				{
					if (movement.isEmpty())
						movement.add(1);
				}
			});

			buttonsGroup = new Group();
			pauseButton = new Button("||");
			pauseButton.setLayoutX(600 - 30);
			pauseButton.setLayoutY(15);
			buttonsGroup.getChildren().add(pauseButton);

			screenElements.getChildren().add(buttonsGroup);


		AudioClip song = new AudioClip(Paths.get("song.mp3").toUri().toString());
		song.play();

			pauseButton.setOnKeyPressed(event ->
			{
				if (!snakeCanMove)
					return;

				if (event.getCode() == LEFT)
				{
					if (movement.isEmpty())
						movement.add(-1);

				}
				else if (event.getCode() == RIGHT)
				{
					if (movement.isEmpty())
						movement.add(1);
				}
			});

			pauseButton.setOnMouseClicked(e ->
			{
				if (isPause)
				{
					pauseButton.setText("|>");
					timer.stop();
					snakeCanMove = false;
		}
				else
				{
					pauseButton.setText("||");
					timer.start();
					snakeCanMove = true;
				}
				isPause = !isPause;
				serializer.writeAllValues();
				serializer.serializeSnake(snake);
				serializer.serializeBlock(block);
				serializer.serializeCoin(coin);
				serializer.serializeMagnet(magnet);
				serializer.serializeShield(shield);
				serializer.serializeHammer(hammer);
				serializer.serializeWall(wall);
			});

			backButton = new Button("<");
			backButton.setLayoutX(25);
			backButton.setLayoutY(25);
			backButton.setOnMouseClicked(event ->
			{
				try
				{
					timer.stop();
					Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
					stage.setScene(new Scene(root));
				}
				catch (IOException e) { }
			});

			backButton.setOnKeyPressed(event ->
			{
				if (!snakeCanMove)
					return;

				if (event.getCode() == LEFT)
				{
					if (movement.isEmpty())
						movement.add(-1);

				}
				else if (event.getCode() == RIGHT)
				{
					if (movement.isEmpty())
						movement.add(1);
				}
			});

			screenElements.getChildren().add(backButton);
			serializer = new Serializer();

			timer = new AnimationTimer()
			{
				double x = 0.0, y = block.currY;
				double coinX = coin.currX;
				double coinY = coin.currY;
				double hammerX = hammer.currX;
				double hammerY = hammer.currY;
				double magnetX = magnet.currX;
				double magnetY = magnet.currY;
				double shieldX = shield.currX;
				double shieldY = shield.currY;
				double wallX = 0.0;
				double wallY = wall.currY;
				double weedX = 120;
				double weedY = -350;

				@Override
				public void handle(long now)
				{
					adjustFactor();
					if (Snake.length == 0)
					{
						System.out.println("you lost men");
						serializer.writeAllValues();
						serializer.serializeSnake(snake);
						serializer.serializeBlock(block);
						serializer.serializeCoin(coin);
						serializer.serializeMagnet(magnet);
						serializer.serializeShield(shield);
						serializer.serializeHammer(hammer);
						serializer.serializeWall(wall);
						serializer.serializeScore(score);
						Text t = new Text("Game over! Press back key!");
						t.setX(300);
						t.setY(200);
						screenElements.getChildren().add(t);
//						System.out.println(score);

						try
						{
							showMainMenu(mouseEvent);
						}
						catch (Exception e) { }
						timer.stop();
						return;
					}

					scoreText.setText("" + score);

					if (!(movement.isEmpty()))
					{
						endTime = System.nanoTime();
						endTime /= 1e6;
						boolean checkWallCollision;
						if (endTime - startTime >= 2 * delay + 50)
						{
							if (movement.get(0) == -1)
							{
								wall.movementToward = 0;
								checkWallCollision = wall.checkCollision();
								if (!checkWallCollision)
									snake.move(-1);
							}
							else
							{
								wall.movementToward = 1;
								checkWallCollision = wall.checkCollision();
								if (!checkWallCollision)
									snake.move(1);
							}
							for (int i = 0, size = movement.size(); i < size; i++)
								movement.remove(i);
						}
					}
					explosionEnd = System.nanoTime();
					explosionEnd /= 1e6;
					if (explosionEnd - explosionStart >= 400)
					{
						ex.setOpacity(0);
					}
					y += factor;
					block.currY = y + blockWidth;
					coinY += factor;
					coin.currY = (int) coinY + 50;
					hammerY += factor;
					hammer.currY = (int) hammerY + 50;
					shieldY += factor;
					shield.currY = (int) shieldY + 50;
					magnetY += factor;
					magnet.currY = (int) magnetY + 50;
					wallY += factor;
					wall.currY = (int) wallY + Wall.wallHeight;
					weedY += factor;
					weed.currY = (int) weedY + 50;

					block.checkCollisionWithSnake();
					coin.checkCollision();
					snake.adjustColor();
					hammer.checkCollision();
					shield.checkCollision();
					magnet.checkCollision();
					weed.checkCollision();
					long weedModeEnds = System.nanoTime();
					weedModeEnds /= 1e9;
					if (weedModeEnds - Weed.activationStarts > 6 && Weed.isActivated)
					{
						Weed.isActivated = false;
						factor = normFactor;
					}
					if (y > 501)
					{
						block.spawn(0, 0);
						y = -125;
						screenElements.getChildren().add(block.allBlocksAndValues);
					}
					else if (y < 501)
					{
						block.allBlocksAndValues.relocate(x, block.currY - blockWidth);
						ex.relocate(explosionX, y);
					}

					if (coinY > 501)
					{
						int[] getXY = coin.randomCoord();
						coin.spawn(getXY[0], getXY[1]);
						coinX = getXY[0];
						coinY = getXY[1];
					}
					else if (coinY < 501)
					{
						if (specialFlag)
						{
							if (snake.x - 30 > coin.currX)
							{
								coinX += 10;
								coin.currX += 10;
							}
							else if (snake.x - 30 < coin.currX)
							{
								coinX -= 10;
								coin.currX -= 10;
							}
							coinY -= 1;
							coin.currY -= 1;
						}
						coin.coin.relocate(coinX, coinY);
						coin.checkCollision();
					}
					if (magnetY > 501)
					{
						int[] getXY = magnet.randomCoord();
						magnet.spawn(getXY[0], getXY[1]);
						magnetY = getXY[1];
						magnetX = getXY[0];
					}
					else if (magnetY < 501)
					{
						magnet.magnet.relocate(magnetX, magnetY);
					}
					if (hammerY > 501)
					{
						int[] getXY = hammer.randomCoord();
						hammer.spawn(getXY[0], getXY[1]);
						hammerY = getXY[1];
						hammerX = getXY[0];
					}
					else if (hammerY < 501)
					{
						hammer.hammer.relocate(hammerX, hammerY);
					}
					if (shieldY > 501)
					{
						int[] getXY = shield.randomCoord();
						shield.spawn(getXY[0], getXY[1]);
						shieldY = getXY[1];
						shieldX = getXY[0];
					}
					else if (shieldY < 501)
					{
						shield.shield.relocate(shieldX, shieldY);
					}
					if (wallY > 501)
					{
						wall.spawn(0, 0);
//                    wallX = getXY[0];
						wallY = -175;
						screenElements.getChildren().add(wall.wall);
					}
					else if (wallY < 501)
					{
						wall.wall.relocate(wallX, wallY);
					}
					if (weedY > 501)
					{
						int[] getXY = weed.randomCoord();
						weed.spawn(getXY[0], getXY[1]);
						weedY = getXY[1];
						weedX = getXY[0];
					}
					else
						weed.weed.relocate(weedX, weedY);

					buttonsGroup.toFront();
					serializer.writeAllValues();
					serializer.serializeSnake(snake);
					serializer.serializeBlock(block);
					serializer.serializeCoin(coin);
					serializer.serializeMagnet(magnet);
					serializer.serializeShield(shield);
					serializer.serializeHammer(hammer);
					serializer.serializeScore(score);
					serializer.serializeWall(wall);
				}
			};

			stage.setTitle("Snake Vs Block");
		stage.setScene(scene);
		stage.show();
		timer.start();
	}

		private static void adjustFactor()
		{
			if (factor == 0.1) // in case of Block collision
				return;
			if (Snake.length == 0)
			{
				factor = 0;
			}
			else
			{
				if (Weed.isActivated)
				{
					endTime = System.nanoTime();
					endTime /= 1e6;

					if (endTime - startTime >= 2 * delay + 50)
					{
						factor = weedFactor;
						factor += (Snake.length) * 0.05;
					}
				}
				else
				{
					if (Snake.length <= 5)
						factor = 2;
					else
						factor = 2 + (Snake.length - 5) * 0.04;
				}
			}
		}

	/**
	 * Exits the game
	 * @param mouseEvent
	 */
	public void closeApp(MouseEvent mouseEvent)
	{
		System.exit(0);
	}
}
