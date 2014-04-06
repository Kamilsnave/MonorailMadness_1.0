package com.mstudios.monorailmadnessand.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.mstudios.monorailmadnessand.EnemyEntity;
import com.mstudios.monorailmadnessand.GameEntity;
import com.mstudios.monorailmadnessand.GameParams;
import com.mstudios.monorailmadnessand.ResourceManager;
import com.mstudios.monorailmadnessand.Sprite;
import com.mstudios.monorailmadnessand.Vector2f;
import com.mstudios.monorailmadnessand.scenes.GameScene;

public class Tree extends GameEntity{

	Sprite sprite;
	public Tree(GameScene gameScene, int layer, String typeOb) {
		super(gameScene, layer);

		float rand = (float)Math.random()*0.5f + 0.5f;
		
		

		
		sprite = new Sprite(typeOb);
		float scalX = rand *sprite.getBitmapWidth();
		float scalY = rand*sprite.getBitmapHeight();
		sprite.setDimensions(scalX, scalY);
 
		setSize(new Vector2f(scalX, scalY));
		setDisplacement(new Vector2f(GameParams.getWindow().getX(), GameParams.getWindow().getY()-scalY));
		setVelocity(new Vector2f(-scalX*rand*3, 0));
		setAcceleration(new Vector2f(0, 0));
		setHitBoxOffset(new Vector2f(0, 0));
		setHitBoxSize(getSize());

		
	}
	
	public void render(Canvas canvas, Paint paint){
		sprite.drawSprite(getDisplacement(), canvas, paint);
			
	}

}