package edu.chl.asciicam.activity.test;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.test.AndroidTestCase;
import edu.chl.asciicam.util.Convert;

public class ConvertTest extends AndroidTestCase {
	
	Context c;
	
	public void testConversions() throws IOException{
		InputStream strin = c.getResources().openRawResource(R.drawable.dsc_8219);
		byte[] data = new byte[strin.available()];
		strin.read(data);
		strin.close();
		
		Bitmap bm = Bitmap.createBitmap(Convert.byteArrayToBitmap(data));
		byte[] modified = Convert.bitmapToByteArray(bm);
		
		assertEquals(data, modified);
		
	}
}
