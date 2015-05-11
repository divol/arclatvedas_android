package com.example.testwebview;

import com.example.testwebview.db.materiel.Materiel;
import com.example.testwebview.db.materiel.MaterielDataSource;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;



public class MaterielFragment  extends DataFragment<Materiel>  {
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		datasource = new MaterielDataSource(this.getActivity().getApplicationContext());
		 datasource.open();
	   
	  }
	

	
	public void showEditDialog(Materiel data) {
        FragmentManager fm = this.getActivity().getSupportFragmentManager();
        MaterielEditFragment editNameDialog =  MaterielEditFragment.newInstance(data);
        editNameDialog.setTargetFragment(this, 0);
        editNameDialog.show(fm, "fragment_edit_name");
    }

	

}