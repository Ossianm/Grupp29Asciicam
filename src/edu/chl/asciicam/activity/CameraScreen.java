package edu.chl.asciicam.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CameraScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_camera_screen, menu);
        return true;
    }
}
