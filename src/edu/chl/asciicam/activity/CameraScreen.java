package edu.chl.asciicam.activity;

import edu.chl.asciicam.camera.CameraPreview;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_screen);

     // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }
    
	/** A safe way to get an instance of the Camera object. */
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
	
	@Override
	public void onPause() {
		super.onPause();    
		releaseCamera();              // release the camera immediately on pause event
	}
	
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
	
	private void releaseCamera(){
        if (mCamera != null){
            mCamera.release();        // release the camera for other applications
            mCamera = null;
            mPreview.setCam(null);
        }
    }
}
