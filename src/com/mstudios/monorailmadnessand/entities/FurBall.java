package com.mstudios.monorailmadnessand.entities;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.mstudios.monorailmadnessand.GameEntity;
import com.mstudios.monorailmadnessand.GameParams;
import com.mstudios.monorailmadnessand.Sprite;
import com.mstudios.monorailmadnessand.Vector2f;
import com.mstudios.monorailmadnessand.scenes.GameScene;

public class FurBall extends GameEntity{
	
	private Sprite sprite;
	public FurBall(GameScene gameScene, int layer) {
		super(gameScene, layer);
		
		setSize(new Vector2f(50, 50));
		setHitBoxOffset(new Vector2f(0, 0));
		setHitBoxSize(getSize());
		setType("FURBALL");

		sprite = new Sprite("FURBALL");
		sprite.setDimensions(getSize().getX(), getSize().getY());
	}
	
	@Override
	public void render(Canvas canvas, Paint paint){
		sprite.drawSprite(getDisplacement(), canvas, paint);
	}
	
	
	@Override
	public void handleWorldBoundaries(){
		if(this.getDisplacement().getX() <= -this.getSize().getX() ||
				this.getDisplacement().getX() > GameParams.getWindow().getX() ||
				this.getDisplacement().getY() > GameParams.getWindow().getY() ||
				this.getDisplacement().getY() < -this.getSize().getY()){
			kill();
		}
		
	}
	
	
	@Override
	public void kill(){
		gameScene.removeEntity(this);
	}
	
	@Override
	public void removedFromScene(){
		gameScene.monorailCat.returnAmmo();
	}
	

}
