/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.remote;

import java.io.FileOutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;




public class ServicioFTP extends Service {
    private NotificationManager mNM;
    private Intent mInvokeIntent;
    private volatile Looper mServiceLooper;
    private volatile ServiceHandler mServiceHandler;
    
    private String localurl = "/sdcard/Movies/Telmex/";
    private String hostname = "Felipe-PC";
    private String remoteurl = "/";
    private FTPClient mFTPClient = null;
    private String filename = "07 Sismic Music Podcast - Episode 8.mp3";
    private String username = "lola";
    private String password = "123";
    private int port =21;
    private boolean con=false;
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Bundle arguments = (Bundle)msg.obj;

           
            username = arguments.getString("username");
            password = arguments.getString("password");
            filename = arguments.getString("filename");
            

            Log.i("ServiceStartArguments", "Message: " + msg + ", "
                    + arguments.getString("name"));

                 if(con==false)
                	 con=ftpConnect(hostname,username,password,port);
               
               // checkExternalMedia();
                if(ftpDownload(remoteurl+filename,localurl+filename)==true)
                {
                	  showNotification("se ha descargado "+filename);
                	  
                	  long endTime = System.currentTimeMillis() + 10*1000;
                      while (System.currentTimeMillis() < endTime) {
                          synchronized (this) {
                              try {
                                  wait(endTime - System.currentTimeMillis());
                              } catch (Exception e) {
                              }
                          }
                      }

                      hideNotification();
                      
                	 
                	  stopSelf(msg.arg1);
                }
            }

    }
    
    public boolean ftpConnect(String host, String username, String password, int port)
	{
	try {
	mFTPClient = new FTPClient();

	mFTPClient.connect(host, port);
	
	
	if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {
	
	boolean status = mFTPClient.login(username, password);
	
	/* Set File Transfer Mode
	*
	* To avoid corruption issue you must specified a correct
	* transfer mode, such as ASCII_FILE_TYPE, BINARY_FILE_TYPE,
	* EBCDIC_FILE_TYPE .etc. Here, I use BINARY_FILE_TYPE
	* for transferring text, image, and compressed files.
	*/
	mFTPClient.setFileType(FTP.ASCII_FILE_TYPE);
	mFTPClient.enterLocalPassiveMode();
	mFTPClient.changeWorkingDirectory("/");
	mostrartxt("conexion establecida");
	return status;
	}
	} catch(Exception e) {
		mostrartxt("Error: could not connect to host " + host );
	}
	
	return false;
	}
    
    public boolean ftpDownload(String srcFilePath, String desFilePath)
    {
    	
    	if(con==true)showNotification("Ha iniciado la descarga de " +filename);
        boolean status = false;
        try {
            FileOutputStream desFileStream = new FileOutputStream(desFilePath);
            status = mFTPClient.retrieveFile(srcFilePath, desFileStream);
            desFileStream.close();
            mFTPClient.disconnect();
            con=false;

            return status;
        } catch (Exception e) {
            //mostrar("download failed");
            mostrartxt(e.getMessage());
        }

        return status;
    }

    @Override
    public void onCreate() {
    	
    	
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        // This is who should be launched if the user selects our persistent
        // notification.
        mInvokeIntent = new Intent(this, VerPrograma.class);

        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
     
    }
    
    

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
      
    	Log.i("ServiceStartArguments","Starting #" + startId + ": " + intent.getExtras());
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        msg.arg2 = flags;
        msg.obj = intent.getExtras();
       // mostrartxt(msg.obj.toString());
        mServiceHandler.sendMessage(msg);
        Log.i("ServiceStartArguments", "Sending: " + msg);

        // For the start fail button, we will simulate the process dying
        // for some reason in onStartCommand().
        if (intent.getBooleanExtra("fail", false)) 
        {
            // Don't do this if we are in a retry... the system will
            // eventually give up if we keep crashing.
            if ((flags&START_FLAG_RETRY) == 0) {
                // Since the process hasn't finished handling the command,
                // it will be restarted with the command again, regardless of
                // whether we return START_REDELIVER_INTENT.
                Process.killProcess(Process.myPid());
            }
        }

        // Normally we would consistently return one kind of result...
        // however, here we will select between these two, so you can see
        // how they impact the behavior.  Try killing the process while it
        // is in the middle of executing the different commands.
        return intent.getBooleanExtra("redeliver", false)
                ? START_REDELIVER_INTENT : START_NOT_STICKY;
    }
    
    
    
    
   
    
    public void mostrartxt(String cadena)
    {
    	Toast toast = Toast.makeText(this, cadena, Toast.LENGTH_SHORT);
    	toast.show();
    }

    @Override
    public void onDestroy()
    {
        mServiceLooper.quit();

        hideNotification();

        // Tell the user we stopped.
        Toast.makeText(ServicioFTP.this, "service_destroyed",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent)
    {
    	 mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
         // This is who should be launched if the user selects our persistent
         // notification.
         mInvokeIntent = new Intent(this, VerPrograma.class);

         // Start up the thread running the service.  Note that we create a
         // separate thread because the service normally runs in the process's
         // main thread, which we don't want to block.  We also make it
         // background priority so CPU-intensive work will not disrupt our UI.
         HandlerThread thread = new HandlerThread("ServiceStartArguments",
                 Process.THREAD_PRIORITY_BACKGROUND);
         thread.start();

         mServiceLooper = thread.getLooper();
         mServiceHandler = new ServiceHandler(mServiceLooper);
        return null;
    }

    /**
     * Show a notification while this service is running.
     */
    private void showNotification(String text) {
        // Set the icon, scrolling text and timestamp
        Notification notification = new Notification(R.drawable.downloading, text,
                System.currentTimeMillis());

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, VerPrograma.class), 0);

        // Set the info for the views that show in the notification panel.
        notification.setLatestEventInfo(this, getText(R.string.service_start_arguments_label),
                       text, contentIntent);

        // We show this for as long as our service is processing a command.
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        notification.defaults |= Notification.DEFAULT_SOUND;

        // Send the notification.
        // We use a string id because it is a unique number.  We use it later to cancel.
        mNM.notify(R.string.service_created, notification);
    }

    private void hideNotification() {
        mNM.cancel(R.string.service_created);
    }
}
