package sample;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Main class that starts the application
 */
public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("splashScreen.fxml"));
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("Snake vs Block");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.getIcons().add(new Image("https://bitbucket.org/karan2dec/ap-game-images/downloads/SB.jpg"));
		primaryStage.show();

		FadeTransition ft = new FadeTransition(Duration.millis(2000), root);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
	}

	public static void main(String[] args)
	{
		saveImage(Coin.CoinURL, Coin.Coin_Image);
		saveImage(Hammer.HammerURL, Hammer.Hammer_Image);
		saveImage(Shield.ShieldURL, Shield.Shield_Image);
		saveImage(Magnet.MagnetURL, Magnet.Magnet_Image);
		saveImage(Weed.WeedURL, Weed.Weed_Image);
		launch(args);
	}

	/**
	 * Used for saving image locally
	 * @param url
	 * @param name
	 */
	public static void saveImage(String url, String name)
	{
		File f = new File(name);

		if (f.exists())
			return;

		try (InputStream in = new URL(url).openStream())
		{
			Files.copy(in, Paths.get(name));
		}
		catch (IOException e)
		{
		}
	}
}
