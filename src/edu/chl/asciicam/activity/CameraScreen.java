package edu.chl.asciicam.activity;

import edu.chl.asciicam.camera.CameraPreview;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.FrameLayout;


/**
 * This activity is for taking a picture. The background will be set to
 * preview what the camera is seeing.
 * Most of the base code from http://developer.android.com/guide/topics/media/camera.html#custom-camera
 * @author Braaf
 *
 */
public class CameraScreen extends Activity {
	
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
	public static Camera getCameraInstance(){
	    Camera c = null;
	    try {
	        c = Camera.open(); // attempt to get a Camera instance
	    }
	    catch (Exception e){
	        // Camera is not available (in use or does not exist)
	    }
	    return c; // returns null if camera is unavailable
	}
	
	@Override
	public void onPause() {
		super.onPause();      // if you are using MediaRecorder, release it first
		releaseCamera();              // release the camera immediately on pause event
	}
	
	private void releaseCamera(){
        if (mCamera != null){
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }
}
