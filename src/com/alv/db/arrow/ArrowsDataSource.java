/**
 * 
 */
package com.alv.db.arrow;

import java.util.ArrayList;
import java.util.List;

import com.alv.app.ArrowArrayAdapter;
import com.alv.app.DataFragment;
import com.alv.db.DataSourceBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

/**
 * @author divol
 *
 */
public class ArrowsDataSource extends DataSourceBase<Arrow>{

	// Database fields
	  private String[] allColumns = { ArrowSQLiteOpenHelper.COLUMN_ID,
			  ArrowSQLiteOpenHelper.COLUMN_NAME,
			  ArrowSQLiteOpenHelper.COLUMN_FEATHER,
			  ArrowSQLiteOpenHelper.COLUMN_LENGTH,
			  ArrowSQLiteOpenHelper.COLUMN_POINT,
			  ArrowSQLiteOpenHelper.COLUMN_SPIN,
			  ArrowSQLiteOpenHelper.COLUMN_DATEACHAT,
			  ArrowSQLiteOpenHelper.COLUMN_COMMENT };

	  
	  
	  
	  public ArrowsDataSource(Context context) {
			super(context);
		}

	  
	  public Arrow create (Arrow inarrow){
		    ContentValues values =inarrow.getValues();

		    long insertId = database.insert(ArrowSQLiteOpenHelper.TABLE_ARROWS, null,
		        values);
		    Cursor cursor = database.query(ArrowSQLiteOpenHelper.TABLE_ARROWS,
		        allColumns, ArrowSQLiteOpenHelper.COLUMN_ID + " = " + insertId, null,
		        null, null, null);
		    cursor.moveToFirst();
		    Arrow newArrow = cursorToArrow(cursor);
		    cursor.close();
		    return newArrow;
		  
	  }
	 
	  
	  public void update(Arrow inarrow){
		  ContentValues values =inarrow.getValues();
		  database.update(ArrowSQLiteOpenHelper.TABLE_ARROWS, values, ArrowSQLiteOpenHelper.COLUMN_ID + " = " +inarrow.getId(), null);
		  System.out.println("Comment update with id: " + inarrow.getId());
	  }
	  
	  public void delete(Arrow arrow) {
	    long id = arrow.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(ArrowSQLiteOpenHelper.TABLE_ARROWS, ArrowSQLiteOpenHelper.COLUMN_ID
	        + " = " + id, null);
	  }

	  public List<Arrow> getAll() {
	    List<Arrow> comments = new ArrayList<Arrow>();

	    Cursor cursor = database.query(ArrowSQLiteOpenHelper.TABLE_ARROWS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Arrow arrow = cursorToArrow(cursor);
	      comments.add(arrow);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return comments;
	  }

	  

      
      
	  private Arrow cursorToArrow(Cursor cursor) {
		  Arrow arrow = new Arrow();
		  arrow.setId(cursor.getLong(0));
		  arrow.setName(cursor.getString(1));
		  arrow.setFeather(cursor.getString(2));
		  arrow.setLength(cursor.getDouble(3));
		  arrow.setPoint(cursor.getString(4));
		  arrow.setSpin(cursor.getInt(5));
		  arrow.setDateAchat(cursor.getString(6));
		  arrow.setComment(cursor.getString(7));

	    return arrow;
	  }
	  
	  
	  public  ArrayAdapter<Arrow> getAdapter(Context context, List<Arrow>  values,DataFragment<Arrow> fragment){
		  return new ArrowArrayAdapter(context,values );
	  }
	  
	  public  Arrow getTestValue(){
	      
	      Arrow arrow = new Arrow();
	      arrow.setName("arrow1");
	      arrow.setLength(31.5);
	      arrow.setSpin(300);
	      arrow.setFeather("spinwind");
	      arrow.setPoint("beiter 120");
	      arrow.setDateAchat("04/05/1965");
	      arrow.setComment("commenatire");
	      
	      return arrow;
	  }

	  
	  
	} 


