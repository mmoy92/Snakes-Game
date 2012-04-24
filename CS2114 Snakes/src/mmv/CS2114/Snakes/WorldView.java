package mmv.CS2114.Snakes;



import java.util.Observable;
import java.util.Observer;
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

    }


    public void setWorld(World newWorld)
    {
        world = newWorld;
        if (world == null)
        {
            return;
        }
        world.addObserver(new WorldObserver());
    }


    public void onDraw(Canvas canvas)
    {

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


    /**
     * An observer that listens for changes made to the Maze Model. THis is a
     * nested class inside the view so that it can still access methods that
     * belong to the surrounding view.
     */
    private class WorldObserver
        implements Observer
    {

        // ~ Methods ...........................................................

        // ----------------------------------------------------------

        /**
         * Called when the Maze (model) is changed (for example the value of the
         * cell is set to a different value.
         * 
         * @param observable
         *            the Observable object that was changed
         * @param data
         *            extra data about the notification; unused here
         */
        public void update(Observable observable, Object data)
        {
            // The invalidate() method is used to force a view to be repainted
            // at the earliest opportunity (which in most cases is essentially
            // immediately, but may not always be). Note that this is a method
            // on the View class, not the Observer.
            invalidate();

        }

    }

}
