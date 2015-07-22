package com.alv.db.tir;

import java.util.ArrayList;


import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;


public class Tir implements Parcelable{

	private long id;
	private String location;
	private String date;
	private String distance;
	private String comment;
	private int blasonType;
	
	private ArrayList<Score> scores;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getDistance() {
		return distance;
	}



	public void setDistance(String distance) {
		this.distance = distance;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getBlasonType() {
		return blasonType;
	}
	public void setBlasonType(int blasonType) {
		this.blasonType = blasonType;
	}


	public ArrayList<Score> getScores() {
		return scores;
	}



	public void setScores(ArrayList<Score> scores) {
		this.scores = scores;
	}


public int getTotal(){
	int tot=0;
	for (Score s: scores){
		tot +=s.getTotal();
	}
	return tot;
}
	public Tir(){
		
	}

	public Tir(Parcel in){
        id = in.readLong();
        location = in.readString();
        date = in.readString();
        distance = in.readString();
        scores = new ArrayList<Score>();
        in.readTypedList(scores, Score.CREATOR);
        comment=in.readString();
        blasonType = in.readInt();

	  
  }

	@Override
	public int describeContents() {
		return 0;
	}


	public ContentValues getValues(){
		  
		ContentValues values = new ContentValues();
		values.put(TirSQLiteOpenHelper.COLUMN_TIR_LOCATION, location);
 		values.put(TirSQLiteOpenHelper.COLUMN_TIR_DATE, date); 
 		values.put(TirSQLiteOpenHelper.COLUMN_TIR_DISTANCE, distance);  
 		values.put(TirSQLiteOpenHelper.COLUMN_TIR_COMMENT, comment); 
 		values.put(TirSQLiteOpenHelper.COLUMN_TIR_BLASON, blasonType); 

		return values;
		
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
				
		dest.writeLong(id);
	    dest.writeString(location);
	    dest.writeString(date);
	    dest.writeString(distance);
	    dest.writeTypedList(scores);
	    dest.writeString(comment);
	    dest.writeInt(blasonType);

	}
	

	public static final Parcelable.Creator<Tir> CREATOR = new Parcelable.Creator<Tir>() {
		   public Tir createFromParcel(Parcel in) {
		       return new Tir(in); 
		   }

		   public Tir[] newArray(int size) {
		       return new Tir[size];
		   }
		};

	
}
