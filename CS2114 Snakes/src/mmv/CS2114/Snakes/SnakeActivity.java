package mmv.CS2114.Snakes;


import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SnakeActivity extends Activity {
    /** Called when the activity is first created. */
	
	private static final String TAG = SnakeActivity.class.getSimpleName();
	private MainGamePanel panel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requesting to turn the title OFF
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set our MainGamePanel as the View
        setContentView(R.layout.main);
        panel = (MainGamePanel)findViewById(R.id.game);
        Log.d(TAG, "View added");
    }
    public void clickLeft(View view)
    {
        panel.changeLeft();

    }
    public void clickRight(View view)
    {
        panel.changeRight();

    }
	@Override
	protected void onDestroy() {
		Log.d(TAG, "Destroying...");
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "Stopping...");
		super.onStop();
	}
    
    
}