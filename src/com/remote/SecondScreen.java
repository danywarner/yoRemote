package com.remote;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class SecondScreen extends Activity{
	
	private TabHost th;
	private Button btnCanales;
	private Button btnFecha;
	private RadioButton rbprograma;
	private RadioButton rbcanal;
	private Button buttonSearch;
	private Button buttonCategorias;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        th = (TabHost)findViewById(R.id.tabhost);
        th.setup();
        TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1); 
        specs.setIndicator("Explorar");
        th.addTab(specs);
        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Buscar");
        th.addTab(specs);
        specs = th.newTabSpec("tag3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Mi Cuenta");
        th.addTab(specs);
        
        for(int i=0;i<th.getTabWidget().getChildCount();i++) 
        {
            TextView tv = (TextView) th.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#0080FF"));
        } 
        
        btnCanales = (Button)findViewById(R.id.bCanales);
        btnFecha = (Button)findViewById(R.id.bFechayHora);
        
        //btnNOCall= (Button)findViewById(R.id.buttonNoCall);
        
        
        rbprograma=(RadioButton) findViewById(R.id.radioButtonPrograma);
        rbcanal=(RadioButton) findViewById(R.id.radioButtonCanal);
        buttonSearch=(Button) findViewById(R.id.buttonBuscar);
        buttonCategorias=(Button) findViewById(R.id.bCategorias);
        
        
        
        btnCanales.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BrowseCanales.class);
                startActivity(i);
            }
        });   
        
        
        btnFecha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BrowseFecha.class);
                startActivity(i);
            }
        }); 
        
        
        
  
        
    }
	
	 public void onClickCat(View view) 
	    {
	    	
	    	Intent i = new Intent(getApplicationContext(), ShowCategorias.class);
	        startActivity(i);
	    	
	    	
	    }
	
	 
	
	public void traerCanalesPPV(View view) //consultar los canales tipo PPV en la tabla de canales y crear textViews con ellos
	  {
	
			 Intent i = new Intent(getApplicationContext(), VerPrograma.class);
			 startActivity(i);
	  }
	
	public void traerProgramasPPV(View view) //consultar los programas tipo PPV en la tabla de canales y crear textViews con ellos
	  {
	
			 Intent i = new Intent(getApplicationContext(), VerPrograma.class);
			 startActivity(i);
	  }
			  
	 
	
	
	 public void onClickLogout(View view) 
     {
     	
		 AlertDialog.Builder builder;
	        AlertDialog alertDialog;
	        Context mContext=getApplicationContext();
	        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
	        View layout =inflater.inflate(R.layout.confirmasalida,null);
	        builder =new AlertDialog.Builder(this);
	        builder.setView(layout);
	        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() 
	        {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					
					finish();
					
				}
			});
	        
	        
	        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					
					Toast.makeText(getApplicationContext(), "OK NO", Toast.LENGTH_SHORT).show();
					
				}
			});
	        
	        alertDialog = builder.create();
	        
	        alertDialog.show();
     }
	
	 public void onClickRSS(View view) 
     {
     	
     			 Intent i = new Intent(getApplicationContext(), InfoCuentaActivity.class);
        startActivity(i);
     }
	 
	 public void onClickPurchases(View view) 
     {
     	
     			 Intent i = new Intent(getApplicationContext(), InfoCuentaActivity.class);
        startActivity(i);
     }
	 
	
	 
	 public void onClickRequCall(View view) 
     {
		 AlertDialog.Builder builder;
	        AlertDialog alertDialog;
	        Context mContext=getApplicationContext();
	        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
	        View layout =inflater.inflate(R.layout.confirmacall,null);
	        builder =new AlertDialog.Builder(this);
	        builder.setView(layout);
	        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					
					Toast.makeText(getApplicationContext(), "OK ESPERA", Toast.LENGTH_SHORT).show();
					
				}
			});
	        
	        
	        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					
					Toast.makeText(getApplicationContext(), "OK NO", Toast.LENGTH_SHORT).show();
					
				}
			});
	        
	        alertDialog = builder.create();
	        
	        alertDialog.show();
	        
	       
     }
	 
	
	 
	 public void onClickInfo(View view) 
     {
     	        
     			 Intent i = new Intent(getApplicationContext(), InfoCuentaActivity.class);
        startActivity(i);
     }
	 
	 public void onClickMyMusic(View view) 
     {
     	
     			 Intent i = new Intent(getApplicationContext(), MyMusic.class);
        startActivity(i);
     }
	 
	 public void onClickMyVids(View view) 
     {
     	
     			 Intent i = new Intent(getApplicationContext(), MyVideos.class);
        startActivity(i);
     }
	 
	 
	 public void onClickBusqueda(View view) 
     {
     	if(rbcanal.isChecked())
     	{
		 Toast.makeText(getApplicationContext(),
	    			"Buscando por canal", Toast.LENGTH_SHORT)
	    			.show();
     	}
     	else if(rbprograma.isChecked())
     	{
		 Toast.makeText(getApplicationContext(),
	    			"Buscando por programa", Toast.LENGTH_SHORT)
	    			.show();
     	}
     	
     	
     	
     	
     	
     	
     }
}