package edu.chl.asciicam.camera;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * A basic camera preview class, mostly the same as the camera guide from google.
 * 
 * @author Braaf
 *
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

	public CameraPreview(Context context, Camera c){
		super(context);
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	

}
