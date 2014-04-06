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
import com.mstudios.monorailmadnessand.Vector2f;

public class LoseScene extends Scene{
	
	int score;
	Sprite sprite0;
	Sprite sprite1;
	
	
	public LoseScene(SceneManager manager, String title, int score) {
		super(manager, title);
		this.score = score;
		sprite0 = new Sprite("CEILINGCAT");
		sprite1 = new Sprite("MONORAILCAT");
	}
	
	@Override
	public void update(float dt, Input input){
		super.update(dt, input);
		
		if(input.check()){
			input.handle();
			getManager().addScene(GameView.MAIN_SCENE, new MainScene(getManager(), "MAIN_SCENE"));
			getManager().setScene(GameView.MAIN_SCENE);
		}

	}
	
	@Override
	public void render(Canvas canvas, Paint paint){
		super.render(canvas, paint);
		paint.setColor(Color.BLACK);
		canvas.drawRect(new RectF(0, 0, GameParams.getWindow().getX(), GameParams.getWindow().getY()), paint);
		
		sprite0.drawSprite(new Vector2f((2*GameParams.getWindow().getX())/3,(GameParams.getWindow().getY())/5), canvas, paint);
		sprite1.drawSprite(new Vector2f((2*GameParams.getWindow().getX())/3,(2*GameParams.getWindow().getY())/3), canvas, paint);
		
		paint.setColor(Color.WHITE);
		canvas.drawText("You haz reached the end of the lines...", 100, 400, paint);
		canvas.drawText("Score: " + score, 100, 448, paint);
		
		canvas.drawText("Credits ", 100, 600, paint);
		canvas.drawText("Ronnie Bar-Kochba - Programming", 100, 648, paint);
		canvas.drawText("Evan Slimak - Programming", 100, 696, paint);
		canvas.drawText("Dan Drexel - Sound", 100, 744, paint);
		
		canvas.drawText("Special Thanks to UB Hacking!", 100, 820, paint);
	}
}
