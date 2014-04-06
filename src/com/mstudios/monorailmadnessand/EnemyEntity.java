package com.mstudios.monorailmadnessand;

import com.mstudios.monorailmadnessand.scenes.GameScene;

public class EnemyEntity extends GameEntity{

	int score;
	int life;
	
	public EnemyEntity(GameScene gameScene, int layer) {
		super(gameScene, layer);
		
		type = "ENEMY";
		score = 0;
		life = 1;
	}
	
	@Override
	public void handleCollision(Entity e1){
		if(e1.getType() == "FURBALL"){
			life--;
			e1.kill();
			if(life <= 0){	
				kill();
			}
		}
	}
	
	@Override
	public void kill(){
		gameScene.addScore(score);
		gameScene.addEntity(new IndicatorEntity(gameScene, Scene.FOREGROUND, "+" + score));
		scene.removeEntity(this);
	}
	
	@Override
	public void handleWorldBoundaries(){
		if(this.getDisplacement().getX() <= -this.getSize().getX() ||
				this.getDisplacement().getX() > GameParams.getWindow().getX() ||
				this.getDisplacement().getY() > GameParams.getWindow().getY()){
			scene.removeEntity(this);
		}
		
	}
	
	public void setLife(int life){
		this.life = life;
	}
	
	public void setScore(int score){
		this.score = score;
	}

}
