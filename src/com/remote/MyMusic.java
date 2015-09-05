package com.remote;

import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.remote.R.drawable;

public class MyMusic extends Activity{

	private String ruta="/sdcard/Music/Telmex";
	private String nombreCancion;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymusic);
        
        traerArchivos(new File(ruta));
    }
    
    public void traerArchivos(File path) 
    { 
    	LinearLayout layout = (LinearLayout) findViewById(R.id.mymusiclayout);
      
        if( path.exists() ) {
        	
            File[] files = path.listFiles();
            for(int i=0; i<files.length; i++) 
            {
               Button cancion=new Button(this);
               cancion.setBackgroundColor(2);
               cancion.setTextSize(23);
               cancion.setCompoundDrawablesWithIntrinsicBounds(0,0,drawable.musicicon,0);
               nombreCancion=new String(files[i].getName());
               cancion.setText(nombreCancion);
               crearListener(cancion,nombreCancion);
               LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                       LinearLayout.LayoutParams.MATCH_PARENT,
                       LinearLayout.LayoutParams.WRAP_CONTENT
               );	
               layout.addView(cancion,p); 
               
            }
        }
    }
    
    public void crearListener(Button video, final String nombre)
    {
    	video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               audioPlayer(ruta,nombre);
            }
        }); 
    }
    
    public void audioPlayer(String path, String fileName)
    {
    	Intent intent = new Intent(MyMusic.this, PlayAudio.class);
        intent.putExtra("songfilename", path+"/"+fileName);
        startActivity(intent);


     
     }
    
    
}
