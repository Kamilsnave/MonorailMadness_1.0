package com.mstudios.monorailmadnessand.scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.mstudios.monorailmadnessand.GameParams;
import com.mstudios.monorailmadnessand.GameView;
import com.mstudios.monorailmadnessand.Input;
import com.mstudios.monorailmadnessand.ResourceManager;
import com.mstudios.monorailmadnessand.Scene;
import com.mstudios.monorailmadnessand.SceneManager;
import com.mstudios.monorailmadnessand.Sprite;

public class StoryScene extends Scene{
	Sprite prophetCat;
	Sprite boredCat;
	Sprite catnarok;
	Sprite storymonorail;
	int cutscene;
	
	public StoryScene(SceneManager manager, String title) {
		super(manager, title);
		prophetCat = new Sprite("PROPHETCAT");
		boredCat = new Sprite("BOREDCAT");
		catnarok = new Sprite("CATNAROK");
		storymonorail = new Sprite("STORYMONORAIL");
		
		boredCat.setDimensions(550, 450);
		cutscene = 0;
	}
	
	@Override
	public void update(float dt, Input input){
		super.update(dt, input);
		if(input.check()){
			input.handle();
			
			switch(cutscene){
			case 0:
				ResourceManager.getAudio("DEATH0").start();
				break;
			case 1:
				ResourceManager.getAudio("DEATH1").start();
				break;
			case 2:
				ResourceManager.getAudio("DEATH2").start();
				break;
			case 3:
				ResourceManager.getAudio("DEATH3").start();
				break;
			case 4:
				ResourceManager.getAudio("DEATH4").start();
				break;
			case 5:
				ResourceManager.getAudio("DEATH5").start();
				break;
			case 6:
				ResourceManager.getAudio("DEATH6").start();
				break;
			case 7:
				break;
			case 8:
				break;
			}
			
			
			
			
			cutscene++;
			if(cutscene >= 9){
				getManager().addScene(GameView.GAME_SCENE, new GameScene(getManager(), "GAME_SCENE"));
				getManager().setScene(GameView.GAME_SCENE);
			}
		}
	}
	
	@Override
	public void render(Canvas canvas, Paint paint){
		super.render(canvas, paint);
		paint.setColor(Color.BLACK);
		canvas.drawRect(new RectF(0, 0, GameParams.getWindow().getX(), GameParams.getWindow().getY()), paint);
		paint.setColor(Color.WHITE);
		
		
		switch(cutscene){
		case 0:
			prophetCat.drawCenteredSprite(GameParams.getWindow().scale(0.8f,0.5f), canvas, paint);
			canvas.drawText("\"Stay a while and listen...\"", 200, GameParams.getWindow().getY()/2-24, paint);
			
			break;
		case 1:
			boredCat.drawCenteredSprite(GameParams.getWindow().scale(0.2f,0.5f), canvas, paint);
			canvas.drawText("\"What are you yelling about today old cat?\"", 800, GameParams.getWindow().getY()/2-24, paint);
			break;
		case 2:
			prophetCat.drawCenteredSprite(GameParams.getWindow().scale(0.8f,0.5f), canvas, paint);
			canvas.drawText("\"The humans have forgotten about us cats.\"", 200, GameParams.getWindow().getY()/2-24, paint);
			canvas.drawText("\"The days of Catnarok are apon us!\"", 200, GameParams.getWindow().getY()/2+24+48, paint);
			break;
		case 3:
			boredCat.drawCenteredSprite(GameParams.getWindow().scale(0.2f,0.5f), canvas, paint);
			canvas.drawText("\"It's time for you to go home old cat.\"", 800, GameParams.getWindow().getY()/2-24, paint);
			break;
		case 4:
			boredCat.drawCenteredSprite(GameParams.getWindow().scale(0.2f,0.5f), canvas, paint);
			canvas.drawText("\"WHAT WAS THAT NOISE?!\"", 800, GameParams.getWindow().getY()/2-24, paint);
			break;
		case 5:
			prophetCat.drawCenteredSprite(GameParams.getWindow().scale(0.8f,0.5f), canvas, paint);
			canvas.drawText("\"IT'S HERE!!!!!\"", 400, GameParams.getWindow().getY()/2-24, paint);
			break;
		case 6:
			prophetCat.setDimensions(700, 500);
			prophetCat.drawCenteredSprite(GameParams.getWindow().scale(0.6f,0.5f), canvas, paint);
			canvas.drawText("\"IT'S HERE!!!!!\"", 400, GameParams.getWindow().getY()/2-24, paint);
			break;
		case 7:
			catnarok.drawCenteredSprite(GameParams.getWindow().scale(0.5f,0.4f), canvas, paint);
			canvas.drawText("\"CATNARRRRRROOOKKKK!!!!\"", 600, GameParams.getWindow().getY()*0.85f, paint);
			break;
		case 8:
			storymonorail.drawCenteredSprite(GameParams.getWindow().scale(0.5f,0.5f), canvas, paint);
			canvas.drawText("\"I must reach the end of the line...\"", 600, GameParams.getWindow().getY()*0.85f, paint);
			break;
		}
		
		
		
		
		
		
		
		
		
		
	}

}
