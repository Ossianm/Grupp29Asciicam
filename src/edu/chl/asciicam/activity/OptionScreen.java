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
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * 
 * @author Martin
 *
 */
public class OptionScreen extends Activity {

	private Button back_btn;
	private ImageView brightness_icon;
	private SeekBar brightnessBar;
	private Spinner filterSpinner, bgSpinner, charSpinner;
	private List<String> filterList, colorList;
	private String[] colorStrings, filterStrings;
	private CharSequence promptArray[];
	private ArrayAdapter<String> filterAdapter, colorAdapter;
	private float brightness;
	private TextView bg_head, char_head, colors_head;

	protected static SettingsController settings = new SettingsController();


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		//Set fullscreen, must be before setContent!
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);	
		
		setContentView(R.layout.activity_option_screen);

		//Views
		brightness_icon = (ImageView) findViewById(R.id.brightness_icon);

		bg_head = (TextView) findViewById(R.id.background_head);
		char_head = (TextView) findViewById(R.id.character_head);
		colors_head = (TextView) findViewById(R.id.colors_head);

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


	//	This is called to initiate the buttons and add functionality
	private void initiateButtons() {
		back_btn.setOnClickListener(new View.OnClickListener() {

			/* Using the finish() to go back to the last activity */
			public void onClick(View v) {
				finish();
			}
		});

	}	

	//This is called to initiate the ImageViews
	private void initiateImageViews(){
		brightness_icon.setBackgroundResource(R.drawable.brightness_temp);
	}
	
	//This is called to initiate the BrightnessBar
	private void initiateBrightnessBar(){

		brightnessBar = (SeekBar)findViewById(R.id.brightness_bar);
		brightnessBar.setMax(200);
		//Set the brightnessbar according to the current brightnessvalue, default is 100 (middle of the bar)
		brightnessBar.setProgress(settings.getBrightnessPos()); 
		brightnessBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			//Not used
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}
			//Not used
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}		  
			
			//Listener for brightnessBar
			public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
				//Brightnessvalue can vary between -100 and 100
				brightness = progress-100;
				settings.setBrigtness(brightness);
			}
		});
	}
	
	//This is called to initiate the Spinners
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
		filterStrings = new String[] {"AsciiFilter","GrayscaleFilter"};
		colorStrings = new String[] {"BLACK","WHITE","GRAY","CYAN","RED","BLUE","GREEN","MAGENTA","YELLOW"};
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
		bgSpinner.setAdapter(colorAdapter);
		charSpinner.setAdapter(colorAdapter);

		//Defining value for default entry to each spinner
		//And set the last settings used if returning to optionscreen again
		filterSpinner.setSelection(settings.getFilterPos());
		bgSpinner.setSelection(settings.getBgPos());
		charSpinner.setSelection(settings.getTextPos());

		//Defining visibility for spinners and textviews
		bgSpinner.setVisibility(View.VISIBLE);
		bg_head.setVisibility(View.VISIBLE);
		charSpinner.setVisibility(View.VISIBLE);
		char_head.setVisibility(View.VISIBLE);
		colors_head.setVisibility(View.VISIBLE);		

		//Listener for filterSpinner
		filterSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			//Called when choosing an item from filterSpinner
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				int position = filterSpinner.getSelectedItemPosition();
				String filter = filterList.get(position);
				settings.setFilter(position);

				if(filter.equals("GrayscaleFilter")){
					bgSpinner.setVisibility(View.INVISIBLE);
					bg_head.setVisibility(View.INVISIBLE);
					charSpinner.setVisibility(View.INVISIBLE);
					char_head.setVisibility(View.INVISIBLE);
					colors_head.setVisibility(View.INVISIBLE);
				}	
				else{
					bgSpinner.setVisibility(View.VISIBLE);
					bg_head.setVisibility(View.VISIBLE);
					charSpinner.setVisibility(View.VISIBLE);
					char_head.setVisibility(View.VISIBLE);
					colors_head.setVisibility(View.VISIBLE);
				}

			}

			//Not used
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		//Listener for backgroundSpinner
		bgSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			//Called when choosing an item from bgSpinner
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				int position = bgSpinner.getSelectedItemPosition();

				settings.setBgColor(position);				
			}

			//Not used
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		//Listener for characterSpinner
		charSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			//Called when choosing an item from charSpinner
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				int position = charSpinner.getSelectedItemPosition();

				settings.setTextColor(position);				
			}

			//Not used
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}

		});
	}
	
	/**
	 * Retrieving settings from OptionScreen
	 * @return the current settings
	 */
	public static SettingsController getSettings(){
		return settings;
	}
}