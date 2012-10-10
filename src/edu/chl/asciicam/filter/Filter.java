package edu.chl.asciicam.filter;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public abstract class Filter implements FilterInterface {

	public abstract Bitmap convert(Bitmap bm);

	/**
	 * Converts a given array to a Bitmap and returns the result.
	 * @param byteArray The byte array to be converted into a Bitmap.
	 * @return The resulting Bitmap.
	 */
	public Bitmap arrayToBitmap(byte[] byteArray) 	{
		Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray , 0, byteArray.length);
		return bitmap;
	}

	/**
	 * Converts a given Bitmap to a byte array and returns the result.
	 * @param bitmap The Bitmap to be converted into a byte array.
	 * @return The resulting byte array.
	 */
	public byte[] bitmapToByteArray(Bitmap bitmap){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 0 /*ignored for PNG*/, out);
		byte[] byteArray = out.toByteArray();
		return byteArray;
	}

}
