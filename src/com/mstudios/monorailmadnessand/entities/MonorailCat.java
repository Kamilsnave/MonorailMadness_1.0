package com.mstudios.monorailmadnessand.entities;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.mstudios.monorailmadnessand.Entity;
import com.mstudios.monorailmadnessand.GameEntity;
import com.mstudios.monorailmadnessand.GameParams;
import com.mstudios.monorailmadnessand.GameView;
import com.mstudios.monorailmadnessand.Input;
import com.mstudios.monorailmadnessand.Scene;
import com.mstudios.monorailmadnessand.SceneManager;
import com.mstudios.monorailmadnessand.Sprite;
import com.mstudios.monorailmadnessand.Vector2f;
import com.mstudios.monorailmadnessand.scenes.GameScene;
import com.mstudios.monorailmadnessand.scenes.LoseScene;

public class MonorailCat extends GameEntity{

	private float maxAccel = 3000.0f;
	private float maxVelocity = 1000.0f;
	private int maxAmmo = 3;
	private int ammo = maxAmmo;
	private boolean hasShield = false;
	private Sprite sprite;
	private Sprite monorailSprite;
	private boolean isAlive;
	

	public MonorailCat(GameScene gameScene, int layer) {
		super(gameScene, layer);

		setSize(new Vector2f(300, 150));
		setDisplacement(new Vector2f(GameParams.getCenter().getX()/2, GameParams.getWindow().getY() - 200 ));
		setVelocity(new Vector2f(0, 0));
		setAcceleration(new Vector2f(maxAccel, 0));
		setHitBoxOffset(new Vector2f(0, 0));
		setHitBoxSize(getSize());
		setType("MONORAILCAT");
		
		isAlive = true;
		sprite = new Sprite("MONORAILCAT");
		sprite.setDimensions(getSize().getX(), getSize().getY());
		
		monorailSprite = new Sprite("MONORAIL");
		monorailSprite.setDimensions(GameParams.getWindow().getX(), 45);
		
		
	}

	@Override
	public void handleInput(Input input){
		if(input.check() && isAlive){
			if(input.getTouchLocation().getY() > 850){
				if(getVelocity().getX() > 0)
					setAcceleration(new Vector2f(-maxAccel, 0));
				else
					setAcceleration(new Vector2f(maxAccel, 0));
			}else{
				if(ammo > 0){
					FurBall f = new FurBall(gameScene, Scene.COREGROUND);
					Vector2f tempVel = input.getTouchLocation().subtract(getDisplacement().add(getSize().scale(1, 0))).unit().scale(1000.0f, 1000.0f);
					f.setDisplacement(getDisplacement().add(getSize().scale(0.5f, 0)));
					f.setVelocity(tempVel);
					gameScene.addEntity(f);
					ammo--;
				}
			}
		}
	}

	@Override
	public void update(float dt, Input input){
		super.update(dt, input);

		if(Math.abs(getVelocity().getX()) > maxVelocity){
			setAcceleration(new Vector2f(0,0));
			if(getVelocity().getX() > 0)
				setVelocity(new Vector2f(maxVelocity, 0));
			else
				setVelocity(new Vector2f(-maxVelocity, 0));
		}
	}
	

	@Override
	public void handleWorldBoundaries(){
		if(getDisplacement().getX() <= 0){
			setDisplacement(new Vector2f(1, getDisplacement().getY()));
			setVelocity(getVelocity().scale(-1,0));
			setAcceleration(new Vector2f(maxAccel, 0));
		}
		if(getDisplacement().getX() + getSize().getX() >= GameParams.getWindow().getX()){
			setDisplacement(new Vector2f(GameParams.getWindow().getX() - getSize().getX() - 1, getDisplacement().getY()));
			setVelocity(getVelocity().scale(-1,0));
			setAcceleration(new Vector2f(-maxAccel, 0));
		}
		
		if(getDisplacement().getY() <= 0 && !isAlive){
			gameScene.getMediaPlayer().pause();
			LoseScene loseScene = new LoseScene(gameScene.getManager(), "LOSE_SCENE", gameScene.getScore());
			gameScene.getManager().addScene(GameView.LOSE_SCENE, loseScene);
			gameScene.getManager().setScene(GameView.LOSE_SCENE);
		}
	}
	
	@Override
	public void handleCollision(Entity e1){
		if(e1.getType() == "ENEMY" && isAlive){
			if(!hasShield){
				isAlive = false;
				setVelocity(new Vector2f(0, -100));
				setAcceleration(new Vector2f(0, -400));
				gameScene.addEntity(new CeilingCat(gameScene, Scene.COREGROUND));
			}else{
				e1.kill();
				hasShield = false;
			}
		}
	}

	public void boostMaxAmmo(){
		maxAmmo++;
		returnAmmo();
	}

	public void returnAmmo(){
		ammo++;
	}
	
	public void addShield(){
		hasShield = true;
	}
	
	@Override
	public void kill(){
		
	}
	
	@Override
	public void render(Canvas canvas, Paint paint){
		monorailSprite.drawSprite(new Vector2f(0, GameParams.getWindow().getY()*0.91f), canvas, paint);
		sprite.drawSprite(getDisplacement(), canvas, paint);
	}
	
	public int getAmmo(){return ammo;}
	public boolean getShield(){return hasShield;}
	public boolean isAlive(){return isAlive;}

}
