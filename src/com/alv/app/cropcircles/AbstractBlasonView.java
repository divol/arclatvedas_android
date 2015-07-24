package com.alv.app.cropcircles;

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
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public abstract class AbstractBlasonView extends ImageView{
	public BlasonInterface delegate;
	
	float delta;

	float decalageloupe = 50;

	float [] radiusCircles;

	int[] colors;
	int[] colorlines;
	PointF zoomPos = new PointF();
	PointF start = new PointF();
	PointF mid = new PointF();
	Boolean zooming;
	Bitmap bitmap;
	BitmapShader mShader;
	Paint mPaint;
	int barheight;
	Matrix mat = new Matrix();
	Paint greenpaint;
	Paint blackpaint;
	Paint whitepaint;
	float taille;
	
	//impacts
	 Vector<PointF> impacts = new Vector<PointF>() ;
	 Vector<Point> zones = new Vector<Point>() ;

	
	public AbstractBlasonView(Context context, AttributeSet attrs) {
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

		zoomPos = new PointF(0,0);
		zooming=false;


		Activity act = (Activity)context;

		Bitmap bitmap = Bitmap.createBitmap((int) act.getWindowManager()
				.getDefaultDisplay().getWidth(), (int) act.getWindowManager()
				.getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);

		this.setImageBitmap(bitmap);
		
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

	public static PointF PointOnCircle(float radius, float angleInDegrees, PointF origin)
	{
		// Convert from degrees to radians via multiplication by PI/180        
		float x = (float)(radius * Math.cos(angleInDegrees * Math.PI / 180F)) + origin.x;
		float y = (float)(radius * Math.sin(angleInDegrees * Math.PI / 180F)) + origin.y;

		return new PointF(x, y);
	}

	
	protected  void culcMidPoint(PointF midPoint, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		midPoint.set(x / 2, y / 2);
	}

	
	
//impacts part

	

    public void addPoint(PointF pt,Point zone ){
		PointF p = new PointF();
		if ((pt.x != pt.y) && (pt.y!= 0f)){
		// normalisation du point
			p.set(pt.x,pt.y);
			impacts.addElement(p);
			zones.addElement(zone);
		}
		invalidate();
	}

    public void removeLastPoint(){
		if (impacts.size() > 0){
			impacts.removeElementAt(impacts.size()-1);
		}
		if (zones.size() > 0){
			zones.removeElementAt(zones.size()-1);
		}

		invalidate();
	}
    
    public abstract int getScoreForPoint(float ptX, float ptY);
}