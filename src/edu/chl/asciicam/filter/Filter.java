package edu.chl.asciicam.filter;

import android.graphics.Bitmap;

public abstract class Filter implements FilterInterface {

	public abstract Bitmap convert(Bitmap bm);

	public Bitmap arrayToBitmap(byte[] byteArray) {
		// TODO Auto-generated method stub
		return null;
	}

}
