package com.alv.db.distance;

import java.util.ArrayList;
import java.util.List;



import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

public class Distance implements Parcelable {

	 private long id;
	 private String name;
	 private String unit;
	 private String comment;
	 
	 private List<Hausse> hausses;

	 public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<Hausse> getHausses() {
		return hausses;
	}
	public void setHausses(List<Hausse> hausses) {
		this.hausses = hausses;
	}





	 
	 public Distance(){
		 
	 }
	 public Distance(Parcel in){
	        id = in.readLong();
	        name = in.readString();
	        unit = in.readString();
	        comment=in.readString();
	        hausses = new ArrayList<Hausse>();
	      in.readTypedList(hausses, Hausse.CREATOR);

		  
	  }
	 
	  

		public ContentValues getValues(){
	  		  
			ContentValues values = new ContentValues();
			values.put(DistanceSQLiteOpenHelper.COLUMN_DISTANCE_NAME, name);
			values.put(DistanceSQLiteOpenHelper.COLUMN_DISTANCE_UNIT, unit); 
			values.put(DistanceSQLiteOpenHelper.COLUMN_DISTANCE_COMMENT, comment);  

			
			return values;
			
		}

		
		
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeLong(id);
	    dest.writeString(name);
	    dest.writeString(unit);
	    dest.writeTypedList(hausses);
	    dest.writeString(comment);
	}
	
	
    


public static final Parcelable.Creator<Distance> CREATOR = new Parcelable.Creator<Distance>() {
   public Distance createFromParcel(Parcel in) {
       return new Distance(in); 
   }

   public Distance[] newArray(int size) {
       return new Distance[size];
   }
};




}
