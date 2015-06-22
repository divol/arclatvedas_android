package com.alv.db.materiel;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

import com.alv.app.DataFragment;
import com.alv.app.MaterielArrayAdapter;
import com.alv.db.DataSourceBase;




public class MaterielDataSource extends DataSourceBase<Materiel>{
	
	// Database fields
	  private String[] allColumns = { MaterielSQLiteOpenHelper.COLUMN_MATERIEL_ID,
			  MaterielSQLiteOpenHelper.COLUMN_MATERIEL_NAME,
			  MaterielSQLiteOpenHelper.COLUMN_MATERIEL_SERIALNUMBER,
			  MaterielSQLiteOpenHelper.COLUMN_MATERIEL_DATEACHAT,
			  MaterielSQLiteOpenHelper.COLUMN_MATERIEL_COMMENT,
			  MaterielSQLiteOpenHelper.COLUMN_MATERIEL_IMAGE};

	  
	  
	  
	  public MaterielDataSource(Context context) {
			super(context);
		}

	  
	  public Materiel create (Materiel inmateriel){
		    ContentValues values =inmateriel.getValues();

		    long insertId = database.insert(MaterielSQLiteOpenHelper.TABLE_MATERIEL, null,
		        values);
		    Cursor cursor = database.query(MaterielSQLiteOpenHelper.TABLE_MATERIEL,
		        allColumns, MaterielSQLiteOpenHelper.COLUMN_MATERIEL_ID + " = " + insertId, null,
		        null, null, null);
		    cursor.moveToFirst();
		    Materiel newMatos = cursorToMateriel(cursor);
		    cursor.close();
		    return newMatos;
		  
	  }
	 
	  
	  public void update(Materiel inmateriel){
		  ContentValues values =inmateriel.getValues();
		  database.update(MaterielSQLiteOpenHelper.TABLE_MATERIEL, values, MaterielSQLiteOpenHelper.COLUMN_MATERIEL_ID + " = " +inmateriel.getId(), null);
		  System.out.println("Materiel update with id: " + inmateriel.getId());
	  }
	  
	  public void delete(Materiel inmateriel) {
	    long id = inmateriel.getId();
	    System.out.println("Materiel deleted with id: " + id);
	    database.delete(MaterielSQLiteOpenHelper.TABLE_MATERIEL, MaterielSQLiteOpenHelper.COLUMN_MATERIEL_ID
	        + " = " + id, null);
	  }

	  public List<Materiel> getAll() {
	    List<Materiel> comments = new ArrayList<Materiel>();

	    Cursor cursor = database.query(MaterielSQLiteOpenHelper.TABLE_MATERIEL,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	Materiel matos = cursorToMateriel(cursor);
	      comments.add(matos);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return comments;
	  }

	  

    
    
	  private Materiel cursorToMateriel(Cursor cursor) {
		  Materiel materiel = new Materiel();
		  materiel.setId(cursor.getLong(0));
		  materiel.setName(cursor.getString(1));
		  materiel.setSerialNumber(cursor.getString(2));
		  materiel.setDateAchat(cursor.getString(3));
		  materiel.setComment(cursor.getString(4));
		  materiel.setImagePath(cursor.getString(5));
	    return materiel;
	  }
	  
	  
	  public  ArrayAdapter<Materiel> getAdapter(Context context, List<Materiel>  values,DataFragment<Materiel> fragment){
		  return new MaterielArrayAdapter(context,values );
	  }
	  
	  public  Materiel getTestValue(){
	      
		  Materiel arrow = new Materiel();
	      arrow.setName("Poignée");
	      arrow.setSerialNumber("SN/000000000000000000");
	      arrow.setDateAchat("");
	      arrow.setComment("");
	      arrow.setImagePath("");
	      return arrow;
	  }

	  
	  
	} 
