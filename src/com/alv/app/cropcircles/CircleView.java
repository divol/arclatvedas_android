package com.alv.app.cropcircles;

import java.util.Arrays;
import java.util.Vector;

import com.alv.app.PageListActivity;
import com.alv.app.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;


///ref : http://stackoverflow.com/questions/13864480/android-how-to-circular-zoom-magnify-part-of-image

/// http://www.java2s.com/Code/Android/2D-Graphics/Drawacircle.htm
public class CircleView extends Activity {
	  ImageView drawingImageView;
	  float centreX;
	  float centreY;
	  float [] radiusCircles;

	  int[] colors;
	  int[] colorlines;
	  int[] scores = {100,10,9,8,7,6,5,4,3,2,1};
	  
	  
	  public static boolean pointIntoCircle(float centX,float centY, float radius, float ptx, float pty){

		  float a = centX - ptx;
		  float b = centY - pty;

		  double c = Math.sqrt( a*a + b*b );
		  
		  return (c <= radius);
	  }
	  
	  
	  
	    public int getScoreForPoint(float ptX, float ptY){
	    	int result = 0;
	        int x=10;
	        do {
	            float radius = radiusCircles[x];
	            
	            if (pointIntoCircle(centreX,centreY,radius,ptX,ptY)) {
	                result = scores[10-x];
	                return result;
	                
	            }
	            x--;
	        }while (x > -1);
	        
	        return result;
	    }

	    
	    
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	       radiusCircles = new float[11];

		   colors = new int[11];
		   colors[0] = Color.YELLOW;
		   colors[1] = Color.YELLOW;
		   colors[2] = Color.YELLOW;
		   colors[3] = Color.RED;
		   colors[4] = Color.RED;
		   colors[5] = Color.BLUE;
		   colors[6] = Color.BLUE;
		   colors[7] = Color.BLACK;
		   colors[8] = Color.BLACK;
		   colors[9] = Color.WHITE;
		   colors[10] = Color.WHITE;

		    
		   colorlines = new int[11];
		   colorlines[0] = Color.BLACK;
		   colorlines[1] = Color.BLACK;
		   colorlines[2] = Color.BLACK;
		   colorlines[3] = Color.BLACK;
		   colorlines[4] = Color.WHITE;
		   colorlines[5] = Color.WHITE;
		   colorlines[6] = Color.WHITE;
		   colorlines[7] = Color.WHITE;
		   colorlines[8] = Color.BLACK;
		   colorlines[9] = Color.BLACK;
		   colorlines[10] = Color.BLACK;

		   
		   
	    setContentView(R.layout.activity_circleview);
	    drawingImageView = (ImageView) this.findViewById(R.id.DrawingImageView);
	    Bitmap bitmap = Bitmap.createBitmap((int) getWindowManager()
	        .getDefaultDisplay().getWidth(), (int) getWindowManager()
	        .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
	    Canvas canvas = new Canvas(bitmap);
	    drawingImageView.setImageBitmap(bitmap);

	    // Circle
	   float taille = (float) Math.min(bitmap.getWidth(),bitmap.getHeight());
	     centreX=drawingImageView.getX() +  taille  / 2;
	     centreY=drawingImageView.getY() + taille / 2;

	    
	    Paint paint = new Paint();
	    paint.setColor(Color.WHITE);
	    paint.setStyle(Paint.Style.FILL);
	    float x = centreX;
	    float y = centreY;
	    float radius = taille/2 ;
	    canvas.drawCircle(x, y, radius, paint);
	    
	    paint.setColor(Color.BLACK);
	    paint.setStyle(Paint.Style.STROKE);
	    canvas.drawCircle(x, y, radius, paint);
	    
	    
	   
	   
	    float delta = taille/10;
	    int X = 10;
	    float lastradius = 10;
	    do {
	    	if (X != 0){
	    		radiusCircles[X] = radius;
	    	}
		    paint.setColor(colors[X]);
		    paint.setStyle(Paint.Style.FILL);
		    canvas.drawCircle(x, y, radius, paint);
		    
		    paint.setColor(colorlines[X]);
		    paint.setStyle(Paint.Style.STROKE);
		    canvas.drawCircle(x, y, radius, paint);
            if (X == 1){
            	lastradius = radius;
            }
		     radius -= delta/2;

	    	X--;
	    }while (X > -1);
	    
	    lastradius -= delta/4;
	    
	    radiusCircles[X] = lastradius;
	    paint.setColor(colors[0]);
	    paint.setStyle(Paint.Style.FILL);
	    canvas.drawCircle(x, y, lastradius, paint);
	    
	    paint.setColor(colorlines[0]);
	    paint.setStyle(Paint.Style.STROKE);
	    canvas.drawCircle(x, y, lastradius, paint);

	    
	    
	    
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // pour activer le retour Home


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
	  
	  
	}