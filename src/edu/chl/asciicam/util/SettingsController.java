package edu.chl.asciicam.util;

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

import android.graphics.Color;

/**
 * A class to store the settings for the conversion
 * @author Ossian
 */
public class SettingsController {
	
	private String filter = "AsciiFilter"; //Setting AsciiFilter as default
	private int bgColor = Color.WHITE, textColor = Color.BLACK; //Setting the default colors
	private float brightnessValue = 0; //Setting 0 as default brightness
	/**
	 * Default constructor
	 */
	public SettingsController(){
		
	}
	
	// Take a index from the list in OptionScreen and turn it to a int that can be used as a Color.CONSTANT
	private int indexToColorConstant(int index){
		int colorC = Color.BLACK; //Setting default color to black
		
		switch(index){
		case 0:
			colorC = Color.BLACK;
			break;
		case 1:
			colorC = Color.WHITE;
			break;
		case 2:
			colorC = Color.GRAY;
			break;
		case 3: 
			colorC = Color.CYAN;
			break;
		case 4:
			colorC = Color.RED;
			break;
		case 5: 
			colorC = Color.BLUE;
			break;
		case 6:
			colorC = Color.GREEN;
			break;
		case 7:
			colorC = Color.MAGENTA;
			break;
		case 8:
			colorC = Color.YELLOW;
			break;		
		}
		return colorC;
	}
	
	//////////////////////////
	// Setters and getters  //
	//////////////////////////
	/**
	 * Sets the background color
	 * @param bgColor must be Color.constant
	 */
	public void setBgColor(int index){
		bgColor = indexToColorConstant(index);
	}
	
	/**
	 * Sets the color of the text
	 * @param bgColor must be Color.constant
	 */
	public void setTextColor(int index){
		textColor = indexToColorConstant(index);
	}
	
	/**
	 * Sets the filter that should be used
	 * @param filter
	 */
	public void setFilter(String filter){
		this.filter = filter;
	}
	
	/**
	 * Sets the brightness value that should be used
	 * @param brightness
	 */
	public void setBrigtness(float brightness){		
		this.brightnessValue = brightness;
	}
	
	/**
	 * Get the background color
	 * @return the chosen background color
	 */
	public int getBgColor(){
		return bgColor;
	}
	
	/**
	 * Get the color of the text
	 * @return the chosen text color
	 */
	public int getTextColor(){
		return textColor;
	}
	
	/**
	 * Get the filter 
	 * @return the chosen filter
	 */
	public String getFilter(){
		return filter;
	}
	
	/**
	 * Get the brightnessValue
	 * @return the current brightnessValue
	 */
	public float getBrightness(){
		return brightnessValue;
	}
	
	
	
	
	
}
