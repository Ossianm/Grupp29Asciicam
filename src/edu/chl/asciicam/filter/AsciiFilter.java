package edu.chl.asciicam.filter;

import android.graphics.Bitmap;
import edu.chl.asciicam.util.Convert;
import android.graphics.Color;
import edu.chl.asciicam.util.Convert;
//Copyright 2012 Robin Braaf, Ossian Madisson, Marting Thörnesson, Fredrik Hansson and Jonas Åström.
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
		int averageValue = 0;
		
		while(true){
			int h = 0;
			int w = 0;
			int x = 1;
			int y = 1;
			
			for(h=10*(y-1); h<10*y; h++){
				for (w=5*(x-1); w<5*x; w++){	
					averageValue += Convert.averageRGB(bm.getPixel(w, h));
				}
			}
			
			//TODO: Call function which determines what ASCII-sign to use given the averageValue variable. Don't forget to divide with 50 due to 50 pixels.
			averageValue = 0;
			//If width is less than the next rectangle, start on a new row,
			if(w+5 > width && h+10 < height){
				x = 1;
				y++;
			}
			//If width allows one more rectangle, increase x.
			else if(w+5 < width){
				x++;
			}
			//If there's no room for a new row or further to the right, break.
			else{
				break;
			}

			
		}
	
	return null;
}
}
