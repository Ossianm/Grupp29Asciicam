package edu.chl.asciicam.activity;

import edu.chl.asciicam.camera.CameraPreview;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;


/**
 * This activity is for taking a picture. The background will be set to
 * preview what the camera is seeing.
 * Most of the base code from http://developer.android.com/guide/topics/media/camera.html#custom-camera
 * @author Braaf
 *
 */
public class CameraScreen extends Activity {
	//For debug
	private static final String TAG = "CameraScreen";
	
	private Camera mCamera;
    private CameraPreview mPreview;
    
    //Buttons for capture and back
    Button capture;
    Button back;
    
    /**
     * This is called automagically by android system.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_screen);

        // Create an instance of Camera
        mCamera = getCameraInstance();
        
        //Find buttons
        capture = (Button) findViewById(R.id.button_capture);
        back = (Button) findViewById(R.id.button_back);

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        
      //Find buttons
        capture = (Button) findViewById(R.id.button_capture);
        back = (Button) findViewById(R.id.button_back);
    }
    
	/**
	 *  A safe way to get an instance of the Camera object. 
	 */
	private static Camera getCameraInstance(){
	    Camera c = null;
	    try {
	        c = Camera.open(); // attempt to get a Camera instance
	    }
	    catch (Exception e){
	    	Log.d(TAG, "Error opening camera: " + e.getMessage());
	    }
	    return c; // returns null if camera is unavailable
	}
	
	/**
	 * This is called automagically by android system.
	 */
	@Override
	public void onPause() {
		super.onPause();    
		releaseCamera();              // release the camera immediately on pause event
	}
	
	/**
	 * This is called automagically by android system.
	 */
	@Override
	public void onResume(){
		if(mCamera == null){
			mCamera = getCameraInstance();
			try{
			mCamera.reconnect();
			}catch(Exception e){
				Log.d(TAG, "Error reconnecting camera: " + e.getMessage());
			}
			mPreview.setCam(mCamera);
		}
		super.onResume();
	}
	
	//use this method to release camera if we dont need it anymore.
	private void releaseCamera(){
        if (mCamera != null){
            mCamera.release();        // release the camera for other applications
            mCamera = null;
            mPreview.setCam(null);
        }
    }
}
