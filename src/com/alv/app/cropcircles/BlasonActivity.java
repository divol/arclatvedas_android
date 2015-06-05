package com.alv.app.cropcircles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import com.alv.app.PageListActivity;
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
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


///ref : http://stackoverflow.com/questions/13864480/android-how-to-circular-zoom-magnify-part-of-image

/// http://www.java2s.com/Code/Android/2D-Graphics/Drawacircle.htm
public class BlasonActivity extends Activity  implements BlasonInterface,OnClickListener {
	BlasonView drawingImageView;
	TirDataSource datasource;
	Tir tir;
	VoleeArrayAdapter listAdapter;

	private Score curScore = null;
	private TextView total;
	private TextView voleetext;
	private TextView  counttext;



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
		drawingImageView = (BlasonView) this.findViewById(R.id.DrawingImageView);
		drawingImageView.delegate = this;


		//action bar

		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // pour activer le retour Home


		total = (TextView)findViewById(R.id.totalBlason);
		total.setText(""+ listAdapter.getTotal());

		voleetext = (TextView)findViewById(R.id.voleeText);
		refreshVolee();

		counttext = (TextView)findViewById(R.id.countText);
		counttext.setText(""+ listAdapter.getCount());
		
		
		for (Score score : scores){
			for (int i = 0 ; i < 6 ; i++){
				PointF p = score.getPointAt(i);
				drawingImageView.addPoint(p);
			}
		}
		drawingImageView.invalidate();
		
		Button b = (Button) findViewById(R.id.blasonok);
		b.setOnClickListener(this);

		b = (Button) findViewById(R.id.blasonmoins);
		b.setOnClickListener(this);
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




	public void managePanEnd(PointF p, int value){
		if (curScore != null) {
			if (curScore.addScore(value,p)){
				System.out.println("score1="+value);
				if (datasource != null){
					System.out.println("datasource= ok ");
					datasource.updateScore(curScore);
					System.out.println("score2="+value);
	
					listAdapter.notifyDataSetChanged();
					drawingImageView.addPoint(p);
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

	

}