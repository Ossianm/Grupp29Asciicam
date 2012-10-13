package edu.chl.asciicam.activity;

import java.io.IOException;

import edu.chl.asciicam.file.FileController;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
 * This activity sets the taken picture as the background. You can choose what
 * to with you taken picture, save, convert or delete picture and take a new
 * one.
 * 
 * @author Ossian, Jonas, Martin
 * 
 */
public class PreviewScreen extends Activity {

	Button back_btn, save_pic_btn, convert_btn;
	Bitmap bmp;
	Bundle extras;
	ImageView iv;
	public static String DIRECTORY_PICTURES;
	BroadcastReceiver mExternalStorageReceiver;
	public FileController fc;
	byte[] picDataArray = null;
	AlertDialog dialog;
	/**
	 * This is called automatically by the android system when the activity is
	 * started.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview_screen);
		back_btn = (Button) findViewById(R.id.back);
		save_pic_btn = (Button) findViewById(R.id.save);
		convert_btn = (Button) findViewById(R.id.convert);
		iv = (ImageView) findViewById(R.id.preview_pic);
		fc = new FileController(getBaseContext());				
		initiateButtons();

		extras = this.getIntent().getExtras(); //get all the extras from intent to the Bundle extras
		String id = extras.getString("id"); //loads the id to check if the background should be loaded from the taken picture or from gallery

		if (id.equals("taken")) {
			loadFromCamera();
		} else if (id.equals("loaded")) {
			loadFromPhone();
		}
	}

	/**
	 * Called automatically by android 
	 */
	@Override
	public void onPause(){
		super.onPause();
	}

	/**
	 * Called automatically by android 
	 */
	@Override
	public void onResume(){
		super.onResume();
	}

	/**
	 * Loading the background from the taken picture
	 */
	public void loadFromCamera() {
		try {
			picDataArray = fc.loadPicPrivate(); // loading the data from the private pic saved from camerascreen
		} catch (IOException e) {
			e.printStackTrace();
		}
		//decode the bytearray to a Bitmap and set as background
		bmp = (Bitmap) BitmapFactory.decodeByteArray(picDataArray, 0, picDataArray.length); 
		setBackground(bmp);
	}

	/**
	 * Will rotate the Bitmap so it's shown as portrait
	 * @param bg
	 * @return a rotated Bitmap
	 */
	private Bitmap rotatePic(Bitmap bg){ 
		//this had to be implemented because the picture was shown turned 90 degrees when set as background, this is to prevent that
		Matrix matrix = new Matrix();
		matrix.setRotate(90, bg.getWidth()/2, bg.getHeight()/2);
		return Bitmap.createBitmap(bg, 0, 0, bg.getWidth(), bg.getHeight(), matrix, true);
	}

	/**
	 * Loading the background from gallery
	 */
	public void loadFromPhone() {
		String filePath = extras.getString("filePath");
		if (filePath != null) {			
			//decode the file to a Bitmap and set as background
			bmp = (Bitmap) BitmapFactory.decodeFile(filePath); 
			setBackground(bmp);
		}
	}

	/**
	 * This method takes a Bitmap and sets it as background
	 * 
	 * @param bg
	 */
	private void setBackground(Bitmap bg) {
		if (bg != null) {
			bg = rotatePic(bg);
			iv.setImageBitmap(bg);

			setBgBmp(bg); // Called for testing purposes
		}
	}

	/**
	 * Opens a dialog to the user that says "The picture is saved!"
	 */
	private void openDialog(){
		dialog = new AlertDialog.Builder(PreviewScreen.this).create();
		dialog.setMessage("The picture is saved!");
		dialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		dialog.show();
	}
	/**
	 * This is called by to initiate the buttons and add functionality
	 */
	private void initiateButtons() {
		back_btn.setOnClickListener(new View.OnClickListener() {

			/* Using the finish() to go back to the last activity */
			public void onClick(View v) {
				finish();
			}
		});

		save_pic_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					fc.savePic(picDataArray); //saves the picture on the phone

					System.out.println("Bilden har sparats i" + 
							Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));	
				} catch (IOException e) {
					e.printStackTrace();
				}				
				openDialog(); //opens a dialog to tell the user that the picture is saved
				save_pic_btn.setEnabled(false); //set the button unclickable to prevent filling up the memory with copies of the same piture
			}
		});

		convert_btn.setOnClickListener(new View.OnClickListener() {
			// if click here, the picture will be converted with the current settings; not yet implemented
			public void onClick(View v) {
				Intent convertPicture = new Intent(getBaseContext(), ConvertedPicScreen.class);
				startActivity(convertPicture);
			}
		}); 
	}

	///////////////////////////////////////////////////////////////
	//////Setters//&//Getters//////////////////////////////////////
	///////////////////////////////////////////////////////////////

	/**
	 * Should probably only be used for testing
	 * @param bg
	 */
	private void setBgBmp(Bitmap bg){
		bmp = bg;
	}

	/**
	 * Should probably only be used for testing
	 * @return the current background as bitmap
	 */
	public Bitmap getBgBmp(){
		return bmp;
	}

}
