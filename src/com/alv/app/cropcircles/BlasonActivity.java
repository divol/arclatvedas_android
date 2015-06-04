package com.alv.app.cropcircles;

import java.util.Arrays;
import java.util.Vector;

import com.alv.app.PageListActivity;
import com.alv.app.R;

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
import android.view.View.OnTouchListener;
import android.widget.ImageView;


///ref : http://stackoverflow.com/questions/13864480/android-how-to-circular-zoom-magnify-part-of-image

/// http://www.java2s.com/Code/Android/2D-Graphics/Drawacircle.htm
public class BlasonActivity extends Activity  implements BlasonInterface {
	BlasonView drawingImageView;
//	PointF zoomPos = new PointF(0,0);  
//	Boolean zooming = false;



	    
	    
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    

		   
		   
	    setContentView(R.layout.activity_circleview);
	    drawingImageView = (BlasonView) this.findViewById(R.id.DrawingImageView);
	    drawingImageView.delegate = this;

	    
	    //action bar
	    
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // pour activer le retour Home

		
		
//		drawingImageView.setOnTouchListener(new OnTouchListener() {
//
//			public boolean onTouch(View view, MotionEvent event) {
//
//			    int action = event.getAction(); 
//
//			    zoomPos.x = event.getX();
//			    zoomPos.y = event.getY();
//
//			    switch (action) { 
//			    case MotionEvent.ACTION_DOWN:
//			    case MotionEvent.ACTION_MOVE:
//			        zooming = true;
//			        drawingImageView.invalidate();
//			        break; 
//			    case MotionEvent.ACTION_UP:   
//			    case MotionEvent.ACTION_CANCEL:
//			        zooming = false;
//			        drawingImageView.invalidate();
//			        break; 
//
//			    default: 
//			        break; 
//			    }
//
//			    return true; 
//			}
//
//             
//            });
		
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
	  
	  
	  
	  
	  public void managePanEnd(PointF p, int value){
		  
		  System.out.println("score="+value);
		  drawingImageView.addPoint(p);

	  }
	  
	}