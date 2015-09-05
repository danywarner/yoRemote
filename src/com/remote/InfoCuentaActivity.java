package com.remote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
 
public class InfoCuentaActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infocuenta);
    }
    
    
    
    
    
    
    
    
    public void onClickVersion(View view) 
    {
    	Toast.makeText(getApplicationContext(),
    			"Version", Toast.LENGTH_SHORT)
    			.show();
    			
    }
	 
	 public void onClickCLUF(View view) 
    {
		 Intent i = new Intent(getApplicationContext(), Terminos.class);
	        startActivity(i);
    			 
    }
	 public void onClickPreguntas(View view) 
	 {
		 Toast.makeText(getApplicationContext(),
	    			"FAQ", Toast.LENGTH_SHORT)
	    			.show();
	    			
	  }
	 
	 public void onClickContacto(View view) 
	    {
		 Toast.makeText(getApplicationContext(),
	    			"Contact Info.", Toast.LENGTH_SHORT)
	    			.show();
	    }
	 
	 public void onClickFactura(View view) 
	    {
		 Toast.makeText(getApplicationContext(),
	    			"Factura$", Toast.LENGTH_SHORT)
	    			.show();
	    }
	 
	 public void onClickCitas(View view) 
	    {
		 Toast.makeText(getApplicationContext(),
	    			"Visitas", Toast.LENGTH_SHORT)
	    			.show();
	    }
}
