package edu.chl.asciicam.filter;

import android.graphics.Bitmap;
import edu.chl.asciicam.util.Convert;

import java.util.Map;
import java.util.TreeMap;

/**
 * A filter class that converts a picture to ASCII characters.
 * @author fredha
 * 
 */

public class AsciiFilter implements FilterInterface{
	
	private Map<Float, Character> darknessTable = new TreeMap<Float, Character>();
	
	
	/**
	 * Converts a bitmap into ASCII characters and returns the result.
	 * @param bm The Bitmap to be converted.
	 * 
	 */
	
	
	public Bitmap convert (Bitmap bm) {

		GrayScaleFilter gsf = new GrayScaleFilter();
		Bitmap bmAscii = Bitmap.createBitmap(gsf.convert(bm)); //Returns a Bitmap with the same dimension as bm, but in gray scale.

		
		int height = bmAscii.getHeight();
		int width = bmAscii.getWidth();
		Float averageValue = new Float(0);

		int h = 0;
		int w = 0;
		int x = 1;
		int y = 1;

		while(true){

			for(h=10*(y-1); h<10*y; h++){
				for (w=5*(x-1); w<5*x; w++){	
					averageValue += Convert.averageRGB(bm.getPixel(w, h));
				}
			}

			//TODO: Call function which determines what ASCII-sign to use given the averageValue variable. Don't forget to divide with 50 due to 50 pixels.
			averageValue = (float) 0; //Reset the averageValue for the next 5 by 10 rectangle.

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
