package com.alv.db.distance;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

import com.alv.app.DataFragment;
import com.alv.app.DistanceArrayAdapter;
import com.alv.db.DataSourceBase;

public class DistancesDataSource extends DataSourceBase<Distance>{

	  private String[] allColumnsDistance = {DistanceSQLiteOpenHelper.COLUMN_DISTANCE_ID,
			  DistanceSQLiteOpenHelper.COLUMN_DISTANCE_NAME	,
			  DistanceSQLiteOpenHelper.COLUMN_DISTANCE_UNIT,
			  DistanceSQLiteOpenHelper.COLUMN_DISTANCE_COMMENT};
	  
	  private String[] allColumnsHausse = {DistanceSQLiteOpenHelper.COLUMN_HAUSSE_ID,
			  DistanceSQLiteOpenHelper.COLUMN_HAUSSE_IDDISTANCE	,
			  DistanceSQLiteOpenHelper.COLUMN_HAUSSE_NAME,
			  DistanceSQLiteOpenHelper.COLUMN_HAUSSE_HAUSSE};
	  

	  
	  public DistancesDataSource(Context context) {
		  super(context);
		  }

		
		  
		  public Distance create(Distance indistance) {
			    ContentValues values =indistance.getValues();

			    long insertId = database.insert(DistanceSQLiteOpenHelper.TABLE_DISTANCES, null,
			        values);
			    Cursor cursor = database.query(DistanceSQLiteOpenHelper.TABLE_DISTANCES,
			    		allColumnsDistance, DistanceSQLiteOpenHelper.COLUMN_DISTANCE_ID + " = " + insertId, null,
			        null, null, null);
			    cursor.moveToFirst();
			    Distance newDistance = cursorToDistance(cursor);
			    cursor.close();
			    
			    newDistance.setHausses(makeNewHaussesEntry(newDistance)) ;
			    return newDistance;
			  }
		  
		  
		  
		  public void update(Distance indistance){
			  ContentValues values =indistance.getValues();
			  database.update(DistanceSQLiteOpenHelper.TABLE_DISTANCES, values, DistanceSQLiteOpenHelper.COLUMN_DISTANCE_ID + " = " +indistance.getId(), null);
			  System.out.println("Distance update with id: " + indistance.getId());
			  
			  for (Hausse hausse : indistance.getHausses()){
				  updateHausse(hausse);
			  }
			  System.out.println("hausses in distance update with id: " + indistance.getId());
		  }
		  
		  public void delete(Distance indistance) {
			  
			  
		    long id = indistance.getId();
		    
		    deleteAllHausses(id);
		    
		    System.out.println("Distance deleted with id: " + id);
		    database.delete(DistanceSQLiteOpenHelper.TABLE_DISTANCES, DistanceSQLiteOpenHelper.COLUMN_DISTANCE_ID
		        + " = " + id, null);
		    
		  }

