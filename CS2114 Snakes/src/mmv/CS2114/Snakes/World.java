package mmv.CS2114.Snakes;


import java.util.Observable;
import java.util.Random;

// -------------------------------------------------------------------------
/**
 * World class sets up the game array and initializes the snake as well as
 * adding random tokens onto the playing field.
 * 
 * @author Michael Moy (mmoy92)
 * @version 2012.04.14
 */

public class World extends Observable
{
    static final int   WORLD_WIDTH     = 10;
    static final int   WORLD_HEIGHT    = 13;
    static final int   SCORE_INCREMENT = 10;
    static final float TICK_INITIAL    = 0.5f;
    static final float TICK_DECREMENT  = 0.05f;
    public Snake       snake;
    public Token       token;
    public boolean     gameOver        = false;                                  ;
    public int         score           = 0;
    boolean            fields[][]      = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    Random             random          = new Random();
    float              tickTime        = 0;
    static float       tick            = TICK_INITIAL;

    public World (Snake newSnake, Token newToken)
    {
        snake = newSnake;
        token = newToken;
    }
    // ----------------------------------------------------------
    /**
     * Spawns random tokens within the world array.
     */
    public void placeToken()
    {
        //Sets the size for the fields array.
        for (int x = 0; x < WORLD_WIDTH; x++)
        {
            for (int y = 0; y < WORLD_HEIGHT; y++)
            {
                fields[x][y] = false;
            }
        }
        //Loop through the number of segments in the snake.
        int len = snake.parts.size();
        for (int i = 0; i < len; i++)
        {
            //Set all cells occupied by the snake to TRUE
            SnakePart part = snake.parts.get(i);
            fields[part.x][part.y] = true;
        }
        //Set a random XY location for the token.
        int tokenX = random.nextInt(WORLD_WIDTH);
        int tokenY = random.nextInt(WORLD_HEIGHT);
        while (true)
        {
            //End while loop if the cell is FALSE (not occupied)
            if (fields[tokenX][tokenY] == false)
            {
                break;
            }
            //Advance the token position up to the XY boundaries.
            tokenX += 1;
            if (tokenX >= WORLD_WIDTH)
            {
                tokenX = 0;
                tokenY += 1;
                if (tokenY >= WORLD_HEIGHT)
                {
                    tokenY = 0;
                }
            }
        }
        token = new Token(tokenX, tokenY);
    }


    // ----------------------------------------------------------
    /**
     * Updates all game objects in the world as well as win/lose conditions.
     * @param deltaTime -Time step to use as reference.
     */
    public void update(float deltaTime)
    {
        //Stop updating once game is over.
        if (gameOver)
        {
            return;
        }
        //Increment the tickTime
        tickTime += deltaTime;
        //Conditions to check for each "tick" of the timer.
        while (tickTime > tick)
        {
            tickTime -= tick;
            //Advance the snake.
            snake.advance();
            //Set gameOver to true once the snake has bitten itself.
            if (snake.checkBitten())
            {
                gameOver = true;
                return;
            }
            //Reset the head as the first segment in the snake.
            SnakePart head = snake.parts.get(0);
            //Check if the snake has "eaten" a token.
            if (head.x == token.x && head.y == token.y)
            {
                //Increase score
                score += SCORE_INCREMENT;
                //Adds another segment
                snake.eat();
                //Check if the snake has completely occupied the game world.
                if (snake.parts.size() == WORLD_WIDTH * WORLD_HEIGHT)
                {
                    gameOver = true;
                    return;
                }
                else
                    //If game world is not full, add a random token.
                {
                    placeToken();
                }
                //For every ten tokens, increase the game speed
                if (score % 100 == 0 && tick - TICK_DECREMENT > 0)
                {
                    tick -= TICK_DECREMENT;
                }
            }
        }
    }

}
