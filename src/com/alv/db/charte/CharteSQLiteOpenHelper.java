package com.alv.db.charte;


import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alv.db.distance.DistanceSQLiteOpenHelper;

public class CharteSQLiteOpenHelper {
//	private long id;
//	private long length;
//	private long hight;
//	private long low;
//	
//	private ArrayList<Groupe> groupes;

	
	  public static final String TABLE_CHARTE = "CHARTE";
	  public static final String COLUMN_CHARTE_ID = "_id";
	  public static final String COLUMN_CHARTE_LENGTH = "length";
	  public static final String COLUMN_CHARTE_HIGHT = "hight";
	  public static final String COLUMN_CHARTE_LOW = "low";

	  
	  
	  private static final String DATABASE_CREATE_CHARTE = "create table "
		      + TABLE_CHARTE + "(" + COLUMN_CHARTE_ID
		      + " integer primary key autoincrement, " 
		      + COLUMN_CHARTE_LENGTH+ " integer not null, "
		      + COLUMN_CHARTE_HIGHT+ " integer not null, "
		       + COLUMN_CHARTE_LOW+ " integer not null "
		      + "); ";
	  
	  
	
	  public static final String TABLE_JOINCHARTEGROUPE = "CHARTEGROUPE";
	  public static final String COLUMN_CHARTEGROUPE_ID = "_id";
	  public static final String COLUMN_CHARTEGROUPE_CHARTE_ID = "idcharte";
	  public static final String COLUMN_CHARTEGROUPE_GROUPE_ID = "idgroupe";


	  
	  private static final String DATABASE_CREATE_CHARTEGROUPE = "create table "
		      + TABLE_JOINCHARTEGROUPE + "(" + COLUMN_CHARTEGROUPE_ID
		      + " integer primary key autoincrement, " 
		      + COLUMN_CHARTEGROUPE_CHARTE_ID+ " integer not null, "
		      + COLUMN_CHARTEGROUPE_GROUPE_ID+ " integer not null "
		       
		      + "); ";

	  
	  
//	private long id;
//	private String name;
//	private ArrayList<Fleche> fleches;
	
	
	  public static final String TABLE_GROUPE = "GROUPE";
	  public static final String COLUMN_GROUPE_ID = "_id";
	  public static final String COLUMN_GROUPE_NAME = "name";

	
	  private static final String DATABASE_CREATE_GROUPE = "create table "
		      + TABLE_GROUPE + "(" + COLUMN_GROUPE_ID
		      + " integer primary key autoincrement, " 
		      + COLUMN_GROUPE_NAME+ " text not null "
		      
		       
		      + "); ";

	  
	  
	  
	  public static final String TABLE_JOINGROUPEFLECHE = "GROUPEFLECHE";
	  public static final String COLUMN_GROUPEFLECHE_ID = "_id";
	  public static final String COLUMN_GROUPEFLECHE_GROUPE_ID = "idgroupe";
	  public static final String COLUMN_GROUPEFLECHE_FLECHE_ID = "idfleche";

	  private static final String DATABASE_CREATE_GROUPEFLECHE = "create table "
		      + TABLE_JOINGROUPEFLECHE + "(" + COLUMN_GROUPEFLECHE_ID
		      + " integer primary key autoincrement, " 
		      + COLUMN_GROUPEFLECHE_GROUPE_ID+ " integer not null, "
		      + COLUMN_GROUPEFLECHE_FLECHE_ID+ " integer not null "
		       
		      + "); ";
	  
	  
//	String modele;
//	String name;
//	String surname;
//	String grain;
//    String spin;
//    float diametreoutside;
//    float taille;
//    String fabricant;

	  
	  public static final String TABLE_FLECHE = "FLECHE";
	  public static final String COLUMN_FLECHE_ID = "_id";
	  public static final String COLUMN_FLECHE_MODELE = "modele";
	  public static final String COLUMN_FLECHE_NAME = "name";
	  public static final String COLUMN_FLECHE_SURNAME = "surname";
	  public static final String COLUMN_FLECHE_GRAIN = "grain";
	  public static final String COLUMN_FLECHE_SPIN= "spin";
	  public static final String COLUMN_FLECHE_DIAMETRE= "diametreoutside";
	  public static final String COLUMN_FLECHE_TAILLE= "taille";
	  public static final String COLUMN_FLECHE_FABRICANT= "fabricant";


	  
	  private static final String DATABASE_CREATE_FLECHE = "create table "
		      + TABLE_FLECHE + "(" + COLUMN_FLECHE_ID
		      + " integer primary key autoincrement, " 
		      + COLUMN_FLECHE_MODELE+ " text not null, "
		      + COLUMN_FLECHE_NAME+ " text , "
		       + COLUMN_FLECHE_SURNAME+ " text , "
		       + COLUMN_FLECHE_GRAIN+ " real , "
		        + COLUMN_FLECHE_SPIN+ " text , "
		        + COLUMN_FLECHE_DIAMETRE+ " real , "
		        + COLUMN_FLECHE_TAILLE+ " real , "
		         + COLUMN_FLECHE_FABRICANT+ " text "
		      + "); ";

	  
		public static void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE_CHARTE);
			db.execSQL(DATABASE_CREATE_CHARTEGROUPE);
			db.execSQL(DATABASE_CREATE_GROUPE);
			db.execSQL(DATABASE_CREATE_GROUPEFLECHE);
			db.execSQL(DATABASE_CREATE_FLECHE);
		}

		public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(DistanceSQLiteOpenHelper.class.getName(),
			        "Upgrading database from version " + oldVersion + " to "
			            + newVersion + ", which will destroy all old data");
			    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARTE);
			    db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOINCHARTEGROUPE);
			    db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPE);
			    db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOINGROUPEFLECHE);
			    db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLECHE);
			    onCreate(db);
		}  
}