		  public List<Distance> getAll() {
		    List<Distance> distances = new ArrayList<Distance>();

		    Cursor cursor = database.query(DistanceSQLiteOpenHelper.TABLE_DISTANCES,
		    		allColumnsDistance, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	Distance distance = cursorToDistance(cursor);
		    	//dois je charger les hausses ici ???
		    	distance.setHausses(getAllHausses(distance.getId()));
		    	distances.add(distance);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    
		    
		    return distances;
		  }
		  
		  
		  
		  private Distance cursorToDistance(Cursor cursor) {
			  Distance distance = new Distance();
			  distance.setId(cursor.getLong(0));
			  distance.setName(cursor.getString(1));
			  distance.setUnit(cursor.getString(2));
			  distance.setComment(cursor.getString(3));

		    return distance;
		  }
		  
		  
		  public Hausse createHausse(Hausse inhausse) {
			    ContentValues values =inhausse.getValues();

			    long insertId = database.insert(DistanceSQLiteOpenHelper.TABLE_HAUSSES, null,
			        values);
			    Cursor cursor = database.query(DistanceSQLiteOpenHelper.TABLE_HAUSSES,
			    		allColumnsHausse, DistanceSQLiteOpenHelper.COLUMN_HAUSSE_ID + " = " + insertId, null,
			        null, null, null);
			    cursor.moveToFirst();
			    Hausse newHausse = cursorToHausse(cursor);
			    cursor.close();
			    return newHausse;
			  }


		  
		  
		  public void updateHausse(Hausse inhausse){
			  ContentValues values =inhausse.getValues();
			  database.update(DistanceSQLiteOpenHelper.TABLE_HAUSSES, values, DistanceSQLiteOpenHelper.COLUMN_HAUSSE_ID + " = " +inhausse.getId(), null);
			  System.out.println("Hausse update with id: " + inhausse.getId());
		  }
		  
		  public void deleteHausse(Hausse inhausse) {
			  
			  
		    long id = inhausse.getId();
		    System.out.println("Hausse deleted with id: " + id);
		    database.delete(DistanceSQLiteOpenHelper.TABLE_HAUSSES, DistanceSQLiteOpenHelper.COLUMN_HAUSSE_ID
		        + " = " + id, null);
		    
		  }

		  
		  public void deleteAllHausses(long idDistance) {
			  
			  
		    System.out.println("all Hausse deleted with iddistance: " + idDistance);
		    database.delete(DistanceSQLiteOpenHelper.TABLE_HAUSSES, DistanceSQLiteOpenHelper.COLUMN_HAUSSE_IDDISTANCE
		        + " = " + idDistance, null);
		    
		  }


		  public List<Hausse> getAllHausses(long idDistance) {
		    List<Hausse> hausses = new ArrayList<Hausse>();

		    Cursor cursor = database.query(DistanceSQLiteOpenHelper.TABLE_HAUSSES,
		    		allColumnsHausse, DistanceSQLiteOpenHelper.COLUMN_HAUSSE_IDDISTANCE + " = " + idDistance, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	Hausse hausse = cursorToHausse(cursor);
		    	hausses.add(hausse);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return hausses;
		  }
		  

		  
		  private Hausse cursorToHausse(Cursor cursor) {
			  Hausse hausse = new Hausse();
			  hausse.setId(cursor.getLong(0));
			  hausse.setIdDistance(cursor.getLong(1));
			  hausse.setName(cursor.getString(2));
			  hausse.setHausse(cursor.getString(3));

		    return hausse;
		  }
		  
		  
		  public  List<Hausse> makeNewHaussesEntry(Distance d){
			  String[] c = {"5","10","15","18","20","30","40","50","60","70"};
			  List<Hausse> hausses = new ArrayList<Hausse>();
			  
			  Hausse hausse ;
			  
			  
			  for (String val : c){
				  hausse = new Hausse();
				  hausse.setIdDistance(d.getId());
				  hausse.setName(val);
				  hausse.setHausse("");
				  hausse = createHausse(hausse);
				  hausses.add(hausse);
			  }
			  
			  return hausses;
		  }
		  
		  
		  public  Distance makeNewDistanceEntry(){
			  Distance d = new Distance();
			  d.setName("Toutes");
			  d.setUnit(UnitLocale.getDefault().getUnit());
			  d.setComment("toutes les distances");
			  
			  d = create(d);
			  
			  
			  makeNewHaussesEntry(d);
			  return d;
		  }
		  
		  public  ArrayAdapter<Distance> getAdapter(Context context, List<Distance>  values,DataFragment<Distance> fragment){
			  return new DistanceArrayAdapter(context,values );

		  }
		  public   Distance getTestValue(){
			  Distance d = new Distance();
			  d.setName("Toutes");
			  d.setUnit(UnitLocale.getDefault().getUnit());
			  d.setComment("toutes les distances");
			  
			  return d;
		  }
}
