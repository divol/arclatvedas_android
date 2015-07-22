package com.alv.app;


import com.alv.lists.MaterielContent;
import com.alv.app.R;

import android.support.v4.app.Fragment;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MaterielList2PlaceholderFragment extends Fragment implements
ActionBar.OnNavigationListener {

	
	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item_LIST";
	
	
	public MaterielList2PlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_materiel_list2,
				container, false);
		
		
		final ActionBar actionBar = getActivity().getActionBar();
		
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// Set up the action bar to show a dropdown list.
		//actionBar.setDisplayShowTitleEnabled(false);
		//actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Set up the dropdown list navigation in the action bar.
		/*
		actionBar.setListNavigationCallbacks(
		// Specify a SpinnerAdapter to populate the dropdown list.
				new ArrayAdapter<String>(actionBar.getThemedContext(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, new String[] {
								getString(R.string.title_section1),
								getString(R.string.title_section2),
								getString(R.string.title_section3), }), this);
		
		*/
		actionBar.setListNavigationCallbacks(
				new ArrayAdapter<String>(
						 actionBar.getThemedContext(),
			                android.R.layout.simple_list_item_1,
			                android.R.id.text1,
			                MaterielContent.MATERIEL_TYPES), this);
		
		 
		
		 
		return rootView;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater menuinf) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuinf.inflate(R.menu.materiel_list2, menu);
	}

	
	
	
	
	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
		
		switch(position){
		case 0:
			MaterielFragment frag1 = new MaterielFragment();

			getFragmentManager()
			.beginTransaction()
			.replace(R.id.fragcontainerList2,
					frag1).commit();
//			getFragmentManager()
//			.beginTransaction()
//			.replace(R.id.fragcontainerList2,
//					PlaceholderFragment.newInstance(position + 1)).commit();
		break;
		
		case 1:
			ArrowFragment frag = new ArrowFragment();

			getFragmentManager()
			.beginTransaction()
			.replace(R.id.fragcontainerList2,
					frag).commit();
			break;
		}
		
		return true;
	}
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState != null){
			if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
				getActivity().getActionBar().setSelectedNavigationItem(
						savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
			}
		}
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActivity().getActionBar()
				.getSelectedNavigationIndex());
		
        super.onSaveInstanceState(outState);

	}

	
		   
	   
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings_list2) {
			
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	  public void onResume() {
		 
		final ActionBar actionBar = getActivity().getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST); // montrer spinner
	    super.onResume();
	  }

	  @Override
	  public void onPause() {
		  final ActionBar actionBar = getActivity().getActionBar();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD); // "cacher" spinner
	    super.onPause();
	  }
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
         public int rang;
		
		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment(sectionNumber);
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
			this.rang=1;
		}
		public PlaceholderFragment(int rang) {
			this.rang=rang;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_materiel,
					container, false);
			TextView text  = (TextView) rootView.findViewById(R.id.enfin);
			//http://www.vogella.com/tutorials/AndroidSQLite/article.html#databasetutorial_intro
			text.setText("valeur="+MaterielContent.MATERIEL_TYPES.get(rang-1));
			return rootView;
		}
	}
	
}
