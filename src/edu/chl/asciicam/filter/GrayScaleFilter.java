package edu.chl.asciicam.filter;

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

import android.graphics.Bitmap;

import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;


/**
 * A filter class that converts a picture into gray scale.
 * @author fredha
 * 
 */

public class GrayScaleFilter implements FilterInterface{

	/**
	 * This method converts the given Bitmap to a gray scale-bitmap and returns it.
	 * @param bm The Bitmap to be converted.
	 * @return The original bitmap converted to gray scale.
	 */

	public Bitmap convert(Bitmap bm) {
		int width, height;
		width = bm.getWidth();
		height = bm.getHeight();
		
		//Create a new bitmap using the properties of the original.
		Bitmap bmGrayScale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		Canvas can = new Canvas(bmGrayScale);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		//By setting the saturation to 0 it maps the color to gray scale.
		cm.setSaturation(0);
		ColorMatrixColorFilter cmcf = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(cmcf);
		can.drawBitmap(bm, 0, 0, paint);

		return bmGrayScale;
	}

}
