package com.mstudios.monorailmadnessand.scenes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;

import com.mstudios.monorailmadnessand.Input;
import com.mstudios.monorailmadnessand.Scene;
import com.mstudios.monorailmadnessand.SceneManager;
import com.mstudios.monorailmadnessand.entities.TitleEntity;
import com.mstudios.monorailmadnessand.entities.Tree;
public class MainScene extends GameScene{

	public MainScene(SceneManager manager, String title) {
		super(manager, title);

		addEntity(new TitleEntity(this, Scene.COREGROUND));
	}

	
	@Override
	public MediaPlayer getMediaPlayer(){
		return mediaPlayer;
	}

	
	@Override
	public void spawnCats(){}
	
	@Override
	public void spawnBurgers(){}
	
	@Override
	public void spawnBackground(){
		double rand = Math.random();
		if(rand < .05)
			addEntity(new Tree(this,Scene.BACKGROUND, "TREE0"));
		else if(rand < .10)
			addEntity(new Tree(this,Scene.BACKGROUND, "TREE1"));
		else if(rand < .15)
			addEntity(new Tree(this,Scene.BACKGROUND, "TREE2"));
		else if(rand < .18)
			addEntity(new Tree(this,Scene.BACKGROUND, "LEANING"));
		else if(rand < .21)
			addEntity(new Tree(this,Scene.BACKGROUND, "BURJ"));
	}

	@Override
	public void update(float dt, Input input){
		super.update(dt, input);
		backGround.update(dt, input);
		foreGround.update(dt, input);

	}
	@Override
	public void render(Canvas canvas, Paint paint){
		backGround.render(canvas, paint);
		super.render(canvas, paint);
		foreGround.render(canvas, paint);
	}

}
