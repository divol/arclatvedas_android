package com.alv.db.distance;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DistanceSQLiteOpenHelper {

	
	  public static final String TABLE_DISTANCES = "DISTANCE";
	  public static final String COLUMN_DISTANCE_ID = "_id";
	  public static final String COLUMN_DISTANCE_NAME = "name";
	  public static final String COLUMN_DISTANCE_UNIT = "unit";
	  public static final String COLUMN_DISTANCE_COMMENT = "comment";

		 
		 
	  public static final String TABLE_HAUSSES = "HAUSSE";
	  public static final String COLUMN_HAUSSE_ID = "_id";
	  public static final String COLUMN_HAUSSE_IDDISTANCE = "iddistance";
	  public static final String COLUMN_HAUSSE_NAME = "name";
	  public static final String COLUMN_HAUSSE_HAUSSE = "hausse";


	  // Database creation sql statement
	  private static final String DATABASE_CREATE_DISTANCE = "create table "
	      + TABLE_DISTANCES + "(" + COLUMN_DISTANCE_ID
	      + " integer primary key autoincrement, " 
	      + COLUMN_DISTANCE_NAME+ " text not null, "
	      + COLUMN_DISTANCE_UNIT+ " text not null, "
	      + COLUMN_DISTANCE_COMMENT+ " text not null); ";
	  
	  private static final String DATABASE_CREATE_HAUSSE ="create table "
	      + TABLE_HAUSSES + "(" + COLUMN_HAUSSE_ID
	      + " integer primary key autoincrement, " 
	      + COLUMN_HAUSSE_IDDISTANCE+ " integer, "
	      + COLUMN_HAUSSE_NAME+ " text not null, "
	      + COLUMN_HAUSSE_HAUSSE+ " text not null); "
	      
	      
	      ;

	
	
	public static void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE_DISTANCE);
		db.execSQL(DATABASE_CREATE_HAUSSE);
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DistanceSQLiteOpenHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISTANCES);
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_HAUSSES);
		    onCreate(db);
	}

}
