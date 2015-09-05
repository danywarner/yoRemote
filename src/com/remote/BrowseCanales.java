package com.remote;




import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class BrowseCanales extends Activity {
    /** Called when the activity is first created. */
	
	
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browsechannels);
        
      

     String todosLosCanales="listacanales.txt";
     cargarCanales(todosLosCanales);

     
    }
    
    public void cargarCanales(String archivoCanales)
    {

     try {
    	 File sdcard = Environment.getExternalStorageDirectory();
    	 File file = new File(sdcard,archivoCanales);
   	  LinearLayout layout = (LinearLayout) findViewById(R.id.channelslistlayout);
         BufferedReader br = new BufferedReader(new FileReader(file));
         String line;
         String videoName;
         while ((line = br.readLine()) != null) {
       	
       	  Button canal=new Button(this);
             canal.setBackgroundColor(2);
             canal.setTextSize(23);
             videoName=new String(line);
             if(!videoName.equalsIgnoreCase("cine+") && !videoName.equalsIgnoreCase("espn+") && !videoName.equalsIgnoreCase("e!") )
             canal.setCompoundDrawablesWithIntrinsicBounds(getIDResource(videoName.toLowerCase().replaceAll("\\s","")), 0, getIDResource("star"), 0);
             else
             {
           	  if(videoName.equalsIgnoreCase("cine+"))
           	     canal.setCompoundDrawablesWithIntrinsicBounds(getIDResource("cineplus"), 0, getIDResource("star"), 0);
           	  if(videoName.equalsIgnoreCase("espn+"))
            	     canal.setCompoundDrawablesWithIntrinsicBounds(getIDResource("espnplus"), 0, getIDResource("star"), 0);
           	  if(videoName.equalsIgnoreCase("e!"))
            	     canal.setCompoundDrawablesWithIntrinsicBounds(getIDResource("e"), 0, getIDResource("star"), 0);
             }
             canal.setText((videoName.toLowerCase()));
             createListener(canal,videoName);
             LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                     LinearLayout.LayoutParams.MATCH_PARENT,
                     LinearLayout.LayoutParams.WRAP_CONTENT
             );	
             layout.addView(canal,p); 
         }
         
     }
     catch (IOException e) {
        e.printStackTrace();
     }
   }
     
    
    
    public int getIDResource(String nombreRecurso)
    {
    	return getResources().getIdentifier(nombreRecurso, "drawable", "com.remote");
    }
    
     public Drawable getAndroidDrawable(String pDrawableName){
        int resourceId=Resources.getSystem().getIdentifier(pDrawableName, "drawable", "android");
        if(resourceId==0)
        {
        	Toast.makeText(getApplicationContext(),
        			"Version", Toast.LENGTH_SHORT)
        			.show();
            return null;
        } 
        else
        {
        	Toast.makeText(getApplicationContext(),
        			"Version2", Toast.LENGTH_SHORT)
        			.show();
            return Resources.getSystem().getDrawable(resourceId);
        }
    }
    
    public static int getResId(String variableName, Context context, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } 
    }
    
    public void createListener(final Button video, final String name)
    {
    	video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent i = new Intent(getApplicationContext(), ShowProgramas.class);
            	i.putExtra("canal", (CharSequence)v.getTag());
                startActivity(i);
            }
        }); 
    }
    
    public void onToggleStar(View view)
    {
    	Intent i = new Intent(getApplicationContext(), ShowProgramas.class);
    	i.putExtra("canal", (CharSequence)view.getTag());
        startActivity(i);
    }
    
   
    
   
        
    
    
    
    
    
    
    
}