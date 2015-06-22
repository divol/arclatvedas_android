package com.alv.app;

import com.alv.db.tir.Tir;
import com.alv.app.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class TirEditFragment extends Fragment implements OnClickListener,OnItemSelectedListener {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "tir";

	// TODO: Rename and change types of parameters
	private Tir mParam1;
    private View baseview;
    
    private ArrayAdapter<CharSequence> adapter;

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
	public static TirEditFragment newInstance(Tir param1) {
		TirEditFragment fragment = new TirEditFragment();
		Bundle args = new Bundle();
		args.putParcelable(ARG_PARAM1, param1);
		fragment.setArguments(args);
		return fragment;
	}

	public TirEditFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = (Tir)getArguments().getParcelable(ARG_PARAM1);
			
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		

		// Inflate the layout for this fragment
		 baseview  = inflater.inflate(R.layout.fragment_tir_edit, container, false);
		
		 Button b = (Button) baseview.findViewById(R.id.okbutton);
	     b.setOnClickListener(this);
	     
	     b = (Button) baseview.findViewById(R.id.cancelbutton);
	     b.setOnClickListener(this);
	     
	     
	     b = (Button) baseview.findViewById(R.id.VoirButton);
	     b.setOnClickListener(this);
	     
	     b = (Button) baseview.findViewById(R.id.VoirGraph);
	     b.setOnClickListener(this);
	     
	     
	   //EditTextLocation
	   //EditTextDate
	   //spinnerDistance
	   //EditTextComment
	     
    	 Spinner spinner = (Spinner)baseview.findViewById(R.id.spinnerDistance);
    	 spinner.setOnItemSelectedListener(this);
	     
    	 adapter = ArrayAdapter.createFromResource(inflater.getContext(),
    		        R.array.distancearray, android.R.layout.simple_spinner_item);
    		// Specify the layout to use when the list of choices appears
    		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		// Apply the adapter to the spinner
    		spinner.setAdapter(adapter);

	     if (mParam1 != null){
	     
	    	 EditText text = (EditText)baseview.findViewById(R.id.EditTextLocation);
	    	 text.setText(mParam1.getLocation());
	    	 text = (EditText)baseview.findViewById(R.id.editTextDate);
	    	 text.setText(""+mParam1.getDate());
	    	 
	    	 String distance= mParam1.getDistance();
	    	 
	    	int pos =  adapter.getPosition(distance);
	    	if (pos >=0){
	    		spinner.setSelection(pos);
	    	}

	    	 text = (EditText)baseview.findViewById(R.id.editTextComment);
	    	 text.setText(""+mParam1.getComment());
	     }
	        

	     
		return baseview;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onClick(View view) {
		
		
		 switch (view.getId()) {
		 case R.id.cancelbutton:
			 TirEditDialog parent = (TirEditDialog) getActivity();
			 parent.goUp(Activity.RESULT_CANCELED);

			 break;
		 case R.id.okbutton:
				 
		    	 EditText text = (EditText)baseview.findViewById(R.id.EditTextLocation);
		    	 mParam1.setLocation(text.getText().toString());
		    	 text = (EditText)baseview.findViewById(R.id.editTextDate);
		    	 mParam1.setDate(text.getText().toString());
		    	 
		    	 Spinner spinner = (Spinner)baseview.findViewById(R.id.spinnerDistance);
		    	 String value = (String)spinner.getSelectedItem();
		    	 mParam1.setDistance(value);
		    	 
		    	 text = (EditText)baseview.findViewById(R.id.editTextComment);
		    	 mParam1.setComment(text.getText().toString());
		    	
		    	 
				 
			 TirEditDialog parenta = (TirEditDialog) getActivity();
			 parenta.goUp(Activity.RESULT_OK,mParam1);
			 break;
			 
		 case R.id.VoirGraph:
			 TirEditDialog parent2 = (TirEditDialog) getActivity();
			 parent2.showGraphFragment();

		 break;
		 
		 case R.id.VoirButton:
			 
//				FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
//			 
//			 		FragmentTransaction transaction = fragmentManager.beginTransaction();
//			 		transaction.setCustomAnimations(R.animator.enter, R.animator.exit, R.animator.pop_enter, R.animator.pop_exit);
//			 
//			 		VoleeEditFragment newCustomFragment = VoleeEditFragment.newInstance(mParam1);
//			 		
//			 		
//			 		transaction.replace(R.id.fragment_tir_edit, newCustomFragment );
//			 		transaction.addToBackStack(null);
//			 		transaction.commit();

			 
			 TirEditDialog parent1 = (TirEditDialog) getActivity();
			 parent1.showNextFragment();

			 break;
		 }
		 //this.dismiss();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		if (mParam1 == null){
			TirEditDialog parent = (TirEditDialog) activity;
			mParam1 = parent.getTir();
		}
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


	//spinner
    public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

	

}
