package com.alv.app;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.provider.Settings;



//https://code.google.com/p/javamail-android/downloads/list
//http://stackoverflow.com/questions/7146706/how-to-receive-email-from-gmail-android?rq=1

public class Utilitaire extends AsyncTask<String, Void, Void> {
 public  void testmailarc() {
		Properties props = new Properties();
	    //IMAPS protocol
	    props.setProperty("mail.store.protocol", "imaps");
	    //Set host address
	    props.setProperty("mail.imaps.host", "imap.gmail.com");
	    //Set specified port
	    props.setProperty("mail.imaps.port", "993");
	    //Using SSL
	    props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.setProperty("mail.imap.socketFactory.fallback", "false");
	    
//	    props.setProperty("mail.imaps.connectiontimeout", "5000");
//	    props.setProperty("mail.imaps.timeout", "5000");
	    //Setting IMAP session
	    Session imapSession = Session.getInstance(props);
	    try {
	    	Store store = imapSession.getStore("imaps");
	    	//Connect to server by sending username and password.
	    	//Example mailServer = imap.gmail.com, username = abc, password = abc
	
	    	store.connect("imap.gmail.com", "arc34430@gmail.com", "arclatvedas34000");
	// nom : arc
	   // arc34430
	//naissance 01 01 1992
	    	//Get all mails in Inbox Forlder
	    	Folder inbox = store.getFolder("Inbox");
	    	inbox.open(Folder.READ_ONLY);
	
	    
	    	//Return result to array of message
	    	Message[] result = inbox.getMessages();
	    	System.out.println("result= "+result);
	    } catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


 
public void activateGPSifPossible(Activity activity){
		LocationManager service = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
		boolean enabled = service
		  .isProviderEnabled(LocationManager.GPS_PROVIDER);

		// check if enabled and if not send user to the GSP settings
		// Better solution would be to display a dialog and suggesting to 
		// go to the settings
		if (!enabled) {
		  Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		  activity.startActivity(intent);
		} 


	}

public String getLastLocation(Activity activity){
	 String result="";
	 
	LocationManager  locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
	boolean enabled = locationManager
			  .isProviderEnabled(LocationManager.PASSIVE_PROVIDER);

	 System.out.println("PASSIVE_PROVIDER enabled= "+ enabled);
	// Define the criteria how to select the locatioin provider -> use
    // default
    Criteria criteria = new Criteria();
    String provider = locationManager.getBestProvider(criteria, false);
    System.out.println("provider= "+provider);
    Location location = locationManager.getLastKnownLocation(provider);
    System.out.println("location= "+location);
    
    if (location != null){
    	
    	
    	Geocoder gcd = new Geocoder(activity.getApplicationContext(), Locale.getDefault());
    	List<Address> addresses;
		try {
			addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
			if (addresses.size() > 0) {
	    	    System.out.println(addresses.get(0).toString());
	    	    result=addresses.get(0).getLocality();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = location.toString();
		}
    	
    }
    System.out.println("getLastLocation= "+result);
    return result;

}

@Override
protected Void doInBackground(String... params) {
	testmailarc();
	return null;
}
}
