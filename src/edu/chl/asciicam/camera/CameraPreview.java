package edu.chl.asciicam.camera;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

/**
 * A basic camera preview class, mostly the same as the camera guide from google.
 * 
 * @author Braaf
 *
 */
public class CameraPreview extends ViewGroup implements SurfaceHolder.Callback {
	
	SurfaceView asciiSurfView;
    SurfaceHolder asciiSurfHolder;
    Camera cam;
	
    /**
     * 
     * @param context Activity using an object of this class
     * @param c The camera object used by activity.
     */
	public CameraPreview(Context context, Camera c){
		super(context);
		setCamera(c);
		asciiSurfView = new SurfaceView(context);
		addView(asciiSurfView);
		
		// Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
		asciiSurfHolder = asciiSurfView.getHolder();
		asciiSurfHolder.addCallback(this);
		asciiSurfHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
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

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * @param c Camera object to be used for preview.
	 */
	public void setCamera(Camera c) {
		cam = c;
	}

	

}
