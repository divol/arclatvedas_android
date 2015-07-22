package com.alv.app;

import com.alv.db.distance.Distance;
import com.alv.db.distance.DistancesDataSource;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;


public class DistanceFragment extends DataFragment<Distance>  {
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		datasource = new DistancesDataSource(this.getActivity().getApplicationContext());
		 datasource.open();
	   
	  }
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);

	}
	
	public void showEditDialog(Distance data) {
        FragmentManager fm = this.getActivity().getSupportFragmentManager();
        DistanceEditFragment editNameDialog =  DistanceEditFragment.newInstance(data);
        editNameDialog.setTargetFragment(this, 0);
        editNameDialog.show(fm, "fragment_edit_name");
    }

	

}