package com.alv.app.cropcircles;

import android.app.Service;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

public class TrispotView extends AbstractBlasonView {

	
//	float centreX;
//	float centreY;
	//PointF centerPoint=new PointF(0,0);
	PointF[] centers;

	int[] scores = {100,10,9,8,7,6};
	

	
	
	
	public TrispotView(Context context, AttributeSet attrs) {
		super(context, attrs);
		nombrezone = 5;

		radiusCircles = new float[nombrezone+1];

		colors = new int[nombrezone+1];
		colors[0] = Color.YELLOW;
		colors[1] = Color.YELLOW;
		colors[2] = Color.YELLOW;
		colors[3] = Color.RED;
		colors[4] = Color.RED;
		colors[5] = Color.BLUE;


		colorlines = new int[nombrezone+1];
		colorlines[0] = Color.BLACK;
		colorlines[1] = Color.BLACK;
		colorlines[2] = Color.BLACK;
		colorlines[3] = Color.BLACK;
		colorlines[4] = Color.BLACK;
		colorlines[5] = Color.WHITE;
		
		Canvas canvas = new Canvas(bitmap);
		centers = new PointF[3];
		
		// Circle
		float tailleMin = (float) Math.min(bitmap.getWidth(),bitmap.getHeight());
		 taille = tailleMin;
		 taille =  (tailleMin / 3.0f);
		 
		// deltaX = (tailleMin - taille / 2.0f);
		 deltaX = 0.0f;
		// http://stackoverflow.com/questions/18268218/change-screen-orientation-programatically-using-a-button
//		 Display display = ((WindowManager) context.getSystemService(Service.WINDOW_SERVICE)).getDefaultDisplay();
//		 final int orientation = display.getRotation(); 
		 
		// Activity.getResources().getConfiguration().orientation
		 
		 
		  // OR: orientation = getRequestedOrientation(); // inside an Activity

		 
		 
		float centreX= deltaX+  (taille / 2.0f);
		float centreY= (taille / 2.0f);
		for (int i =0 ; i<3;i++){
			//3 centers
			centers[i] = new PointF(centreX,centreY+(i*taille));
			drawFace(canvas,centers[i],taille,i);

		}
				
				
		
	
	}

	@Override
	public Point getScoreForPoint(float ptX, float ptY) {

		Point result = new Point(0,0);
		
		for (int zone = 0; zone <3; zone++){
		int x=nombrezone;
		do {
			float radius = radiusCircles[nombrezone-x];
			if (pointIntoCircle(centers[zone].x,centers[zone].y,radius,ptX,ptY)) {
				result.x = scores[nombrezone-x];
				result.y = zone;
				return result;

			}
			x--;
		}while (x > -1);
		}
		return result;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction()  & MotionEvent.ACTION_MASK; 

		zoomPos.x = event.getX();
		zoomPos.y = event.getY();
		//culcMidPoint(mid,event);

		switch (action) { 
		case MotionEvent.ACTION_POINTER_DOWN:

			culcMidPoint(mid,event);
			break;          

		case MotionEvent.ACTION_DOWN:
			start.set(event.getX(), event.getY());
		case MotionEvent.ACTION_MOVE:
			zooming = true;

			if (zoomPos.y > centers[1].y-100){
				decalageloupe = 100;
			}else{
				decalageloupe = -100;
			}

			mat.reset();
			//m.setTranslate(zoomPos.x-start.x,zoomPos.y-start.y);
			mat.postScale(2f, 2f,zoomPos.x,zoomPos.y+decalageloupe);


			mPaint.getShader().setLocalMatrix(mat);





			invalidate();
			break; 
		case MotionEvent.ACTION_UP: 
			zooming = false;
			PointF p = new PointF();
			Point zone = new Point();
			// normalisation du point
			p.set(zoomPos.x/taille,zoomPos.y/taille);
			Point result = getScoreForPoint(zoomPos.x,zoomPos.y);
			zone.set(result.y, 0);
			delegate.managePanEnd(p,result.x,zone);
			break; 
		case MotionEvent.ACTION_CANCEL:
			zooming = false;
			invalidate();
			break; 

		default: 
			break; 
		}



		return true; 
	} 

	
	
	
}
