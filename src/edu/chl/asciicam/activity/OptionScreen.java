package edu.chl.asciicam.activity;

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

import java.util.Arrays;
import java.util.List;

import edu.chl.asciicam.util.SettingsController;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class OptionScreen extends Activity {

	private Button back_btn;
	private ImageView brightness_icon;
	private SeekBar brightnessBar;
	private Spinner filterSpinner, bgSpinner, charSpinner;
	private List<String> filterList, colorList;
	private String[] colorStrings, filterStrings;
	private CharSequence promptArray[];
	private ArrayAdapter<String> filterAdapter, colorAdapter;
	private static final int FILTER_DEFAULT = 0, BG_DEFAULT = 1, CHAR_DEFAULT = 0;
	private float brightness;
	
	protected static SettingsController settings = new SettingsController();

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);
        
        //Layouts
        
        //Views
        brightness_icon = (ImageView) findViewById(R.id.brightness_icon);
        
        //Widgets
		back_btn = (Button) findViewById(R.id.back);
		
		filterSpinner = (Spinner) findViewById(R.id.filter_spinner);
		bgSpinner = (Spinner) findViewById(R.id.background_spinner);
		charSpinner = (Spinner) findViewById(R.id.character_spinner);
		
		//Initiating objects in OptionScreen
		initiateButtons();
		initiateImageViews();
		initiateSpinners();
		initiateBrightnessBar();
    }

	
//	This is called by to initiate the buttons and add functionality
	private void initiateButtons() {
		back_btn.setOnClickListener(new View.OnClickListener() {

			/* Using the finish() to go back to the last activity */
			public void onClick(View v) {
				finish();
			}
		});
			
	}	
	
	//This is called by to initiate the ImageViews
	private void initiateImageViews(){
		brightness_icon.setBackgroundResource(R.drawable.brightness_temp);
	}
	
	private void initiateBrightnessBar(){

		brightnessBar = (SeekBar)findViewById(R.id.brightness_bar);
		brightnessBar.setMax(200);
		brightnessBar.setProgress(100);
		brightnessBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}		         
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}		         
			public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
				
				brightness = progress-100;
				settings.setBrigtness(brightness);
				
			}
		});



	}
	
	private void initiateSpinners(){
		
		//Setting up an array for Spinner Prompts
		promptArray = new String[3];
		promptArray[0] = "Choose Filter";
		promptArray[1] = "Choose Background Color";
		promptArray[2] = "Choose Character Color";
		
		filterSpinner.setPrompt(promptArray[0]);
		bgSpinner.setPrompt(promptArray[1]);
		charSpinner.setPrompt(promptArray[2]);
		
		//Creating lists of Spinner Entries
		filterStrings = new String[] {"AsciiFilter","GrayScale", "BrightnessFilter"};
		colorStrings = new String[] {"WHITE","BLACK","GRAY","CYAN","RED","BLUE","GREEN","MAGENTA","YELLOW"};
		filterList = Arrays.asList(filterStrings);
		colorList = Arrays.asList(colorStrings);
		
		//Creating layout for spinner entries
		filterAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, filterList);
		filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		colorAdapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_spinner_item, colorList);
		colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);	
			
		//Setting up Entries
		filterSpinner.setAdapter(filterAdapter);
		filterSpinner.setSelection(FILTER_DEFAULT);
		bgSpinner.setAdapter(colorAdapter);
		charSpinner.setAdapter(colorAdapter);
		
		//Defining adding value to default entry for each spinner
		bgSpinner.setSelection(BG_DEFAULT);
		charSpinner.setSelection(CHAR_DEFAULT);
	}

	public static SettingsController getSettings(){
		return settings;
	}
}