package com.alv.app;

import com.alv.db.arrow.Arrow;
import com.alv.app.R;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link ArrowEditFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link ArrowEditFragment#newInstance} factory method to create an instance of
 * this fragment.
 * 
 */
public class ArrowEditFragment extends DialogFragment implements OnClickListener {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "arrow";

	// TODO: Rename and change types of parameters
	private Arrow mParam1;
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
	public static ArrowEditFragment newInstance(Arrow param1) {
		ArrowEditFragment fragment = new ArrowEditFragment();
		Bundle args = new Bundle();
		args.putParcelable(ARG_PARAM1, param1);
		fragment.setArguments(args);
		return fragment;
	}

	public ArrowEditFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = (Arrow)getArguments().getParcelable(ARG_PARAM1);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		 baseview  = inflater.inflate(R.layout.fragment_arrow_edit, container, false);
		
		 Button b = (Button) baseview.findViewById(R.id.okbutton);
	     b.setOnClickListener(this);
	     
	     b = (Button) baseview.findViewById(R.id.cancelbutton);
	     b.setOnClickListener(this);
	     
	     
	     
	     
	     
	     
	     if (mParam1 != null){
	     
	    	 EditText text = (EditText)baseview.findViewById(R.id.EditTextName);
	    	 text.setText(mParam1.getName());
	    	 text = (EditText)baseview.findViewById(R.id.editTextLength);
	    	 text.setText(""+mParam1.getLength());
	    	 text = (EditText)baseview.findViewById(R.id.editTextSpin);
	    	 text.setText(""+mParam1.getSpin());
	    	 text = (EditText)baseview.findViewById(R.id.editTextFeather);
	    	 text.setText(""+mParam1.getFeather());
	    	 text = (EditText)baseview.findViewById(R.id.editTextPoint);
	    	 text.setText(""+mParam1.getPoint());
	    	 
	    	 text = (EditText)baseview.findViewById(R.id.editTextDate);
	    	 text.setText(""+mParam1.getDateAchat());

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
		    	 text = (EditText)baseview.findViewById(R.id.editTextLength);
		    	 mParam1.setLength(text.getText().toString());
		    	 text = (EditText)baseview.findViewById(R.id.editTextSpin);
		    	 mParam1.setSpin(text.getText().toString());
		    	 text = (EditText)baseview.findViewById(R.id.editTextFeather);
		    	 mParam1.setFeather(text.getText().toString());
		    	 text = (EditText)baseview.findViewById(R.id.editTextPoint);
		    	 mParam1.setPoint(text.getText().toString());
		    	 
		    	 text = (EditText)baseview.findViewById(R.id.editTextDate);
		    	 mParam1.setDateAchat(text.getText().toString());
		    	 
		    	 text = (EditText)baseview.findViewById(R.id.editTextComment);
		    	 mParam1.setComment(text.getText().toString());
		    	 OnFragmentInteractionListener<Arrow> target; 
		    	 
		    	 target =   ((OnFragmentInteractionListener<Arrow>) getTargetFragment());
		    	
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
