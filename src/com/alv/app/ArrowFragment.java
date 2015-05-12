package com.alv.app;

import com.alv.db.arrow.Arrow;
import com.alv.db.arrow.ArrowsDataSource;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;


//http://www.vogella.com/tutorials/AndroidListView/article.html


public class ArrowFragment extends DataFragment<Arrow>  {
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		datasource = new ArrowsDataSource(this.getActivity().getApplicationContext());
		 datasource.open();
	   
	  }
	

	
	public void showEditDialog(Arrow arrow) {
        FragmentManager fm = this.getActivity().getSupportFragmentManager();
        ArrowEditFragment editNameDialog =  ArrowEditFragment.newInstance(arrow);
        editNameDialog.setTargetFragment(this, 0);
        editNameDialog.show(fm, "fragment_edit_name");
    }

	

}
