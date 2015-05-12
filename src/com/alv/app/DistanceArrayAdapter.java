package com.alv.app;

import java.util.List;

import com.alv.db.distance.Distance;
import com.alv.app.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;

//http://www.javacodegeeks.com/2013/08/android-custom-grid-view-example-with-image-and-text.html


public class DistanceArrayAdapter extends ArrayAdapter<Distance> implements SelectableAdapterInterface{
	  private final Context context;
	  private final  List<Distance> values;
	  private  int selection;
	  
	  
	  GridView gridView;
	   Distance_GridViewAdapter customGridAdapter;

	  
	  
	  public DistanceArrayAdapter(Context context, List<Distance>  values) {
		    super(context, R.layout.distance_row_layout, values);
		    this.context = context;
		    this.values = values;
		    this.selection=-1;
		  }
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    
	    View rowView = convertView;
	    if (rowView == null)
	     rowView = inflater.inflate(R.layout.distance_row_layout, parent, false);
	    
	    
	    TextView textView = (TextView) rowView.findViewById(R.id.textViewName);

	    textView.setText(values.get(position).getName());

	   
	    CheckBox cb = (CheckBox) rowView.findViewById(R.id.checkBoxSelected);
	    if (this.selection == position){
	    	cb.setVisibility(CheckBox.VISIBLE);
	    	cb.setChecked(true);
	    }else{
	    	cb.setVisibility(CheckBox.INVISIBLE);
	    	cb.setChecked(false);
	    }

	    gridView = (GridView) rowView.findViewById(R.id.gridDistances);

	    customGridAdapter = new Distance_GridViewAdapter(this.context, R.layout.distance_row_grid, values.get(position).getHausses());
	      gridView.setAdapter(customGridAdapter);
	      rowView.setId(position);
//	      rowView.setOnClickListener(new OnClickListener() {
//
//	          @Override
//	          public void onClick(View v) {
//	        	  setSelection(v.getId());
//	        	  notifyDataSetChanged();
// 	          }
//	      });
	      gridView.setActivated(false);
	      gridView.setClickable(false);
	      gridView.setEnabled(false);
	      gridView.setFocusable(false);

	    return rowView;
	  }
	  @Override
	  public void add(Distance object){
		  super.add(object);
		  this.selection=-1;
	  }
	  @Override
	  public void remove(Distance object){
		  super.remove(object);
		  this.selection=-1;
	  }
	  public int getSelection(){
		  return this.selection;
	  }
	  public void setSelection(int position){
		  if (this.selection != position){
			  this.selection = position;
		  }else{
			  this.selection=-1;
		  }
	  }
}
