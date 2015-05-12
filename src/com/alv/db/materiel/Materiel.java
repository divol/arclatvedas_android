package com.alv.db.materiel;



import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

public class Materiel implements Parcelable {

	 private long id;
	 private String name;
	 private String serialNumber;
	 private String dateAchat;
	 private String comment;
	 private String imagePath;
	 
	 


	 
	 
	 
	 
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
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getDateAchat() {
		return dateAchat;
	}
	public void setDateAchat(String dateAchat) {
		this.dateAchat = dateAchat;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	 public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
	
	public Materiel(){
		 
	 }
	 public Materiel(Parcel in){
	        id = in.readLong();
	        name = in.readString();
	        serialNumber = in.readString();
	        dateAchat = in.readString();
	        comment=in.readString();
	        imagePath=in.readString();

		  
	  }
	 
	  

		public ContentValues getValues(){
	  		  
			ContentValues values = new ContentValues();
			values.put(MaterielSQLiteOpenHelper.COLUMN_MATERIEL_NAME, name);
			values.put(MaterielSQLiteOpenHelper.COLUMN_MATERIEL_SERIALNUMBER, serialNumber); 
			values.put(MaterielSQLiteOpenHelper.COLUMN_MATERIEL_DATEACHAT, dateAchat);  
			values.put(MaterielSQLiteOpenHelper.COLUMN_MATERIEL_COMMENT, comment);  
			values.put(MaterielSQLiteOpenHelper.COLUMN_MATERIEL_IMAGE, imagePath);
			
			return values;
			
		}

		
		
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
	    dest.writeString(name);
	    dest.writeString(serialNumber);
	    dest.writeString(dateAchat);
	    dest.writeString(comment);
	    dest.writeString(imagePath);
	}
	
	
    


public static final Parcelable.Creator<Materiel> CREATOR = new Parcelable.Creator<Materiel>() {
   public Materiel createFromParcel(Parcel in) {
       return new Materiel(in); 
   }

   public Materiel[] newArray(int size) {
       return new Materiel[size];
   }
};


}
