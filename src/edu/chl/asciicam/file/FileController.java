/**
 * 
 */
package edu.chl.asciicam.file;

import java.util.*;
import java.io.*;


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
 * This class if or saving pictures for the application Asciicam. It also handles
 * saving and reading options from file.
 * 
 * @author Braaf
 *
 */
public class FileController {
	
	public FileController(){
		
	}
	
	/**
	 * Use this method to save a picture on the external storage (SD card).
	 * @param picArray A byte array of the pictures data.
	 */
	public void savePic(byte[] picArray){
		
	}
	
	/**
	 * Use this method to save options made by the user.
	 * @param options A hashmap consisting of the options to be saved.
	 */
	public void saveOptions(HashMap<String, String> options){
		
	}
	
	/**
	 * Use this method to load options on application start.
	 * @return A map of all options saved.
	 */
	public HashMap<String, String> getOptions(){
		return new HashMap<String, String>();
	}
}
