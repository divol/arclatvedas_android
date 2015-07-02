package com.alv.app.spinchart;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.alv.db.charte.CharteDataSource;

import android.content.Context;

public class SQLLoader {
	Context c;
	CharteDataSource charteDS;

    public SQLLoader(Context context)
    {
         c= context;
     }
    
    public SQLLoader(Context context,CharteDataSource charteDS)
    {
        this(context);
        this.charteDS = charteDS;
        
     }
    public void loadSQL(int fileresourceid){

		
		
		
		InputStream csvFileInputStream = c.getResources().openRawResource(fileresourceid); // getting XML
	
	
	    BufferedReader reader = new BufferedReader(new InputStreamReader(csvFileInputStream));
	    
	    charteDS.beginTransaction();
	    try {
	        String line;
	        
	        while ((line = reader.readLine()) != null) {
	        	charteDS.execSQL(line);
	        }
	        
	        charteDS.setTransactionSuccessful();
	    }
	    catch (IOException ex) {
	        // handle exception
	    }
	    finally {
	    	charteDS.endTransaction();
	        try {
	        	csvFileInputStream.close();
	        }
	        catch (IOException e) {
	            // handle exception
	        }
	    }

	
	
	}
}
