package com.alv.db.materiel;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alv.db.distance.DistanceSQLiteOpenHelper;

public class MaterielSQLiteOpenHelper {


public static final String TABLE_MATERIEL = "MATERIEL";
public static final String COLUMN_MATERIEL_ID = "_id";
public static final String COLUMN_MATERIEL_NAME = "name";
public static final String COLUMN_MATERIEL_SERIALNUMBER = "serialnumber";
public static final String COLUMN_MATERIEL_DATEACHAT = "dateAchat";
public static final String COLUMN_MATERIEL_COMMENT = "comment";
public static final String COLUMN_MATERIEL_IMAGE ="imagepath";
	 
	 
public static final String TABLE_HAUSSES = "HAUSSE";
public static final String COLUMN_HAUSSE_ID = "_id";
public static final String COLUMN_HAUSSE_IDDISTANCE = "iddistance";
public static final String COLUMN_HAUSSE_NAME = "name";
public static final String COLUMN_HAUSSE_HAUSSE = "hausse";


// Database creation sql statement
private static final String DATABASE_CREATE_MATERIEL = "create table "
    + TABLE_MATERIEL + "(" + COLUMN_MATERIEL_ID
    + " integer primary key autoincrement, " 
    + COLUMN_MATERIEL_NAME+ " text not null, "
    + COLUMN_MATERIEL_SERIALNUMBER+ " text not null, "
    + COLUMN_MATERIEL_DATEACHAT+ " text not null, "
    + COLUMN_MATERIEL_COMMENT+ " text not null," 
    +  COLUMN_MATERIEL_IMAGE+ " text not null )";




public static void onCreate(SQLiteDatabase db) {
	// TODO Auto-generated method stub
	db.execSQL(DATABASE_CREATE_MATERIEL);
}

public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	Log.w(DistanceSQLiteOpenHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIEL);
	    onCreate(db);
}
}