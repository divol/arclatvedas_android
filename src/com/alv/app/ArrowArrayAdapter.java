package com.alv.app;

import java.util.List;

import com.alv.db.arrow.Arrow;
import com.alv.app.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


public class ArrowArrayAdapter extends ArrayAdapter<Arrow> implements SelectableAdapterInterface{
	  private final Context context;
	  private final  List<Arrow> values;
	  private  int selection;
	  
	  public ArrowArrayAdapter(Context context, List<Arrow>  values) {
		    super(context, R.layout.arrow_row_layout, values);
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
	     rowView = inflater.inflate(R.layout.arrow_row_layout, parent, false);
	    
	    
	    TextView textView = (TextView) rowView.findViewById(R.id.textViewName);

	    textView.setText(values.get(position).getName());
	    // Change the icon for Windows and iPhone
	   
	    CheckBox cb = (CheckBox) rowView.findViewById(R.id.checkBoxSelected);
	    if (this.selection == position){
	    	cb.setVisibility(CheckBox.VISIBLE);
	    	cb.setChecked(true);
	    }else{
	    	cb.setVisibility(CheckBox.INVISIBLE);
	    	cb.setChecked(false);
	    }

	    return rowView;
	  }
	  @Override
	  public void add(Arrow object){
		  super.add(object);
		  this.selection=-1;
	  }
	  @Override
	  public void remove(Arrow object){
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
