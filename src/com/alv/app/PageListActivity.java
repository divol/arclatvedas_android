package com.alv.app;

import com.alv.app.R;
import com.alv.app.spinchart.SpinCharteLoader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
//import android.support.v4.view.WindowCompat;




/**
 * An activity representing a list of Pages. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PageDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link PageListFragment} and the item details
 * (if present) is a {@link PageDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link PageListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class PageListActivity extends FragmentActivity
        implements PageListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //getWindow().requestFeature(WindowCompat.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_page_list);
        new Utilitaire().getLastLocation(this);
        
        
        //TODO pas terrible  ...
		SpinCharteLoader charteloader = new SpinCharteLoader(this.getApplicationContext());
		boolean go =charteloader.isbootneeded();
		charteloader.closeDB();
		
		if (go){
			BackgroundTask task = new BackgroundTask(PageListActivity.this);
			task.execute();
		}

        if (findViewById(R.id.page_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((PageListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.page_list))
                    .setActivateOnItemClick(true);
            
            


        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link PageListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
    	int idInt = Integer.parseInt(id);
    	switch (idInt){
    		case 1:
    		case 2:
    		case 3:
    		case 9:
	        if (mTwoPane) {
	            // In two-pane mode, show the detail view in this activity by
	            // adding or replacing the detail fragment using a
	            // fragment transaction.
	            Bundle arguments = new Bundle();
	            arguments.putString(PageDetailFragment.ARG_ITEM_ID, id);
	            PageDetailFragment fragment = new PageDetailFragment();
	            fragment.setArguments(arguments);
	            getSupportFragmentManager().beginTransaction()
	                    .replace(R.id.page_detail_container, fragment)
	                    .commit();
	
	        } else {
	            // In single-pane mode, simply start the detail activity
	            // for the selected item ID.
	            Intent detailIntent = new Intent(this, PageDetailActivity.class);
	            detailIntent.putExtra(PageDetailFragment.ARG_ITEM_ID, id);
	            startActivity(detailIntent);
	        }
	        break;
	        
    		

    		case 4:
    			if (mTwoPane) {
		            // In two-pane mode, show the detail view in this activity by
		            // adding or replacing the detail fragment using a
		            // fragment transaction.
		           
   				MaterielList2PlaceholderFragment fragment = new MaterielList2PlaceholderFragment();
		            getSupportFragmentManager().beginTransaction()
		                    .replace(R.id.page_detail_container, fragment)
		                    .commit();
		
		        } else {
		            // In single-pane mode, simply start the detail activity
		            // for the selected item ID.
		            Intent detailIntent = new Intent(this, MaterielList2.class);
		            //detailIntent.putExtra(PageDetailFragment.ARG_ITEM_ID, id);
		            startActivity(detailIntent);
		        }
    			break;
    			
    		case 5: //Sélecteur de flèche
    			
    			


    			if (mTwoPane) {
    				CharteActivity.PlaceholderFragment frag = new CharteActivity.PlaceholderFragment();
    						
		            getSupportFragmentManager().beginTransaction()
		                    .replace(R.id.page_detail_container,frag)
		                    .commit();

    			}else{
    	            Intent charteIntent = new Intent(this, CharteActivity.class);
    	            //detailIntent.putExtra(PageDetailFragment.ARG_ITEM_ID, id);
    	            startActivity(charteIntent);

    			}
    			
    			
    			

    			break;
    		case 6://Distances
    			
    			if (mTwoPane) {
		            // In two-pane mode, show the detail view in this activity by
		            // adding or replacing the detail fragment using a
		            // fragment transaction.
		           
    				DistanceFragment frag = new DistanceFragment();
		            getSupportFragmentManager().beginTransaction()
		                    .replace(R.id.page_detail_container, frag)
		                    .commit();
		
		        } else {
		            // In single-pane mode, simply start the detail activity
		            // for the selected item ID.
		            Intent detailIntent = new Intent(this, DistanceActivity.class);
		            //detailIntent.putExtra(PageDetailFragment.ARG_ITEM_ID, id);
		            startActivity(detailIntent);
		        }
    			
    			break;
    		case 7: //scores
    			
    			
    			
    			
    			if (mTwoPane) {
		            // In two-pane mode, show the detail view in this activity by
		            // adding or replacing the detail fragment using a
		            // fragment transaction.
		           
    				TirFragment frag = new TirFragment();
		            getSupportFragmentManager().beginTransaction()
		                    .replace(R.id.page_detail_container, frag)
		                    .commit();
		
		        } else {
		            // In single-pane mode, simply start the detail activity
		            // for the selected item ID.
		            Intent detailIntent = new Intent(this, TirActivity.class);
		            //detailIntent.putExtra(PageDetailFragment.ARG_ITEM_ID, id);
		            startActivity(detailIntent);
		        }
    			
    			
    			
    			break;
    		
    			
    		case 8:
    			
    			
    			if (mTwoPane) {
		            // In two-pane mode, show the detail view in this activity by
		            // adding or replacing the detail fragment using a
		            // fragment transaction.
		           
    				TirGraphFragment frag = new TirGraphFragment();
		            getSupportFragmentManager().beginTransaction()
		                    .replace(R.id.page_detail_container, frag)
		                    .commit();
		
		        } else {
		            // In single-pane mode, simply start the detail activity
		            // for the selected item ID.
		            Intent detailIntent = new Intent(this, TirGraphActivity.class);
		            //detailIntent.putExtra(PageDetailFragment.ARG_ITEM_ID, id);
		            startActivity(detailIntent);
		        }
    			
    			
    	        

    	}
    }
    
    
    
    //TODO pas bon du tout
    private class BackgroundTask extends AsyncTask <Void, Void, Void> {
        private ProgressDialog dialog;
        PageListActivity activity;
        public BackgroundTask(PageListActivity activity) {
        	this.activity = activity;
            dialog = new ProgressDialog(activity);
            
        }
     
        @Override
        protected void onPreExecute() {
            dialog.setMessage("Chargement des tables...");
            dialog.show();
        }
         
        @Override
        protected void onPostExecute(Void result) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
         
        @Override
        protected Void doInBackground(Void... params) {
            try {
            	Thread.sleep(1);
        		SpinCharteLoader charteloader = new SpinCharteLoader(this.activity.getApplicationContext());
        		//charteloader.boot();
        		charteloader.bootSQL();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
     
            return null;
        }
         
    }
}
