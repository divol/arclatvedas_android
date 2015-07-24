package com.alv.app;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alv.db.tir.Score;
import com.alv.app.R;

public class VoleeArrayAdapter extends ArrayAdapter<Score> implements
		SelectableAdapterInterface {
	private final Context context;
	private final List<Score> values;
	private int selection;

	public VoleeArrayAdapter(Context context, List<Score> values) {
		super(context, R.layout.volee_row_layout, values);
		this.context = context;
		this.values = values;
		this.selection = -1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = convertView;
		if (rowView == null)
			rowView = inflater
					.inflate(R.layout.volee_row_layout, parent, false);

		if (values.get(position) != null) {

			TextView textView = (TextView) rowView.findViewById(R.id.total);

			textView.setText("" + values.get(position).getTotal());

			textView = (TextView) rowView.findViewById(R.id.vol);

			textView.setText(String.valueOf(position+1));

			for (int i = 0; i < Score.NOMBREMAX; i++) {

				int val = values.get(position).getScoreAt(i);
				int visible = View.VISIBLE;
				if (val < 0)
					visible = View.INVISIBLE;

				switch (i) {
				case 0:
					textView = (TextView) rowView.findViewById(R.id.tv1);
					break;
				case 1:
					textView = (TextView) rowView.findViewById(R.id.tv2);
					break;
				case 2:
					textView = (TextView) rowView.findViewById(R.id.tv3);
					break;
				case 3:
					textView = (TextView) rowView.findViewById(R.id.tv4);
					break;
				case 4:
					textView = (TextView) rowView.findViewById(R.id.tv5);
					break;
				case 5:
					textView = (TextView) rowView.findViewById(R.id.tv6);
					break;

				}
				if (val == 100) {
					textView.setText("X");
				} else if (val == 0) {
					textView.setText("M");
				} else {
					textView.setText("" + val);
				}
				textView.setVisibility(visible);
			}

		}

		return rowView;
	}

	@Override
	public void add(Score object) {
		super.add(object);
	}

	@Override
	public void remove(Score object) {
		super.remove(object);
		this.selection = -1;
	}

	public int getSelection() {
		return this.selection;
	}

	public void setSelection(int position) {
		if (this.selection != position) {
			this.selection = position;
		} else {
			this.selection = -1;
		}
	}
	
	public int getTotal(){
		int total=0;
		for (Score s : values){
			total+=s.getTotal();
		}
		return total;
	}
	public ArrayList<Score> getValues(){
		return (ArrayList<Score>) values;
	}
}
