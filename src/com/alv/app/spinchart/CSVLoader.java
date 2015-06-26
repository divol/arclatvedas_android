package com.alv.app.spinchart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;


public class CSVLoader {

	Context c;
	String[] headers=null;
	public ArrayList<HashMap<String,ArrayList<String>>> resultAsArray ; // colonnes multiples avec le meme nom possible
	public ArrayList<HashMap<String,String>> resultAsString ; // colonnes simple

	
    public CSVLoader(Context context)
    {
         c= context;
         resultAsArray = new ArrayList<HashMap<String,ArrayList<String>>>();
         resultAsString = new  ArrayList<HashMap<String,String>>();
     }
    
    public CSVLoader(Context context,int fileresourceid)
    {
        this(context);
        
        loadCSV(fileresourceid);
     }
    
	public void loadCSV(int fileresourceid){
		
		
		
		InputStream csvFileInputStream = c.getResources().openRawResource(fileresourceid); // getting XML
	
	
	    BufferedReader reader = new BufferedReader(new InputStreamReader(csvFileInputStream));
	    try {
	        String line;
	        
	        if ((line = reader.readLine()) != null) {
	        	//line 0 for headers
	        	headers = line.split(";");
	        }
	        while ((line = reader.readLine()) != null) {
	        	HashMap<String,ArrayList<String>> row = new HashMap<String, ArrayList<String>>();
	        	HashMap<String,String> rowsimple = new HashMap<String, String>();
                 int i=0;
	             String[] RowData = line.split(";");
	             for (String str : RowData){
	            	 ArrayList<String> col =(ArrayList<String>) row.get(headers[i]);
	            	 
	            	 if (col == null){
	            		 col = new ArrayList<String>();
	            	 }
	            	 col.add(str);
	            	 row.put(headers[i], col);
	            	 rowsimple.put(headers[i], str);
	            	 i++;
	             }
	             resultAsArray.add(row);
	             resultAsString.add(rowsimple);
	        }
	    }
	    catch (IOException ex) {
	        // handle exception
	    }
	    finally {
	        try {
	        	csvFileInputStream.close();
	        }
	        catch (IOException e) {
	            // handle exception
	        }
	    }

	
	}
}
