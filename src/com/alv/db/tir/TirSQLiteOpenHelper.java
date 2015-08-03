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
	  public static final String COLUMN_TIR_BLASON = "blasontype";


//		private long id;
//		private long idScore;
//		private List<Integer> v;
	 
		 
	  public static final String TABLE_SCORES = "SCORE";
	  public static final String COLUMN_SCORE_ID = "_id";
	  public static final String COLUMN_SCORE_IDTIR = "idTir";
	//  public static final String COLUMN_SCORE_VOLEENUMERO = "voeenumero";
	  
	  public static final String COLUMN_SCORE_FLY = "fly";
	  public static final String COLUMN_SCORE_POINTS = "points";
	  public static final String COLUMN_SCORE_ZONES = "zones";
	  
	  // Database creation sql statement
	  private static final String DATABASE_CREATE_TIR = "create table "
	      + TABLE_TIRS + "(" + COLUMN_TIR_ID
	      + " integer primary key autoincrement, " 
	      + COLUMN_TIR_LOCATION+ " text not null, "
	      + COLUMN_TIR_DATE+ " text not null, "
	       + COLUMN_TIR_DISTANCE+ " text not null, "
	      + COLUMN_TIR_COMMENT+ " text not null," 
	      + COLUMN_TIR_BLASON+ " integer DEFAULT 0 "+
	      		"); ";
	  
	  private static final String DATABASE_CREATE_SCORE ="create table "
	      + TABLE_SCORES + "(" + COLUMN_SCORE_ID
	      + " integer primary key autoincrement, " 
	      + COLUMN_SCORE_IDTIR+ " integer, "
	      + COLUMN_SCORE_FLY+ " text not null, "
	       + COLUMN_SCORE_POINTS+ " text not null, "
	      + COLUMN_SCORE_ZONES+ " text not null); "
	      
	      
	      ;

	
	
	public static void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE_TIR);
		db.execSQL(DATABASE_CREATE_SCORE);
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		    // 13 premiere version playstore, gestion meilleur de l'evolution du schemat, avant ... désolé 
		    if (oldVersion == 13){
		    	// ajout du champ "blason" en v. 14
				//TODO migration vers une nouvelle table avec des nouvelles colonnes
		    	
		    	 db.execSQL("ALTER TABLE " + TABLE_TIRS + " RENAME TO TempOld"+TABLE_TIRS);
		    	 db.execSQL(DATABASE_CREATE_TIR);
		    	 db.execSQL("INSERT INTO " + TABLE_TIRS + " ("+COLUMN_TIR_ID+","+COLUMN_TIR_LOCATION+","+COLUMN_TIR_DATE+","+COLUMN_TIR_DISTANCE+","+COLUMN_TIR_COMMENT+   ") SELECT " +COLUMN_TIR_ID+","+COLUMN_TIR_LOCATION+","+COLUMN_TIR_DATE+","+COLUMN_TIR_DISTANCE+","+COLUMN_TIR_COMMENT+ " FROM TempOld"+TABLE_TIRS);
		    	 db.execSQL("DROP TABLE TempOld"+TABLE_TIRS);

		    	 
		    	 // ajout des zones de score (mono, bi, tristop et de la position ABCD...)
		    	 db.execSQL("ALTER TABLE " + TABLE_SCORES + " RENAME TO TempOld"+TABLE_SCORES);
		    	 db.execSQL(DATABASE_CREATE_SCORE);
		    	 //db.execSQL("INSERT INTO " + TABLE_SCORES + " ("+COLUMN_SCORE_ID+","+COLUMN_SCORE_IDTIR+","+COLUMN_SCORE_VOLEENUMERO+","+COLUMN_SCORE_FLY+","+COLUMN_SCORE_POINTS+   ") SELECT " +COLUMN_SCORE_ID+","+COLUMN_SCORE_IDTIR+","+COLUMN_SCORE_VOLEENUMERO+","+COLUMN_SCORE_FLY+","+COLUMN_SCORE_POINTS+ " FROM TempOld"+TABLE_SCORES);
		    	 db.execSQL("INSERT INTO " + TABLE_SCORES + " ("+COLUMN_SCORE_ID+","+COLUMN_SCORE_IDTIR+","+COLUMN_SCORE_FLY+","+COLUMN_SCORE_POINTS+   ") SELECT " +COLUMN_SCORE_ID+","+COLUMN_SCORE_IDTIR+","+COLUMN_SCORE_FLY+","+COLUMN_SCORE_POINTS+ " FROM TempOld"+TABLE_SCORES);

		    	 db.execSQL("DROP TABLE TempOld"+TABLE_SCORES);
		    	 
		    	 
		    	 
				//ALTER TABLE {tableName} RENAME TO TempOldTable;
				//CREATE TABLE {tableName} (name TEXT, COLNew {type} DEFAULT {defaultValue}, qty INTEGER, rate REAL);
				//INSERT INTO {tableName} (name, qty, rate) SELECT name, qty, rate FROM TempOldTable;
				//DROP TABLE TempOldTable;

		    }else {
		    	if (oldVersion < 13 ){
		    		Log.w(DistanceSQLiteOpenHelper.class.getName(),
		    		        "Upgrading database from version " + oldVersion + " to "
		    		            + newVersion + ", which will destroy all old data");
		    		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIRS);
		    		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
		    		    onCreate(db);

		    	}
		    	
		    }

		    
		    
	}

}