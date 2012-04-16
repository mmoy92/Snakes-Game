package mmv.CS2114.Snakes;

import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

// -------------------------------------------------------------------------
/**
 * @author Michael Moy (mmoy92)
 * @author Vincent Ngo (vincentn)
 * @version 2012.14.04
 */

public class SnakeActivity
    extends Activity
{
    private WorldView worldView;
    private World     world;
    private Snake     snake;
    private Token     token;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        // Retrieve mazeView
        worldView = (WorldView)findViewById(R.id.WorldView);

        snake = new Snake();
        token = new Token(1, 5);

        // Create new maze, assign it to the mazeView
        world = new World(snake, token);
        worldView.setWorld(world);

    }


    public void leftButtonClick(View view)
    {
        world.snake.turnLeft();
    }


    public void rightButtonClick(View view)
    {
        world.snake.turnRight();
    }

}
