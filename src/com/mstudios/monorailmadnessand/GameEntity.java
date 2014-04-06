package com.mstudios.monorailmadnessand;

import com.mstudios.monorailmadnessand.scenes.GameScene;

public class GameEntity extends Entity{
	
	public GameScene gameScene;
	public GameEntity(GameScene gameScene, int layer){
		super(gameScene, layer);
		this.gameScene = gameScene;
	}

}
