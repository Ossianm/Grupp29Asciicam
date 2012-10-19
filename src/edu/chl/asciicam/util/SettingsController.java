package edu.chl.asciicam.util;

import android.graphics.Color;

/**
 * A class to store the settings for the conversion
 * @author Ossian
 */
public class SettingsController {
	
	private String filter = "AsciiFilter"; //Setting AsciiFilter as default
	private int bgcolor, textcolor;
	
	/**
	 * Default constructor
	 */
	public SettingsController(){
		
	}
	
	// Take a string and turn it to a int that can be used as a Color.CONSTANT
	private int stringToColorConstant(String color){
		int colorint = Color.BLACK; //Setting default color to black
		switch(color){
		case "BLACK":
			colorint = Color.BLACK;
			break;
		case "WHITE":
			colorint = Color.WHITE;
			break;
		case "GRAY":
			colorint = Color.GRAY;
			break;
		case "CYAN": 
			colorint = Color.CYAN;
			break;
		case "RED":
			colorint = Color.RED;
			break;
		case "BLUE": 
			colorint = Color.BLUE;
			break;
		case "GREEN":
			colorint = Color.GREEN;
			break;
		case "MAGENTA":
			colorint = Color.MAGENTA;
			break;
		case "YELLOW":
			colorint = Color.YELLOW;
			break;		
		}
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
