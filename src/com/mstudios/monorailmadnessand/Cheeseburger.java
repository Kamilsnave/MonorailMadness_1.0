package com.mstudios.monorailmadnessand;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;

import com.mstudios.monorailmadnessand.entities.MonorailCat;
import com.mstudios.monorailmadnessand.scenes.GameScene;

public class Cheeseburger extends GameEntity{
	MediaPlayer mediaPlayer;
	Sprite sprite;
	
	public Cheeseburger(GameScene gameScene, int layer) {
		super(gameScene, layer);
		
		setSize(new Vector2f(100, 80));
		setDisplacement(new Vector2f((float) (Math.random()*GameParams.getWindow().getX()), -getSize().getY()));
		setVelocity(new Vector2f(0, 200.0f));
		setHitBoxOffset(new Vector2f(0, 0));
		setHitBoxSize(getSize());
		setType("CHEESEBURGER");
		
		mediaPlayer = ResourceManager.getAudio("CHEESEBURGER");
		
		sprite = new Sprite("CHEESEBURGER");
		sprite.setDimensions(getSize().getX(), getSize().getY());
	}
	
	@Override
	public void handleCollision(Entity e1){
		if(e1.getType() == "MONORAILCAT"){
			applyModifier(gameScene.monorailCat);
			mediaPlayer.start();
			kill();
		}
	}
	
	public void applyModifier(MonorailCat monorailCat){}
	
	@Override
	public void kill(){
		gameScene.removeEntity(this);
	}
	
	@Override
	public void render(Canvas canvas, Paint paint){
		sprite.drawSprite(getDisplacement(), canvas, paint);
	}
}
