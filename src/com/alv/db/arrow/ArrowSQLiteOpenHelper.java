/**
 * 
 */
package com.alv.db.arrow;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
/**
 * @author divol
 *
 */
public class ArrowSQLiteOpenHelper  {
	
	
	  public static final String TABLE_ARROWS = "arrow";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_NAME = "name";
	  public static final String COLUMN_LENGTH = "length";
	  public static final String COLUMN_SPIN = "spin";
	  public static final String COLUMN_FEATHER = "feather";
	  public static final String COLUMN_POINT = "point";
	  public static final String COLUMN_COMMENT = "comment";
	  public static final String COLUMN_DATEACHAT = "dateachat";

	  

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_ARROWS + "(" + COLUMN_ID
	      + " integer primary key autoincrement, " 
	      + COLUMN_NAME+ " text not null, "
	      + COLUMN_LENGTH+ " real, "
	      + COLUMN_SPIN+ " integer, "
	      + COLUMN_FEATHER+ " text not null, "
	      + COLUMN_POINT+ " text not null, "
	      + COLUMN_DATEACHAT+ " text not null, "
	      + COLUMN_COMMENT+ " text not null);";

	
	
	public static void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(ArrowSQLiteOpenHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARROWS);
		    onCreate(db);
	}

}
