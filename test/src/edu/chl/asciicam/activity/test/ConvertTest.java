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

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.test.AndroidTestCase;
import edu.chl.asciicam.util.Convert;

public class ConvertTest extends AndroidTestCase {
	
	Context c;
	
	public void testConversions() throws IOException{
		InputStream strin = c.getResources().openRawResource(R.drawable.dsc_8219);
		byte[] data = new byte[strin.available()];
		strin.read(data);
		strin.close();
		
		Bitmap bm = Bitmap.createBitmap(Convert.byteArrayToBitmap(data));
		byte[] modified = Convert.bitmapToByteArray(bm);
		
		assertEquals(data, modified);
		
	}
}
