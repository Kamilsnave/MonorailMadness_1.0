package com.mstudios.monorailmadnessand.entities;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.mstudios.monorailmadnessand.EnemyEntity;
import com.mstudios.monorailmadnessand.GameParams;
import com.mstudios.monorailmadnessand.ResourceManager;
import com.mstudios.monorailmadnessand.Sprite;
import com.mstudios.monorailmadnessand.Vector2f;
import com.mstudios.monorailmadnessand.scenes.GameScene;

public class LongCat extends EnemyEntity{

	private Sprite sprite;
	
	public LongCat(GameScene gameScene, int layer) {
		super(gameScene, layer);
		
		setLife(3);
		setScore(1000);
		setSize(new Vector2f(200, 400));
		setDisplacement(new Vector2f((float) (Math.random()*GameParams.getWindow().getX()), -getSize().getY()));
		setVelocity(new Vector2f(0, 100.0f));
		setAcceleration(new Vector2f(0, 0));
		setHitBoxOffset(new Vector2f(0, 0));
		setHitBoxSize(getSize());
		
		sprite = new Sprite("LONGCAT");
		sprite.setDimensions(getSize().getX(), getSize().getY());
		
		mediaPlayer = ResourceManager.getAudio("DEATH0");
	}
	
	@Override
	public void render(Canvas canvas, Paint paint){
		sprite.drawSprite(getDisplacement(), canvas, paint);
	}
	
	@Override
	public void kill(){
		mediaPlayer.start();
		super.kill();
	}
}
