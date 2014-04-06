package com.mstudios.monorailmadnessand.powerups;

import com.mstudios.monorailmadnessand.Cheeseburger;
import com.mstudios.monorailmadnessand.IndicatorEntity;
import com.mstudios.monorailmadnessand.Scene;
import com.mstudios.monorailmadnessand.entities.MonorailCat;
import com.mstudios.monorailmadnessand.entities.NyanCat;
import com.mstudios.monorailmadnessand.scenes.GameScene;

public class NyanBurger extends Cheeseburger{
	public NyanBurger(GameScene gameScene, int layer) {
		super(gameScene, layer);
	}
	
	@Override
	public void applyModifier(MonorailCat monorailCat){
		gameScene.addEntity(new NyanCat(gameScene, Scene.COREGROUND));
		gameScene.addEntity(new IndicatorEntity(gameScene, Scene.FOREGROUND, "NYANCAT!!!"));
	}
}
