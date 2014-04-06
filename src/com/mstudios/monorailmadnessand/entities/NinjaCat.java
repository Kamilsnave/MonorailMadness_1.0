package com.mstudios.monorailmadnessand.entities;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.mstudios.monorailmadnessand.EnemyEntity;
import com.mstudios.monorailmadnessand.GameParams;
import com.mstudios.monorailmadnessand.ResourceManager;
import com.mstudios.monorailmadnessand.Sprite;
import com.mstudios.monorailmadnessand.Vector2f;
import com.mstudios.monorailmadnessand.scenes.GameScene;

public class NinjaCat extends EnemyEntity{

	private Sprite sprite;
	
	public NinjaCat(GameScene gameScene, int layer) {
		super(gameScene, layer);
		
		float disX = (float) (Math.random()*GameParams.getWindow().getX());
		float disY = -getSize().getY();
		
		setLife(2);
		setScore(1500);
		setSize(new Vector2f(200, 200));
		setDisplacement(new Vector2f(disX, disY));
		
		setVelocity(new Vector2f((gameScene.monorailCat.getDisplacement().getX() - disX)
				/(GameParams.getWindow().getX()*.003f),(gameScene.monorailCat.getDisplacement().getY()-disY)
				/(GameParams.getWindow().getY()*.003f)));
		setAcceleration(new Vector2f(0, 0));
		setHitBoxOffset(new Vector2f(0, 0));
		setHitBoxSize(getSize());
		
		sprite = new Sprite("NINJACAT");
		sprite.setDimensions(getSize().getX(), getSize().getY());
		
		mediaPlayer = ResourceManager.getAudio("DEATH2");
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

