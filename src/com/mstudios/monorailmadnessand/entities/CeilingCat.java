package com.mstudios.monorailmadnessand.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.mstudios.monorailmadnessand.GameEntity;
import com.mstudios.monorailmadnessand.GameParams;
import com.mstudios.monorailmadnessand.Input;
import com.mstudios.monorailmadnessand.Sprite;
import com.mstudios.monorailmadnessand.Vector2f;
import com.mstudios.monorailmadnessand.scenes.GameScene;

public class CeilingCat extends GameEntity {
	
	private Sprite sprite;
	
	public CeilingCat(GameScene gameScene, int layer) {
		super(gameScene, layer);
		
		
		setSize(new Vector2f(400, 250));
		setDisplacement(new Vector2f(gameScene.monorailCat.getDisplacement().getX() - 50, -getSize().getY()));
		
		setHitBoxOffset(new Vector2f(0, 0));
		setHitBoxSize(getSize());
		
		sprite = new Sprite("CEILINGCAT");
		sprite.setDimensions(getSize().getX(), getSize().getY());
	}
	
	@Override
	public void update(float dt, Input input){
		super.update(dt, input);
		
		setVelocity(new Vector2f(gameScene.monorailCat.getDisplacement().getX()-50, 0).subtract(getDisplacement()));
		
		/*if(getDisplacement().getY() > 500f){
			setAcceleration(new Vector2f(0,0));
			setVelocity(new Vector2f(0,0));
		}*/
	}
	
	@Override
	public void render(Canvas canvas, Paint paint){
		paint.setColor(Color.argb(100, 255, 255, 255));
		canvas.drawRect(new RectF(gameScene.monorailCat.getDisplacement().getX()-50,0,gameScene.monorailCat.getDisplacement().getX() -50 + getSize().getX(), GameParams.getWindow().getY()), paint);
		sprite.drawSprite(getDisplacement(), canvas, paint);
	}

}
