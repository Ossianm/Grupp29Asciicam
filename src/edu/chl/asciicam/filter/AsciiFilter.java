package edu.chl.asciicam.filter;

import android.graphics.Bitmap;
import edu.chl.asciicam.util.Convert;
import android.graphics.Color;

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
 * A filter class that converts a picture to ASCII characters.
 * @author fredha
 * 
 */

public class AsciiFilter implements FilterInterface{


	/**
	 * Converts a bitmap into ASCII characters and returns the result.
	 * @param bm The Bitmap to be converted.
	 * 
	 */
	public Bitmap convert(Bitmap bm) {

		GrayScaleFilter gsf = new GrayScaleFilter();
		Bitmap bmAscii = Bitmap.createBitmap(gsf.convert(bm)); //Returns a Bitmap with the same dimension as bm, but in gray scale.

		int height = bmAscii.getHeight();
		int width = bmAscii.getWidth();
		int pixel, A,R,G,B;

		for(int h=0; h<height; h+=10){
			for (int w=0; w<width; w+=5){
				pixel = bmAscii.getPixel(w,h);
				A=Color.alpha(pixel);
				R=Color.red(pixel);
				G=Color.green(pixel);
				B=Color.blue(pixel);	

			}
		}

		return null;
	}

}
