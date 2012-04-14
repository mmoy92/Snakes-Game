package mmv.CS2114.Snakes;

// -------------------------------------------------------------------------
/**
 *  Simple class used to represent the tokens that the snake will be
 *  attempting to eat.
 *
 *
 *  Hold simply two integer fields which represent the x and y
 *  coordinate of where on the board the token is at. Has no methods other than
 *  a constructor to create a new token.
 *
 *
 *
 *  @author Mike Gazdich (mgazdich)
 *  @version 2012.04.12
 */
public class Token
{

    private int x, y;

    public Token()
    {
        x = 0;
        y = 0;
    }
    public Token(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
