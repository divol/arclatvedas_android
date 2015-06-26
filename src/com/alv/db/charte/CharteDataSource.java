package com.alv.db.charte;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

import com.alv.app.DataFragment;
import com.alv.db.DataSourceBase;

public class CharteDataSource extends DataSourceBase<Charte> {


	  
	private String[] allColumnsCharte = {CharteSQLiteOpenHelper.COLUMN_CHARTE_ID,
			CharteSQLiteOpenHelper.COLUMN_CHARTE_LENGTH	,
			CharteSQLiteOpenHelper.COLUMN_CHARTE_HIGHT,
			CharteSQLiteOpenHelper.COLUMN_CHARTE_LOW};


	  private String[] allColumnsCharteGroupe = {CharteSQLiteOpenHelper.COLUMN_CHARTEGROUPE_ID,
			  CharteSQLiteOpenHelper.COLUMN_CHARTEGROUPE_CHARTE_ID	,
			  CharteSQLiteOpenHelper.COLUMN_CHARTEGROUPE_GROUPE_ID};
	  
	
	  
	  private String[] allColumnsGroupe = {CharteSQLiteOpenHelper.COLUMN_GROUPE_ID,
				CharteSQLiteOpenHelper.COLUMN_GROUPE_NAME	,
				
				};


		  private String[] allColumnsGroupeFleche = {CharteSQLiteOpenHelper.COLUMN_GROUPEFLECHE_ID,
				  CharteSQLiteOpenHelper.COLUMN_GROUPEFLECHE_GROUPE_ID	,
				  CharteSQLiteOpenHelper.COLUMN_GROUPEFLECHE_FLECHE_ID};
		
		  

		  
		  private String[] allColumnsFleche = {CharteSQLiteOpenHelper.COLUMN_FLECHE_ID,
				  CharteSQLiteOpenHelper.COLUMN_FLECHE_MODELE,
				  CharteSQLiteOpenHelper.COLUMN_FLECHE_NAME,
				  CharteSQLiteOpenHelper.COLUMN_FLECHE_SURNAME,
				  CharteSQLiteOpenHelper.COLUMN_FLECHE_GRAIN,
				  CharteSQLiteOpenHelper.COLUMN_FLECHE_SPIN,
				  CharteSQLiteOpenHelper.COLUMN_FLECHE_DIAMETRE,
				  CharteSQLiteOpenHelper.COLUMN_FLECHE_TAILLE,
				  CharteSQLiteOpenHelper.COLUMN_FLECHE_FABRICANT
				  };
		  
		  
		  
		  
		  
