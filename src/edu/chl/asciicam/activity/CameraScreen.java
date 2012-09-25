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
	Camera asciiCamera = null;
	
	/**
	 * 
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_screen);
        
        try{
        	//Access the camera
        	asciiCamera = Camera.open();
        	
        }catch(RuntimeException e){
        	//If the camera is already in use
        }
    }
    
    /**
     * 
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_camera_screen, menu);
        return true;
    }
    
    //Called when activity is paused
    @Override
    public void onPause(){
    	//Release the camera so other applications can use it
    	if (asciiCamera != null) {
            asciiCamera.release();
            asciiCamera = null;
        }
    }
    
    //Called when activity is resumed after a pause.
    @Override
    public void onResume(){
    	//Regain access to camera so we can use it again.
    	asciiCamera = null;
    	try{
        	//Access the camera
        	asciiCamera = Camera.open();
        	
        }catch(RuntimeException e){
        	//If the camera is already in use
        }
    }
}
