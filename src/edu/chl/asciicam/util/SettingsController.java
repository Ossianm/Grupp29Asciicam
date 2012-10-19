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
	private int bgcolor = Color.WHITE, textcolor = Color.BLACK; //Setting the default colors
	
	/**
	 * Default constructor
	 */
	public SettingsController(){
		
	}
	
	// Take a string and turn it to a int that can be used as a Color.CONSTANT
	private int stringToColorConstant(String color){
		int colorint = Color.BLACK; //Setting default color to black
//		switch(color){
//		case "BLACK":
//			colorint = Color.BLACK;
//			break;
//		case "WHITE":
//			colorint = Color.WHITE;
//			break;
//		case "GRAY":
//			colorint = Color.GRAY;
//			break;
//		case "CYAN": 
//			colorint = Color.CYAN;
//			break;
//		case "RED":
//			colorint = Color.RED;
//			break;
//		case "BLUE": 
//			colorint = Color.BLUE;
//			break;
//		case "GREEN":
//			colorint = Color.GREEN;
//			break;
//		case "MAGENTA":
//			colorint = Color.MAGENTA;
//			break;
//		case "YELLOW":
//			colorint = Color.YELLOW;
//			break;		
//		}
		return colorint;
	}
	
	//////////////////////////
	// Setters and getters  //
	//////////////////////////
	/**
	 * Sets the background color
	 * @param bgcolor must be Color.constant
	 */
	public void setBgColor(String color){
		bgcolor = stringToColorConstant(color);
	}
	
	/**
	 * Sets the color of the text
	 * @param bgcolor must be Color.constant
	 */
	public void setTextColor(String color){
		textcolor = stringToColorConstant(color);
	}
	
	/**
	 * Sets the filter that should be used
	 * @param filter
	 */
	public void setFilter(String filter){
		this.filter = filter;
	}
	
	/**
	 * Get the background color
	 * @return the chosen background color
	 */
	public int getBgColor(){
		return bgcolor;
	}
	
	/**
	 * Get the color of the text
	 * @return the chosen text color
	 */
	public int getTextColor(){
		return textcolor;
	}
	
	/**
	 * Get the filter 
	 * @return the chosen filter
	 */
	public String getFilter(){
		return filter;
	}
	
	
	
}
