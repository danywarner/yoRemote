package com.remote;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
//import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainScreen extends Activity {
    /** Called when the activity is first created. */
	
	//private Button l2a;
	private TextView tv1;
	private TextView tv2;
	private boolean loggedOK;
	
	private static final String NAMESPACE = "http://integration/";
	private static final String URL = "http://192.168.43.80:8080/YoRemoteWebService/YoRemoteClienteWSService?WSDL";
	private static final String SOAP_ACTION = "http://integration/YoRemoteClienteWS/loguearseRequest";
	private static final String METHOD_NAME = "loguearse";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv1=(TextView)findViewById(R.id.userLogin);
        tv2=(TextView)findViewById(R.id.passwordLogin);
        //loggedOK=(Button)findViewById(R.id.btnLogin);
        loggedOK=true;
 
        
        SoapObject request  = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("user","174Hoy31@yahoo.com");
        request.addProperty("password","Juan124");
     /*   SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        
        
        try{
        	androidHttpTransport.call(SOAP_ACTION, envelope);
        	SoapObject resultRequestSOAP = (SoapObject) envelope.bodyIn;
        	
        	Toast.makeText(getApplicationContext(),
        			resultRequestSOAP.toString(), Toast.LENGTH_LONG)
        			.show();
        	
        //	tv.setText();
        }catch(Exception e){
        	Toast.makeText(getApplicationContext(),
        			e.getMessage(), Toast.LENGTH_LONG)
        			.show();
        }*/
        
    }
    
    public void loggearse(View view) 
    {
    	Usuario person=new Usuario();
    	loggedOK=person.loguearse(tv1.getText(),tv2.getText());
    	if(loggedOK==true)
    	{
    	   Intent i = new Intent(getApplicationContext(), SecondScreen.class);
           startActivity(i);
    	}
    	else
    	{
    		Toast.makeText(getApplicationContext(),
        			"Usuario o password incorrectos", Toast.LENGTH_LONG)
        			.show();
    	}
    	
    }

                 
}
