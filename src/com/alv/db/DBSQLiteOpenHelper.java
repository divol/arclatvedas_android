package com.alv.db;

import com.alv.db.arrow.ArrowSQLiteOpenHelper;
import com.alv.db.charte.CharteSQLiteOpenHelper;
import com.alv.db.distance.DistanceSQLiteOpenHelper;
import com.alv.db.materiel.MaterielSQLiteOpenHelper;
import com.alv.db.tir.TirSQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
@see http://www.vogella.com/tutorials/AndroidSQLite/article.html
*/
	
public class DBSQLiteOpenHelper extends SQLiteOpenHelper {

	  private static final String DATABASE_NAME = "archerdata.db";
	  private static final int DATABASE_VERSION = 14;

	public DBSQLiteOpenHelper(Context context){
		 super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {

		ArrowSQLiteOpenHelper.onCreate(db);
		DistanceSQLiteOpenHelper.onCreate(db);
		MaterielSQLiteOpenHelper.onCreate(db);
		TirSQLiteOpenHelper.onCreate(db);
		CharteSQLiteOpenHelper.onCreate(db); //version 13
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		
			ArrowSQLiteOpenHelper.onUpgrade(db,oldVersion,newVersion);
			DistanceSQLiteOpenHelper.onUpgrade(db,oldVersion,newVersion);
			MaterielSQLiteOpenHelper.onUpgrade(db,oldVersion,newVersion);
			TirSQLiteOpenHelper.onUpgrade(db,oldVersion,newVersion);
			CharteSQLiteOpenHelper.onUpgrade(db,oldVersion,newVersion);

			
			//TODO migration vers une nouvelle table avec des nouvelles colonnes
		//ALTER TABLE {tableName} RENAME TO TempOldTable;
		//CREATE TABLE {tableName} (name TEXT, COLNew {type} DEFAULT {defaultValue}, qty INTEGER, rate REAL);
		//INSERT INTO {tableName} (name, qty, rate) SELECT name, qty, rate FROM TempOldTable;
		//DROP TABLE TempOldTable;


	}

}
