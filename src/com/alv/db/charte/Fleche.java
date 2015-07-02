package com.alv.db.charte;


import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

public class Fleche implements Parcelable{
	long id;
	String modele;
	String name;
	String surname;
	float grain;
    String spin;
    float diametreoutside;
    float taille;
    String fabricant;
    

    public Fleche(){
    	
    }
       
	public Fleche(Parcel in){
        id = in.readLong();
        modele = in.readString();
        name = in.readString();
        surname = in.readString();
        grain = in.readFloat();
        spin = in.readString();
        diametreoutside = in.readFloat();
        taille = in.readFloat();
        fabricant = in.readString();
  }
	
	
	@Override
    public String toString(){
    	
    	String str ="Modèle: "+modele;
    	str +="\nNom: "+name;
    	str +=" Spin: "+spin;
    	str +="\nTaille: "+taille;
    	str +=" Grain: "+grain;
    	str +=" Diam: "+diametreoutside;
    	str +="\nFabricant: "+fabricant;
    	
    	return  str;
    }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public float getGrain() {
		return grain;
	}
	public void setGrain(float grain) {
		this.grain = grain;
	}
	public String getSpin() {
		return spin;
	}
	public void setSpin(String spin) {
		this.spin = spin;
	}
	public float getDiametreoutside() {
		return diametreoutside;
	}
	public void setDiametreoutside(float diametreoutside) {
		this.diametreoutside = diametreoutside;
	}
	public float getTaille() {
		return taille;
	}
	public void setTaille(float taille) {
		this.taille = taille;
	}
	public String getFabricant() {
		return fabricant;
	}
	public void setFabricant(String fabricant) {
		this.fabricant = fabricant;
	}
	

	public ContentValues getValues(){
		  
		ContentValues values = new ContentValues();
		values.put(CharteSQLiteOpenHelper.COLUMN_FLECHE_MODELE, modele);
		values.put(CharteSQLiteOpenHelper.COLUMN_FLECHE_NAME, name);
		values.put(CharteSQLiteOpenHelper.COLUMN_FLECHE_SURNAME, surname);
		values.put(CharteSQLiteOpenHelper.COLUMN_FLECHE_GRAIN, grain);
		values.put(CharteSQLiteOpenHelper.COLUMN_FLECHE_SPIN, spin);
		values.put(CharteSQLiteOpenHelper.COLUMN_FLECHE_DIAMETRE, diametreoutside);
		values.put(CharteSQLiteOpenHelper.COLUMN_FLECHE_TAILLE, taille);
		values.put(CharteSQLiteOpenHelper.COLUMN_FLECHE_FABRICANT, fabricant);

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
		dest.writeString(modele);
		dest.writeString(name);
		dest.writeString(surname);
		dest.writeFloat(grain);
		dest.writeString(spin);
		dest.writeFloat(diametreoutside);
		dest.writeFloat(taille);
		dest.writeString(fabricant);

	}

	
	public static final Parcelable.Creator<Fleche> CREATOR = new Parcelable.Creator<Fleche>() {
		   public Fleche createFromParcel(Parcel in) {
		       return new Fleche(in); 
		   }

		   public Fleche[] newArray(int size) {
		       return new Fleche[size];
		   }
		};
		
		
}
