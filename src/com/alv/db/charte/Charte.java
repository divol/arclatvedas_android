package com.alv.db.charte;

import java.util.ArrayList;



import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

public class Charte implements Parcelable {
	private long id;
	private long length;
	private long hight;
	private long low;
	
	private ArrayList<Groupe> groupes;
	
	public Charte(){

		  groupes = new ArrayList<Groupe>();
	}

	public Charte(Parcel in){
		this();
        id = in.readLong();
        length = in.readLong();
        hight = in.readLong();
        low = in.readLong();
      in.readTypedList(groupes, Groupe.CREATOR);

	  
  }


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getLength() {
		return length;
	}


	public void setLength(long length) {
		this.length = length;
	}


	public long getHight() {
		return hight;
	}


	public void setHight(long hight) {
		this.hight = hight;
	}


	public long getLow() {
		return low;
	}


	public void setLow(long low) {
		this.low = low;
	}


	public ArrayList<Groupe> getGroupes() {
		return groupes;
	}


	public void setGroupes(ArrayList<Groupe> groupes) {
		this.groupes = groupes;
	}


	public ContentValues getValues(){
		  
		ContentValues values = new ContentValues();
		values.put(CharteSQLiteOpenHelper.COLUMN_CHARTE_LENGTH, length);
 		values.put(CharteSQLiteOpenHelper.COLUMN_CHARTE_HIGHT, hight); 
 		values.put(CharteSQLiteOpenHelper.COLUMN_CHARTE_LOW, low);  
		
		return values;
		
	}
	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeLong(id);
		dest.writeLong(length);
		dest.writeLong(hight);
		dest.writeLong(low);
	    dest.writeTypedList(groupes);

	}
	
	public static final Parcelable.Creator<Charte> CREATOR = new Parcelable.Creator<Charte>() {
		   public Charte createFromParcel(Parcel in) {
		       return new Charte(in); 
		   }

		   public Charte[] newArray(int size) {
		       return new Charte[size];
		   }
		};

		

}
