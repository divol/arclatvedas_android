package com.example.testwebview.db;

import com.example.testwebview.db.arrow.ArrowSQLiteOpenHelper;
import com.example.testwebview.db.distance.DistanceSQLiteOpenHelper;
import com.example.testwebview.db.materiel.MaterielSQLiteOpenHelper;
import com.example.testwebview.db.tir.TirSQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
@see http://www.vogella.com/tutorials/AndroidSQLite/article.html
*/
	
public class DBSQLiteOpenHelper extends SQLiteOpenHelper {

	  private static final String DATABASE_NAME = "archerdata.db";
	  private static final int DATABASE_VERSION = 11;

	public DBSQLiteOpenHelper(Context context){
		 super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {

		ArrowSQLiteOpenHelper.onCreate(db);
		DistanceSQLiteOpenHelper.onCreate(db);
		MaterielSQLiteOpenHelper.onCreate(db);
		TirSQLiteOpenHelper.onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		ArrowSQLiteOpenHelper.onUpgrade(db,oldVersion,newVersion);
		DistanceSQLiteOpenHelper.onUpgrade(db,oldVersion,newVersion);
		MaterielSQLiteOpenHelper.onUpgrade(db,oldVersion,newVersion);
		TirSQLiteOpenHelper.onUpgrade(db,oldVersion,newVersion);

	}

}
