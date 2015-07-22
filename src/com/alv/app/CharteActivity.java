package com.alv.app;

import java.util.ArrayList;

import com.alv.db.charte.CharteDataSource;
import com.alv.db.charte.Fleche;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class CharteActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // pour activer le retour Home

		setContentView(R.layout.activity_charte);
		
//		datasource = new CharteDataSource(this.getApplicationContext());
//		 datasource.open();

		 
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.charte, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		
		if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
			//datasource.close();
            NavUtils.navigateUpTo(this, new Intent(this, PageListActivity.class));
            return true;
        }

		return super.onOptionsItemSelected(item);
	}

	
	
	
	
	
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements OnClickListener {
		 CharteDataSource datasource;

		View rootView;
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			datasource = new CharteDataSource(this.getActivity().getApplicationContext());
			 datasource.open();

			 rootView = inflater.inflate(R.layout.fragment_charte,
					container, false);
			
			//EditText eTaille =  (EditText) rootView.findViewById(R.id.editTextTaille);
		//	EditText ePuissance =  (EditText) rootView.findViewById(R.id.editTextPuissance);
			Button bRecherche =  (Button) rootView.findViewById(R.id.buttonCherche);
			bRecherche.setOnClickListener(this);
			ListView list = (ListView)rootView.findViewById(R.id.listViewResult);
			CharteArrayAdapter adapter = new CharteArrayAdapter(this.getActivity(),new ArrayList<Fleche>() );
			list.setAdapter(adapter);

			return rootView;
		}
		
		
		 @Override
		  public void onResume() {
			 
		   
		    super.onResume();
		  }

		  @Override
		  public void onPause() {
		    datasource.close();
		    super.onPause();
		  }

		public void onClick(View view) {
			
			
			 switch (view.getId()) {
			 case R.id.buttonCherche:
					EditText eTaille =  (EditText) rootView.findViewById(R.id.editTextTaille);
					EditText ePuissance =  (EditText) rootView.findViewById(R.id.editTextPuissance);
					String staille = eTaille.getText().toString();
					String spuissance = ePuissance.getText().toString();
					int taille=0;
					int puissance = 0;
					ArrayList<Fleche> fleches =new ArrayList<Fleche>();
					
					
	                InputMethodManager keyboard = (InputMethodManager)
	                		this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
	                        keyboard.hideSoftInputFromWindow(ePuissance.getWindowToken(), 0);

	                        
	                        
					try {
				          taille = Integer.parseInt(staille);
				          puissance = Integer.parseInt(spuissance);

				      } catch (NumberFormatException nfe) {
				         System.out.println("mauvais nombre saisi: " + nfe.getMessage());
				      }

					if (taille >0 && puissance >0)
					{
						fleches = datasource.getFleches(taille, puissance);
					}
					CharteArrayAdapter adapter = new CharteArrayAdapter(this.getActivity(),fleches );
					ListView list = (ListView)rootView.findViewById(R.id.listViewResult);
					list.setAdapter(adapter);
				 break;
			 }
		}
	}
}
