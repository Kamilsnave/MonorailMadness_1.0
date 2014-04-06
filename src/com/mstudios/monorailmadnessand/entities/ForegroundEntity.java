package com.mstudios.monorailmadnessand.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.mstudios.monorailmadnessand.GameEntity;
import com.mstudios.monorailmadnessand.GameParams;
import com.mstudios.monorailmadnessand.Input;
import com.mstudios.monorailmadnessand.scenes.GameScene;

public class ForegroundEntity extends GameEntity{

	float time;
	int prog;
	int foreColor;
	double period;
	double freq;

	public ForegroundEntity(GameScene gameScene, int layer) {
		super(gameScene, layer);
		period = 120.0;
		freq = 1/period;
	}

	@Override
	public void update(float dt, Input input){
		time += dt;

		//2pi0.16time
		prog =  (int)((Math.cos(2*Math.PI*freq*time)*0.25 + 0.75)*255);
		
		if(time >= period){
			time = 0;
		}

		foreColor = Color.argb(255-prog, 0,0, 50);
	}

	@Override
	public void render(Canvas canvas, Paint paint){
		paint.setColor(foreColor);
		canvas.drawRect(0, 0, GameParams.getWindow().getX(), GameParams.getWindow().getY(), paint);
	}
}
