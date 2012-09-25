package edu.chl.asciicam.activity;

import android.hardware.Camera;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
/**
 * This activity is for taking a picture. The background will be set to
 * preview what the camera is seeing.
 * 
 * @author Braaf
 *
 */
public class CameraScreen extends Activity {
	
	//Camera object
	Camera asciiCamera;
	
	/**
	 * 
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_screen);
    }
    
    /**
     * 
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_camera_screen, menu);
        return true;
    }
}
