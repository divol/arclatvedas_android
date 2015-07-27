package com.alv.app.cropcircles;

import java.util.ArrayList;
import java.util.Vector;

import com.alv.app.R;
import com.alv.app.TirActivity;
import com.alv.app.TirEditDialog;
import com.alv.app.VoleeArrayAdapter;
import com.alv.db.tir.Score;
import com.alv.db.tir.Tir;
import com.alv.db.tir.TirDataSource;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


///ref : http://stackoverflow.com/questions/13864480/android-how-to-circular-zoom-magnify-part-of-image

/// http://www.java2s.com/Code/Android/2D-Graphics/Drawacircle.htm
public class BlasonActivity extends Activity  implements BlasonInterface,OnClickListener,ActionBar.OnNavigationListener {
	AbstractBlasonView drawingImageView=null;
	TirDataSource datasource;
	Tir tir;
	VoleeArrayAdapter listAdapter;

	private Score curScore = null;
	private TextView total;
	private TextView voleetext;
	private TextView  counttext;

	Vector<String> vectBlason;


	//	PointF zoomPos = new PointF(0,0);  
	//	Boolean zooming = false;





	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tir = getIntent().getParcelableExtra(TirEditDialog.ARG_ITEM_ID);

		datasource = new TirDataSource(this.getApplicationContext());
		datasource.open();
		ArrayList<Score> scores = datasource.getAllScores(tir.getId());
		listAdapter = new VoleeArrayAdapter(this,
				scores);

		curScore = listAdapter.getItem(scores.size()-1);



		setContentView(R.layout.activity_circleview);




		//action bar

		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // pour activer le retour Home


		total = (TextView)findViewById(R.id.totalBlason);
		total.setText(""+ listAdapter.getTotal());

		voleetext = (TextView)findViewById(R.id.voleeText);
		refreshVolee();

		counttext = (TextView)findViewById(R.id.countText);
		counttext.setText(""+ listAdapter.getCount());

		Button b = (Button) findViewById(R.id.blasonok);
		b.setOnClickListener(this);

		b = (Button) findViewById(R.id.blasonmoins);
		b.setOnClickListener(this);



		//menu blason
		vectBlason = new Vector<String>();
		vectBlason.add(Blason.FITA.toString());
		vectBlason.add(Blason.FITAReduce.toString());
		 		vectBlason.add(Blason.TriSpot.toString());
		//		vectBlason.add(Blason.Campagne.toString());
		//		vectBlason.add(Blason.CampagneDouble.toString());
		//		vectBlason.add(Blason.CampagneTriple.toString());
		//		vectBlason.add(Blason.Beursault.toString());



		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		actionBar.setListNavigationCallbacks(
				new ArrayAdapter<String>(
						actionBar.getThemedContext(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1,
						vectBlason), this);

		int toto  =(int) tir.getBlasonType();
		if (toto >= vectBlason.size()) {
			toto = 0; // ^^ for future use ...
		}
		System.out.println("getBlasonType = "+toto);
		
		buildBlason(toto);
		actionBar.setSelectedNavigationItem(toto);

//		//drawingImageView
//		LinearLayout layout = (LinearLayout) this.findViewById(R.id.conteneurview);
//		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//				LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//
//		drawingImageView = new  BlasonView(this,null);
//		drawingImageView.setLayoutParams(params);
//		layout.addView(drawingImageView);
//
//
//		//drawingImageView = (BlasonView) this.findViewById(R.id.DrawingImageView);
//
//		drawingImageView.delegate = this;
//
//
//		for (Score score : scores){
//			for (int i = 0 ; i < 6 ; i++){
//				PointF p = score.getPointAt(i);
//				Point zone = score.getZoneAt(i);
//				drawingImageView.addPoint(p,zone);
//			}
//		}
//		drawingImageView.invalidate();




	}


