package com.alv.app.spinchart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

import com.alv.app.R;

public class CSVLoader {

	Context c;
	String[] headers=null;
	public ArrayList<HashMap<String,ArrayList<String>>> result ; // colonnes multiples avec le meme nom possible

	
    public CSVLoader(Context context)
    {
         c= context;
         result = new ArrayList<HashMap<String,ArrayList<String>>>();
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
                 int i=0;
	             String[] RowData = line.split(";");
	             for (String str : RowData){
	            	 ArrayList<String> col =(ArrayList<String>) row.get(headers[i]);
	            	 
	            	 if (col == null){
	            		 col = new ArrayList<String>();
	            	 }
	            	 col.add(str);
	            	 row.put(headers[i], col);
	            	 
	            	 i++;
	             }
	             result.add(row);
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
