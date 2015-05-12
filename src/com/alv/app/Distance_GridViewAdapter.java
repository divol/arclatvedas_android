package com.alv.app;

import java.util.ArrayList;
import java.util.List;

import com.alv.db.distance.Hausse;
import com.alv.app.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Distance_GridViewAdapter extends ArrayAdapter<Hausse> {
	private Context context;
	private int layoutResourceId;
	private List<Hausse> data = new ArrayList<Hausse>();

	
	public Distance_GridViewAdapter(Context context, int layoutResourceId, List<Hausse> data){
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
	   holder.distance = (TextView) row.findViewById(R.id.item_text_distance);
	   holder.hausse = (TextView) row.findViewById(R.id.item_text_hausse);
	   row.setTag(holder);
	  } else {
	   holder = (RecordHolder) row.getTag();
	  }
	 
	  Hausse item = data.get(position);
	  holder.distance.setText(item.getName());
	  holder.hausse.setText(item.getHausse());
	  
//	  row.setClickable(false);
//	  row.setEnabled(false);
//	  row.setFocusable(false);
	  
	  return row;
	 
	 }
	 
	 static class RecordHolder {
	  TextView distance;
	  TextView hausse;
	 }

	 
}