	public CharteDataSource(Context context) {
		super(context);
	}

	
	@Override
	public Charte create(Charte data) {
		ContentValues values =data.getValues();

	    long insertId = database.insert(CharteSQLiteOpenHelper.TABLE_CHARTE, null,
	        values);
	    Cursor cursor = database.query(CharteSQLiteOpenHelper.TABLE_CHARTE,
	    		allColumnsCharte, CharteSQLiteOpenHelper.COLUMN_CHARTE_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Charte newCharte = cursorToCharte(cursor);
	    cursor.close();
	    

		return newCharte;
	}

	@Override
	public void update(Charte data) {


		 ContentValues values =data.getValues();
		  database.update(CharteSQLiteOpenHelper.TABLE_CHARTE, values, CharteSQLiteOpenHelper.COLUMN_CHARTE_ID + " = " +data.getId(), null);
		 // System.out.println("Charte update with id: " + data.getId());
		  
		  
	}

	@Override
	public void delete(Charte data) {
		long id = data.getId();
	    
	  //  System.out.println("Charte deleted with id: " + id);
	    
	    database.delete(CharteSQLiteOpenHelper.TABLE_JOINCHARTEGROUPE, CharteSQLiteOpenHelper.COLUMN_CHARTEGROUPE_CHARTE_ID
		        + " = " + id, null);

	    
	    database.delete(CharteSQLiteOpenHelper.TABLE_CHARTE, CharteSQLiteOpenHelper.COLUMN_CHARTE_ID
	        + " = " + id, null);
		
	}

	@Override
	public List<Charte> getAll() {

	    List<Charte> chartes = new ArrayList<Charte>();

	    Cursor cursor = database.query(CharteSQLiteOpenHelper.TABLE_CHARTE,
	    		allColumnsCharte, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	Charte charte = cursorToCharte(cursor);
	    	chartes.add(charte);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    
	    return chartes;

	}

	@Override
	public ArrayAdapter<Charte> getAdapter(Context context,
			List<Charte> values, DataFragment<Charte> frag) {
		
		
		return null;
	}

	
	 private Charte cursorToCharte(Cursor cursor) {
		 Charte charte = new Charte();
		 charte.setId(cursor.getLong(0));
		charte.setLength(cursor.getLong(1));
		charte.setHight(cursor.getLong(2));
		charte.setLow(cursor.getLong(3));

	    return charte;
	  }

	 
	 
	 
	@Override
	public Charte getTestValue() {
		
		return null;
	}
	
	
	//JOINTURES

	public ContentValues getCHARTEGROUPEValues(long idcharte, long idgroupe){
		  
		ContentValues values = new ContentValues();
		values.put(CharteSQLiteOpenHelper.COLUMN_CHARTEGROUPE_CHARTE_ID, idcharte);
		values.put(CharteSQLiteOpenHelper.COLUMN_CHARTEGROUPE_GROUPE_ID, idgroupe);

		return values;
		
	}
	
	public void createCharteGroupe(Charte charte, Groupe groupe) {
//		ContentValues chartevalues =charte.getValues();
//		ContentValues groupevalues =groupe.getValues();
		
		
	    long insertId = database.insert(CharteSQLiteOpenHelper.TABLE_JOINCHARTEGROUPE, null,
	    		getCHARTEGROUPEValues(charte.getId(),(groupe.getId())));
	    Cursor cursor = database.query(CharteSQLiteOpenHelper.TABLE_JOINCHARTEGROUPE,
	    		allColumnsCharteGroupe, CharteSQLiteOpenHelper.COLUMN_CHARTEGROUPE_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    cursor.close();
	    

	}


	
	
	
	public ContentValues getGROUPEFLECHEValues(long idgroupe, long idfleche){
		  
		ContentValues values = new ContentValues();
		values.put(CharteSQLiteOpenHelper.COLUMN_GROUPEFLECHE_GROUPE_ID, idgroupe);
		values.put(CharteSQLiteOpenHelper.COLUMN_GROUPEFLECHE_FLECHE_ID, idfleche);

		return values;
		
	}
	
	public void createGroupeFleche(Groupe groupe, Fleche fleche) {
		
		
	    long insertId = database.insert(CharteSQLiteOpenHelper.TABLE_JOINGROUPEFLECHE, null,
	    		getGROUPEFLECHEValues(groupe.getId(),(fleche.getId())));
	    Cursor cursor = database.query(CharteSQLiteOpenHelper.TABLE_JOINGROUPEFLECHE,
	    		allColumnsGroupeFleche, CharteSQLiteOpenHelper.COLUMN_GROUPEFLECHE_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    cursor.close();
	    

	}

	
	
	 private Groupe cursorToGroupe(Cursor cursor) {
		 Groupe groupe = new Groupe();
		 groupe.setId(cursor.getLong(0));
		 groupe.setName(cursor.getString(1));

	    return groupe;
	  }

	 public Groupe getGroupeByName(String name){
		    Cursor cursor = database.query(CharteSQLiteOpenHelper.TABLE_GROUPE,
		    		allColumnsGroupe, CharteSQLiteOpenHelper.COLUMN_GROUPE_NAME + " LIKE '" + name+"'", null, null, null, null);

		    cursor.moveToFirst();
		    Groupe newGroupe = cursorToGroupe(cursor);
		    cursor.close();
		    return newGroupe;
	 }
	 
	public Groupe createGroupe(Groupe data) {
		ContentValues values =data.getValues();

	    long insertId = database.insert(CharteSQLiteOpenHelper.TABLE_GROUPE, null,
	        values);
	    Cursor cursor = database.query(CharteSQLiteOpenHelper.TABLE_GROUPE,
	    		allColumnsGroupe, CharteSQLiteOpenHelper.COLUMN_GROUPE_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Groupe newGroupe = cursorToGroupe(cursor);
	    cursor.close();
	    

		return newGroupe;
	}

	public void updateGroupe(Groupe data) {


		 ContentValues values =data.getValues();
		  database.update(CharteSQLiteOpenHelper.TABLE_GROUPE, values, CharteSQLiteOpenHelper.COLUMN_GROUPE_ID + " = " +data.getId(), null);
		 // System.out.println("Groupe update with id: " + data.getId());
		  
		  
	}

	public void deleteGroupe(Groupe data) {
		long id = data.getId();
	    
	   // System.out.println("Groupe deleted with id: " + id);
	    
	    database.delete(CharteSQLiteOpenHelper.TABLE_JOINCHARTEGROUPE, CharteSQLiteOpenHelper.COLUMN_CHARTEGROUPE_GROUPE_ID
		        + " = " + id, null);
	    
	    database.delete(CharteSQLiteOpenHelper.TABLE_JOINGROUPEFLECHE, CharteSQLiteOpenHelper.COLUMN_GROUPEFLECHE_GROUPE_ID
		        + " = " + id, null);

	    database.delete(CharteSQLiteOpenHelper.TABLE_GROUPE, CharteSQLiteOpenHelper.COLUMN_GROUPE_ID
	        + " = " + id, null);
		
	}
	
	

	
	 private Fleche cursorToFleche(Cursor cursor) {
		 Fleche fleche = new Fleche();
		 fleche.setId(cursor.getLong(0));
		 fleche.setModele(cursor.getString(1));
		 fleche.setName(cursor.getString(2));
		 fleche.setSurname(cursor.getString(3));
		 fleche.setGrain(cursor.getFloat(4));
		 fleche.setSpin(cursor.getString(5));
		 fleche.setDiametreoutside(cursor.getFloat(6));
		 fleche.setTaille(cursor.getFloat(7));
		 fleche.setFabricant(cursor.getString(8));
	    return fleche;
	  }

	 
	 public Fleche getFlecheByModele(String modele){
		    Cursor cursor = database.query(CharteSQLiteOpenHelper.TABLE_FLECHE,
		    		allColumnsFleche, CharteSQLiteOpenHelper.COLUMN_FLECHE_MODELE + " LIKE '" + modele+"'", null, null, null, null);

		    cursor.moveToFirst();
		    Fleche newFleche = cursorToFleche(cursor);
		    cursor.close();
		    return newFleche;
	 }

	 
	 
	public Fleche createFleche(Fleche data) {
		ContentValues values =data.getValues();

	    long insertId = database.insert(CharteSQLiteOpenHelper.TABLE_FLECHE, null,
	        values);
	    Cursor cursor = database.query(CharteSQLiteOpenHelper.TABLE_FLECHE,
	    		allColumnsFleche, CharteSQLiteOpenHelper.COLUMN_FLECHE_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Fleche newFleche = cursorToFleche(cursor);
	    cursor.close();
	    

		return newFleche;
	}

	public void updateFleche(Fleche data) {


		 ContentValues values =data.getValues();
		  database.update(CharteSQLiteOpenHelper.TABLE_FLECHE, values, CharteSQLiteOpenHelper.COLUMN_FLECHE_ID + " = " +data.getId(), null);
		//  System.out.println("Fleche update with id: " + data.getId());
		  
		  
	}

	public void deleteFleche(Fleche data) {
		long id = data.getId();
	    
	 //   System.out.println("Fleche deleted with id: " + id);
	    
	    
	    database.delete(CharteSQLiteOpenHelper.TABLE_JOINGROUPEFLECHE, CharteSQLiteOpenHelper.COLUMN_GROUPEFLECHE_FLECHE_ID
		        + " = " + id, null);

	    database.delete(CharteSQLiteOpenHelper.TABLE_FLECHE, CharteSQLiteOpenHelper.COLUMN_FLECHE_ID
	        + " = " + id, null);
		
	}
	
	
	
	


	public ArrayList<Fleche> getFleches(int taille, int weight){
		 ArrayList<Fleche> fleches = new ArrayList<Fleche>();

		Cursor cursor=  database.rawQuery("SELECT FLECHE._id,FLECHE.modele,FLECHE.name,FLECHE.surname, FLECHE.grain, FLECHE.spin, FLECHE.diametreoutside, FLECHE.taille, FLECHE.fabricant FROM CHARTE,GROUPE,CHARTEGROUPE, FLECHE,GROUPEFLECHE WHERE length = ? AND low <= ? AND hight >= ? AND CHARTE._id=CHARTEGROUPE.idcharte  AND GROUPE._id=CHARTEGROUPE.idgroupe AND GROUPE._id=GROUPEFLECHE.idgroupe AND FLECHE._id=GROUPEFLECHE.idfleche", new String[] {""+taille, ""+weight, ""+weight});

		
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	Fleche fleche = cursorToFleche(cursor);
	    	fleches.add(fleche);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();

	    
	    
		return fleches;
	}
	public void testFleches (){
		
		List<Charte> charte =  getAll();
		System.out.println("charte nombre=" + charte.size());

		
		ArrayList<Fleche> fleches = getFleches(29,21);
		System.out.println("testFleches nombre=" + fleches.size());
		for (int i = 0; i < fleches.size(); i++){
			Fleche f = fleches.get(i);
			System.out.println(f.getModele() +" " + f.getFabricant());
			
		}
	}
	
}
