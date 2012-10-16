package edu.chl.asciicam.filter;

import android.graphics.Bitmap;
import edu.chl.asciicam.util.Convert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
	public String convert (Bitmap bm) {
		
		//Declare the array with ASCII-signs. The index is used to decide what sign to translate the 5x10 rectangle to.
		String[] signs = {" ", ",", "-", "*", "/", "r", "m", "+", "(", "¿", "x", "w", "K", "O", "&", "9", "#", "W", "$", "@" };
		String picture = "";
	

		File file = new File("ASCII-result.txt");
		File write = new File("ASCII-table.txt");
		GrayScaleFilter gsf = new GrayScaleFilter();
		Bitmap bmAscii = Bitmap.createBitmap(gsf.convert(bm)); //Returns a Bitmap with the same dimension as bm, but in gray scale.
		//TreeMap<Float, String> darknessTable = createValues(file);
		

		int height = bmAscii.getHeight();
		int width = bmAscii.getWidth();
		int averageValue = 0;

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
//			try{
//				BufferedWriter out = new BufferedWriter(new FileWriter(write));
//				out.write(signs[averageValue]);
//				out.close();
//			}
//			catch(Exception e){
//				System.out.println("Error in deciding ASCII-sign"+e.getMessage());
//			}
//			//TODO: Call function which determines what ASCII-sign to use given the averageValue variable. Don't forget to divide with 50 due to 50 pixels.
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

		return StringToBitMap(picture);
	}

}
