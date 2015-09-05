package com.remote;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;



public class VerPrograma extends Activity
{
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showprograma);	
        
        Button button1 = (Button)findViewById(R.id.buttonDownload);
        button1.setOnClickListener(mStart2Listener);
        
        Button button2 = (Button)findViewById(R.id.buttonDownload2);
        button2.setOnClickListener(mStart3Listener); 
       
    }
  

	
	
	
	
	
	
	private OnClickListener mStart2Listener = new OnClickListener() {
        public void onClick(View v) { 
            startService(new Intent(VerPrograma.this, ServicioFTP.class)
                            .putExtra("name", "One")
                            .putExtra("username", "lola")
                            .putExtra("password", "123")
                            .putExtra("filename", "Windows Shopper.3gp"));
           /* bindService(new Intent(VerPrograma.this, ServiceStartArguments.class)
            .putExtra("name", "One")
            .putExtra("username", "lola")
            .putExtra("password", "123")
            .putExtra("filename", "Windows Shopper.3gp"),mConnection,Context.BIND_AUTO_CREATE);*/
                            
        }
    };
    
    private OnClickListener mStart3Listener = new OnClickListener() {
        public void onClick(View v) { 
            startService(new Intent(VerPrograma.this,
                    ServicioFTP.class)
                            .putExtra("name", "One")
                            .putExtra("username", "lola")
                            .putExtra("password", "123")
                            .putExtra("filename", "07 Sismic Music Podcast - Episode 8.mp3"));
                            
        }
    };
	
	 public void mostrartxt(String cadena)
	    {
	    	Toast toast = Toast.makeText(this, cadena, Toast.LENGTH_SHORT);
	    	toast.show();
	    }
    

}
