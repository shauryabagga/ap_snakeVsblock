package sample;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * Simulates the snake object which is the main actor in this game
 */
class Snake
{
    ArrayList<Circle> snake = new ArrayList<>();
    Group Snake = new Group();
    double x = 300.0;
    double y = 300.0;
    double r = 10.0;
    static int length;
    int R,G,B;
	TranslateTransition[] allTransitions = new TranslateTransition[5];
    Snake(int initial )
    {
        Snake.setAutoSizeChildren(false);
        for ( int i = 0 ; i < initial; i++ )
        {
            Circle temp = new Circle();
            temp.setCenterX(x);
            temp.setCenterY(y);
            temp.setRadius(r);
            temp.setFill(Color.YELLOW);
            snake.add(temp);
            Snake.getChildren().add(temp);
            y += (2*r+1);
        }
        length = initial;
    }

    void move( int direction )
    {
		if ( Weed.isActivated )
		{
			Controller.weedFactor = Controller.factor;
			Controller.factor = 0.220;
			Controller.delay = 6*Controller.normMovTime;
		}
		else
		{
			Controller.delay = Controller.normMovTime;
		}
		if ( direction == 1 )
		{
			if ( x <= 420 )
			{
				x += 120;

				for ( int i = 0; i < 5; i++ )
				{
					allTransitions[i] = new TranslateTransition();
					allTransitions[i].setNode(snake.get(i));
					allTransitions[i].setByX(120);
					allTransitions[i].setCycleCount(1);
					allTransitions[i].setAutoReverse(false);
					allTransitions[i].setDuration(Duration.millis(Controller.delay + 35*i));
				}
				Controller.startTime = System.nanoTime();
				Controller.startTime /= 1e6;

				for ( int i= 0; i < 5; i++ )
					allTransitions[i].play();
			}
		}
		else
		{
			if ( x >= 180 )
			{
				x -= 120;
				for ( int i = 0; i < 5; i++ )
				{
					allTransitions[i] = new TranslateTransition();
					allTransitions[i].setNode(snake.get(i));
					allTransitions[i].setByX(-120);
					allTransitions[i].setCycleCount(1);
					allTransitions[i].setAutoReverse(false);
					allTransitions[i].setDuration(Duration.millis(Controller.delay + 35*i));
				}

				Controller.startTime = System.nanoTime();
				Controller.startTime /= 1e6;

				for ( int i= 0; i < 5; i++ )
					allTransitions[i].play();
			}
		}
    }

    void adjustColor()
    {
    	length = Math.abs(length);

        R = 128 + ((length * 5) % 128);
        G = 255 - ((length * 5) % 256);
        B = 128 + ((length * 5) % 128);

        for (int i = 0; i < Block.numBlocks; ++i)
			snake.get(i).setFill(Color.rgb(R, G, B));
    }
}