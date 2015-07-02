package com.alv.app;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.alv.db.DataSourceBase;
import com.alv.app.R;

public abstract class DataFragment<T>  extends Fragment implements OnClickListener , OnFragmentInteractionListener<T> {
	protected DataSourceBase<T> datasource;
	protected ArrayAdapter<T> adapter;
	private boolean opened;
	private ListView liste;
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	   
	  }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_arrow,
				container, false);
		//http://www.vogella.com/tutorials/AndroidSQLite/article.html#databasetutorial_intro
		
		 liste  = (ListView) rootView.findViewById(R.id.listarrow);
		
		 liste.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		 liste.setActivated(true);
		 liste.setClickable(true);
		liste.setEnabled(true);
		liste.setFocusable(true);
		//liste.setItemsCanFocus(false);
		List<T> values = datasource.getAll();
 			       
		adapter = datasource.getAdapter(liste.getContext(),values,this);
		
		 liste.setAdapter(adapter);
         liste.setOnItemLongClickListener(new OnItemLongClickListener() {
			  @Override
			  public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				  System.out.println("row long clicked"+position);
				  T data = (T) adapter.getItem(position);
				  showEditDialog(data);
				  return true;
			  }
			});
		 liste.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				  System.out.println("row clicked"+position);
				 // int oldpos = parent.getSelectedItemPosition();
				  
				  parent.setSelection(position);
				  ((SelectableAdapterInterface)adapter).setSelection(position);
				  adapter.notifyDataSetChanged();
			  }
			}); 
		 
		
		 
		 Button b = (Button) rootView.findViewById(R.id.addArrow);
	     b.setOnClickListener(this);
	     
	     b = (Button) rootView.findViewById(R.id.editArrow);
	     b.setOnClickListener(this);
	     
	     b = (Button) rootView.findViewById(R.id.deleteArrow);
	     b.setOnClickListener(this);
		return rootView;
	}
	
	public abstract void showEditDialog(T data) ;
	
	
	// Will be called via the onClick attribute
	  // of the buttons in main.xml
	  public void onClick(View view) {
	    @SuppressWarnings("unchecked")
	    T data = null;
	    switch (view.getId()) {
	    case R.id.addArrow:
	      // save the new comment to the database
	    	data =  datasource.getTestValue(); 
	    	data = datasource.create(data);
	        adapter.add(data);

	      break;
	    case R.id.editArrow:
	    	int  sel  = ((SelectableAdapterInterface)adapter).getSelection();
	    	if ( sel >= 0) {
		    	  data =  adapter.getItem(sel);
		    	  showEditDialog(data);
		    	  return; 
	    	}
		      break;
	    case R.id.deleteArrow:
	    	 sel  = ((SelectableAdapterInterface)adapter).getSelection();
	      if ( sel >= 0) {
	    	  data =  adapter.getItem(sel);
	        datasource.delete(data);
	        adapter.remove(data);
	      }
	      break;
	    }
	    adapter.notifyDataSetChanged();
	  }
	  
	  
	  
	 @Override
	  public void onResume() {
		 
		 if (!opened){
			 datasource.open();
			 opened=true;
			 List<T> values = datasource.getAll();
		       
				adapter = datasource.getAdapter(liste.getContext(),values,this);
				 liste.setAdapter(adapter);
			 adapter.notifyDataSetChanged();
		 }
	   
	    super.onResume();
	  }

	  @Override
	  public void onPause() {
	    datasource.close();
	    opened=false;
	    super.onPause();
	  }
	  
	  
	  @Override
	  public void onFragmentInteraction(T data){
		System.out.println(data);
		 if (!opened){
			 datasource.open();
			 opened=true;
		 }
		datasource.update(data);
		
		List<T> values = datasource.getAll();
	       
		adapter = datasource.getAdapter(liste.getContext(),values,this);
		 liste.setAdapter(adapter);
		 
		 
		adapter.notifyDataSetChanged();
	  }
	
	
}
