package edu.chl.asciicam.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class OptionScreen extends Activity {

	Button back_btn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);
		back_btn = (Button) findViewById(R.id.back);
		initiateButtons();
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
	}
}