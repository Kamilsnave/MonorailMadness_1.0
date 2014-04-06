package com.mstudios.monorailmadnessand.entities;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.mstudios.monorailmadnessand.EnemyEntity;
import com.mstudios.monorailmadnessand.GameParams;
import com.mstudios.monorailmadnessand.Input;
import com.mstudios.monorailmadnessand.ResourceManager;
import com.mstudios.monorailmadnessand.Scene;
import com.mstudios.monorailmadnessand.Sprite;
import com.mstudios.monorailmadnessand.Vector2f;
import com.mstudios.monorailmadnessand.scenes.GameScene;

public class HoverCat extends EnemyEntity{
	
	float time;
	private Sprite sprite;
	public HoverCat(GameScene gameScene, int layer) {
		super(gameScene, layer);
		
		setLife(2);
		setScore(2250);
		setSize(new Vector2f(270, 200));
		setDisplacement(new Vector2f(GameParams.getWindow().getX(), (float)((Math.random()*0.5*GameParams.getWindow().getY()))));
		setVelocity(new Vector2f(-100f, 0));
		setHitBoxOffset(new Vector2f(0, 0));
		setHitBoxSize(getSize());
		
		time = 0;

		sprite = new Sprite("HOVERCAT");
		sprite.setDimensions(getSize().getX(), getSize().getY());
		
		mediaPlayer = ResourceManager.getAudio("DEATH1");
	}
	
	@Override
	public void render(Canvas canvas, Paint paint){
		sprite.drawSprite(getDisplacement(), canvas, paint);
	}
	
	@Override
	public void update(float dt, Input input){
		super.update(dt, input);
		
		time += dt;
		if(time >= 1){
			HoverFurBall f = new HoverFurBall(gameScene, Scene.COREGROUND);
			f.setDisplacement(getDisplacement().add(getSize().scale(0, 1)));
			gameScene.addEntity(f);
			time = 0;
		}
	}

	@Override
	public void kill(){
		mediaPlayer.start();
		super.kill();
	}
}
