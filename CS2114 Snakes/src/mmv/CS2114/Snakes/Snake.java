package mmv.CS2114.Snakes;

import java.util.ArrayList;
import java.util.List;

// -------------------------------------------------------------------------
/**
 *  Snake class used to represent the snake as an entity.
 *
 *  Has an ArrayList of SnakePart objects representing the head and tails, then
 *  an int value indicating where the snake is currently heading direction
 *  wise.
 *
 *  @author Mike Gazdich (mgazdich)
 *  @version 2012.04.14
 */
public class Snake
{

    // Fields ---------------------------------------------------------------
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;

    public List<SnakePart> parts = new ArrayList<SnakePart>();
    public int direction;


    // Constructor ---------------------------------------------------------

    /**
     * Initializes the Snake object for when the game begins.
     */
    public Snake()
    {
        direction = UP;
        parts.add(new SnakePart(5,6));
        parts.add(new SnakePart(5,7));
        parts.add(new SnakePart(5,8));
    }

    // Methods --------------------------------------------------------------

    /**
     * Deals with having the snake turn to the left.
     */
    public void turnLeft()
    {
        direction += 1;
        if (direction > RIGHT)
        {
            direction = UP;
        }
    }

    /**
     * Deals with having the snake turn to the right.
     */
    public void turnRight()
    {
        direction -= 1;
        if (direction < UP)
        {
            direction = RIGHT;
        }
    }

}


