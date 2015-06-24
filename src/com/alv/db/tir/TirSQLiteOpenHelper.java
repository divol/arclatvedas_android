package com.alv.db.tir;


import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alv.db.distance.DistanceSQLiteOpenHelper;

public class TirSQLiteOpenHelper {

//	private long id;
//	private String location;
//	private String date;
//	private String distance;
//	private String comment;
//	private List<Score> scores;

	  public static final String TABLE_TIRS = "TIR";
	  public static final String COLUMN_TIR_ID = "_id";
	  public static final String COLUMN_TIR_LOCATION = "location";
	  public static final String COLUMN_TIR_DATE = "date";
	  public static final String COLUMN_TIR_DISTANCE = "distance";
	  public static final String COLUMN_TIR_COMMENT = "comment";

//		private long id;
//		private long idScore;
//		private List<Integer> v;
	 
		 
	  public static final String TABLE_SCORES = "SCORE";
	  public static final String COLUMN_SCORE_ID = "_id";
	  public static final String COLUMN_SCORE_IDTIR = "idTir";
	  public static final String COLUMN_SCORE_VOLEENUMERO = "voeenumero";
	  
	  public static final String COLUMN_SCORE_FLY = "fly";
	  public static final String COLUMN_SCORE_POINTS = "points";
	  
	  // Database creation sql statement
	  private static final String DATABASE_CREATE_TIR = "create table "
	      + TABLE_TIRS + "(" + COLUMN_TIR_ID
	      + " integer primary key autoincrement, " 
	      + COLUMN_TIR_LOCATION+ " text not null, "
	      + COLUMN_TIR_DATE+ " text not null, "
	       + COLUMN_TIR_DISTANCE+ " text not null, "
	      + COLUMN_TIR_COMMENT+ " text not null); ";
	  
	  private static final String DATABASE_CREATE_SCORE ="create table "
	      + TABLE_SCORES + "(" + COLUMN_SCORE_ID
	      + " integer primary key autoincrement, " 
	      + COLUMN_SCORE_IDTIR+ " integer, "
	      + COLUMN_SCORE_FLY+ " text not null, "
	      + COLUMN_SCORE_POINTS+ " text not null); "
	      
	      
	      ;

	
	
	public static void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE_TIR);
		db.execSQL(DATABASE_CREATE_SCORE);
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DistanceSQLiteOpenHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIRS);
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
		    onCreate(db);
	}

}