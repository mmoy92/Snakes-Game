/**
 * 
 */
package mmv.CS2114.Snakes;

import android.graphics.BitmapFactory;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * This is a test droid that is dragged, dropped, moved, smashed against the
 * wall and done other terrible things with. Wait till it gets a weapon!
 * 
 * @author impaler
 */
public class Snake
{
    public static final int UP       = 0;
    public static final int LEFT     = 1;
    public static final int DOWN     = 2;
    public static final int RIGHT    = 3;
    public static final int WIDTH    = 12;
    public static final int HEIGHT   = 17;
    private Bitmap          bitmap;
    private Bitmap tokenBitmap;
    Random                  random   = new Random();

    public boolean          grid[][] = new boolean[WIDTH][HEIGHT];
    public List<SnakePart>  parts    = new ArrayList<SnakePart>();
    public int              direction;
    public Token            token;
    public int              score;


    public Snake(Bitmap bitmap,Bitmap tokenBitmap, int x, int y)
    {
        placeToken();
        this.bitmap = bitmap;
        this.tokenBitmap = tokenBitmap;
        parts.add(new SnakePart(x, y));
        parts.add(new SnakePart(x + 1, y));
        parts.add(new SnakePart(x + 2, y));
        parts.add(new SnakePart(x + 3, y));
        parts.add(new SnakePart(x + 4, y));
    }


    public Bitmap getBitmap()
    {
        return bitmap;
    }


    public void setBitmap(Bitmap bitmap)
    {
        this.bitmap = bitmap;
    }


    public void draw(Canvas canvas)
    {
        float boxSize = canvas.getWidth() / 13;
        for (SnakePart s : parts)
        {
            canvas.drawBitmap(bitmap, s.x * boxSize, s.y * boxSize, null);
        }

        canvas.drawBitmap(
            tokenBitmap
            , token.x * boxSize
            , token.y * boxSize
            , null);
    }


    /**
     * Method which updates the droid's internal state every tick
     */
    public void update()
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

            if (part.x == head.x && part.y == head.y)
            {
                // BITTEN
            }
            else if (head.x == token.x && head.y == token.y)
            {
                score++;
                SnakePart end = parts.get(len - 1);
                parts.add(new SnakePart(end.x, end.y));

                placeToken();
            }
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

        // Then we deal with wrapping the snake through to the other side of the
// board
        // if he goes through on side of the screen.
        if (head.x < 0)
        {
            head.x = WIDTH;
        }
        if (head.x > WIDTH)
        {
            head.x = 0;
        }
        if (head.y < 0)
        {
            head.y = HEIGHT;
        }
        if (head.y > HEIGHT)
        {
            head.y = 0;
        }
    }


    public void placeToken()
    {
        // Sets the size for the fields array.
        for (int x = 0; x < WIDTH; x++)
        {
            for (int y = 0; y < WIDTH; y++)
            {
                grid[x][y] = false;
            }
        }
        // Loop through the number of segments in the snake.
        for (SnakePart myPart : parts)
        {
            // Set all cells occupied by the snake to TRUE
            grid[myPart.x][myPart.y] = true;
        }
        // Set a random XY location for the token.
        int tokenX = random.nextInt(WIDTH);
        int tokenY = random.nextInt(HEIGHT);
        while (true)
        {
            // End while loop if the cell is FALSE (not occupied)
            if (grid[tokenX][tokenY] == false)
            {
                break;
            }
            // Advance the token position up to the XY boundaries.
            tokenX += 1;
            if (tokenX >= WIDTH)
            {
                tokenX = 0;
                tokenY += 1;
                if (tokenY >= WIDTH)
                {
                    tokenY = 0;
                }
            }
        }
        token = new Token(tokenX, tokenY);
    }
}
