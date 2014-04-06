package com.mstudios.monorailmadnessand.entities;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.mstudios.monorailmadnessand.EnemyEntity;
import com.mstudios.monorailmadnessand.GameParams;
import com.mstudios.monorailmadnessand.ResourceManager;
import com.mstudios.monorailmadnessand.Sprite;
import com.mstudios.monorailmadnessand.Vector2f;
import com.mstudios.monorailmadnessand.scenes.GameScene;

public class GrumpyCat extends EnemyEntity{

	private Sprite sprite;
	
	public GrumpyCat(GameScene gameScene, int layer) {
		super(gameScene, layer);
		
		setLife(30);
		setScore(10000);
		setSize(new Vector2f(GameParams.getWindow().getX()/3, GameParams.getWindow().getY()/1.3f));
		setDisplacement(new Vector2f(GameParams.getWindow().getX(), (GameParams.getWindow().getY()/3f)));
		setVelocity(new Vector2f(-20f, 0));
		setAcceleration(new Vector2f(0, 0));
		setHitBoxOffset(new Vector2f(0, 0));
		setHitBoxSize(getSize());

		sprite = new Sprite("GRUMPYCAT");
		sprite.setDimensions(getSize().getX(), getSize().getY());
		
		mediaPlayer = ResourceManager.getAudio("DEATH6");
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
