package com.alv.app;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alv.db.tir.Tir;
import com.alv.app.R;
import com.alv.app.cropcircles.BlasonActivity;

import android.view.View.OnClickListener;

public class TirArrayAdapter extends ArrayAdapter<Tir> implements SelectableAdapterInterface,OnClickListener{
	 private final Context context;
	  private final  List<Tir> values;
	  private  int selection;
	private View rowView;
	DataFragment<Tir> fragment;
	 public TirArrayAdapter(Context context, List<Tir>  values, DataFragment<Tir> fragment) {
		    super(context, R.layout.tir_row_layout, values);
		    this.context = context;
		    this.values = values;
		    this.selection=-1;
		    this.fragment = fragment;
		  }
	 
	 
	 
	 @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    
	     rowView = convertView;
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

	    Button blasonButton = (Button)rowView.findViewById(R.id.blasonButton);
	    blasonButton.setTag(values.get(position));
	    blasonButton.setOnClickListener(this);
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



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.blasonButton:
			
// 			 Intent detailIntentCircle = new Intent(this.context, BlasonActivity.class);
//	            //detailIntent.putExtra(PageDetailFragment.ARG_ITEM_ID, id);
// 			this.context.startActivity(detailIntentCircle);
			
 	        Intent intent=new Intent(this.context, BlasonActivity.class);
 	      
 	        intent.putExtra(TirEditDialog.ARG_ITEM_ID,(Tir)  v.getTag());
 	       fragment.startActivityForResult(intent,TirFragment.SAVETIR); 

	        
	        
			break;
		}
		
	}

}
