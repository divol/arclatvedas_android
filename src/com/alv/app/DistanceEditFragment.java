package com.alv.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.alv.db.distance.Distance;
import com.alv.app.R;

public class DistanceEditFragment extends DialogFragment implements OnClickListener {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "distance";

	// TODO: Rename and change types of parameters
	private Distance mParam1;
    private View baseview;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment ArrowEditFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static DistanceEditFragment newInstance(Distance param1) {
		DistanceEditFragment fragment = new DistanceEditFragment();
		Bundle args = new Bundle();
		args.putParcelable(ARG_PARAM1, param1);
		fragment.setArguments(args);
		return fragment;
	}

	public DistanceEditFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = (Distance)getArguments().getParcelable(ARG_PARAM1);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		 baseview  = inflater.inflate(R.layout.fragment_distance_edit, container, false);
		
		 Button b = (Button) baseview.findViewById(R.id.okbutton);
	     b.setOnClickListener(this);
	     
	     b = (Button) baseview.findViewById(R.id.cancelbutton);
	     b.setOnClickListener(this);
	     
	     
	     
	     GridView gridView = (GridView) baseview.findViewById(R.id.gridDistancesEdit);
	     Distance_GridViewEditAdapter customGridEditAdapter = new Distance_GridViewEditAdapter(this.getActivity(), R.layout.distance_edit_row_grid, mParam1.getHausses());
		gridView.setAdapter(customGridEditAdapter);
	     
	     
	     if (mParam1 != null){
	     
	    	 EditText text = (EditText)baseview.findViewById(R.id.EditTextName);
	    	 text.setText(mParam1.getName());
	    	 text = (EditText)baseview.findViewById(R.id.editTextUnit);
	    	 text.setText(""+mParam1.getUnit());

	    	 text = (EditText)baseview.findViewById(R.id.editTextComment);
	    	 text.setText(""+mParam1.getComment());
	    	 
	    	 
	    	 
	     }
	        

	     
		return baseview;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onClick(View view) {
		
		
		 switch (view.getId()) {
		 case R.id.okbutton:
			 if (getTargetFragment() != null) {
				 
		    	 EditText text = (EditText)baseview.findViewById(R.id.EditTextName);
		    	 mParam1.setName(text.getText().toString());
		    	 text = (EditText)baseview.findViewById(R.id.editTextUnit);
		    	 mParam1.setUnit(text.getText().toString());
		    	 text = (EditText)baseview.findViewById(R.id.editTextComment);
		    	 mParam1.setComment(text.getText().toString());
		    	 OnFragmentInteractionListener<Distance> target; 
		    	 
		    	 target =   ((OnFragmentInteractionListener<Distance>) getTargetFragment());
		    	
		    	 target.onFragmentInteraction(mParam1);
				}
			 break;
		 }
		 this.dismiss();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
//		try {
//			mListener = (OnFragmentInteractionListener) activity;
//		} catch (ClassCastException e) {
//			throw new ClassCastException(activity.toString()
//					+ " must implement OnFragmentInteractionListener");
//		}
		
		
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}


}
