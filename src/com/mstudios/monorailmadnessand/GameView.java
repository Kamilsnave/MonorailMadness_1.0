package com.mstudios.monorailmadnessand;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mstudios.monorailmadnessand.scenes.SplashScene;

public class GameView extends View{
	public static final int SPLASH_SCENE = 0;
	public static final int MAIN_SCENE = 1;
	public static final int STORY_SCENE = 2;
	public static final int GAME_SCENE = 3;
	public static final int LOSE_SCENE = 4;
	
	public final boolean DEBUG_OPTIONS = false;
	
	int viewWidth;
	int viewHeight;
	
	int scaleWidth;
	int scaleHeight;
	float aspectRatio;
	
	Paint paint;

	//FPS Tracking
	long previousTime;
	long currentTime;
	long elapsedTime;
	float dt;
	int fps;
	
	SceneManager manager;
	Input input;
	
	public SharedPreferences sharedPref;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		//Non-View related initialization
		fps = 60;
		
		currentTime = System.currentTimeMillis();
		previousTime = currentTime;
		
		//Set Default Color
		paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setTextSize(48.0f);
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		
		
		
	}
	
	@Override
	public void onSizeChanged(int w, int h, int oldw, int oldh){
		
		//View related initialization
		
		ResourceManager.loadResources(this);
		
		//Get View Properties
		viewWidth = getWidth();
		viewHeight = getHeight();
		
		aspectRatio = (float)viewWidth/(float)viewHeight;
		
		scaleHeight = 1080;
		scaleWidth = (int)(scaleHeight*aspectRatio);
		
		GameParams.setWindow(scaleWidth, scaleHeight);
		
		manager = new SceneManager(this);
		
		manager.addScene(SPLASH_SCENE, new SplashScene(manager, "SPLASH_SCENE"));
		manager.setScene(SPLASH_SCENE);
		
		input = new Input(this);
		
		setOnTouchListener(new OnTouchListener() {
		    public boolean onTouch(View v, MotionEvent event) {
		    	int action = MotionEventCompat.getActionMasked(event);
		        
		    	if(action == MotionEvent.ACTION_DOWN){
		    		input.trigger(new Vector2f(event.getX(), event.getY()));
		    	}
		        
		        return true;
		    }
		});
	}
	
	
	
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		
		//Handle FPS
		currentTime = System.currentTimeMillis();
		elapsedTime = currentTime - previousTime;
		previousTime = currentTime;
		dt = (float)elapsedTime/1000.0f;
		fps = (int)(1/dt);
		
		//Scale Canvas to Proper Size
		canvas.scale((float)viewWidth/(float)scaleWidth, (float)viewHeight/(float)scaleHeight);
		
		//Update and Render Engine
		manager.update(dt, input);
		manager.render(canvas, paint);
		input.handle();
		
		
		//Render Debugging Options
		if(DEBUG_OPTIONS){
			paint.setTextSize(48.0f);
			paint.setColor(Color.GREEN);
			canvas.drawText("VIEW_WIDTH " + viewWidth, 10, 1*48, paint);
			canvas.drawText("VIEW_HEIGHT " + viewHeight, 10, 2*48, paint);
			canvas.drawText("SCALE_WIDTH " + scaleWidth, 10, 3*48, paint);
			canvas.drawText("SCALE_HEIGHT " + scaleHeight, 10, 4*48, paint);
			
			if(fps < 40) paint.setColor(Color.YELLOW);
			if(fps < 20) paint.setColor(Color.RED);
			canvas.drawText("VIEW_FPS    " + fps + " " + dt, 10, 6*48, paint);
			paint.setColor(Color.GREEN);
			
			canvas.drawText("INPUT_COOR " + (int)input.getTouchLocation().getX() + " " + (int)input.getTouchLocation().getY(), 10, 7*48, paint);
			canvas.drawText("TRIG_COUNT " + input.getTriggerCount(), 10, 8*48, paint);
			
			if(input.check()){
				paint.setColor(Color.RED);
				canvas.drawText("TRIG_ENABLED TRUE", 10, 9*48, paint);
			}
			else
			{
				paint.setColor(Color.GREEN);
				canvas.drawText("TRIG_ENABLED FALSE", 10, 9*48, paint);
			}
			
			if(manager.hasScene()){
				canvas.drawText("SCENE_TITLE " + manager.getCurrentScene().getTitle(), 10, 11*48, paint);
				canvas.drawText("BACKGROUND " + manager.getCurrentScene().getNumEntities(Scene.BACKGROUND), 10, 12*48, paint);
				canvas.drawText("COREGROUND " + manager.getCurrentScene().getNumEntities(Scene.COREGROUND), 10, 13*48, paint);
				canvas.drawText("FOREGROUND " + manager.getCurrentScene().getNumEntities(Scene.FOREGROUND), 10, 14*48, paint);
				canvas.drawText("ALL " + manager.getCurrentScene().getNumEntities(Scene.ALL), 10, 15*24, paint);
			}else{
				paint.setColor(Color.RED);
				canvas.drawText("NO SCENE AVAILABLE!", 10, 8*24, paint);
			}
		}
		
		
		this.postInvalidate();
	}
	
	public int getScaledHeight(){
		return scaleHeight;
	}
	
	public int getScaledWidth(){
		return scaleWidth;
	}
	
	public float getHeightScaling(){
		return (float)viewHeight/(float)scaleHeight;
	}
	
	public float getWidthScaling(){
		return (float)viewWidth/(float)scaleWidth;
	}

}
