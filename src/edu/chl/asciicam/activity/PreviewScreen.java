package edu.chl.asciicam.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
 * 
 * This activity sets the taken picture as the background.
 * You can choose what to with you taken picture, 
 * save, convert or delete picture and take a new one.
 * @author Kryckans
 *
 */
public class PreviewScreen extends Activity {

	Button back_btn, save_pic_btn, convert_btn;
	Bitmap bmp;
	ImageView iv = (ImageView) findViewById(R.id.preview_pic);
	public static String DIRECTORY_PICTURES;
	BroadcastReceiver mExternalStorageReceiver;
	boolean mExternalStorageAvailable = false;
	boolean mExternalStorageWriteable = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_screen);
        back_btn = (Button) findViewById(R.id.back);
        save_pic_btn = (Button) findViewById(R.id.save);
        convert_btn = (Button) findViewById(R.id.convert);
        
        //Setting the taken picture as background
        Bundle extras = this.getIntent().getExtras();
        byte[] jpgArray = (byte[]) extras.getByteArray("jpgByteArray");
        //byte[] jpgArray = (byte[]) this.getIntent().getByteArrayExtra("jpgByteArray");
        //savedInstanceState.getByteArray("jpgByteArray");
        bmp = (Bitmap) BitmapFactory.decodeByteArray(jpgArray, 0, jpgArray.length);
        if(bmp != null){
        iv.setImageBitmap(bmp);
        }

    
        back_btn.setOnClickListener(new View.OnClickListener() {
    		
        	/*Using the finish() to go back to the last activity */
    		public void onClick(View v) {
    			finish();
    		}
    	});
        
//        save_pic_btn.setOnClickListener(new View.OnClickListener() {
//    		
//    		public void onClick(View v) {
//    			updateExternalStorageState();
//       		}
//    	}); 
    
    
        convert_btn.setOnClickListener(new View.OnClickListener() {
    		// if click here, the picture will be converted
    		public void onClick(View v) {
    		}
    	});    //TODO convert ´picture to ascii
        
        
    }

/** Code from https://developer.android.com/ */
    
//    void updateExternalStorageState() {
//        String state = Environment.getExternalStorageState();
//        if (Environment.MEDIA_MOUNTED.equals(state)) {
//            mExternalStorageAvailable = mExternalStorageWriteable = true;
//        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
//            mExternalStorageAvailable = true;
//            mExternalStorageWriteable = false;
//        } else {
//            mExternalStorageAvailable = mExternalStorageWriteable = false;
//        }
//        handleExternalStorageState(mExternalStorageAvailable,
//                mExternalStorageWriteable);
//    }
//    
//    public boolean handleExternalStorageState(boolean i, boolean j){
//    	if(i && j)
//    	return true;
//    	else
//    	return false;
//    }
//    
//
//    void startWatchingExternalStorage() {
//        mExternalStorageReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Log.i("test", "Storage: " + intent.getData());
//                updateExternalStorageState();
//            }
//        };
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
//        filter.addAction(Intent.ACTION_MEDIA_REMOVED);
//        registerReceiver(mExternalStorageReceiver, filter);
//        updateExternalStorageState();
//    }
//
//    void stopWatchingExternalStorage() {
//        unregisterReceiver(mExternalStorageReceiver);
//    }
//    
//    void createExternalStoragePublicPicture() {
//        // Create a path where we will place our picture in the user's
//        // public pictures directory.  Note that you should be careful about
//        // what you place here, since the user often manages these files.  For
//        // pictures and other media owned by the application, consider
//        // Context.getExternalMediaDir().
//        File path = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
//        File file = new File(path, "DemoPicture.jpg");
//
//        try {
//            // Make sure the Pictures directory exists.
//            path.mkdirs();
//
//            // Very simple code to copy a picture from the application's
//            // resource into the external file.  Note that this code does
//            // no error checking, and assumes the picture is small (does not
//            // try to copy it in chunks).  Note that if external storage is
//            // not currently mounted this will silently fail.
//            InputStream is = getResources().openRawResource(R.drawable.logo);
//            OutputStream os = new FileOutputStream(file);
//            byte[] data = new byte[is.available()];
//            is.read(data);
//            os.write(data);
//            is.close();
//            os.close();
//
//            // Tell the media scanner about the new file so that it is
//            // immediately available to the user.
//            MediaScannerConnection.scanFile(this,
//                    new String[] { file.toString() }, null,
//                    new MediaScannerConnection.OnScanCompletedListener() {
//          
//			public void onScanCompleted(String path, Uri uri) {
//					Log.i("ExternalStorage", "Scanned " + path + ":");
//                    Log.i("ExternalStorage", "-> uri=" + uri);
//				}
//            });
//        } catch (IOException e) {
//            // Unable to create file, likely because external storage is
//            // not currently mounted.
//            Log.w("ExternalStorage", "Error writing " + file, e);
//        }
//    }


    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_preview_screen, menu);
        return true;
    }
}
