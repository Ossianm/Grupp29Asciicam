package edu.chl.asciicam.util;

//Copyright 2012 Robin Braaf, Ossian Madisson, Martin Th�rnesson, Fredrik Hansson and Jonas �str�m.
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
 * This is mainly used as a supportclass for Optionscreen and middlehand between Optionscreen and ConvertedPicScreen
 * @author Ossian
 */
public class SettingsController {
	
	private String filter = "AsciiFilter"; //Setting AsciiFilter as default
	private int bgColor = Color.WHITE, textColor = Color.BLACK; //Setting the default colors
	private int bgPos = 1, textPos = 0, filterPos = 0; //Variables to return the position of the current color for bg and text and filter
	private int compression = 6; //The default compression is set to 6, this is the number of pixels to check before checking again during asciiconversion
	private float brightnessValue = 0; //Setting 0 as default brightness
	
	/**
	 * Default constructor
	 */
	public SettingsController(){
		
	}
	/////////////////////////////////////////////////////////
	// Take a index from the list in OptionScreen and turn //
	// it to a int that can be used as a Color.CONSTANT    //
	/////////////////////////////////////////////////////////
	private int indexToColorConstant(int index){
		int colorC = Color.BLACK; //Setting default color to black
		
		//These cases is taken from the list in OptionScreen
		//String[] {"BLACK","WHITE","GRAY","CYAN","RED","BLUE","GREEN","MAGENTA","YELLOW"};
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
	
	////////////////////////////////////////////////
	// Take a index from the list in OptionScreen //
	// and turn it to a string for a filter       //
	////////////////////////////////////////////////
	private void indexToFilter(int index){
		switch(index){
		
		//These cases is taken from a list in OptionScreen
		// String[] {"AsciiFilter","GrayscaleFilter"};
		case 0:
			filter = "AsciiFilter";
			break;
		case 1:
			filter = "GrayscaleFilter";
		}			
	}
	
	/**
	 * Reset the settings to default
	 */
	public void resetToDefault(){
		bgColor = Color.WHITE;
		textColor = Color.BLACK;
		bgPos = 1;
		textPos = 0;
		filterPos = 0; 
		brightnessValue = 0;
		compression = 6;
	}
	
	//////////////////////////
	// Setters and getters  //
	//////////////////////////
	
	/**
	 * Sets the background color
	 * @param bgColor must be Color.constant
	 */
	public void setBgColor(int index){
		bgColor = indexToColorConstant(index); //Set the color for the bg
		bgPos = index; //Set the position for the color
	}
	
	/**
	 * Sets the color of the text
	 * @param bgColor must be Color.constant
	 */
	public void setTextColor(int index){
		textColor = indexToColorConstant(index); //Set the color for the text
		textPos = index; //Set the position for the text
	}
	
	/**
	 * Sets the filter that should be used
	 * @param filter
	 */
	public void setFilter(int index){
		indexToFilter(index); //Set the filter that should be used
		filterPos = index; //Set the position for the filter
	}
	
	/**
	 * Sets the brightness value that should be used
	 * @param brightness
	 */
	public void setBrightness(float brightness){		
		this.brightnessValue = brightness;
	}

	
	/**
	 * Sets the compress value that should be used
	 * @param compression
	 */
	public void setCompression(int compression){
		this.compression = compression;
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
	
	/**
	 * Get the position for the current background color
	 * @return the position of the current background color
	 */
	public int getBgPos(){
		return bgPos;
	}
	/**
	 * Get the position of the current text color
	 * @return the position of the current color of the text	
	 */
	public int getTextPos(){
		return textPos;
	}
	
	/**
	 * Get the position of the current active filter
	 * @return the position of the current filter
	 */
	public int getFilterPos(){
		return filterPos;
	}
	
	/**
	 * Get the current compressionsetting
	 * @return the current compressionvalue
	 */
	public int getCompression(){
		return compression;
	}
	
}
