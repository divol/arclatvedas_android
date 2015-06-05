package com.alv.db.tir;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.content.ContentValues;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;


public class Score implements Parcelable{
	
	
	public static int NOMBREMAX=6;
	private long id;
	private long idScore;
	private ArrayList<Integer> v;
    private ArrayList<PointF> points;
	
	
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

	
	public ArrayList<PointF> getPoints() {
		return points;
	}
	public void setPoints(ArrayList<PointF> points) {
		this.points = points;
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
		points = new ArrayList<PointF>();
		id=-1;
		idScore = idTir;
	}
	
	
	 public Score(Parcel in){
	        id = in.readLong();
	        idScore = in.readLong();
	        v=  in.readArrayList( Integer.class.getClassLoader());
	        points = in.readArrayList(PointF.class.getClassLoader());
	  }
	 
	 
	public boolean addScore(int score){
		if (v.size() <  NOMBREMAX){
			v.add(score);
			points.add(new PointF(0,0));
			return true;
		}
		return false;
	}
	
	public boolean addScore(int score,PointF point){
		if (v.size() <  NOMBREMAX){
			v.add(score);
			points.add(new PointF(point.x,point.y));
			return true;
		}
		return false;
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
	
	public PointF getPointAt(int fleche){
		
		PointF res=new PointF(0,0);
		if (v != null){
			if (fleche <points.size()){
				res = points.get(fleche);
			}
		}
		return res;
	}
	
	public void deleteLast(){
		
		if (v.size() != 0){
			v.remove(v.size()-1);
		}
		if (points.size() != 0){
			points.remove(points.size()-1);
		}
	}
	
	public static ArrayList<Integer> stringtov(String jsonarray){
		ArrayList<Integer> liste=null;
		
		//System.out.println("stringtov="+jsonarray);
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
		//System.out.println("vtostring="+arr.toString());
		return arr.toString();
	}
	
	
	
	public static ArrayList<PointF> stringtopoints(String jsonarray){
		ArrayList<PointF> liste=null;
		
		//System.out.println("stringtov="+jsonarray);
		try {
			JSONArray json = new JSONArray(jsonarray);
			liste = new  ArrayList<PointF>();
			for (int index=0 ; index<json.length();index++){
				
				JSONObject jsonpoint = json.optJSONObject(index);
				
				PointF p = new PointF();
				p.set((float) jsonpoint.getDouble("x"),(float)jsonpoint.getDouble("y"));
				liste.add(p);
		      }
			}
		     catch (Exception e) {
		      e.printStackTrace();
		    }
		
		return liste;
	}

	
	
	public static String pointstostring(ArrayList<PointF> liste){
		JSONArray arr = new JSONArray();
		for (PointF val: liste){
			JSONObject jsonpoint = new JSONObject();
			try {
				jsonpoint.put("x", (double)val.x);
				jsonpoint.put("y", (double)val.y);

			} catch (JSONException e) {
				e.printStackTrace();
			}
			arr.put(jsonpoint);
		}
		//System.out.println("vtostring="+arr.toString());
		return arr.toString();
	}
	
	
	public ContentValues getValues(){
		  
		ContentValues values = new ContentValues();
		
		values.put(TirSQLiteOpenHelper.COLUMN_SCORE_IDTIR, idScore);
		values.put(TirSQLiteOpenHelper.COLUMN_SCORE_FLY, vtostring(v));
		values.put(TirSQLiteOpenHelper.COLUMN_SCORE_POINTS, pointstostring(points));
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
		dest.writeList(points);
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
