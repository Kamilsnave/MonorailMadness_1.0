package com.mstudios.monorailmadnessand.scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.mstudios.monorailmadnessand.GameParams;
import com.mstudios.monorailmadnessand.GameView;
import com.mstudios.monorailmadnessand.Input;
import com.mstudios.monorailmadnessand.Scene;
import com.mstudios.monorailmadnessand.SceneManager;
import com.mstudios.monorailmadnessand.Sprite;

public class SplashScene extends Scene{
	Sprite sprite;
	public SplashScene(SceneManager manager, String title) {
		super(manager, title);
		sprite = new Sprite("UBHACKING");
	}
	
	@Override
	public void update(float dt, Input input){
		super.update(dt, input);
		if(input.check()){
			input.handle();
			getManager().addScene(GameView.MAIN_SCENE, new MainScene(getManager(), "MAIN_SCENE"));
			getManager().setScene(GameView.MAIN_SCENE);
			//getManager().addScene(GameView.STORY_SCENE, new StoryScene(getManager(), "STORY_SCENE"));
			//getManager().setScene(GameView.STORY_SCENE);
		}
	}
	
	@Override
	public void render(Canvas canvas, Paint paint){
		super.render(canvas, paint);
		paint.setColor(Color.WHITE);
		canvas.drawRect(new RectF(0, 0, GameParams.getWindow().getX(), GameParams.getWindow().getY()), paint);
		sprite.drawCenteredSprite(GameParams.getWindow().scale(0.5f, 0.5f), canvas, paint);
	}
}
