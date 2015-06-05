package com.alv.db;

import java.util.List;

import com.alv.app.DataFragment;
import com.alv.db.arrow.Arrow;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

public abstract class DataSourceBase<T> {
	
	  protected SQLiteDatabase database;
	  protected DBSQLiteOpenHelper dbHelper;
	  protected ArrayAdapter<T> adapter;
	  
	public  DataSourceBase(Context context){
		 dbHelper = new DBSQLiteOpenHelper(context);
	}
	
	 public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
	}

	public void close() {
		 dbHelper.close();
	}
	
	public abstract T create(T data);
	public abstract void update(T data);
	public abstract void delete(T data);
	public abstract List<T> getAll();
	
	public abstract ArrayAdapter<T> getAdapter(Context context, List<T>  values, DataFragment<T> frag);
	
	public abstract  T getTestValue();
}
