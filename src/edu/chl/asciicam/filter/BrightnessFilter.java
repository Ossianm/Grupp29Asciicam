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
import android.graphics.Color;

/**
 * A filterclass that change the brightness of a given bitmap
 * The brightness can be set to -100 (very dark) up to 100 (very light)
 * @author Ossian
 */
public class BrightnessFilter implements FilterInterface{

	private float brightnessValue = 0; // Default brightness change is 0

	public BrightnessFilter(){
	}

	/**
	 * change the brightness of the given bitmap 
	 * @param bm the bitmap that should be changed
	 */
	public Bitmap convert(Bitmap bm) {
		Bitmap bmp;
		bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), bm.getConfig());

		int A, R, G, B, maxValue = 255, minValue = 0;
		int pixel;

		//Go through every pixel in the picture and get it's rgbvalues
		for(int x = 0; x < bmp.getWidth(); ++x) {
			for(int y = 0; y < bmp.getHeight(); ++y) {

				pixel = bm.getPixel(x, y);
				A = Color.alpha(pixel);
				R = Color.red(pixel);
				G = Color.green(pixel);
				B = Color.blue(pixel);

				//Increase or decrease each color to adjust the brightness of the pixels
				R += brightnessValue;
				G += brightnessValue;
				B += brightnessValue;
				
				if(R > maxValue){
					R = maxValue; 
				}else if(R < minValue){
					R = minValue; }

				if(G > maxValue){
					G = maxValue; 
				}else if(G < minValue){
					G = minValue; }

				if(B > maxValue){
					B = maxValue; 
				}else if(B < minValue){
					B = minValue; }

				//Set the recolored pixel in the same position on the new bitmap
				bmp.setPixel(x, y, Color.argb(A, R, G, B));
			}		
		}
		return bmp;
	}

	/**
	 * Set the brightness setting, between -100 and 100
	 * @param brightness
	 */
	public void setBrightness(float brightness){
		this.brightnessValue = brightness;
	}

	/**
	 * Get the current brightness setting, default = 0;
	 * @return the current brightness setting
	 */
	public float getBrightness(){
		return brightnessValue;
	}

}

