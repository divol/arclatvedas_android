package com.alv.app;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alv.db.charte.Fleche;
import com.alv.app.R;

public class CharteArrayAdapter extends ArrayAdapter<Fleche> {
	  private final Context context;
	  private final  List<Fleche> values;
	  
	  public CharteArrayAdapter(Context context, List<Fleche>  values) {
		    super(context, R.layout.charte_row_layout, values);
		    this.context = context;
		    this.values = values;
		  }
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    
	    View rowView = convertView;
	    if (rowView == null)
	     rowView = inflater.inflate(R.layout.charte_row_layout, parent, false);
	    
	    
	    TextView textView = (TextView) rowView.findViewById(R.id.textView);

	    textView.setText(values.get(position).toString());
	    // Change the icon for Windows and iPhone
	   
	    

	    return rowView;
	  }
	 
}
