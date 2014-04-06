package com.mstudios.monorailmadnessand.scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;

import com.mstudios.monorailmadnessand.Input;
import com.mstudios.monorailmadnessand.ResourceManager;
import com.mstudios.monorailmadnessand.Scene;
import com.mstudios.monorailmadnessand.SceneManager;
import com.mstudios.monorailmadnessand.entities.BackgroundEntity;
import com.mstudios.monorailmadnessand.entities.ForegroundEntity;
import com.mstudios.monorailmadnessand.entities.GrumpyCat;
import com.mstudios.monorailmadnessand.entities.HoverCat;
import com.mstudios.monorailmadnessand.entities.LongCat;
import com.mstudios.monorailmadnessand.entities.MonorailCat;
import com.mstudios.monorailmadnessand.entities.NinjaCat;
import com.mstudios.monorailmadnessand.entities.TacGnol;
import com.mstudios.monorailmadnessand.entities.Tree;
import com.mstudios.monorailmadnessand.powerups.AmmoBurger;
import com.mstudios.monorailmadnessand.powerups.NyanBurger;
import com.mstudios.monorailmadnessand.powerups.ShieldBurger;

public class GameScene extends Scene{
	int score, level;	
	public MonorailCat monorailCat;
	float time;
	BackgroundEntity backGround;
	ForegroundEntity foreGround;
	MediaPlayer mediaPlayer;


	public GameScene(SceneManager manager, String title) {
		super(manager, title);

		mediaPlayer = ResourceManager.getAudio("THEME");
		mediaPlayer.setLooping(true);
		


		backGround = new BackgroundEntity(this, Scene.BACKGROUND);
		foreGround = new ForegroundEntity(this, Scene.FOREGROUND);

		score = 0;
		level = 1;


		monorailCat = new MonorailCat(this, Scene.COREGROUND);
		addEntity(monorailCat);
	}
	
	@Override
	public void setAsCurrentScene(){
		mediaPlayer.seekTo(0);
		mediaPlayer.start();
	}
	
	public MediaPlayer getMediaPlayer(){
		return mediaPlayer;
	}

	public void spawnCats(){
		if(Math.random() <= 0.03 + level*0.005) addEntity(new LongCat(this, Scene.COREGROUND));
		if(Math.random() <= 0.01 + level*0.005) addEntity(new NinjaCat(this, Scene.COREGROUND));
		if(Math.random() <= 0.0003) addEntity(new GrumpyCat(this, Scene.COREGROUND));
		if(Math.random() <= 0.01 + level*0.005) addEntity(new TacGnol(this, Scene.COREGROUND));
		if(Math.random() <= 0.005 + level*0.005) addEntity(new HoverCat(this, Scene.COREGROUND));


	}


	public void spawnBurgers(){
		if(Math.random() <= 0.01) addEntity(new AmmoBurger(this, Scene.COREGROUND));
		if(Math.random() <= 0.01) addEntity(new NyanBurger(this, Scene.COREGROUND));
		if(Math.random() <= 0.01) addEntity(new ShieldBurger(this, Scene.COREGROUND));
	}

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

	public void addScore(int score){

		this.score += score;
		updateLevel();
	}
	public void updateLevel(){

		level = score/10000 + 1;
	}

	public void update(float dt, Input input){
		super.update(dt, input);
		backGround.update(dt, input);
		foreGround.update(dt, input);

		if(monorailCat.isAlive()){
			this.addScore(10);
			time += dt;
			if(time >= 0.50){
				spawnCats();
				spawnBurgers();
				spawnBackground();
				time = 0;
			}
		}

	}
	public void render(Canvas canvas, Paint paint){
		backGround.render(canvas, paint);
		super.render(canvas, paint);
		foreGround.render(canvas, paint);

		paint.setTextSize(48.0f);
		paint.setColor(Color.WHITE);
		canvas.drawText("Score: "+ (int)score , 10, 1*48, paint);
		canvas.drawText("Level: "+ (int)level , 10, 2*48, paint);
		canvas.drawText("Ammo: "+ monorailCat.getAmmo() , 10, 3*48, paint);

		if(monorailCat.getShield()){
			paint.setColor(Color.GREEN);
			canvas.drawText("Shield Up!" , 10, 4*48, paint);
		}else{
			paint.setColor(Color.RED);
			canvas.drawText("Shield Down" , 10, 4*48, paint);
		}

	}
	
	public int getScore(){return score;}


}
