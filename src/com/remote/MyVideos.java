package com.remote;
	
import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.remote.R.drawable;

public class MyVideos extends Activity{

	private String path="/sdcard/Movies/Telmex";
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myvideos);
       
        createLinks(new File(path));
       
    }
    
    public void createLinks(File path) 
    { 
    	LinearLayout layout = (LinearLayout) findViewById(R.id.myvideoslayout);
        if( path.exists() ) {
            File[] files = path.listFiles();
            for(int i=0; i<files.length; i++) 
            {
              if(files[i].getName().toString().charAt(0)!='.')
              {
               String videoName;
               Button video=new Button(this);
               video.setBackgroundColor(2);
               video.setTextSize(23);
               video.setCompoundDrawablesWithIntrinsicBounds(0,0,drawable.videoicon,0);
               videoName=new String(files[i].getName());
               video.setText(videoName);
               createListener(video,videoName);
               LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                       LinearLayout.LayoutParams.MATCH_PARENT,
                       LinearLayout.LayoutParams.WRAP_CONTENT
               );	
               layout.addView(video,p); 
              }
               
            }
        }
    }
    
    public void createListener(final Button video, final String name)
    {
    	video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	//videoPlayer(path, name, true);
            	videoPlayer(path,  video.getText().toString(), true);
            }
        }); 
    }
    
    public void videoPlayer(String path, String fileName, boolean autoplay)
    {
    	Intent intent = new Intent(MyVideos.this, ViewVideo.class);
        intent.putExtra("videofilename", path+"/"+fileName);
        startActivity(intent);
     }
    
    
	
}
