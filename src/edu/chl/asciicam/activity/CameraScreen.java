package edu.chl.asciicam.activity;

import edu.chl.asciicam.camera.CameraPreview;
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
	CameraPreview cameraView = null;
	
	/**
	 * 
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_screen);
        
        //Start the camera.
        //openCamera();
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
    	//releaseCameraAndPreview();
    }
    
    //Called when activity is resumed after a pause.
    @Override
    public void onResume(){
    	//Regain access to camera so we can use it again.
    	//openCamera();
    }
    
    
    //Use this method to gain access to the camera if it has been released.
    private void openCamera(){
    	//gain access to camera so we can use it.
    	try{
    		releaseCameraAndPreview();
        	//Access the camera
        	asciiCamera = Camera.open();
        	
        }catch(RuntimeException e){
        	//If the camera is already in use
        }
    }
    
    //Release the camera
    private void releaseCameraAndPreview() {
        cameraView.setCamera(null);
        if (asciiCamera != null) {
        	asciiCamera.release();
        	asciiCamera = null;
        }
    }
}
