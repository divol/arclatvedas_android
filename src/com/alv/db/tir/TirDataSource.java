package com.alv.db.tir;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

import com.alv.app.DataFragment;
import com.alv.app.TirArrayAdapter;
import com.alv.db.DataSourceBase;

public class TirDataSource extends DataSourceBase<Tir>{


	private String[] allColumnsTir = {TirSQLiteOpenHelper.COLUMN_TIR_ID,
			TirSQLiteOpenHelper.COLUMN_TIR_LOCATION	,
			TirSQLiteOpenHelper.COLUMN_TIR_DATE,
			TirSQLiteOpenHelper.COLUMN_TIR_DISTANCE,
			TirSQLiteOpenHelper.COLUMN_TIR_COMMENT,
			TirSQLiteOpenHelper.COLUMN_TIR_BLASON};


	  private String[] allColumnsScore = {TirSQLiteOpenHelper.COLUMN_SCORE_ID,
			  TirSQLiteOpenHelper.COLUMN_SCORE_IDTIR	,
			  TirSQLiteOpenHelper.COLUMN_SCORE_FLY,
			  TirSQLiteOpenHelper.COLUMN_SCORE_POINTS,
			  TirSQLiteOpenHelper.COLUMN_SCORE_ZONES};
	  
	public TirDataSource(Context context) {
		super(context);
	}

	@Override
	public Tir create(Tir data) {
		ContentValues values =data.getValues();

	    long insertId = database.insert(TirSQLiteOpenHelper.TABLE_TIRS, null,
	        values);
	    Cursor cursor = database.query(TirSQLiteOpenHelper.TABLE_TIRS,
	    		allColumnsTir, TirSQLiteOpenHelper.COLUMN_TIR_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Tir newTir = cursorToTir(cursor);
	    cursor.close();
	    
	    newTir.setScores(makeNewScoreEntry(newTir)) ;
	    return newTir;
	}


	@Override
	public void update(Tir data) {
		  ContentValues values =data.getValues();
		  database.update(TirSQLiteOpenHelper.TABLE_TIRS, values, TirSQLiteOpenHelper.COLUMN_TIR_ID + " = " +data.getId(), null);
		  System.out.println("Tir update with id: " + data.getId());
		  System.out.println("Tir update with getBlasonType: " + data.getBlasonType());
		  ArrayList<Score> scores = data.getScores();
		  
		  if (scores != null){
		  for (Score score : scores){
			  if ( score != null){
				  if (score.getId() == -1){
					  createScore(score);
				  }else{
					  updateScore(score);
				  }
			  }
			  
		  }
		  }
		  System.out.println("scores in tir update with id: " + data.getId());
		
	}

	@Override
	public void delete(Tir data) {
	    long id = data.getId();
	    
	    deleteAllScores(id);
	    
	    System.out.println("tir deleted with id: " + id);
	    database.delete(TirSQLiteOpenHelper.TABLE_TIRS, TirSQLiteOpenHelper.COLUMN_TIR_ID
	        + " = " + id, null);
		
	}

	@Override
	public List<Tir> getAll() {
	    List<Tir> tirs = new ArrayList<Tir>();

	    Cursor cursor = database.query(TirSQLiteOpenHelper.TABLE_TIRS,
	    		allColumnsTir, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	Tir tir = cursorToTir(cursor);
	    	//dois je charger les hausses ici ???
	    	tir.setScores(getAllScores(tir.getId()));
	    	tirs.add(tir);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    
	    
	    return tirs;
	}

    
	  private Tir cursorToTir(Cursor cursor) {
		  Tir tir = new Tir();
		  tir.setId(cursor.getLong(0));
		  tir.setLocation(cursor.getString(1));
		  tir.setDate(cursor.getString(2));
		  tir.setDistance(cursor.getString(3));
		  tir.setComment(cursor.getString(4));
		  tir.setBlasonType(cursor.getInt(5));


	    return tir;
	  }

	
	@Override
	public ArrayAdapter<Tir> getAdapter(Context context, List<Tir> values,DataFragment<Tir> fragment) {
		
		return new TirArrayAdapter(context,values,fragment );
	}

	@Override
	public Tir getTestValue() {
		Tir tir = new Tir();
		tir.setDistance("70");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
		String d =sdf.format(new Date()); 

		tir.setDate(d);
		tir.setLocation("");
		tir.setComment("");
		tir.setBlasonType(0);
		return tir;
	}
	
	
 
	public Score createScore(Score data) {
		
	    ContentValues values =data.getValues();

	    long insertId = database.insert(TirSQLiteOpenHelper.TABLE_SCORES, null,
	        values);
	    Cursor cursor = database.query(TirSQLiteOpenHelper.TABLE_SCORES,
	    		allColumnsScore, TirSQLiteOpenHelper.COLUMN_SCORE_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Score newScore = cursorToScore(cursor);
	    cursor.close();
	    return newScore;

		
	}
	
	
	public void updateScore(Score data){
		  ContentValues values =data.getValues();
		  database.update(TirSQLiteOpenHelper.TABLE_SCORES, values, TirSQLiteOpenHelper.COLUMN_SCORE_ID + " = " +data.getId(), null);
		  System.out.println("data update with id: " + data.getId());

	}
	
	public void deleteScore(Score data) {
	    long id = data.getId();
	    
	    System.out.println("Score deleted with id: " + id);
	    database.delete(TirSQLiteOpenHelper.TABLE_SCORES, TirSQLiteOpenHelper.COLUMN_SCORE_ID
	        + " = " + id, null);
		
	}
	
	
	  public void deleteAllScores(long idTir) {
		  
		    System.out.println("all score deleted with idtir: " + idTir);
		    database.delete(TirSQLiteOpenHelper.TABLE_SCORES, TirSQLiteOpenHelper.COLUMN_SCORE_IDTIR
		        + " = " + idTir, null);
		  }

	  
	  
	  public ArrayList<Score> getAllScores(long idtir) {
		  ArrayList<Score> scores = new ArrayList<Score>();

		    Cursor cursor = database.query(TirSQLiteOpenHelper.TABLE_SCORES,
		    		allColumnsScore, TirSQLiteOpenHelper.COLUMN_SCORE_IDTIR + " = " + idtir, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	Score score = cursorToScore(cursor);
		    	scores.add(score);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return scores;
		  }

	
	private Score cursorToScore(Cursor cursor) {
		Score score = new Score(0);
		score.setId(cursor.getLong(0));
		score.setIdScore (cursor.getLong(1));
		score.setV( Score.stringtov(cursor.getString(2)));
		score.setPoints( Score.stringtopoints(cursor.getString(3)));
		score.setZones(Score.stringtopoint(cursor.getString(4)));
	    return score;
	  }
	
	
	private ArrayList<Score> makeNewScoreEntry(Tir newTir) {
		  
		  Score score ;
		  ArrayList<Score> scores = new ArrayList<Score>();
		  
		  score = new Score(newTir.getId());
		  score = createScore(score);
		  scores.add(score);
		  
		  
		  return scores;

		  
	}

	
}
