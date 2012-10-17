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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.chl.asciicam.util.Convert;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Style;

/**
 * A filter which creates a bitmap consisting of ascii symbols from
 * a input bitmap. 
 * @author Braaf
 *
 */
public class AsciiFilter2 implements FilterInterface {
	//How many pixels to skip before checking again
	private int compression;
	//Fontsize on the output picture, will change size on the picture.
	private int fontSize;
	//Color for background and text
	private int bgColor;
	private int textColor;
	
	String[] symbol = {"@", "@", "@", "#", "#", "&", "&", "w", "w", "¿", "¿", "+", "+", "m", "m", "*", "*", ".", ".", " ", " "};
	
	/**
	 * Default constructor, sets default options.
	 */
	public AsciiFilter2(){
		compression = 6;
		fontSize = 20;
		bgColor = Color.WHITE;
		textColor = Color.BLACK;
	}
	
	/**
	 * This method takes a 
	 * @param bm Bitmap to be converted
	 */
	public Bitmap convert(Bitmap bm) {
		List<String> list = filter(bm);
		return createBitmap(list);
	}
	
	
	
	private List<String> filter(Bitmap bmp){
    	
		//List to populate with rows of strings
    	List<String> list = new ArrayList<String>();
    	
    	int heightAdjust = bmp.getHeight() % compression;
    	int widthAdjust = bmp.getWidth() % compression;
    	int height = bmp.getHeight() - heightAdjust;
    	int width = bmp.getWidth() - widthAdjust;
    	
    	//Loop through the picture, i being rows of pixels and
    	//j being column at a row. Skip a number of rows set in
    	//compression
    	for(int i = 0; i < height; i+=compression){
    		String s = "";
    		for(int j =0; j < width; j+=compression){
    			//get average RGB and add a symbol depending
    			//average value should always be between 0 and 255!
    			float color = Convert.averageRGB(bmp.getPixel(j, i)) * symbol.length;
    			color = color / 255;
    			
    			s += symbol[(int)color]; //something from ascii
    		}
    		list.add(s);
    	}
    	return list;
    }
	
	//
    //This method creates the output bitmap from a List<String>.
    //
    private Bitmap createBitmap(List<String> pic){
    	// Bitmap width = length
    	Paint paint = new Paint();
    	Bitmap bitmap = Bitmap.createBitmap(pic.get(0).length() * fontSize, pic.size() * fontSize, Bitmap.Config.RGB_565);
    	Canvas canvas = new Canvas(bitmap);
    	
    	
    	//Fill background with black
    	paint.setColor(bgColor);
    	paint.setStyle(Style.FILL);
    	canvas.drawPaint(paint);
    	
    	//Set color and stuff for drawing text
    	paint.setColor(textColor); 
    	paint.setTextSize(fontSize);
    	//Monospace is needed for correct indentation
    	paint.setTypeface(Typeface.MONOSPACE);
    	//Scale or or will be shrimped in width, 1.7 seems to be the magical number. (1 is default)
    	paint.setTextScaleX((float)1.7);
    	//paint.setTextAlign(Paint.Align.CENTER);
    	
    	float x = 0, y = fontSize;
    	
    	for(String s : pic){
    		char[] string = s.toCharArray();
    		canvas.drawText(string, 0, string.length, x, y, paint);
    		y += fontSize;
    	}
    	return bitmap;
    }
    
    // TODO create options for filter.
	

}	