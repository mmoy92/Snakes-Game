package mmv.CS2114.Snakes;

import java.util.Observable;
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
public class Snake extends Observable
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
        setChanged();
        notifyObservers();
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
        setChanged();
        notifyObservers();
    }

    /**
     * Has the Snake add a new part to the end of its body when it eats a token.
     */
    public void eat()
    {
        SnakePart end = parts.get(parts.size() - 1);
        parts.add(new SnakePart(end.x, end.y));
        setChanged();
        notifyObservers();
    }

    /**
     * Advances each part of the snake one cell in the direction it is currently
     * moving in.
     */
    public void advance()
    {
        SnakePart head = parts.get(0);

        // Deals with moving each of the tails to the position of the part
        // before it.
        int len = parts.size() - 1;
        for (int i = len; i > 0; i--)
        {
            SnakePart before = parts.get(i - 1);
            SnakePart part = parts.get(i);
            part.x = before.x;
            part.y = before.y;
        }

        // Next we move the head in on position depending on where it is
        // currently heading
        if (direction == UP)
        {
            head.y -= 1;
        }
        if (direction == LEFT)
        {
            head.x -= 1;
        }
        if (direction == DOWN)
        {
            head.y += 1;
        }
        if (direction == RIGHT)
        {
            head.x += 1;
        }

        // Then we deal with wrapping the snake through to the other side of the board
        // if he goes through on side of the screen.
        if (head.x < 0)
        {
            head.x = 9;
        }
        if (head.x > 9)
        {
            head.x = 0;
        }
        if (head.y < 0)
        {
            head.y = 12;
        }
        if (head.y > 12)
        {
            head.y = 0;
        }
        
        setChanged();
        notifyObservers();
    }

    /**
     * Checks to see if the snake has ran into himself at some point, if so, the
     * game is over.
     *
     * @return Whether he has bitten himself or not.
     */
    public boolean checkBitten()
    {
        int len = parts.size();
        SnakePart head = parts.get(0);
        for (int i = 1; i < len; i++)
        {
            SnakePart part = parts.get(i);
            if (part.x == head.x && part.y == head.y)
            {
                return true;
            }
        }
        setChanged();
        notifyObservers();
        return false;
        
    }
}


