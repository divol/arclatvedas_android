package com.example.testwebview;

import com.example.testwebview.db.tir.Tir;
import com.example.testwebview.db.tir.TirDataSource;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.widget.ViewFlipper;

public class TirEditDialog extends FragmentActivity{
	public static final String ARG_ITEM_ID = "tir";
	
	private ViewFlipper flipper;
	private Tir  tir;
	private TirDataSource datasource;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState == null) {
			tir = getIntent().getParcelableExtra(TirEditDialog.ARG_ITEM_ID);
			
			datasource = new TirDataSource(this.getApplicationContext());
			 datasource.open();

			 
			 
			setContentView(R.layout.activity_tir_edit_dialog);	
			

			flipper = (ViewFlipper)findViewById(R.id.flipper);
			

	
	
		}else{
			setContentView(R.layout.activity_tir_edit_dialog);
		}
		this.setFinishOnTouchOutside(false);
	}
	
	public Tir getTir(){
		return tir;
	}
	
	public TirDataSource getdatasource(){
		return datasource;
	}
	public void showNextFragment() {
		flipper.showNext();
	}

	public void showPreviousFragment() {
		flipper.showPrevious();
	}

	public void goUp(int resultCode){
		setResult(resultCode);
   	 	finish();

	}
	
	public void goUp(int resultCode,Tir result){
				
        Intent intent=new Intent(getApplicationContext(), TirFragment.class);
        intent.putExtra(TirEditDialog.ARG_ITEM_ID, result);

		setResult(resultCode,intent);
		
   	 	finish();

	}
	
	 @Override
	  public void onResume() {
		 
			 datasource.open();
	   
	    super.onResume();
	  }

	  @Override
	  public void onPause() {
	    datasource.close();
	    super.onPause();
	  }
	
	  @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
	            fragment.onActivityResult(requestCode, resultCode, data);
	        	
	        }
	    }
}
