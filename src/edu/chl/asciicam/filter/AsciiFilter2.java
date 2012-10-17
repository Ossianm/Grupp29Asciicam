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
	
	private int compression;
	private String[] symbol = {" ", ",", "-", "*", "/", "r", "m", "+", "(", "¿", "x", "w", "K", "O", "&", "9", "#", "W", "$", "@" };
	/**
	 * Default constructor, sets default options.
	 */
	public AsciiFilter2(){
		compression = 10;
	}
	
	public Bitmap convert(Bitmap bm) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private ArrayList<String> filter(Bitmap bmp){
    	
    	ArrayList<String> list = new ArrayList<String>();
    	
    	int heightAdjust = bmp.getHeight() % compression;
    	int widthAdjust = bmp.getWidth() % compression;
    	int height = bmp.getHeight() - heightAdjust;
    	int width = bmp.getWidth() - widthAdjust;
    	
    	//int strCounter = 0;
    	//Loop through the picture, i being rows of pixels and
    	//j being column at a row. Skip a number of rows set in
    	//compression
    	for(int i = 0; i < height; i+=compression){
    		String s = new String();
    		for(int j =0; j < width; j+=compression){
    			//Make sure we can use it as index
    			int color = bmp.getPixel(j, i) % symbol.length;
    			if(color < 0)
    				color = color * (-1);
    			s += symbol[color]; //something from ascii
    		}
    		list.add(s);
    		//strCounter++;
    	}
    	
    	return list;
    }

}	