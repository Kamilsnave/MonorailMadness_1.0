package com.mstudios.monorailmadnessand.entities;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.mstudios.monorailmadnessand.Entity;
import com.mstudios.monorailmadnessand.GameEntity;
import com.mstudios.monorailmadnessand.GameParams;
import com.mstudios.monorailmadnessand.GameView;
import com.mstudios.monorailmadnessand.Sprite;
import com.mstudios.monorailmadnessand.Vector2f;
import com.mstudios.monorailmadnessand.scenes.GameScene;
import com.mstudios.monorailmadnessand.scenes.StoryScene;

public class TitleEntity extends GameEntity{
	Sprite sprite;
	public TitleEntity(GameScene gameScene, int layer) {
		super(gameScene, layer);
		setSize(new Vector2f(270, 200));
		setDisplacement(new Vector2f(GameParams.getWindow().getX()/2 - getSize().getX(), 20));
		setVelocity(new Vector2f(0, 0));
		setHitBoxOffset(new Vector2f(0, 0));
		setHitBoxSize(getSize());
		
		sprite = new Sprite("CEILINGCAT");
		sprite.setDimensions(500, 400);
	}
	
	@Override
	public void handleCollision(Entity e1){
		if(e1.getType() == "FURBALL"){
			gameScene.getMediaPlayer().pause();
			gameScene.getManager().addScene(GameView.STORY_SCENE, new StoryScene(gameScene.getManager(), "STORY_SCENE"));
			gameScene.getManager().setScene(GameView.STORY_SCENE);
		}
	}
	
	public void render(Canvas canvas, Paint paint){
		sprite.drawSprite(getDisplacement(), canvas, paint);
		paint.setColor(Color.WHITE);
		paint.setTextSize(64.0f);
		canvas.drawText("Welcome to Monorail Madness!", 500, 500, paint);
		canvas.drawText("Touch the monorail anywhere to change direction.", 300, 500 + 64, paint);
		canvas.drawText("Shoot ceiling cat to continue...", 520, 500 + 64*2, paint);
	}

}
