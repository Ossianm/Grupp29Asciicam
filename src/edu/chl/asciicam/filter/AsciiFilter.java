package edu.chl.asciicam.filter;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Style;
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
	
	//fontSize on the asciioutput. If changed it will change the size of the picture.
	private static final int fontSize = 20;
	
	/**
	 * Takes a file object and reads one line at a time. The method separates the line at each occurance of "," and puts the two strings in a TreeMap as V,K.
	 * @param input The file object to be read from.
	 * @return The resulting TreeMap.
	 */

	/**
	 * This function is currently not used and it's unsure if it will be again.
	private TreeMap<Float, String> createValues(File input){
		TreeMap<Float, String> map = new TreeMap<Float, String>();
		String[] s = new String[1];
		try{
			Scanner sc = new Scanner(input);
			s = sc.nextLine().split(",");
			map.put(new Float(s[1]), s[0]);
			while(sc.hasNextLine()){
				s = sc.nextLine().split(",");
				map.put(new Float(s[1]), s[0]);
			}

		}
		catch(FileNotFoundException e){
			System.out.println("Could not read file." + e);
		}
		return map;
	}
*/

	/**
	 * Converts a bitmap into ASCII characters and returns the result.
	 * @param bm The Bitmap to be converted.
	 * @return The resulting Bitmap (ASCII).
	 */
	public Bitmap convert (Bitmap bm) {

		//Declare the array with ASCII-signs. The index is used to decide what sign to translate the 5x10 rectangle to.
		String[] signs = {" ", ",", "-", "*", "/", "r", "m", "+", "(", "¿", "x", "w", "K", "O", "&", "9", "#", "W", "$", "@" };
		String picture = "";

		int height = bm.getHeight();
		int width = bm.getWidth();
		int averageValue = 0;
		
		//Set starting values for integers used in the for-loops iterating over 5x10 pixels.
		int h = 0;
		int w = 0;
		int x = 1;
		int y = 1;

		while(true){
			//Iterate over 10 rows.
			for(h=10*(y-1); h<10*y; h++){
				//For each row, iterate over 5 in width.
				for (w=5*(x-1); w<5*x; w++){	
					//Add the average value of each pixel into the variable in order to calculate appropriate ASCII-sign.
					averageValue += Convert.averageRGB(bm.getPixel(w, h));
				}
			}

			averageValue = ((averageValue/50)/255)*20; //Calculates what index to use as the ASCII sign.
			picture+=signs[averageValue];
			averageValue = 0; //Reset the averageValue for the next 5 by 10 rectangle.

			//If width is less than the next rectangle, start on a new row,
			if(w+5 > width && h+10 < height){
				x = 1;
				y++;
				picture+="\n";
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

		return Convert.StringToBitMap(picture);
	}
	
    ///////////////////////////////////////////////////////////////////////
    //This method creates the output bitmap from a List<String>.///////////
    //Each string in the list will represent a row on the outputpicture.///
	///////////////////////////////////////////////////////////////////////
    private Bitmap createBitmap(List<String> pic){
    	// Bitmap width = length
    	Paint paint = new Paint();
    	Bitmap bitmap = Bitmap.createBitmap(pic.get(0).length() * fontSize, pic.size() * fontSize, Bitmap.Config.RGB_565);
    	//Creata canvas and put in bitmap as param so it will draw on it.
    	Canvas canvas = new Canvas(bitmap);
    	
    	
    	//Fill background with black
    	paint.setColor(Color.BLACK);
    	paint.setStyle(Style.FILL);
    	canvas.drawPaint(paint);
    	
    	//Set color and stuff for drawing text
    	paint.setColor(Color.WHITE); 
    	paint.setTextSize(fontSize);
    	//Monospace is needed for correct indentation
    	paint.setTypeface(Typeface.MONOSPACE);
    	//Scale or or will be shrimped in width, 1.8 seems to be the magical number. (1 is default)
    	paint.setTextScaleX((float)1.7);
    	//paint.setTextAlign(Paint.Align.CENTER);
    	
    	float x = 0, y = 0;
    	//For each String we want to make a road. x is never changed since we always want
    	//each row to start as far left as possible.
    	for(String s : pic){
    		char[] string = s.toCharArray();
    		canvas.drawText(string, 0, string.length, x, y, paint);
    		y += fontSize;
    	}
    	return bitmap;
    }
}
