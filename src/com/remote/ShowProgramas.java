package com.remote;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


public class ShowProgramas extends Activity
{
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.showprograma);
	     
	        Bundle extras = getIntent().getExtras(); 
	        if(extras !=null)
	        {
	        	Toast.makeText(getApplicationContext(),
	        			extras.getString("canal"), Toast.LENGTH_LONG)
	        			.show();
	      
	        }

	        //Read more: http://getablogger.blogspot.com/2008/01/android-pass-data-to-activity.html#ixzz1qbbnuvPG
	    }
}
