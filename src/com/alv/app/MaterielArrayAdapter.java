package com.alv.app;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alv.db.materiel.Materiel;
import com.alv.app.R;

public class MaterielArrayAdapter extends ArrayAdapter<Materiel> implements SelectableAdapterInterface{
	  private final Context context;
	  private final  List<Materiel> values;
	  private  int selection;
	  
	  public MaterielArrayAdapter(Context context, List<Materiel>  values) {
		    super(context, R.layout.materiel_row_layout, values);
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
	     rowView = inflater.inflate(R.layout.materiel_row_layout, parent, false);
	    
	    
	    TextView textView = (TextView) rowView.findViewById(R.id.textViewName);

	    textView.setText(values.get(position).getName());
	    // Change the icon for Windows and iPhone
	   
	    
	    TextView textViewSN = (TextView) rowView.findViewById(R.id.textViewSerialNumber);

	    textViewSN.setText(values.get(position).getSerialNumber());
	    
	    
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
	  public void add(Materiel object){
		  super.add(object);
		  this.selection=-1;
	  }
	  @Override
	  public void remove(Materiel object){
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
