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

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class OptionScreen extends Activity {

	Button back_btn;
	ImageView brightness_icon;
	//BrightnessView brightness_icon;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);
		back_btn = (Button) findViewById(R.id.back);
		brightness_icon = (ImageView) findViewById(R.id.brightness_icon);
		initiateButtons();
		initiateImageView();
    }

	
	//This is called by to initiate the buttons and add functionality
	private void initiateButtons() {
		back_btn.setOnClickListener(new View.OnClickListener() {

			/* Using the finish() to go back to the last activity */
			public void onClick(View v) {
				finish();
			}
		});
			
	}	
	
	//This is called by to initiate the imageviews.
	private void initiateImageView(){
		brightness_icon.setBackgroundResource(R.drawable.brightness_temp);
		
	}
	
}