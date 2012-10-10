package edu.chl.asciicam.activity.test;

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
		File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "AsciiCAM");
		File file = new File(path.getPath() + File.separator + "ASCIIPIC_" + (i-1) + ".jpg");
		//Make sure file is created
		assertTrue(file.exists());
		//Double check
		fc.savePic(data);
		i++;
		File file2 = new File(path.getPath() + File.separator + "ASCIIPIC_" + (i-1) + ".jpg");
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
