package edu.chl.asciicam.activity.test;

import edu.chl.asciicam.activity.CameraScreen;
import edu.chl.asciicam.camera.CameraPreview;
import android.app.Activity;
import android.hardware.Camera;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class CameraScreenTest extends
		ActivityInstrumentationTestCase2<CameraScreen> {
	
	private Activity mActivity;
	private FrameLayout mFrame;
	private RelativeLayout mRel;
	private Camera cam;
	private CameraPreview camPreview;
	
	public CameraScreenTest(){
		super("edu.chl.asciicam.activity", CameraScreen.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//turn off touch se we can send inputs from test.
		setActivityInitialTouchMode(false);

		mActivity = (CameraScreen) getActivity();
		//mFrame is the frame which the camera is in.
		mFrame = (FrameLayout) mActivity.findViewById(edu.chl.asciicam.activity.R.id.preview);
		//mView is the entire surrounding layout.
		mRel = (RelativeLayout) mActivity.findViewById(edu.chl.asciicam.activity.R.id.activity_camera_screen);
		cam = ((CameraScreen) mActivity).getCamera();
		camPreview = ((CameraScreen) mActivity).getPreview();
	}
	
	/**
	 * Test startup of activity.
	 */
	public void testPreConditions(){
		assertTrue(cam != null);
		assertTrue(camPreview != null);
	}
}
