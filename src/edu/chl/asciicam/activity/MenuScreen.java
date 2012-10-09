package edu.chl.asciicam.activity;

import edu.chl.asciicam.file.FileController;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * This activity is the first one you see when you start the app.
 * Its a menu with the buttons, take picture, load picture and options
 * @author 
 *
 */
public class MenuScreen extends Activity {

	Button take_Pic_B, load_Pic_B, optionsB;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        take_Pic_B = (Button) findViewById(R.id.Take_pic_B);
        load_Pic_B = (Button) findViewById(R.id.Load_pic_B);
        optionsB = (Button) findViewById(R.id.Options_B);
        
        /* Setting actionListeners to the buttons */
        take_Pic_B.setOnClickListener(new View.OnClickListener() {
			
        	/*Using the Intent class to open the phones camera */
			public void onClick(View v) {
				Intent i = new Intent(MenuScreen.this, CameraScreen.class);
				startActivity(i);
				
//				i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//				startActivity ForResult(i, 0);;
			}
		});
        load_Pic_B.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        
    }
    	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_screen, menu);
        return true;
    }
}
