package com.alv.app.cropcircles;

import java.util.ArrayList;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

//http://developer.android.com/training/custom-views/create-view.html


public class BlasonView extends ImageView {

	
	public BlasonInterface delegate;
	float centreX;
	float centreY;
	PointF centerPoint=new PointF(0,0);
	float delta;

	float decalageloupe = 50;

	float [] radiusCircles;

	int[] colors;
	int[] colorlines;
	int[] scores = {100,10,9,8,7,6,5,4,3,2,1};
	PointF zoomPos = new PointF();
	PointF start = new PointF();
	PointF mid = new PointF();
	Boolean zooming;
	BitmapShader mShader;
	Paint mPaint;
	int barheight;
	Matrix m = new Matrix();
	Paint greenpaint;
	Paint blackpaint;
	Paint whitepaint;
	float taille;


	//impacts
	Vector<PointF> impacts = new Vector<PointF>() ;

	public BlasonView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setClickable(true);


		greenpaint = new Paint();
		greenpaint.setStyle(Paint.Style.STROKE);

		greenpaint.setColor(Color.GREEN);

		blackpaint = new Paint();
		blackpaint.setStyle(Paint.Style.STROKE);

		blackpaint.setColor(Color.BLACK);

		whitepaint = new Paint();
		whitepaint.setStyle(Paint.Style.STROKE);
		whitepaint.setColor(Color.WHITE);


		setScaleType(ScaleType.MATRIX);
		if (this.isInEditMode()){
			return;
		}


		Resources resources = context.getResources();
		int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
		if (resourceId > 0) {
			barheight =  resources.getDimensionPixelSize(resourceId);
		}
		barheight =  0;

		System.out.println("barheight="+barheight);

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
		Activity act = (Activity)context;

		Bitmap bitmap = Bitmap.createBitmap((int) act.getWindowManager()
				.getDefaultDisplay().getWidth(), (int) act.getWindowManager()
				.getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmap);
		this.setImageBitmap(bitmap);

		// Circle
		 taille = (float) Math.min(bitmap.getWidth(),bitmap.getHeight());
		centreX=  taille  / 2;
		centreY=  taille / 2;
		centerPoint.x= centreX;
		centerPoint.y= centreY;

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




		delta = taille/10;
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

		radiusCircles[0] = lastradius;
		paint.setColor(colors[0]);
		paint.setStyle(Paint.Style.FILL);
		canvas.drawCircle(x, y, lastradius, paint);

		paint.setColor(colorlines[0]);
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawCircle(x, y, lastradius, paint);


		zoomPos = new PointF(0,0);
		zooming=false;



		mShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);

		mPaint = new Paint();
		mPaint.setShader(mShader);

	}

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
			float radius = radiusCircles[10-x];
			if (pointIntoCircle(centreX,centreY,radius,ptX,ptY)) {
				result = scores[10-x];
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

			if (zoomPos.y > centreX+10){
				decalageloupe = 50;
			}else{
				decalageloupe = -50;
			}

			m.reset();
			//m.setTranslate(zoomPos.x-start.x,zoomPos.y-start.y);
			m.postScale(2f, 2f,zoomPos.x,zoomPos.y+decalageloupe);


			mPaint.getShader().setLocalMatrix(m);





			invalidate();
			break; 
		case MotionEvent.ACTION_UP: 
			zooming = false;
			PointF p = new PointF();
			
			// normalisation du point
			p.set(zoomPos.x/taille,zoomPos.y/taille);
		
			delegate.managePanEnd(p,	getScoreForPoint(zoomPos.x,zoomPos.y));
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

	private void culcMidPoint(PointF midPoint, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		midPoint.set(x / 2, y / 2);
	}


	public static PointF PointOnCircle(float radius, float angleInDegrees, PointF origin)
	{
		// Convert from degrees to radians via multiplication by PI/180        
		float x = (float)(radius * Math.cos(angleInDegrees * Math.PI / 180F)) + origin.x;
		float y = (float)(radius * Math.sin(angleInDegrees * Math.PI / 180F)) + origin.y;

		return new PointF(x, y);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		if (this.isInEditMode()){
			return;
		}

		for (PointF pinpact : impacts){
			blackpaint.setStyle(Paint.Style.FILL);
			canvas.drawCircle(pinpact.x*taille, pinpact.y*taille, 3, blackpaint);
			canvas.drawCircle(pinpact.x*taille, pinpact.y*taille, 4, whitepaint);

		}

		if (zooming) {

			canvas.drawCircle(zoomPos.x, zoomPos.y-decalageloupe , 50, mPaint);
			canvas.drawCircle(zoomPos.x, zoomPos.y-decalageloupe, 50, greenpaint);

			//reticule
			canvas.drawLine(zoomPos.x-8, zoomPos.y-decalageloupe, zoomPos.x+8, zoomPos.y-decalageloupe, greenpaint);
			canvas.drawLine(zoomPos.x, zoomPos.y-(decalageloupe-8), zoomPos.x, zoomPos.y-(decalageloupe +8), greenpaint);

		}
		
	}


	//impacts part

	

    public void addPoint(PointF pt){
		PointF p = new PointF();
		
		// normalisation du point
		p.set(pt.x,pt.y);
		impacts.addElement(p);
		invalidate();
	}

    public void removeLastPoint(){
		if (impacts.size() > 0){
			impacts.removeElementAt(impacts.size()-1);
		}

		invalidate();
	}

}
