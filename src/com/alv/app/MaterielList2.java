package com.alv.app;

import com.alv.app.R;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.view.MenuItem;

public class MaterielList2 extends FragmentActivity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // pour activer le retour Home
		setContentView(R.layout.activity_materiel_list2);
		if (savedInstanceState == null) {
			
			
			
			MaterielList2PlaceholderFragment fragment = new MaterielList2PlaceholderFragment();
			
			 getSupportFragmentManager().beginTransaction()
            .add(R.id.containerList2, fragment)
            .commit();

	
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, PageListActivity.class));
            return true;
        }
		return super.onOptionsItemSelected(item);
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
        	if (fragment.getClass().equals(MaterielEditFragment.class)){
        		fragment.onActivityResult(requestCode, resultCode, data);
        	}
        	
        }
    }
}
