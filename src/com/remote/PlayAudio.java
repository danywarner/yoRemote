package com.remote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayAudio extends Activity {
      private String filename;
      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            System.gc();
            Intent i = getIntent();
            Bundle extras = i.getExtras();
            filename = extras.getString("songfilename");
            VideoView vv = new VideoView(getApplicationContext());
            setContentView(vv);
            vv.setVideoPath(filename);
            vv.setMediaController(new MediaController(this));
            vv.requestFocus();
            vv.start();
      }
      @Override
      public void onBackPressed() {
    	 
          this.finish();
      }
    
}