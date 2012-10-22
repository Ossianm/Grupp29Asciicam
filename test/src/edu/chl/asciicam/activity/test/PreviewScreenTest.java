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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;
import edu.chl.asciicam.activity.PreviewScreen;
import edu.chl.asciicam.file.FileController;
import edu.chl.asciicam.util.Convert;

public class PreviewScreenTest extends ActivityInstrumentationTestCase2<PreviewScreen>{
	private static final String OPTIONS_FILENAME = "OptionsAscii";
	private Activity mActivity;
	private byte[] data;
	private FileController fc;
	private Context c;

	public PreviewScreenTest(){
		super("edu.chl.asciicam.activity", PreviewScreen.class);
	}

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		c = getInstrumentation().getTargetContext();
		
		fc = new FileController(c);
		
		//Loading a testpicture to use in the tests
		InputStream strin = c.getResources().openRawResource(R.drawable.test);
		data = new byte[strin.available()];
		strin.read(data);
		strin.close();
		//And save it before getting activity so it gets used as bg.
		fc.savePicPrivate(data);
		
		//Turning off touch so we can send inputs from test
		setActivityInitialTouchMode(false);
		//Set up intent for the 'loaded-from-camera' mode.
		Intent i = new Intent(c, PreviewScreen.class);
		i.putExtra("id", "taken");
		setActivityIntent(i);
		mActivity = getActivity();
		
		
	}

	/**
	 * Tests to set a "taken" picture from the camera to background
	 */
	public void testTakenPicture(){
		//Get the background from the previewscreen after the loadFromCamera is done
		// and compare it to original picture.
		Bitmap bmp = ((PreviewScreen) mActivity).getBgBmp();
		Bitmap orig = BitmapFactory.decodeByteArray(data, 0, data.length);
		//Since original image is so small it should not have been compressed
		//but only rotated.
		assertEquals(bmp.getWidth(), orig.getHeight());
		assertEquals(bmp.getHeight(), orig.getWidth());
	}

	/**
	 * Tests to load a picture from the phone and set it as background
	 * @throws IOException 
	 */
	public void testLoadedPicture() throws IOException{
		//First set up for 'loaded-from-phone' mode
		Intent i = new Intent(c, PreviewScreen.class);
		i.putExtra("id", "loaded");
		//Now we have to save the testfile
		fc.savePic(data);
		//set up filepath to pass on to activity
		SharedPreferences settings = c.getSharedPreferences(OPTIONS_FILENAME, Context.MODE_PRIVATE);
		int seqnmbr = settings.getInt(FileController.SEQUENCENUMBER, 1);
		File path = new File(Environment.getExternalStorageDirectory(), "DCIM" + File.separator + "AsciiCAM");
		File file = new File(path.getPath() + File.separator + "ASCIIPIC_" + (seqnmbr-1) + ".jpg");
		i.putExtra("filePath", file.getPath());
		setActivityIntent(i);
		mActivity = getActivity();
		
		//Get the background from the previewscreen after the loadFromPhone is done
		// and compare it to original picture
		Bitmap bmp = ((PreviewScreen) mActivity).getBgBmp();
		Bitmap orig = BitmapFactory.decodeByteArray(data, 0, data.length);
		
		//Since original image is so small it should not have been compressed
		//but only rotated (same as testTakenPicture()).
		assertEquals(bmp.getWidth(), orig.getHeight());
		assertEquals(bmp.getHeight(), orig.getWidth());
		
	}


}
