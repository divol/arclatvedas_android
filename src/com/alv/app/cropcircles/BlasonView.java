package com.alv.app.cropcircles;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;

//http://developer.android.com/training/custom-views/create-view.html


public class BlasonView extends AbstractBlasonView {
	
	float centreX;
	float centreY;
//	PointF centerPoint=new PointF(0,0);
	
	int[] scores = {100,10,9,8,7,6,5,4,3,2,1};

	int[] scoresimperial = {9,9,9,7,7,5,5,3,3,1,1};


	 
	 
	public BlasonView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		nombrezone = 10;
		radiusCircles = new float[nombrezone+1];

		colors = new int[nombrezone+1];
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


		colorlines = new int[nombrezone+1];
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
		
		Canvas canvas = new Canvas(bitmap);

		// Circle
		 taille = (float) Math.min(bitmap.getWidth(),bitmap.getHeight());
		centreX=  taille  / 2;
		centreY=  taille / 2;
		
		PointF center =  new PointF(centreX,centreY);
		drawFace(canvas,center,taille,0);


	}




	public Point getScoreForPoint(float ptX, float ptY){
		Point result = new Point(0,0);
		int x=nombrezone;
		do {
			float radius = radiusCircles[nombrezone-x];
			if (pointIntoCircle(centreX,centreY,radius,ptX,ptY)) {
				result.x = scores[nombrezone-x];
				return result;

			}
			x--;
		}while (x > -1);

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

			if (zoomPos.y > centreX-100){
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
