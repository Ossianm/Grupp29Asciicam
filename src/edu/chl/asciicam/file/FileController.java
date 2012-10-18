package edu.chl.asciicam.file;

import java.util.*;
import java.io.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
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

/**
 * This class if or saving pictures for the application Asciicam. It also handles
 * saving and reading options from file.
 * 
 * @author Braaf
 *
 */
public class FileController {
	//String for Exception case if SD card is unmounted when trying to save a picture
	public static final String UNMOUNTED_SD = "SDCARD_NOT_MOUNTED";
	//Local filenames for private files
	private static final String OPTIONS_FILENAME = "OptionsAscii";
	private static final String PRIV_PIC = "PrivatePictureAscii";

	//Static names for sharedpreferense data
	public static final String SEQUENCENUMBER = "sequence";

	//Sequence number, used for naming files.
	private static int SEQ_NUMBER = 1;

	//Context is needed to get important information about filesystem.
	private Context context = null;

	/**
	 * In order to get access to local storage on phone we need a context.
	 * This constructor does try to init the sequence number for naming pictures automagically.
	 * @param context 
	 * @throws IOException 
	 */
	public FileController(Context context){
		this.context = context;
		setSequence();
	}

	/**
	 * Use this method to save a picture on the external storage (SD card).
	 * @param picArray A byte array of the pictures data.
	 * @throws IOException If an error occurs while writing or if the SD card is not avaible.
	 * Check message for FileController.UNMOUNTED_SD if its because of unavaible SD card.
	 */
	public void savePic(byte[] picArray) throws IOException{
		if(checkSD()){
			//Set up file with path to SD card and file name
			File path = new File(Environment.getExternalStorageDirectory(), "DCIM" + File.separator + "AsciiCAM");
			//Assure us directory exist
			path.mkdirs();
			File file = new File(path.getPath() + File.separator + "ASCIIPIC_" + getSequenceNumber() + ".jpg");
			//Make sure file doesnt already exist and loop until we get a valid sequencenumber
			while(file.exists() == true){
				//If file already exists we need to change sequencenumber until we get 
				//a new file so we dont overwrite anything!
				sequenceIncrement();
				file = new File(path.getPath() + File.separator + "ASCIIPIC_" + getSequenceNumber() + ".jpg");
			}
			//Save picture
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			bos.write(picArray);
			bos.close();

			//Handle the sequence number
			sequenceIncrement();
			saveSequence();
			//invoke media scanner
			context.sendBroadcast( new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.fromFile(file))
			);

		}else{
			throw new IOException(UNMOUNTED_SD);
		}
	}

	//Check if SD-card is avaible.
	private boolean checkSD(){
		//Get current state of SD card from Environment
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// We can read and write the media
			return true;
		}
		return false;
	}

	//Sequence for file storing
	private static int getSequenceNumber(){
		return SEQ_NUMBER;
	}

	//Increment sequence for file storing
	private static void sequenceIncrement(){
		SEQ_NUMBER++;
	}

	//Save sequencenumber locally
	private void saveSequence(){

		SharedPreferences settings = context.getSharedPreferences(OPTIONS_FILENAME, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putInt(SEQUENCENUMBER, SEQ_NUMBER);
		editor.commit();

	}

	/**
	 * Reloads sequence from saved file for naming saved pictures.
	 */
	public void setSequence(){
		SharedPreferences settings = context.getSharedPreferences(OPTIONS_FILENAME, Context.MODE_PRIVATE);
		SEQ_NUMBER = settings.getInt(SEQUENCENUMBER, 1);
	}

	/**
	 * Saves an picture locally to be used within application.
	 * @param pic Picture to be saved locally for use within application
	 * @throws IOException If an error occurs while saving.
	 */
	public void savePicPrivate(byte[] pic) throws IOException{

		//Save the sequence to a local file here
		FileOutputStream fos = context.openFileOutput(PRIV_PIC, Context.MODE_PRIVATE);
		BufferedOutputStream buf = new BufferedOutputStream(fos);
		buf.write(pic);
		buf.flush();
		buf.close();
	}

	/**
	 * Use this to load locally saved picture.
	 * @return A picture in byte array format.
	 * @throws IOException Whenever an error occurs while reading data.
	 */
	public byte[] loadPicPrivate() throws IOException{
		
		FileInputStream fis = context.openFileInput(PRIV_PIC);
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] data = new byte[fis.available()];
		byte[] returnArray = null;
		while(bis.read(data) != -1){
			returnArray = data;
		}
		bis.close();
		data = null;

		return returnArray;
	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////OPTIONS//SAVE//AND//GET///////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	/**
	 * Use this method to save options made by the user.
	 * @param options A hashmap consisting of the options to be saved <String Key, String Value>.
	 * @throws IOException If something goes wrong wile saving options.
	 */
	public void saveOptions(HashMap<String, String> options) throws IOException{
		Set<String> keySet = options.keySet();
		
		for(String s : keySet){
			FileOutputStream fos = context.openFileOutput(s, Context.MODE_PRIVATE);
			fos.write(Base64.decode(options.get(s), Base64.DEFAULT));
			fos.flush();
			fos.close();
		}
		
	}
	
	/**
	 * To save a single value locally on sd card.
	 * @param key key for the option to be saved.
	 * @param Value value to be saved
	 * @throws IOException If something goes wrong wile saving option.
	 */
	public void saveOption(String key, String value) throws IOException{
		FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
		fos.write(Base64.decode(value, Base64.DEFAULT));
		fos.flush();
		fos.close();
	}

	/**
	 * Use this method to load options on application start.
	 * @param values A list of all values to be read.
	 * @return A map of all options saved.
	 * @throws IOException If something goes wrong wile reading options.
	 */
	public HashMap<String, String> getOptions(List<String> values) throws IOException{

		//Create iteration over all values to be read here
		HashMap<String, String> optionMap = new HashMap<String, String>();
		for(String s : values){
			//read value here
			FileInputStream fis = context.openFileInput(s);
			byte[] data = new byte[fis.available()];
			byte[] valueArray = null;
			while(fis.read(data) != -1){
				valueArray = data;
			}
			fis.close();
			optionMap.put(s, Base64.encodeToString(valueArray, Base64.DEFAULT));
			data = null;
		}
		return optionMap;
	}
	
	/**
	 * Returns the value saved at key.
	 * @param key key to find your value.
	 * @return Value saved in app storage.
	 * @throws IOException If something goes wrong wile reading option.
	 */
	public String getOption(String key) throws IOException{
			//read value here
			FileInputStream fis = context.openFileInput(key);
			byte[] data = new byte[fis.available()];
			byte[] valueArray = null;
			while(fis.read(data) != -1){
				valueArray = data;
			}
			fis.close();
			String option = Base64.encodeToString(valueArray, Base64.DEFAULT);
			data = null;
			return option;
	}

}
