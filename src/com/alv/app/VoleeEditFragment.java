package com.alv.app;

import java.util.ArrayList;

import com.alv.db.tir.Score;
import com.alv.db.tir.Tir;
import com.alv.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class VoleeEditFragment extends Fragment implements OnClickListener {
	private static final String ARG_PARAM1 = "tir";
	int EFFACEVOLEE = 1;
	// TODO: Rename and change types of parameters
	private Tir mParam1;
	private View baseview;
	private ListView liste;
	private VoleeArrayAdapter listAdapter;
	private Score curScore = null;
	private TirEditDialog delegue;
	private TextView total;
	private int lastpositiontodelete;
	
	public static VoleeEditFragment newInstance(Tir param1) {
		VoleeEditFragment fragment = new VoleeEditFragment();
		Bundle args = new Bundle();
		args.putParcelable(ARG_PARAM1, param1);
		fragment.setArguments(args);
		return fragment;
	}

	public VoleeEditFragment() {
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
		
		lastpositiontodelete=-1;
		// Inflate the layout for this fragment
		baseview = inflater.inflate(R.layout.fragment_volee_edit, container,
				false);

		liste = (ListView) baseview.findViewById(R.id.listVolees);

		delegue = (TirEditDialog) getActivity();
		ArrayList<Score> scores = delegue.getdatasource().getAllScores(mParam1.getId());
		listAdapter = new VoleeArrayAdapter(this.getActivity(),
				scores);
		liste.setAdapter(listAdapter);

		curScore = listAdapter.getItem(scores.size()-1);
		
		 total = (TextView)baseview.findViewById(R.id.total);
		 total.setText(""+ listAdapter.getTotal());
		
		liste.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				lastpositiontodelete=position;
				demandeDeleteUser();
				
				
			}
		});

		Button b = (Button) baseview.findViewById(R.id.buttonRetour);
		b.setOnClickListener(this);

		b = (Button) baseview.findViewById(R.id.buttonok);
		b.setOnClickListener(this);

		b = (Button) baseview.findViewById(R.id.buttonmoins);
		b.setOnClickListener(this);

		b = (Button) baseview.findViewById(R.id.buttonx);
		b.setOnClickListener(this);

		b = (Button) baseview.findViewById(R.id.button10);
		b.setOnClickListener(this);

		b = (Button) baseview.findViewById(R.id.button9);
		b.setOnClickListener(this);

		b = (Button) baseview.findViewById(R.id.button8);
		b.setOnClickListener(this);

		b = (Button) baseview.findViewById(R.id.button7);
		b.setOnClickListener(this);

		b = (Button) baseview.findViewById(R.id.button6);
		b.setOnClickListener(this);

		b = (Button) baseview.findViewById(R.id.button5);
		b.setOnClickListener(this);

		b = (Button) baseview.findViewById(R.id.button4);
		b.setOnClickListener(this);

		b = (Button) baseview.findViewById(R.id.button3);
		b.setOnClickListener(this);

		b = (Button) baseview.findViewById(R.id.button2);
		b.setOnClickListener(this);

		b = (Button) baseview.findViewById(R.id.button1);
		b.setOnClickListener(this);

		b = (Button) baseview.findViewById(R.id.button0);
		b.setOnClickListener(this);

		return baseview;
	}
	
	// TODO: Rename method, update argument and hook method into UI event
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.buttonRetour:
			
			delegue.showPreviousFragment();

			break;
		case R.id.buttonok:
			if (curScore.getV().size() == Score.NOMBREMAX) {
				curScore = new Score(mParam1.getId());
				curScore = delegue.getdatasource().createScore(curScore);
				listAdapter.add(curScore);
				listAdapter.notifyDataSetChanged();
				liste.smoothScrollToPosition(listAdapter.getCount()-1);
				
			}

			break;

		case R.id.buttonmoins:
			if (curScore != null) {
				curScore.deleteLast();
				delegue.getdatasource().updateScore(curScore);
				listAdapter.notifyDataSetChanged();
			}
			break;

		case R.id.buttonx:
			if (curScore != null){
				curScore.addScore(100);
				delegue.getdatasource().updateScore(curScore);
			}
			break;
		case R.id.button10:
			if (curScore != null){
				curScore.addScore(10);
				delegue.getdatasource().updateScore(curScore);
			}
			break;
		case R.id.button9:
			if (curScore != null){
				curScore.addScore(9);
				delegue.getdatasource().updateScore(curScore);
			}
			break;
		case R.id.button8:
			if (curScore != null){
				curScore.addScore(8);
				delegue.getdatasource().updateScore(curScore);
			}

			break;
		case R.id.button7:
			if (curScore != null){
				curScore.addScore(7);
				delegue.getdatasource().updateScore(curScore);
			}

			break;
		case R.id.button6:
			if (curScore != null){
				curScore.addScore(6);
				delegue.getdatasource().updateScore(curScore);
			}

			break;
		case R.id.button5:
			if (curScore != null){
				curScore.addScore(5);
				delegue.getdatasource().updateScore(curScore);
			}

			break;
		case R.id.button4:
			if (curScore != null){
				curScore.addScore(4);
				delegue.getdatasource().updateScore(curScore);
			}

			break;
		case R.id.button3:
			if (curScore != null){
				curScore.addScore(3);
				delegue.getdatasource().updateScore(curScore);
			}

			break;
		case R.id.button2:
			if (curScore != null){
				curScore.addScore(2);
				delegue.getdatasource().updateScore(curScore);
			}

			break;
		case R.id.button1:
			if (curScore != null){
				curScore.addScore(1);
				delegue.getdatasource().updateScore(curScore);
			}

			break;
		case R.id.button0:
			if (curScore != null){
				curScore.addScore(0);
				delegue.getdatasource().updateScore(curScore);
			}

			break;

		}
		
		listAdapter.notifyDataSetChanged();
		liste.smoothScrollToPosition(listAdapter.getCount()-1);
		total.setText(""+ listAdapter.getTotal());
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
	
	public void deleteAtPosition(int position){
		if (listAdapter.getItem(position) == curScore) {
			delegue.getdatasource().deleteScore(listAdapter.getItem(position));
			listAdapter.remove(listAdapter.getItem(position));
			
			
			
			curScore = new Score(mParam1.getId());
			curScore = delegue.getdatasource().createScore(curScore);
			listAdapter.add(curScore);
			

		} else {
			delegue.getdatasource().deleteScore(listAdapter.getItem(position));
			listAdapter.remove(listAdapter.getItem(position));
			if (curScore.getV().size() == Score.NOMBREMAX) {
				curScore = new Score(mParam1.getId());
				curScore = delegue.getdatasource().createScore(curScore);
				listAdapter.add(curScore);
				
			}

		}
		
		
		listAdapter.notifyDataSetChanged();
		total.setText(""+ listAdapter.getTotal());
		liste.smoothScrollToPosition(listAdapter.getCount()-1);
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == EFFACEVOLEE){
			if (resultCode == Activity.RESULT_OK){
				if (lastpositiontodelete != -1){
					deleteAtPosition(lastpositiontodelete);
					lastpositiontodelete=-1;
				}
			}
		}
	}

	public void demandeDeleteUser(){
		DialogFragment dialog = new YesNoDialog();
		Bundle args = new Bundle();
		args.putString("title", "Attention");
		args.putString("message", "Voulez-vous effacer cette volée ?");
		dialog.setArguments(args);
		dialog.setTargetFragment(this, EFFACEVOLEE);
		dialog.show(getFragmentManager(), "tag");

	}
}