	private void buildBlason(int blasontype){
		
		//drawingImageView
		LinearLayout layout = (LinearLayout) this.findViewById(R.id.conteneurview);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

		
		if (drawingImageView != null) {
			layout.removeView(drawingImageView);

		}
		switch(blasontype){
		case 0: //FITA
			drawingImageView = new  BlasonView(this,null);


			break;
		case 1: //FITAReduce
			drawingImageView = new  ReducedBlasonView(this,null);

			break;
		case 2: //TriSpot
			drawingImageView = new  TrispotView(this,null);
			break;
		case 3: //Campagne
			break;
		case 4: //CampagneDouble
			break;
		case 5: //CampagneTriple
			break;
		case 6: //Beursault
			break;

		}

		
		drawingImageView.setLayoutParams(params);
		layout.addView(drawingImageView);


		//drawingImageView = (BlasonView) this.findViewById(R.id.DrawingImageView);

		drawingImageView.delegate = this;

		ArrayList<Score> scores  =listAdapter.getValues();
		
		for (Score score : scores){
			for (int i = 0 ; i < 6 ; i++){
				PointF p = score.getPointAt(i);
				Point zone = score.getZoneAt(i);
				drawingImageView.addPoint(p,zone);
			}
		}
		drawingImageView.invalidate();


		
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

			//				 TirEditDialog parent = (TirEditDialog) getActivity();
			//				 parent.goUp(Activity.RESULT_CANCELED);

			NavUtils.navigateUpTo(this, new Intent(this, TirActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}




	public void managePanEnd(PointF p, int value, Point zone){
		if (curScore != null) {
			if (curScore.addScore(value,p)){
				System.out.println("score1="+value);
				if (datasource != null){
					System.out.println("datasource= ok ");
					datasource.updateScore(curScore);
					System.out.println("score2="+value);

					listAdapter.notifyDataSetChanged();
					drawingImageView.addPoint(p,zone);
					total.setText(""+ listAdapter.getTotal());
				}else{
					System.out.println("datasource NOT ok ");
				}
			}else{
				drawingImageView.invalidate();
			}
		}
		refreshVolee();
	}



	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.blasonok:
			if (curScore.getV().size() == Score.NOMBREMAX) {
				curScore = new Score(tir.getId());
				curScore = datasource.createScore(curScore);
				listAdapter.add(curScore);
				listAdapter.notifyDataSetChanged();
				counttext.setText(""+ listAdapter.getCount());
			}

			break;

		case R.id.blasonmoins:
			if (curScore != null) {
				if (curScore.getV().size() > 0){
					curScore.deleteLast();
					datasource.updateScore(curScore);
					listAdapter.notifyDataSetChanged();
					drawingImageView.removeLastPoint();
				}

			}
			break;



		}

		listAdapter.notifyDataSetChanged();
		total.setText(""+ listAdapter.getTotal());
		refreshVolee();
	}


	void refreshVolee(){
		voleetext.setText("");
		String str = "";

		for (int i = 0 ; i < 6 ; i++){
			//une constante pour le 6 !!
			int points =curScore.getScoreAt(i);
			if (points >= 0) {

				if (points == 100) {
					str += "X";
				}else  if (points == 0) {
					str += "M";

				} else {
					str += points;
				}

				if (i < 6-1) {
					str += "-";
				}

			}else{
				str += "*";
			}

		}
		voleetext.setText(str);


	}


	public enum Blason{
		FITA ("FITA",0) ,	
		FITAReduce ("FITA rŽduit",1),
		TriSpot ("TriSpot",2),
		Campagne ("Campagne",3),
		CampagneDouble ("Campagne double",4),
		CampagneTriple ("TriSpot campagne",5),
		Beursault ("Beursault",6)
		//		,Nature ("Nature",7),
		//		ThreeD ("3D",8),
		//		TriSpotVegas ("TriSpot VŽgas",9)
		;


		private String name = "";
		private int value;


		Blason(String name,int value){
			this.name = name;
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
		public String toString(){
			return name;
		}
	}


	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		//		vectBlason.add(Blason.FITA.toString());
		//		vectBlason.add(Blason.FITAReduce.toString());
		//		vectBlason.add(Blason.TriSpot.toString());
		//		vectBlason.add(Blason.Campagne.toString());
		//		vectBlason.add(Blason.CampagneDouble.toString());
		//		vectBlason.add(Blason.CampagneTriple.toString());
		//		vectBlason.add(Blason.Beursault.toString());

		System.out.println("itemPosition = "+itemPosition);
		if (itemPosition != tir.getBlasonType()){
			tir.setBlasonType(itemPosition);
			datasource.update(tir);
	
			buildBlason(itemPosition);
			switch(itemPosition){
			case 0: //FITA
	
	
				break;
			case 1: //FITAReduce
	
				break;
			case 2: //TriSpot
				break;
			case 3: //Campagne
				break;
			case 4: //CampagneDouble
				break;
			case 5: //CampagneTriple
				break;
			case 6: //Beursault
				break;
	
			}
		}
		return true;
	};

}