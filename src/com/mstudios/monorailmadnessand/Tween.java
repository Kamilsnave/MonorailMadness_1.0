package com.mstudios.monorailmadnessand;

public class Tween {
	public static final int SLIDE_IN = 0;
	public static final int LINEAR = 1;

	float start, pos, end, vel;
	float speed;
	boolean running;
	int type;
	
	Tween parentTween;
	Tween nextTween;
	
	float trigger;
	
	boolean triggered;

	public Tween(float start, float end, float speed, int type){
		this.start = start;
		this.end = end;
		this.speed = speed;
		this.type = type;

		running = false;
		triggered = false;
		
		pos = start;
		
		trigger = 10;
	}
	

	public void update(float dt){
		if(running){
			switch(type){
			case SLIDE_IN:
				pos += (end-pos)/speed;
				break;
			case LINEAR:
				pos += (end-start)/speed;
				break;
			}
		}
		
		if(Math.abs(end-pos) < trigger){
			if(nextTween != null && !nextTween.isRunning()){
				nextTween.start();
			}
			triggered = true;
		}
		else{
			triggered = false;
		}
	}

	public float getPosition(){
		return pos;
	}

	public void start(){
		running = true;
	}
	
	public void reverse(){
		float temp;
		temp = end;
		end = start;
		start = temp;
		
		/*Tween tempTween = parentTween;
		parentTween = nextTween;
		nextTween = tempTween;*/
	}
	
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void stop(){
		running = false;
	}
	
	public void setNextTween(Tween tween){
		tween.setParentTween(this);
		nextTween = tween;
	}
	
	public void setParentTween(Tween tween){
		parentTween = tween;
	}
	
	public void setTrigger(float trigger){
		this.trigger = trigger;
	}
	
	public boolean triggered(){
		return triggered;
	}
}
