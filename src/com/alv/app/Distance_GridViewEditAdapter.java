package com.alv.app;

import java.util.ArrayList;
import java.util.List;

import com.alv.db.distance.Hausse;
import com.alv.app.R;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class Distance_GridViewEditAdapter extends ArrayAdapter<Hausse> {
	private Context context;
	private int layoutResourceId;
	private List<Hausse> data = new ArrayList<Hausse>();

	
	public Distance_GridViewEditAdapter(Context context, int layoutResourceId, List<Hausse> data){
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}
	
	@Override
	 public View getView(int position, View convertView, ViewGroup parent) {
	  View row = convertView;
	  RecordHolder holder = null;
	 
	  if (row == null) {
	   LayoutInflater inflater = ((Activity) context).getLayoutInflater();
	   row = inflater.inflate(layoutResourceId, parent, false);
	 
	   holder = new RecordHolder();
	   holder.distance = (EditText) row.findViewById(R.id.item_text_distance_edit);
	   holder.hausse = (EditText) row.findViewById(R.id.item_text_hausse_edit);
	   holder.row = position;
	   row.setTag(holder);
	  } else {
	   holder = (RecordHolder) row.getTag();
	   holder.row = position;
	  }
	 
	  Hausse item = data.get(position);
	  
		final EditText fdistance=holder.distance;
		final EditText fhausse=holder.hausse;

	  
	  
		fdistance.setText(item.getName());
		fhausse.setText(item.getHausse());
	  
	  
	  fdistance.setId(position);
	  fhausse.setId(position);
	  

	  fdistance.addTextChangedListener(new TextWatcher(){

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			 Hausse item = data.get(fdistance.getId());
       	    item.setName(s.toString());
		}
		  
	  });
	  
	  
	  fhausse.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				 Hausse item = data.get(fhausse.getId());
	       	    item.setHausse(s.toString());
			}
			  
		  });
	  
	  
//	  holder.distance.setOnEditorActionListener(new OnEditorActionListener() {
//			@Override
//			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//				boolean handled = false;
//				switch(actionId){
//		        case EditorInfo.IME_ACTION_DONE:
//		        case EditorInfo.IME_ACTION_SEND: 	
//		        case EditorInfo.IME_ACTION_NEXT:{
//		        	 handled = true;
//		        	 Hausse item = data.get(v.getId());
//		        	 item.setName(v.getText().toString());
//		        }
//		        break;
//		        }
//				
//				return handled;
//			}
//		});
	  
	  
	  holder.hausse.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				boolean handled = false;
				switch(actionId){
		        case EditorInfo.IME_ACTION_DONE:
		        case EditorInfo.IME_ACTION_SEND: 	
		        case EditorInfo.IME_ACTION_NEXT:{
		        	 handled = true;
		        	 Hausse item = data.get(v.getId());
		        	 item.setHausse(v.getText().toString());
		        }
		        break;
		        }
				
				return handled;
			}
		});
	  
	  return row;
	 
	 }
	 
	 static class RecordHolder {
		 EditText distance;
		 EditText hausse;
		 int row;
	 }

	 
}
