package com.alv.app;

import java.util.ArrayList;

import com.alv.db.tir.Score;
import com.alv.db.tir.Tir;
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

public class VoleeGraphFragment extends Fragment implements OnClickListener {
	private static final String ARG_PARAM1 = "tir";
	int EFFACEVOLEE = 1;
	// TODO: Rename and change types of parameters
	private Tir mParam1;
	private View baseview;
	private TirEditDialog delegue;
	
	public static VoleeGraphFragment newInstance(Tir param1) {
		VoleeGraphFragment fragment = new VoleeGraphFragment();
		Bundle args = new Bundle();
		args.putParcelable(ARG_PARAM1, param1);
		fragment.setArguments(args);
		return fragment;
	}

	public VoleeGraphFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = (Tir) getArguments().getParcelable(ARG_PARAM1);
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the layout for this fragment
		baseview = inflater.inflate(R.layout.fragment_volee_graph, container,
				false);

		delegue = (TirEditDialog) getActivity();
		ArrayList<Score> scores = delegue.getdatasource().getAllScores(mParam1.getId());

		
		ArrayList<Entry> vals = new ArrayList<Entry>();
	    ArrayList<String> xVals = new ArrayList<String>();

		 for (int i =0; i<scores.size() ; i++){
			 Score s = scores.get(i);
			 Entry entry = new Entry(s.getTotal(),i);
			 xVals.add("Tir "+i); 
			 vals.add(entry);
		 }
				
		LineChart chart = (LineChart) baseview.findViewById(R.id.chart);
		
	    LineDataSet setComp1 = new LineDataSet(vals, "VolŽes");
	    
	    ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
	    dataSets.add(setComp1);

	   

	    
	    
	    LineData data = new LineData(xVals, dataSets);
	    
	    
	    chart.setData(data);
	    chart.invalidate(); // refresh


		Button b = (Button) baseview.findViewById(R.id.buttonRetour);
		b.setOnClickListener(this);

		
		return baseview;
	}
	
	// TODO: Rename method, update argument and hook method into UI event
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.buttonRetour:
			
			delegue.showFirstFragment();

			break;
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		 delegue = (TirEditDialog)activity;
		if (mParam1 == null) {
			mParam1 = delegue.getTir();
		}

	}

	@Override
	public void onDetach() {
		super.onDetach();
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
	}

}
