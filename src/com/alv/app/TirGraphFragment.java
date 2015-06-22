package com.alv.app;

import java.util.ArrayList;

import com.alv.db.tir.Tir;
import com.alv.db.tir.TirDataSource;
import com.alv.app.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TirGraphFragment extends Fragment implements OnClickListener {
	// TODO: Rename and change types of parameters
	private View baseview;
	TirDataSource  datasource;
	

	public TirGraphFragment() {
		// Required empty public constructor

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datasource = new TirDataSource(this.getActivity().getApplicationContext());
		 datasource.open();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the layout for this fragment
		baseview = inflater.inflate(R.layout.fragment_volee_graph, container,
				false);
		Button but = (Button) baseview.findViewById(R.id.buttonRetour);
		but.setVisibility(Button.INVISIBLE);
		but.setOnClickListener(this);

		if (datasource != null){
			ArrayList<Tir> scores = (ArrayList<Tir>) datasource.getAll();
	
			ArrayList<Entry> vals = new ArrayList<Entry>();
		    ArrayList<String> xVals = new ArrayList<String>();
	
			 for (int i =0; i<scores.size() ; i++){
				 Tir s = scores.get(i);
				 Entry entry = new Entry(s.getTotal(),i);
				 xVals.add("T."+(i+1)); 
				 vals.add(entry);
			 }
					
			LineChart chart = (LineChart) baseview.findViewById(R.id.chart);
	
		    LineDataSet setComp1 = new LineDataSet(vals, "Tirs");
	
		    ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
		    dataSets.add(setComp1);
		    LineData data = new LineData(xVals, dataSets);
		    chart.setData(data);
		    chart.invalidate(); // refresh
		}


		
		return baseview;
	}
	
	// TODO: Rename method, update argument and hook method into UI event
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.buttonRetour:
			

			break;
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public void onDetach() {
		super.onDetach();
		if (datasource != null){
			datasource.close();
		}
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
	}

}
