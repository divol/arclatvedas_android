package com.alv.db.distance;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;


public class Hausse implements Parcelable{
	 private long id;
	 private long idDistance;
	 private String name;
	 private String hausse;
	 
	 
	 public Hausse(){
		 
	 }
	 public Hausse(Parcel in){
	        id = in.readLong();
	        idDistance = in.readLong();
	        name = in.readString();
	        hausse=in.readString();
		  
	  }
	
	 
	 
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getIdDistance() {
			return idDistance;
		}

		public void setIdDistance(long idDistance) {
			this.idDistance = idDistance;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getHausse() {
			return hausse;
		}

		public void setHausse(String hausse) {
			this.hausse = hausse;
		}

		public ContentValues getValues(){
	  		  
			ContentValues values = new ContentValues();
			
			values.put(DistanceSQLiteOpenHelper.COLUMN_HAUSSE_IDDISTANCE, idDistance);
			values.put(DistanceSQLiteOpenHelper.COLUMN_HAUSSE_NAME, name);
			values.put(DistanceSQLiteOpenHelper.COLUMN_HAUSSE_HAUSSE, hausse); 
			
			return values;
			
		}
	
		
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeLong(idDistance);
	    dest.writeString(name);
	    dest.writeString(hausse);
		
	}
	
	public static final Parcelable.Creator<Hausse> CREATOR = new Parcelable.Creator<Hausse>() {
		   public Hausse createFromParcel(Parcel in) {
		       return new Hausse(in); 
		   }

		   public Hausse[] newArray(int size) {
		       return new Hausse[size];
		   }
		};



}