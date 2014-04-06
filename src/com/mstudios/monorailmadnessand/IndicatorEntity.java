package com.mstudios.monorailmadnessand;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.mstudios.monorailmadnessand.scenes.GameScene;

public class IndicatorEntity extends GameEntity{

	String message;
	
	public IndicatorEntity(GameScene gameScene, int layer, String message) {
		super(gameScene, layer);
		
		this.message = message;
		
		this.setDisplacement(new Vector2f(GameParams.getWindow().getX()/2, -24));
		this.setVelocity(new Vector2f(0,300));
		this.setAcceleration(new Vector2f(0,-600));
	}
	
	@Override
	public void update(float dt, Input input){
		super.update(dt, input);
		
		if(getDisplacement().getY() < -40){
			gameScene.removeEntity(this);
		}
	}
	
	@Override
	public void render(Canvas canvas, Paint paint){
		paint.setColor(Color.WHITE);
		canvas.drawText(message, getDisplacement().getX(), getDisplacement().getY(), paint);
	}

}
