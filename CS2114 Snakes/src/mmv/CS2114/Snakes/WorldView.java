package mmv.CS2114.Snakes;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;

// -------------------------------------------------------------------------
/**
 * Class displays a visual representation of the world.
 * 
 * @author Michael Moy (mmoy92)
 * @version 2012.04.14
 */

public class WorldView
    extends View
{
    private World world;


    public WorldView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        world = new World();
    }


    public void onDraw(Canvas canvas)
    {
        // Set up paint object
        Paint paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setStyle(Style.FILL);

        // Get box tile size
        float boxSize = getWidth() / world.WORLD_WIDTH;
        SnakePart head = world.snake.parts.get(0);

        canvas.drawRect(head.y - 5, head.x - 5, head.y + 5, head.x + 5, paint);
        paint.setColor(Color.BLUE);
        for (SnakePart sp : world.snake.parts)
        {
            if (!sp.equals(head))
            {
                canvas.drawRect(sp.y - 5, sp.x - 5, sp.y + 5, sp.x + 5, paint);
            }
        }
        paint.setColor(Color.GREEN);
        Token token = world.token;
        canvas.drawRect(
            token.y - 5,
            token.x - 5,
            token.y + 5,
            token.x + 5,
            paint);

    }

}
