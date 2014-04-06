package com.mstudios.monorailmadnessand.powerups;

import com.mstudios.monorailmadnessand.Cheeseburger;
import com.mstudios.monorailmadnessand.IndicatorEntity;
import com.mstudios.monorailmadnessand.Scene;
import com.mstudios.monorailmadnessand.entities.MonorailCat;
import com.mstudios.monorailmadnessand.entities.NyanCat;
import com.mstudios.monorailmadnessand.scenes.GameScene;

public class ShieldBurger extends Cheeseburger{

	public ShieldBurger(GameScene gameScene, int layer) {
		super(gameScene, layer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyModifier(MonorailCat monorailCat){
		monorailCat.addShield();
		gameScene.addEntity(new IndicatorEntity(gameScene, Scene.FOREGROUND, "Shields Up!"));
	}
}
