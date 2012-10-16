package edu.chl.asciicam.util;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
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
import android.util.Base64;

public class Convert{

	/**
	 * Converts a given array to a Bitmap and returns the result.
	 * @param byteArray The byte array to be converted into a Bitmap.
	 * @return The resulting Bitmap.
	 */
	public static Bitmap byteArrayToBitmap(byte[] byteArray) 	{

		Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray , 0, byteArray.length);
		return bitmap;
	}

	/**
	 * Converts a given array to a Bitmap and returns the result as a compressed bitmap. Does not change the 
	 * byte array. This should be used for bitmaps to be displayed on screen.
	 * @param byteArray The byte array to be converted into a Bitmap.
	 * @return The resulting Bitmap.
	 */
	public static Bitmap compressPicture(byte[] byteArray) 	{
		//Compress picture, a power of 2 is fastest way for decoder.
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 8;

		Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray , 0, byteArray.length, options);
		return bitmap;

	}

	/**
	 * Converts a given Bitmap to a byte array and returns the result.
	 * @param bitmap The Bitmap to be converted into a byte array.
	 * @return The resulting byte array.
	 */
	public static byte[] bitmapToByteArray(Bitmap bitmap){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 0, out);
		byte[] byteArray = out.toByteArray();
		return byteArray;
	}

	/**
	 * Calculates the average value of the RGB in a pixel and returns the result.
	 * @param rgb Representation of the RGB-colors in a pixel.
	 * @return Returns an integer of the average value.
	 */
	public static float averageRGB(int rgb){
		int red = (rgb>>16)&0x0ff;
		int green=(rgb>>8) &0x0ff;
		int blue= (rgb) &0x0ff;

		float average = (red+blue+green)/3;
		return average;
	}

	/**Converts a bitmap to a string and returns the result.
	 * @param bitmap The bitmap to be converted.
	 * @return converting bitmap and return a string
	 */
	public static String BitMapToString(Bitmap bitmap){
		ByteArrayOutputStream baos=new  ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
		byte [] b=baos.toByteArray();
		String temp=Base64.encodeToString(b, Base64.DEFAULT);
		return temp;
	}

	/**Converts a Bitmap to a String and returns the result.
	 * @param encodedString The String to be converted.
	 * @return bitmap The resulting Bitmap after conversion.
	 */
	public static Bitmap StringToBitMap(String encodedString){
		try{
			byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
			Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
			return bitmap;
		}catch(Exception e){
			e.getMessage();
			return null;
		}
	}

}
