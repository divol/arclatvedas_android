package com.alv.db.charte;

import java.util.ArrayList;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

public class Groupe implements Parcelable{

	private long id;
	private String name;
	private ArrayList<Fleche> fleches;
	
	
	public Groupe(){
        fleches = new ArrayList<Fleche>();

	  
  }
	
	public Groupe(Parcel in){
		this();
        id = in.readLong();
        name = in.readString();
      in.readTypedList(fleches, Fleche.CREATOR);

	  
  }
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

	public ArrayList<Fleche> getFleches() {
		return fleches;
	}

	public void setFleches(ArrayList<Fleche> fleches) {
		this.fleches = fleches;
	}

	
	public ContentValues getValues(){
		  
		ContentValues values = new ContentValues();
		values.put(CharteSQLiteOpenHelper.COLUMN_GROUPE_NAME, name);
		
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
		dest.writeTypedList(fleches);

	}

	
	public static final Parcelable.Creator<Groupe> CREATOR = new Parcelable.Creator<Groupe>() {
		   public Groupe createFromParcel(Parcel in) {
		       return new Groupe(in); 
		   }

		   public Groupe[] newArray(int size) {
		       return new Groupe[size];
		   }
		};
		
		
}
