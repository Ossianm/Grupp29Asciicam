package edu.chl.asciicam.activity.test;

import edu.chl.asciicam.activity.CameraScreen;
import edu.chl.asciicam.camera.CameraPreview;
import android.app.Activity;
import android.app.Instrumentation;
import android.hardware.Camera;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class CameraScreenTest extends
		ActivityInstrumentationTestCase2<CameraScreen> {
	
	private Activity mActivity;
	
	public CameraScreenTest(){
		super("edu.chl.asciicam.activity", CameraScreen.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//turn off touch se we can send inputs from test.
		setActivityInitialTouchMode(false);

		mActivity = (CameraScreen) getActivity();
	}
	
	/**
	 * Test startup of activity.
	 */
	public void testPreConditions(){
		assertTrue(((CameraScreen) mActivity).getCamera() != null);
		assertTrue(((CameraScreen) mActivity).getPreview() != null);
	}
	
	/**
	 * Test the onPause and onResume methods.
	 */
	public void testPauseResume(){
		Instrumentation mInstr = this.getInstrumentation();
		//Pause activity
		mInstr.callActivityOnPause(mActivity);
		//Make sure camera is released!
		assertTrue(((CameraScreen) mActivity).getCamera() == null);
		//Start activity
		mInstr.callActivityOnResume(mActivity);
		//Make sure camera is reconnected.
		assertTrue(((CameraScreen) mActivity).getCamera() != null);
		
	}
}
