/**
 * 
 */
package mmv.CS2114.Snakes;

import android.util.AttributeSet;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author impaler This is the main surface that handles the ontouch events and
 *         draws the image to the screen.
 */
public class MainGamePanel
    extends SurfaceView
    implements SurfaceHolder.Callback
{
    private static final String TAG   = MainGamePanel.class.getSimpleName();

    private MainThread          thread;
    private Snake               droid;
    private float               timer = 0;


    public MainGamePanel(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // adding the callback (this) to the surface holder to intercept events
        getHolder().addCallback(this);

        // create droid and load bitmap
        droid =
            new Snake(BitmapFactory.decodeResource(
                getResources(),
                R.drawable.droid_1), 8, 2);

        // create the game loop thread
        thread = new MainThread(getHolder(), this);

        // make the GamePanel focusable so it can handle events
        setFocusable(true);
    }


    public void surfaceChanged(
        SurfaceHolder holder,
        int format,
        int width,
        int height)
    {
    }


    public void surfaceCreated(SurfaceHolder holder)
    {
        // at this point the surface is created and
        // we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }


    public void surfaceDestroyed(SurfaceHolder holder)
    {
        Log.d(TAG, "Surface is being destroyed");
        // tell the thread to shut down and wait for it to finish
        // this is a clean shutdown
        boolean retry = true;
        while (retry)
        {
            try
            {
                thread.join();
                retry = false;
            }
            catch (InterruptedException e)
            {
                // try again shutting down the thread
            }
        }
        Log.d(TAG, "Thread was shut down cleanly");
    }


    public void render(Canvas canvas)
    {
        canvas.drawColor(Color.BLACK);
        droid.draw(canvas);
    }


    /**
     * This is the game update method. It iterates through all the objects and
     * calls their update method if they have one or calls specific engine's
     * update method.
     */
    public void update()
    {
        timer += 0.1;

        if (timer > 0.2)
        {
            timer = 0;
            // Update the lone droid
            droid.update();
            /*
             * // check collision with right wall if heading right if
             * (droid.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT &&
             * droid.getX() + droid.getBitmap().getWidth() / 2 >= getWidth()) {
             * droid.getSpeed().toggleXDirection(); } // check collision with
             * left wall if heading left if (droid.getSpeed().getxDirection() ==
             * Speed.DIRECTION_LEFT && droid.getX() -
             * droid.getBitmap().getWidth() / 2 <= 0) {
             * droid.getSpeed().toggleXDirection(); } // check collision with
             * bottom wall if heading down if (droid.getSpeed().getyDirection()
             * == Speed.DIRECTION_DOWN && droid.getY() +
             * droid.getBitmap().getHeight() / 2 >= getHeight()) {
             * droid.getSpeed().toggleYDirection(); } // check collision with
             * top wall if heading up if (droid.getSpeed().getyDirection() ==
             * Speed.DIRECTION_UP && droid.getY() -
             * droid.getBitmap().getHeight() / 2 <= 0) {
             * droid.getSpeed().toggleYDirection(); }
             */

        }
    }


    public void changeLeft()
    {
        if (droid.direction == droid.LEFT)
        {
            droid.direction = droid.DOWN;
        }
        else if (droid.direction == droid.DOWN)
        {
            droid.direction = droid.RIGHT;
        }
        else if (droid.direction == droid.RIGHT)
        {
            droid.direction = droid.UP;
        }
        else if (droid.direction == droid.UP)
        {
            droid.direction = droid.LEFT;
        }

    }


    public void changeRight()
    {
        if (droid.direction == droid.LEFT)
        {
            droid.direction = droid.UP;
        }
        else if (droid.direction == droid.DOWN)
        {
            droid.direction = droid.LEFT;
        }
        else if (droid.direction == droid.RIGHT)
        {
            droid.direction = droid.DOWN;
        }
        else if (droid.direction == droid.UP)
        {
            droid.direction = droid.RIGHT;
        }
    }
}
