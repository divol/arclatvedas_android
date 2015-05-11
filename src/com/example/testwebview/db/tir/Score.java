package com.example.testwebview.db.tir;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;

import com.example.testwebview.db.distance.DistanceSQLiteOpenHelper;
import com.example.testwebview.db.distance.Hausse;


import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;


public class Score implements Parcelable{
	
	
	public static int NOMBREMAX=6;
	private long id;
	private long idScore;
	private ArrayList<Integer> v;

	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdScore() {
		return idScore;
	}
	public void setIdScore(long idScore) {
		this.idScore = idScore;
	}
	public ArrayList<Integer> getV() {
		return v;
	}
	public void setV(ArrayList<Integer> v) {
		this.v = v;
	}

	public int getTotal(){
		int res=0;
		if (v != null){
		for (int val : v){
			if (val ==100){
				val =10;
			}
			res +=val;
		}
		}
		return res;
	}
	
	public Score(long idTir){
		v = new  ArrayList<Integer>();
		id=-1;
		idScore = idTir;
	}
	
	
	 public Score(Parcel in){
	        id = in.readLong();
	        idScore = in.readLong();
	        v=  in.readArrayList( Integer.class.getClassLoader());
	  }
	 
	 
	public void addScore(int score){
		if (v.size() <  NOMBREMAX){
			v.add(score);
		}
	}
	
	public int getScoreAt(int fleche){
		
		int res=-1;
		if (v != null){
			if (fleche <v.size()){
				res = v.get(fleche);
			}
		}
		return res;
	}
	
	
	
	public void deleteLast(){
		
		if (v.size() != 0){
			v.remove(v.size()-1);
		}
	}
	
	public static ArrayList<Integer> stringtov(String jsonarray){
		ArrayList<Integer> liste=null;
		
		System.out.println("stringtov="+jsonarray);
		try {
			JSONArray json = new JSONArray(jsonarray);
			liste = new  ArrayList<Integer>();
			for (int index=0 ; index<json.length();index++)
				liste.add(json.optInt(index));
		      }
			
		     catch (Exception e) {
		      e.printStackTrace();
		    }
		
		return liste;
	}
	
	public static String vtostring(ArrayList<Integer> liste){
		JSONArray arr = new JSONArray();
		for (Integer val: liste){
			arr.put(val.intValue());
		}
		System.out.println("vtostring="+arr.toString());
		return arr.toString();
	}
	
	public ContentValues getValues(){
		  
		ContentValues values = new ContentValues();
		
		values.put(TirSQLiteOpenHelper.COLUMN_SCORE_IDTIR, idScore);
		values.put(TirSQLiteOpenHelper.COLUMN_SCORE_FLY, vtostring(v));
		
		return values;
		
	}

	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeLong(idScore);
		dest.writeList(v);
	}
	
	public static final Parcelable.Creator<Score> CREATOR = new Parcelable.Creator<Score>() {
		   public Score createFromParcel(Parcel in) {
		       return new Score(in); 
		   }

		   public Score[] newArray(int size) {
		       return new Score[size];
		   }
		};
}
