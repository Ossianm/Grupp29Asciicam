package edu.chl.asciicam.activity.test;

//Copyright 2012 Robin Braaf, Ossian Madisson, Martin Thörnesson, Fredrik Hansson and Jonas Åström.
//
//This file is part of Asciicam.
//
//Asciicam is free software: you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//Asciicam is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with Asciicam.  If not, see <http://www.gnu.org/licenses/>.

import edu.chl.asciicam.activity.CameraScreen;
import android.app.Activity;
import android.app.Instrumentation;
import android.hardware.Camera;
import android.test.ActivityInstrumentationTestCase2;

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
		//Make sure we can get a camera object so it is properly released.
		try{
			Camera c = Camera.open();
			c.release();
			c=null;
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
		//Start activity
		mInstr.callActivityOnResume(mActivity);
		//Make sure camera is reconnected.
		assertTrue(((CameraScreen) mActivity).getCamera() != null);
		
	}
}
