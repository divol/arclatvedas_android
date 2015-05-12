/**
 * 
 */
package com.alv.db.arrow;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author divol
 *
 */



public class Arrow implements Parcelable{
	  private long id;
	  private String name;
	  private double length;
	  private int spin;
	  private String feather;
	  private String point;
	  private String dateAchat;
	  private String comment;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getComment() {
	    return comment;
	  }

	  public void setComment(String comment) {
	    this.comment = comment;
	  }


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
	public void setLength(String length) {
		this.length =Double.valueOf(length) ;
	}
	public int getSpin() {
		return spin;
	}

	public void setSpin(int spin) {
		this.spin = spin;
	}
	public void setSpin(String spin) {
		this.spin = Integer.valueOf(spin);
	}
	public String getFeather() {
		return feather;
	}

	public void setFeather(String feather) {
		this.feather = feather;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(String date) {
		this.dateAchat = date;
	}

	
	public ContentValues getValues(){
		  		  
		ContentValues values = new ContentValues();
		//values.put(MySQLiteOpenHelper.COLUMN_ID, getId());
		values.put(ArrowSQLiteOpenHelper.COLUMN_NAME, getName());
		values.put(ArrowSQLiteOpenHelper.COLUMN_FEATHER, getFeather()); 
		values.put(ArrowSQLiteOpenHelper.COLUMN_LENGTH, getLength());  
		values.put(ArrowSQLiteOpenHelper.COLUMN_POINT, getPoint()); 
		values.put(ArrowSQLiteOpenHelper.COLUMN_SPIN, getSpin());  
		values.put(ArrowSQLiteOpenHelper.COLUMN_DATEACHAT, getDateAchat());  
		values.put(ArrowSQLiteOpenHelper.COLUMN_COMMENT, getComment());

		
		return values;
		
	}
	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return name +" "+ length;
	  }
	  
	  
	  public Arrow(){
		  
	  }
	  
	  //http://www.parcelabler.com
	  
	  public Arrow(Parcel in){
	        id = in.readLong();
	        name = in.readString();
	        length = in.readDouble();
	        spin = in.readInt();
	        feather = in.readString();
	        point = in.readString();
	        dateAchat=in.readString();
	        comment = in.readString();

		  
	  }
	  
	  @Override
      public int describeContents(){
          return 0;
      }
	  
	  @Override
      public void writeToParcel(Parcel dest, int flags) {
		  
		 
	        dest.writeLong(id);
	        dest.writeString(name);
	        dest.writeDouble(length);
	        dest.writeInt(spin);
	        dest.writeString(feather);
	        dest.writeString(point);
	        dest.writeString(dateAchat);
	        dest.writeString(comment);

      }
      public static final Parcelable.Creator<Arrow> CREATOR = new Parcelable.Creator<Arrow>() {
          public Arrow createFromParcel(Parcel in) {
              return new Arrow(in); 
          }

          public Arrow[] newArray(int size) {
              return new Arrow[size];
          }
      };

      
      
}
