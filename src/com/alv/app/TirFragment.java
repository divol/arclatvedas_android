package com.alv.app;

import com.alv.db.tir.Tir;
import com.alv.db.tir.TirDataSource;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



public class TirFragment extends DataFragment<Tir>  {
	
	
	static public final int SAVETIR = 1;  
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		datasource = new TirDataSource(this.getActivity().getApplicationContext());
		 datasource.open();
	   
	  }
	

	
	
	
	
	public void showEditDialog(Tir tir) {
//        FragmentManager fm = this.getActivity().getSupportFragmentManager();
//        TirEditFragment editNameDialog =  TirEditFragment.newInstance(tir);
//        editNameDialog.setTargetFragment(this, 0);
//        editNameDialog.show(fm, "TirEditFragment");
        		
		
        Intent intent=new Intent(this.getActivity().getApplicationContext(), TirEditDialog.class);
        intent.putExtra(TirEditDialog.ARG_ITEM_ID, tir);
        
        startActivityForResult(intent,SAVETIR);

        
        
//		FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
//
//		FragmentTransaction transaction = fragmentManager.beginTransaction();
//		transaction.setCustomAnimations(R.animator.enter, R.animator.exit, R.animator.pop_enter, R.animator.pop_exit);
//
//		TirEditFragment newCustomFragment = TirEditFragment.newInstance(tir);
//		transaction.replace(R.id.activityTir, newCustomFragment );
//		transaction.addToBackStack(null);
//		transaction.commit();

		
    }
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    // Check which request we're responding to
	    if (requestCode == SAVETIR) {
	        // Make sure the request was successful
	        if (resultCode == Activity.RESULT_OK) {
	        	Tir tosave=null;
	        	if (data.hasExtra(TirEditDialog.ARG_ITEM_ID)){
	        	  tosave = data.getParcelableExtra(TirEditDialog.ARG_ITEM_ID);
	        	}
	        	try {
	        		if (tosave != null){
	        			onFragmentInteraction(tosave);
	        		}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
	        adapter.notifyDataSetChanged();
	    }
	    adapter.notifyDataSetChanged();
	}



	

	

	

	
	
}