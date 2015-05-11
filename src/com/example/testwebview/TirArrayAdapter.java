package com.example.testwebview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.testwebview.db.tir.Tir;

public class TirArrayAdapter extends ArrayAdapter<Tir> implements SelectableAdapterInterface{
	 private final Context context;
	  private final  List<Tir> values;
	  private  int selection;
	
	 public TirArrayAdapter(Context context, List<Tir>  values) {
		    super(context, R.layout.tir_row_layout, values);
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
	     rowView = inflater.inflate(R.layout.tir_row_layout, parent, false);
	    
	    
	    TextView textView = (TextView) rowView.findViewById(R.id.textViewName);

	    textView.setText(values.get(position).getDistance());
	    
	    textView = (TextView) rowView.findViewById(R.id.textViewDate);

	    textView.setText(values.get(position).getDate().toString()+"");
	   
	    textView = (TextView) rowView.findViewById(R.id.textTotal);

	    textView.setText(values.get(position).getTotal()+"");
	    
	    
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
	  public void add(Tir object){
		  super.add(object);
		  this.selection=-1;
	  }
	  @Override
	  public void remove(Tir object){
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
