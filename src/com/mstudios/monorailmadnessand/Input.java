package com.mstudios.monorailmadnessand;

public class Input {
	GameView view;
	boolean touchEvent;
	Vector2f touchLocation;
	int triggerCount;
	
	public Input(GameView view){
		this.view = view;
		touchEvent = false;
		touchLocation = new Vector2f(0.0f, 0.0f);
		triggerCount = 0;
	}
	
	public boolean check(){
		return touchEvent;
	}
	
	public void trigger(Vector2f v1){
		touchEvent = true;
		triggerCount++;
		touchLocation = v1.scale(1/view.getWidthScaling(), 1/view.getHeightScaling());
	}
	
	public Vector2f getTouchLocation(){
		return touchLocation;
	}
	
	
	public void handle(){
		touchEvent = false;
	}
	
	public int getTriggerCount(){
		return triggerCount;
	}
	
	

}
