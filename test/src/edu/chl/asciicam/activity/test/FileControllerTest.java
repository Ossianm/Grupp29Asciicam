package edu.chl.asciicam.activity.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import edu.chl.asciicam.file.FileController;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.test.AndroidTestCase;

public class FileControllerTest extends AndroidTestCase {
	
	private static final String OPTIONS_FILENAME = "OptionsAscii";
	Context c;
	FileController fc;
	
	public FileControllerTest(){
		
	}

	@Override
	protected void setUp() throws Exception {
		
		super.setUp();
		c = getContext();
		fc = new FileController(c);
		

		
	}
	
	/**
	 * Tests if we can save a image to the 
	 * @throws IOException goodie
	 */
	public void testSavePic() throws IOException{

		InputStream strin = c.getResources().openRawResource(R.drawable.dsc_8219);
		byte[] data = new byte[strin.available()];
		strin.read(data);
		strin.close();
		int i = checkSeq();
		fc.savePic(data);
		//Make sure sequence is increased
		i++;
		assertEquals(i, checkSeq());
		File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File file = new File(path, "ASCIIPIC_" + (i-1) + ".jpg");
		//Make sure file is created
		assertTrue(file.exists());
		//Double check
		fc.savePic(data);
		i++;
		File file2 = new File(path, "ASCIIPIC_" + (i-1) + ".jpg");
		assertEquals(i, checkSeq());
		assertTrue(file2.exists());
	}
	
	//Returns the saved sequence
	private int checkSeq() throws IOException{
		SharedPreferences settings = c.getSharedPreferences(OPTIONS_FILENAME, Context.MODE_PRIVATE);
		return settings.getInt(FileController.SEQUENCENUMBER, 1);
	}
	
	public void testPrivateLoadAndSave(){
		// TODO
	}
}
