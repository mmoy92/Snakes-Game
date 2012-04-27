/**
 * 
 */
package mmv.CS2114.Snakes;

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

    private Bitmap          bitmap;               // the actual
// bitmap
    private boolean         touched;         // if droid is touched/picked up
    public static final int UP    = 0;
    public static final int LEFT  = 1;
    public static final int DOWN  = 2;
    public static final int RIGHT = 3;
    
    public boolean grid[][] = new boolean [12][16];
    public List<SnakePart>  parts = new ArrayList<SnakePart>();
    public int              direction;


    public Snake(Bitmap bitmap, int x, int y)
    {
        this.bitmap = bitmap;
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
        // canvas.drawB
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
            head.x = 12;
        }
        if (head.x > 12)
        {
            head.x = 0;
        }
        if (head.y < 0)
        {
            head.y = 17;
        }
        if (head.y > 17)
        {
            head.y = 0;
        }
    }
}
