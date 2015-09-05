package com.remote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class BrowseFecha extends Activity {
    /** Called when the activity is first created. */
	
	private DatePicker dp;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browsefecha);
        
        dp=(DatePicker) findViewById (R.id.datePicker2);
    }
    
    
    
    
    
    public void searchByDate(View view) 
    {
    	//dp.day
    	Toast.makeText(getApplicationContext(),
    			"Buscando por fecha", Toast.LENGTH_SHORT)
    			.show();
    }
}