package com.mstudios.monorailmadnessand.entities;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;

import com.mstudios.monorailmadnessand.R;
import com.mstudios.monorailmadnessand.GameEntity;
import com.mstudios.monorailmadnessand.GameParams;
import com.mstudios.monorailmadnessand.Input;
import com.mstudios.monorailmadnessand.ResourceManager;
import com.mstudios.monorailmadnessand.Scene;
import com.mstudios.monorailmadnessand.Sprite;
import com.mstudios.monorailmadnessand.Vector2f;
import com.mstudios.monorailmadnessand.scenes.GameScene;

public class NyanCat extends GameEntity{
	
	NyanFurBall nyanAmmo;
	private Sprite sprite;
	MediaPlayer mediaPlayer;
	
	public NyanCat(GameScene gameScene, int layer) {
		super(gameScene, layer);
		
		setSize(new Vector2f(540,175));
		setDisplacement(new Vector2f(-getSize().getX(), (GameParams.getWindow().getY()/2f) - getSize().scale(0, 0.5f).getY()));
		setVelocity(new Vector2f(800f, 0));
		setAcceleration(new Vector2f(0, 0));
		setHitBoxOffset(new Vector2f(0, 0));
		setHitBoxSize(getSize());
		
		sprite = new Sprite("NYANCAT");
		sprite.setDimensions(getSize().getX(), getSize().getY());
		
		gameScene.getMediaPlayer().pause();
		
		mediaPlayer = ResourceManager.getAudio("NYANCAT_AUDIO");
		mediaPlayer.start();
	}
	
	@Override
	public void render(Canvas canvas, Paint paint){
		sprite.drawSprite(getDisplacement(), canvas, paint);
	}
	
	@Override
	public void update(float dt, Input input){
		super.update(dt, input);
		handleExplosion();
	}
	public void handleExplosion(){
		if(getDisplacement().getX() >= GameParams.getWindow().getX()/2 - getSize().scale(0.5f, 0).getX()){
			setVelocity(getVelocity().scale(0,0));
			
			Vector2f[] vec = new Vector2f [8];
			vec[0] = new Vector2f(1,0).unit().scale(1000,1000);
			vec[1] = new Vector2f(1,1).unit().scale(1000,1000);
			vec[2] = new Vector2f(0,1).unit().scale(1000,1000);
			vec[3] = new Vector2f(-1,1).unit().scale(1000,1000);
			vec[4] = new Vector2f(-1,0).unit().scale(1000,1000);
			vec[5] = new Vector2f(-1,-1).unit().scale(1000,1000);
			vec[6] = new Vector2f(0,-1).unit().scale(1000,1000);
			vec[7] = new Vector2f(1,-1).unit().scale(1000,1000);
			for(int i = 0; i <8;i++){
				NyanFurBall f = new NyanFurBall(gameScene, Scene.COREGROUND);
				f.setDisplacement(getDisplacement().add(getSize().scale(0.5f, .5f)));
				f.setVelocity(vec[i]);
				gameScene.addEntity(f);
			}
			
			kill();
		}
	}
	public void kill(){
		gameScene.getMediaPlayer().start();
		gameScene.removeEntity(this);
			
	}

}
