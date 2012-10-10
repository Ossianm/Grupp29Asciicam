package edu.chl.asciicam.filter;

import android.graphics.Bitmap;

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

/**
 * A simple interface for filter classes.
 * @author fredha
 * 
 */

public interface FilterInterface {

	public Bitmap convert(Bitmap bm);
	
	public Bitmap arrayToBitmap(byte[] byteArray){
		Bitmap bitmap = BitmapFactory.decodeFile(byteArray);
		ByteArrayOutputStream blob = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 0 /*ignored for PNG*/, blob);
		byte[] bitmapdata = blob.toByteArray();
		
		Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata , 0, bitmapdata .length);
	}

}

