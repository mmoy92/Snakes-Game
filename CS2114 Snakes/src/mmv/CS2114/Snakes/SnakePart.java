package mmv.CS2114.Snakes;

/**
 * // -------------------------------------------------------------------------
 * /** Class for each individual segment of the snake.
 * 
 * @author Michael Moy (mmoy92)
 * @version 2012.04.14
 */
public class SnakePart
{
    /**
     * x field.
     */
    public int x;
    /**
     * y field.
     */
    public int y;


    // ----------------------------------------------------------
    /**
     * Create a new SnakePart object.
     * @param x -X coordinate of snake part.
     * @param y -Y coordinate of snake part.
     */
    public SnakePart(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
