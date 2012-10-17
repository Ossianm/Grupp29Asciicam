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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;
import edu.chl.asciicam.activity.PreviewScreen;
import edu.chl.asciicam.file.FileController;

public class PreviewScreenTest extends ActivityInstrumentationTestCase2<PreviewScreen>{

	private Activity mActivity;
	byte[] data;
	FileController fc;
	String filePath;

	public PreviewScreenTest(){
		super("edu.chl.asciicam.activity", PreviewScreen.class);
	}

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		
		//TODO get the filePath correct
//////////////////////////////////////
		//save a picture and set the filepath there to load it from gallery
//		try{
//			fc.savePic(data);
//		}catch(IOException e){			
//		}
//		filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
//
//		Intent i = new Intent();
//		i.putExtra("filePath", filePath);
//		setActivityIntent(i);
///////////////////////////////////
		
		//Turning off touch so we can send inputs from test
		setActivityInitialTouchMode(false);
		mActivity = getActivity();

		//Loading a testpicture to use in the tests
		InputStream strin = mActivity.getResources().openRawResource(R.drawable.test);
		data = new byte[strin.available()];
		strin.read(data);
		strin.close();
		fc.savePicPrivate(data);
	}

	/**
	 * Tests to set a "taken" picture from the camera to background
	 */
	public void testTakenPicture(){
		((PreviewScreen) mActivity).loadFromCamera();

		//Get the background from the previewscreen after the loadFromCamera is done
		// and convert it to a byte[] to be able to compare with the original pic
		Bitmap bmp = ((PreviewScreen) mActivity).getBgBmp();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 0, bos);
		byte[] bgArray = bos.toByteArray();

		//Use byteBuffers to be able to do a correct comparison
		ByteBuffer dataBB = ByteBuffer.wrap(data);
		ByteBuffer bgArrayBB = ByteBuffer.wrap(bgArray);
		assertEquals(dataBB, bgArrayBB);
	}

	/**
	 * Tests to load a picture from the phone and set it as background
	 */
	public void testLoadedPicture(){

		((PreviewScreen) mActivity).loadFromPhone();

		//Get the background from the previewscreen after the loadFromPhone is done
		// and convert it to a byte[] to be able to compare with the original pic
		Bitmap bmp = ((PreviewScreen) mActivity).getBgBmp();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 0, bos);
		byte[] bgArray = bos.toByteArray();

		//Use byteBuffers to be able to do a correct comparison
		ByteBuffer dataBB = ByteBuffer.wrap(data);
		ByteBuffer bgArrayBB = ByteBuffer.wrap(bgArray);
		assertEquals(dataBB, bgArrayBB);
	}


}
